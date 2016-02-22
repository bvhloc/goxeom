package asia.covisoft.goom.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Covisoft on 12/01/2016.
 */
public class SystemHelper {

    @SuppressWarnings("ConstantConditions")
    public void hideKeyboard(Activity context) {

        try {
            InputMethodManager inputManager =
                    (InputMethodManager) context.
                            getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(
                    context.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }catch (Exception ignored){

        }

    }
}
