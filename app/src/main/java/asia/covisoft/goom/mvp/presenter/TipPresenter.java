package asia.covisoft.goom.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.view.TipView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Preferences;

public class TipPresenter {

    private TipView view;
    private Context context;

    public TipPresenter(TipView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void tip(final String tradingId, final String value) {

        final String userToken = context.getSharedPreferences(Preferences.LOGIN_PREFERENCES, Activity.MODE_PRIVATE)
                .getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "usertip.php?token=" + userToken +
                        "&tradingid=" + tradingId +
                        "&tip=" + value;
                Log.d("sdb", URL);

                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    JSONObject jsonRootObject = new JSONObject(json);

                    int result = jsonRootObject.optInt("usertip");

                    return result == 0;

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                if(success){
                    view.onTipConfirm();
                }else {
                    view.onConnectionFail();
                }
            }
        }.execute();
    }
}
