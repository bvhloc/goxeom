package asia.covisoft.goom.fragment.history;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import asia.covisoft.goom.Constant;
import asia.covisoft.goom.GPSTracker;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.RootFragment;

public class HistoryDetailsFragment extends RootFragment {

    public static HistoryDetailsFragment newInstance(boolean isCompleted) {

        HistoryDetailsFragment f = new HistoryDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.HISTORY_STATE, isCompleted);
        f.setArguments(bundle);
        return f;
    }

    private ScrollView scrollView;
    private TextView tvTitle;
    private Button btnCancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_details, container, false);

        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
        tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);

        return rootView;
    }

    private GoogleMap mMap;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String newTitle = getArguments().getBoolean(Constant.HISTORY_STATE) ? getString(R.string.fragment_history_completed) : getString(R.string.fragment_history_inprocess);
        newTitle = tvTitle.getText() + " - " + newTitle;
        tvTitle.setText(newTitle);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        setUpMap();
    }

    private void setUpMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mMap))
                    .getMap();

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                GPSTracker gpsTracker = new GPSTracker(this.getContext());
                LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
                mMap.addMarker(new MarkerOptions().position(currentLatLng).title(getString(R.string.lowcase_your_location)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
                mMap.setMyLocationEnabled(true);
            }
        }
    }
}
