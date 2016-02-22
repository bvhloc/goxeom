package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.NetworkClient;
import asia.covisoft.goom.mvp.view.OrderMadeView;
import asia.covisoft.goom.prefs.Constant;
import asia.covisoft.goom.prefs.Preferences;

public class OrderMadePresenter {

    private OrderMadeView view;
    private Context context;

    public OrderMadePresenter(OrderMadeView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public final String CONFIRM_VALUE_INPROCESS = "inprocess";
    public final String CONFIRM_VALUE_CANCEL = "cancel";

    public final int CONFIRM_RESULT_SUCCESS = 0;
    public final int CONFIRM_RESULT_CANCELED = 1;

    private ProgressDialog progressDialog;

    public void setConfirm(final String bookingId, final String value) {
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @SuppressWarnings("UnnecessaryLocalVariable")
            @Override
            protected Integer doInBackground(Void... params) {

                SharedPreferences loginPreferences = context
                        .getSharedPreferences(Preferences.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
                String userToken = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");

                String URL = Constant.HOST +
                        "setconfirm.php?token=" + userToken +
                        "&tradingid=" + bookingId +
                        "&value=" + value;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);
                    Log.d("sdb", json);

                    JSONObject rootObject = new JSONObject(json);

                    int result = rootObject.optInt("setconfirm");

                    return result;

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);

                progressDialog.dismiss();
                if (result == null)
                    view.onConnectionFail();
                else {
                    switch (result){
                        case CONFIRM_RESULT_SUCCESS:

                            view.onBookingCanceled();

                            break;
                        case CONFIRM_RESULT_CANCELED:

                            view.onBookingCanceled();

                            break;
                    }
                }
            }

        }.execute();
    }
}
