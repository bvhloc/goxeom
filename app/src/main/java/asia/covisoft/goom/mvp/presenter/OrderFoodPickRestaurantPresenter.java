package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.NetworkClient;
import asia.covisoft.goom.mvp.view.OrderFoodPickRestaurantView;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;
import asia.covisoft.goom.pojo.gson.RestaurantlistRoot;
import asia.covisoft.goom.prefs.Constant;

public class OrderFoodPickRestaurantPresenter {

    private OrderFoodPickRestaurantView view;
    private Context context;

    public OrderFoodPickRestaurantPresenter(OrderFoodPickRestaurantView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void getRestaurants(final String token, final String type) {
        new AsyncTask<Void, Void, List<RestaurantList>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected List<RestaurantList> doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "restaurantlist.php?token=" + token +
                        "&type=" + type;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    RestaurantlistRoot root = new Gson().fromJson(json, RestaurantlistRoot.class);

                    return root.getRestaurantlist();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(List<RestaurantList> restaurants) {
                super.onPostExecute(restaurants);

                progressDialog.dismiss();
                if (restaurants == null)
                    view.onConnectionFail();
                else {
                    view.onRestaurantsLoaded(restaurants);
                }
            }

        }.execute();
    }
}
