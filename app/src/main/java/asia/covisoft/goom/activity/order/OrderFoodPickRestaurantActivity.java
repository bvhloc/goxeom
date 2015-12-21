package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.RestaurantListAdapter;
import asia.covisoft.goom.pojo.RestaurantItem;
import asia.covisoft.goom.customview.HeaderGridView;

public class OrderFoodPickRestaurantActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private Context mContext;

    private HeaderGridView gvRestarants;
    private RestaurantListAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_pick_restaurant);
        mContext = this;
        initView();

        tvTitle.setText(getIntent().getStringExtra(Constant.ORDER_FOOD_PICK_RESTAURANT_TITLE));

        restaurantAdapter = new RestaurantListAdapter(this, gridDataSet());
        gvRestarants.setAdapter(restaurantAdapter);

        gvRestarants.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(mContext, OrderFoodPickFoodActivity.class);
        intent.putExtra("name", restaurantAdapter.getItem(position).getName());
        intent.putExtra("address", restaurantAdapter.getItem(position).getAddress());
        intent.putExtra("imageurl", restaurantAdapter.getItem(position).getImageUrl());
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

    private ArrayList<RestaurantItem> gridDataSet() {
        ArrayList<RestaurantItem> list = new ArrayList<>();
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Bánh Xèo A Phủ", "121 Nguyễn Văn Nghi, 7, Gò Vấp, Hồ Chí Minh", "menu/banhxeo.jpg"));
        list.add(new RestaurantItem("Chả Giò Bà Rịa", "78 Tân Sơn Nhì, Tân Phú, Hồ Chí Minh", "menu/chagio.jpg"));
        list.add(new RestaurantItem("Cơm Tấm Cây Khế 3", "32 Cây Trâm, 9, Gò Vấp, Hồ Chí Minh", "menu/comtam.jpg"));
        list.add(new RestaurantItem("Gỏi Cuốn Cô Huệ", "57 Châu Văn Liêm, 14, 5, Hồ Chí Minh", "menu/goicuon.jpg"));
        list.add(new RestaurantItem("Nem Nướng Đà Lạt", "69 Vạn Kiếp, phường 3, Bình Thạnh, Hồ Chí Minh", "menu/nemnuong.jpg"));
        list.add(new RestaurantItem("Phở Quê Hương", "1223 Phan Văn Trị, 10, Gò Vấp, Hồ Chí Minh", "category/pho.jpg"));
        list.add(new RestaurantItem("Cơm Tấm Thuận Kiều", "17 Út Tịch, 4, Hồ Chí Minh", "category/com.jpg"));
        list.add(new RestaurantItem("Pizza Hut", "264 Nguyễn Trãi, 8, 5, Hồ Chí Minh", "category/pizza.jpg"));
        list.add(new RestaurantItem("Cơm Tấm Cali", "82 Nguyễn Văn Trỗi, 8, Phú Nhuận, Hồ Chí Minh", "menu/comtam.jpg"));
        return list;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
