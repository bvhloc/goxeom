package asia.covisoft.goom.pojo;

/**
 * Created by Covisoft on 02/12/2015.
 */
public class FoodItem {

    private String name;
    private String price;

    public FoodItem(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
