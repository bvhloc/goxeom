package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;

public interface OrderFoodPickRestaurantView {
    void onConnectionFail();
    void onRestaurantsLoaded(List<RestaurantList> restaurants);
}
