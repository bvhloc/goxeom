package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RestaurantlistRoot {

    @SerializedName("restaurantlist")
    @Expose
    private List<LoadfoodingRoot.Loadfooding.RestaurantList> restaurantlist = new ArrayList<LoadfoodingRoot.Loadfooding.RestaurantList>();

    /**
     * @return The restaurantlist
     */
    public List<LoadfoodingRoot.Loadfooding.RestaurantList> getRestaurantlist() {
        return restaurantlist;
    }

    /**
     * @param restaurantlist The restaurantlist
     */
    public void setRestaurantlist(List<LoadfoodingRoot.Loadfooding.RestaurantList> restaurantlist) {
        this.restaurantlist = restaurantlist;
    }
}
