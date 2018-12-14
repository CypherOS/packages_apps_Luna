package com.google.android.apps.nexuslauncher.reflection.p010a;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.UserHandle;
import java.util.HashMap;
import java.util.Map;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.a.b */
public class C0789b {
    /* renamed from: aT */
    public static final String[] f99aT = new String[]{"com.google.android.apps.photos", "com.google.android.apps.maps", "com.google.android.gm", "com.google.android.deskclock", "com.android.settings", "com.whatsapp", "com.facebook.katana", "com.facebook.orca", "com.google.android.youtube", "com.yodo1.crossyroad", "com.spotify.music", "com.android.chrome", "com.instagram.android", "com.skype.raider", "com.snapchat.android", "com.viber.voip", "com.twitter.android", "com.android.phone", "com.google.android.music", "com.google.android.calendar", "com.google.android.apps.genie.geniewidget", "com.netflix.mediaclient", "bbc.iplayer.android", "com.google.android.videos", "com.amazon.mShop.android.shopping", "com.microsoft.office.word", "com.google.android.apps.docs", "com.google.android.keep", "com.google.android.apps.plus", "com.google.android.talk", "com.pandora.android", "com.sdu.didi.psnger", "com.amazon.mp3", "com.ubercab", "com.olacabs.customer"};
    /* renamed from: aU */
    public final C0788a[] f100aU = new C0788a[f99aT.length];
    /* renamed from: aV */
    public final Map<String, C0788a> f101aV = new HashMap();
    /* renamed from: aW */
    private final UserHandle f102aW;
    public final PackageManager mPackageManager;

    public C0789b(Context context) {
        this.mPackageManager = context.getPackageManager();
        this.f102aW = Process.myUserHandle();
        for (int i = 0; i < f99aT.length; i++) {
            C0788a c0788a = new C0788a(f99aT[i], BuildConfig.FLAVOR, -1);
            this.f100aU[i] = c0788a;
            this.f101aV.put(f99aT[i], c0788a);
        }
    }

    /* renamed from: a */
    public final void mo8480a(int i, String[] strArr, UserHandle userHandle) {
        for (String a : strArr) {
            mo8479a(i, a, userHandle);
        }
    }

    /* renamed from: a */
    public final void mo8479a(int i, String str, UserHandle userHandle) {
        if (this.f102aW.equals(userHandle)) {
            C0788a c0788a = (C0788a) this.f101aV.get(str);
            if (c0788a != null) {
                c0788a.state = i;
            }
        }
    }

    /* renamed from: a */
    public final synchronized void mo8478a(int i, LauncherActivityInfo launcherActivityInfo) {
        if (this.f102aW.equals(launcherActivityInfo.getUser())) {
            C0788a c0788a = (C0788a) this.f101aV.get(launcherActivityInfo.getComponentName().getPackageName());
            if (c0788a != null) {
                c0788a.state = i;
                c0788a.className = launcherActivityInfo.getComponentName().getClassName();
            }
        }
    }
}
