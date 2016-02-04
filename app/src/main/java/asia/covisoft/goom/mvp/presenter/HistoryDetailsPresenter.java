package asia.covisoft.goom.mvp.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.helper.PolylineDrawer;
import asia.covisoft.goom.mvp.view.HistoryDetailsView;
import asia.covisoft.goom.pojo.gson.LoaddetailhistoryRoot;
import asia.covisoft.goom.pojo.gson.LoaddetailhistoryRoot.Loaddetailhistory;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;

public class HistoryDetailsPresenter {

    private HistoryDetailsView view;
    private Context context;

    public HistoryDetailsPresenter(HistoryDetailsView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void setupTitle(Bundle extras) {

        boolean HISTORY_STATE = extras.getBoolean(Extras.HISTORY_STATE, true);
        String newTitle = HISTORY_STATE ? context.getString(R.string.upcase_completed) : context.getString(R.string.upcase_inprocess);
        view.setTitle(newTitle);
    }

    public void loadInfo(final String userToken, final String id) {
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
                            break;
                    }
                    view.onInfoLoaded(datetime, addressFrom, addressTo, cost);

                    String requestUrl = PolylineDrawer.makeURL(context.getString(R.string.google_maps_key),
                            Double.parseDouble(latFrom),
                            Double.parseDouble(lngFrom),
                            Double.parseDouble(latTo),
                            Double.parseDouble(lngTo));
                    view.onMapDraw(requestUrl);
                }
            }
        }.execute();
    }
}
