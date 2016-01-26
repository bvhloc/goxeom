package asia.covisoft.goom.activity.order;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodTypeListAdapter;
import asia.covisoft.goom.adapter.list.RestaurantListAdapter;
import asia.covisoft.goom.base.BaseMapActivity;
import asia.covisoft.goom.customview.HeaderGridView;
import asia.covisoft.goom.customview.WorkaroundMapFragment;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.mvp.presenter.OrderFoodPresenter;
import asia.covisoft.goom.mvp.view.OrderFoodView;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.Category;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.utils.Preferences;

public class OrderFoodActivity extends BaseMapActivity implements OrderFoodView, GoogleMap.OnMarkerClickListener {

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
    private String userToken;

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

            Intent intent = new Intent(mContext, OrderFoodPickRestaurantActivity.class);
            intent.putExtra(Extras.FOOD_TYPE, foodtypeAdapter.getItem(position).getFoodTypeId());
            intent.putExtra(Extras.FOOD_TYPE_NAME, foodtypeAdapter.getItem(position).getFoodTypeName());
            intent.putExtra(Extras.USER_TOKEN, userToken);
            startActivity(intent);
        }
    };

    private AdapterView.OnItemClickListener gvRestaurantsListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            position = position - gvRestarants.getNumColumns();
            Intent intent = new Intent(mContext, OrderFoodPickFoodActivity.class);
            intent.putExtra(Extras.USER_TOKEN, userToken);
            intent.putExtra(Extras.RESTAURANT_ID, restaurantAdapter.getItem(position).getRestaurantId());
            intent.putExtra(Extras.RESTAURANT_NAME, restaurantAdapter.getItem(position).getRestaurantName());
            intent.putExtra(Extras.RESTAURANT_ADDRESS, restaurantAdapter.getItem(position).getRestaurantAddress());
            intent.putExtra(Extras.RESTAURANT_IMAGE, restaurantAdapter.getItem(position).getRestaurantImage());
            startActivity(intent);
        }
    };

    @Override
    public boolean onMarkerClick(Marker marker) {

        RestaurantList restaurant = restaurantHashMap.get(marker);
        Intent intent = new Intent(mContext, OrderFoodPickFoodActivity.class);
        intent.putExtra(Extras.USER_TOKEN, userToken);
        intent.putExtra(Extras.RESTAURANT_ID, restaurant.getRestaurantId());
        intent.putExtra(Extras.RESTAURANT_NAME, restaurant.getRestaurantName());
        intent.putExtra(Extras.RESTAURANT_ADDRESS, restaurant.getRestaurantAddress());
        intent.putExtra(Extras.RESTAURANT_IMAGE, restaurant.getRestaurantImage());
        startActivity(intent);

        return true;
    }

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
            userToken = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");

            presenter.getFooding(userToken, currentLatLng.latitude, currentLatLng.longitude);

            mMap.setOnMarkerClickListener(this);
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
    public void onCategoriesLoaded(List<Category> categories) {

        foodtypeAdapter = new FoodTypeListAdapter(mContext, categories);
        lvFoodType.setAdapter(foodtypeAdapter);
    }

    private HashMap<Marker, RestaurantList> restaurantHashMap;

    @Override
    public void onRestaurantsLoaded(List<RestaurantList> restaurants) {

        restaurantAdapter = new RestaurantListAdapter(mContext, restaurants);
        gvRestarants.setAdapter(restaurantAdapter);
        if (restaurants.isEmpty()) {
            Snackbar.make(findViewById(R.id.tab_container), getString(R.string.snackbar_norestaurantnearby), Snackbar.LENGTH_LONG).show();
        } else {
            restaurantHashMap = new HashMap<>();
            for (RestaurantList restaurant : restaurants) {

                String restaurantName = Hex.decode(restaurant.getRestaurantName());
                LatLng restaurantLatLng = new LatLng(Double.valueOf(restaurant.getRestaurantLat()), Double.valueOf(restaurant.getRestaurantLong()));
                Marker marker = mMap.addMarker(new MarkerOptions().title(restaurantName).position(restaurantLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_red_marker)));

                restaurantHashMap.put(marker, restaurant);
            }
        }
    }
}
