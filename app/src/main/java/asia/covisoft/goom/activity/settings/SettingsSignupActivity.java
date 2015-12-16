package asia.covisoft.goom.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.R;

public class SettingsSignupActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_signup);
        mContext = this;
        initView();
    }

    private void initView(){

        findViewById(R.id.btnSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, SettingsSignupVerifyActivity.class));
            }
        });
        findViewById(R.id.tvLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, SettingsLoginActivity.class));
                SettingsSignupActivity.this.finish();
            }
        });
    }
}
