package asia.covisoft.goom.fragment.history;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import asia.covisoft.goom.R;
import asia.covisoft.goom.activity.settings.SettingsLoginActivity;
import asia.covisoft.goom.activity.settings.SettingsSignupActivity;
import asia.covisoft.goom.utils.Preferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    public HistoryFragment() {
        // Required empty public constructor
    }

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        rootView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, SettingsLoginActivity.class));
            }
        });
        rootView.findViewById(R.id.tvSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext, SettingsSignupActivity.class));
            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences loginPreferences = mContext.getSharedPreferences(Preferences.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String token = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");
        if(!token.equals("")){

            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.tab_container, new HistoryListFragment());
            transaction.commit();
        }
    }
}
