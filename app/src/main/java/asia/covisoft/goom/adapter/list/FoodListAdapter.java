package asia.covisoft.goom.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import asia.covisoft.goom.R;
import asia.covisoft.goom.pojo.FoodItem;
import asia.covisoft.goom.pojo.FoodTypeItem;

/**
 * Created by Covisoft on 23/11/2015.
 */
public class FoodListAdapter extends ArrayAdapter<FoodItem> {

    public Context context;
    private ArrayList<FoodItem> model;

    @Override
    public int getCount() {
        return model.size();
    }

    private static final int resId = R.layout.list_item_food;

    public FoodListAdapter(Context context, ArrayList<FoodItem> model) {
        super(context, resId, model);

        this.context = context;
        this.model = model;
    }

    private class ViewHolder {

        TextView tvName;
        TextView tvPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FoodItem item = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resId, parent, false);

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(item.getName());
        viewHolder.tvPrice.setText(item.getPrice());

        return convertView;
    }
}
