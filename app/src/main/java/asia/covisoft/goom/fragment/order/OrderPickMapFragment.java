package asia.covisoft.goom.fragment.order;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.GeoHelper;
import asia.covisoft.goom.utils.Extras;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderPickMapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {


    public OrderPickMapFragment() {
        // Required empty public constructor
    }

    public static OrderPickMapFragment newInstance(LatLng latlng){

        OrderPickMapFragment fragment = new OrderPickMapFragment();
        Bundle args = new Bundle();
        args.putParcelable(Extras.PLACE_LATLNG, latlng);
        fragment.setArguments(args);

        return fragment;
    }

    private TextView tvAddress;
    private ProgressBar pbAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_pick_map, container, false);

        tvAddress = (TextView) rootView.findViewById(R.id.tvAddress);
        pbAddress = (ProgressBar) rootView.findViewById(R.id.pbAddress);
        rootView.findViewById(R.id.pickBox).setOnClickListener(this);

        return rootView;
    }

    private Context mContext;
    private Activity mActivity;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        mActivity = getActivity();

        initMap();
    }

    private void initMap() {

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mMap);
        mapFragment.getMapAsync(this);

    }

    private LatLng latlng;

    @SuppressWarnings("ResourceType")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Bundle args = getArguments();
        if(args != null){
            latlng = getArguments().getParcelable(Extras.PLACE_LATLNG);
        }else {
            latlng = new GPSTracker(mContext).getLatLng();
        }
        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

                latlng = cameraPosition.target;
                setAddress(latlng);
            }
        });
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15));
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void setAddress(final LatLng latlng) {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                tvAddress.setText("");
                pbAddress.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(Void... params) {
                String address = new GeoHelper(mContext).getAddress(latlng);
                Log.d("sdb", address);
                return address;
            }

            @Override
            protected void onPostExecute(String address) {
                super.onPostExecute(address);

                pbAddress.setVisibility(View.INVISIBLE);
                tvAddress.setText(address);
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {

        String address = tvAddress.getText().toString().trim();
        if(!address.isEmpty()){

            Intent data = new Intent();
            data.putExtra(Extras.PICKED_ADDRESS, address);
            data.putExtra(Extras.PICKED_LATLNG, latlng);
            mActivity.setResult(Activity.RESULT_OK, data);
            mActivity.finish();
        }
    }
}
