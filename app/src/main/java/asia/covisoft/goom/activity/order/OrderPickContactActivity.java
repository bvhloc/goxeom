package asia.covisoft.goom.activity.order;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.helper.ContactProvider;
import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.Extras;

public class OrderPickContactActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtName, edtPhone;

    private void initView() {
        setContentView(R.layout.activity_order_pick_contact);

        findViewById(R.id.tvPickContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI),
                        Constant.REQUEST_CODE_PICK_CONTACTS);
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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
            Uri uriContact = data.getData();

            edtName.setText(new ContactProvider().getContactName(mContext, uriContact));
            edtPhone.setText(new ContactProvider().getContactNumber(mContext, uriContact));
            edtPhone.clearFocus();
        }
    }

    @Override
    public void onClick(View v) {

        Intent data = new Intent();
        data.putExtra(Extras.RECEIVED_CONTACT_NAME, edtName.getText().toString());
        data.putExtra(Extras.RECEIVED_CONTACT_PHONE, edtPhone.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }
}
