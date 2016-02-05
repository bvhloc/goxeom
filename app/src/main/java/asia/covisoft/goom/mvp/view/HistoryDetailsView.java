package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoaddetailhistoryRoot.Loaddetailhistory.Foodlist;

public interface HistoryDetailsView {

    void setTitle(String title);
    void onDriverInfo(String driverName, String driverPhone);
    void onInfoLoaded(String datetime, String addressFrom, String addressTo, String cost);
    void onMapDraw(String requestUrl);
    void onFoodsLoaded(List<Foodlist> foods);
}
