package asia.covisoft.goom.fragment.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import asia.covisoft.goom.R;
import asia.covisoft.goom.activity.order.OrderCourierActivity;
import asia.covisoft.goom.activity.order.OrderFoodActivity;
import asia.covisoft.goom.activity.order.OrderShoppingActivity;
import asia.covisoft.goom.activity.order.OrderTransportActivity;
import asia.covisoft.goom.activity.settings.SettingsLoginActivity;
import asia.covisoft.goom.activity.settings.SettingsSignupActivity;
import asia.covisoft.goom.backpress.BackFragment;
import asia.covisoft.goom.helper.TouchEffect;
import asia.covisoft.goom.utils.Preferences;

public class OrderFragment extends BackFragment {

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);

        mContext = getContext();

        SharedPreferences loginPreferences = mContext.getSharedPreferences(Preferences.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        final String token = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");
        ImageView imgvCourier = (ImageView) rootView.findViewById(R.id.imgvCourier);
        TouchEffect.addAlpha(imgvCourier);

        imgvCourier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (token.equals(""))
                    showDialogLogin();
                else
                    startActivity(new Intent(getActivity(), OrderCourierActivity.class));
            }
        });
        ImageView imgvTransport = (ImageView) rootView.findViewById(R.id.imgvTransport);
        TouchEffect.addAlpha(imgvTransport);
        imgvTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (token.equals(""))
                    showDialogLogin();
                else
                    startActivity(new Intent(getActivity(), OrderTransportActivity.class));
            }
        });
        ImageView imgvFood = (ImageView) rootView.findViewById(R.id.imgvFood);
        TouchEffect.addAlpha(imgvFood);
        imgvFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO add check and show dialog
                startActivity(new Intent(getActivity(), OrderFoodActivity.class));
            }
        });
        ImageView imgvShopping = (ImageView) rootView.findViewById(R.id.imgvShopping);
        TouchEffect.addAlpha(imgvShopping);
        imgvShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (token.equals(""))
                    showDialogLogin();
                else
                startActivity(new Intent(getActivity(), OrderShoppingActivity.class));
            }
        });

        return rootView;
    }

    private void showDialogLogin() {
        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_pleaseloginsignup))
                .setPositiveButton(getString(R.string.lowcase_login), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(mContext, SettingsLoginActivity.class));
                    }
                })
                .setNeutralButton(getString(R.string.lowcase_signup), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(mContext, SettingsSignupActivity.class));
                    }
                })
                .setNegativeButton(getString(R.string.lowcase_cancel), null)
                .show();
    }

}
