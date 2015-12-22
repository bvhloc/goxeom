package asia.covisoft.goom;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import asia.covisoft.goom.eventbus.ActivityResultEvent;
import asia.covisoft.goom.utils.Constant;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private TabFragment tabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // withholding the previously created fragment from being created again
            // On orientation change, it will prevent fragment recreation
            // its necessary to reserving the fragment stack inside each tab
            int tabPos = getIntent().getIntExtra(Constant.TAB_POSTION, 1);
            initScreen(tabPos);

        } else {
            // restoring the previously created fragment
            // and getting the reference
            tabFragment = (TabFragment) getSupportFragmentManager().getFragments().get(0);
        }
    }

    private void initScreen(int tabPos) {
        // Creating the ViewPager container fragment once
        tabFragment = TabFragment.newInstance(tabPos);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, tabFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {

        if (!tabFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class
            super.onBackPressed();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //push received data to fragment by EventBus
        EventBus.getDefault().post(new ActivityResultEvent(requestCode, resultCode, data));
    }


}
