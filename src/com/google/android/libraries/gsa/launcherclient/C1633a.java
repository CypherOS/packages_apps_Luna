package com.google.android.libraries.gsa.launcherclient;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import com.google.android.libraries.launcherclient.ILauncherOverlay;
import com.google.android.libraries.launcherclient.ILauncherOverlay.Stub;
import java.lang.ref.WeakReference;

/* renamed from: com.google.android.libraries.gsa.launcherclient.a */
public class C1633a extends C1251i {
    public static C1633a aek;
    ILauncherOverlay ael;
    public WeakReference aem;
    private boolean aen;

    /* renamed from: I */
    static C1633a m5340I(Context context) {
        if (aek == null) {
            aek = new C1633a(context.getApplicationContext());
        }
        return aek;
    }

    private C1633a(Context context) {
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
            mo4045il();
        }
    }

    /* renamed from: a */
    private void m5341a(ILauncherOverlay iLauncherOverlay) {
        this.ael = iLauncherOverlay;
        LauncherClient ig = mo5124ig();
        if (ig != null) {
            ig.mo4017b(this.ael);
        }
    }

    /* renamed from: ig */
    public final LauncherClient mo5124ig() {
        return this.aem != null ? (LauncherClient) this.aem.get() : null;
    }
}
