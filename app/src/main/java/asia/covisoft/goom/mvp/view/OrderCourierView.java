package asia.covisoft.goom.mvp.view;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadcourierRoot;

/**
 * Created by Covisoft on 16/12/2015.
 */
public interface OrderCourierView {

    void onMapReady(LatLng currentLatLng);
    void onDriverReady(List<LoadcourierRoot.Loadcourier> drivers);
    void onConnectionFail();
}
