package asia.covisoft.goom.activity.order;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodListAdapter;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.mvp.model.OrderFoodOrderedModel;
import asia.covisoft.goom.mvp.model.OrderPresenter;
import asia.covisoft.goom.mvp.view.OrderView;
import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;

@SuppressLint("SetTextI18n")
public class OrderFoodOrderedActivity extends BaseActivity implements OrderView, FoodListAdapter.OnQuantitiesChangedListener {

    private ListView lvFood;
    private TextView tvFoodCost, tvDeliveryCost, tvTotalCost, tvAddressTo;
    private EditText edtDetailsTo;

    private void initView() {
        setContentView(R.layout.activity_order_food_ordered);

        lvFood = (ListView) findViewById(R.id.lvFood);
        tvFoodCost = (TextView) findViewById(R.id.tvFoodCost);
        tvDeliveryCost = (TextView) findViewById(R.id.tvDeliveryCost);
        tvTotalCost = (TextView) findViewById(R.id.tvTotalCost);
        tvAddressTo = (TextView) findViewById(R.id.tvAddressTo);
        edtDetailsTo = (EditText) findViewById(R.id.edtDetailsTo);
        findViewById(R.id.lnlPick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(mContext, OrderPickLocationActivity.class), 0);
            }
        });
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnNextClick();
            }
        });
        ((CheckBox) findViewById(R.id.ckbPickNow)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                model.pickNow = isChecked;
            }
        });
    }

    private Context mContext;
    private FoodListAdapter mAdapter;
    private OrderPresenter presenter;
    private OrderFoodOrderedModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = new OrderPresenter(this);
        model = new OrderFoodOrderedModel();
        initView();

        updateUI();
    }

    @SuppressWarnings("unchecked")
    private void updateUI() {

        Bundle extras = getIntent().getExtras();

        model.userToken = extras.getString(Extras.USER_TOKEN);
        model.addressFrom = extras.getString(Extras.RESTAURANT_ADDRESS);
        model.latFrom = extras.getDouble(Extras.RESTAURANT_LAT);
        model.lngFrom = extras.getDouble(Extras.RESTAURANT_LNG);

        List<Foodlist> foods = (List<Foodlist>) extras.getSerializable(Extras.PICKED_FOODS);
        model.foods = foods;

        mAdapter = new FoodListAdapter(mContext, foods);
        mAdapter.setOnQuantitiesChangedListener(this);
        lvFood.setAdapter(mAdapter);
        mAdapter.changePrice();
    }

    @Override
    public void onQuantitiesChanged(long price) {

        model.foodCost = price;
        model.cost = model.foodCost + model.deliveryCost;
        tvFoodCost.setText(model.foodCost + " " + getString(R.string.money_unit));
        tvTotalCost.setText(model.cost + " " + getString(R.string.money_unit));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            LatLng receivedLatLng = data.getParcelableExtra(Extras.PICKED_LATLNG);
            model.latTo = receivedLatLng.latitude;
            model.lngTo = receivedLatLng.longitude;
            model.addressTo = data.getStringExtra(Extras.PICKED_ADDRESS);

            tvAddressTo.setText(model.addressTo);
            tvAddressTo.setError(null);

            presenter.getCost(model.userToken, model.latFrom, model.lngFrom, model.latTo, model.lngTo, 0);
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
    public void onCostResult(String cost) {

        model.deliveryCost = Long.parseLong(cost);
        model.cost = model.deliveryCost + model.foodCost;
        tvDeliveryCost.setText(model.deliveryCost + " " + getString(R.string.money_unit));
        tvTotalCost.setText(model.cost + " " + getString(R.string.money_unit));
    }

    private void btnNextClick() {

        if (validInput()) {

            Intent intent = new Intent(mContext, OrderConfirmActivity.class);
            intent.putExtra(Extras.BOOKING_TYPE, Constant.BOOK_TYPE_FOODING);
            intent.putExtra(Extras.BOOKING_INFO, model);
            startActivity(intent);
        }
    }

    private boolean validInput() {

        model.detailsTo = edtDetailsTo.getText().toString().trim();
        model.foods = mAdapter.getFoods();
        for (int i = model.foods.size() - 1; i >= 0; i--) {
            if (model.foods.get(i).getQuatity() <= 0) {
                model.foods.remove(i);
            }
        }

        if (model.foodCost == 0) {

            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_pickoneitem), Snackbar.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (model.latTo == 0 || model.lngTo == 0) {

            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_picklocationto), Snackbar.LENGTH_SHORT)
                    .show();
            tvAddressTo.setError("");
            return false;
        }

        return true;
    }
}
