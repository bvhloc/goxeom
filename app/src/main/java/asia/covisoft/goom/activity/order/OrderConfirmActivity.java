package asia.covisoft.goom.activity.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.mvp.model.OrderCourierModel;
import asia.covisoft.goom.mvp.model.OrderTransportModel;
import asia.covisoft.goom.mvp.presenter.OrderConfirmPresenter;
import asia.covisoft.goom.mvp.view.OrderConfirmView;
import asia.covisoft.goom.utils.Extras;

public class OrderConfirmActivity extends BaseActivity implements OrderConfirmView {

    private TextView tvAddressFrom, tvAddressTo, tvPrice, tvTotal;

    private void initView() {
        setContentView(R.layout.activity_order_confirm);

        tvAddressFrom = (TextView) findViewById(R.id.tvAddressFrom);
        tvAddressTo = (TextView) findViewById(R.id.tvAddressTo);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        findViewById(R.id.btnOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnOrderClicked();
            }
        });
    }

    private Context mContext;
    private String bookType;
    private OrderConfirmPresenter presenter;
    private OrderCourierModel courierModel;
    private OrderTransportModel transportModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = new OrderConfirmPresenter(this);
        initView();

        setupUI();
    }

    public static final String BOOK_TYPE_COURIER = "C";
    public static final String BOOK_TYPE_TRANSPORT = "T";
    public static final String BOOK_TYPE_FOODING = "F";
    public static final String BOOK_TYPE_SHOPPING = "S";

    private void setupUI() {

        Bundle extras = getIntent().getExtras();
        bookType = extras.getString(Extras.BOOKING_TYPE, "");
        switch (bookType) {
            case BOOK_TYPE_COURIER:

                courierModel = (OrderCourierModel) extras.getSerializable(Extras.BOOKING_INFO);
                if (courierModel != null) {
                    tvAddressFrom.setText(courierModel.addressFrom);
                    tvAddressTo.setText(courierModel.addressTo);
                    tvPrice.setText(courierModel.cost + " " + getString(R.string.money_unit));
                    tvTotal.setText(courierModel.cost + " " + getString(R.string.money_unit));
                }

                break;
            case BOOK_TYPE_TRANSPORT:

                transportModel = (OrderTransportModel) extras.getSerializable(Extras.BOOKING_INFO);
                if (transportModel != null) {
                    tvAddressFrom.setText(transportModel.addressFrom);
                    tvAddressTo.setText(transportModel.addressTo);
                    tvPrice.setText(transportModel.cost + " " + getString(R.string.money_unit));
                    tvTotal.setText(transportModel.cost + " " + getString(R.string.money_unit));
                }

                break;
            case BOOK_TYPE_FOODING:
                break;
            case BOOK_TYPE_SHOPPING:
                break;
        }
    }

    private void btnOrderClicked() {

        switch (bookType) {
            case BOOK_TYPE_COURIER:
                presenter.bookCourier(courierModel);
                break;
            case BOOK_TYPE_TRANSPORT:
                presenter.bookTransport(transportModel);
                break;
            case BOOK_TYPE_FOODING:
                break;
            case BOOK_TYPE_SHOPPING:
                break;
        }
    }

    @Override
    public void onBookingMade(String bookingId) {

        Intent intent = new Intent(mContext, OrderMadeActivity.class);
        intent.putExtra(Extras.BOOKING_ID, bookingId);
        startActivity(intent);
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
}
