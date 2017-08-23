package com.android.launcher3.pixelui;

import android.widget.RemoteViews;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class WeatherUpdateReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
<<<<<<< HEAD:src/com/android/launcher3/pixelui/WeatherUpdateReceiver.java
        WeatherListener.getInstance(context).bH((RemoteViews)intent.getParcelableExtra("com.google.android.apps.nexuslauncher.weather_view"));
=======
        WeatherListener.getInstanceUI(context).bH((RemoteViews)intent.getParcelableExtra("com.google.android.apps.nexuslauncher.weather_view"));
>>>>>>> 658340c... Switch to Pixel Launcher QSB:src/com/android/launcher3/pixel/WeatherUpdateReceiver.java
    }
}
