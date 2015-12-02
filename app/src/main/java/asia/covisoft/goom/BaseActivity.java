package asia.covisoft.goom;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import asia.covisoft.goom.ActivityAnim;

/**
 * Created by Covisoft on 02/12/2015.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityAnim.forward(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityAnim.back(this);
    }
}
