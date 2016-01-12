package asia.covisoft.goom.activity.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.helper.DatetimeHelper;
import asia.covisoft.goom.mvp.model.SettingsProfileModel;
import asia.covisoft.goom.mvp.presenter.SettingsProfilePresenter;
import asia.covisoft.goom.mvp.view.SettingsProfileView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.DatetimeFormat;
import asia.covisoft.goom.utils.Preferences;

public class SettingsProfileActivity extends BaseActivity implements SettingsProfileView {

    private TextView tvUsername;
    private EditText edtEmail, edtFullname, edtPhone, edtBirthday, edtPassword, edtNewPassword, edtConfirmPassword;

    private void initView(){
        setContentView(R.layout.activity_settings_profile);

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtFullname = (EditText) findViewById(R.id.edtFullname);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtBirthday = (EditText) findViewById(R.id.edtBirthday);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtNewPassword = (EditText) findViewById(R.id.edtNewPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);

        new DatetimeHelper().addDatePicker(edtBirthday, DatetimeFormat.APP_DATE_FORMAT);
        findViewById(R.id.tvLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogLogout();
            }
        });
        findViewById(R.id.btnUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateInfo();
            }
        });
    }

    private Context mContext;
    private SettingsProfilePresenter presenter;
    private SettingsProfileModel model;

    private SharedPreferences loginPreferences;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mContext = this;
        presenter = new SettingsProfilePresenter(this);
        model = new SettingsProfileModel();

        loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);
        token = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_TOKEN, "");
        presenter.loadInfo(token);
    }

    @Override
    public void onInfoLoaded(SettingsProfileModel model) {

        this.model = model;
        tvUsername.setText(model.username);
        edtEmail.setText(model.email);
        edtFullname.setText(model.fullname);
        edtPhone.setText(model.phone);
        if(!model.birthday.equals("0000-00-00")){
            Calendar birthCal = new DatetimeHelper().getCalendar(model.birthday, DatetimeFormat.SERVER_DATE_FORMAT);
            String birthday  = new DatetimeHelper().getString(birthCal, DatetimeFormat.APP_DATE_FORMAT);
            edtBirthday.setText(birthday);
        }
    }

    private final int UPDATE_SUCCESS = 1;
    private final int EMAIL_EXIST = 2;
    private final int PHONE_EXIST = 3;

    @Override
    public void onInfoUpdate(Integer result) {

        switch (result) {

            case UPDATE_SUCCESS:

                Toast.makeText(mContext, getString(R.string.toast_updatesuccess), Toast.LENGTH_LONG).show();

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

    private void updateInfo() {

        if(validInput()){

            presenter.updateInfo(token, model);
        }
    }

    private boolean validInput() {

        model.newEmail = edtEmail.getText().toString();
        model.newFullname = edtFullname.getText().toString();
        model.newPhone = edtPhone.getText().toString();
        model.newBirthday = edtBirthday.getText().toString();

        if (model.newEmail.isEmpty()) {

            edtEmail.setError(getString(R.string.activity_settings_signup_error_email_empty));
            edtEmail.requestFocus();
            return false;
        }
        if (model.newPhone.isEmpty()) {
            edtPhone.setError(getString(R.string.activity_settings_signup_error_phone_empty));
            edtPhone.requestFocus();
            return false;
        }
        if (model.newPhone.length() > 11) {
            edtPhone.setError(getString(R.string.activity_settings_signup_error_phone_length));
            edtPhone.requestFocus();
            return false;
        }
        if (model.newBirthday.isEmpty()) {
            edtBirthday.setError(getString(R.string.activity_settings_profile_error_birthday_empty));
            edtBirthday.requestFocus();
            return false;
        }
//        if (model.getPassword().isEmpty()) {
//
//            edtPassword.setError(getString(R.string.activity_settings_signup_error_password_empty));
//            edtPassword.requestFocus();
//            return false;
//        }
//        if (model.getConfirm().isEmpty()) {
//
//            edtConfirm.setError(getString(R.string.activity_settings_signup_error_confirm_empty));
//            edtConfirm.requestFocus();
//            return false;
//        }
//        if (!model.getPassword().equals(model.getConfirm())) {
//            edtConfirm.setError(getString(R.string.activity_settings_signup_error_confirm));
//            edtConfirm.requestFocus();
//            return false;
//        }

        return true;
    }

    private void showDialogLogout() {

        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_logout))
                .setNegativeButton(getString(R.string.lowcase_no), null)
                .setPositiveButton(getString(R.string.lowcase_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        loginPreferences.edit()
                                .putString(Preferences.LOGIN_PREFERENCES_TOKEN, "")
                                .apply();

                        Intent intent = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra(Constant.TAB_POSTION, 3);
                        startActivity(intent);

                    }
                }).show();
    }
}
