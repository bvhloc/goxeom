package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.model.SettingsProfileModel;
import asia.covisoft.goom.mvp.view.SettingsProfileView;
import asia.covisoft.goom.pojo.gson.LoadinfoRoot;
import asia.covisoft.goom.utils.Constant;

public class SettingsProfilePresenter {

    private SettingsProfileView view;
    private Context context;

    public SettingsProfilePresenter(SettingsProfileView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void loadInfo(String token) {

        final SettingsProfileModel model = new SettingsProfileModel();

        new AsyncTask<String, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected Boolean doInBackground(String... params) {

                String URL = Constant.HOST +
                        "loadinfo.php?token=" + params[0];
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    LoadinfoRoot root = new Gson().fromJson(json, LoadinfoRoot.class);
                    LoadinfoRoot.Loadinfo info = root.getLoadinfo();

                    model.username = info.getUserName();
                    model.email = info.getEmail();
                    model.fullname = new Hex().toString(info.getFullName());
                    model.phone = info.getPhoneNumber();
                    model.password = info.getPassWord();

                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);

                progressDialog.dismiss();

                if (success)
                    view.onInfoLoaded(model);
                else
                    view.onConnectionFail();
            }

        }.execute(token);
    }


}
