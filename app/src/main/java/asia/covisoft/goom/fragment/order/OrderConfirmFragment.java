package asia.covisoft.goom.fragment.order;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.covisoft.goom.FragmentNavigator;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.RootFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderConfirmFragment extends RootFragment {


    public OrderConfirmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_confirm, container, false);

        rootView.findViewById(R.id.btnOrder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(OrderConfirmFragment.this, new OrderMadeFragment());
            }
        });

        return rootView;
    }


}
