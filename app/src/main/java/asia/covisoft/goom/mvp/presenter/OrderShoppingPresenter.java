package asia.covisoft.goom.mvp.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.mvp.view.OrderShoppingView;

public class OrderShoppingPresenter {

    private OrderShoppingView view;
    private Context context;

    public OrderShoppingPresenter(OrderShoppingView view) {
        this.view = view;
        this.context = (Context) view;
    }


    public void setupMap() {

        GPSTracker gpsTracker = new GPSTracker(context);
        LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

        view.onMapReady(currentLatLng);
    }
}
