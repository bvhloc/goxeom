package asia.covisoft.goom.activity.history;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.R;
import asia.covisoft.goom.widget.WorkaroundMapFragment;
import asia.covisoft.goom.mvp.presenter.HistoryDetailsPresenter;
import asia.covisoft.goom.mvp.view.HistoryDetailsView;

public class HistoryDetailsActivity extends BaseActivity implements HistoryDetailsView {

    private Context mContext;
    private HistoryDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        mContext = this;
        presenter = new HistoryDetailsPresenter(this);
        initView();

        presenter.setupTitle(getIntent().getExtras());

        presenter.setupMap();
    }

    private ScrollView scrollView;
    private TextView tvTitle;

    private void initView() {

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }

    @Override
    public void setTitle(String title) {

        title = tvTitle.getText() + " - " + title;
        tvTitle.setText(title);
    }

    private GoogleMap mMap;
    @Override
    public void onMapReady(LatLng currentLatLng) {

        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap))
                    .getMap();
            ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap)).setOnTouchListener(new WorkaroundMapFragment.OnTouchListener() {
                @Override
                public void onTouch() {

                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
            });
            // Check if we were successful in obtaining the map.
            if (mMap != null) {

                mMap.addMarker(new MarkerOptions().position(currentLatLng).title(getString(R.string.lowcase_your_location)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 14));
                mMap.setMyLocationEnabled(true);
            }
        }
    }
}
