package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;

public class RestaurantlistRoot {

    @SerializedName("restaurantlist")
    @Expose
    private List<RestaurantList> restaurantlist = new ArrayList<RestaurantList>();

    /**
     * @return The restaurantlist
     */
    public List<RestaurantList> getRestaurantlist() {
        return restaurantlist;
    }

    /**
     * @param restaurantlist The restaurantlist
     */
    public void setRestaurantlist(List<RestaurantList> restaurantlist) {
        this.restaurantlist = restaurantlist;
    }

}
