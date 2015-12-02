package asia.covisoft.goom.fragment.order;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import asia.covisoft.goom.ActivityAnim;
import asia.covisoft.goom.Constant;
import asia.covisoft.goom.FragmentNavigator;
import asia.covisoft.goom.GPSTracker;
import asia.covisoft.goom.R;
import asia.covisoft.goom.activity.order.OrderConfirmActivity;
import asia.covisoft.goom.activity.order.OrderPickDriverActivity;
import asia.covisoft.goom.backpress.RootFragment;
import asia.covisoft.goom.view.CustomMapView;
import asia.covisoft.goom.view.WorkaroundMapFragment;
import asia.covisoft.goom.view.WorkaroundMapView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderCourierFragment extends RootFragment {


    public OrderCourierFragment() {
        // Required empty public constructor
    }

    private ScrollView scrollView;
    private MapView mapView;

    public int RESULT_CODE = 0;
    public Bundle RECEIVED_DATA = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_courier, container, false);

        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
        mapView = (MapView) rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        rootView.findViewById(R.id.lnlPickFrom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(OrderCourierFragment.this, new OrderPickLocationFragment());
            }
        });
        rootView.findViewById(R.id.lnlPickTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(OrderCourierFragment.this, new OrderPickLocationFragment());
            }
        });
        rootView.findViewById(R.id.lnlContactFrom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(OrderCourierFragment.this, new OrderPickContactFragment(OrderCourierFragment.this));
            }
        });
        rootView.findViewById(R.id.lnlContactTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(OrderCourierFragment.this, new OrderPickContactFragment(OrderCourierFragment.this));
            }
        });
        rootView.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), OrderConfirmActivity.class));
                ActivityAnim.forward(getActivity());
            }
        });

        return rootView;
    }

    private GoogleMap mMap;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpMap();
    }

    private void setUpMap() {

        MapsInitializer.initialize(this.getActivity());

        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = mapView
                    .getMap();

//            mapView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//
//                            Log.d("myDebug", "down");
//
//                            return true;
//                        case MotionEvent.ACTION_UP:
//
//                            Log.d("myDebug", "up");
//
//                            return true;
//                    }
//                    return true;
//                }
//            });
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                GPSTracker gpsTracker = new GPSTracker(this.getContext());
                double lat = gpsTracker.getLatitude();
                double lng = gpsTracker.getLongitude();
                LatLng currentLatLng = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(currentLatLng).title(getString(R.string.lowcase_your_location)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
                mMap.setMyLocationEnabled(true);

                LatLng latlng1 = new LatLng(lat + 0.002, lng + 0.002);
                mMap.addMarker(new MarkerOptions().position(latlng1).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));
                LatLng latlng2 = new LatLng(lat + 0.001, lng + 0.004);
                mMap.addMarker(new MarkerOptions().position(latlng2).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));
                LatLng latlng3 = new LatLng(lat - 0.001, lng - 0.004);
                mMap.addMarker(new MarkerOptions().position(latlng3).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));
                LatLng latlng4 = new LatLng(lat - 0.003, lng);
                mMap.addMarker(new MarkerOptions().position(latlng4).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        Intent intent = new Intent(getActivity(), OrderPickDriverActivity.class);
                        intent.putExtra(Constant.DRIVER_LAT, marker.getPosition().latitude);
                        intent.putExtra(Constant.DRIVER_LNG, marker.getPosition().longitude);
                        startActivity(intent);
                        ActivityAnim.forward(getActivity());

                        return true;
                    }
                });
            }
        }
    }

    @Override
    public void onResume() {

//        if (RESULT_CODE == 1) {
//
//            Log.d("myDebug", RECEIVED_DATA.getString("haha"));
//            RECEIVED_DATA.clear();
//            RESULT_CODE = 0;
//        }

        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
