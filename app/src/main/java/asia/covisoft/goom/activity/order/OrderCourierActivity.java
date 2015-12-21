package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.R;
import asia.covisoft.goom.customview.WorkaroundMapFragment;
import asia.covisoft.goom.mvp.presenter.OrderCourierPresenter;
import asia.covisoft.goom.mvp.view.OrderCourierView;

public class OrderCourierActivity extends BaseActivity implements OrderCourierView {

    private Context mContext;
    private OrderCourierPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_courier);
        mContext = this;
        presenter = new OrderCourierPresenter(this);
        initView();

        presenter.setupMap();
    }

    private ScrollView scrollView;
    @Override
    public void initView() {

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        findViewById(R.id.lnlPickFrom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderPickLocationActivity.class));
            }
        });
        findViewById(R.id.lnlPickTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderPickLocationActivity.class));
            }
        });
        findViewById(R.id.lnlContactFrom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderPickContactActivity.class));
            }
        });
        findViewById(R.id.lnlContactTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderPickContactActivity.class));
            }
        });
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderConfirmActivity.class));
            }
        });
    }

    private GoogleMap mMap;
    @Override
    public void onMapReady(LatLng currentLatLng) {

//        MapsInitializer.initialize(mContext);

        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap))
                    .getMap();
            ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap)).setOnTouchListener(new WorkaroundMapFragment.OnTouchListener() {
                @Override
                public void onTouch() {

                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
            });
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                double lat = currentLatLng.latitude;
                double lng = currentLatLng.longitude;
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

                        Intent intent = new Intent(mContext, OrderPickDriverActivity.class);
                        intent.putExtra(Constant.DRIVER_LATLNG, marker.getPosition());
                        startActivity(intent);

                        return true;
                    }
                });
            }
        }
    }
}
