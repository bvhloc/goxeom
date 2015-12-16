package asia.covisoft.goom.mvp.presenter;

import android.content.Context;
import com.google.android.gms.maps.model.LatLng;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.mvp.view.HistoryDetailsView;

public class HistoryDetailsPresenter {

    private HistoryDetailsView view;
    private Context context;

    public HistoryDetailsPresenter(HistoryDetailsView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void setupTitle(boolean HISTORY_STATE){

        String newTitle = HISTORY_STATE ? context.getString(R.string.fragment_history_completed) : context.getString(R.string.fragment_history_inprocess);
        view.setTitle(newTitle);
    }

    public void setupMap() {

        GPSTracker gpsTracker = new GPSTracker(context);
        LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

        view.onMapReady(currentLatLng);
    }
}
