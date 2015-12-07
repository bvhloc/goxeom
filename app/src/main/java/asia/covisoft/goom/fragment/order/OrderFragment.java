package asia.covisoft.goom.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.covisoft.goom.R;
import asia.covisoft.goom.activity.order.OrderCourierActivity;
import asia.covisoft.goom.activity.order.OrderFoodActivity;
import asia.covisoft.goom.activity.order.OrderShoppingActivity;
import asia.covisoft.goom.activity.order.OrderTransportActivity;
import asia.covisoft.goom.backpress.BackFragment;

public class OrderFragment extends BackFragment {

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

                startActivity(new Intent(getActivity(), OrderCourierActivity.class));
            }
        });
        rootView.findViewById(R.id.imgvTransport).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), OrderTransportActivity.class));
            }
        });
        rootView.findViewById(R.id.imgvFood).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), OrderFoodActivity.class));
            }
        });
        rootView.findViewById(R.id.imgvShopping).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), OrderShoppingActivity.class));
            }
        });

        return rootView;
    }

}
