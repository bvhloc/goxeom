package asia.covisoft.goom.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.DistanceMatrix;
import asia.covisoft.goom.utils.Hex;
import asia.covisoft.goom.utils.NetworkClient;
import asia.covisoft.goom.utils.PolylineDrawer;
import asia.covisoft.goom.mvp.view.HistoryDetailsView;
import asia.covisoft.goom.pojo.gson.LoaddetailhistoryRoot;
import asia.covisoft.goom.pojo.gson.LoaddetailhistoryRoot.Loaddetailhistory;
import asia.covisoft.goom.service.CancelTipService;
import asia.covisoft.goom.prefs.Constant;
import asia.covisoft.goom.prefs.Preferences;

public class HistoryDetailsPresenter {

    private HistoryDetailsView view;
    private Context context;

    public HistoryDetailsPresenter(HistoryDetailsView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void loadInfo(final String id) {

        final String userToken = context
                .getSharedPreferences(Preferences.LOGIN_PREFERENCES, Activity.MODE_PRIVATE)
                .getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");

        new AsyncTask<String, Void, Loaddetailhistory>() {

            @Override
            protected Loaddetailhistory doInBackground(String... params) {

                String URL = Constant.HOST +
                        "loaddetailhistory.php?token=" + userToken +
                        "&id=" + id;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    LoaddetailhistoryRoot rootObject = new Gson().fromJson(json, LoaddetailhistoryRoot.class);

                    return rootObject.getLoaddetailhistory();

                } catch (IOException | JsonSyntaxException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Loaddetailhistory result) {
                super.onPostExecute(result);
                if (result != null) {

                    //get driver info
                    Loaddetailhistory.Driver driver = result.getDriver();
                    if (driver != null) {

                        String driverName = Hex.decode(driver.getFullName());
                        String driverPhone = driver.getPhoneNumber();

                        view.onDriverInfo(driverName, driverPhone);
                    }

                    //get trading info
                    String bookType = String.valueOf(id.charAt(0));
                    String datetime = "";
                    String addressFrom = "";
                    String latFrom = "";
                    String lngFrom = "";
                    String addressTo = "";
                    String latTo = "";
                    String lngTo = "";
                    String cost = "";
                    switch (bookType) {
                        case Constant.BOOK_TYPE_COURIER:
                            Loaddetailhistory.Detailhistorycourier historycourier = result.getDetailhistorycourier();
                            datetime = historycourier.getCourierDate();
                            addressFrom = Hex.decode(historycourier.getCourierFrom());
                            addressTo = Hex.decode(historycourier.getCourierTo());
                            latFrom = historycourier.getCourierFromLat();
                            lngFrom = historycourier.getCourierFromLong();
                            latTo = historycourier.getCourierToLat();
                            lngTo = historycourier.getCourierToLong();
                            cost = historycourier.getCourierCost();

                            view.onItemsLoaded(historycourier.getCourierItem());
                            break;
                        case Constant.BOOK_TYPE_TRANSPORT:
                            Loaddetailhistory.Detailhistorytransport historytransport = result.getDetailhistorytransport();
                            datetime = historytransport.getTransportDate();
                            addressFrom = Hex.decode(historytransport.getTransportFrom());
                            addressTo = Hex.decode(historytransport.getTransportTo());
                            latFrom = historytransport.getTransportFromLat();
                            lngFrom = historytransport.getTransportFromLong();
                            latTo = historytransport.getTransportToLat();
                            lngTo = historytransport.getTransportToLong();
                            cost = historytransport.getTransportCost();
                            break;
                        case Constant.BOOK_TYPE_SHOPPING:
                            Loaddetailhistory.Detailhistoryshopping historyshopping = result.getDetailhistoryshopping();
                            datetime = historyshopping.getShoppingTime();
                            addressFrom = Hex.decode(historyshopping.getShoppingFrom());
                            addressTo = Hex.decode(historyshopping.getShoppingTo());
                            latFrom = historyshopping.getShoppingFromLat();
                            lngFrom = historyshopping.getShoppingFromLong();
                            latTo = historyshopping.getShoppingToLat();
                            lngTo = historyshopping.getShoppingToLong();
                            cost = historyshopping.getShoppingCost();

                            view.onItemsLoaded(historyshopping.getShoppingItem());
                            break;
                        case Constant.BOOK_TYPE_FOODING:
                            Loaddetailhistory.Detailhistoryfooding historyfooding = result.getDetailhistoryfooding();
                            datetime = historyfooding.getFoodingTime();
                            addressFrom = Hex.decode(historyfooding.getFoodingFrom());
                            addressTo = Hex.decode(historyfooding.getFoodingTo());
                            latFrom = historyfooding.getFoodingFromLat();
                            lngFrom = historyfooding.getFoodingFromLong();
                            latTo = historyfooding.getFoodingToLat();
                            lngTo = historyfooding.getFoodingToLong();
                            cost = historyfooding.getFoodingCost();

                            view.onFoodsLoaded(result.getFoodlist());
                            break;
                    }
                    view.onInfoLoaded(datetime, addressFrom, addressTo, cost);

                    double sourceLat = Double.parseDouble(latFrom);
                    double sourceLng = Double.parseDouble(lngFrom);
                    double destinationLat = Double.parseDouble(latTo);
                    double destinationLng = Double.parseDouble(lngTo);
                    LatLng source = new LatLng(sourceLat, sourceLng);
                    LatLng destination = new LatLng(destinationLat, destinationLng);

                    //make url to draw polyline
                    String requestUrl = PolylineDrawer.makeURL(context.getString(R.string.google_maps_key),
                            source, destination);

                    //build CameraUpdate
                    LatLngBounds bounds = new LatLngBounds.Builder()
                            .include(source)
                            .include(destination)
                            .build();
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 50);

                    //build markers
                    MarkerOptions sourceMarker = new MarkerOptions()
                            .icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_green_marker_transparent_small))
                            .position(source);
                    MarkerOptions destinationMarker = new MarkerOptions()
                            .icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_blue_marker_transparent_small))
                            .position(destination);

