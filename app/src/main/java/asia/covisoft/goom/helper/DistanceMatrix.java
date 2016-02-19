package asia.covisoft.goom.helper;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DistanceMatrix {

    public static void requestJourneyInfo(final LatLng originsLatLng, final LatLng destinationLatLng, final OnInfoReceivedListener listener) {

        new AsyncTask<Void, Void, Result>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                listener.onPreRequest();
            }

            @Override
            protected Result doInBackground(Void... params) {

                String origins = originsLatLng.latitude + "," + originsLatLng.longitude;
                String destination = destinationLatLng.latitude + "," + destinationLatLng.longitude;

                String URL = "http://maps.googleapis.com/maps/api/distancematrix/json?" +
                        "origins=" + origins +
                        "&destinations=" + destination +
                        "&mode=driving&language=en-EN&sensor=false";
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject rootObject = new JSONObject(json);

                    String status = rootObject.optString("status");
                    if (!status.equals("OK")) {
                        return null;
                    }

                    Result result = new Result();

                    JSONArray origin_addresses = rootObject.optJSONArray("origin_addresses");
                    JSONArray destination_addresses = rootObject.optJSONArray("destination_addresses");
                    String origin_address = origin_addresses.getString(0);
                    String destination_address = destination_addresses.getString(0);
                    result.setOrigin_address(origin_address);
                    result.setDestination_address(destination_address);

                    JSONArray rows = rootObject.optJSONArray("rows");
                    JSONArray elements = rows.getJSONObject(0).optJSONArray("elements");

                    for (int i = 0; i < elements.length(); i++) {
                        JSONObject element = elements.getJSONObject(0);
                        String elementStatus = element.optString("status");
                        if (elementStatus.equals("OK")) {
                            JSONObject distance = element.optJSONObject("distance");
                            String distance_text = distance.optString("text");
                            String distance_value = distance.optString("value");
                            result.setDistance_text(distance_text);
                            result.setDistance_value(distance_value);
                            JSONObject duration = element.optJSONObject("duration");
                            String duration_text = duration.optString("text");
                            String duration_value = duration.optString("value");
                            result.setDuration_text(duration_text);
                            result.setDuration_value(duration_value);
                            break;
                        }
                    }

                    return result;

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Result result) {
                super.onPostExecute(result);

                if (result != null) {
                    listener.onResultReceived(result.getOrigin_address(), result.getDestination_address(),
                            result.getDistance_text(), result.getDistance_value(),
                            result.getDuration_text(), result.getDuration_value());
                } else {
                    listener.onFail();
                }
            }
        }.execute();
    }

    public interface OnInfoReceivedListener {

        void onPreRequest();

        void onResultReceived(String originAddress, String destinationAddress, String distanceText, String distanceValue, String durationText, String durationValue);

        void onFail();
    }

    private static class Result {

        private String origin_address;
        private String destination_address;
        private String distance_text;
        private String distance_value;
        private String duration_text;
        private String duration_value;

        public String getOrigin_address() {
            return origin_address;
        }

        public void setOrigin_address(String origin_address) {
            this.origin_address = origin_address;
        }

        public String getDestination_address() {
            return destination_address;
        }

        public void setDestination_address(String destination_address) {
            this.destination_address = destination_address;
        }

        public String getDistance_text() {
            return distance_text;
        }

        public void setDistance_text(String distance_text) {
            this.distance_text = distance_text;
        }

        public String getDistance_value() {
            return distance_value;
        }

        public void setDistance_value(String distance_value) {
            this.distance_value = distance_value;
        }

        public String getDuration_text() {
            return duration_text;
        }

        public void setDuration_text(String duration_text) {
            this.duration_text = duration_text;
        }

        public String getDuration_value() {
            return duration_value;
        }

        public void setDuration_value(String duration_value) {
            this.duration_value = duration_value;
        }
    }
}
