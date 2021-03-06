package asia.covisoft.goom.mvp.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import asia.covisoft.goom.utils.NetworkClient;
import asia.covisoft.goom.mvp.model.HistoryModel;
import asia.covisoft.goom.mvp.view.HistoryView;
import asia.covisoft.goom.pojo.HistoryItem;
import asia.covisoft.goom.pojo.gson.LoadhistoryRoot;
import asia.covisoft.goom.prefs.Constant;

public class HistoryPresenter {

    private HistoryView view;
    private Context context;

    public HistoryPresenter(HistoryView view) {
        this.view = view;
        this.context = ((Fragment) view).getContext();
    }

    public void getHistory(final String userToken, final HistoryModel model) {

        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {


                String URL = Constant.HOST +
                        "loadhistory.php?token=" + userToken;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    LoadhistoryRoot root = new Gson().fromJson(json, LoadhistoryRoot.class);

                    List<LoadhistoryRoot.Loadhistory.Inprocess> inprocessList = root.getLoadhistory().getInprocess();
                    ArrayList<HistoryItem> inprocessItems = new ArrayList<>();
                    inprocessItems.addAll(inprocessList);

                    model.setInprocessList(inprocessItems);//TODO active this code

//                    //TODO remove this testing code
//                    List<LoadhistoryRoot.Loadhistory.Waiting> waitingList = root.getLoadhistory().getWaiting();
//                    inprocessItems.addAll(waitingList);
                    model.setInprocessList(inprocessItems);

                    List<LoadhistoryRoot.Loadhistory.Success> successList = root.getLoadhistory().getSuccess();
                    List<LoadhistoryRoot.Loadhistory.Cancel> cancelList = root.getLoadhistory().getCancel();
                    ArrayList<HistoryItem> completedItems = new ArrayList<>();
                    completedItems.addAll(successList);
                    completedItems.addAll(cancelList);
                    model.setCompletedList(completedItems);

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
                if (success) {

                    view.onInprocessListReady(model.getInprocessList());
                    view.onCompletedListReady(model.getCompletedList());
                }
            }
        }.execute();
    }
}
