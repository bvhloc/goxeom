package asia.covisoft.goom.mvp.view;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoaddetailhistoryRoot.Loaddetailhistory.Foodlist;

public interface HistoryDetailsView {

    void onDriverInfo(String driverName, String driverPhone);
    void onInfoLoaded(String datetime, String addressFrom, String addressTo, String cost);
    void onFoodsLoaded(List<Foodlist> foods);
    void onItemsLoaded(String items);
    void onMapDraw(String requestUrl, CameraUpdate cameraUpdate, MarkerOptions sourceMarker, MarkerOptions destinationMarker);
    void onConnectionFail();
    void onCountDown(int countdownTime);
}
