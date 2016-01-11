package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadinfoRoot {

    @SerializedName("loadinfo")
    @Expose
    private Loadinfo loadinfo;

    /**
     *
     * @return
     * The loadinfo
     */
    public Loadinfo getLoadinfo() {
        return loadinfo;
    }

    /**
     *
     * @param loadinfo
     * The loadinfo
     */
    public void setLoadinfo(Loadinfo loadinfo) {
        this.loadinfo = loadinfo;
    }

    public class Loadinfo {

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
         * The userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         *
         * @param userName
         * The user_name
         */
        public void setUserName(String userName) {
            this.userName = userName;
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
