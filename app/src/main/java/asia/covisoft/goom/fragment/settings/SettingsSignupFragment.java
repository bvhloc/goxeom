package asia.covisoft.goom.fragment.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import asia.covisoft.goom.FragmentNavigator;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.BackFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsSignupFragment extends BackFragment {


    public SettingsSignupFragment() {
        // Required empty public constructor
    }

    private Button btnSignup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings_signup, container, false);

        btnSignup = (Button) rootView.findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(SettingsSignupFragment.this, new SettingsSignupVerifyFragment(), false);
            }
        });

        return rootView;
    }

}
