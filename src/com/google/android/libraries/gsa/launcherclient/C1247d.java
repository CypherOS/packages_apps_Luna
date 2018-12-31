package com.google.android.libraries.gsa.launcherclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;

class C1247d extends BroadcastReceiver {
    private LauncherClient mClient;

    C1247d(LauncherClient launcherClient) {
        mClient = launcherClient;
    }

    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if (VERSION.SDK_INT >= 19 || (data != null && "com.google.android.googlequicksearchbox".equals(data.getSchemeSpecificPart()))) {
            mClient.aex.disconnect();
            mClient.mClientService.disconnect();
            if ((mClient.mState & 2) != 0) {
                mClient.reconnect();
            }
        }
    }
}
