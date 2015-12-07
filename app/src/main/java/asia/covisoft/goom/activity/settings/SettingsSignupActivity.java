package asia.covisoft.goom.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    private Button btnSignup;

    private void initView(){

        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, SettingsSignupVerifyActivity.class));
            }
        });
    }
}
