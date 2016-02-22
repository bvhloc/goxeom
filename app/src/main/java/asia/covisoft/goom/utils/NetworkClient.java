package asia.covisoft.goom.utils;

import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import asia.covisoft.goom.prefs.Constant;

public class NetworkClient {

    public String getJsonFromUrl(String URL) throws IOException {

        Request request = new Request.Builder().url(URL).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(Constant.CONECTION_TIMEOUT, TimeUnit.SECONDS);
        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();
    }

    public static void checkInternetConnection(final int timeout /*seconds*/, final OnConnectedListener listener) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                listener.onPreConnect();
            }

            @Override
            protected Boolean doInBackground(Void... params) {

                Request request = new Request.Builder().url("https://www.google.com").build();
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(timeout, TimeUnit.SECONDS);
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    return response != null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean responded) {
                super.onPostExecute(responded);
                if (responded) {
                    listener.onConnected();
                } else {
                    listener.onFail();
                }
            }
        }.execute();
    }

    public interface OnConnectedListener {

        void onPreConnect();

        void onConnected();

        void onFail();
    }
}
