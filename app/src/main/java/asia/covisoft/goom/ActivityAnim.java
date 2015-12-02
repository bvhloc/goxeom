package asia.covisoft.goom;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Covisoft on 02/12/2015.
 */
public class ActivityAnim {

    public static void forward(Activity context){

        context.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public static void back(Activity context){

        context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
