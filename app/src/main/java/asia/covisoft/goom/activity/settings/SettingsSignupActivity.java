package asia.covisoft.goom.activity.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.utils.AppHelper;
import asia.covisoft.goom.mvp.model.SettingsSignupModel;
import asia.covisoft.goom.mvp.presenter.SettingsSignupPresenter;
import asia.covisoft.goom.mvp.view.SettingsSignupView;
import asia.covisoft.goom.prefs.Preferences;

@SuppressWarnings("FieldCanBeLocal")
public class SettingsSignupActivity extends BaseActivity implements SettingsSignupView {

    private Context mContext;
    private SettingsSignupModel model;
    private SettingsSignupPresenter presenter;

    private SharedPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_signup);
        mContext = this;
        presenter = new SettingsSignupPresenter(this);
        model = new SettingsSignupModel();

        loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);
        initView();
    }

    private EditText edtEmail, edtUsername, edtPhone, edtPassword, edtConfirm;

    private void initView() {

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtCurrentPassword);
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

                if (!s.toString().isEmpty()) {

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

        model.setEmail(edtEmail.getText().toString());
        model.setPhone(edtPhone.getText().toString());
        model.setUsername(edtUsername.getText().toString());
        model.setPassword(edtPassword.getText().toString());
        model.setConfirm(edtConfirm.getText().toString());

        if (model.getEmail().isEmpty()) {

            edtEmail.setError(getString(R.string.activity_settings_signup_error_email_empty));
            edtEmail.requestFocus();
            return false;
        }
        if (model.getPhone().isEmpty()) {
            edtPhone.setError(getString(R.string.activity_settings_signup_error_phone_empty));
            edtPhone.requestFocus();
            return false;
        }
        if (model.getPhone().length() > 11) {
            edtPhone.setError(getString(R.string.activity_settings_signup_error_phone_length));
            edtPhone.requestFocus();
            return false;
        }
        if (model.getUsername().isEmpty()) {

            edtUsername.setError(getString(R.string.activity_settings_signup_error_username_empty));
            edtUsername.requestFocus();
            return false;
        }
        if (model.getPassword().isEmpty()) {

            edtPassword.setError(getString(R.string.activity_settings_signup_error_password_empty));
            edtPassword.requestFocus();
            return false;
        }
        if (model.getConfirm().isEmpty()) {

            edtConfirm.setError(getString(R.string.activity_settings_signup_error_confirm_empty));
            edtConfirm.requestFocus();
            return false;
        }
        if (!model.getPassword().equals(model.getConfirm())) {
            edtConfirm.setError(getString(R.string.activity_settings_signup_error_confirm));
            edtConfirm.requestFocus();
            return false;
        }

        return true;
    }

    private final int SIGN_UP_SUCCESS = 1;
    private final int USERNAME_EXIST = 2;
    private final int EMAIL_EXIST = 3;
    private final int PHONE_EXIST = 4;

    @Override
    public void onSignup(int result) {

        switch (result) {

            case SIGN_UP_SUCCESS:

                loginPreferences.edit()
                        .putString(Preferences.LOGIN_PREFERENCES_USERNAME, model.getUsername())
                        .putString(Preferences.LOGIN_PREFERENCES_PASSWORD, model.getPassword())
                        .apply();

                new AlertDialog.Builder(mContext)
                        .setMessage(getString(R.string.activity_settings_signup_dialog_success))
                        .setNeutralButton(getString(R.string.lowcase_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                new AppHelper(mContext).restartToMain(3);

                            }
                        }).show();

                break;
            case USERNAME_EXIST:

                edtUsername.setError(getString(R.string.activity_settings_signup_error_username_exist));
                edtUsername.requestFocus();

                break;
            case EMAIL_EXIST:

                edtEmail.setError(getString(R.string.activity_settings_signup_error_email_exist));
                edtEmail.requestFocus();

                break;
            case PHONE_EXIST:

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
