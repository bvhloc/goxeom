package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadshoppingRoot.Loadshopping;

public interface OrderShoppingView extends OrderView{

    void onDriverReady(List<Loadshopping> drivers);
}
