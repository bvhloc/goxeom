package asia.covisoft.goom.fragment.order;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import asia.covisoft.goom.Constant;
import asia.covisoft.goom.GPSTracker;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.RootFragment;
import asia.covisoft.goom.view.WorkaroundMapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderPickDriverFragment extends RootFragment {


    public static OrderPickDriverFragment newInstance(double lat, double lng) {
        // Required empty public constructor
        OrderPickDriverFragment f = new OrderPickDriverFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(Constant.DRIVER_LAT, lat);
        bundle.putDouble(Constant.DRIVER_LNG, lng);
        f.setArguments(bundle);
        return f;
    }

    private ScrollView scrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_pick_driver, container, false);

        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
        rootView.findViewById(R.id.btnBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });

        return rootView;
    }

    private GoogleMap mMap;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpMap(getArguments().getDouble(Constant.DRIVER_LAT), getArguments().getDouble(Constant.DRIVER_LNG));
    }

    private void setUpMap(double lat, double lng) {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((WorkaroundMapFragment) getChildFragmentManager().findFragmentById(R.id.mMap))
                    .getMap();
            ((WorkaroundMapFragment) getChildFragmentManager().findFragmentById(R.id.mMap)).setOnTouchListener(new WorkaroundMapFragment.OnTouchListener() {
                @Override
                public void onTouch() {

                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
            });
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                LatLng diverLatLng = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(diverLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(diverLatLng, 14));
                mMap.setMyLocationEnabled(true);
            }
        }
    }
}
