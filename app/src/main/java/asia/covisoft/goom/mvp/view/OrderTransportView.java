package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadtransportRoot.Loadtransport;

public interface OrderTransportView extends OrderView {

    void onDriverReady(List<Loadtransport> drivers);
}
