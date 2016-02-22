package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.activeandroid.query.From;
import com.activeandroid.query.Select;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.DatetimeHelper;
import asia.covisoft.goom.utils.Hex;
import asia.covisoft.goom.utils.NetworkClient;
import asia.covisoft.goom.mvp.model.OrderCourierModel;
import asia.covisoft.goom.mvp.model.OrderFoodOrderedModel;
import asia.covisoft.goom.mvp.model.OrderShoppingModel;
import asia.covisoft.goom.mvp.model.OrderTransportModel;
import asia.covisoft.goom.mvp.view.OrderConfirmView;
import asia.covisoft.goom.pojo.activeandroid.LocationHistory;
import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;
import asia.covisoft.goom.prefs.Constant;
import asia.covisoft.goom.prefs.DatetimeFormat;

public class OrderConfirmPresenter {

    private OrderConfirmView view;
    private Context context;

    public OrderConfirmPresenter(OrderConfirmView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void saveHistory(String address, double lat, double lng) {

//        From query = new Select()
//                .from(LocationHistory.class)
//                .where(LocationHistory.COL_ADDRESS + " = '" + address +
//                        "' and " + LocationHistory.COL_LAT + " = '" + lat +
//                        "' and " + LocationHistory.COL_LNG + " = '" + lng + "'");
        From query = new Select()
                .from(LocationHistory.class)
                .where(LocationHistory.COL_LAT + " = '" + lat + "' and " +
                        LocationHistory.COL_LNG + " = '" + lng + "'");
        int count = query.count();
        if (count == 0) {
            LocationHistory history = new LocationHistory();
            String datetime = new DatetimeHelper()
                    .getString(Calendar.getInstance(), DatetimeFormat.APP_HISTORY_DATETIME_FORMAT);
            history.setDatetime(datetime);
            history.setAddress(address);
            String latitute = String.valueOf(lat);
            String longitute = String.valueOf(lng);
            history.setLat(latitute);
            history.setLng(longitute);
            history.save();
        }
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

                String addressFrom = Hex.encode(model.addressFrom);
                String addressTo = Hex.encode(model.addressTo);
                String detailsFrom = Hex.encode(model.detailsFrom);
                String detailsTo = Hex.encode(model.detailsTo);
                String contactNameFrom = Hex.encode(model.contactNameFrom);
                String contactNameTo = Hex.encode(model.contactNameTo);
                String items = Hex.encode(model.items);

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
                    Log.d("sdb", json);

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

                String addressFrom = Hex.encode(model.addressFrom);
                String addressTo = Hex.encode(model.addressTo);
                String detailsFrom = Hex.encode(model.detailsFrom);

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
                    Log.d("sdb", json);

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
                    int resultValue = result.optInt("settransport");
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

    public void bookShopping(final OrderShoppingModel model) {
        new AsyncTask<Void, Void, JSONObject>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @SuppressWarnings("UnnecessaryLocalVariable")
            @Override
            protected JSONObject doInBackground(Void... params) {

                String addressFrom = Hex.encode(model.addressFrom);
                String addressTo = Hex.encode(model.addressTo);
                String detailsFrom = Hex.encode(model.shopDetails);
                String detailsTo = Hex.encode(model.locationDetails);
                String items = Hex.encode(model.items);

                String URL = Constant.HOST +
                        "setshopping.php?usertoken=" + model.userToken +
                        "&drivertoken=" + model.driverToken +
                        "&shoppingfrom=" + addressFrom +
                        "&shoppingfromdetail=" + detailsFrom +
                        "&shoppingfromlat=" + model.latFrom +
                        "&shoppingfromlong=" + model.lngFrom +
                        "&shoppingto=" + addressTo +
                        "&shoppingtodetail=" + detailsTo +
                        "&shoppingtolat=" + model.latTo +
                        "&shoppingtolong=" + model.lngTo +
                        "&type=buy" +
                        "&iteminfo=" + items +
                        "&cost=" + model.cost;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);
                    Log.d("sdb", json);

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
                    int resultValue = result.optInt("setshopping");
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

    public void bookFooding(final OrderFoodOrderedModel model) {
        new AsyncTask<Void, Void, JSONObject>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @SuppressWarnings("UnnecessaryLocalVariable")
            @Override
            protected JSONObject doInBackground(Void... params) {

                String addressFrom = model.addressFrom;
                String addressTo = Hex.encode(model.addressTo);
                String detailsTo = Hex.encode(model.detailsTo);
                String foodIds = "";
                String foodQuantities = "";
                String foodNotes = "";
                for (Foodlist food : model.foods) {
                    foodIds += ";" + food.getFoodId();
                    foodQuantities += ";" + food.getQuatity();
                    if (food.getNote() == null) {
                        food.setNote("");
                    }
                    foodNotes += ";" + Hex.encode(food.getNote());
                }
                foodIds = foodIds.substring(1);
                foodQuantities = foodQuantities.substring(1);
                foodNotes = foodNotes.substring(1);

                String URL = Constant.HOST +
                        "setfooding.php?usertoken=" + model.userToken +
                        "&drivertoken=" +
                        "&foodingfrom=" + addressFrom +
                        "&foodingfromdetail=20" +
                        "&foodingfromlat=" + model.latFrom +
                        "&foodingfromlong=" + model.lngFrom +
                        "&foodingto=" + addressTo +
                        "&foodingtodetail=" + detailsTo +
                        "&foodingtolat=" + model.latTo +
                        "&foodingtolong=" + model.lngTo +
                        "&cost=" + model.cost +
                        "&picknow=" + model.pickNow +
                        "&type=buy&time=20" +
                        "&foodid=" + foodIds +
                        "&foodnumber=" + foodQuantities +
                        "&foodnote=" + foodNotes;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);
                    Log.d("sdb", json);

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
                    int resultValue = result.optInt("setfooding");
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
