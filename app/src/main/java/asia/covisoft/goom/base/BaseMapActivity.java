package asia.covisoft.goom.base;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import asia.covisoft.goom.R;

public abstract class BaseMapActivity extends BaseActivity implements OnMapReadyCallback {

    @SuppressWarnings("ResourceType")
    @Override
    public void onMapReady(GoogleMap googleMap) {

        resizeMap();

        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }

    private void resizeMap() {

        View mapView = findViewById(R.id.mMap);

        if (mapView != null) {

            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int screenHeight = displaymetrics.heightPixels;

            mapView.setLayoutParams(new LinearLayout
                    .LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, screenHeight * 40 / 100));
        }
    }
}
