package asia.covisoft.goom.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.helper.NetworkClient;
import asia.covisoft.goom.mvp.view.OrderFoodPickFoodView;
import asia.covisoft.goom.pojo.gson.FoodlistRoot;
import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;

public class OrderFoodPickFoodPresenter {

    private OrderFoodPickFoodView view;
    private Context context;

    public OrderFoodPickFoodPresenter(OrderFoodPickFoodView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void setupListHeader(Bundle extras) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        @SuppressLint("InflateParams")
        View header = inflater.inflate(R.layout.header_restaurant, null);

        String name = Hex.decode(extras.getString(Extras.RESTAURANT_NAME));
        String address = Hex.decode(extras.getString(Extras.RESTAURANT_ADDRESS));
        String url = Hex.decode(extras.getString(Extras.RESTAURANT_IMAGE));

        ((TextView) header.findViewById(R.id.tvName)).setText(name);
        ((TextView) header.findViewById(R.id.tvAddress)).setText(address);
        ImageView imgvAvatar = (ImageView) header.findViewById(R.id.imgvAvatar);
        Picasso.with(context)
                .load(Constant.HOST + url)
                .into(imgvAvatar);

        view.initListHeader(header);
    }

    private ProgressDialog progressDialog;

    public void getMenu(final String token, final String restaurantId) {
        new AsyncTask<Void, Void, List<Foodlist>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "", context.getString(R.string.dialog_loading));
            }

            @Override
            protected List<Foodlist> doInBackground(Void... params) {

                String URL = Constant.HOST +
                        "foodlist.php?token=" + token +
                        "&restaurantid=" + restaurantId;
                Log.d("sdb", URL);
                try {
                    String json = new NetworkClient().getJsonFromUrl(URL);

                    FoodlistRoot root = new Gson().fromJson(json, FoodlistRoot.class);

                    return root.getFoodlist();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(List<Foodlist> foods) {
                super.onPostExecute(foods);

                if (foods == null){
                    view.onConnectionFail();
                }
                else{
                    initData(foods);
                }
                progressDialog.dismiss();
            }

        }.execute();
    }

    private void initData(List<Foodlist> foods) {

        List<String> groups = new ArrayList<>();
        HashMap<String, List<Foodlist>> childs = new HashMap<>();
        List<Foodlist> groupChilds = null;

        for (Foodlist food : foods) {
            String menuName = food.getMenuName();
            if (!groups.contains(menuName)) {
                groups.add(menuName);
                groupChilds = new ArrayList<>();
            }
            if (groupChilds != null) {
                groupChilds.add(food);
            }
            childs.put(menuName, groupChilds);
        }

        view.onMenuLoaded(groups, childs);
    }
}
