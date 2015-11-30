package asia.covisoft.goom.fragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import asia.covisoft.goom.FragmentNavigator;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.RootFragment;


public class SettingsFragment extends RootFragment {


    private LinearLayout lnlSignup, lnlTerms, lnlRate, lnlCall;

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
        lnlTerms = (LinearLayout) view.findViewById(R.id.lnlTerms);
        lnlRate = (LinearLayout) view.findViewById(R.id.lnlRate);
        lnlCall = (LinearLayout) view.findViewById(R.id.lnlCall);

        lnlSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(SettingsFragment.this, new SettingsSignupFragment());
            }
        });

        return view;
    }

}