                    view.onMapDraw(requestUrl, cameraUpdate, sourceMarker, destinationMarker);
                } else {
                    view.onConnectionFail();
                }
            }
        }.execute();
    }

    private Handler countdownHandler;
    private Runnable countdownRunnable;

    public void countdown() {

        countdownHandler = new Handler();
        countdownRunnable = new Runnable() {
            @Override
            public void run() {
                if (CancelTipService.countdownTime >= 0) {
                    view.onCountDown(CancelTipService.countdownTime);
                    countdownHandler.postDelayed(countdownRunnable, 1000);
                }
            }
        };
        countdownHandler.post(countdownRunnable);
    }

    private Handler trackdriverHandler;
    private Runnable trackdriverRunnable;
    public boolean stopTracking;

    public void trackDriver(final String tradingId, final LatLng toLatLng) {
        trackdriverHandler = new Handler();
        trackdriverRunnable = new Runnable() {
            @Override
            public void run() {

                if (!stopTracking) {
                    requestDriverLocation(tradingId, toLatLng);
                    trackdriverHandler.postDelayed(trackdriverRunnable, 5000);
                }
            }
        };
        trackdriverHandler.post(trackdriverRunnable);
    }

    private void requestDriverLocation(final String tradingId, final LatLng toLatLng) {

        final String userToken = context
                .getSharedPreferences(Preferences.LOGIN_PREFERENCES, Activity.MODE_PRIVATE)
                .getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");

        new AsyncTask<Void, Void, LatLng>() {
            @Override
            protected LatLng doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "getlocation.php?token=" + userToken +
                        "&tradingid=" + tradingId;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject rootObject = new JSONObject(json);
                    JSONObject getlocation = rootObject.optJSONObject("getlocation");
                    int result = getlocation.getInt("Value");
                    if (result == 0) {
                        double latitude = getlocation.getDouble("latitude");
                        double longitude = getlocation.getDouble("longitude");
                        return new LatLng(latitude, longitude);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(LatLng driverLatLng) {
                super.onPostExecute(driverLatLng);
                if (driverLatLng != null) {
                    requestJourneyInfo(driverLatLng, toLatLng);
                }
            }
        }.execute();
    }

    private void requestJourneyInfo(final LatLng driverLatLng, final LatLng toLatLng) {

        DistanceMatrix.requestJourneyInfo(driverLatLng, toLatLng, new DistanceMatrix.OnInfoReceivedListener() {
            @Override
            public void onPreRequest() {

            }

            @Override
            public void onResultReceived(String originAddress, String destinationAddress, String distanceText, String distanceValue, String durationText, String durationValue) {

                MarkerOptions driverMarkerOptions = new MarkerOptions()
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.ic_marker_driver))
                        .position(driverLatLng);

                view.onDriverTracking(driverMarkerOptions, distanceText, durationText);
            }

            @Override
            public void onFail() {

            }
        });
    }

}
