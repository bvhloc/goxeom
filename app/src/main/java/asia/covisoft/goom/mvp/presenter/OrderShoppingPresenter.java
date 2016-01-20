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
import asia.covisoft.goom.mvp.view.OrderShoppingView;
import asia.covisoft.goom.pojo.gson.LoadshoppingRoot;
import asia.covisoft.goom.pojo.gson.LoadshoppingRoot.Loadshopping;
import asia.covisoft.goom.utils.Constant;

public class OrderShoppingPresenter extends OrderPresenter {

    private OrderShoppingView view;
    private Context context;

    public OrderShoppingPresenter(OrderShoppingView view) {
        super(view);
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void getDriver(final String token, final double lat, final double lng) {
        new AsyncTask<Void, Void, List<Loadshopping>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected List<Loadshopping> doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "loadshopping.php?token=" + token +
                        "&latitude=" + lat +
                        "&longitude=" + lng;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    LoadshoppingRoot root = new Gson().fromJson(json, LoadshoppingRoot.class);

                    return root.getLoadshopping();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JsonSyntaxException e) {
                    return new ArrayList<>();
                }

                return null;
            }

            @Override
            protected void onPostExecute(List<Loadshopping> drivers) {
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
