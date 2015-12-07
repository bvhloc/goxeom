package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.Constant;
import asia.covisoft.goom.R;
import asia.covisoft.goom.customview.WorkaroundMapFragment;

public class OrderPickDriverActivity extends BaseActivity {

    private Context mContext;

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pick_driver);
        mContext = this;

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        findViewById(R.id.btnBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        Bundle extra = getIntent().getExtras();
        setUpMap(extra.getDouble(Constant.DRIVER_LAT), extra.getDouble(Constant.DRIVER_LNG));
    }

    private GoogleMap mMap;

    private void setUpMap(double lat, double lng) {
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
                LatLng diverLatLng = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(diverLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(diverLatLng, 14));
                mMap.setMyLocationEnabled(true);
            }
        }
    }
}
