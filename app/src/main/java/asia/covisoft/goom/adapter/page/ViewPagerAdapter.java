package asia.covisoft.goom.adapter.page;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import asia.covisoft.goom.R;
import asia.covisoft.goom.fragment.history.HistoryFragment;
import asia.covisoft.goom.fragment.order.OrderFragment;
import asia.covisoft.goom.fragment.settings.SettingsFragment;
import asia.covisoft.goom.fragment.wallet.WalletFragment;

/**
 * Created by Covisoft on 17/11/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final Resources resources;

    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public ViewPagerAdapter(final Resources resources, FragmentManager fm) {
        super(fm);
        this.resources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        final Fragment result;
        switch (position) {
            case 0:
                // First Fragment of First Tab
                result = new HistoryFragment();
                break;
            case 1:
                // First Fragment of Second Tab
                result = new OrderFragment();
                break;
            case 2:
                // First Fragment of Third Tab
                result = new WalletFragment();
                break;
            case 3:
                // First Fragment of Fourth Tab
                result = new SettingsFragment();
                break;
            default:
                result = null;
                break;
        }

        return result;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        switch (position) {
            case 0:
                return resources.getString(R.string.history_tab_title);
            case 1:
                return resources.getString(R.string.order_tab_title);
            case 2:
                return resources.getString(R.string.wallet_tab_title);
            case 3:
                return resources.getString(R.string.settings_tab_title);
            default:
                return null;
        }
    }

    /**
     * On each Fragment instantiation we are saving the reference of that Fragment in a Map
     * It will help us to retrieve the Fragment by position
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    /**
     * Remove the saved reference from our Map on the Fragment destroy
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }


    /**
     * Get the Fragment by position
     *
     * @param position tab position of the fragment
     * @return
     */
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}