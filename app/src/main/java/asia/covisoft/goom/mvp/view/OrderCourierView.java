package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadcourierRoot.Loadcourier;

public interface OrderCourierView extends OrderView {

    void onDriverReady(List<Loadcourier> drivers);
}
