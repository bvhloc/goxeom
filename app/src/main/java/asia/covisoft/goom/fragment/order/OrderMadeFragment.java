package asia.covisoft.goom.fragment.order;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.covisoft.goom.Constant;
import asia.covisoft.goom.MainActivity;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.RootFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderMadeFragment extends RootFragment {


    public OrderMadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_made, container, false);

        rootView.findViewById(R.id.btnCancelBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });
        rootView.findViewById(R.id.btnNewBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), MainActivity.class).putExtra(Constant.TAB_POSTION, 1));
                getActivity().finish();
            }
        });

        return rootView;
    }


}
