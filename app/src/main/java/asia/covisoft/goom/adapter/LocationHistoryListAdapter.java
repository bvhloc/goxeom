package asia.covisoft.goom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import asia.covisoft.goom.R;
import asia.covisoft.goom.pojo.HistoryItem;
import asia.covisoft.goom.pojo.LocationHistoryItem;

/**
 * Created by Covisoft on 26/11/2015.
 */
public class LocationHistoryListAdapter extends ArrayAdapter<LocationHistoryItem> {

    public Context context;
    private ArrayList<LocationHistoryItem> model;

    private static final int resId = R.layout.list_item_location_history;

    private static class ViewHolder {

        TextView tvName;
        TextView tvAddress;

    }

    @Override
    public int getCount() {
        return model.size();
    }

    public LocationHistoryListAdapter(Context context, ArrayList<LocationHistoryItem> model) {
        super(context, resId, model);

        this.context = context;
        this.model = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LocationHistoryItem item = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resId, parent, false);

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(item.getName());
        viewHolder.tvAddress.setText(item.getAddress());

        return convertView;
    }
}
