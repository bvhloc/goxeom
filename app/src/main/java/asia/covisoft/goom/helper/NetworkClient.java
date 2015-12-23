package asia.covisoft.goom.helper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import asia.covisoft.goom.utils.Constant;

/**
 * Created by Covisoft on 23/12/2015.
 */
public class NetworkClient {

    public String getJsonFromUrl(String URL) throws IOException {

        Request request = new Request.Builder().url(URL).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(Constant.CONECTION_TIMEOUT, TimeUnit.SECONDS);
        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();
    }
}
