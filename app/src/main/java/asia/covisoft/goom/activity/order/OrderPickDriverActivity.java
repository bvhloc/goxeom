package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.customview.WorkaroundMapFragment;
import asia.covisoft.goom.mvp.model.OrderPickDriverModel;
import asia.covisoft.goom.mvp.presenter.OrderPickDriverPresenter;
import asia.covisoft.goom.mvp.view.OrderPickDriverView;
import asia.covisoft.goom.utils.Extras;

public class OrderPickDriverActivity extends BaseActivity implements OrderPickDriverView, OnMapReadyCallback, View.OnClickListener {

    private Context mContext;
    private OrderPickDriverPresenter presenter;
    private OrderPickDriverModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pick_driver);
        mContext = this;
        presenter = new OrderPickDriverPresenter(this);
        model = new OrderPickDriverModel();
        initView();

        Bundle extras = getIntent().getExtras();
        presenter.getDiverInfo(extras);
        initMap();
    }

    private ScrollView scrollView;
    private TextView tvId, tvName, tvAge;

    private void initView() {

        tvId = (TextView) findViewById(R.id.tvId);
        tvName = (TextView) findViewById(R.id.tvName);
        tvAge = (TextView) findViewById(R.id.tvAge);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        findViewById(R.id.btnBook).setOnClickListener(this);
    }

    private void initMap() {

        WorkaroundMapFragment mapFragment = ((WorkaroundMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mMap));
        mapFragment.setOnTouchListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {

                scrollView.requestDisallowInterceptTouchEvent(true);
            }
        });
        mapFragment.getMapAsync(this);
    }

    private GoogleMap mMap;

    @SuppressWarnings("ResourceType")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng driverLatLng = getIntent().getParcelableExtra(Extras.DRIVER_LATLNG);
        // Check if we were successful in obtaining the map.
        if (mMap != null) {
            mMap.addMarker(new MarkerOptions().position(driverLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(driverLatLng, 14));
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void setDriverInfo(OrderPickDriverModel model) {

        this.model = model;
        tvId.setText(model.id);
        tvName.setText(model.name);
        tvAge.setText(model.age + "");
    }


    @Override
    public void onClick(View v) {

        Intent data = new Intent();
        data.putExtra(Extras.PICKED_DRIVER_NAME, model.name);
        data.putExtra(Extras.PICKED_DRIVER_TOKEN, model.token);
        setResult(RESULT_OK, data);
        finish();
    }
}
