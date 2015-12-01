package asia.covisoft.goom.fragment.order;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import asia.covisoft.goom.GPSTracker;
import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.FoodTypeListAdapter;
import asia.covisoft.goom.adapter.list.RestaurantListAdapter;
import asia.covisoft.goom.backpress.RootFragment;
import asia.covisoft.goom.pojo.FoodTypeItem;
import asia.covisoft.goom.pojo.RestaurantItem;
import asia.covisoft.goom.view.HeaderGridView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFoodFragment extends RootFragment {


    public OrderFoodFragment() {
        // Required empty public constructor
    }

    private RadioButton rdbCategory, rdbNearMe;
    private ListView lvFoodType;
    private HeaderGridView gvRestarants;
    private LinearLayout lnlNearMe;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_food, container, false);

        rdbCategory = (RadioButton) rootView.findViewById(R.id.rdbCategory);
        rdbCategory.setTextColor(ContextCompat.getColor(getActivity(), R.color.mAppBackground));
        rdbNearMe = (RadioButton) rootView.findViewById(R.id.rdbNearMe);
        lvFoodType = (ListView) rootView.findViewById(R.id.lvFoodType);
        lnlNearMe = (LinearLayout) rootView.findViewById(R.id.lnlNearMe);
        gvRestarants = (HeaderGridView) rootView.findViewById(R.id.gvRestaurants);
        searchView = (SearchView) rootView.findViewById(R.id.searchView);
        rootView.findViewById(R.id.search_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //click searchView
                searchView.setIconified(false);
            }
        });

        mapView = new MapView(getActivity());
        mapView.setClickable(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 400);
        mapView.setLayoutParams(params);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(this.getActivity());

        return rootView;
    }

    private RestaurantListAdapter restaurantAdapter;
    private FoodTypeListAdapter foodtypeAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rdbCategory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    //show Category
                    lvFoodType.setVisibility(View.VISIBLE);
                    rdbCategory.setTextColor(ContextCompat.getColor(getActivity(), R.color.mAppBackground));

                    //hide NearMe
                    lnlNearMe.setVisibility(View.GONE);
                    rdbNearMe.setTextColor(ContextCompat.getColor(getActivity(), R.color.mGreen));
                } else {

                    //hide Category
                    lvFoodType.setVisibility(View.GONE);
                    rdbCategory.setTextColor(ContextCompat.getColor(getActivity(), R.color.mGreen));

                    //show NearMe
                    lnlNearMe.setVisibility(View.VISIBLE);
                    rdbNearMe.setTextColor(ContextCompat.getColor(getActivity(), R.color.mAppBackground));
                }
            }
        });

        foodtypeAdapter = new FoodTypeListAdapter(getActivity(), listDataSet());
        lvFoodType.setAdapter(foodtypeAdapter);

//        int pixel = getActivity().getWindowManager().getDefaultDisplay();

//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        header = inflater.inflate(R.layout.map_header, null);
//        gvRestarants.addHeaderView(header);

        gvRestarants.addHeaderView(mapView);

        restaurantAdapter = new RestaurantListAdapter(getActivity(), gridDataSet());
        gvRestarants.setAdapter(restaurantAdapter);

        setUpMap();
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
                GPSTracker gpsTracker = new GPSTracker(this.getContext());
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
