package asia.covisoft.goom.mvp.view;

/**
 * Created by Covisoft on 19/01/2016.
 */
public interface OrderConfirmView {

    void onBookingMade(String bookingId);
    void onConnectionFail();
}
