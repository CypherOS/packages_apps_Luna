package com.android.launcher3.reflection.sensing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import com.android.launcher3.reflection.C0836h.C0835a;
import com.android.launcher3.reflection.ReflectionClient;
import com.android.launcher3.reflection.signal.C1214a;
import com.android.launcher3.reflection.signal.C1219f;
import com.android.launcher3.reflection.research.p018c.C0947a;
import com.android.launcher3.reflection.research.p018c.C0948c;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.sensing.a */
public class C1213a extends BroadcastReceiver implements C0835a, C0947a {
    /* renamed from: cH */
    private boolean f459cH;
    /* renamed from: cI */
    private long f460cI;
    /* renamed from: cJ */
    private boolean f461cJ;
    /* renamed from: cK */
    private long f462cK;
    /* renamed from: cL */
    private final List<C0948c> f463cL;
    private final Context mContext;

    public C1213a(Context context) {
        boolean z;
        this.f460cI = 0;
        this.f462cK = 0;
        this.mContext = context;
        this.f463cL = new ArrayList();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        context.registerReceiver(this, intentFilter, null, new Handler());
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        this.f459cH = audioManager.isWiredHeadsetOn();
        if (!audioManager.isBluetoothA2dpOn()) {
            if (!audioManager.isBluetoothScoOn()) {
                z = false;
                this.f461cJ = z;
            }
        }
        z = true;
        this.f461cJ = z;
    }

    /* renamed from: a */
    public final C0947a mo9195a(C0948c c0948c) {
        this.f463cL.add(c0948c);
        return this;
    }

    /* renamed from: d */
    private void m4767d(C1214a c1214a) {
        for (C0948c a : this.f463cL) {
            a.mo8526a(c1214a.mo14091I());
        }
    }

    /* renamed from: b */
    private void m4766b(long j) {
        UsageEventSensor e = UsageEventSensor.m4757e(this.mContext);
        if (e != null) {
            e.mo14084d(j);
        }
    }

    /* renamed from: A */
    public final void mo9194A() {
        C1214a c1214a;
        if (this.f460cI > 0) {
            c1214a = new C1214a();
            c1214a.mo9269a(ReflectionEventType.HEADSET);
            c1214a.mo9270a(new C1219f().mo14098e(this.f460cI));
            c1214a.mo14095g(this.f459cH ? "headset_wired_in" : "headset_wired_out");
            c1214a.mo14096h("HeadsetStatusSensor");
            m4766b(c1214a.mo9264D().getTimestamp());
            m4767d(c1214a);
        }
        if (this.f462cK > 0) {
            c1214a = new C1214a();
            c1214a.mo9269a(ReflectionEventType.HEADSET);
            c1214a.mo9270a(new C1219f().mo14098e(this.f462cK));
            c1214a.mo14095g(this.f461cJ ? "headset_bluetooth_in" : "headset_bluetooth_out");
            c1214a.mo14096h("HeadsetStatusSensor");
            m4766b(c1214a.mo9264D().getTimestamp());
            m4767d(c1214a);
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.intent.action.HEADSET_PLUG") || isInitialStickyBroadcast()) {
            if (intent.getAction().equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED") && !isInitialStickyBroadcast()) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                if (intExtra == 0) {
                    mo14089d(false);
                } else if (intExtra == 2) {
                    mo14089d(true);
                    return;
                }
            }
            return;
        }
        switch (intent.getIntExtra("state", -1)) {
            case 0:
                mo14088c(false);
                break;
            case 1:
                mo14088c(true);
                return;
        }
    }

    /* renamed from: c */
    protected final void mo14088c(boolean z) {
        this.f459cH = z;
        this.f460cI = Calendar.getInstance().getTimeInMillis();
        mo9194A();
        m4765B();
    }

    /* renamed from: d */
    protected final void mo14089d(boolean z) {
        this.f461cJ = z;
        this.f462cK = Calendar.getInstance().getTimeInMillis();
        mo9194A();
        m4765B();
    }

    /* renamed from: B */
    private void m4765B() {
        ReflectionClient.getInstance(this.mContext).updatePredictionsNow("GEL");
        ReflectionClient.getInstance(this.mContext).updatePredictionsNow("OVERVIEW_GEL");
    }

    /* renamed from: h */
    public final void mo8519h() {
        this.mContext.unregisterReceiver(this);
    }

    protected C1213a() {
        this.f460cI = 0;
        this.f462cK = 0;
        this.f463cL = new ArrayList();
        this.mContext = null;
    }
}
