package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodListAdapter;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;
import asia.covisoft.goom.utils.Extras;

public class OrderFoodOrderedActivity extends BaseActivity {

    private ListView lvFood;

    private void initView() {
        setContentView(R.layout.activity_order_food_ordered);

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

    private Context mContext;
    private FoodListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initView();

        updateUI();
    }

    @SuppressWarnings("unchecked")
    private void updateUI(){

        Bundle extras = getIntent().getExtras();

        List<Foodlist> foods = (List<Foodlist>) extras.getSerializable(Extras.PICKED_FOODS);

        mAdapter = new FoodListAdapter(mContext, foods);
        lvFood.setAdapter(mAdapter);
    }
}
