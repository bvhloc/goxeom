package asia.covisoft.goom.pojo;

/**
 * Created by Covisoft on 23/11/2015.
 */
public class HistoryItem {

    private String datetime;
    private String address;
    private boolean status;

    public HistoryItem(String datetime, String address, boolean status) {
        this.datetime = datetime;
        this.address = address;
        this.status = status;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
