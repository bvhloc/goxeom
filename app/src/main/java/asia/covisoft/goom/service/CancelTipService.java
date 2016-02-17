package asia.covisoft.goom.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import asia.covisoft.goom.helper.SLog;
import asia.covisoft.goom.mvp.presenter.TipPresenter;
import asia.covisoft.goom.mvp.view.TipView;
import asia.covisoft.goom.utils.Constant;
import asia.covisoft.goom.utils.Extras;

public class CancelTipService extends Service implements TipView {

    private String userToken;
    private String trandingId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Bundle extras = intent.getExtras();
        trandingId = extras.getString(Extras.TRADING_ID);
        countdownToCancel();

        return super.onStartCommand(intent, flags, startId);
    }

    public static boolean sent;
    public static int countdownTime;
    private TipPresenter tipPresenter;
    private Handler mHandler;
    private Runnable countdownRunnable;

    private void countdownToCancel() {

        sent = false;
        countdownTime = Constant.COUNT_DOWN_START;

        tipPresenter = new TipPresenter(this);
        final NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mHandler = new Handler();
        countdownRunnable = new Runnable() {
            @Override
            public void run() {

                if (sent) {
                    return;
                }
                countdownTime--;
                SLog.d("" + countdownTime);
                if (countdownTime < 0) {
                    notificationManager.cancelAll();
                    tipPresenter.tip(trandingId, "0");
                } else {
                    mHandler.postDelayed(countdownRunnable, 1000);
                }
            }
        };
        mHandler.post(countdownRunnable);
    }

    @Override
    public void onTipConfirm() {

        this.stopSelf();
    }

    @Override
    public void onConnectionFail() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                tipPresenter.tip(trandingId, "0");
            }
        }, 3000);
    }

}
