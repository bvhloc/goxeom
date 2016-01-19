package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.model.OrderCourierModel;
import asia.covisoft.goom.mvp.view.OrderCourierView;
import asia.covisoft.goom.pojo.gson.LoadcourierRoot;
import asia.covisoft.goom.utils.Constant;

public class OrderCourierPresenter {

    private OrderCourierView view;
    private Context context;

    public OrderCourierPresenter(OrderCourierView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void getDriver(final String token) {
        GPSTracker gpsTracker = new GPSTracker(context);
        new AsyncTask<Double, Void, List<LoadcourierRoot.Loadcourier>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected List<LoadcourierRoot.Loadcourier> doInBackground(Double... params) {

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

    public void getCost(final OrderCourierModel model) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected String doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "getcost.php?token=" + model.userToken +
                        "&type=C&fromlat=" + model.latFrom +
                        "&fromlong=" + model.lngFrom +
                        "&tolat=" + model.latTo +
                        "&tolong=" + model.lngTo +
                        "&itemcost=0";
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject rootObject = new JSONObject(json);

                    model.cost = rootObject.optString("getcost");

                    return model.cost;

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }

                return "";
            }

            @Override
            protected void onPostExecute(String cost) {
                super.onPostExecute(cost);

                progressDialog.dismiss();
                if (cost.isEmpty())
                    view.onConnectionFail();
                else {
                    view.onCostResult(model);
                }
            }

        }.execute();
    }
}
