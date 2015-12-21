package asia.covisoft.goom.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import asia.covisoft.goom.mvp.view.OrderPickDriverView;
import asia.covisoft.goom.utils.Constant;

/**
 * Created by Covisoft on 21/12/2015.
 */
public class OrderPickDriverPresenter {

    private OrderPickDriverView view;
    private Context context;

    public OrderPickDriverPresenter(OrderPickDriverView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void setupMap(Bundle extras) {

        LatLng driverLatLng = extras.getParcelable(Constant.DRIVER_LATLNG);
        view.onMapReady(driverLatLng);
    }
}
