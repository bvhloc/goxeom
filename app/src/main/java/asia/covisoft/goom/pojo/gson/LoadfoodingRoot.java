package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covisoft on 21/01/2016.
 */
public class LoadfoodingRoot {

    @SerializedName("loadfooding")
    @Expose
    private Loadfooding loadfooding;

    /**
     *
     * @return
     * The loadfooding
     */
    public Loadfooding getLoadfooding() {
        return loadfooding;
    }

    /**
     *
     * @param loadfooding
     * The loadfooding
     */
    public void setLoadfooding(Loadfooding loadfooding) {
        this.loadfooding = loadfooding;
    }

    public class Loadfooding {

        @SerializedName("category")
        @Expose
        private List<Category> category = new ArrayList<Category>();
        @SerializedName("restaurant_list")
        @Expose
        private List<RestaurantList> restaurantList = new ArrayList<RestaurantList>();

        /**
         *
         * @return
         * The category
         */
        public List<Category> getCategory() {
            return category;
        }

        /**
         *
         * @param category
         * The category
         */
        public void setCategory(List<Category> category) {
            this.category = category;
        }

        /**
         *
         * @return
         * The restaurantList
         */
        public List<RestaurantList> getRestaurantList() {
            return restaurantList;
        }

        /**
         *
         * @param restaurantList
         * The restaurant_list
         */
        public void setRestaurantList(List<RestaurantList> restaurantList) {
            this.restaurantList = restaurantList;
        }

        public class Category {

            @SerializedName("food_type_id")
            @Expose
            private String foodTypeId;
            @SerializedName("food_type_name")
            @Expose
            private String foodTypeName;
            @SerializedName("food_type_info")
            @Expose
            private String foodTypeInfo;
            @SerializedName("food_type_image")
            @Expose
            private String foodTypeImage;

            /**
             *
             * @return
             * The foodTypeId
             */
            public String getFoodTypeId() {
                return foodTypeId;
            }

            /**
             *
             * @param foodTypeId
             * The food_type_id
             */
            public void setFoodTypeId(String foodTypeId) {
                this.foodTypeId = foodTypeId;
            }

            /**
             *
             * @return
             * The foodTypeName
             */
            public String getFoodTypeName() {
                return foodTypeName;
            }

            /**
             *
             * @param foodTypeName
             * The food_type_name
             */
            public void setFoodTypeName(String foodTypeName) {
                this.foodTypeName = foodTypeName;
            }

            /**
             *
             * @return
             * The foodTypeInfo
             */
            public String getFoodTypeInfo() {
                return foodTypeInfo;
            }

            /**
             *
             * @param foodTypeInfo
             * The food_type_info
             */
            public void setFoodTypeInfo(String foodTypeInfo) {
                this.foodTypeInfo = foodTypeInfo;
            }

            /**
             *
             * @return
             * The foodTypeImage
             */
            public String getFoodTypeImage() {
                return foodTypeImage;
            }

            /**
             *
             * @param foodTypeImage
             * The food_type_image
             */
            public void setFoodTypeImage(String foodTypeImage) {
                this.foodTypeImage = foodTypeImage;
            }

        }

        public class RestaurantList {

            @SerializedName("restaurant_id")
            @Expose
            private String restaurantId;
            @SerializedName("manager_id")
            @Expose
            private String managerId;
            @SerializedName("restaurant_name")
            @Expose
            private String restaurantName;
            @SerializedName("restaurant_address")
            @Expose
            private String restaurantAddress;
            @SerializedName("restaurant_lat")
            @Expose
            private String restaurantLat;
            @SerializedName("restaurant_long")
            @Expose
            private String restaurantLong;
            @SerializedName("restaurant_info")
            @Expose
            private String restaurantInfo;
            @SerializedName("restaurant_image")
            @Expose
            private String restaurantImage;
            @SerializedName("restaurant_rating")
            @Expose
            private String restaurantRating;
            @SerializedName("restaurant_view")
            @Expose
            private String restaurantView;

            /**
             *
             * @return
             * The restaurantId
             */
            public String getRestaurantId() {
                return restaurantId;
            }

            /**
             *
             * @param restaurantId
             * The restaurant_id
             */
            public void setRestaurantId(String restaurantId) {
                this.restaurantId = restaurantId;
            }

            /**
             *
             * @return
             * The managerId
             */
            public String getManagerId() {
                return managerId;
            }

            /**
             *
             * @param managerId
             * The manager_id
             */
            public void setManagerId(String managerId) {
                this.managerId = managerId;
            }

            /**
             *
             * @return
             * The restaurantName
             */
            public String getRestaurantName() {
                return restaurantName;
            }

            /**
             *
             * @param restaurantName
             * The restaurant_name
             */
            public void setRestaurantName(String restaurantName) {
                this.restaurantName = restaurantName;
            }

            /**
             *
             * @return
             * The restaurantAddress
             */
            public String getRestaurantAddress() {
                return restaurantAddress;
            }

            /**
             *
             * @param restaurantAddress
             * The restaurant_address
             */
            public void setRestaurantAddress(String restaurantAddress) {
                this.restaurantAddress = restaurantAddress;
            }

            /**
             *
             * @return
             * The restaurantLat
             */
            public String getRestaurantLat() {
                return restaurantLat;
            }

            /**
             *
             * @param restaurantLat
             * The restaurant_lat
             */
            public void setRestaurantLat(String restaurantLat) {
                this.restaurantLat = restaurantLat;
            }

            /**
             *
             * @return
             * The restaurantLong
             */
            public String getRestaurantLong() {
                return restaurantLong;
            }

            /**
             *
             * @param restaurantLong
             * The restaurant_long
             */
            public void setRestaurantLong(String restaurantLong) {
                this.restaurantLong = restaurantLong;
            }

            /**
             *
             * @return
             * The restaurantInfo
             */
            public String getRestaurantInfo() {
                return restaurantInfo;
            }

            /**
             *
             * @param restaurantInfo
             * The restaurant_info
             */
            public void setRestaurantInfo(String restaurantInfo) {
                this.restaurantInfo = restaurantInfo;
            }

            /**
             *
             * @return
             * The restaurantImage
             */
            public String getRestaurantImage() {
                return restaurantImage;
            }

            /**
             *
             * @param restaurantImage
             * The restaurant_image
             */
            public void setRestaurantImage(String restaurantImage) {
                this.restaurantImage = restaurantImage;
            }

            /**
             *
             * @return
             * The restaurantRating
             */
            public String getRestaurantRating() {
                return restaurantRating;
            }

            /**
             *
             * @param restaurantRating
             * The restaurant_rating
             */
            public void setRestaurantRating(String restaurantRating) {
                this.restaurantRating = restaurantRating;
            }

            /**
             *
             * @return
             * The restaurantView
             */
            public String getRestaurantView() {
                return restaurantView;
            }

            /**
             *
             * @param restaurantView
             * The restaurant_view
             */
            public void setRestaurantView(String restaurantView) {
                this.restaurantView = restaurantView;
            }

        }
    }
}
