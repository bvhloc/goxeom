package asia.covisoft.goom.mvp.view;

import com.google.android.gms.maps.model.LatLng;

import asia.covisoft.goom.mvp.model.OrderPickDriverModel;

/**
 * Created by Covisoft on 21/12/2015.
 */
public interface OrderPickDriverView {

    void onMapReady(LatLng currentLatLng);
    void setDriverInfo(OrderPickDriverModel model);
}
