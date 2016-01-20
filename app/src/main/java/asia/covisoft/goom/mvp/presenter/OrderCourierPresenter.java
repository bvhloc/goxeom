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
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.model.OrderPresenter;
import asia.covisoft.goom.mvp.view.OrderCourierView;
import asia.covisoft.goom.pojo.gson.LoadcourierRoot;
import asia.covisoft.goom.pojo.gson.LoadcourierRoot.Loadcourier;
import asia.covisoft.goom.utils.Constant;

public class OrderCourierPresenter extends OrderPresenter {

    private OrderCourierView view;
    private Context context;

    public OrderCourierPresenter(OrderCourierView view) {
        super(view);
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void getDriver(final String token, final double lat, final  double lng) {
        new AsyncTask<Void, Void, List<Loadcourier>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected List<Loadcourier> doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "loadcourier.php?token=" + token +
                        "&latitude=" + lat +
                        "&longitude=" + lng;
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
            protected void onPostExecute(List<Loadcourier> drivers) {
                super.onPostExecute(drivers);

                progressDialog.dismiss();
                if (drivers == null)
                    view.onConnectionFail();
                else {
                    view.onDriverReady(drivers);
                }
            }

        }.execute();
    }

    @Override
    public void getCost(String userToken, double latFrom, double lngFrom, double latTo, double lngTo, double cost) {
        super.getCost(userToken, latFrom, lngFrom, latTo, lngTo, cost);
    }
}
