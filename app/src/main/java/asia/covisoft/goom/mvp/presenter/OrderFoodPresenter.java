package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.view.OrderFoodView;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding;
import asia.covisoft.goom.utils.Constant;

public class OrderFoodPresenter {

    private OrderFoodView view;
    private Context context;

    public OrderFoodPresenter(OrderFoodView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void getFooding(final String token, final double lat, final  double lng) {
        new AsyncTask<Void, Void, Loadfooding>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected Loadfooding doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "loadfooding.php?token=" + token +
                        "&latitude=" + lat +
                        "&longitude=" + lng;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    LoadfoodingRoot root = new Gson().fromJson(json, LoadfoodingRoot.class);

                    return root.getLoadfooding();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Loadfooding fooding) {
                super.onPostExecute(fooding);

                progressDialog.dismiss();
                if (fooding == null)
                    view.onConnectionFail();
                else {
                    view.onCategoriesLoaded(fooding.getCategory());
                    view.onRestaurantsLoaded(fooding.getRestaurantList());
                }
            }

        }.execute();
    }
}
