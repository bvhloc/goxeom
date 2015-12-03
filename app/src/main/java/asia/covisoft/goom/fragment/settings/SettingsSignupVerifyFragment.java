package asia.covisoft.goom.fragment.settings;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import asia.covisoft.goom.Constant;
import asia.covisoft.goom.MainActivity;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.BackFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsSignupVerifyFragment extends BackFragment {


    public SettingsSignupVerifyFragment() {
        // Required empty public constructor
    }

    private Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings_signup_verify, container, false);

        btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), MainActivity.class).putExtra(Constant.TAB_POSTION, 3));
                getActivity().finish();
            }
        });

        return rootView;

    }
}
