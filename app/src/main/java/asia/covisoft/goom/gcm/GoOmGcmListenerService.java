/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package asia.covisoft.goom.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;

import asia.covisoft.goom.R;
import asia.covisoft.goom.activity.history.HistoryDetailsActivity;
import asia.covisoft.goom.pojo.gson.DriverconfirmRoot;
import asia.covisoft.goom.pojo.gson.DriverconfirmRoot.Driverconfirm;
import asia.covisoft.goom.service.CancelTipService;
import asia.covisoft.goom.utils.Extras;
import asia.covisoft.goom.utils.Preferences;

public class GoOmGcmListenerService extends GcmListenerService {

    private static final String TAG = "GoOmGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
//        String title = data.getString("title");
        String message = data.getString("message");
        String json = data.getString("trading");
        Driverconfirm response = new Gson().fromJson(json, DriverconfirmRoot.class).getDriverconfirm();
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
        sendNotification(message, response);
        // [END_EXCLUDE]
    }
    // [END receive_message]

    private String trandingId;
    private String userToken;

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message, Driverconfirm response) {

        Intent intent = new Intent(this, HistoryDetailsActivity.class);
        trandingId = response.getTradingid();
        intent.putExtra(Extras.TRADING_ID, trandingId);
        userToken = getSharedPreferences(Preferences.LOGIN_PREFERENCES, MODE_PRIVATE)
                .getString(Preferences.LOGIN_PREFERENCES_USER_TOKEN, "");
        intent.putExtra(Extras.USER_TOKEN, userToken);
        intent.putExtra(Extras.HISTORY_STATE, false);
        if (response.getValue().equals("tip")) {
            intent.putExtra(Extras.REQUEST_TIP, true);
            intent.putExtra(Extras.MAX_TIP, response.getMaxsuggest());
            intent.putExtra(Extras.MIN_TIP, response.getMinsuggest());
            cancelTip();
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle(getString(R.string.app_name_full))
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void cancelTip() {

        Intent intent = new Intent(GoOmGcmListenerService.this, CancelTipService.class);
        intent.putExtra(Extras.USER_TOKEN, userToken);
        intent.putExtra(Extras.TRADING_ID, trandingId);
        startService(intent);
    }

//    public static boolean countdown;
//    private TipPresenter tipPresenter;
//    private int countdownTime = 10;
//    private Handler mHandler;
//    private Runnable countdownRunnable;
//
//    private void countdownToCancel() {
//
//        countdown = true;
//        tipPresenter = new TipPresenter(this);
//
//        Looper.prepare();
//        mHandler = new Handler();
//        countdownRunnable = new Runnable() {
//            @Override
//            public void run() {
//
//                countdownTime--;
//                SLog.d(countdownTime + "");
//                Log.d("sdb", countdownTime + "");
//                if (countdown) {
//                    if (countdownTime < 0) {
//                        notificationManager.cancelAll();
//                        tipPresenter.tip(userToken, trandingId, "0");
//                    } else {
//                        mHandler.postDelayed(countdownRunnable, 1000);
//                    }
//                }
//            }
//        };
//        mHandler.post(countdownRunnable);
//    }
//
//    @Override
//    public void onTipConfirm() {
//
//    }
//
//    @Override
//    public void onConnectionFail() {
//
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                tipPresenter.tip(userToken, trandingId, "0");
//            }
//        }, 3000);
//    }
}
