package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.helper.ContactProvider;
import asia.covisoft.goom.utils.Extras;

public class OrderPickContactActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtName, edtPhone;

    private void initView() {
        setContentView(R.layout.activity_order_pick_contact);

        findViewById(R.id.tvPickContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
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
