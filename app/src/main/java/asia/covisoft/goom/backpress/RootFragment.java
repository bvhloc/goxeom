package asia.covisoft.goom.backpress;

import android.support.v4.app.Fragment;

import asia.covisoft.goom.backpress.BackPressImpl;
import asia.covisoft.goom.backpress.OnBackPressListener;

public class RootFragment extends Fragment implements OnBackPressListener {

    @Override
    public boolean onBackPressed() {
        return new BackPressImpl(this).onBackPressed();
    }
}
