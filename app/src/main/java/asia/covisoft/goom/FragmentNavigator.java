package asia.covisoft.goom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Covisoft on 19/11/2015.
 */
public class FragmentNavigator {

    public static void goTo(Fragment from, Fragment to, boolean addToBackStack) {

        FragmentTransaction transaction = from.getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        transaction.replace(R.id.tab_container, to);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void goTo(Fragment from, Fragment to) {

        goTo(from, to, true);
    }
}
