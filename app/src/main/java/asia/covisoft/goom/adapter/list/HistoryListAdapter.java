package asia.covisoft.goom.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import asia.covisoft.goom.R;
import asia.covisoft.goom.utils.Hex;
import asia.covisoft.goom.pojo.HistoryItem;

public class HistoryListAdapter extends ArrayAdapter<HistoryItem> {

    public Context context;
    private ArrayList<HistoryItem> model;

    @Override
    public int getCount() {
        return model.size();
    }

    private static final int resId = R.layout.list_item_history;

    public HistoryListAdapter(Context context, ArrayList<HistoryItem> model) {
        super(context, resId, model);

        this.context = context;
        this.model = model;
    }

    private class ViewHolder {

        TextView tvDatetime;
        TextView tvAddress;
        ImageView imgvCanceled;
        ImageView imgvType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HistoryItem item = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resId, parent, false);

            viewHolder.tvDatetime = (TextView) convertView.findViewById(R.id.tvDatetime);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            viewHolder.imgvCanceled = (ImageView) convertView.findViewById(R.id.imgvCanceled);
            viewHolder.imgvType = (ImageView) convertView.findViewById(R.id.imgvType);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvDatetime.setText(item.getTradingDate());
        viewHolder.tvAddress.setText( Hex.decode(item.getTradingLocation()));
        if (item.getTradingStatus().equals("cancel")) {
            viewHolder.imgvCanceled.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imgvCanceled.setVisibility(View.GONE);
        }
        String tradingType = item.getTradingId().substring(0, 1);
        switch (tradingType){
            case "C":
                viewHolder.imgvType.setImageResource(R.drawable.main_courier);
                break;
            case "T":
                viewHolder.imgvType.setImageResource(R.drawable.main_transport);
                break;
            case "F":
                viewHolder.imgvType.setImageResource(R.drawable.main_food);
                break;
            case "S":
                viewHolder.imgvType.setImageResource(R.drawable.main_shopping);
                break;
        }

        return convertView;
    }
}
