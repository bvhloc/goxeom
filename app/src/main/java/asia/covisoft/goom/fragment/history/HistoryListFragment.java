package asia.covisoft.goom.fragment.history;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import asia.covisoft.goom.FragmentNavigator;
import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.HistoryListAdapter;
import asia.covisoft.goom.backpress.RootFragment;
import asia.covisoft.goom.pojo.HistoryItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryListFragment extends RootFragment implements AdapterView.OnItemClickListener {

    private boolean historyState;

    public HistoryListFragment(boolean historyState) {

        this.historyState = historyState;
    }

    private ListView lvHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history_list, container, false);

        lvHistory = (ListView) rootView.findViewById(R.id.lvHistory);

        return rootView;
    }

    private HistoryListAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!historyState){
            adapter = new HistoryListAdapter(this.getContext(), dataSet1());
        }else {
            adapter = new HistoryListAdapter(this.getContext(), dataSet2());
        }
        lvHistory.setAdapter(adapter);
        lvHistory.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FragmentNavigator.goTo(getParentFragment(), HistoryDetailsFragment.newInstance(historyState));
    }

    private ArrayList<HistoryItem> dataSet1() {
        ArrayList<HistoryItem> dataSet = new ArrayList<>();
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        return dataSet;
    }

    private ArrayList<HistoryItem> dataSet2() {
        ArrayList<HistoryItem> dataSet = new ArrayList<>();
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", false));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", false));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        dataSet.add(new HistoryItem("Sep 3rd, 2015 - 18:05", "20, Trường Chinh, Tân Bình, HCM", true));
        return dataSet;
    }
}
