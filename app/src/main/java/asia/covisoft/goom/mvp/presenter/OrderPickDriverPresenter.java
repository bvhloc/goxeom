package asia.covisoft.goom.mvp.presenter;

import android.content.Context;
import android.os.Bundle;

import asia.covisoft.goom.mvp.model.OrderPickDriverModel;
import asia.covisoft.goom.mvp.view.OrderPickDriverView;
import asia.covisoft.goom.prefs.Extras;

public class OrderPickDriverPresenter {

    private OrderPickDriverView view;
    private Context context;

    public OrderPickDriverPresenter(OrderPickDriverView view) {
        this.view = view;
        this.context = (Context) view;
    }

    public void getDiverInfo(Bundle extras) {

        OrderPickDriverModel model = new OrderPickDriverModel();

        model.id = extras.getString(Extras.DRIVER_ID, "");
        model.name = extras.getString(Extras.DRIVER_NAME, "");
        model.age = extras.getInt(Extras.DRIVER_AGE, 0);
        model.gender = extras.getString(Extras.DRIVER_GENDER, "male");
        model.token = extras.getString(Extras.DRIVER_TOKEN, "");

        view.setDriverInfo(model);
    }
}
