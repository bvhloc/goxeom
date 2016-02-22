package asia.covisoft.goom.activity.order;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.utils.ContactProvider;
import asia.covisoft.goom.prefs.Extras;

@SuppressWarnings("FieldCanBeLocal")
public class OrderPickContactActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtName, edtPhone;

    private void initView() {
        setContentView(R.layout.activity_order_pick_contact);

        findViewById(R.id.lnlPickContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pickPhoneContact();
            }
        });
        findViewById(R.id.btnDone).setOnClickListener(this);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
    }

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initView();

        setupUI();
    }

    private final int PERMISSIONS_REQUEST_ACCESS_CONTACT = 1;
    private final int REQUEST_CODE_ACCESS_CONTACT = 0;

    private void pickPhoneContact(){

        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) mContext,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_ACCESS_CONTACT);
        } else {

            startActivityForResult(new Intent(Intent.ACTION_PICK,
                    ContactsContract.Contacts.CONTENT_URI),
                    REQUEST_CODE_ACCESS_CONTACT);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                //noinspection StatementWithEmptyBody
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //noinspection MissingPermission
                    startActivityForResult(new Intent(Intent.ACTION_PICK,
                                    ContactsContract.Contacts.CONTENT_URI),
                            0);
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
//                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void setupUI() {

        Bundle extras = getIntent().getExtras();
        String name = extras.getString(Extras.PICKED_CONTACT_NAME, "");
        String phone = extras.getString(Extras.PICKED_CONTACT_PHONE, "");
        edtName.setText(name);
        edtPhone.setText(phone);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri uriContact = data.getData();

            edtName.setText(new ContactProvider().getContactName(mContext, uriContact));
            edtPhone.setText(new ContactProvider().getContactNumber(mContext, uriContact));
            edtPhone.clearFocus();
        }
    }

    @Override
    public void onClick(View v) {

        if (validInput()) {

            Intent data = new Intent();
            data.putExtra(Extras.PICKED_CONTACT_NAME, edtName.getText().toString());
            data.putExtra(Extras.PICKED_CONTACT_PHONE, edtPhone.getText().toString());
            setResult(RESULT_OK, data);
            finish();
        }
    }

    private boolean validInput() {

        if (edtPhone.getText().toString().trim().isEmpty()) {
            edtPhone.setError(getString(R.string.activity_settings_signup_error_phone_empty));
            return false;
        }

        return true;
    }
}
