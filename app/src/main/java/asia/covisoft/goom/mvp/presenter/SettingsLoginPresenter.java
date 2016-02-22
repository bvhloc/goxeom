package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.GPSTracker;
import asia.covisoft.goom.utils.MD5;
import asia.covisoft.goom.utils.NetworkClient;
import asia.covisoft.goom.mvp.model.SettingsLoginModel;
import asia.covisoft.goom.mvp.view.SettingsLoginView;
import asia.covisoft.goom.prefs.Constant;
import asia.covisoft.goom.prefs.Preferences;

public class SettingsLoginPresenter {

    private SettingsLoginView view;
    private Context context;

    public SettingsLoginPresenter(SettingsLoginView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void login(final String username, final String password) {

        final SettingsLoginModel model = new SettingsLoginModel();
        model.setUsername(username);
        model.setPassword(password);
        final double lat = new GPSTracker(context).getLatitude();
        final double lng = new GPSTracker(context).getLongitude();
        final String gcmToken = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Preferences.GCM_TOKEN, "");

        new AsyncTask<String, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected Boolean doInBackground(String... params) {

                boolean result = false;
                final String URL = Constant.HOST +
                        "login.php?userid=" + username +
                        "&pass=" + MD5.encrypt(password) +
                        "&lat=" + lat +
                        "&long=" + lng +
                        "&registration=" + gcmToken;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject jsonRootObject = new JSONObject(json);

                    JSONObject loginObject = jsonRootObject.optJSONObject("login");

                    model.setLoginResult(loginObject.optInt("Value"));
                    if (model.getLoginResult() == 2) {
                        model.setFailCount(loginObject.optInt("False"));
                    } else if (model.getLoginResult() == 1) {
                        model.setToken(loginObject.optString("Token"));
                    }

                    result = true;

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);

                progressDialog.dismiss();
                if (!success)
                    view.onConnectionFail();
                else
                    view.onLogin(model);
            }

        }.execute();
    }
}
