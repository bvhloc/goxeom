package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.model.OrderCourierModel;
import asia.covisoft.goom.mvp.model.OrderTransportModel;
import asia.covisoft.goom.mvp.view.OrderConfirmView;
import asia.covisoft.goom.utils.Constant;

public class OrderConfirmPresenter {

    private OrderConfirmView view;
    private Context context;

    public OrderConfirmPresenter(OrderConfirmView view) {
        this.view = view;
        this.context = (Context) view;
    }

    private final int RESULT_BOOKING_SUCCESS = 0;
    private final int RESULT_DRIVER_UNAVAIBLE = 1;
    private final int RESULT_ACCOUNT_OUT_OF_MONEY = 2;
    private final int RESULT_BOOKING_FAIL = 3;

    private ProgressDialog progressDialog;

    public void bookCourier(final OrderCourierModel model) {
        new AsyncTask<Void, Void, JSONObject>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @SuppressWarnings("UnnecessaryLocalVariable")
            @Override
            protected JSONObject doInBackground(Void... params) {

                Hex hex = new Hex();
                String addressFrom = hex.fromString(model.addressFrom);
                String addressTo = hex.fromString(model.addressTo);
                String detailsFrom = hex.fromString(model.detailsFrom);
                String detailsTo = hex.fromString(model.detailsTo);
                String contactNameFrom = hex.fromString(model.contactNameFrom);
                String contactNameTo = hex.fromString(model.contactNameTo);
                String items = hex.fromString(model.items);

                String URL = Constant.HOST +
                        "setcourier.php?usertoken=" + model.userToken +
                        "&drivertoken=" + model.driverToken +
                        "&courierfrom=" + addressFrom +
                        "&courierfromdetail=" + detailsFrom +
                        "&courierfromlat=" + model.latFrom +
                        "&courierfromlong=" + model.lngFrom +
                        "&courierto=" + addressTo +
                        "&couriertodetail=" + detailsTo +
                        "&couriertolat=" + model.latTo +
                        "&couriertolong=" + model.lngTo +
                        "&contactnamefrom=" + contactNameFrom +
                        "&contactphonefrom=" + model.contactPhoneFrom +
                        "&contactnameto=" + contactNameTo +
                        "&contactphoneto=" + model.contactPhoneTo +
                        "&iteminfo=" + items +
                        "&cost=" + model.cost;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject result = new JSONObject(json);

                    return result;

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(JSONObject result) {
                super.onPostExecute(result);

                progressDialog.dismiss();
                if (result == null)
                    view.onConnectionFail();
                else {
                    int resultValue = result.optInt("setcourier");
                    switch (resultValue) {
                        case RESULT_BOOKING_SUCCESS:
                            String bookingId = result.optString("id");
                            view.onBookingMade(bookingId);
                            break;
                        case RESULT_DRIVER_UNAVAIBLE:
                            break;
                        case RESULT_ACCOUNT_OUT_OF_MONEY:
                            break;
                        case RESULT_BOOKING_FAIL:
                            break;
                    }
                }
            }

        }.execute();
    }

    public void bookTransport(final OrderTransportModel model) {
        new AsyncTask<Void, Void, JSONObject>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @SuppressWarnings("UnnecessaryLocalVariable")
            @Override
            protected JSONObject doInBackground(Void... params) {

                Hex hex = new Hex();
                String addressFrom = hex.fromString(model.addressFrom);
                String addressTo = hex.fromString(model.addressTo);
                String detailsFrom = hex.fromString(model.detailsFrom);

                String URL = Constant.HOST +
                        "settransport.php?usertoken=" + model.userToken +
                        "&drivertoken=" + model.driverToken +
                        "&transportfrom=" + addressFrom +
                        "&transportfromdetail=" + detailsFrom +
                        "&transportfromlat=" + model.latFrom +
                        "&transportfromlong=" + model.lngFrom +
                        "&transportto=" + addressTo +
                        "&transporttolat=" + model.latTo +
                        "&transporttolong=" + model.lngTo +
                        "&cost=" + model.cost;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject result = new JSONObject(json);

                    return result;

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(JSONObject result) {
                super.onPostExecute(result);

                progressDialog.dismiss();
                if (result == null)
                    view.onConnectionFail();
                else {
                    int resultValue = result.optInt("setcourier");
                    switch (resultValue) {
                        case RESULT_BOOKING_SUCCESS:
                            String bookingId = result.optString("id");
                            view.onBookingMade(bookingId);
                            break;
                        case RESULT_DRIVER_UNAVAIBLE:
                            break;
                        case RESULT_ACCOUNT_OUT_OF_MONEY:
                            break;
                        case RESULT_BOOKING_FAIL:
                            break;
                    }
                }
            }

        }.execute();
    }
}
