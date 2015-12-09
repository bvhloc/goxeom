package asia.covisoft.goom;

import android.app.Application;
import android.util.Log;

/**
 * Created by Covisoft on 09/12/2015.
 */
public class GoOm extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("myDebug", "appStart");
    }
}
