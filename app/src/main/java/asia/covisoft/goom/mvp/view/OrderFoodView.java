package asia.covisoft.goom.mvp.view;

import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.Category;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;

public interface OrderFoodView {
    void onConnectionFail();
    void onCategoriesLoaded(List<Category> categories);
    void onRestaurantsLoaded(List<RestaurantList> restaurants);
}
