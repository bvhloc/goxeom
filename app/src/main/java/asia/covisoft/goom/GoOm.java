package asia.covisoft.goom;

import android.app.Application;

public class GoOm extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        Log.d("myDebug", "appStart");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
