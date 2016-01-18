package asia.covisoft.goom.fragment.order;


import android.content.Context;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderPickMapFragment extends Fragment implements OnMapReadyCallback {


    public OrderPickMapFragment() {
        // Required empty public constructor
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

        return rootView;
    }

    private Context mContext;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = this.getContext();

        initMap();
    }

    private void initMap() {

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mMap);
        mapFragment.getMapAsync(this);

    }

    @SuppressWarnings("ResourceType")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng currentLatLng = new GPSTracker(mContext).getLatLng();
        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

                setAddress(cameraPosition.target);
            }
        });
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
        googleMap.setMyLocationEnabled(true);
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
}
