package asia.covisoft.goom.mvp.view;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Covisoft on 14/12/2015.
 */
public interface HistoryDetailsView {

    void initView();
    void setTitle(String title);
    void onMapReady(LatLng currentLatLng);
}
