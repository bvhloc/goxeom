package asia.covisoft.goom.activity.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;

import com.google.android.gms.maps.model.LatLng;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.LocationHistoryListAdapter;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.fragment.order.OrderPickMapFragment;
import asia.covisoft.goom.pojo.activeandroid.LocationHistory;
import asia.covisoft.goom.utils.Extras;

public class OrderPickLocationActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private Context mContext;

    private LocationHistoryListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pick_location);
        mContext = this;
        initView();

        mAdapter = new LocationHistoryListAdapter(mContext, new LocationHistory().getAll());
        lvLocationHistory.setAdapter(mAdapter);
        lvLocationHistory.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        LocationHistory item = mAdapter.getItem(position);

        //TODO check this
        Intent data = new Intent();
        data.putExtra(Extras.PICKED_ADDRESS, item.getAddress());
        LatLng latlng = new LatLng(item.getLat(), item.getLng());
        data.putExtra(Extras.PICKED_LATLNG, latlng);
        this.setResult(Activity.RESULT_OK, data);
        this.finish();
    }

    private SearchView searchView;
    private ListView lvLocationHistory;

    private void initView() {

        searchView = (SearchView) findViewById(R.id.searchView);
        findViewById(R.id.search_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchView.setIconified(false);
            }
        });
        ((RadioButton)findViewById(R.id.btnOpenLocation)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setChecked(false);
                    openMap();
                }
            }
        });
        ((RadioButton)findViewById(R.id.btnCurrentLocation)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setChecked(false);
                    openMap();
                }
            }
        });
        lvLocationHistory = (ListView) findViewById(R.id.lvLocationHistory);
    }

    private void openMap() {

        getSupportFragmentManager().beginTransaction()
                .add(R.id.tab_container, new OrderPickMapFragment())
                .addToBackStack(null)
                .commit();
    }
}
