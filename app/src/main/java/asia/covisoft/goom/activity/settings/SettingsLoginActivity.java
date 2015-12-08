package asia.covisoft.goom.activity.settings;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.R;

public class SettingsLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_login);

        String url = "http://192.168.0.20:90/webservice/login.php?username=john&password=Saltro45";
        JsonParseAsyncTask asyncTask = new JsonParseAsyncTask(this);
        asyncTask.execute(url);
    }

    private class JsonParseAsyncTask extends AsyncTask<String, Void, String> {

        private Context mContext;
        private ProgressBar progressBar;

        public JsonParseAsyncTask(Context context) {

            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {

            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String json = null;
            String urlString = params[0];
            try {
                Request request = new Request.Builder().url(urlString).build();
                Response response = new OkHttpClient().newCall(request).execute();
                json = response.body().string();

                Log.d("myDebug", json);

                JSONObject jsonRootObject = new JSONObject(json);

                JSONArray jsonArray = jsonRootObject.optJSONArray("data");

//                //Iterate the jsonArray and print the info of JSONObjects
//                for (int i = 0; i < jsonArray.length(); i++) {
//
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    String email = jsonObject.optString("email");
//
//                    Log.d("myDebug", email);
//                }

                JSONObject jsonObject = jsonArray.optJSONObject(0);

                String email = jsonObject.optString("email");

                Log.d("myDebug", email);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return json;
        }


        @Override
        protected void onPostExecute(String aVoid) {

            progressBar.setVisibility(View.INVISIBLE);

            Log.d("myDebug", aVoid);

            super.onPostExecute(aVoid);
        }
    }

}
