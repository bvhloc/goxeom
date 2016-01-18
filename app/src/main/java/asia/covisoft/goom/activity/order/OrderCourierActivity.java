package asia.covisoft.goom.activity.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
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
import asia.covisoft.goom.helper.DatetimeHelper;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.mvp.model.OrderCourierModel;
import asia.covisoft.goom.mvp.presenter.OrderCourierPresenter;
import asia.covisoft.goom.mvp.view.OrderCourierView;
import asia.covisoft.goom.pojo.gson.LoadcourierRoot;
import asia.covisoft.goom.utils.DatetimeFormat;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.utils.RequestCodes;

public class OrderCourierActivity extends BaseActivity implements OrderCourierView, GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private ScrollView scrollView;
    private TextView tvDriverName, tvAddressFrom, tvAddressTo, tvContactFrom, tvContactTo;

    private void initView() {
        setContentView(R.layout.activity_order_courier);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tvDriverName = (TextView) findViewById(R.id.tvDriverName);
        tvAddressFrom = (TextView) findViewById(R.id.tvAddressFrom);
        tvAddressTo = (TextView) findViewById(R.id.tvAddressTo);
        tvContactFrom = (TextView) findViewById(R.id.tvContactFrom);
        tvContactTo = (TextView) findViewById(R.id.tvContactTo);
        findViewById(R.id.lnlPickFrom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(mContext, OrderPickLocationActivity.class),
                        RequestCodes.PICK_LOCATION_FROM);
            }
        });
        findViewById(R.id.lnlPickTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(mContext, OrderPickLocationActivity.class),
                        RequestCodes.PICK_LOCATION_TO);
            }
        });
        findViewById(R.id.lnlContactFrom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(mContext, OrderPickContactActivity.class),
                        RequestCodes.PICK_CONTACT_FROM);
            }
        });
        findViewById(R.id.lnlContactTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(mContext, OrderPickContactActivity.class),
                        RequestCodes.PICK_CONTACT_TO);
            }
        });
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderConfirmActivity.class));
            }
        });
    }

    private Context mContext;
    private OrderCourierPresenter presenter;
    private OrderCourierModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = new OrderCourierPresenter(this);
        model = new OrderCourierModel();
        initView();

        initMap();
    }

    private void initMap() {

        MapsInitializer.initialize(mContext);

        WorkaroundMapFragment mapFragment = (WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap);
        mapFragment.setOnTouchListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {

                scrollView.requestDisallowInterceptTouchEvent(true);
            }
        });
        mapFragment.getMapAsync(this);
    }

    private HashMap<Marker, LoadcourierRoot.Loadcourier> driverHashMap;

    @Override
    public boolean onMarkerClick(Marker marker) {

        LoadcourierRoot.Loadcourier driver = driverHashMap.get(marker);

        Intent intent = new Intent(mContext, OrderPickDriverActivity.class);

        intent.putExtra(Extras.DRIVER_ID, driver.getDriverId());

        intent.putExtra(Extras.DRIVER_NAME, new Hex().toString(driver.getFullName()));

        Calendar birthCalendar = new DatetimeHelper().getCalendar(driver.getBirthDay(), DatetimeFormat.SERVER_DATE_FORMAT);
        int driverAge = Calendar.getInstance().get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        intent.putExtra(Extras.DRIVER_AGE, driverAge);

        intent.putExtra(Extras.DRIVER_TOKEN, driver.getToken());

        intent.putExtra(Extras.DRIVER_LATLNG, marker.getPosition());

        startActivityForResult(intent, RequestCodes.PICK_DRIVER);

        return true;
    }

    private GoogleMap mMap;

    @SuppressWarnings("ResourceType")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // Check if we were successful in obtaining the map.
        if (mMap != null) {

            GPSTracker gpsTracker = new GPSTracker(mContext);
            LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
            mMap.setMyLocationEnabled(true);

            mMap.setOnMarkerClickListener(this);

            presenter.getDriver();
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
                LatLng driverLatLng = new LatLng(Double.valueOf(driver.getLatitude()), Double.valueOf(driver.getLongitude()));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            LatLng receivedLatLng;
            switch (requestCode) {
                case RequestCodes.PICK_DRIVER:

                    model.driverName = data.getStringExtra(Extras.RECEIVED_DRIVER_NAME);
                    model.driverToken = data.getStringExtra(Extras.RECEIVED_DRIVER_TOKEN);

                    tvDriverName.setText(model.driverName);

                    break;
                case RequestCodes.PICK_LOCATION_FROM:

                    receivedLatLng = data.getParcelableExtra(Extras.RECEIVED_LATLNG);
                    model.latFrom = receivedLatLng.latitude;
                    model.lngFrom = receivedLatLng.longitude;
                    model.addressFrom = data.getStringExtra(Extras.RECEIVED_ADDRESS);

                    tvAddressFrom.setText(model.addressFrom);

                    break;
                case RequestCodes.PICK_LOCATION_TO:

                    receivedLatLng = data.getParcelableExtra(Extras.RECEIVED_LATLNG);
                    model.latTo = receivedLatLng.latitude;
                    model.lngTo = receivedLatLng.longitude;
                    model.addressTo = data.getStringExtra(Extras.RECEIVED_ADDRESS);

                    tvAddressTo.setText(model.addressTo);

                    break;
                case RequestCodes.PICK_CONTACT_FROM:

                    model.contactNameFrom = data.getStringExtra(Extras.RECEIVED_CONTACT_NAME);
                    model.contactPhoneFrom = data.getStringExtra(Extras.RECEIVED_CONTACT_PHONE);

                    tvContactFrom.setText(model.contactNameFrom +"\n"+model.contactPhoneFrom);

                    break;
                case RequestCodes.PICK_CONTACT_TO:

                    model.contactNameTo = data.getStringExtra(Extras.RECEIVED_CONTACT_NAME);
                    model.contactPhoneTo = data.getStringExtra(Extras.RECEIVED_CONTACT_PHONE);

                    tvContactTo.setText(model.contactNameTo +"\n"+model.contactPhoneTo);

                    break;
            }
        }
    }
}
