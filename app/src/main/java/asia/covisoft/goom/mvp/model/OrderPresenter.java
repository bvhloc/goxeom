package asia.covisoft.goom.mvp.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.NetworkClient;
import asia.covisoft.goom.mvp.view.OrderView;
import asia.covisoft.goom.prefs.Constant;

public class OrderPresenter {

    private OrderView view;
    private Context context;

    public OrderPresenter(OrderView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private ProgressDialog progressDialog;

    public void getCost(final String userToken, final double latFrom, final double lngFrom, final double latTo, final double lngTo, final double cost) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @SuppressWarnings("UnnecessaryLocalVariable")
            @Override
            protected String doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "getcost.php?token=" + userToken +
                        "&type=C&fromlat=" + latFrom +
                        "&fromlong=" + lngFrom +
                        "&tolat=" + latTo +
                        "&tolong=" + lngTo +
                        "&itemcost=" + cost;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject rootObject = new JSONObject(json);

                    String cost = rootObject.optString("getcost");

                    return cost;

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
                    view.onCostResult(cost);
                }
            }

        }.execute();
    }
}
