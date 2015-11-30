package asia.covisoft.goom.fragment.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.covisoft.goom.FragmentNavigator;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.RootFragment;

public class OrderFragment extends RootFragment {

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);

        rootView.findViewById(R.id.imgvCourier).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(OrderFragment.this, new OrderCourierFragment());
            }
        });
        rootView.findViewById(R.id.imgvTransport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(OrderFragment.this, new OrderTransportFragment());
            }
        });
        rootView.findViewById(R.id.imgvShopping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(OrderFragment.this, new OrderShoppingFragment());
            }
        });

        return rootView;
    }

}
