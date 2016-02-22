package asia.covisoft.goom.utils;

import android.content.Context;
import android.content.Intent;

import asia.covisoft.goom.MainActivity;
import asia.covisoft.goom.prefs.Constant;
import asia.covisoft.goom.prefs.Extras;

public class AppHelper {

    private Context context;

    public AppHelper(Context context) {
        this.context = context;
    }

    public void restartToMain(int tabPos){
        restartToMain(tabPos, false);
    }

    public void restartToMain(int tabPos, boolean isLogin) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Constant.TAB_POSTION, tabPos);
        if (isLogin) {
            intent.putExtra(Extras.IS_LOGIN, true);
        }
        context.startActivity(intent);
    }

    public void restartApp() {

        Intent intent = context.getApplicationContext().getPackageManager()
                .getLaunchIntentForPackage(context.getApplicationContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
