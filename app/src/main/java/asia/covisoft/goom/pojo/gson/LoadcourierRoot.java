package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoadcourierRoot {

    @SerializedName("loadcourier")
    @Expose
    private List<Loadcourier> loadcourier = new ArrayList<Loadcourier>();

    /**
     *
     * @return
     * The loadcourier
     */
    public List<Loadcourier> getLoadcourier() {
        return loadcourier;
    }

    /**
     *
     * @param loadcourier
     * The loadcourier
     */
    public void setLoadcourier(List<Loadcourier> loadcourier) {
        this.loadcourier = loadcourier;
    }

    public class Loadcourier {

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
         * The driverName
         */
        public String getDriverName() {
            return driverName;
        }

        /**
         *
         * @param driverName
         * The driver_name
         */
        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        /**
         *
         * @return
         * The fullName
         */
        public String getFullName() {
            return fullName;
        }

        /**
         *
         * @param fullName
         * The full_name
         */
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        /**
         *
         * @return
         * The birthDay
         */
        public String getBirthDay() {
            return birthDay;
        }

        /**
         *
         * @param birthDay
         * The birth_day
         */
        public void setBirthDay(String birthDay) {
            this.birthDay = birthDay;
        }

        /**
         *
         * @return
         * The passWord
         */
        public String getPassWord() {
            return passWord;
        }

        /**
         *
         * @param passWord
         * The pass_word
         */
        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        /**
         *
         * @return
         * The status
         */
        public String getStatus() {
            return status;
        }

        /**
         *
         * @param status
         * The status
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         *
         * @return
         * The rating
         */
        public String getRating() {
            return rating;
        }

        /**
         *
         * @param rating
         * The rating
         */
        public void setRating(String rating) {
            this.rating = rating;
        }

        /**
         *
         * @return
         * The view
         */
        public String getView() {
            return view;
        }

        /**
         *
         * @param view
         * The view
         */
        public void setView(String view) {
            this.view = view;
        }

        /**
         *
         * @return
         * The email
         */
        public String getEmail() {
            return email;
        }

        /**
         *
         * @param email
         * The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         *
         * @return
         * The phoneNumber
         */
        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         *
         * @param phoneNumber
         * The phone_number
         */
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        /**
         *
         * @return
         * The avatarImage
         */
        public String getAvatarImage() {
            return avatarImage;
        }

        /**
         *
         * @param avatarImage
         * The avatar_image
         */
        public void setAvatarImage(String avatarImage) {
            this.avatarImage = avatarImage;
        }

        /**
         *
         * @return
         * The latitude
         */
        public String getLatitude() {
            return latitude;
        }

        /**
         *
         * @param latitude
         * The latitude
         */
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        /**
         *
         * @return
         * The longitude
         */
        public String getLongitude() {
            return longitude;
        }

        /**
         *
         * @param longitude
         * The longitude
         */
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        /**
         *
         * @return
         * The token
         */
        public String getToken() {
            return token;
        }

        /**
         *
         * @param token
         * The token
         */
        public void setToken(String token) {
            this.token = token;
        }

        /**
         *
         * @return
         * The loginFault
         */
        public String getLoginFault() {
            return loginFault;
        }

        /**
         *
         * @param loginFault
         * The login_fault
         */
        public void setLoginFault(String loginFault) {
            this.loginFault = loginFault;
        }

    }
}