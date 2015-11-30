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

/**
 * Created by Covisoft on 23/11/2015.
 */
public class HistoryListAdapter extends ArrayAdapter<HistoryItem> {

    public Context context;
    private ArrayList<HistoryItem> model;

    private static final int resId = R.layout.list_item_history;

    private static class ViewHolder {

        TextView tvDatetime;
        TextView tvAddress;
        ImageView imgvCanceled;

    }

    @Override
    public int getCount() {
        return model.size();
    }

    public HistoryListAdapter(Context context, ArrayList<HistoryItem> model) {
        super(context, resId, model);

        this.context = context;
        this.model = model;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HistoryItem item = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resId, parent, false);

            viewHolder.tvDatetime = (TextView) convertView.findViewById(R.id.tvDatetime);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            viewHolder.imgvCanceled = (ImageView) convertView.findViewById(R.id.imgvCanceled);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvDatetime.setText(item.getDatetime());
        viewHolder.tvAddress.setText(item.getAddress());
        if(!item.getStatus()){
            viewHolder.imgvCanceled.setVisibility(View.VISIBLE);
        }else {
            viewHolder.imgvCanceled.setVisibility(View.GONE);
        }

        return convertView;
    }
}
