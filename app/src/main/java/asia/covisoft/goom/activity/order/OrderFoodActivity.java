package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import asia.covisoft.goom.BaseActivity;
import asia.covisoft.goom.Constant;
import asia.covisoft.goom.GPSTracker;
import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodTypeListAdapter;
import asia.covisoft.goom.adapter.list.RestaurantListAdapter;
import asia.covisoft.goom.pojo.FoodTypeItem;
import asia.covisoft.goom.pojo.RestaurantItem;
import asia.covisoft.goom.view.HeaderGridView;

public class OrderFoodActivity extends BaseActivity {

    private Context mContext;

    private RestaurantListAdapter restaurantAdapter;
    private FoodTypeListAdapter foodtypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        mContext = this;
        initView();

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

        foodtypeAdapter = new FoodTypeListAdapter(mContext, listDataSet());
        lvFoodType.setAdapter(foodtypeAdapter);
        lvFoodType.setOnItemClickListener(lvFoodTypeListener);

//        int pixel = getActivity().getWindowManager().getDefaultDisplay();

//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        header = inflater.inflate(R.layout.header_map, null);
//        gvRestarants.addHeaderView(header);

        mapView = new MapView(mContext);
        mapView.setClickable(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 400);
        mapView.setLayoutParams(params);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(mContext);

        gvRestarants.addHeaderView(mapView);

        restaurantAdapter = new RestaurantListAdapter(mContext, gridDataSet());
        gvRestarants.setAdapter(restaurantAdapter);
        gvRestarants.setOnItemClickListener(gvRestaurantsListener);

        setUpMap();
    }

    private AdapterView.OnItemClickListener lvFoodTypeListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(mContext, OrderFoodPickRestaurantActivity.class);
            intent.putExtra(Constant.ORDER_FOOD_PICK_RESTAURANT_TITLE, foodtypeAdapter.getItem(position).getName());
            startActivity(intent);
        }
    };

    private AdapterView.OnItemClickListener gvRestaurantsListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent(mContext, OrderFoodPickFoodActivity.class);
            intent.putExtra("imageurl", restaurantAdapter.getItem(position).getImageUrl());
            startActivity(intent);
        }
    };

    private RadioButton rdbCategory, rdbNearMe;
    private ListView lvFoodType;
    private HeaderGridView gvRestarants;
    private LinearLayout lnlNearMe;
    private SearchView searchView;

    private void initView(){

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
    }

    private MapView mapView;
    private GoogleMap mMap;

    private void setUpMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = mapView
                    .getMap();
//            mapView.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    switch (event.getAction()) {
//                        case MotionEvent.ACTION_DOWN:
//
//                            Log.d("myDebug", "down");
//
//                            return true;
//                        case MotionEvent.ACTION_UP:
//
//                            Log.d("myDebug", "up");
//
//                            return true;
//                    }
//                    return false;
//                }
//            });
//            mapView.setOnTouchListener(new WorkaroundMapView.OnTouchListener() {
//                @Override
//                public void onTouch() {
//
//                    gvRestarants.requestDisallowInterceptTouchEvent(false);
//                }
//            });
//            ((WorkaroundMapFragment) getChildFragmentManager().findFragmentById(R.id.mMap)).setOnTouchListener(new WorkaroundMapFragment.OnTouchListener() {
//                @Override
//                public void onTouch() {
//
//                    gvRestarants.requestDisallowInterceptTouchEvent(true);
//                }
//            });
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                GPSTracker gpsTracker = new GPSTracker(mContext);
                double lat = gpsTracker.getLatitude();
                double lng = gpsTracker.getLongitude();
                LatLng currentLatLng = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(currentLatLng).title(getString(R.string.lowcase_your_location)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private ArrayList<FoodTypeItem> listDataSet() {
        ArrayList<FoodTypeItem> list = new ArrayList<>();
        list.add(new FoodTypeItem("BEST-SELLER", "category/best.jpg"));
        list.add(new FoodTypeItem("PHO", "category/pho.jpg"));
        list.add(new FoodTypeItem("COM", "category/com.jpg"));
        list.add(new FoodTypeItem("PIZZA", "category/pizza.jpg"));
        return list;
    }

    private ArrayList<RestaurantItem> gridDataSet() {
        ArrayList<RestaurantItem> list = new ArrayList<>();
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        list.add(new RestaurantItem("Cơm gà 3 ghiền", "96 Đặng Văn Ngữ, P4, Q3, HCM", "category/best.jpg"));
        return list;
    }
}
