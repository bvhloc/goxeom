package asia.covisoft.goom.mvp.view;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Covisoft on 21/12/2015.
 */
public interface OrderPickDriverView {

    void initView();
    void onMapReady(LatLng currentLatLng);
}
