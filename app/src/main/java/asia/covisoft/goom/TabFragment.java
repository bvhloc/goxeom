package asia.covisoft.goom;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.covisoft.goom.adapter.page.ViewPagerAdapter;
import asia.covisoft.goom.backpress.OnBackPressListener;
import asia.covisoft.goom.view.WorkaroundMapFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

    public TabFragment() {
        // Required empty public constructor
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ViewPagerAdapter adapter;

    public static TabFragment newInstance(int tabPos) {
        TabFragment f = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.TAB_POSTION, tabPos);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new ViewPagerAdapter(getResources(), getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons(tabLayout);

        viewPager.setCurrentItem(getArguments().getInt(Constant.TAB_POSTION));
    }

    @SuppressWarnings("ConstantConditions")
    private void setupTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.drawable.tab_history);
        tabLayout.getTabAt(1).setIcon(R.drawable.tab_order);
        tabLayout.getTabAt(2).setIcon(R.drawable.tab_wallet);
        tabLayout.getTabAt(3).setIcon(R.drawable.tab_setting);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);

        return rootView;
    }

    public boolean onBackPressed() {

        // currently visible tab Fragment
        OnBackPressListener currentFragment = (OnBackPressListener) adapter.getRegisteredFragment(viewPager.getCurrentItem());

        if (currentFragment != null) {
            // lets see if the currentFragment or any of its childFragment can handle onBackPressed
            return currentFragment.onBackPressed();
        }

        // this Fragment couldn't handle the onBackPressed call
        return false;
    }


}
