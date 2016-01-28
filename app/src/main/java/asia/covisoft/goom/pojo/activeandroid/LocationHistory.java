package asia.covisoft.goom.pojo.activeandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "LocationHistory", id = "_id")
public class LocationHistory extends Model {

    public final static String COL_DATETIME = "COL_DATETIME";
    @Column(name = COL_DATETIME)
    private String datetime;

    public final static String COL_ADDRESS = "COL_ADDRESS";
    @Column(name = COL_ADDRESS)
    private String address;

    public final static String COL_LAT = "COL_LAT";
    @Column(name = COL_LAT)
    private String lat;

    public final static String COL_LNG = "COL_LNG";
    @Column(name = COL_LNG)
    private String lng;

    public final static String COL_DETAILS = "COL_DETAILS";
    @Column(name = COL_DETAILS)
    private String details;

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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<LocationHistory> getAll() {
        return new Select()
                .from(LocationHistory.class)
                .orderBy("_id desc")
                .execute();
    }
}