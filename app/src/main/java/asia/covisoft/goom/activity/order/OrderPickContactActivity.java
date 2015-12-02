package asia.covisoft.goom.activity.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import asia.covisoft.goom.ActivityAnim;
import asia.covisoft.goom.Constant;
import asia.covisoft.goom.ContactProvider;
import asia.covisoft.goom.R;
import asia.covisoft.goom.eventbus.ActivityResultEvent;

public class OrderPickContactActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pick_contact);
        mContext = this;
        initView();
    }

    private EditText edtName, edtPhone;

    private void initView(){

        findViewById(R.id.tvPickContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), Constant.REQUEST_CODE_PICK_CONTACTS);
            }
        });
        findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
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
    public void onBackPressed() {
        super.onBackPressed();
        ActivityAnim.back(mContext);
    }
}
