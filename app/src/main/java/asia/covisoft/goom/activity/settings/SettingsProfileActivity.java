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

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.Calendar;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.helper.DatetimeHelper;
import asia.covisoft.goom.helper.MD5;
import asia.covisoft.goom.helper.SystemHelper;
import asia.covisoft.goom.mvp.model.SettingsProfileModel;
import asia.covisoft.goom.mvp.presenter.SettingsProfilePresenter;
import asia.covisoft.goom.mvp.view.SettingsProfileView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.DatetimeFormat;
import asia.covisoft.goom.utils.Preferences;

@SuppressWarnings("FieldCanBeLocal")
public class SettingsProfileActivity extends BaseActivity implements SettingsProfileView {

    private TextView tvUsername, tvChangePassword;
    private EditText edtEmail, edtFullname, edtPhone, edtBirthday, edtCurrentPassword, edtNewPassword, edtConfirmPassword;
    private ExpandableRelativeLayout elChangePassword;

    private void initView() {
        setContentView(R.layout.activity_settings_profile);

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvChangePassword = (TextView) findViewById(R.id.tvChangePassword);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtFullname = (EditText) findViewById(R.id.edtFullname);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtBirthday = (EditText) findViewById(R.id.edtBirthday);
        edtCurrentPassword = (EditText) findViewById(R.id.edtCurrentPassword);
        edtNewPassword = (EditText) findViewById(R.id.edtNewPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        elChangePassword = (ExpandableRelativeLayout) findViewById(R.id.elChangePassword);

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
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                elChangePassword.toggle();
                if (!elChangePassword.isExpanded()) {
                    tvChangePassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_up_float, 0);
                } else {
                    tvChangePassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_down_float, 0);
                }
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
        if (!model.birthday.equals("0000-00-00")) {
            Calendar birthCal = new DatetimeHelper().getCalendar(model.birthday, DatetimeFormat.SERVER_DATE_FORMAT);
            String birthday = new DatetimeHelper().getString(birthCal, DatetimeFormat.APP_DATE_FORMAT);
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

                elChangePassword.collapse();
                tvChangePassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.arrow_down_float, 0);
                Toast.makeText(mContext, getString(R.string.toast_updatesuccess), Toast.LENGTH_LONG).show();

                loginPreferences.edit()
                        .putString(Preferences.LOGIN_PREFERENCES_PASSWORD, model.newPassword)
                        .apply();

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

        new SystemHelper().hideKeyboard(this);
        if (validInput()) {

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

        if(elChangePassword.isExpanded()){

            model.currentPassword = edtCurrentPassword.getText().toString();
            model.newPassword = edtNewPassword.getText().toString();
            model.confirmPassword = edtConfirmPassword.getText().toString();

            if (!new MD5().encrypt(model.currentPassword).equals(model.password)) {

                edtCurrentPassword.setError(getString(R.string.activity_settings_profile_error_current_password_wrong));
                edtCurrentPassword.requestFocus();
                return false;
            }
            if (model.currentPassword.isEmpty()) {

                edtCurrentPassword.setError(getString(R.string.activity_settings_profile_error_current_password_empty));
                edtCurrentPassword.requestFocus();
                return false;
            }
            if (model.newPassword.isEmpty()) {

                edtNewPassword.setError(getString(R.string.activity_settings_profile_error_new_password_empty));
                edtNewPassword.requestFocus();
                return false;
            }
            if (model.confirmPassword.isEmpty()) {

                edtConfirmPassword.setError(getString(R.string.activity_settings_profile_error_confirm_empty));
                edtConfirmPassword.requestFocus();
                return false;
            }
            if (!model.newPassword.equals(model.confirmPassword)) {
                edtConfirmPassword.setError(getString(R.string.activity_settings_signup_error_confirm));
                edtConfirmPassword.requestFocus();
                return false;
            }
        }else {
            model.newPassword = model.password;
        }

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
                                .putString(Preferences.LOGIN_PREFERENCES_USERNAME, "")
                                .putString(Preferences.LOGIN_PREFERENCES_PASSWORD, "")
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
