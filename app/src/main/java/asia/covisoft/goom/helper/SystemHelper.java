package asia.covisoft.goom.helper;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Covisoft on 12/01/2016.
 */
public class SystemHelper {

    @SuppressWarnings("ConstantConditions")
    public void hideKeyboard(Activity context) {

        InputMethodManager inputManager =
                (InputMethodManager) context.
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                context.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
