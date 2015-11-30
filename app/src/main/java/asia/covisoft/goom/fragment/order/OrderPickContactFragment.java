package asia.covisoft.goom.fragment.order;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import asia.covisoft.goom.Constant;
import asia.covisoft.goom.ContactProvider;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.RootFragment;
import asia.covisoft.goom.eventbus.ActivityResultEvent;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderPickContactFragment extends RootFragment {


    public OrderPickContactFragment() {
        // Required empty public constructor
    }

    private EditText edtName, edtPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_pick_contact, container, false);

        rootView.findViewById(R.id.tvPickContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), Constant.REQUEST_CODE_PICK_CONTACTS);
            }
        });
        rootView.findViewById(R.id.btnDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });
        edtName = (EditText) rootView.findViewById(R.id.edtName);
        edtPhone = (EditText) rootView.findViewById(R.id.edtPhone);

        //register to receive data from onActivityResult by EventBus
        EventBus.getDefault().register(this);

        return rootView;
    }

    //get data from Activity's onActivityResult
    @SuppressWarnings("unused")
    public void onEvent(ActivityResultEvent event) {
        Log.d("myDebug", "Message from MainActivity via EvenBus: request code = " + event.getRequestCode());
        if (event.getRequestCode() == Constant.REQUEST_CODE_PICK_CONTACTS && event.getResultCode() == Activity.RESULT_OK) {
            Uri uriContact = event.getData().getData();

            edtName.setText(new ContactProvider().getContactName(getActivity(), uriContact));
            edtPhone.setText(new ContactProvider().getContactNumber(getActivity(), uriContact));
            edtPhone.clearFocus();
        }
    }
}
