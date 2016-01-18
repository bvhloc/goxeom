package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadcourierRoot;

/**
 * Created by Covisoft on 16/12/2015.
 */
public interface OrderCourierView {

    void onDriverReady(List<LoadcourierRoot.Loadcourier> drivers);
    void onConnectionFail();
}
