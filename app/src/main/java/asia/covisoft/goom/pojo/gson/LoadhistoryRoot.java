package asia.covisoft.goom.pojo.gson;

/**
 * Created by Covisoft on 04/01/2016.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import asia.covisoft.goom.pojo.HistoryItem;

public class LoadhistoryRoot {

    @SerializedName("loadhistory")
    @Expose
    private Loadhistory loadhistory;

    /**
     * @return The loadhistory
     */
    public Loadhistory getLoadhistory() {
        return loadhistory;
    }

    /**
     * @param loadhistory The loadhistory
     */
    public void setLoadhistory(Loadhistory loadhistory) {
        this.loadhistory = loadhistory;
    }

    public class Loadhistory {

        @SerializedName("success")
        @Expose
        private List<Success> success = new ArrayList<Success>();
        @SerializedName("inprocess")
        @Expose
        private List<Inprocess> inprocess = new ArrayList<Inprocess>();
        @SerializedName("cancel")
        @Expose
        private List<Cancel> cancel = new ArrayList<Cancel>();
        @SerializedName("waiting")
        @Expose
        private List<Waiting> waiting = new ArrayList<Waiting>();

        /**
         *
         * @return
         * The success
         */
        public List<Success> getSuccess() {
            return success;
        }

        /**
         *
         * @param success
         * The success
         */
        public void setSuccess(List<Success> success) {
            this.success = success;
        }

        /**
         *
         * @return
         * The inprocess
         */
        public List<Inprocess> getInprocess() {
            return inprocess;
        }

        /**
         *
         * @param inprocess
         * The inprocess
         */
        public void setInprocess(List<Inprocess> inprocess) {
            this.inprocess = inprocess;
        }

        /**
         *
         * @return
         * The cancel
         */
        public List<Cancel> getCancel() {
            return cancel;
        }

        /**
         *
         * @param cancel
         * The cancel
         */
        public void setCancel(List<Cancel> cancel) {
            this.cancel = cancel;
        }

        /**
         *
         * @return
         * The waiting
         */
        public List<Waiting> getWaiting() {
            return waiting;
        }

        /**
         *
         * @param waiting
         * The waiting
         */
        public void setWaiting(List<Waiting> waiting) {
            this.waiting = waiting;
        }

        public class Cancel extends HistoryItem {

            @SerializedName("history_trading_id")
            @Expose
            private String historyTradingId;
            @SerializedName("trading_id")
            @Expose
            private String tradingId;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("trading_date")
            @Expose
            private String tradingDate;
            @SerializedName("trading_status")
            @Expose
            private String tradingStatus;
            @SerializedName("trading_location")
            @Expose
            private String tradingLocation;

            /**
             *
             * @return
             * The historyTradingId
             */
            public String getHistoryTradingId() {
                return historyTradingId;
            }

            /**
             *
             * @param historyTradingId
             * The history_trading_id
             */
            public void setHistoryTradingId(String historyTradingId) {
                this.historyTradingId = historyTradingId;
            }

            /**
             *
             * @return
             * The tradingId
             */
            public String getTradingId() {
                return tradingId;
            }

            /**
             *
             * @param tradingId
             * The trading_id
             */
            public void setTradingId(String tradingId) {
                this.tradingId = tradingId;
            }

            /**
             *
             * @return
             * The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             * The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             * The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             * The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             * The tradingDate
             */
            public String getTradingDate() {
                return tradingDate;
            }

            /**
             *
             * @param tradingDate
             * The trading_date
             */
            public void setTradingDate(String tradingDate) {
                this.tradingDate = tradingDate;
            }

            /**
             *
             * @return
             * The tradingStatus
             */
            public String getTradingStatus() {
                return tradingStatus;
            }

            /**
             *
             * @param tradingStatus
             * The trading_status
             */
            public void setTradingStatus(String tradingStatus) {
                this.tradingStatus = tradingStatus;
            }

            /**
             *
             * @return
             * The tradingLocation
             */
            public String getTradingLocation() {
                return tradingLocation;
            }

            /**
             *
             * @param tradingLocation
             * The trading_location
             */
            public void setTradingLocation(String tradingLocation) {
                this.tradingLocation = tradingLocation;
            }

        }

        public class Inprocess extends HistoryItem {

            @SerializedName("history_trading_id")
            @Expose
            private String historyTradingId;
            @SerializedName("trading_id")
            @Expose
            private String tradingId;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("trading_date")
            @Expose
            private String tradingDate;
            @SerializedName("trading_status")
            @Expose
            private String tradingStatus;
            @SerializedName("trading_location")
            @Expose
            private String tradingLocation;

            /**
             *
             * @return
             * The historyTradingId
             */
            public String getHistoryTradingId() {
                return historyTradingId;
            }

            /**
             *
             * @param historyTradingId
             * The history_trading_id
             */
            public void setHistoryTradingId(String historyTradingId) {
                this.historyTradingId = historyTradingId;
            }

            /**
             *
             * @return
             * The tradingId
             */
            public String getTradingId() {
                return tradingId;
            }

            /**
             *
             * @param tradingId
             * The trading_id
             */
            public void setTradingId(String tradingId) {
                this.tradingId = tradingId;
            }

            /**
             *
             * @return
             * The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             * The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             * The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             * The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             * The tradingDate
             */
            public String getTradingDate() {
                return tradingDate;
            }

            /**
             *
             * @param tradingDate
             * The trading_date
             */
            public void setTradingDate(String tradingDate) {
                this.tradingDate = tradingDate;
            }

            /**
             *
             * @return
             * The tradingStatus
             */
            public String getTradingStatus() {
                return tradingStatus;
            }

            /**
             *
             * @param tradingStatus
             * The trading_status
             */
            public void setTradingStatus(String tradingStatus) {
                this.tradingStatus = tradingStatus;
            }

            /**
             *
             * @return
             * The tradingLocation
             */
            public String getTradingLocation() {
                return tradingLocation;
            }

            /**
             *
             * @param tradingLocation
             * The trading_location
             */
            public void setTradingLocation(String tradingLocation) {
                this.tradingLocation = tradingLocation;
            }

        }

        public class Success extends HistoryItem {

            @SerializedName("history_trading_id")
            @Expose
            private String historyTradingId;
            @SerializedName("trading_id")
            @Expose
            private String tradingId;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("trading_date")
            @Expose
            private String tradingDate;
            @SerializedName("trading_status")
            @Expose
            private String tradingStatus;
            @SerializedName("trading_location")
            @Expose
            private String tradingLocation;

            /**
             *
             * @return
             * The historyTradingId
             */
            public String getHistoryTradingId() {
                return historyTradingId;
            }

            /**
             *
             * @param historyTradingId
             * The history_trading_id
             */
            public void setHistoryTradingId(String historyTradingId) {
                this.historyTradingId = historyTradingId;
            }

            /**
             *
             * @return
             * The tradingId
             */
            public String getTradingId() {
                return tradingId;
            }

            /**
             *
             * @param tradingId
             * The trading_id
             */
            public void setTradingId(String tradingId) {
                this.tradingId = tradingId;
            }

            /**
             *
             * @return
             * The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             * The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             * The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             * The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             * The tradingDate
             */
            public String getTradingDate() {
                return tradingDate;
            }

            /**
             *
             * @param tradingDate
             * The trading_date
             */
            public void setTradingDate(String tradingDate) {
                this.tradingDate = tradingDate;
            }

            /**
             *
             * @return
             * The tradingStatus
             */
            public String getTradingStatus() {
                return tradingStatus;
            }

            /**
             *
             * @param tradingStatus
             * The trading_status
             */
            public void setTradingStatus(String tradingStatus) {
                this.tradingStatus = tradingStatus;
            }

            /**
             *
             * @return
             * The tradingLocation
             */
            public String getTradingLocation() {
                return tradingLocation;
            }

            /**
             *
             * @param tradingLocation
             * The trading_location
             */
            public void setTradingLocation(String tradingLocation) {
                this.tradingLocation = tradingLocation;
            }

        }

        public class Waiting extends HistoryItem {

            @SerializedName("history_trading_id")
            @Expose
            private String historyTradingId;
            @SerializedName("trading_id")
            @Expose
            private String tradingId;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("trading_date")
            @Expose
            private String tradingDate;
            @SerializedName("trading_status")
            @Expose
            private String tradingStatus;
            @SerializedName("trading_location")
            @Expose
            private String tradingLocation;

            /**
             *
             * @return
             * The historyTradingId
             */
            public String getHistoryTradingId() {
                return historyTradingId;
            }

            /**
             *
             * @param historyTradingId
             * The history_trading_id
             */
            public void setHistoryTradingId(String historyTradingId) {
                this.historyTradingId = historyTradingId;
            }

            /**
             *
             * @return
             * The tradingId
             */
            public String getTradingId() {
                return tradingId;
            }

            /**
             *
             * @param tradingId
             * The trading_id
             */
            public void setTradingId(String tradingId) {
                this.tradingId = tradingId;
            }

            /**
             *
             * @return
             * The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             * The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             * The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             * The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             * The tradingDate
             */
            public String getTradingDate() {
                return tradingDate;
            }

            /**
             *
             * @param tradingDate
             * The trading_date
             */
            public void setTradingDate(String tradingDate) {
                this.tradingDate = tradingDate;
            }

            /**
             *
             * @return
             * The tradingStatus
             */
            public String getTradingStatus() {
                return tradingStatus;
            }

            /**
             *
             * @param tradingStatus
             * The trading_status
             */
            public void setTradingStatus(String tradingStatus) {
                this.tradingStatus = tradingStatus;
            }

            /**
             *
             * @return
             * The tradingLocation
             */
            public String getTradingLocation() {
                return tradingLocation;
            }

            /**
             *
             * @param tradingLocation
             * The trading_location
             */
            public void setTradingLocation(String tradingLocation) {
                this.tradingLocation = tradingLocation;
            }

        }
    }
}
