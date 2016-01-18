package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.view.OrderCourierView;
import asia.covisoft.goom.pojo.gson.LoadcourierRoot;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Preferences;

public class OrderCourierPresenter {

    private OrderCourierView view;
    private Context context;

    public OrderCourierPresenter(OrderCourierView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void getDriver() {
        GPSTracker gpsTracker = new GPSTracker(context);
        new AsyncTask<Double, Void, List<LoadcourierRoot.Loadcourier>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected List<LoadcourierRoot.Loadcourier> doInBackground(Double... params) {

                String token = context.getSharedPreferences(Preferences.LOGIN_PREFERENCES, Context.MODE_PRIVATE)
                        .getString(Preferences.LOGIN_PREFERENCES_TOKEN, "");
                String URL = Constant.HOST +
                        "loadcourier.php?token=" + token +
                        "&latitude=" + params[0] +
                        "&longitude=" + params[1];
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    LoadcourierRoot root = new Gson().fromJson(json, LoadcourierRoot.class);

                    return root.getLoadcourier();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JsonSyntaxException e) {
                    return new ArrayList<>();
                }

                return null;
            }

            @Override
            protected void onPostExecute(List<LoadcourierRoot.Loadcourier> drivers) {
                super.onPostExecute(drivers);

                progressDialog.dismiss();
                if (drivers == null)
                    view.onConnectionFail();
                else {
                    view.onDriverReady(drivers);
                }
            }

        }.execute(gpsTracker.getLatitude(), gpsTracker.getLongitude());
    }
}
