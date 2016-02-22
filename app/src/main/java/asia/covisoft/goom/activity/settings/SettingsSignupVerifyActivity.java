package asia.covisoft.goom.activity.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.prefs.Constant;
import asia.covisoft.goom.R;

public class SettingsSignupVerifyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_signup_verify);

        initView();
    }

    private Button btnSubmit;

    private void initView(){

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Constant.TAB_POSTION, 3);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //disable onBackPressed
    }
}
