package asia.covisoft.goom.mvp.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.GPSTracker;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.view.HistoryDetailsView;
import asia.covisoft.goom.pojo.gson.LoaddetailhistoryRoot;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;

public class HistoryDetailsPresenter {

    private HistoryDetailsView view;
    private Context context;

    public HistoryDetailsPresenter(HistoryDetailsView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void setupTitle(Bundle extras) {

        boolean HISTORY_STATE = extras.getBoolean(Extras.HISTORY_STATE, true);
        String newTitle = HISTORY_STATE ? context.getString(R.string.upcase_completed) : context.getString(R.string.upcase_inprocess);
        view.setTitle(newTitle);
    }

    public void setupMap() {

        GPSTracker gpsTracker = new GPSTracker(context);
        LatLng currentLatLng = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

        view.onMapReady(currentLatLng);
    }

    private ProgressDialog progressDialog;

    public void loadInfo(final String userToken, final String id) {
        new AsyncTask<String, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected Boolean doInBackground(String... params) {

                String URL = Constant.HOST +
                        "loaddetailhistory.php?token=" + userToken +
                        "&id=" + id;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    LoaddetailhistoryRoot rootObject = new Gson().fromJson(json, LoaddetailhistoryRoot.class);

                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JsonSyntaxException e) {
                    return false;
                }

                return false;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                progressDialog.dismiss();
                if (success) {

//                    view.onInprocessListReady(model.getInprocessList());
//                    view.onCompletedListReady(model.getCompletedList());
                }
            }
        }.execute();
    }
}
