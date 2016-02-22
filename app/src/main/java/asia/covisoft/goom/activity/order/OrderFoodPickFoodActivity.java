package asia.covisoft.goom.activity.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodExpandableListAdapter;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.mvp.presenter.OrderFoodPickFoodPresenter;
import asia.covisoft.goom.mvp.view.OrderFoodPickFoodView;
import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;
import asia.covisoft.goom.prefs.Extras;

public class OrderFoodPickFoodActivity extends BaseActivity implements OrderFoodPickFoodView {

    private ExpandableListView lvFood;
    private TextView tvItemPrice;

    private void initView() {
        setContentView(R.layout.activity_order_food_pick_food);

        tvItemPrice = (TextView) findViewById(R.id.tvItemPrice);
        setItemPrice(0, 0);
        lvFood = (ExpandableListView) findViewById(R.id.lvFood);
        findViewById(R.id.btnOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnOrderClick();
            }
        });
    }

    private Context mContext;
    private OrderFoodPickFoodPresenter presenter;
    private FoodExpandableListAdapter mAdapter;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        presenter = new OrderFoodPickFoodPresenter(this);
        initView();

        extras = getIntent().getExtras();

        presenter.setupListHeader(extras);

        String userToken = extras.getString(Extras.USER_TOKEN);
        String restaurantId = extras.getString(Extras.RESTAURANT_ID);

        presenter.getMenu(userToken, restaurantId);
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
    public void initListHeader(View header) {

        lvFood.addHeaderView(header);
    }

    @Override
    public void onMenuLoaded(List<String> groups, HashMap<String, List<Foodlist>> childs) {

        mAdapter = new FoodExpandableListAdapter(mContext, groups, childs);
        mAdapter.setOnQuantitiesChangedListener(new FoodExpandableListAdapter.OnQuantitiesChangedListener() {
            @Override
            public void onQuantitiesChanged(int itemCount, long price) {

                setItemPrice(itemCount, price);
            }
        });
        lvFood.setAdapter(mAdapter);
    }

    private int itemCount;

    private void setItemPrice(int itemCount, long price) {

        this.itemCount = itemCount;
        String itemprice = getString(R.string.activity_order_food_pick_food_itemprice)
                .replace("$item$", "" + itemCount)
                .replace("$price$", "" + price);
        tvItemPrice.setText(itemprice);
    }

    private void btnOrderClick() {

        if (itemCount > 0) {

            Intent intent = new Intent(mContext, OrderFoodOrderedActivity.class);
            intent.putExtras(extras);
            intent.putExtra(Extras.PICKED_FOODS, (Serializable) mAdapter.getPickedFoods());
            startActivity(intent);
        } else {
            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_pickoneitem), Snackbar.LENGTH_SHORT)
                    .show();
        }
    }
}
