package asia.covisoft.goom.fragment.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;

import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.R;
import asia.covisoft.goom.activity.history.HistoryDetailsActivity;
import asia.covisoft.goom.adapter.list.HistoryListAdapter;
import asia.covisoft.goom.backpress.BackFragment;
import asia.covisoft.goom.pojo.HistoryItem;


public class HistoryFragment extends BackFragment {

    public HistoryFragment() {
        // Required empty public constructor
    }

    private ListView lvInprocess, lvCompleted;
    private RadioButton rdbInprocess, rdbCompleted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        lvInprocess = (ListView) rootView.findViewById(R.id.lvInprocess);
        lvCompleted = (ListView) rootView.findViewById(R.id.lvCompleted);
        rdbInprocess = (RadioButton) rootView.findViewById(R.id.rdbInprocess);
        rdbCompleted = (RadioButton) rootView.findViewById(R.id.rdbCompleted);

        return rootView;
    }

    private Context mContext;

    private HistoryListAdapter inprocessAdapter, completedAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        inprocessAdapter = new HistoryListAdapter(mContext, dataSet1());
        lvInprocess.setAdapter(inprocessAdapter);
        lvInprocess.setOnItemClickListener(inprocessListener);

        completedAdapter = new HistoryListAdapter(mContext, dataSet2());
        lvCompleted.setAdapter(completedAdapter);
        lvCompleted.setOnItemClickListener(completedListener);

        rdbInprocess.setTextColor(ContextCompat.getColor(mContext, R.color.mAppBackground));
        rdbInprocess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    //show Category
                    lvInprocess.setVisibility(View.VISIBLE);
                    rdbInprocess.setTextColor(ContextCompat.getColor(mContext, R.color.mAppBackground));

                    //hide NearMe
                    lvCompleted.setVisibility(View.GONE);
                    rdbCompleted.setTextColor(ContextCompat.getColor(mContext, R.color.mGreen));
                } else {

                    //hide Category
                    lvInprocess.setVisibility(View.GONE);
                    rdbInprocess.setTextColor(ContextCompat.getColor(mContext, R.color.mGreen));

                    //show NearMe
                    lvCompleted.setVisibility(View.VISIBLE);
                    rdbCompleted.setTextColor(ContextCompat.getColor(mContext, R.color.mAppBackground));
                }
            }
        });
    }

    private AdapterView.OnItemClickListener inprocessListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            startActivity(new Intent(mContext, HistoryDetailsActivity.class).putExtra(Constant.HISTORY_STATE, false));
        }
    };
    private AdapterView.OnItemClickListener completedListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            startActivity(new Intent(mContext, HistoryDetailsActivity.class).putExtra(Constant.HISTORY_STATE, true));
        }
    };

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
