package asia.covisoft.goom.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import asia.covisoft.goom.R;

/**
 * Created by Covisoft on 02/12/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
