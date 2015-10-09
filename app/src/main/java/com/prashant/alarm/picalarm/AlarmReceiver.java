package com.prashant.alarm.picalarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(context, "Brush Chesko idiot", Toast.LENGTH_LONG).show();
        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(context);
        try {
            myWallpaperManager.setResource(R.drawable.brush);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Receiver");
    }
}
