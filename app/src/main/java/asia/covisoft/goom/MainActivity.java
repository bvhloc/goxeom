package asia.covisoft.goom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import asia.covisoft.goom.eventbus.ActivityResultEvent;
import asia.covisoft.goom.mvp.model.SettingsLoginModel;
import asia.covisoft.goom.mvp.presenter.SettingsLoginPresenter;
import asia.covisoft.goom.mvp.view.SettingsLoginView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.utils.Preferences;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, SettingsLoginView {

    private Context mContext;
    private TabFragment tabFragment;
    private SharedPreferences loginPreferences;
    private SharedPreferences sharedPreferences;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

//        if (savedInstanceState == null) {
//            // withholding the previously created fragment from being created again
//            // On orientation change, it will prevent fragment recreation
//            // its necessary to reserving the fragment stack inside each tab
//            int tabPos = getIntent().getIntExtra(Constant.TAB_POSTION, 1);
//            initScreen(tabPos);
//
//        } else {
//            // restoring the previously created fragment
//            // and getting the reference
//            tabFragment = (TabFragment) getSupportFragmentManager().getFragments().get(0);
//        }


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (validGmsVersion()) {
            initMap();
        } else {
            sharedPreferences.edit()
                    .putBoolean(Preferences.INVALID_GMS, true)
                    .apply();
        }

        int tabPos = getIntent().getIntExtra(Constant.TAB_POSTION, 1);
        initScreen(tabPos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferences.getBoolean(Preferences.INVALID_GMS, false)) {
            if (validGmsVersion()) {
                initMap();
                sharedPreferences.edit()
                        .putBoolean(Preferences.INVALID_GMS, false)
                        .apply();
            }
        }
    }

    private boolean validGmsVersion() {

        int gmsVersion = 0;
        try {
            gmsVersion = getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return gmsVersion >= getResources().getInteger(R.integer.google_play_services_version);
    }

    private void initMap() {

        progressDialog = ProgressDialog.show(MainActivity.this, "", getString(R.string.dialog_loading));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mMap);
        mapFragment.getMapAsync(this);
    }

    private final int PERMISSIONS_REQUEST_ACCESS_LOCATION = 1;
    private GoogleMap mMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(mContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mContext,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(mContext,
//                    Manifest.permission.READ_CONTACTS)) {
//
//                // Show an expanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(mContext,
//                        new String[]{Manifest.permission.READ_CONTACTS},
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
            ActivityCompat.requestPermissions((Activity) mContext,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_LOCATION);
        } else {

            mMap.setMyLocationEnabled(true);
            checkLogin();
        }
        progressDialog.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //noinspection MissingPermission
                    mMap.setMyLocationEnabled(true);
                    checkLogin();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    MainActivity.this.finish();
                }
//                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void checkLogin() {

        if (!getIntent().getBooleanExtra(Extras.IS_LOGIN, false)) {

            loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);

            loginPreferences.edit()
                    .putString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "")
                    .apply();

            String username = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USERNAME, "");
            String password = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_PASSWORD, "");
            if (!username.equals("") && !password.equals("")) {
                progressDialog = ProgressDialog.show(MainActivity.this, "", getString(R.string.dialog_loading));
                new SettingsLoginPresenter(this).login(username, password);
            }
        }
    }

    @Override
    public void onConnectionFail() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(getString(R.string.dialog_connection_fail))
                .setNeutralButton(getString(R.string.lowcase_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .show();
    }

    @Override
    public void onLogin(SettingsLoginModel model) {

        progressDialog.dismiss();
        String userToken = model.getToken();
        if (!userToken.equals("")) {
            loginPreferences.edit()
                    .putString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, userToken)
                    .apply();
        } else {
            loginPreferences.edit()
                    .putString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "")
                    .apply();
        }
        int tabPos = getIntent().getIntExtra(Constant.TAB_POSTION, 1);
        initScreen(tabPos);
    }

    private void initScreen(int tabPos) {
        // Creating the ViewPager container fragment once
        tabFragment = TabFragment.newInstance(tabPos);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, tabFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {

        if (tabFragment != null) {
            if (!tabFragment.onBackPressed()) {
                // container Fragment or its associates couldn't handle the back pressed task
                // delegating the task to super class
                super.onBackPressed();
            }
        } else {
//            this.moveTaskToBack(true);
            MainActivity.this.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //push received data to fragment by EventBus
        EventBus.getDefault().post(new ActivityResultEvent(requestCode, resultCode, data));
    }
}
