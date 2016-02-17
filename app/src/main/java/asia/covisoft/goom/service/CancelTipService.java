package asia.covisoft.goom.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import asia.covisoft.goom.helper.SLog;
import asia.covisoft.goom.mvp.presenter.TipPresenter;
import asia.covisoft.goom.mvp.view.TipView;
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
        userToken = extras.getString(Extras.USER_TOKEN);
        trandingId = extras.getString(Extras.TRADING_ID);
        countdownToCancel();

        return super.onStartCommand(intent, flags, startId);
    }

    private TipPresenter tipPresenter;
    public static int countdownTime = 30;
    private Handler mHandler;
    private Runnable countdownRunnable;

    private void countdownToCancel() {

        tipPresenter = new TipPresenter(this);
        final NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mHandler = new Handler();
        countdownRunnable = new Runnable() {
            @Override
            public void run() {

                countdownTime--;
                SLog.d("" + countdownTime);
                if (countdownTime < 0) {
                    notificationManager.cancelAll();
                    tipPresenter.tip(userToken, trandingId, "0");
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
                tipPresenter.tip(userToken, trandingId, "0");
            }
        }, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show();
    }

//    public static boolean openCurrent = false;
//    private int countTime = 30;
//    private Handler mHandler = new Handler();
//    private final Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//
//            countTime--;
//
//            if (countTime <= 0) {
//
//                Log.d("jsonData_from_server", jsonData);
//                TradingRoot mTradingRoot = new Gson().fromJson(jsonData, TradingRoot.class);
//
//                // get tradingID & value
//                tradingID = mTradingRoot.getSendtrading().getId();
//                value = mTradingRoot.getSendtrading().getStatus();
//
//                if (openCurrent) {
//                    Log.d("sdb_service_not_sent", "Not sent");
//                } else {
//                    Log.d("sdb_service_sent", "Sent");
//                    putURLToServer();
//                }
//
//                CancelTipService.this.stopSelf();
//            } else
//                mHandler.postDelayed(mRunnable, 1000);
//        }
//    };
//
//    public void putURLToServer() {
//        new AsyncTask<Void, Void, Void>() {
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                mSharedPreferences = getSharedPreferences(Preferences.DRIVER_TOKEN, MODE_PRIVATE);
//                String driver_token = mSharedPreferences.getString("driverToken", "");
//
//                TinyDB tinyDB = new TinyDB(CancelTipService.this);
//                if (!value.equals("")) {
//                    tinyDB.putString(Preferences.DRIVER_STATUS, value);
//                }
//
//                String tipStatus = tinyDB.getString(Preferences.DRIVER_STATUS);
//
//                // put URL to server
//                String url = Constant.HOST + "setconfirm.php?token=" + driver_token +
//                        "&tradingid=" + tradingID + "&value=" + tipStatus;
//
//                String json = null;
//                try {
//                    json = new NetworkClient().getJsonFromUrl(url);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                new Gson().fromJson(json, TradingRoot.class);
//                Log.d("url_put_server", url);
//
//                /**
//                 * Check Driver Status
//                 * If status = "wait4"
//                 * Remove notification
//                 */
//                NotificationManager notificationManager =
//                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.cancel(GoOmGcmListenerService.NOTIFICATION_ID);
//
//                return null;
//            }
//        }.execute();
//    }

}
