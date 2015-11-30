package asia.covisoft.goom.pojo;

/**
 * Created by Covisoft on 26/11/2015.
 */
public class LocationHistoryItem {

    private String name;
    private String address;

    public LocationHistoryItem(String name, String address) {
        this.name = name;
        this.address = address;
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
}
