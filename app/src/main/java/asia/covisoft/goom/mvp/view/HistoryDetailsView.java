package asia.covisoft.goom.mvp.view;

public interface HistoryDetailsView {

    void setTitle(String title);
    void onDriverInfo(String driverName, String driverPhone);
    void onInfoLoaded(String datetime, String addressFrom, String addressTo, String cost);
    void onMapDraw(String requestUrl);
}
