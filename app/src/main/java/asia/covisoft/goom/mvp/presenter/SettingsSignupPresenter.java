package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.MD5;
import asia.covisoft.goom.utils.NetworkClient;
import asia.covisoft.goom.mvp.model.SettingsSignupModel;
import asia.covisoft.goom.mvp.view.SettingsSignupView;
import asia.covisoft.goom.prefs.Constant;

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
                        + "&pass=" + MD5.encrypt(params[1])
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
}
