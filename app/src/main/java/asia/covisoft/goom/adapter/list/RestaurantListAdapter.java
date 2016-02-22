package asia.covisoft.goom.adapter.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.activity.order.OrderFoodPickFoodActivity;
import asia.covisoft.goom.utils.Hex;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;
import asia.covisoft.goom.prefs.Constant;
import asia.covisoft.goom.prefs.Extras;
import asia.covisoft.goom.prefs.Preferences;

public class RestaurantListAdapter extends ArrayAdapter<RestaurantList> {

    public Context context;
    private List<RestaurantList> model;

    @Override
    public int getCount() {
        return model.size();
    }

    private static final int resId = R.layout.grid_item_restaurant;

    public RestaurantListAdapter(Context context, List<RestaurantList> model) {
        super(context, resId, model);

        this.context = context;
        this.model = model;
    }

    private class ViewHolder {

        ImageView imgvAvatar;
        TextView tvName;
        TextView tvAddress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final RestaurantList item = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resId, parent, false);

            viewHolder.imgvAvatar = (ImageView) convertView.findViewById(R.id.imgvAvatar);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = Hex.decode(item.getRestaurantName());
        viewHolder.tvName.setText(name);
        String address = Hex.decode(item.getRestaurantAddress());
        viewHolder.tvAddress.setText(address);
        String imageUrl = Hex.decode(item.getRestaurantImage());
        Picasso.with(context)
                .load(Constant.HOST + imageUrl)
                .into(viewHolder.imgvAvatar);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startPickFoodActivity(item);
            }
        });

        return convertView;
    }

    public void startPickFoodActivity(RestaurantList restaurant){

        SharedPreferences loginPreferences = context.getSharedPreferences(Preferences.LOGIN_PREFERENCES, Activity.MODE_PRIVATE);
        String userToken = loginPreferences.getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");

        Intent intent = new Intent(context, OrderFoodPickFoodActivity.class);
        intent.putExtra(Extras.USER_TOKEN, userToken);
        intent.putExtra(Extras.RESTAURANT_ID, restaurant.getRestaurantId());
        intent.putExtra(Extras.RESTAURANT_LAT, Double.parseDouble(restaurant.getRestaurantLat()));
        intent.putExtra(Extras.RESTAURANT_LNG, Double.parseDouble(restaurant.getRestaurantLong()));
        intent.putExtra(Extras.RESTAURANT_NAME, restaurant.getRestaurantName());
        intent.putExtra(Extras.RESTAURANT_ADDRESS, restaurant.getRestaurantAddress());
        intent.putExtra(Extras.RESTAURANT_IMAGE, restaurant.getRestaurantImage());
        context.startActivity(intent);
    }
}
