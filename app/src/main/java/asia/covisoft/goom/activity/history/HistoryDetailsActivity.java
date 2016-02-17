package asia.covisoft.goom.activity.history;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseMapActivity;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.helper.PhoneHelper;
import asia.covisoft.goom.helper.PolylineDrawer;
import asia.covisoft.goom.helper.TouchEffect;
import asia.covisoft.goom.mvp.presenter.HistoryDetailsPresenter;
import asia.covisoft.goom.mvp.presenter.OrderMadePresenter;
import asia.covisoft.goom.mvp.presenter.TipPresenter;
import asia.covisoft.goom.mvp.view.HistoryDetailsView;
import asia.covisoft.goom.mvp.view.OrderMadeView;
import asia.covisoft.goom.mvp.view.TipView;
import asia.covisoft.goom.pojo.gson.LoaddetailhistoryRoot.Loaddetailhistory.Foodlist;
import asia.covisoft.goom.service.CancelTipService;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.widget.WorkaroundMapFragment;

@SuppressLint("SetTextI18n")
public class HistoryDetailsActivity extends BaseMapActivity implements HistoryDetailsView, OnMapReadyCallback, OrderMadeView, TipView {

    private ScrollView scrollView;
    private TextView tvTitle, tvDriverName, tvDatetime, tvAddressFrom, tvAddressTo, tvTotal,
            tvMaxTip, tvMinTip;
    private LinearLayout lnlList;
    private Button btnCancel;
    private EditText edtTip;

    private void initView() {
        setContentView(R.layout.activity_history_details);

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvDriverName = (TextView) findViewById(R.id.tvDriverName);
        tvDatetime = (TextView) findViewById(R.id.tvDatetime);
        lnlList = (LinearLayout) findViewById(R.id.lnlList);
        tvAddressFrom = (TextView) findViewById(R.id.tvAddressFrom);
        tvAddressTo = (TextView) findViewById(R.id.tvAddressTo);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvMaxTip = (TextView) findViewById(R.id.tvMaxTip);
        TouchEffect.addAlpha(tvMaxTip);
        tvMaxTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtTip.setText(maxTip);
                edtTip.setSelection(maxTip.length());
            }
        });
        tvMinTip = (TextView) findViewById(R.id.tvMinTip);
        TouchEffect.addAlpha(tvMinTip);
        tvMinTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtTip.setText(minTip);
                edtTip.setSelection(minTip.length());
            }
        });
        edtTip = (EditText) findViewById(R.id.edtTip);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogCancel();
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

    private void showDialogCancel() {
        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_cancelbooking))
                .setNegativeButton(getString(R.string.lowcase_no), null)
                .setPositiveButton(getString(R.string.lowcase_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        OrderMadePresenter presenter = new OrderMadePresenter(HistoryDetailsActivity.this);
                        presenter.setConfirm(tradingId, presenter.CONFIRM_VALUE_CANCEL);
                    }
                }).show();
    }

    private Context mContext;
    private HistoryDetailsPresenter presenter;
    private TipPresenter tipPresenter;
    private ProgressDialog progressDialog;
    private String userToken;
    private String tradingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = new HistoryDetailsPresenter(this);
        tipPresenter = new TipPresenter(this);
        initView();

        initMap();

        Bundle extras = getIntent().getExtras();

        setupUI(extras);

        progressDialog = ProgressDialog.show(mContext, "", getString(R.string.dialog_loading));

        userToken = extras.getString(Extras.USER_TOKEN);
        tradingId = extras.getString(Extras.TRADING_ID);
        presenter.loadInfo(userToken, tradingId);
    }

    private boolean requestTip;
    private String maxTip;
    private String minTip;

    public void setupUI(Bundle extras) {

        boolean HISTORY_STATE = extras.getBoolean(Extras.HISTORY_STATE, true);
        String title = HISTORY_STATE ? getString(R.string.upcase_completed) : getString(R.string.upcase_inprocess);
        title = tvTitle.getText() + " - " + title;
        tvTitle.setText(title);

        requestTip = extras.getBoolean(Extras.REQUEST_TIP, false);
        if (requestTip) {
            CancelTipService.countdownTime = 30; //reset countdownTime

            btnCancel.setVisibility(View.GONE);
            findViewById(R.id.lnlDriverInfo).setVisibility(View.GONE);

            maxTip = extras.getString(Extras.MAX_TIP);
            minTip = extras.getString(Extras.MIN_TIP);
            String maxSuggest = getString(R.string.activity_history_detail_maxtip).replace("$value$", maxTip);
            String minSuggest = getString(R.string.activity_history_detail_maxtip).replace("$value$", minTip);
            tvMaxTip.setText(maxSuggest);
            tvMinTip.setText(minSuggest);

            findViewById(R.id.btnAccept).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    progressDialog = ProgressDialog.show(mContext, "", getString(R.string.dialog_loading));
                    tipPresenter.tip(userToken, tradingId, edtTip.getText().toString());
                }
            });
            findViewById(R.id.btnDecline).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    progressDialog = ProgressDialog.show(mContext, "", getString(R.string.dialog_loading));
                    tipPresenter.tip(userToken, tradingId, "0");
                }
            });

        } else {
            findViewById(R.id.lnlTip).setVisibility(View.GONE);
        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);

        mMap = googleMap;
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
    public void onItemsLoaded(String items) {

        findViewById(R.id.tvItems).setVisibility(View.VISIBLE);
        TextView textView = new TextView(mContext);
        textView.setText(Hex.decode(items));
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.mBlackText));
        lnlList.addView(textView);
    }

    @Override
    public void onFoodsLoaded(List<Foodlist> foods) {

        findViewById(R.id.tvMenu).setVisibility(View.VISIBLE);
        for (Foodlist food : foods) {
            TextView tvFood = new TextView(mContext);
            tvFood.setText("- " + Hex.decode(food.getFoodName()) + " x" + food.getNumber());
            tvFood.setTextColor(ContextCompat.getColor(mContext, R.color.mBlackText));
            lnlList.addView(tvFood);
        }
    }

    @Override
    public void onMapDraw(String requestUrl, CameraUpdate cameraUpdate, MarkerOptions sourceMarker, MarkerOptions destinationMarker) {

        mMap.addMarker(sourceMarker);
        mMap.addMarker(destinationMarker);
        mMap.moveCamera(cameraUpdate);
        new PolylineDrawer().drawPath(mMap, requestUrl);
        progressDialog.dismiss();
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
    public void onBookingCanceled() {
        this.finish();
    }

    @Override
    public void onTipConfirm() {
        progressDialog.dismiss();
        this.finish();
    }

    @Override
    public void onBackPressed() {
        if (!requestTip) {
            super.onBackPressed();
        }
    }
}
