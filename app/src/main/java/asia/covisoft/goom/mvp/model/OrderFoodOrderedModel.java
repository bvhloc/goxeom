package asia.covisoft.goom.mvp.model;

import java.util.List;

import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;

public class OrderFoodOrderedModel {

    public String userToken;
    public String addressFrom;
    public double latFrom;
    public double lngFrom;
    public String addressTo;
    public String detailsTo;
    public double latTo;
    public double lngTo;
    public List<Foodlist> foods;
    public long cost;
    public long deliveryCost;
    public long foodCost;
}
