package asia.covisoft.goom.mvp.model;

import java.io.Serializable;

public class OrderShoppingModel implements Serializable {

    public String userToken;
    public String addressFrom;
    public String addressTo;
    public double latFrom;
    public double latTo;
    public double lngFrom;
    public double lngTo;
    public String detailsFrom;
    public String detailsTo;
    public String contactNameFrom;
    public String contactNameTo;
    public String contactPhoneFrom;
    public String contactPhoneTo;
    public String items;
    public String driverName;
    public String driverToken;
    public String cost;
}
