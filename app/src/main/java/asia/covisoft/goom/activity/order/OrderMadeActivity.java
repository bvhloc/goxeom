package asia.covisoft.goom.activity.order;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import asia.covisoft.goom.R;
import asia.covisoft.goom.base.BaseActivity;
import asia.covisoft.goom.helper.AppHelper;
import asia.covisoft.goom.mvp.presenter.OrderMadePresenter;
import asia.covisoft.goom.mvp.view.OrderMadeView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;

public class OrderMadeActivity extends BaseActivity implements OrderMadeView {

    private Context mContext;
    private OrderMadePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_made);
        mContext = this;
        presenter = new OrderMadePresenter(this);

        findViewById(R.id.btnCancelBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogCancel();
            }
        });
        findViewById(R.id.btnNewBooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AppHelper(mContext).restartToMain(1);
            }
        });
    }

    private void showDialogCancel() {
        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_cancelbooking))
                .setNegativeButton(getString(R.string.lowcase_no),null)
                .setPositiveButton(getString(R.string.lowcase_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String bookingId = getIntent().getStringExtra(Extras.BOOKING_ID);
                        presenter.setConfirm(bookingId, presenter.CONFIRM_VALUE_CANCEL);
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        //disable backPress
    }

    @Override
    public void onConnectionFail() {

        new AlertDialog.Builder(mContext)
                .setMessage(getString(R.string.dialog_connection_fail))
                .setNeutralButton(getString(R.string.lowcase_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        onBackPressed();
                    }
                })
                .show();
    }

    @Override
    public void onBookingCanceled() {

        Intent intent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Constant.TAB_POSTION, 0);
        startActivity(intent);
    }
}
