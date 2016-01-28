package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodExpandableListAdapter;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.mvp.presenter.OrderFoodPickFoodPresenter;
import asia.covisoft.goom.mvp.view.OrderFoodPickFoodView;
import asia.covisoft.goom.pojo.gson.FoodlistRoot;
import asia.covisoft.goom.utils.Extras;

public class OrderFoodPickFoodActivity extends BaseActivity implements OrderFoodPickFoodView {

    private Context mContext;
    private OrderFoodPickFoodPresenter presenter;

    private FoodExpandableListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_pick_food);
        mContext = this;
        presenter = new OrderFoodPickFoodPresenter(this);
        initView();

        Bundle extras = getIntent().getExtras();

        presenter.setupListHeader(extras);

        String userToken = extras.getString(Extras.USER_TOKEN);
        String restaurantId = extras.getString(Extras.RESTAURANT_ID);

        presenter.getMenu(userToken, restaurantId);


    }

    private ExpandableListView lvFood;

    private void initView() {

        lvFood = (ExpandableListView) findViewById(R.id.lvFood);
        findViewById(R.id.btnOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderFoodOrderedActivity.class));

            }
        });
    }

    @Override
    public void onConnectionFail() {

    }

    @Override
    public void initListHeader(View header) {

        lvFood.addHeaderView(header);
    }

    @Override
    public void onMenuLoaded(List<String> groups, HashMap<String, List<FoodlistRoot.Foodlist>> childs) {

        mAdapter = new FoodExpandableListAdapter(mContext, groups, childs);
        lvFood.setAdapter(mAdapter);
    }
}
