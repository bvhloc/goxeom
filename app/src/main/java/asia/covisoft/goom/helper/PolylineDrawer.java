package asia.covisoft.goom.helper;

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import asia.covisoft.goom.utils.Constant;

public class  PolylineDrawer {

    @SuppressWarnings("StringBufferReplaceableByString")
    public static String makeURL(String apiKey, LatLng source, LatLng destination) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(source.latitude));
        urlString.append(",");
        urlString
                .append(Double.toString(source.longitude));
        urlString.append("&destination=");// to
        urlString
                .append(Double.toString(destination.latitude));
        urlString.append(",");
        urlString.append(Double.toString(destination.longitude));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        urlString.append("&key=").append(apiKey);
        return urlString.toString();
    }

    public void drawPath(final GoogleMap googleMap, final String requestUrl) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {

                try {

                    return getJsonFromUrl(requestUrl);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);

                try {
                    //Tranform the string into a json object
                    JSONObject json = new JSONObject(response);
                    JSONArray routeArray = json.getJSONArray("routes");
                    JSONObject routes = routeArray.getJSONObject(0);
                    JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
                    String encodedString = overviewPolylines.getString("points");
                    List<LatLng> list = decodePoly(encodedString);
                    PolylineOptions polyline = new PolylineOptions()
                            .addAll(list)
                            .width(7)
                            .color(Color.parseColor("#03A9F4"))
                            .geodesic(true)
                            .geodesic(true);
                    googleMap.addPolyline(polyline);
                    /*
                    for (int z = 0; z < list.size() - 1; z++) {
                        LatLng src = list.get(z);
                        LatLng dest = list.get(z + 1);
                        Polyline line = googleMap.addPolyline(new PolylineOptions()
                                .add(new LatLng(src.latitude, src.longitude), new LatLng(dest.latitude, dest.longitude))
                                .width(2)
                                .color(Color.BLUE).geodesic(true));
                    }
                    */

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    private String getJsonFromUrl(String URL) throws IOException {

        Request request = new Request.Builder().url(URL).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(Constant.CONECTION_TIMEOUT, TimeUnit.SECONDS);
        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}
