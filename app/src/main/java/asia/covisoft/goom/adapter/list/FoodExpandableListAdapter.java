package asia.covisoft.goom.adapter.list;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;

public class FoodExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groups; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Foodlist>> childs;

    public FoodExpandableListAdapter(Context context, List<String> groups,
                                 HashMap<String, List<Foodlist>> childs) {
        this.context = context;
        this.groups = groups;
        this.childs = childs;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.childs.get(this.groups.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        Foodlist child = (Foodlist) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_food, null);
        }

        TextView tvName = (TextView) convertView
                .findViewById(R.id.tvName);
        TextView tvPrice = (TextView) convertView
                .findViewById(R.id.tvPrice);

        String name = Hex.decode(child.getFoodName());
        String price = child.getFoodCost();

        tvName.setText(name);
        tvPrice.setText(price);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childs.get(this.groups.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(android.R.id.text1);
        lblListHeader.setTypeface(null, Typeface.BOLD);

        headerTitle = Hex.decode(headerTitle);

        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

