package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.DatetimeHelper;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.model.SettingsProfileModel;
import asia.covisoft.goom.mvp.view.SettingsProfileView;
import asia.covisoft.goom.pojo.gson.LoadinfoRoot;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.DatetimeFormat;

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
                    model.birthday = info.getBirthDay();
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

    public void updateInfo(final String token, final SettingsProfileModel model) {

        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @SuppressWarnings("UnnecessaryLocalVariable")
            @Override
            protected Integer doInBackground(Void... params) {

                String fullname = new Hex().fromString(model.newFullname);
                Calendar birthCal = new DatetimeHelper().getCalendar(model.newBirthday, DatetimeFormat.APP_DATE_FORMAT);
                String birthday = new DatetimeHelper().getString(birthCal, DatetimeFormat.SERVER_DATE_FORMAT);

                String URL = Constant.HOST +
                        "updateinfo.php?token=" + token +
                        "&fullname=" + fullname +
                        "&pass=" + model.newPassword +
                        "&email=" + model.newEmail +
                        "&phonenumber=" + model.newPhone +
                        "&birthday=" + birthday +
                        "&avatar=&status=active";
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject rootObject = new JSONObject(json);
                    int result = rootObject.optInt("updateinfo");

                    return result;

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return 0;
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);

                progressDialog.dismiss();

                if (result == 0)
                    view.onConnectionFail();
                else
                    view.onInfoUpdate(result);
            }
        }.execute();
    }

}
