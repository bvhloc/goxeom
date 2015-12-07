package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.LocationHistoryListAdapter;
import asia.covisoft.goom.pojo.LocationHistoryItem;

public class OrderPickLocationActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private Context mContext;

    private LocationHistoryListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pick_location);
        mContext = this;
        initView();

        mAdapter = new LocationHistoryListAdapter(mContext, dataSet());
        lvLocationHistory.setAdapter(mAdapter);
        lvLocationHistory.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        onBackPressed();
    }

    private SearchView searchView;
    private ListView lvLocationHistory;

    private void initView(){

        searchView = (SearchView) findViewById(R.id.searchView);
        findViewById(R.id.search_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchView.setIconified(false);
            }
        });
        findViewById(R.id.btnOpenLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMap();
            }
        });
        findViewById(R.id.btnCurrentLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMap();
            }
        });
        lvLocationHistory = (ListView) findViewById(R.id.lvLocationHistory);
    }

    private void openMap(){

        SupportMapFragment mapFragment = new SupportMapFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.tab_container, mapFragment);
        transaction.commit();
    }

    private ArrayList<LocationHistoryItem> dataSet(){
        ArrayList<LocationHistoryItem> list = new ArrayList<>();
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        list.add(new LocationHistoryItem("Toa nha 8", "To Ky - Q.12 - Ho Chi Minh"));
        return list;
    }
}
