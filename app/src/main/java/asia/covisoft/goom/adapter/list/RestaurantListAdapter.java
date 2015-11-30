package asia.covisoft.goom.adapter.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import asia.covisoft.goom.R;
import asia.covisoft.goom.pojo.RestaurantItem;

/**
 * Created by Covisoft on 23/11/2015.
 */
public class RestaurantListAdapter extends ArrayAdapter<RestaurantItem> {

    public Context context;
    private ArrayList<RestaurantItem> model;

    @Override
    public int getCount() {
        return model.size();
    }

    private static final int resId = R.layout.grid_item_restaurant;

    public RestaurantListAdapter(Context context, ArrayList<RestaurantItem> model) {
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

        RestaurantItem item = getItem(position);

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

        viewHolder.tvName.setText(item.getName());
        viewHolder.tvAddress.setText(item.getAddress());
        Picasso.with(context)
                .load("file:///android_asset/"+item.getImageUrl())
                .into(viewHolder.imgvAvatar);

        return convertView;
    }
}
