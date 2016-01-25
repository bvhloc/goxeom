package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.RestaurantListAdapter;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.customview.HeaderGridView;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.mvp.presenter.OrderFoodPickRestaurantPresenter;
import asia.covisoft.goom.mvp.view.OrderFoodPickRestaurantView;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;
import asia.covisoft.goom.utils.Extras;

public class OrderFoodPickRestaurantActivity extends BaseActivity implements AdapterView.OnItemClickListener, OrderFoodPickRestaurantView {

    private Context mContext;

    private HeaderGridView gvRestarants;
    private RestaurantListAdapter restaurantAdapter;
    private OrderFoodPickRestaurantPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_pick_restaurant);
        mContext = this;
        presenter = new OrderFoodPickRestaurantPresenter(this);
        initView();

        gvRestarants.setOnItemClickListener(this);

        Bundle extras = getIntent().getExtras();
        String token = extras.getString(Extras.USER_TOKEN);
        String type = extras.getString(Extras.FOOD_TYPE);
        String typeName = new Hex().toString(extras.getString(Extras.FOOD_TYPE_NAME));

        tvTitle.setText(typeName);

        presenter.getRestaurants(token, type);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(mContext, OrderFoodPickFoodActivity.class);
        intent.putExtra(Extras.RESTAURANT_ID, restaurantAdapter.getItem(position).getRestaurantId());
        intent.putExtra(Extras.RESTAURANT_NAME, restaurantAdapter.getItem(position).getRestaurantName());
        intent.putExtra(Extras.RESTAURANT_ADDRESS, restaurantAdapter.getItem(position).getRestaurantAddress());
        intent.putExtra(Extras.RESTAURANT_IMAGE, restaurantAdapter.getItem(position).getRestaurantImage());
        startActivity(intent);
    }

    private TextView tvTitle;
    private SearchView searchView;

    private void initView() {

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        gvRestarants = (HeaderGridView) findViewById(R.id.gvRestaurants);
        searchView = (SearchView) findViewById(R.id.searchView);
        findViewById(R.id.search_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //click searchView
                searchView.setIconified(false);
            }
        });
    }

    @Override
    public void onConnectionFail() {

    }

    @Override
    public void onRestaurantsLoaded(List<RestaurantList> restaurants) {

        restaurantAdapter = new RestaurantListAdapter(mContext, restaurants);
        gvRestarants.setAdapter(restaurantAdapter);
    }
}
