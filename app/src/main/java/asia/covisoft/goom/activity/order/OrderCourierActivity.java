package asia.covisoft.goom.activity.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.customview.WorkaroundMapFragment;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.mvp.presenter.OrderCourierPresenter;
import asia.covisoft.goom.mvp.view.OrderCourierView;
import asia.covisoft.goom.pojo.gson.LoadcourierRoot;
import asia.covisoft.goom.utils.Constant;

public class OrderCourierActivity extends BaseActivity implements OrderCourierView, GoogleMap.OnMarkerClickListener {

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
        presenter.getDriver();
    }

    private ScrollView scrollView;

    private void initView() {

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
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
                mMap.setMyLocationEnabled(true);

                mMap.setOnMarkerClickListener(this);
            }
        }
    }

    @Override
    public void onDriverReady(List<LoadcourierRoot.Loadcourier> drivers) {

        if (drivers.isEmpty()) {
            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_nodrivernearby), Snackbar.LENGTH_LONG).show();
        } else {
            driverHashMap = new HashMap<>();
            for (LoadcourierRoot.Loadcourier driver : drivers) {

                String driverFullName = new Hex().toString(driver.getFullName());
                LatLng driverLatLng = new LatLng(Double.valueOf(driver.getLat()), Double.valueOf(driver.getLong()));
                Marker marker = mMap.addMarker(new MarkerOptions().title(driverFullName).position(driverLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));

                driverHashMap.put(marker, driver);
            }
        }
    }

    @Override
    public void onConnectionFail() {

        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_connection_fail))
                .setNeutralButton(getString(R.string.lowcase_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onBackPressed();
                    }
                })
                .show();
    }

    private HashMap<Marker, LoadcourierRoot.Loadcourier> driverHashMap;

    @Override
    public boolean onMarkerClick(Marker marker) {

        LoadcourierRoot.Loadcourier driver = driverHashMap.get(marker);

        Intent intent = new Intent(mContext, OrderPickDriverActivity.class);
        intent.putExtra(Constant.DRIVER_ID, driver.getDriverId());
        intent.putExtra(Constant.DRIVER_NAME, new Hex().toString(driver.getFullName()));
        int driverAge = Calendar.getInstance().get(Calendar.YEAR) - Integer.valueOf(driver.getBirthDay().substring(0, 4));
        intent.putExtra(Constant.DRIVER_AGE, driverAge);
        intent.putExtra(Constant.DRIVER_LATLNG, marker.getPosition());
        startActivity(intent);

        return true;
    }
}
