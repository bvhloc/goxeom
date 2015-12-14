package asia.covisoft.goom.activity.settings;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.helper.Encoder;

public class SettingsLoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_login);
        initView();

        setupStaticData();

//        String url = "http://192.168.0.20:90/webservice/login.php?username=CoviTester1&password=CoviTester";
//        JsonParseAsyncTask asyncTask = new JsonParseAsyncTask(this);
//        asyncTask.execute(url);
    }

    private EditText edtUsername, edtPassword;
    private CheckBox chkShowPassword;
    private Button btnLogin;

    private void initView() {

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        chkShowPassword = (CheckBox) findViewById(R.id.chkShowPassword);
        chkShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edtPassword.setInputType(129);
                }
            }
        });
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtPassword.setText(Encoder.encryptByMD5(edtUsername.getText().toString()));

            }
        });
    }

    private class JsonParseAsyncTask extends AsyncTask<String, Void, String> {

        private Context mContext;
        private ProgressBar progressBar;

        public JsonParseAsyncTask(Context context) {

            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {

//            progressBar = (ProgressBar) findViewById(R.id.progressBar);
//            progressBar.setVisibility(View.VISIBLE);

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String json = null;
            String urlString = params[0];
            try {
                Request request = new Request.Builder().url(urlString).build();
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(20, TimeUnit.SECONDS);
                Response response = okHttpClient.newCall(request).execute();
                json = response.body().string();

                JSONObject jsonRootObject = new JSONObject(json);

//                JSONArray jsonArray = jsonRootObject.optJSONArray("data");
//
//                //Iterate the jsonArray and print the info of JSONObjects
//                for (int i = 0; i < jsonArray.length(); i++) {
//
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    String email = jsonObject.optString("email");
//
//                    Log.d("myDebug", email);
//                }

                JSONObject loginObject = jsonRootObject.optJSONObject("login");

                int value = loginObject.optInt("Value");
                if (value == 1) {

                    Log.d("myDebug", loginObject.optString("Type"));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return json;
        }


        @Override
        protected void onPostExecute(String aVoid) {

//            progressBar.setVisibility(View.INVISIBLE);

            Log.d("myDebug", aVoid);

            super.onPostExecute(aVoid);
        }
    }

    private void setupStaticData() {

        edtUsername.setText("CoviTester1");
        edtPassword.setText("CoviTester");
    }
}
