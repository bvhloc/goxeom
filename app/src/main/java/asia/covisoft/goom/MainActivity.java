package asia.covisoft.goom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.eventbus.ActivityResultEvent;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.MD5;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.utils.Preferences;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TabFragment tabFragment;
    private SharedPreferences loginPreferences;

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

        progressDialog = ProgressDialog.show(MainActivity.this, "", getString(R.string.dialog_loading));
        initMap();
    }

    @SuppressWarnings("ResourceType")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMyLocationEnabled(true);
        findViewById(R.id.mapContainer).setVisibility(View.GONE);
        progressDialog.dismiss();

        int tabPos = getIntent().getIntExtra(Constant.TAB_POSTION, 1);
        initScreen(tabPos);
        checkLogin();
    }

    private void initMap() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mMap);
        mapFragment.getMapAsync(this);
    }

    private void checkLogin() {

        if (!getIntent().getBooleanExtra(Extras.IS_LOGIN, false)) {

            loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);
            String username = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USERNAME, "");
            String password = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_PASSWORD, "");
            if (!username.equals("") && !password.equals("")) {
                loginForToken(username, password);
            }
        }
    }

    public void loginForToken(String username, String password) {

        final double lat = new GPSTracker(this).getLatitude();
        final double lng = new GPSTracker(this).getLongitude();

        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(MainActivity.this, "", getString(R.string.dialog_loading));
            }

            @Override
            protected String doInBackground(String... params) {

                String token = "";
                final String URL = Constant.HOST +
                        "login.php?userid=" + params[0] +
                        "&pass=" + new MD5().encrypt(params[1]) +
                        "&lat=" + lat +
                        "&long=" + lng;
                Log.d("sdb", URL);
                try {
//                    Log.d("sdb", URL);
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject jsonRootObject = new JSONObject(json);

                    JSONObject loginObject = jsonRootObject.optJSONObject("login");

                    int result = loginObject.optInt("Value");

                    if (result == 1)
                        token = loginObject.optString("Token");

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return token;
            }

            @Override
            protected void onPostExecute(String token) {
                super.onPostExecute(token);

                progressDialog.dismiss();
                if (!token.equals("")) {
                    loginPreferences.edit()
                            .putString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, token)
                            .apply();
                }
                int tabPos = getIntent().getIntExtra(Constant.TAB_POSTION, 1);
                initScreen(tabPos);
            }

        }.execute(username, password);
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

        if (!tabFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class
            super.onBackPressed();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //push received data to fragment by EventBus
        EventBus.getDefault().post(new ActivityResultEvent(requestCode, resultCode, data));
    }
}
