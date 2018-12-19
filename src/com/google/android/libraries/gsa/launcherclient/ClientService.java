package com.google.android.libraries.gsa.launcherclient;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import com.google.android.libraries.launcherclient.ILauncherOverlay;
import com.google.android.libraries.launcherclient.ILauncherOverlay.Stub;
import java.lang.ref.WeakReference;

/* renamed from: com.google.android.libraries.gsa.launcherclient.a */
public class ClientService extends C1251i {
    public static ClientService sInstance;
    ILauncherOverlay ael;
    public WeakReference mWeakReference;
    private boolean aen;

    /* renamed from: I */
    static ClientService m5340I(Context context) {
        if (sInstance == null) {
            sInstance = new ClientService(context.getApplicationContext());
        }
        return sInstance;
    }

    private ClientService(Context context) {
        super(context, 33);
    }

    /* renamed from: I */
    public final void mo5123I(boolean z) {
        this.aen = z;
        m5342if();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        m5341a(Stub.asInterface(iBinder));
    }

    public void onServiceDisconnected(ComponentName componentName) {
        m5341a(null);
        m5342if();
    }

    /* renamed from: if */
    private void m5342if() {
        if (this.aen && this.ael == null) {
            disconnect();
        }
    }

    /* renamed from: a */
    private void m5341a(ILauncherOverlay iLauncherOverlay) {
        this.ael = iLauncherOverlay;
        LauncherClient ig = getClient();
        if (ig != null) {
            ig.mo4017b(this.ael);
        }
    }

    /* renamed from: ig */
    public final LauncherClient getClient() {
        return this.mWeakReference != null ? (LauncherClient) this.mWeakReference.get() : null;
    }
}
