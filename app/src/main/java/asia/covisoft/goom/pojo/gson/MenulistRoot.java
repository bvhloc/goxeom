package asia.covisoft.goom.pojo.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Covisoft on 25/01/2016.
 */
public class MenulistRoot {

    @SerializedName("menulist")
    @Expose
    private List<Menulist> menulist = new ArrayList<Menulist>();

    /**
     *
     * @return
     * The menulist
     */
    public List<Menulist> getMenulist() {
        return menulist;
    }

    /**
     *
     * @param menulist
     * The menulist
     */
    public void setMenulist(List<Menulist> menulist) {
        this.menulist = menulist;
    }

    public class Menulist {

        @SerializedName("menu_id")
        @Expose
        private String menuId;
        @SerializedName("menu_name")
        @Expose
        private String menuName;
        @SerializedName("restaurant_id")
        @Expose
        private String restaurantId;
        @SerializedName("food_type_id")
        @Expose
        private String foodTypeId;
        @SerializedName("menu_image")
        @Expose
        private String menuImage;
        @SerializedName("menu_info")
        @Expose
        private String menuInfo;
        @SerializedName("menu_rating")
        @Expose
        private String menuRating;
        @SerializedName("menu_view")
        @Expose
        private String menuView;

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
         * The menuImage
         */
        public String getMenuImage() {
            return menuImage;
        }

        /**
         *
         * @param menuImage
         * The menu_image
         */
        public void setMenuImage(String menuImage) {
            this.menuImage = menuImage;
        }

        /**
         *
         * @return
         * The menuInfo
         */
        public String getMenuInfo() {
            return menuInfo;
        }

        /**
         *
         * @param menuInfo
         * The menu_info
         */
        public void setMenuInfo(String menuInfo) {
            this.menuInfo = menuInfo;
        }

        /**
         *
         * @return
         * The menuRating
         */
        public String getMenuRating() {
            return menuRating;
        }

        /**
         *
         * @param menuRating
         * The menu_rating
         */
        public void setMenuRating(String menuRating) {
            this.menuRating = menuRating;
        }

        /**
         *
         * @return
         * The menuView
         */
        public String getMenuView() {
            return menuView;
        }

        /**
         *
         * @param menuView
         * The menu_view
         */
        public void setMenuView(String menuView) {
            this.menuView = menuView;
        }

    }
}