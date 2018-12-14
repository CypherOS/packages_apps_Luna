package com.google.android.apps.nexuslauncher.reflection.p011b;

import android.content.ComponentName;
import android.content.Context;
import com.google.android.apps.nexuslauncher.reflection.p010a.C0792e;
import com.google.research.reflection.predictor.C0968k.C0967a;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.b.c */
public class C0796c {
    /* renamed from: bg */
    private static int f117bg = 1;
    /* renamed from: bh */
    private static long f118bh = 21600000;
    /* renamed from: bi */
    private static int f119bi = 10;
    /* renamed from: bj */
    public final LinkedList<C0795a> f120bj = new LinkedList();
    /* renamed from: bk */
    private final HashSet<String> f121bk = new HashSet();
    private final Context mContext;

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.b.c$a */
    private class C0795a {
        /* renamed from: bl */
        public String f114bl;
        /* renamed from: bm */
        public int f115bm = 0;
        public long time;

        public C0795a(ComponentName componentName, long j, long j2) {
            this.f114bl = C0792e.m2563a(componentName, j, C0796c.this.mContext);
            this.time = j2;
        }
    }

    public C0796c(Context context) {
        this.mContext = context;
    }

    /* renamed from: a */
    public final void mo8489a(List<C0967a> list, List<C0967a> list2) {
        mo8490o();
        float f = 1.0f;
        if (list.size() > 0) {
            f = 1.0f + ((C0967a) list.get(0)).f360ca;
        }
        this.f121bk.clear();
        LinkedList linkedList = new LinkedList();
        int i = 0;
        for (int max = Math.max(this.f120bj.size() - f117bg, 0); max < this.f120bj.size(); max++) {
            this.f121bk.add(((C0795a) this.f120bj.get(max)).f114bl);
            C0967a c0967a = new C0967a(((C0795a) this.f120bj.get(max)).f114bl, ((float) i) + f, "Reflection.NewInstFilt");
            linkedList.add(0, c0967a);
            if (list2 != null) {
                list2.add(0, c0967a);
            }
            i++;
        }
        for (C0967a c0967a2 : list) {
            if (!this.f121bk.contains(c0967a2.f363id)) {
                linkedList.add(c0967a2);
            }
        }
        list.clear();
        list.addAll(linkedList);
    }

    /* renamed from: o */
    public final void mo8490o() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        while (this.f120bj.size() > 0) {
            if (timeInMillis > ((C0795a) this.f120bj.peek()).time + f118bh || ((C0795a) this.f120bj.peek()).f115bm > f119bi) {
                this.f120bj.removeFirst();
            } else {
                return;
            }
        }
    }

    void setMaxNumPromotion(int i) {
        f117bg = i;
    }
}
