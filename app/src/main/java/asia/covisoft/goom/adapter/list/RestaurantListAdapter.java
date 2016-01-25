package asia.covisoft.goom.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.pojo.gson.LoadfoodingRoot.Loadfooding.RestaurantList;
import asia.covisoft.goom.utils.Constant;

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

        RestaurantList item = getItem(position);

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

        String name = new Hex().decode(item.getRestaurantName());
        viewHolder.tvName.setText(name);
        String address = new Hex().decode(item.getRestaurantAddress());
        viewHolder.tvAddress.setText(address);
        String imageUrl = new Hex().decode(item.getRestaurantImage());
        Picasso.with(context)
                .load(Constant.HOST + imageUrl)
                .into(viewHolder.imgvAvatar);

        return convertView;
    }
}
