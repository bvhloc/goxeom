package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadcourierRoot;


public interface OrderCourierView extends OrderView {

    void onDriverReady(List<LoadcourierRoot.Loadcourier> drivers);
}
