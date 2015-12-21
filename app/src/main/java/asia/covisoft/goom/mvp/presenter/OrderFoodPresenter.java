package asia.covisoft.goom.mvp.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.mvp.view.OrderFoodView;

/**
 * Created by Covisoft on 21/12/2015.
 */
public class OrderFoodPresenter {

    private OrderFoodView view;
    private Context context;

    public OrderFoodPresenter(OrderFoodView view) {
        this.view = view;
        this.context = (Context) view;
    }


    public void setupMap() {

        GPSTracker gpsTracker = new GPSTracker(context);
        LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

        view.onMapReady(currentLatLng);
    }
}
