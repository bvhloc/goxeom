package asia.covisoft.goom.activity.history;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.PhoneHelper;
import asia.covisoft.goom.helper.PolylineDrawer;
import asia.covisoft.goom.mvp.presenter.HistoryDetailsPresenter;
import asia.covisoft.goom.mvp.view.HistoryDetailsView;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.widget.WorkaroundMapFragment;

public class HistoryDetailsActivity extends BaseActivity implements HistoryDetailsView, OnMapReadyCallback {

    private ScrollView scrollView;
    private TextView tvTitle, tvDriverName, tvDatetime, tvAddressFrom, tvAddressTo, tvTotal;

    private void initView() {
        setContentView(R.layout.activity_history_details);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDriverName = (TextView) findViewById(R.id.tvDriverName);
        tvDatetime = (TextView) findViewById(R.id.tvDatetime);
        tvAddressFrom = (TextView) findViewById(R.id.tvAddressFrom);
        tvAddressTo = (TextView) findViewById(R.id.tvAddressTo);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        findViewById(R.id.btnCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new PhoneHelper(mContext).dial(driverPhone);
            }
        });
        findViewById(R.id.btnSms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new PhoneHelper(mContext).message(driverPhone, null);
            }
        });
    }

    private Context mContext;
    private HistoryDetailsPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = new HistoryDetailsPresenter(this);
        initView();

        initMap();

        Bundle extras = getIntent().getExtras();

        presenter.setupTitle(extras);

        progressDialog = ProgressDialog.show(mContext, "", getString(R.string.dialog_loading));

        String userToken = extras.getString(Extras.USER_TOKEN);
        String tradingId = extras.getString(Extras.TRADING_ID);
        presenter.loadInfo(userToken, tradingId);
    }

    @Override
    public void setTitle(String title) {

        title = tvTitle.getText() + " - " + title;
        tvTitle.setText(title);
    }

    private void initMap() {

        // Try to obtain the map from the SupportMapFragment.
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

            mMap.addMarker(new MarkerOptions().position(currentLatLng).title(getString(R.string.lowcase_your_location)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
            mMap.setMyLocationEnabled(true);
        }
    }

    private String driverPhone;

    @Override
    public void onDriverInfo(String driverName, String driverPhone) {

        tvDriverName.setText(driverName);
        this.driverPhone = driverPhone;
    }

    @Override
    public void onInfoLoaded(String datetime, String addressFrom, String addressTo, String cost) {

        tvDatetime.setText(datetime);
        tvAddressFrom.setText(addressFrom);
        tvAddressTo.setText(addressTo);
        tvTotal.setText(cost + " " + getString(R.string.money_unit));
    }

    @Override
    public void onMapDraw(final String requestUrl) {

        new PolylineDrawer().drawPath(mMap, requestUrl);
        progressDialog.dismiss();
    }
}
