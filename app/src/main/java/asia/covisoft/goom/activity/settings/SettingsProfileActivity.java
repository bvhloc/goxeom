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

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.mvp.model.SettingsProfileModel;
import asia.covisoft.goom.mvp.presenter.SettingsProfilePresenter;
import asia.covisoft.goom.mvp.view.SettingsProfileView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Preferences;

public class SettingsProfileActivity extends BaseActivity implements SettingsProfileView {

    private TextView tvUsername;
    private EditText edtEmail, edtFullname, edtPhone, edtPassword, edtNewPassword, edtConfirmPassword;

    private void initView(){
        setContentView(R.layout.activity_settings_profile);

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtFullname = (EditText) findViewById(R.id.edtFullname);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtNewPassword = (EditText) findViewById(R.id.edtNewPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);

        findViewById(R.id.tvLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogLogout();
            }
        });
    }

    private Context mContext;
    private SettingsProfilePresenter presenter;
    private SharedPreferences loginPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        mContext = this;
        presenter = new SettingsProfilePresenter(this);

        loginPreferences = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE);
        String token = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_TOKEN, "");
        presenter.loadInfo(token);
    }

    @Override
    public void onInfoLoaded(SettingsProfileModel model) {

        tvUsername.setText(model.username);
        edtEmail.setText(model.email);
        edtFullname.setText(model.fullname);
        edtPhone.setText(model.phone);
    }

    @Override
    public void onConnectionFail() {

        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_connection_fail))
                .setNeutralButton(getString(R.string.lowcase_ok), null)
                .show();
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
