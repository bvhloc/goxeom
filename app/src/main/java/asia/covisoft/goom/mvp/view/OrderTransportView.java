package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadtransportRoot;

public interface OrderTransportView {

    void onConnectionFail();
    void onDriverReady(List<LoadtransportRoot.Loadtransport> drivers);
}
