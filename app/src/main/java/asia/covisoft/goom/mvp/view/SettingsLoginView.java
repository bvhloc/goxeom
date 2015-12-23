package asia.covisoft.goom.mvp.view;

import asia.covisoft.goom.mvp.model.SettingsLoginModel;

/**
 * Created by Covisoft on 22/12/2015.
 */
public interface SettingsLoginView {

    void onConnectionFail();
    void onLogin(SettingsLoginModel model);
}
