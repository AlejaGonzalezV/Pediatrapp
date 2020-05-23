package com.example.pediatrapp.utilities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.pediatrapp.R;

public class NotificationUtils {

    public static final String CHANNEL_ID = "PediatrApp";
    public static final String CHANNEL_NAME = "Messages";
    public static final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
    public static int consecutive = 1;


    public static void createNotification(Context context, String mensaje) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("Nuevo Mensaje")
                .setContentText(mensaje)
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify(consecutive, builder.build());
        consecutive++;
    }


}
