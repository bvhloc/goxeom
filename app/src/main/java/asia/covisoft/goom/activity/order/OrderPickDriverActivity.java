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
import asia.covisoft.goom.mvp.presenter.OrderPickDriverPresenter;
import asia.covisoft.goom.mvp.view.OrderPickDriverView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.R;
import asia.covisoft.goom.customview.WorkaroundMapFragment;

public class OrderPickDriverActivity extends BaseActivity implements OrderPickDriverView {

    private Context mContext;
    private OrderPickDriverPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pick_driver);
        mContext = this;
        presenter = new OrderPickDriverPresenter(this);
        initView();

        presenter.setupMap(getIntent().getExtras());
    }

    private ScrollView scrollView;

    private void initView() {

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        findViewById(R.id.btnBook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }

    private GoogleMap mMap;

    @Override
    public void onMapReady(LatLng driverLatLng) {

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
                mMap.addMarker(new MarkerOptions().position(driverLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(driverLatLng, 14));
                mMap.setMyLocationEnabled(true);
            }
        }
    }
}
