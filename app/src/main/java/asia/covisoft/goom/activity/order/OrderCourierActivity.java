package asia.covisoft.goom.activity.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import asia.covisoft.goom.mvp.model.SettingsProfileModel;
import asia.covisoft.goom.mvp.presenter.OrderCourierPresenter;
import asia.covisoft.goom.mvp.presenter.SettingsProfilePresenter;
import asia.covisoft.goom.mvp.view.OrderCourierView;
import asia.covisoft.goom.mvp.view.SettingsProfileView;
import asia.covisoft.goom.pojo.gson.LoadcourierRoot;
import asia.covisoft.goom.utils.DatetimeFormat;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.utils.Preferences;
import asia.covisoft.goom.utils.RequestCodes;

public class OrderCourierActivity extends BaseActivity implements OrderCourierView, GoogleMap.OnMarkerClickListener, OnMapReadyCallback, SettingsProfileView {

    private ScrollView scrollView;
    private TextView tvDriverName,
            tvAddressFrom, tvAddressTo,
            tvContactFrom, tvContactTo;
    private EditText edtItems,
            edtDetailsFrom, edtDetailsTo;

    private void initView() {
        setContentView(R.layout.activity_order_courier);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tvDriverName = (TextView) findViewById(R.id.tvDriverName);
        tvAddressFrom = (TextView) findViewById(R.id.tvAddressFrom);
        tvAddressTo = (TextView) findViewById(R.id.tvAddressTo);
        tvContactFrom = (TextView) findViewById(R.id.tvContactFrom);
        tvContactTo = (TextView) findViewById(R.id.tvContactTo);
        edtDetailsFrom = (EditText) findViewById(R.id.edtDetailsFrom);
        edtDetailsTo = (EditText) findViewById(R.id.edtDetailsTo);
        edtItems = (EditText) findViewById(R.id.edtItems);
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

                Intent intent = new Intent(mContext, OrderPickContactActivity.class);
                intent.putExtra(Extras.PICKED_CONTACT_NAME, model.contactNameFrom);
                intent.putExtra(Extras.PICKED_CONTACT_PHONE, model.contactPhoneFrom);
                startActivityForResult(intent, RequestCodes.PICK_CONTACT_FROM);
            }
        });
        findViewById(R.id.lnlContactTo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, OrderPickContactActivity.class);
                intent.putExtra(Extras.PICKED_CONTACT_NAME, model.contactNameTo);
                intent.putExtra(Extras.PICKED_CONTACT_PHONE, model.contactPhoneTo);
                startActivityForResult(intent, RequestCodes.PICK_CONTACT_TO);
            }
        });
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnNextClicked();
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

        SharedPreferences loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);
        model.userToken = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");
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

            presenter.getDriver(model.userToken);
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

        setupUI();
    }

    private void setupUI() {

        new SettingsProfilePresenter(this).loadInfo(model.userToken);
    }

    @Override
    public void onInfoLoaded(SettingsProfileModel model) {

        this.model.contactNameFrom = model.fullname;
        this.model.contactPhoneFrom = model.phone;

        String contactFrom = this.model.contactNameFrom.equals("")
                ? this.model.contactPhoneFrom
                : this.model.contactNameFrom + "\n" + this.model.contactPhoneFrom;
        tvContactFrom.setText(contactFrom);
    }

    @Override
    public void onInfoUpdate(Integer result) {

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

                    model.driverName = data.getStringExtra(Extras.PICKED_DRIVER_NAME);
                    model.driverToken = data.getStringExtra(Extras.PICKED_DRIVER_TOKEN);

                    tvDriverName.setText(model.driverName);
                    tvDriverName.setError(null);

                    break;
                case RequestCodes.PICK_LOCATION_FROM:

                    receivedLatLng = data.getParcelableExtra(Extras.PICKED_LATLNG);
                    model.latFrom = receivedLatLng.latitude;
                    model.lngFrom = receivedLatLng.longitude;
                    model.addressFrom = data.getStringExtra(Extras.PICKED_ADDRESS);

                    tvAddressFrom.setText(model.addressFrom);
                    tvAddressFrom.setError(null);

                    break;
                case RequestCodes.PICK_LOCATION_TO:

                    receivedLatLng = data.getParcelableExtra(Extras.PICKED_LATLNG);
                    model.latTo = receivedLatLng.latitude;
                    model.lngTo = receivedLatLng.longitude;
                    model.addressTo = data.getStringExtra(Extras.PICKED_ADDRESS);

                    tvAddressTo.setText(model.addressTo);
                    tvAddressTo.setError(null);

                    break;
                case RequestCodes.PICK_CONTACT_FROM:

                    model.contactNameFrom = data.getStringExtra(Extras.PICKED_CONTACT_NAME);
                    model.contactPhoneFrom = data.getStringExtra(Extras.PICKED_CONTACT_PHONE);

                    String contactFrom = model.contactNameFrom.equals("")
                            ? model.contactPhoneFrom
                            : model.contactNameFrom + "\n" + model.contactPhoneFrom;
                    tvContactFrom.setText(contactFrom);
                    tvContactFrom.setError(null);

                    break;
                case RequestCodes.PICK_CONTACT_TO:

                    model.contactNameTo = data.getStringExtra(Extras.PICKED_CONTACT_NAME);
                    model.contactPhoneTo = data.getStringExtra(Extras.PICKED_CONTACT_PHONE);

                    String contactTo = model.contactNameTo.equals("")
                            ? model.contactPhoneTo
                            : model.contactNameTo + "\n" + model.contactPhoneTo;
                    tvContactTo.setText(contactTo);
                    tvContactTo.setError(null);

                    break;
            }
        }
    }

    private void btnNextClicked() {

        if (validInput()) {
            presenter.getCost(model);
        }
    }

    @Override
    public void onCostResult(OrderCourierModel model) {

        Intent intent = new Intent(mContext, OrderConfirmActivity.class);
        intent.putExtra(Extras.BOOKING_TYPE, OrderConfirmActivity.BOOK_TYPE_COURIER);
        intent.putExtra(Extras.BOOKING_INFO, model);
        startActivity(intent);
    }

    private boolean validInput() {

        model.detailsFrom = edtDetailsFrom.getText().toString().trim();
        model.detailsTo = edtDetailsTo.getText().toString().trim();
        model.items = edtItems.getText().toString().trim();

        if (model.driverToken == null || model.driverToken.isEmpty()) {
            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_pickdriver), Snackbar.LENGTH_SHORT)
                    .show();
            tvDriverName.setError("");
            return false;
        }
        if (model.latFrom == 0 || model.lngFrom == 0) {

            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_picklocationfrom), Snackbar.LENGTH_SHORT)
                    .show();
            tvAddressFrom.setError("");
            return false;
        }
        if (model.latTo == 0 || model.lngTo == 0) {

            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_picklocationto), Snackbar.LENGTH_SHORT)
                    .show();
            tvAddressTo.setError("");
            return false;
        }
        if (model.contactPhoneFrom == null || model.contactPhoneFrom.isEmpty()) {
            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_inputcontactfrom), Snackbar.LENGTH_SHORT)
                    .show();
            tvContactFrom.setError("");
            return false;
        }
        if (model.contactPhoneTo == null || model.contactPhoneTo.isEmpty()) {
            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_inputcontactto), Snackbar.LENGTH_SHORT)
                    .show();
            tvContactTo.setError("");
            return false;
        }
        if (model.items.isEmpty()) {
            edtItems.setError(getText(R.string.error_itemsempty));
            return false;
        }

        return true;
    }
}
