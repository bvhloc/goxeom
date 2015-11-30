package asia.covisoft.goom.fragment.history;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.covisoft.goom.R;
import asia.covisoft.goom.adapter.page.HistoryPagerAdapter;
import asia.covisoft.goom.backpress.RootFragment;
import asia.covisoft.goom.view.NonSwipeableViewPager;


public class HistoryFragment extends RootFragment {

    public HistoryFragment() {
        // Required empty public constructor
    }

    private HistoryPagerAdapter adapter;
    private TabLayout tabsHistory;
    private NonSwipeableViewPager vpHistory;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new HistoryPagerAdapter(getResources(), getChildFragmentManager());
        vpHistory.setAdapter(adapter);

        tabsHistory.setupWithViewPager(vpHistory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        tabsHistory = (TabLayout) rootView.findViewById(R.id.tabsHistory);
        vpHistory = (NonSwipeableViewPager) rootView.findViewById(R.id.vpHistory);

        return rootView;
    }

}
