package asia.covisoft.goom.helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Covisoft on 30/12/2015.
 */
public class GeoHelper {

    private Context context;

    public GeoHelper(Context context) {
        this.context = context;
    }

    public String getAddress(double latitude, double longitude) {

        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    if(i==address.getMaxAddressLineIndex()){
                        result.append(address.getAddressLine(i));
                        break;
                    }
                    result.append(address.getAddressLine(i)).append(", ");
                }
//                result.append(address.getAddressLine(0)).append(", ");
//                result.append(address.getSubLocality()).append(", ");
//                result.append(address.getLocality()).append(", ");
//                result.append(address.getCountryName());
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }

    public String getAddress(LatLng latlng){

        return getAddress(latlng.latitude, latlng.longitude);
    }
}
