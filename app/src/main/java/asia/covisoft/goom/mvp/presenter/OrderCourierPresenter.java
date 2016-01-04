package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
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

public class OrderCourierPresenter {

    private OrderCourierView view;
    private Context context;

    public OrderCourierPresenter(OrderCourierView view) {
        this.view = view;
        this.context = (Context) view;
    }


    public void setupMap() {

        GPSTracker gpsTracker = new GPSTracker(context);
        LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

        view.onMapReady(currentLatLng);
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

                String URL = Constant.HOST +
                        "loadcourier.php?lat=" + params[0] +
                        "&long=" + params[1];
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
