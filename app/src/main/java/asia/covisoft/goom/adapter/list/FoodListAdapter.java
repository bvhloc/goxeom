package asia.covisoft.goom.adapter.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.bvhloc.numpicker.widget.NumberPicker;

import java.util.List;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.helper.TouchEffect;
import asia.covisoft.goom.pojo.gson.FoodlistRoot.Foodlist;

public class FoodListAdapter extends ArrayAdapter<Foodlist> {

    public Context context;
    private List<Foodlist> model;

    @Override
    public int getCount() {
        return model.size();
    }

    private static final int resId = R.layout.list_item_food;

    public FoodListAdapter(Context context, List<Foodlist> model) {
        super(context, resId, model);

        this.context = context;
        this.model = model;
    }

    private class ViewHolder {

        TextView tvName;
        TextView tvPrice;
        TextView tvAddNote;
        NumberPicker numPicker;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Foodlist item = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resId, parent, false);

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            viewHolder.tvAddNote = (TextView) convertView.findViewById(R.id.tvAddNote);
            viewHolder.numPicker = (NumberPicker) convertView.findViewById(R.id.numPicker);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = Hex.decode(item.getFoodName());
        String price = item.getFoodCost();

        viewHolder.tvName.setText(name);
        viewHolder.tvPrice.setText(price);
        viewHolder.numPicker.setCurrent(item.getQuatity());
        viewHolder.numPicker.setOnPickListener(new NumberPicker.OnPickedListener() {
            @Override
            public void onPicked(int pickedValue) {

                item.setQuatity(pickedValue);
                notifyDataSetChanged();
//                genPrice();
            }
        });
        TouchEffect.addAlpha(viewHolder.tvAddNote);
        viewHolder.tvAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new NoteDialog(context).showNoteDialog(item);
            }
        });

        return convertView;
    }

    public static class NoteDialog{

        private Context context;

        public NoteDialog(Context context) {
            this.context = context;
        }

        public void showNoteDialog(final Foodlist food) {

            final EditText edtNote = new EditText(context);
            edtNote.setHint(context.getString(R.string.lowcase_note));
            String note = food.getNote();
            if (note != null) {
                edtNote.setText(note);
                edtNote.setSelection(note.length());
            }
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setView(edtNote)
                    .setTitle(context.getString(R.string.lowcase_addnote))
                    .setNegativeButton(context.getString(R.string.lowcase_cancel), null)
                    .setNeutralButton(context.getString(R.string.lowcase_clear), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            food.setNote("");
                        }
                    })
                    .setPositiveButton(context.getString(R.string.lowcase_save), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            food.setNote(edtNote.getText().toString());
                        }
                    })
                    .create();
            dialog.show();
        }
    }
}
