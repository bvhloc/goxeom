package asia.covisoft.goom.helper;

import android.view.View;

/**
 * Created by Covisoft on 18/02/2016.
 */
public class ViewHelper {

    public static void gone(View... views) {

        for (View view : views) {
            view.setVisibility(View.GONE);
        }
    }

    public static void visible(View... views) {

        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void invisible(View... views) {

        for (View view : views) {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
