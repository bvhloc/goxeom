package asia.covisoft.goom.mvp.presenter;

import android.content.Context;

import asia.covisoft.goom.mvp.view.OrderFoodView;

public class OrderFoodPresenter {

    private OrderFoodView view;
    private Context context;

    public OrderFoodPresenter(OrderFoodView view) {
        this.view = view;
        this.context = (Context) view;
    }

}
