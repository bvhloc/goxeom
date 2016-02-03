
package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoaddetailhistoryRoot {

    @SerializedName("loaddetailhistory")
    @Expose
    private Loaddetailhistory loaddetailhistory;

    /**
     * 
     * @return
     *     The loaddetailhistory
     */
    public Loaddetailhistory getLoaddetailhistory() {
        return loaddetailhistory;
    }

    /**
     * 
     * @param loaddetailhistory
     *     The loaddetailhistory
     */
    public void setLoaddetailhistory(Loaddetailhistory loaddetailhistory) {
        this.loaddetailhistory = loaddetailhistory;
    }

    public class Loaddetailhistory {

        @SerializedName("detailhistorycourier")
        @Expose
        private Detailhistorycourier detailhistorycourier;
        @SerializedName("detailhistorytransport")
        @Expose
        private Detailhistorytransport detailhistorytransport;
        @SerializedName("detailhistoryshopping")
        @Expose
        private Detailhistoryshopping detailhistoryshopping;
        @SerializedName("detailhistoryfooding")
        @Expose
        private Detailhistoryfooding detailhistoryfooding;
        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("driver")
        @Expose
        private Driver driver;
        @SerializedName("foodlist")
        @Expose
        private List<Foodlist> foodlist = new ArrayList<Foodlist>();

        /**
         *
         * @return
         *     The detailhistorycourier
         */
        public Detailhistorycourier getDetailhistorycourier() {
            return detailhistorycourier;
        }

        /**
         *
         * @param detailhistorycourier
         *     The detailhistorycourier
         */
        public void setDetailhistorycourier(Detailhistorycourier detailhistorycourier) {
            this.detailhistorycourier = detailhistorycourier;
        }

        /**
         *
         * @return
         *     The detailhistorytransport
         */
        public Detailhistorytransport getDetailhistorytransport() {
            return detailhistorytransport;
        }

        /**
         *
         * @param detailhistorytransport
         *     The detailhistorytransport
         */
        public void setDetailhistorytransport(Detailhistorytransport detailhistorytransport) {
            this.detailhistorytransport = detailhistorytransport;
        }

        /**
         *
         * @return
         *     The detailhistoryshopping
         */
        public Detailhistoryshopping getDetailhistoryshopping() {
            return detailhistoryshopping;
        }

        /**
         *
         * @param detailhistoryshopping
         *     The detailhistoryshopping
         */
        public void setDetailhistoryshopping(Detailhistoryshopping detailhistoryshopping) {
            this.detailhistoryshopping = detailhistoryshopping;
        }

        /**
         *
         * @return
         *     The detailhistoryfooding
         */
        public Detailhistoryfooding getDetailhistoryfooding() {
            return detailhistoryfooding;
        }

        /**
         *
         * @param detailhistoryfooding
         *     The detailhistoryfooding
         */
        public void setDetailhistoryfooding(Detailhistoryfooding detailhistoryfooding) {
            this.detailhistoryfooding = detailhistoryfooding;
        }

        /**
         *
         * @return
         *     The user
         */
        public User getUser() {
            return user;
        }

        /**
         *
         * @param user
         *     The user
         */
        public void setUser(User user) {
            this.user = user;
        }

        /**
         *
         * @return
         *     The driver
         */
        public Driver getDriver() {
            return driver;
        }

        /**
         *
         * @param driver
         *     The driver
         */
        public void setDriver(Driver driver) {
            this.driver = driver;
        }

        /**
         *
         * @return
         *     The foodlist
         */
        public List<Foodlist> getFoodlist() {
            return foodlist;
        }

        /**
         *
         * @param foodlist
         *     The foodlist
         */
        public void setFoodlist(List<Foodlist> foodlist) {
            this.foodlist = foodlist;
        }

        public class User {

            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("user_name")
            @Expose
            private String userName;
            @SerializedName("full_name")
            @Expose
            private String fullName;
            @SerializedName("birth_day")
            @Expose
            private String birthDay;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("pass_word")
            @Expose
            private String passWord;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("phone_number")
            @Expose
            private String phoneNumber;
            @SerializedName("avatar_image")
            @Expose
            private String avatarImage;
            @SerializedName("token")
            @Expose
            private String token;
            @SerializedName("login_fault")
            @Expose
            private String loginFault;
            @SerializedName("user_registration")
            @Expose
            private String userRegistration;

            /**
             *
             * @return
             *     The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             *     The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             *     The userName
             */
            public String getUserName() {
                return userName;
            }

            /**
             *
             * @param userName
             *     The user_name
             */
            public void setUserName(String userName) {
                this.userName = userName;
            }

            /**
             *
             * @return
             *     The fullName
             */
            public String getFullName() {
                return fullName;
            }

            /**
             *
             * @param fullName
             *     The full_name
             */
            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            /**
             *
             * @return
             *     The birthDay
             */
            public String getBirthDay() {
                return birthDay;
            }

            /**
             *
             * @param birthDay
             *     The birth_day
             */
            public void setBirthDay(String birthDay) {
                this.birthDay = birthDay;
            }

            /**
             *
             * @return
             *     The email
             */
            public String getEmail() {
                return email;
            }

            /**
             *
             * @param email
             *     The email
             */
            public void setEmail(String email) {
                this.email = email;
            }

            /**
             *
             * @return
             *     The passWord
             */
            public String getPassWord() {
                return passWord;
            }

            /**
             *
             * @param passWord
             *     The pass_word
             */
            public void setPassWord(String passWord) {
                this.passWord = passWord;
            }

            /**
             *
             * @return
             *     The status
             */
            public String getStatus() {
                return status;
            }

            /**
             *
             * @param status
             *     The status
             */
            public void setStatus(String status) {
                this.status = status;
            }

            /**
             *
             * @return
             *     The phoneNumber
             */
            public String getPhoneNumber() {
                return phoneNumber;
            }

            /**
             *
             * @param phoneNumber
             *     The phone_number
             */
            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            /**
             *
             * @return
             *     The avatarImage
             */
            public String getAvatarImage() {
                return avatarImage;
            }

            /**
             *
             * @param avatarImage
             *     The avatar_image
             */
            public void setAvatarImage(String avatarImage) {
                this.avatarImage = avatarImage;
            }

            /**
             *
             * @return
             *     The token
             */
            public String getToken() {
                return token;
            }

            /**
             *
             * @param token
             *     The token
             */
            public void setToken(String token) {
                this.token = token;
            }

            /**
             *
             * @return
             *     The loginFault
             */
            public String getLoginFault() {
                return loginFault;
            }

            /**
             *
             * @param loginFault
             *     The login_fault
             */
            public void setLoginFault(String loginFault) {
                this.loginFault = loginFault;
            }

            /**
             *
             * @return
             *     The userRegistration
             */
            public String getUserRegistration() {
                return userRegistration;
            }

            /**
             *
             * @param userRegistration
             *     The user_registration
             */
            public void setUserRegistration(String userRegistration) {
                this.userRegistration = userRegistration;
            }

        }

        public class Driver {

            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("driver_name")
            @Expose
            private String driverName;
            @SerializedName("full_name")
            @Expose
            private String fullName;
            @SerializedName("birth_day")
            @Expose
            private String birthDay;
            @SerializedName("pass_word")
            @Expose
            private String passWord;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("gender")
            @Expose
            private String gender;
            @SerializedName("rating")
            @Expose
            private String rating;
            @SerializedName("view")
            @Expose
            private String view;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("phone_number")
            @Expose
            private String phoneNumber;
            @SerializedName("avatar_image")
            @Expose
            private String avatarImage;
            @SerializedName("latitude")
            @Expose
            private String latitude;
            @SerializedName("longitude")
            @Expose
            private String longitude;
            @SerializedName("token")
            @Expose
            private String token;
            @SerializedName("login_fault")
            @Expose
            private String loginFault;
            @SerializedName("driver_registration")
            @Expose
            private String driverRegistration;

            /**
             *
             * @return
             *     The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             *     The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             *     The driverName
             */
            public String getDriverName() {
                return driverName;
            }

            /**
             *
             * @param driverName
             *     The driver_name
             */
            public void setDriverName(String driverName) {
                this.driverName = driverName;
            }

            /**
             *
             * @return
             *     The fullName
             */
            public String getFullName() {
                return fullName;
            }

            /**
             *
             * @param fullName
             *     The full_name
             */
            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            /**
             *
             * @return
             *     The birthDay
             */
            public String getBirthDay() {
                return birthDay;
            }

            /**
             *
             * @param birthDay
             *     The birth_day
             */
            public void setBirthDay(String birthDay) {
                this.birthDay = birthDay;
            }

            /**
             *
             * @return
             *     The passWord
             */
            public String getPassWord() {
                return passWord;
            }

            /**
             *
             * @param passWord
             *     The pass_word
             */
            public void setPassWord(String passWord) {
                this.passWord = passWord;
            }

            /**
             *
             * @return
             *     The status
             */
            public String getStatus() {
                return status;
            }

            /**
             *
             * @param status
             *     The status
             */
            public void setStatus(String status) {
                this.status = status;
            }

            /**
             *
             * @return
             *     The gender
             */
            public String getGender() {
                return gender;
            }

            /**
             *
             * @param gender
             *     The gender
             */
            public void setGender(String gender) {
                this.gender = gender;
            }

            /**
             *
             * @return
             *     The rating
             */
            public String getRating() {
                return rating;
            }

            /**
             *
             * @param rating
             *     The rating
             */
            public void setRating(String rating) {
                this.rating = rating;
            }

            /**
             *
             * @return
             *     The view
             */
            public String getView() {
                return view;
            }

            /**
             *
             * @param view
             *     The view
             */
            public void setView(String view) {
                this.view = view;
            }

            /**
             *
             * @return
             *     The email
             */
            public String getEmail() {
                return email;
            }

            /**
             *
             * @param email
             *     The email
             */
            public void setEmail(String email) {
                this.email = email;
            }

            /**
             *
             * @return
             *     The phoneNumber
             */
            public String getPhoneNumber() {
                return phoneNumber;
            }

            /**
             *
             * @param phoneNumber
             *     The phone_number
             */
            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            /**
             *
             * @return
             *     The avatarImage
             */
            public String getAvatarImage() {
                return avatarImage;
            }

            /**
             *
             * @param avatarImage
             *     The avatar_image
             */
            public void setAvatarImage(String avatarImage) {
                this.avatarImage = avatarImage;
            }

            /**
             *
             * @return
             *     The latitude
             */
            public String getLatitude() {
                return latitude;
            }

            /**
             *
             * @param latitude
             *     The latitude
             */
            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            /**
             *
             * @return
             *     The longitude
             */
            public String getLongitude() {
                return longitude;
            }

            /**
             *
             * @param longitude
             *     The longitude
             */
            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            /**
             *
             * @return
             *     The token
             */
            public String getToken() {
                return token;
            }

            /**
             *
             * @param token
             *     The token
             */
            public void setToken(String token) {
                this.token = token;
            }

            /**
             *
             * @return
             *     The loginFault
             */
            public String getLoginFault() {
                return loginFault;
            }

            /**
             *
             * @param loginFault
             *     The login_fault
             */
            public void setLoginFault(String loginFault) {
                this.loginFault = loginFault;
            }

            /**
             *
             * @return
             *     The driverRegistration
             */
            public String getDriverRegistration() {
                return driverRegistration;
            }

            /**
             *
             * @param driverRegistration
             *     The driver_registration
             */
            public void setDriverRegistration(String driverRegistration) {
                this.driverRegistration = driverRegistration;
            }

        }

        public class Detailhistorycourier {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("courier_id")
            @Expose
            private String courierId;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("contact_name_from")
            @Expose
            private String contactNameFrom;
            @SerializedName("contact_phone_from")
            @Expose
            private String contactPhoneFrom;
            @SerializedName("contact_name_to")
            @Expose
            private String contactNameTo;
            @SerializedName("contact_phone_to")
            @Expose
            private String contactPhoneTo;
            @SerializedName("courier_from")
            @Expose
            private String courierFrom;
            @SerializedName("courier_from_detail")
            @Expose
            private String courierFromDetail;
            @SerializedName("courier_from_lat")
            @Expose
            private String courierFromLat;
            @SerializedName("courier_from_long")
            @Expose
            private String courierFromLong;
            @SerializedName("courier_to")
            @Expose
            private String courierTo;
            @SerializedName("courier_to_detail")
            @Expose
            private String courierToDetail;
            @SerializedName("courier_to_lat")
            @Expose
            private String courierToLat;
            @SerializedName("courier_to_long")
            @Expose
            private String courierToLong;
            @SerializedName("courier_date")
            @Expose
            private String courierDate;
            @SerializedName("courier_item")
            @Expose
            private String courierItem;
            @SerializedName("courier_cost")
            @Expose
            private String courierCost;
            @SerializedName("courier_status")
            @Expose
            private String courierStatus;
            @SerializedName("courier_reason")
            @Expose
            private String courierReason;

            /**
             *
             * @return
             *     The id
             */
            public String getId() {
                return id;
            }

            /**
             *
             * @param id
             *     The id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             *
             * @return
             *     The courierId
             */
            public String getCourierId() {
                return courierId;
            }

            /**
             *
             * @param courierId
             *     The courier_id
             */
            public void setCourierId(String courierId) {
                this.courierId = courierId;
            }

            /**
             *
             * @return
             *     The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             *     The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             *     The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             *     The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             *     The contactNameFrom
             */
            public String getContactNameFrom() {
                return contactNameFrom;
            }

            /**
             *
             * @param contactNameFrom
             *     The contact_name_from
             */
            public void setContactNameFrom(String contactNameFrom) {
                this.contactNameFrom = contactNameFrom;
            }

            /**
             *
             * @return
             *     The contactPhoneFrom
             */
            public String getContactPhoneFrom() {
                return contactPhoneFrom;
            }

            /**
             *
             * @param contactPhoneFrom
             *     The contact_phone_from
             */
            public void setContactPhoneFrom(String contactPhoneFrom) {
                this.contactPhoneFrom = contactPhoneFrom;
            }

            /**
             *
             * @return
             *     The contactNameTo
             */
            public String getContactNameTo() {
                return contactNameTo;
            }

            /**
             *
             * @param contactNameTo
             *     The contact_name_to
             */
            public void setContactNameTo(String contactNameTo) {
                this.contactNameTo = contactNameTo;
            }

            /**
             *
             * @return
             *     The contactPhoneTo
             */
            public String getContactPhoneTo() {
                return contactPhoneTo;
            }

            /**
             *
             * @param contactPhoneTo
             *     The contact_phone_to
             */
            public void setContactPhoneTo(String contactPhoneTo) {
                this.contactPhoneTo = contactPhoneTo;
            }

            /**
             *
             * @return
             *     The courierFrom
             */
            public String getCourierFrom() {
                return courierFrom;
            }

            /**
             *
             * @param courierFrom
             *     The courier_from
             */
            public void setCourierFrom(String courierFrom) {
                this.courierFrom = courierFrom;
            }

            /**
             *
             * @return
             *     The courierFromDetail
             */
            public String getCourierFromDetail() {
                return courierFromDetail;
            }

            /**
             *
             * @param courierFromDetail
             *     The courier_from_detail
             */
            public void setCourierFromDetail(String courierFromDetail) {
                this.courierFromDetail = courierFromDetail;
            }

            /**
             *
             * @return
             *     The courierFromLat
             */
            public String getCourierFromLat() {
                return courierFromLat;
            }

            /**
             *
             * @param courierFromLat
             *     The courier_from_lat
             */
            public void setCourierFromLat(String courierFromLat) {
                this.courierFromLat = courierFromLat;
            }

            /**
             *
             * @return
             *     The courierFromLong
             */
            public String getCourierFromLong() {
                return courierFromLong;
            }

            /**
             *
             * @param courierFromLong
             *     The courier_from_long
             */
            public void setCourierFromLong(String courierFromLong) {
                this.courierFromLong = courierFromLong;
            }

            /**
             *
             * @return
             *     The courierTo
             */
            public String getCourierTo() {
                return courierTo;
            }

            /**
             *
             * @param courierTo
             *     The courier_to
             */
            public void setCourierTo(String courierTo) {
                this.courierTo = courierTo;
            }

            /**
             *
             * @return
             *     The courierToDetail
             */
            public String getCourierToDetail() {
                return courierToDetail;
            }

            /**
             *
             * @param courierToDetail
             *     The courier_to_detail
             */
            public void setCourierToDetail(String courierToDetail) {
                this.courierToDetail = courierToDetail;
            }

            /**
             *
             * @return
             *     The courierToLat
             */
            public String getCourierToLat() {
                return courierToLat;
            }

            /**
             *
             * @param courierToLat
             *     The courier_to_lat
             */
            public void setCourierToLat(String courierToLat) {
                this.courierToLat = courierToLat;
            }

            /**
             *
             * @return
             *     The courierToLong
             */
            public String getCourierToLong() {
                return courierToLong;
            }

            /**
             *
             * @param courierToLong
             *     The courier_to_long
             */
            public void setCourierToLong(String courierToLong) {
                this.courierToLong = courierToLong;
            }

            /**
             *
             * @return
             *     The courierDate
             */
            public String getCourierDate() {
                return courierDate;
            }

            /**
             *
             * @param courierDate
             *     The courier_date
             */
            public void setCourierDate(String courierDate) {
                this.courierDate = courierDate;
            }

            /**
             *
             * @return
             *     The courierItem
             */
            public String getCourierItem() {
                return courierItem;
            }

            /**
             *
             * @param courierItem
             *     The courier_item
             */
            public void setCourierItem(String courierItem) {
                this.courierItem = courierItem;
            }

            /**
             *
             * @return
             *     The courierCost
             */
            public String getCourierCost() {
                return courierCost;
            }

            /**
             *
             * @param courierCost
             *     The courier_cost
             */
            public void setCourierCost(String courierCost) {
                this.courierCost = courierCost;
            }

            /**
             *
             * @return
             *     The courierStatus
             */
            public String getCourierStatus() {
                return courierStatus;
            }

            /**
             *
             * @param courierStatus
             *     The courier_status
             */
            public void setCourierStatus(String courierStatus) {
                this.courierStatus = courierStatus;
            }

            /**
             *
             * @return
             *     The courierReason
             */
            public String getCourierReason() {
                return courierReason;
            }

            /**
             *
             * @param courierReason
             *     The courier_reason
             */
            public void setCourierReason(String courierReason) {
                this.courierReason = courierReason;
            }

        }

        public class Detailhistorytransport {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("transport_id")
            @Expose
            private String transportId;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("transport_from")
            @Expose
            private String transportFrom;
            @SerializedName("transport_from_detail")
            @Expose
            private String transportFromDetail;
            @SerializedName("transport_from_lat")
            @Expose
            private String transportFromLat;
            @SerializedName("transport_from_long")
            @Expose
            private String transportFromLong;
            @SerializedName("transport_to")
            @Expose
            private String transportTo;
            @SerializedName("transport_to_lat")
            @Expose
            private String transportToLat;
            @SerializedName("transport_to_long")
            @Expose
            private String transportToLong;
            @SerializedName("transport_date")
            @Expose
            private String transportDate;
            @SerializedName("transport_cost")
            @Expose
            private String transportCost;
            @SerializedName("transport_status")
            @Expose
            private String transportStatus;
            @SerializedName("transport_reson")
            @Expose
            private String transportReson;

            /**
             *
             * @return
             *     The id
             */
            public String getId() {
                return id;
            }

            /**
             *
             * @param id
             *     The id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             *
             * @return
             *     The transportId
             */
            public String getTransportId() {
                return transportId;
            }

            /**
             *
             * @param transportId
             *     The transport_id
             */
            public void setTransportId(String transportId) {
                this.transportId = transportId;
            }

            /**
             *
             * @return
             *     The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             *     The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             *     The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             *     The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             *     The transportFrom
             */
            public String getTransportFrom() {
                return transportFrom;
            }

            /**
             *
             * @param transportFrom
             *     The transport_from
             */
            public void setTransportFrom(String transportFrom) {
                this.transportFrom = transportFrom;
            }

            /**
             *
             * @return
             *     The transportFromDetail
             */
            public String getTransportFromDetail() {
                return transportFromDetail;
            }

            /**
             *
             * @param transportFromDetail
             *     The transport_from_detail
             */
            public void setTransportFromDetail(String transportFromDetail) {
                this.transportFromDetail = transportFromDetail;
            }

            /**
             *
             * @return
             *     The transportFromLat
             */
            public String getTransportFromLat() {
                return transportFromLat;
            }

            /**
             *
             * @param transportFromLat
             *     The transport_from_lat
             */
            public void setTransportFromLat(String transportFromLat) {
                this.transportFromLat = transportFromLat;
            }

            /**
             *
             * @return
             *     The transportFromLong
             */
            public String getTransportFromLong() {
                return transportFromLong;
            }

            /**
             *
             * @param transportFromLong
             *     The transport_from_long
             */
            public void setTransportFromLong(String transportFromLong) {
                this.transportFromLong = transportFromLong;
            }

            /**
             *
             * @return
             *     The transportTo
             */
            public String getTransportTo() {
                return transportTo;
            }

            /**
             *
             * @param transportTo
             *     The transport_to
             */
            public void setTransportTo(String transportTo) {
                this.transportTo = transportTo;
            }

            /**
             *
             * @return
             *     The transportToLat
             */
            public String getTransportToLat() {
                return transportToLat;
            }

            /**
             *
             * @param transportToLat
             *     The transport_to_lat
             */
            public void setTransportToLat(String transportToLat) {
                this.transportToLat = transportToLat;
            }

            /**
             *
             * @return
             *     The transportToLong
             */
            public String getTransportToLong() {
                return transportToLong;
            }

            /**
             *
             * @param transportToLong
             *     The transport_to_long
             */
            public void setTransportToLong(String transportToLong) {
                this.transportToLong = transportToLong;
            }

            /**
             *
             * @return
             *     The transportDate
             */
            public String getTransportDate() {
                return transportDate;
            }

            /**
             *
             * @param transportDate
             *     The transport_date
             */
            public void setTransportDate(String transportDate) {
                this.transportDate = transportDate;
            }

            /**
             *
             * @return
             *     The transportCost
             */
            public String getTransportCost() {
                return transportCost;
            }

            /**
             *
             * @param transportCost
             *     The transport_cost
             */
            public void setTransportCost(String transportCost) {
                this.transportCost = transportCost;
            }

            /**
             *
             * @return
             *     The transportStatus
             */
            public String getTransportStatus() {
                return transportStatus;
            }

            /**
             *
             * @param transportStatus
             *     The transport_status
             */
            public void setTransportStatus(String transportStatus) {
                this.transportStatus = transportStatus;
            }

            /**
             *
             * @return
             *     The transportReson
             */
            public String getTransportReson() {
                return transportReson;
            }

            /**
             *
             * @param transportReson
             *     The transport_reson
             */
            public void setTransportReson(String transportReson) {
                this.transportReson = transportReson;
            }

        }

        public class Detailhistoryshopping {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("shopping_id")
            @Expose
            private String shoppingId;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("shopping_from")
            @Expose
            private String shoppingFrom;
            @SerializedName("shopping_from_detail")
            @Expose
            private String shoppingFromDetail;
            @SerializedName("shopping_from_lat")
            @Expose
            private String shoppingFromLat;
            @SerializedName("shopping_from_long")
            @Expose
            private String shoppingFromLong;
            @SerializedName("shopping_to")
            @Expose
            private String shoppingTo;
            @SerializedName("shopping_to_detail")
            @Expose
            private String shoppingToDetail;
            @SerializedName("shopping_to_lat")
            @Expose
            private String shoppingToLat;
            @SerializedName("shopping_to_long")
            @Expose
            private String shoppingToLong;
            @SerializedName("shopping_time")
            @Expose
            private String shoppingTime;
            @SerializedName("shopping_type")
            @Expose
            private String shoppingType;
            @SerializedName("shopping_item")
            @Expose
            private String shoppingItem;
            @SerializedName("shopping_cost")
            @Expose
            private String shoppingCost;
            @SerializedName("shopping_status")
            @Expose
            private String shoppingStatus;
            @SerializedName("shopping_reason")
            @Expose
            private String shoppingReason;

            /**
             *
             * @return
             *     The id
             */
            public String getId() {
                return id;
            }

            /**
             *
             * @param id
             *     The id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             *
             * @return
             *     The shoppingId
             */
            public String getShoppingId() {
                return shoppingId;
            }

            /**
             *
             * @param shoppingId
             *     The shopping_id
             */
            public void setShoppingId(String shoppingId) {
                this.shoppingId = shoppingId;
            }

            /**
             *
             * @return
             *     The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             *     The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             *     The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             *     The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             *     The shoppingFrom
             */
            public String getShoppingFrom() {
                return shoppingFrom;
            }

            /**
             *
             * @param shoppingFrom
             *     The shopping_from
             */
            public void setShoppingFrom(String shoppingFrom) {
                this.shoppingFrom = shoppingFrom;
            }

            /**
             *
             * @return
             *     The shoppingFromDetail
             */
            public String getShoppingFromDetail() {
                return shoppingFromDetail;
            }

            /**
             *
             * @param shoppingFromDetail
             *     The shopping_from_detail
             */
            public void setShoppingFromDetail(String shoppingFromDetail) {
                this.shoppingFromDetail = shoppingFromDetail;
            }

            /**
             *
             * @return
             *     The shoppingFromLat
             */
            public String getShoppingFromLat() {
                return shoppingFromLat;
            }

            /**
             *
             * @param shoppingFromLat
             *     The shopping_from_lat
             */
            public void setShoppingFromLat(String shoppingFromLat) {
                this.shoppingFromLat = shoppingFromLat;
            }

            /**
             *
             * @return
             *     The shoppingFromLong
             */
            public String getShoppingFromLong() {
                return shoppingFromLong;
            }

            /**
             *
             * @param shoppingFromLong
             *     The shopping_from_long
             */
            public void setShoppingFromLong(String shoppingFromLong) {
                this.shoppingFromLong = shoppingFromLong;
            }

            /**
             *
             * @return
             *     The shoppingTo
             */
            public String getShoppingTo() {
                return shoppingTo;
            }

            /**
             *
             * @param shoppingTo
             *     The shopping_to
             */
            public void setShoppingTo(String shoppingTo) {
                this.shoppingTo = shoppingTo;
            }

            /**
             *
             * @return
             *     The shoppingToDetail
             */
            public String getShoppingToDetail() {
                return shoppingToDetail;
            }

            /**
             *
             * @param shoppingToDetail
             *     The shopping_to_detail
             */
            public void setShoppingToDetail(String shoppingToDetail) {
                this.shoppingToDetail = shoppingToDetail;
            }

            /**
             *
             * @return
             *     The shoppingToLat
             */
            public String getShoppingToLat() {
                return shoppingToLat;
            }

            /**
             *
             * @param shoppingToLat
             *     The shopping_to_lat
             */
            public void setShoppingToLat(String shoppingToLat) {
                this.shoppingToLat = shoppingToLat;
            }

            /**
             *
             * @return
             *     The shoppingToLong
             */
            public String getShoppingToLong() {
                return shoppingToLong;
            }

            /**
             *
             * @param shoppingToLong
             *     The shopping_to_long
             */
            public void setShoppingToLong(String shoppingToLong) {
                this.shoppingToLong = shoppingToLong;
            }

            /**
             *
             * @return
             *     The shoppingTime
             */
            public String getShoppingTime() {
                return shoppingTime;
            }

            /**
             *
             * @param shoppingTime
             *     The shopping_time
             */
            public void setShoppingTime(String shoppingTime) {
                this.shoppingTime = shoppingTime;
            }

            /**
             *
             * @return
             *     The shoppingType
             */
            public String getShoppingType() {
                return shoppingType;
            }

            /**
             *
             * @param shoppingType
             *     The shopping_type
             */
            public void setShoppingType(String shoppingType) {
                this.shoppingType = shoppingType;
            }

            /**
             *
             * @return
             *     The shoppingItem
             */
            public String getShoppingItem() {
                return shoppingItem;
            }

            /**
             *
             * @param shoppingItem
             *     The shopping_item
             */
            public void setShoppingItem(String shoppingItem) {
                this.shoppingItem = shoppingItem;
            }

            /**
             *
             * @return
             *     The shoppingCost
             */
            public String getShoppingCost() {
                return shoppingCost;
            }

            /**
             *
             * @param shoppingCost
             *     The shopping_cost
             */
            public void setShoppingCost(String shoppingCost) {
                this.shoppingCost = shoppingCost;
            }

            /**
             *
             * @return
             *     The shoppingStatus
             */
            public String getShoppingStatus() {
                return shoppingStatus;
            }

            /**
             *
             * @param shoppingStatus
             *     The shopping_status
             */
            public void setShoppingStatus(String shoppingStatus) {
                this.shoppingStatus = shoppingStatus;
            }

            /**
             *
             * @return
             *     The shoppingReason
             */
            public String getShoppingReason() {
                return shoppingReason;
            }

            /**
             *
             * @param shoppingReason
             *     The shopping_reason
             */
            public void setShoppingReason(String shoppingReason) {
                this.shoppingReason = shoppingReason;
            }

        }

        public class Detailhistoryfooding {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("fooding_id")
            @Expose
            private String foodingId;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("driver_id")
            @Expose
            private String driverId;
            @SerializedName("fooding_from")
            @Expose
            private String foodingFrom;
            @SerializedName("fooding_from_detail")
            @Expose
            private String foodingFromDetail;
            @SerializedName("fooding_from_lat")
            @Expose
            private String foodingFromLat;
            @SerializedName("fooding_from_long")
            @Expose
            private String foodingFromLong;
            @SerializedName("fooding_to")
            @Expose
            private String foodingTo;
            @SerializedName("fooding_to_detail")
            @Expose
            private String foodingToDetail;
            @SerializedName("fooding_to_lat")
            @Expose
            private String foodingToLat;
            @SerializedName("fooding_to_long")
            @Expose
            private String foodingToLong;
            @SerializedName("fooding_time")
            @Expose
            private String foodingTime;
            @SerializedName("fooding_picknow")
            @Expose
            private String foodingPicknow;
            @SerializedName("fooding_type")
            @Expose
            private String foodingType;
            @SerializedName("fooding_cost")
            @Expose
            private String foodingCost;
            @SerializedName("fooding_status")
            @Expose
            private String foodingStatus;
            @SerializedName("fooding_reason")
            @Expose
            private String foodingReason;

            /**
             *
             * @return
             *     The id
             */
            public String getId() {
                return id;
            }

            /**
             *
             * @param id
             *     The id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             *
             * @return
             *     The foodingId
             */
            public String getFoodingId() {
                return foodingId;
            }

            /**
             *
             * @param foodingId
             *     The fooding_id
             */
            public void setFoodingId(String foodingId) {
                this.foodingId = foodingId;
            }

            /**
             *
             * @return
             *     The userId
             */
            public String getUserId() {
                return userId;
            }

            /**
             *
             * @param userId
             *     The user_id
             */
            public void setUserId(String userId) {
                this.userId = userId;
            }

            /**
             *
             * @return
             *     The driverId
             */
            public String getDriverId() {
                return driverId;
            }

            /**
             *
             * @param driverId
             *     The driver_id
             */
            public void setDriverId(String driverId) {
                this.driverId = driverId;
            }

            /**
             *
             * @return
             *     The foodingFrom
             */
            public String getFoodingFrom() {
                return foodingFrom;
            }

            /**
             *
             * @param foodingFrom
             *     The fooding_from
             */
            public void setFoodingFrom(String foodingFrom) {
                this.foodingFrom = foodingFrom;
            }

            /**
             *
             * @return
             *     The foodingFromDetail
             */
            public String getFoodingFromDetail() {
                return foodingFromDetail;
            }

            /**
             *
             * @param foodingFromDetail
             *     The fooding_from_detail
             */
            public void setFoodingFromDetail(String foodingFromDetail) {
                this.foodingFromDetail = foodingFromDetail;
            }

            /**
             *
             * @return
             *     The foodingFromLat
             */
            public String getFoodingFromLat() {
                return foodingFromLat;
            }

            /**
             *
             * @param foodingFromLat
             *     The fooding_from_lat
             */
            public void setFoodingFromLat(String foodingFromLat) {
                this.foodingFromLat = foodingFromLat;
            }

            /**
             *
             * @return
             *     The foodingFromLong
             */
            public String getFoodingFromLong() {
                return foodingFromLong;
            }

            /**
             *
             * @param foodingFromLong
             *     The fooding_from_long
             */
            public void setFoodingFromLong(String foodingFromLong) {
                this.foodingFromLong = foodingFromLong;
            }

            /**
             *
             * @return
             *     The foodingTo
             */
            public String getFoodingTo() {
                return foodingTo;
            }

            /**
             *
             * @param foodingTo
             *     The fooding_to
             */
            public void setFoodingTo(String foodingTo) {
                this.foodingTo = foodingTo;
            }

            /**
             *
             * @return
             *     The foodingToDetail
             */
            public String getFoodingToDetail() {
                return foodingToDetail;
            }

            /**
             *
             * @param foodingToDetail
             *     The fooding_to_detail
             */
            public void setFoodingToDetail(String foodingToDetail) {
                this.foodingToDetail = foodingToDetail;
            }

            /**
             *
             * @return
             *     The foodingToLat
             */
            public String getFoodingToLat() {
                return foodingToLat;
            }

            /**
             *
             * @param foodingToLat
             *     The fooding_to_lat
             */
            public void setFoodingToLat(String foodingToLat) {
                this.foodingToLat = foodingToLat;
            }

            /**
             *
             * @return
             *     The foodingToLong
             */
            public String getFoodingToLong() {
                return foodingToLong;
            }

            /**
             *
             * @param foodingToLong
             *     The fooding_to_long
             */
            public void setFoodingToLong(String foodingToLong) {
                this.foodingToLong = foodingToLong;
            }

            /**
             *
             * @return
             *     The foodingTime
             */
            public String getFoodingTime() {
                return foodingTime;
            }

            /**
             *
             * @param foodingTime
             *     The fooding_time
             */
            public void setFoodingTime(String foodingTime) {
                this.foodingTime = foodingTime;
            }

            /**
             *
             * @return
             *     The foodingPicknow
             */
            public String getFoodingPicknow() {
                return foodingPicknow;
            }

            /**
             *
             * @param foodingPicknow
             *     The fooding_picknow
             */
            public void setFoodingPicknow(String foodingPicknow) {
                this.foodingPicknow = foodingPicknow;
            }

            /**
             *
             * @return
             *     The foodingType
             */
            public String getFoodingType() {
                return foodingType;
            }

            /**
             *
             * @param foodingType
             *     The fooding_type
             */
            public void setFoodingType(String foodingType) {
                this.foodingType = foodingType;
            }

            /**
             *
             * @return
             *     The foodingCost
             */
            public String getFoodingCost() {
                return foodingCost;
            }

            /**
             *
             * @param foodingCost
             *     The fooding_cost
             */
            public void setFoodingCost(String foodingCost) {
                this.foodingCost = foodingCost;
            }

            /**
             *
             * @return
             *     The foodingStatus
             */
            public String getFoodingStatus() {
                return foodingStatus;
            }

            /**
             *
             * @param foodingStatus
             *     The fooding_status
             */
            public void setFoodingStatus(String foodingStatus) {
                this.foodingStatus = foodingStatus;
            }

            /**
             *
             * @return
             *     The foodingReason
             */
            public String getFoodingReason() {
                return foodingReason;
            }

            /**
             *
             * @param foodingReason
             *     The fooding_reason
             */
            public void setFoodingReason(String foodingReason) {
                this.foodingReason = foodingReason;
            }

        }

        public class Foodlist {

            @SerializedName("food_id")
            @Expose
            private String foodId;
            @SerializedName("food_name")
            @Expose
            private String foodName;
            @SerializedName("menu_id")
            @Expose
            private String menuId;
            @SerializedName("food_image")
            @Expose
            private String foodImage;
            @SerializedName("food_cost")
            @Expose
            private String foodCost;
            @SerializedName("food_info")
            @Expose
            private String foodInfo;
            @SerializedName("food_rating")
            @Expose
            private String foodRating;
            @SerializedName("food_view")
            @Expose
            private String foodView;
            @SerializedName("number")
            @Expose
            private String number;
            @SerializedName("note")
            @Expose
            private String note;

            /**
             *
             * @return
             *     The foodId
             */
            public String getFoodId() {
                return foodId;
            }

            /**
             *
             * @param foodId
             *     The food_id
             */
            public void setFoodId(String foodId) {
                this.foodId = foodId;
            }

            /**
             *
             * @return
             *     The foodName
             */
            public String getFoodName() {
                return foodName;
            }

            /**
             *
             * @param foodName
             *     The food_name
             */
            public void setFoodName(String foodName) {
                this.foodName = foodName;
            }

            /**
             *
             * @return
             *     The menuId
             */
            public String getMenuId() {
                return menuId;
            }

            /**
             *
             * @param menuId
             *     The menu_id
             */
            public void setMenuId(String menuId) {
                this.menuId = menuId;
            }

            /**
             *
             * @return
             *     The foodImage
             */
            public String getFoodImage() {
                return foodImage;
            }

            /**
             *
             * @param foodImage
             *     The food_image
             */
            public void setFoodImage(String foodImage) {
                this.foodImage = foodImage;
            }

            /**
             *
             * @return
             *     The foodCost
             */
            public String getFoodCost() {
                return foodCost;
            }

            /**
             *
             * @param foodCost
             *     The food_cost
             */
            public void setFoodCost(String foodCost) {
                this.foodCost = foodCost;
            }

            /**
             *
             * @return
             *     The foodInfo
             */
            public String getFoodInfo() {
                return foodInfo;
            }

            /**
             *
             * @param foodInfo
             *     The food_info
             */
            public void setFoodInfo(String foodInfo) {
                this.foodInfo = foodInfo;
            }

            /**
             *
             * @return
             *     The foodRating
             */
            public String getFoodRating() {
                return foodRating;
            }

            /**
             *
             * @param foodRating
             *     The food_rating
             */
            public void setFoodRating(String foodRating) {
                this.foodRating = foodRating;
            }

            /**
             *
             * @return
             *     The foodView
             */
            public String getFoodView() {
                return foodView;
            }

            /**
             *
             * @param foodView
             *     The food_view
             */
            public void setFoodView(String foodView) {
                this.foodView = foodView;
            }

            /**
             *
             * @return
             *     The number
             */
            public String getNumber() {
                return number;
            }

            /**
             *
             * @param number
             *     The number
             */
            public void setNumber(String number) {
                this.number = number;
            }

            /**
             *
             * @return
             *     The note
             */
            public String getNote() {
                return note;
            }

            /**
             *
             * @param note
             *     The note
             */
            public void setNote(String note) {
                this.note = note;
            }

        }

    }
}
