package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadshoppingRoot;

public interface OrderShoppingView extends OrderView{

    void onDriverReady(List<LoadshoppingRoot.Loadshopping> drivers);
}
