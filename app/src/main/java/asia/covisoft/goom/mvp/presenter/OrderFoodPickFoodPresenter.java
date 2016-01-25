package asia.covisoft.goom.mvp.presenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import asia.covisoft.goom.R;
import asia.covisoft.goom.helper.Hex;
import asia.covisoft.goom.mvp.view.OrderFoodPickFoodView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;

public class OrderFoodPickFoodPresenter {

    private OrderFoodPickFoodView view;
    private Context context;

    public OrderFoodPickFoodPresenter(OrderFoodPickFoodView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void setupListHeader(Bundle extras) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        @SuppressLint("InflateParams")
        View header = inflater.inflate(R.layout.header_restaurant, null);

        String name = new Hex().toString(extras.getString(Extras.RESTAURANT_NAME));
        String address = new Hex().toString(extras.getString(Extras.RESTAURANT_ADDRESS));
        String url = new Hex().toString(extras.getString(Extras.RESTAURANT_IMAGE));

        ((TextView)header.findViewById(R.id.tvName)).setText(name);
        ((TextView)header.findViewById(R.id.tvAddress)).setText(address);
        ImageView imgvAvatar = (ImageView) header.findViewById(R.id.imgvAvatar);
        Picasso.with(context)
                .load(Constant.HOST + url)
                .into(imgvAvatar);

        view.initListHeader(header);
    }
}
