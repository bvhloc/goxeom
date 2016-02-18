package asia.covisoft.goom.activity.order;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.mvp.model.OrderCourierModel;
import asia.covisoft.goom.mvp.model.OrderFoodOrderedModel;
import asia.covisoft.goom.mvp.model.OrderShoppingModel;
import asia.covisoft.goom.mvp.model.OrderTransportModel;
import asia.covisoft.goom.mvp.presenter.OrderConfirmPresenter;
import asia.covisoft.goom.mvp.view.OrderConfirmView;
import asia.covisoft.goom.utils.Constant;
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
    private OrderShoppingModel shoppingModel;
    private OrderFoodOrderedModel foodingModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = new OrderConfirmPresenter(this);
        initView();

        setupUI();
    }

    @SuppressLint("SetTextI18n")
    private void setupUI() {

        Bundle extras = getIntent().getExtras();
        if(extras == null)
            return;
        bookType = extras.getString(Extras.BOOKING_TYPE, "");
        switch (bookType) {
            case Constant.BOOK_TYPE_COURIER:

                courierModel = (OrderCourierModel) extras.getSerializable(Extras.BOOKING_INFO);
                if (courierModel != null) {
                    tvAddressFrom.setText(courierModel.addressFrom);
                    tvAddressTo.setText(courierModel.addressTo);
                    tvPrice.setText(courierModel.cost + " " + getString(R.string.money_unit));
                    tvTotal.setText(courierModel.cost + " " + getString(R.string.money_unit));
                }

                break;
            case Constant.BOOK_TYPE_TRANSPORT:

                transportModel = (OrderTransportModel) extras.getSerializable(Extras.BOOKING_INFO);
                if (transportModel != null) {
                    tvAddressFrom.setText(transportModel.addressFrom);
                    tvAddressTo.setText(transportModel.addressTo);
                    tvPrice.setText(transportModel.cost + " " + getString(R.string.money_unit));
                    tvTotal.setText(transportModel.cost + " " + getString(R.string.money_unit));
                }

                break;
            case Constant.BOOK_TYPE_FOODING:

                foodingModel = (OrderFoodOrderedModel) extras.getSerializable(Extras.BOOKING_INFO);
                if (foodingModel != null) {
                    tvAddressFrom.setText(Hex.decode(foodingModel.addressFrom));
                    tvAddressTo.setText(foodingModel.addressTo);
                    tvPrice.setText(foodingModel.cost + " " + getString(R.string.money_unit));
                    tvTotal.setText(foodingModel.cost + " " + getString(R.string.money_unit));
                }
                break;
            case Constant.BOOK_TYPE_SHOPPING:

                shoppingModel = (OrderShoppingModel) extras.getSerializable(Extras.BOOKING_INFO);
                if (shoppingModel != null) {
                    tvAddressFrom.setText(shoppingModel.addressFrom);
                    tvAddressTo.setText(shoppingModel.addressTo);
                    tvPrice.setText(shoppingModel.cost + " " + getString(R.string.money_unit));
                    tvTotal.setText(shoppingModel.cost + " " + getString(R.string.money_unit));
                }
                break;
        }
    }

    private void btnOrderClicked() {

        switch (bookType) {
            case Constant.BOOK_TYPE_COURIER:
                presenter.bookCourier(courierModel);
                break;
            case Constant.BOOK_TYPE_TRANSPORT:
                presenter.bookTransport(transportModel);
                break;
            case Constant.BOOK_TYPE_FOODING:
                presenter.bookFooding(foodingModel);
                break;
            case Constant.BOOK_TYPE_SHOPPING:
                presenter.bookShopping(shoppingModel);
                break;
        }
    }

    @Override
    public void onBookingMade(String bookingId) {

        switch (bookType) {
            case Constant.BOOK_TYPE_COURIER:

                presenter.saveHistory(courierModel.addressFrom, courierModel.latFrom, courierModel.lngFrom);
                presenter.saveHistory(courierModel.addressTo, courierModel.latTo, courierModel.lngTo);

                break;
            case Constant.BOOK_TYPE_TRANSPORT:

                presenter.saveHistory(transportModel.addressFrom, transportModel.latFrom, transportModel.lngFrom);
                presenter.saveHistory(transportModel.addressTo, transportModel.latTo, transportModel.lngTo);

                break;
            case Constant.BOOK_TYPE_FOODING:

                presenter.saveHistory(foodingModel.addressTo, foodingModel.latTo, foodingModel.lngTo);
                break;
            case Constant.BOOK_TYPE_SHOPPING:

                presenter.saveHistory(shoppingModel.addressFrom, shoppingModel.latFrom, shoppingModel.lngFrom);
                presenter.saveHistory(shoppingModel.addressTo, shoppingModel.latTo, shoppingModel.lngTo);

                break;
        }

        Intent intent = new Intent(mContext, OrderMadeActivity.class);
        intent.putExtra(Extras.BOOKING_ID, bookingId);
        startActivity(intent);
    }

    @Override
    public void onConnectionFail() {

        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_connection_fail))
                .setNeutralButton(getString(R.string.lowcase_ok), null)
                .show();
    }
}
