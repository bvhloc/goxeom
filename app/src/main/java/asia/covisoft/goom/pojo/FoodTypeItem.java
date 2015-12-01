package asia.covisoft.goom.pojo;

/**
 * Created by Covisoft on 01/12/2015.
 */
public class FoodTypeItem {

    private String name;
    private String imageUrl;

    public FoodTypeItem(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
