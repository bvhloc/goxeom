package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.MD5;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.model.SettingsLoginModel;
import asia.covisoft.goom.mvp.view.SettingsLoginView;
import asia.covisoft.goom.utils.Constant;

public class SettingsLoginPresenter {

    private SettingsLoginView view;
    private Context context;

    public SettingsLoginPresenter(SettingsLoginView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void login(final SettingsLoginModel model) {

        final double lat = new GPSTracker(context).getLatitude();
        final double lng = new GPSTracker(context).getLongitude();

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

        }.execute(model.getUsername(), model.getPassword());
    }
}
