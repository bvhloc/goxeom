package asia.covisoft.goom.fragment.order;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.list.LocationHistoryListAdapter;
import asia.covisoft.goom.backpress.RootFragment;
import asia.covisoft.goom.pojo.LocationHistoryItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderPickLocationFragment extends RootFragment implements AdapterView.OnItemClickListener {


    public OrderPickLocationFragment() {
        // Required empty public constructor
    }

    private SearchView searchView;
    private ListView lvLocationHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_pick_location, container, false);

        searchView = (SearchView) rootView.findViewById(R.id.searchView);
        rootView.findViewById(R.id.search_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchView.setIconified(false);
            }
        });
        rootView.findViewById(R.id.btnOpenLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMap();
            }
        });
        rootView.findViewById(R.id.btnCurrentLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMap();
            }
        });
        lvLocationHistory = (ListView) rootView.findViewById(R.id.lvLocationHistory);

        return rootView;
    }

    private void openMap(){

        SupportMapFragment mapFragment = new SupportMapFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.tab_container, mapFragment);
        transaction.commit();
    }

    private LocationHistoryListAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new LocationHistoryListAdapter(this.getContext(), dataSet());
        lvLocationHistory.setAdapter(adapter);
        lvLocationHistory.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        getActivity().onBackPressed();
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
