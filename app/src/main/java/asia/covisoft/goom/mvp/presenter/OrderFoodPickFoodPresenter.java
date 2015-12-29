package asia.covisoft.goom.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import asia.covisoft.goom.R;
import asia.covisoft.goom.mvp.view.OrderFoodPickFoodView;

public class OrderFoodPickFoodPresenter {

    private OrderFoodPickFoodView view;
    private Context context;

    public OrderFoodPickFoodPresenter(OrderFoodPickFoodView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void setupListHeader(Bundle extras) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View header = inflater.inflate(R.layout.header_restaurant, null);

        ((TextView)header.findViewById(R.id.tvName)).setText(extras.getString("name"));
        ((TextView)header.findViewById(R.id.tvAddress)).setText(extras.getString("address"));
        ImageView imgvAvatar = (ImageView) header.findViewById(R.id.imgvAvatar);
        Picasso.with(context)
                .load("file:///android_asset/" + extras.getString("imageurl"))
                .into(imgvAvatar);

        view.initListHeader(header);
    }
}
