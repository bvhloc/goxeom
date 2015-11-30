package asia.covisoft.goom.fragment.order;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import asia.covisoft.goom.FragmentNavigator;
import asia.covisoft.goom.GPSTracker;
import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.RestaurantListAdapter;
import asia.covisoft.goom.backpress.RootFragment;
import asia.covisoft.goom.pojo.RestaurantItem;
import asia.covisoft.goom.view.HeaderGridView;
import asia.covisoft.goom.view.WorkaroundMapFragment;

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

                searchView.setIconified(false);
            }
        });

        return rootView;
    }

    private RestaurantListAdapter mAdapter;

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

//        int pixel = getActivity().getWindowManager().getDefaultDisplay();

//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        header = inflater.inflate(R.layout.map_header, null);
//        gvRestarants.addHeaderView(header);

        WorkaroundMapFragment mapFrag = WorkaroundMapFragment.newInstance();
        View header = mapFrag.getView();
        gvRestarants.addHeaderView(header);

        mAdapter = new RestaurantListAdapter(getActivity(), dataSet());
        gvRestarants.setAdapter(mAdapter);

        setUpMap();
    }

    private GoogleMap mMap;
    private WorkaroundMapFragment mapFrag;

    private void setUpMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((WorkaroundMapFragment) getChildFragmentManager().findFragmentById(R.id.mMap))
                    .getMap();
            ((WorkaroundMapFragment) getChildFragmentManager().findFragmentById(R.id.mMap)).setOnTouchListener(new WorkaroundMapFragment.OnTouchListener() {
                @Override
                public void onTouch() {

                    gvRestarants.requestDisallowInterceptTouchEvent(true);
                }
            });
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


    private ArrayList<RestaurantItem> dataSet() {
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
