package asia.covisoft.goom.activity.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.mvp.model.SettingsLoginModel;
import asia.covisoft.goom.mvp.presenter.SettingsLoginPresenter;
import asia.covisoft.goom.mvp.view.SettingsLoginView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Preferences;

@SuppressWarnings("FieldCanBeLocal")
public class SettingsLoginActivity extends BaseActivity implements SettingsLoginView {

    private Context mContext;
    private SettingsLoginModel model;
    private SettingsLoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_login);
        mContext = this;
        presenter = new SettingsLoginPresenter(this);
        model = new SettingsLoginModel();

        initView();
    }

    private EditText edtUsername, edtPassword;
    private TextView tvError;

    private void initView() {

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        tvError = (TextView) findViewById(R.id.tvError);
        CheckBox chkShowPassword = (CheckBox) findViewById(R.id.chkShowPassword);
        chkShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edtPassword.setInputType(129);
                }
            }
        });
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnLoginOnClick();
            }
        });
    }

    private void btnLoginOnClick() {

        if (validInput()) {

            presenter.login(model);
        }
    }

    private boolean validInput() {

        model.setUsername(edtUsername.getText().toString());
        model.setPassword(edtPassword.getText().toString());
        if (model.getUsername().isEmpty()) {

            tvError.setText(getString(R.string.activity_settings_signup_error_username_empty));
            edtUsername.requestFocus();
            return false;
        }
        if (model.getPassword().isEmpty()) {

            tvError.setText(getString(R.string.activity_settings_signup_error_password_empty));
            edtPassword.requestFocus();
            return false;
        }

        return true;
    }

    private final int LOGIN_SUCCESS = 1;
    private final int LOGIN_FAIL = 2;
    private final int USERNAME_NOT_EXIST = 3;
    @Override
    public void onLogin(SettingsLoginModel model) {

        switch (model.getLoginResult()) {

            case LOGIN_SUCCESS:

                SharedPreferences loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);


//                loginPreferences.edit().putString(Preferences.LOGIN_PREFERENCES_TOKEN, model.getToken()).apply();
                loginPreferences.edit()
                        .putString(Preferences.LOGIN_PREFERENCES_TOKEN, "O9GJzRwlZrvDrmLOLBRA")
                        .apply();//TODO remove testing code

                new AlertDialog.Builder(mContext)
                        .setMessage(getString(R.string.activity_settings_login_dialog_success))
                        .setNeutralButton(getString(R.string.lowcase_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = getBaseContext().getPackageManager()
                                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra(Constant.TAB_POSTION, 3);
                                startActivity(intent);

                            }
                        }).show();

                break;
            case LOGIN_FAIL:

                String extendMessage = "";
                if (model.getFailCount() >= 2) {
                    extendMessage = ", login fail " + model.getFailCount() + " times";
                }
                tvError.setText(getString(R.string.activity_settings_login_error_wrongpassword) + extendMessage);

                break;
            case USERNAME_NOT_EXIST:

                tvError.setText(getString(R.string.activity_settings_login_error_username_notexist));

                break;
        }
    }

    @Override
    public void onConnectionFail() {

        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_connection_fail))
                .setNeutralButton(getString(R.string.lowcase_ok), null)
                .show();
    }
}
