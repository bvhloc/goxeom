package asia.covisoft.goom.mvp.model;

import java.io.Serializable;

public class OrderTransportModel implements Serializable {

    public String userToken;
    public String addressFrom;
    public String addressTo;
    public double latFrom;
    public double latTo;
    public double lngFrom;
    public double lngTo;
    public String detailsFrom;
    public String driverName;
    public String driverToken;
    public String cost;
}
