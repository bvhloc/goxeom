package asia.covisoft.goom.mvp.presenter;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

import asia.covisoft.goom.mvp.model.OrderPickDriverModel;
import asia.covisoft.goom.mvp.view.OrderPickDriverView;
import asia.covisoft.goom.utils.Constant;

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

    public void getDiverInfo(Bundle extras) {

        OrderPickDriverModel model = new OrderPickDriverModel();

        model.setId(extras.getString(Constant.DRIVER_ID, ""));
        model.setName(extras.getString(Constant.DRIVER_NAME, ""));
        model.setAge(extras.getInt(Constant.DRIVER_AGE, 0));

        view.setDriverInfo(model);
    }
}
