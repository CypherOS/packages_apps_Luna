package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker;
import com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker.Stub;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.D */
class C0877D implements ServiceConnection {
    /* renamed from: yy */
    final /* synthetic */ C0875B f1018yy;

    C0877D(C0875B c0875b) {
        this.f1018yy = c0875b;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String str = "Service connected ";
        String valueOf = String.valueOf(componentName.flattenToShortString());
        C0936x.m652g(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.f1018yy.f1004yl = Stub.asInterface(iBinder);
        if (this.f1018yy.f1006yn != null) {
            this.f1018yy.f1006yn.run();
            this.f1018yy.f1006yn = null;
        }
        C0875B c0875b = this.f1018yy;
        try {
            if (c0875b.f1008yp.mo3403cD() != null) {
                ((IScreenMatchmaker) C0915av.m613t(c0875b.f1008yp.mo3403cD())).registerSettingsCallback(new C1990K(c0875b));
            }
        } catch (Throwable e) {
            C0936x.m647a("Unable to obtain settings", e);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        C0936x.m652g("Service disconnected");
        this.f1018yy.f1004yl = null;
        this.f1018yy.f1006yn = null;
        this.f1018yy.f1002yj.post(new C0878E(this.f1018yy));
    }

    public void onBindingDied(ComponentName componentName) {
        String valueOf = String.valueOf(componentName);
        StringBuilder stringBuilder = new StringBuilder(15 + String.valueOf(valueOf).length());
        stringBuilder.append("onBindingDied: ");
        stringBuilder.append(valueOf);
        C0936x.m653h(stringBuilder.toString());
        this.f1018yy.f1004yl = null;
        this.f1018yy.f1006yn = null;
        this.f1018yy.f1002yj.post(new C0879F(this.f1018yy));
    }
}
