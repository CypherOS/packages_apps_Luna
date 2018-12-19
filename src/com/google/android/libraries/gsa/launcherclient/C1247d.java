package com.google.android.libraries.gsa.launcherclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;

/* renamed from: com.google.android.libraries.gsa.launcherclient.d */
class C1247d extends BroadcastReceiver {
    final /* synthetic */ LauncherClient aeI;

    C1247d(LauncherClient launcherClient) {
        this.aeI = launcherClient;
    }

    public void onReceive(Context context, Intent intent) {
        Uri data = intent.getData();
        if (VERSION.SDK_INT >= 19 || (data != null && "com.google.android.googlequicksearchbox".equals(data.getSchemeSpecificPart()))) {
            this.aeI.aex.mo4045il();
            this.aeI.aey.mo4045il();
            LauncherClient.m3606K(context);
            if ((this.aeI.aeB & 2) != 0) {
                this.aeI.reconnect();
            }
        }
    }
}
