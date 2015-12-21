package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodListAdapter;
import asia.covisoft.goom.mvp.presenter.OrderFoodPickFoodPresenter;
import asia.covisoft.goom.mvp.view.OrderFoodPickFoodView;
import asia.covisoft.goom.pojo.FoodItem;

public class OrderFoodPickFoodActivity extends BaseActivity implements OrderFoodPickFoodView {

    private Context mContext;
    private OrderFoodPickFoodPresenter presenter;

    private FoodListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_pick_food);
        mContext = this;
        presenter = new OrderFoodPickFoodPresenter(this);
        initView();

        presenter.setupListHeader(getIntent().getExtras());

        mAdapter = new FoodListAdapter(mContext, dataSet());
        lvFood.setAdapter(mAdapter);
    }

    private ListView lvFood;

    @Override
    public void initView() {

        lvFood = (ListView) findViewById(R.id.lvFood);
        findViewById(R.id.btnOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderFoodOrderedActivity.class));

            }
        });
    }

    @Override
    public void initListHeader(View header) {

        lvFood.addHeaderView(header);
    }

    private ArrayList<FoodItem> dataSet() {
        ArrayList<FoodItem> list = new ArrayList<>();
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        list.add(new FoodItem("Com Ga Hai Nam", "50.000"));
        return list;
    }
}
