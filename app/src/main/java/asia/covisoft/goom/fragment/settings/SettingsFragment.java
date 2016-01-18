package asia.covisoft.goom.fragment.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import asia.covisoft.goom.R;
import asia.covisoft.goom.activity.settings.SettingsProfileActivity;
import asia.covisoft.goom.activity.settings.SettingsSignupActivity;
import asia.covisoft.goom.backpress.BackFragment;
import asia.covisoft.goom.utils.Preferences;


public class SettingsFragment extends BackFragment {


    private LinearLayout lnlSignup, lnlProfile, lnlTerms, lnlRate, lnlCall;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        lnlSignup = (LinearLayout) view.findViewById(R.id.lnlSignup);
        lnlProfile = (LinearLayout) view.findViewById(R.id.lnlProfile);
        lnlProfile.setVisibility(View.GONE);
        lnlTerms = (LinearLayout) view.findViewById(R.id.lnlTerms);
        lnlRate = (LinearLayout) view.findViewById(R.id.lnlRate);
        lnlCall = (LinearLayout) view.findViewById(R.id.lnlCall);

        lnlSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), SettingsSignupActivity.class));
            }
        });
        lnlProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), SettingsProfileActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences loginPreferences = getContext().getSharedPreferences(Preferences.LOGIN_PREFERENCES, Context.MODE_PRIVATE);
        String token = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");
        if (!token.equals("")) {
            lnlSignup.setVisibility(View.GONE);
            lnlProfile.setVisibility(View.VISIBLE);
        }
    }
}
