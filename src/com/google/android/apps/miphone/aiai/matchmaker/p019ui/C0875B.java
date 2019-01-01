package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker;
import com.google.android.apps.miphone.aiai.matchmaker.api.SettingsData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1987w;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.B */
public class C0875B {
    public final Context context;
    /* renamed from: yi */
    final Executor f1001yi;
    /* renamed from: yj */
    final Handler f1002yj;
    /* renamed from: yk */
    public final SettingsData f1003yk = SettingsData.m575a(new C1987w());
    /* renamed from: yl */
    IScreenMatchmaker f1004yl;
    /* renamed from: ym */
    private boolean f1005ym;
    /* renamed from: yn */
    Runnable f1006yn;
    /* renamed from: yo */
    private final ServiceConnection f1007yo = new C0877D(this);
    /* renamed from: yp */
    final C0937y f1008yp = new C1701G(this);
    /* renamed from: yq */
    public final BroadcastReceiver f1009yq = new C0881I(this);

    /* renamed from: cE */
    public final void mo3303cE() {
        if (this.f1005ym) {
            this.context.unbindService(this.f1007yo);
            this.f1005ym = false;
            this.f1004yl = null;
        }
    }

    /* renamed from: cF */
    public final C0875B mo3304cF() {
        if (!this.f1005ym) {
            Intent intent = new Intent(IScreenMatchmaker.class.getCanonicalName());
            intent.setClassName("com.google.android.as", "com.google.android.apps.miphone.aiai.matchmaker.MatchmakerService2");
            this.f1005ym = this.context.bindService(intent, this.f1007yo, 1);
        }
        return this;
    }

    /* renamed from: e */
    public final void mo3305e(Runnable runnable) {
        if (runnable != null) {
            if (this.f1004yl == null) {
                C0936x.m651f("Waiting for service to start.");
                this.f1006yn = runnable;
                return;
            }
            runnable.run();
        }
    }

    public C0875B(Context context, Executor executor, Handler handler) {
        this.context = context;
        this.f1001yi = executor;
        this.f1002yj = handler;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart("com.google.android.as", 0);
        context.registerReceiver(this.f1009yq, intentFilter);
    }
}
