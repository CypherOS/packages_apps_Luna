package com.google.android.libraries.gsa.launcherclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/* renamed from: com.google.android.libraries.gsa.launcherclient.i */
public class C1251i implements ServiceConnection {
    public boolean aeJ;
    private final Context context;
    private final int flags;

    C1251i(Context context, int i) {
        this.context = context;
        this.flags = i;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    /* renamed from: il */
    public final void mo4045il() {
        if (this.aeJ) {
            this.context.unbindService(this);
            this.aeJ = false;
        }
    }

    /* renamed from: im */
    public final boolean mo4046im() {
        if (!this.aeJ) {
            try {
                this.aeJ = this.context.bindService(LauncherClient.m3605J(this.context), this, this.flags);
            } catch (SecurityException e) {
                Log.e("LauncherClient", "Unable to connect to overlay service", e);
            }
        }
        return this.aeJ;
    }
}
