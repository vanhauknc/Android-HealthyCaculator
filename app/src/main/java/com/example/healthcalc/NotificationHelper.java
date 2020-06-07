package com.example.healthcalc;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


public class NotificationHelper extends ContextWrapper {
    private NotificationManager mManager;
    public static final String channel1ID = "channel1ID";
    public static final String channel1Name = "Chanel 1";

    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannels();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels(){
        NotificationChannel channel = new NotificationChannel(channel1ID,channel1Name, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }

    public  NotificationManager getManager(){
        if(mManager==null){
            mManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification(){
        return  new NotificationCompat.Builder(getApplicationContext(),channel1ID)
                .setContentTitle("Heath Caculator !")
                .setContentText("Bạn đã kiểm tra sức khỏe hôm nay chưa -> Vào Ngay")
                .setSmallIcon(R.drawable.icons8_exercise_50px);

    }
}
