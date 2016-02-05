package asia.covisoft.goom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    private TabFragment tabFragment;
    private SharedPreferences loginPreferences;
    private SharedPreferences sharedPreferences;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
//        Log.d("sdb", "registered: " + sharedPreferences.getString(Preferences.GCM_TOKEN, ""));
        if (validGmsVersion()) {
            initMap();
        } else {
            sharedPreferences.edit()
                    .putBoolean(Preferences.INVALID_GMS, true)
                    .apply();
        }
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

    @SuppressWarnings("ResourceType")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMyLocationEnabled(true);
        findViewById(R.id.mapContainer).setVisibility(View.GONE);
        progressDialog.dismiss();

        checkLogin();
    }

    private void checkLogin() {

        int tabPos = getIntent().getIntExtra(Constant.TAB_POSTION, 1);

        if (!getIntent().getBooleanExtra(Extras.IS_LOGIN, false)) {

            loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);

            loginPreferences.edit()
                    .putString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "")
            .apply();

            initScreen(tabPos);

            String username = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USERNAME, "");
            String password = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_PASSWORD, "");
            if (!username.equals("") && !password.equals("")) {
                new SettingsLoginPresenter(this).login(username, password);
            }
        }else {
            initScreen(tabPos);
        }
    }

    @Override
    public void onConnectionFail() {

    }

    @Override
    public void onLogin(SettingsLoginModel model) {

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
