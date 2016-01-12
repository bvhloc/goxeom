package asia.covisoft.goom.mvp.view;

import asia.covisoft.goom.mvp.model.SettingsProfileModel;

public interface SettingsProfileView {

    void onConnectionFail();
    void onInfoLoaded(SettingsProfileModel model);
    void onInfoUpdate(Integer result);
}
