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
import asia.covisoft.goom.mvp.model.SettingsSignupModel;
import asia.covisoft.goom.mvp.view.SettingsSignupView;
import asia.covisoft.goom.utils.Constant;

public class SettingsSignupPresenter {

    private SettingsSignupView view;
    private Context context;

    public SettingsSignupPresenter(SettingsSignupView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void signUp(SettingsSignupModel model) {

        new AsyncTask<String, Void, Integer>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected Integer doInBackground(String... params) {

                int result = 0;
                final String URL = Constant.HOST + "signup.php?type=user&userid=" + params[0]
                        + "&pass=" + new MD5().encrypt(params[1])
                        + "&email=" + params[2]
                        + "&phonenumber=" + params[3];
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject jsonRootObject = new JSONObject(json);

                    result = jsonRootObject.optInt("signup");

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);

                progressDialog.dismiss();

                if (result == 0)
                    view.onConnectionFail();
                else
                    view.onSignup(result);
            }

        }.execute(model.getUsername(), model.getPassword(), model.getEmail(), model.getPhone());
    }

    public void loginForToken(SettingsSignupModel model) {

        final double lat = new GPSTracker(context).getLatitude();
        final double lng = new GPSTracker(context).getLongitude();

        new AsyncTask<String, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
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
                if (token.equals(""))
                    view.onConnectionFail();
                else
                    view.onTokenReady(token);
            }

        }.execute(model.getUsername(), model.getPassword());
    }
}
