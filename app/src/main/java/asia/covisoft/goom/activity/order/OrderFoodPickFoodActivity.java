package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asia.covisoft.goom.BaseActivity;
import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodListAdapter;
import asia.covisoft.goom.pojo.FoodItem;

public class OrderFoodPickFoodActivity extends BaseActivity {

    private Context mContext;

    private FoodListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_pick_food);
        mContext = this;
        initView();

        setupListHeader();

        mAdapter = new FoodListAdapter(mContext, dataSet());
        lvFood.setAdapter(mAdapter);
    }

    private ListView lvFood;

    private void initView() {

        lvFood = (ListView) findViewById(R.id.lvFood);
        findViewById(R.id.btnOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, OrderFoodOrderedActivity.class));

            }
        });
    }

    ;

    private void setupListHeader() {

        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.header_restaurant, null);

        ImageView imgvAvatar = (ImageView) header.findViewById(R.id.imgvAvatar);
        Picasso.with(mContext)
                .load("file:///android_asset/" + getIntent().getStringExtra("imageurl"))
                .into(imgvAvatar);

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
