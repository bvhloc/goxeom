package asia.covisoft.goom.activity.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.RestaurantListAdapter;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.utils.Hex;
import asia.covisoft.goom.mvp.presenter.OrderFoodPickRestaurantPresenter;
import asia.covisoft.goom.mvp.view.OrderFoodPickRestaurantView;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;
import asia.covisoft.goom.prefs.Extras;
import asia.covisoft.goom.widget.HeaderGridView;

public class OrderFoodPickRestaurantActivity extends BaseActivity implements OrderFoodPickRestaurantView {

    private Context mContext;

    private HeaderGridView gvRestarants;
    private RestaurantListAdapter restaurantAdapter;
    private OrderFoodPickRestaurantPresenter presenter;
    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_pick_restaurant);
        mContext = this;
        presenter = new OrderFoodPickRestaurantPresenter(this);
        initView();

        Bundle extras = getIntent().getExtras();
        userToken = extras.getString(Extras.USER_TOKEN);
        String type = extras.getString(Extras.FOOD_TYPE);
        String typeName = Hex.decode(extras.getString(Extras.FOOD_TYPE_NAME));

        tvTitle.setText(typeName);

        presenter.getRestaurants(userToken, type);
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
    public void onRestaurantsLoaded(List<RestaurantList> restaurants) {

        restaurantAdapter = new RestaurantListAdapter(mContext, restaurants);
        gvRestarants.setAdapter(restaurantAdapter);
    }
}
