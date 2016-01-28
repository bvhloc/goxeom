package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FoodlistRoot {

    @SerializedName("foodlist")
    @Expose
    private List<Foodlist> foodlist = new ArrayList<Foodlist>();

    /**
     *
     * @return
     * The foodlist
     */
    public List<Foodlist> getFoodlist() {
        return foodlist;
    }

    /**
     *
     * @param foodlist
     * The foodlist
     */
    public void setFoodlist(List<Foodlist> foodlist) {
        this.foodlist = foodlist;
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
        @SerializedName("menu_name")
        @Expose
        private String menuName;

        /**
         *
         * @return
         * The foodId
         */
        public String getFoodId() {
            return foodId;
        }

        /**
         *
         * @param foodId
         * The food_id
         */
        public void setFoodId(String foodId) {
            this.foodId = foodId;
        }

        /**
         *
         * @return
         * The foodName
         */
        public String getFoodName() {
            return foodName;
        }

        /**
         *
         * @param foodName
         * The food_name
         */
        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        /**
         *
         * @return
         * The menuId
         */
        public String getMenuId() {
            return menuId;
        }

        /**
         *
         * @param menuId
         * The menu_id
         */
        public void setMenuId(String menuId) {
            this.menuId = menuId;
        }

        /**
         *
         * @return
         * The foodImage
         */
        public String getFoodImage() {
            return foodImage;
        }

        /**
         *
         * @param foodImage
         * The food_image
         */
        public void setFoodImage(String foodImage) {
            this.foodImage = foodImage;
        }

        /**
         *
         * @return
         * The foodCost
         */
        public String getFoodCost() {
            return foodCost;
        }

        /**
         *
         * @param foodCost
         * The food_cost
         */
        public void setFoodCost(String foodCost) {
            this.foodCost = foodCost;
        }

        /**
         *
         * @return
         * The foodInfo
         */
        public String getFoodInfo() {
            return foodInfo;
        }

        /**
         *
         * @param foodInfo
         * The food_info
         */
        public void setFoodInfo(String foodInfo) {
            this.foodInfo = foodInfo;
        }

        /**
         *
         * @return
         * The foodRating
         */
        public String getFoodRating() {
            return foodRating;
        }

        /**
         *
         * @param foodRating
         * The food_rating
         */
        public void setFoodRating(String foodRating) {
            this.foodRating = foodRating;
        }

        /**
         *
         * @return
         * The foodView
         */
        public String getFoodView() {
            return foodView;
        }

        /**
         *
         * @param foodView
         * The food_view
         */
        public void setFoodView(String foodView) {
            this.foodView = foodView;
        }

        /**
         *
         * @return
         * The menuName
         */
        public String getMenuName() {
            return menuName;
        }

        /**
         *
         * @param menuName
         * The menu_name
         */
        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }


        private int quatity;
        private String note;

        public int getQuatity() {
            return quatity;
        }

        public void setQuatity(int quatity) {
            this.quatity = quatity;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}