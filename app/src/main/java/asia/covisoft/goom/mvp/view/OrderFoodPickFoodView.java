package asia.covisoft.goom.mvp.view;

import android.view.View;

import java.util.HashMap;
import java.util.List;

import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;

public interface OrderFoodPickFoodView {

    void onConnectionFail();
    void initListHeader(View header);
    void onMenuLoaded(List<String> groups, HashMap<String, List<Foodlist>> childs);
}
