package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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
import asia.covisoft.goom.mvp.model.OrderTransportModel;
import asia.covisoft.goom.mvp.presenter.OrderTransportPresenter;
import asia.covisoft.goom.mvp.view.OrderTransportView;
import asia.covisoft.goom.pojo.gson.LoadtransportRoot;
import asia.covisoft.goom.utils.DatetimeFormat;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.utils.Preferences;
import asia.covisoft.goom.utils.RequestCodes;

public class OrderTransportActivity extends BaseActivity implements OrderTransportView, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private ScrollView scrollView;
    private TextView tvDriverName, tvAddressFrom, tvAddressTo;
    private EditText edtDetailsFrom;

    private void initView() {
        setContentView(R.layout.activity_order_transport);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tvDriverName = (TextView) findViewById(R.id.tvDriverName);
        tvAddressFrom = (TextView) findViewById(R.id.tvAddressFrom);
        tvAddressTo = (TextView) findViewById(R.id.tvAddressTo);
        edtDetailsFrom = (EditText) findViewById(R.id.edtDetailsFrom);
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
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                btnNextClicked();
            }
        });
    }
    
    private Context mContext;
    private OrderTransportPresenter presenter;
    private OrderTransportModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = new OrderTransportPresenter(this);
        model = new OrderTransportModel();
        initView();

        SharedPreferences loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);
        model.userToken = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");
        initMap();
    }

    private void initMap() {

        WorkaroundMapFragment mapFragment = ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap));
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
        // Check if we were successful in obtaining the map.
        if (mMap != null) {

            GPSTracker gpsTracker = new GPSTracker(mContext);
            LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
            mMap.setMyLocationEnabled(true);
            mMap.setOnMarkerClickListener(this);

            presenter.getDriver(model.userToken, currentLatLng.latitude, currentLatLng.longitude);
        }
    }

    @Override
    public void onDriverReady(List<LoadtransportRoot.Loadtransport> drivers) {

        if (drivers.isEmpty()) {
            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_nodrivernearby), Snackbar.LENGTH_LONG).show();
        } else {
            driverHashMap = new HashMap<>();
            for (LoadtransportRoot.Loadtransport driver : drivers) {

                String driverFullName = new Hex().toString(driver.getFullName());
                LatLng driverLatLng = new LatLng(Double.valueOf(driver.getLatitude()), Double.valueOf(driver.getLongitude()));
                Marker marker = mMap.addMarker(new MarkerOptions().title(driverFullName).position(driverLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)));

                driverHashMap.put(marker, driver);
            }
        }
    }

    private HashMap<Marker, LoadtransportRoot.Loadtransport> driverHashMap;

    @Override
    public boolean onMarkerClick(Marker marker) {

        LoadtransportRoot.Loadtransport driver = driverHashMap.get(marker);

        Intent intent = new Intent(mContext, OrderPickDriverActivity.class);

        intent.putExtra(Extras.DRIVER_ID, driver.getDriverId());

        intent.putExtra(Extras.DRIVER_NAME, new Hex().toString(driver.getFullName()));

        Calendar birthCalendar = new DatetimeHelper().getCalendar(driver.getBirthDay(), DatetimeFormat.SERVER_DATE_FORMAT);
        int driverAge = Calendar.getInstance().get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        intent.putExtra(Extras.DRIVER_AGE, driverAge);

        intent.putExtra(Extras.DRIVER_GENDER, driver.getGender());

        intent.putExtra(Extras.DRIVER_TOKEN, driver.getToken());

        intent.putExtra(Extras.DRIVER_LATLNG, marker.getPosition());

        startActivityForResult(intent, RequestCodes.PICK_DRIVER);

        return true;
    }

    @Override
    public void onConnectionFail() {

    }


}
