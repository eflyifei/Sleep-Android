package com.arefly.sleep.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.arefly.sleep.R;
import com.arefly.sleep.activities.StatisticActivity;
import com.arefly.sleep.receivers.ScreenReceiver;
import com.orhanobut.logger.Logger;

/**
 * Created by eflyjason on 3/8/2016.
 */
public class ScreenService extends Service {

    BroadcastReceiver mReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i("ScreenService onCreate()");

        // TODO: Change StatisticActivity to YesterdayActivity
        Intent notificationIntent = new Intent(this, StatisticActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        // TODO: Hide notification on morning? (seemly done)
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("晚上好！今晚多睡會吧！\n\n輕觸查看詳細統計記錄..."))
                .setContentTitle("昨晚睡眠時間：6小時")
                .setContentText("輕觸查看詳細統計記錄")
                .setContentIntent(notificationPendingIntent)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();

        startForeground(88, notification);


        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        screenStateFilter.addAction(Intent.ACTION_SHUTDOWN);
        screenStateFilter.addAction(getString(R.string.htc_action_quickboot_poweroff));
        mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, screenStateFilter);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i("ScreenService onStartCommand()");

        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.i("ScreenService onBind()");
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("ScreenService onDestroy()");

        unregisterReceiver(mReceiver);
    }

}