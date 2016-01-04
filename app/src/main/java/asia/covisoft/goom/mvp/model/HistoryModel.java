package asia.covisoft.goom.mvp.model;

import java.util.ArrayList;

import asia.covisoft.goom.pojo.HistoryItem;

/**
 * Created by Covisoft on 04/01/2016.
 */
public class HistoryModel {

    private ArrayList<HistoryItem> inprocessList;
    private ArrayList<HistoryItem> completedList;

    public ArrayList<HistoryItem> getInprocessList() {
        return inprocessList;
    }

    public void setInprocessList(ArrayList<HistoryItem> inprocessList) {
        this.inprocessList = inprocessList;
    }

    public ArrayList<HistoryItem> getCompletedList() {
        return completedList;
    }

    public void setCompletedList(ArrayList<HistoryItem> completedList) {
        this.completedList = completedList;
    }
}
