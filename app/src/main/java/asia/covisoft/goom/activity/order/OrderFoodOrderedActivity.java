package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.adapter.list.FoodListAdapter;
import asia.covisoft.goom.pojo.FoodItem;

public class OrderFoodOrderedActivity extends BaseActivity {

    private Context mContext;

    private FoodListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_ordered);
        mContext = this;
        initView();

        mAdapter = new FoodListAdapter(mContext, dataSet());
        lvFood.setAdapter(mAdapter);
    }

    private ListView lvFood;

    private void initView() {

        lvFood = (ListView) findViewById(R.id.lvFood);
        findViewById(R.id.lnlPick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderPickLocationActivity.class));
            }
        });
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderConfirmActivity.class));
            }
        });
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
        return list;
    }
}
