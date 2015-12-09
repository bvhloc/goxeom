package asia.covisoft.goom.fragment.wallet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import asia.covisoft.goom.helper.FragmentNavigator;
import asia.covisoft.goom.R;
import asia.covisoft.goom.backpress.BackFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends BackFragment {


    public WalletFragment() {
        // Required empty public constructor
    }

    private Button btnToup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        btnToup = (Button) view.findViewById(R.id.btnTopup);
        btnToup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentNavigator.goTo(WalletFragment.this, new WalletTopupFragment());
            }
        });

        return view;
    }

}
