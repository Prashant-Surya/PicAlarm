package com.prashant.alarm.picalarm;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;

public class BdayReceiver extends BroadcastReceiver {
    public BdayReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        Toast.makeText(context, "Happy Birthday", Toast.LENGTH_LONG).show();
        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(context);
        try {
            myWallpaperManager.setResource(R.drawable.hbd2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Birthday");
    }
}
