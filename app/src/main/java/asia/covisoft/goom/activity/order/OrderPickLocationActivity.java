package asia.covisoft.goom.activity.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
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

        Intent data = new Intent();
        data.putExtra(Extras.PICKED_ADDRESS, item.getAddress());
        LatLng latlng = new LatLng(item.getLat(), item.getLng());
        data.putExtra(Extras.PICKED_LATLNG, latlng);
        this.setResult(Activity.RESULT_OK, data);
        this.finish();
    }

    private ListView lvLocationHistory;

    private void initView() {

        findViewById(R.id.search_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSearch();
            }
        });
        ((RadioButton) findViewById(R.id.btnOpenLocation)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setChecked(false);
                    openMap(new OrderPickMapFragment());
                }
            }
        });
        ((RadioButton) findViewById(R.id.btnCurrentLocation)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    buttonView.setChecked(false);
                    openMap(new OrderPickMapFragment());
                }
            }
        });
        lvLocationHistory = (ListView) findViewById(R.id.lvLocationHistory);
    }

    private void openSearch() {

        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, 0);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Place place = PlaceAutocomplete.getPlace(this, data);
            Log.i("sdb", "Place: " + place.getName());

            //add mapFragment with argurment placeLatLng
            openMap(OrderPickMapFragment.newInstance(place.getLatLng()));

        } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
            Status status = PlaceAutocomplete.getStatus(this, data);
            Log.i("sdb", status.getStatusMessage());
        }
    }

    private void openMap(OrderPickMapFragment mapFragment) {

        String tag = "mapTAG";
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(tag);
        FragmentTransaction transaction = fm.beginTransaction();
        if (fragment == null) {
            transaction.add(R.id.tab_container, mapFragment, tag);
            transaction.addToBackStack(null);
            transaction.commitAllowingStateLoss();
        }
    }
}
