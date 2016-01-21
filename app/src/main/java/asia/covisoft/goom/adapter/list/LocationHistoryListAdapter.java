package asia.covisoft.goom.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.pojo.activeandroid.LocationHistory;

/**
 * Created by Covisoft on 26/11/2015.
 */
public class LocationHistoryListAdapter extends ArrayAdapter<LocationHistory> {

    public Context context;
    private List<LocationHistory> model;

    @Override
    public int getCount() {
        return model.size();
    }

    private static final int resId = R.layout.list_item_location_history;

    public LocationHistoryListAdapter(Context context, List<LocationHistory> model) {
        super(context, resId, model);

        this.context = context;
        this.model = model;
    }

    private class ViewHolder {

        TextView tvDatetime;
        TextView tvAddress;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LocationHistory item = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resId, parent, false);

            viewHolder.tvDatetime = (TextView) convertView.findViewById(R.id.tvDatetime);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvDatetime.setText(item.getDatetime());
        viewHolder.tvAddress.setText(item.getAddress());

        return convertView;
    }
}
