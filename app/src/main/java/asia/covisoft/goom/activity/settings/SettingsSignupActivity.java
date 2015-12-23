package asia.covisoft.goom.activity.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.R;
import asia.covisoft.goom.mvp.model.SettingsSignupModel;
import asia.covisoft.goom.mvp.presenter.SettingsSignupPresenter;
import asia.covisoft.goom.mvp.view.SettingsSignupView;
import asia.covisoft.goom.utils.Constant;

public class SettingsSignupActivity extends BaseActivity implements SettingsSignupView {

    private Context mContext;
    private SettingsSignupModel model;
    private SettingsSignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_signup);
        mContext = this;
        presenter = new SettingsSignupPresenter(this);
        model = new SettingsSignupModel();

        initView();
    }

    private EditText edtEmail, edtUsername, edtPhone, edtPassword, edtConfirm;

    private void initView() {

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtConfirm = (EditText) findViewById(R.id.edtConfirm);
        edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!s.toString().isEmpty()){

                    findViewById(R.id.lnlConfirm).setVisibility(View.VISIBLE);
                }
            }
        });
        findViewById(R.id.btnSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(mContext, SettingsSignupVerifyActivity.class));
                btnSignupOnClick();
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

    private void btnSignupOnClick() {

        if (validInput()) {
            presenter.signUp(model);
        }
    }

    private boolean validInput() {

        model.email = edtEmail.getText().toString();
        model.phone = edtPhone.getText().toString();
        model.username = edtUsername.getText().toString();
        model.password = edtPassword.getText().toString();
        model.confirm = edtConfirm.getText().toString();

        if (model.email.isEmpty()) {

            edtEmail.setError(getString(R.string.activity_settings_signup_error_email_empty));
            edtEmail.requestFocus();
            return false;
        }
        if (model.phone.length() > 11) {
            edtPhone.setError(getString(R.string.activity_settings_signup_error_phone_empty));
            edtPhone.requestFocus();
            return false;
        }
        if (model.phone.length() > 11) {
            edtPhone.setError(getString(R.string.activity_settings_signup_error_phone_length));
            edtPhone.requestFocus();
            return false;
        }
        if (model.username.isEmpty()) {

            edtUsername.setError(getString(R.string.activity_settings_signup_error_username_empty));
            edtUsername.requestFocus();
            return false;
        }
        if (model.password.isEmpty()) {

            edtPassword.setError(getString(R.string.activity_settings_signup_error_password_empty));
            edtPassword.requestFocus();
            return false;
        }
        if (model.confirm.isEmpty()) {

            edtConfirm.setError(getString(R.string.activity_settings_signup_error_confirm_empty));
            edtConfirm.requestFocus();
            return false;
        }
        if (!model.password.equals(model.confirm)) {
            edtConfirm.setError(getString(R.string.activity_settings_signup_error_confirm));
            edtConfirm.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void onSignup(int result) {

        switch (result) {

            case 1:

                new AlertDialog.Builder(mContext)
                        .setMessage(getString(R.string.activity_settings_signup_dialog_success))
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
            case 2:

                edtUsername.setError(getString(R.string.activity_settings_signup_error_username_exist));
                edtUsername.requestFocus();

                break;
            case 3:

                edtEmail.setError(getString(R.string.activity_settings_signup_error_email_exist));
                edtEmail.requestFocus();

                break;
            case 4:

                edtPhone.setError(getString(R.string.activity_settings_signup_error_phone_exist));
                edtPhone.requestFocus();

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
