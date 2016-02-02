package asia.covisoft.goom.adapter.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.bvhloc.numpicker.widget.NumberPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.helper.TouchEffect;
import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;

public class FoodExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groups; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Foodlist>> childs;

    public List<Foodlist> getFoods() {

        List<Foodlist> foods = new ArrayList<>();
        for (String group : groups) {
            for (Foodlist food : childs.get(group)) {
                foods.add(food);
            }
        }
        return foods;
    }

    public List<Foodlist> getPickedFoods() {

        List<Foodlist> pickedFoods = new ArrayList<>();
        for (Foodlist food : getFoods()) {
            if (food.getQuatity() > 0) {
                pickedFoods.add(food);
            }
        }
        return pickedFoods;
    }

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

    private class ChildViewHolder {

        TextView tvName;
        TextView tvPrice;
        TextView tvAddNote;
        NumberPicker numPicker;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Foodlist food = (Foodlist) getChild(groupPosition, childPosition);

        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_food, parent, false);

            childViewHolder = new ChildViewHolder();

            childViewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.tvName);
            childViewHolder.tvPrice = (TextView) convertView
                    .findViewById(R.id.tvPrice);
            childViewHolder.tvAddNote = (TextView) convertView
                    .findViewById(R.id.tvAddNote);
            childViewHolder.numPicker = (NumberPicker) convertView
                    .findViewById(R.id.numPicker);

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        String name = Hex.decode(food.getFoodName());
        String price = food.getFoodCost();

        childViewHolder.tvName.setText(name);
        childViewHolder.tvPrice.setText(price);
        childViewHolder.numPicker.setCurrent(food.getQuatity());
        childViewHolder.numPicker.setOnPickListener(new NumberPicker.OnPickedListener() {
            @Override
            public void onPicked(int pickedValue) {

                food.setQuatity(pickedValue);
                notifyDataSetChanged();
                changePrice();
            }
        });
        TouchEffect.addAlpha(childViewHolder.tvAddNote);
        childViewHolder.tvAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FoodListAdapter.NoteDialog(context).showNoteDialog(food);
            }
        });

        return convertView;
    }

    private void changePrice() {

        int itemCount = 0;
        long price = 0;
        for (Foodlist food : getFoods()) {

            itemCount += food.getQuatity();

            long cost = Long.parseLong(food.getFoodCost());
            price += (food.getQuatity() * cost);
        }
        onQuantitiesChangedListener.onQuantitiesChanged(itemCount, price);
    }

    private OnQuantitiesChangedListener onQuantitiesChangedListener;

    public interface OnQuantitiesChangedListener {
        void onQuantitiesChanged(int itemCount, long price);
    }

    public void setOnQuantitiesChangedListener(OnQuantitiesChangedListener onQuantitiesChangedListener) {
        this.onQuantitiesChangedListener = onQuantitiesChangedListener;
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

    private class GroupViewHolder {

        TextView tvTitle;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String title = (String) getGroup(groupPosition);

        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(android.R.layout.simple_expandable_list_item_1, null);

            groupViewHolder = new GroupViewHolder();

            groupViewHolder.tvTitle = (TextView) convertView.findViewById(android.R.id.text1);
            groupViewHolder.tvTitle.setTypeface(null, Typeface.BOLD);
            groupViewHolder.tvTitle.setTextColor(ContextCompat.getColor(context, R.color.mDarkGreen));

            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        title = Hex.decode(title);

        groupViewHolder.tvTitle.setText(title + " (" + getChildrenCount(groupPosition) + ")");

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

