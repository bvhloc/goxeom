package asia.covisoft.goom.pojo;

/**
 * Created by Covisoft on 30/11/2015.
 */
public class RestaurantItem {

    private String name;
    private String address;
    private String imageUrl;

    public RestaurantItem(String name, String address, String imageUrl) {
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
