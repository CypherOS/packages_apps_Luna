package com.google.android.apps.nexuslauncher.reflection.p011b;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import com.google.android.apps.nexuslauncher.reflection.C0832f;
import com.google.android.apps.nexuslauncher.reflection.p010a.C0789b;
import com.google.research.reflection.predictor.C0968k.C0967a;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.b.d */
public class C0797d {
    /* renamed from: ad */
    private final C0789b f122ad;
    /* renamed from: bk */
    private final HashSet<String> f123bk = new HashSet();

    public C0797d(C0789b c0789b) {
        this.f122ad = c0789b;
    }

    /* renamed from: a */
    public final void mo8492a(List<C0967a> list, List<C0967a> list2) {
        C0967a c0967a;
        float f = 1.0f;
        if (list.size() > 0) {
            f = ((C0967a) list.get(list.size() - 1)).f360ca - 1.0f;
        }
        C0789b c0789b = this.f122ad;
        ArrayList arrayList = new ArrayList(C0789b.f99aT.length);
        for (int i = 0; i < C0789b.f99aT.length; i++) {
            if (c0789b.f100aU[i].state == -1) {
                ActivityInfo activityInfo = null;
                Intent launchIntentForPackage = c0789b.mPackageManager.getLaunchIntentForPackage(C0789b.f99aT[i]);
                if (launchIntentForPackage != null) {
                    ResolveInfo resolveActivity = c0789b.mPackageManager.resolveActivity(launchIntentForPackage, 0);
                    if (resolveActivity != null) {
                        activityInfo = resolveActivity.activityInfo;
                    }
                }
                if (activityInfo != null) {
                    String str = activityInfo.name;
                    if (str.startsWith(".")) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(activityInfo.packageName);
                        sb.append(str);
                        str = sb.toString();
                    }
                    c0789b.f100aU[i].state = 1;
                    c0789b.f100aU[i].className = str;
                } else {
                    c0789b.f100aU[i].state = 0;
                    c0789b.f100aU[i].className = BuildConfig.FLAVOR;
                }
            }
            if (c0789b.f100aU[i].state == 1) {
                arrayList.add(new C0967a(C0832f.m2647a(new ComponentName(c0789b.f100aU[i].packageName, c0789b.f100aU[i].className)), f - ((float) arrayList.size()), "Reflection.AppInfoHelper"));
            }
        }
        this.f123bk.clear();
        for (C0967a c0967a2 : list) {
            this.f123bk.add(c0967a2.f363id);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            c0967a2 = (C0967a) it.next();
            if (!this.f123bk.contains(c0967a2.f363id)) {
                list.add(c0967a2);
                if (list2 != null) {
                    list2.add(c0967a2);
                }
            }
        }
    }
}
