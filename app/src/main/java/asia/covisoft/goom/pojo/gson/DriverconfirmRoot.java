package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverconfirmRoot {

    @SerializedName("driverconfirm")
    @Expose
    private Driverconfirm driverconfirm;

    /**
     *
     * @return
     * The driverconfirm
     */
    public Driverconfirm getDriverconfirm() {
        return driverconfirm;
    }

    /**
     *
     * @param driverconfirm
     * The driverconfirm
     */
    public void setDriverconfirm(Driverconfirm driverconfirm) {
        this.driverconfirm = driverconfirm;
    }

    public class Driverconfirm {

        @SerializedName("minsuggest")
        @Expose
        private String minsuggest;
        @SerializedName("tradingid")
        @Expose
        private String tradingid;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("maxsuggest")
        @Expose
        private String maxsuggest;

        /**
         *
         * @return
         * The minsuggest
         */
        public String getMinsuggest() {
            return minsuggest;
        }

        /**
         *
         * @param minsuggest
         * The minsuggest
         */
        public void setMinsuggest(String minsuggest) {
            this.minsuggest = minsuggest;
        }

        /**
         *
         * @return
         * The tradingid
         */
        public String getTradingid() {
            return tradingid;
        }

        /**
         *
         * @param tradingid
         * The tradingid
         */
        public void setTradingid(String tradingid) {
            this.tradingid = tradingid;
        }

        /**
         *
         * @return
         * The value
         */
        public String getValue() {
            return value;
        }

        /**
         *
         * @param value
         * The value
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         *
         * @return
         * The maxsuggest
         */
        public String getMaxsuggest() {
            return maxsuggest;
        }

        /**
         *
         * @param maxsuggest
         * The maxsuggest
         */
        public void setMaxsuggest(String maxsuggest) {
            this.maxsuggest = maxsuggest;
        }

    }
}