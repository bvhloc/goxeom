package asia.covisoft.goom.mvp.view;

import java.util.ArrayList;

import asia.covisoft.goom.pojo.HistoryItem;

public interface HistoryView {

    void onInprocessListReady(ArrayList<HistoryItem> historyItems);
    void onCompletedListReady(ArrayList<HistoryItem> historyItems);
}
