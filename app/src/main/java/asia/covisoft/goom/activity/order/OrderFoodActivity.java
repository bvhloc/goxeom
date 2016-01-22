package asia.covisoft.goom.activity.order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodTypeListAdapter;
import asia.covisoft.goom.adapter.list.RestaurantListAdapter;
import asia.covisoft.goom.base.BaseMapActivity;
import asia.covisoft.goom.customview.HeaderGridView;
import asia.covisoft.goom.customview.WorkaroundMapFragment;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.mvp.presenter.OrderFoodPresenter;
import asia.covisoft.goom.mvp.view.OrderFoodView;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot;
import asia.covisoft.goom.utils.Preferences;

public class OrderFoodActivity extends BaseMapActivity implements OrderFoodView {

    private RadioButton rdbCategory, rdbNearMe;
    private ListView lvFoodType;
    private HeaderGridView gvRestarants;
    private LinearLayout lnlNearMe;
    private SearchView searchView;

    private void initView() {

        rdbCategory = (RadioButton) findViewById(R.id.rdbCategory);
        rdbCategory.setTextColor(ContextCompat.getColor(mContext, R.color.mAppBackground));
        rdbNearMe = (RadioButton) findViewById(R.id.rdbNearMe);
        lvFoodType = (ListView) findViewById(R.id.lvFoodType);
        lnlNearMe = (LinearLayout) findViewById(R.id.lnlNearMe);
        gvRestarants = (HeaderGridView) findViewById(R.id.gvRestaurants);
        searchView = (SearchView) findViewById(R.id.searchView);
        findViewById(R.id.search_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //click searchView
                searchView.setIconified(false);
            }
        });
        rdbCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    //show Category
                    lvFoodType.setVisibility(View.VISIBLE);
                    rdbCategory.setTextColor(ContextCompat.getColor(mContext, R.color.mAppBackground));

                    //hide NearMe
                    lnlNearMe.setVisibility(View.GONE);
                    rdbNearMe.setTextColor(ContextCompat.getColor(mContext, R.color.mGreen));
                } else {

                    //hide Category
                    lvFoodType.setVisibility(View.GONE);
                    rdbCategory.setTextColor(ContextCompat.getColor(mContext, R.color.mGreen));

                    //show NearMe
                    lnlNearMe.setVisibility(View.VISIBLE);
                    rdbNearMe.setTextColor(ContextCompat.getColor(mContext, R.color.mAppBackground));
                }
            }
        });
    }

    private Context mContext;
    private OrderFoodPresenter presenter;

    private RestaurantListAdapter restaurantAdapter;
    private FoodTypeListAdapter foodtypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        mContext = this;
        presenter = new OrderFoodPresenter(this);
        initView();

        lvFoodType.setOnItemClickListener(lvFoodTypeListener);

        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams")
        View header = inflater.inflate(R.layout.header_map, null);
        gvRestarants.addHeaderView(header);
        gvRestarants.setOnItemClickListener(gvRestaurantsListener);

        initMap();
    }

    private AdapterView.OnItemClickListener lvFoodTypeListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//            Intent intent = new Intent(mContext, OrderFoodPickRestaurantActivity.class);
//            intent.putExtra(Constant.ORDER_FOOD_PICK_RESTAURANT_TITLE, foodtypeAdapter.getItem(position).getName());
//            startActivity(intent);
        }
    };

    private AdapterView.OnItemClickListener gvRestaurantsListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//            Intent intent = new Intent(mContext, OrderFoodPickFoodActivity.class);
//            intent.putExtra("name", restaurantAdapter.getItem(position - gvRestarants.getNumColumns()).getName());
//            intent.putExtra("address", restaurantAdapter.getItem(position - gvRestarants.getNumColumns()).getAddress());
//            intent.putExtra("imageurl", restaurantAdapter.getItem(position - gvRestarants.getNumColumns()).getImageUrl());
//            startActivity(intent);
        }
    };

    private void initMap() {

        WorkaroundMapFragment mapFragment = ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap));
        mapFragment.setOnTouchListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {

                gvRestarants.requestDisallowInterceptTouchEvent(true);
            }
        });
        mapFragment.getMapAsync(this);
    }

    private GoogleMap mMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);

        mMap = googleMap;
        // Check if we were successful in obtaining the map.
        if (mMap != null) {

            GPSTracker gpsTracker = new GPSTracker(mContext);
            LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));

            SharedPreferences loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);
            String userToken = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");

            presenter.getFooding(userToken, currentLatLng.latitude, currentLatLng.longitude);
        }
    }

    //    @Override
//    public void onResume() {
//        mapView.onResume();
//        super.onResume();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }

//    private ArrayList<FoodTypeItem> listDataSet() {
//        ArrayList<FoodTypeItem> list = new ArrayList<>();
//        list.add(new FoodTypeItem("BEST-SELLER", "category/best.jpg"));
//        list.add(new FoodTypeItem("PHO", "category/pho.jpg"));
//        list.add(new FoodTypeItem("COM", "category/com.jpg"));
//        list.add(new FoodTypeItem("PIZZA", "category/pizza.jpg"));
//        return list;
//    }

//    private ArrayList<RestaurantItem> gridDataSet() {
//        ArrayList<RestaurantItem> list = new ArrayList<>();
//        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
//        list.add(new RestaurantItem("Bánh Xèo A Phủ", "121 Nguyễn Văn Nghi, 7, Gò Vấp, Hồ Chí Minh", "menu/banhxeo.jpg"));
//        list.add(new RestaurantItem("Chả Giò Bà Rịa", "78 Tân Sơn Nhì, Tân Phú, Hồ Chí Minh", "menu/chagio.jpg"));
//        list.add(new RestaurantItem("Cơm Tấm Cây Khế 3", "32 Cây Trâm, 9, Gò Vấp, Hồ Chí Minh", "menu/comtam.jpg"));
//        list.add(new RestaurantItem("Gỏi Cuốn Cô Huệ", "57 Châu Văn Liêm, 14, 5, Hồ Chí Minh", "menu/goicuon.jpg"));
//        list.add(new RestaurantItem("Nem Nướng Đà Lạt", "69 Vạn Kiếp, phường 3, Bình Thạnh, Hồ Chí Minh", "menu/nemnuong.jpg"));
//        list.add(new RestaurantItem("Phở Quê Hương", "1223 Phan Văn Trị, 10, Gò Vấp, Hồ Chí Minh", "category/pho.jpg"));
//        list.add(new RestaurantItem("Cơm Tấm Thuận Kiều", "17 Út Tịch, 4, Hồ Chí Minh", "category/com.jpg"));
//        list.add(new RestaurantItem("Pizza Hut", "264 Nguyễn Trãi, 8, 5, Hồ Chí Minh", "category/pizza.jpg"));
//        list.add(new RestaurantItem("Cơm Tấm Cali", "82 Nguyễn Văn Trỗi, 8, Phú Nhuận, Hồ Chí Minh", "menu/comtam.jpg"));
//        return list;
//    }

    @Override
    public void onConnectionFail() {

    }

    @Override
    public void onCategoriesLoaded(List<LoadfoodingRoot.Loadfooding.Category> categories) {

        foodtypeAdapter = new FoodTypeListAdapter(mContext, categories);
        lvFoodType.setAdapter(foodtypeAdapter);
    }

    @Override
    public void onRestaurantsLoaded(List<LoadfoodingRoot.Loadfooding.RestaurantList> restaurants) {

        restaurantAdapter = new RestaurantListAdapter(mContext, restaurants);
        gvRestarants.setAdapter(restaurantAdapter);
    }
}
