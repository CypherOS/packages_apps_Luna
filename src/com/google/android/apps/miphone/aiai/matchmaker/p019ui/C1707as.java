package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.as */
final /* synthetic */ class C1707as implements C0897Y {
    /* renamed from: zK */
    private final List f2394zK;
    /* renamed from: zL */
    private final CountDownLatch f2395zL;

    C1707as(List list, CountDownLatch countDownLatch) {
        this.f2394zK = list;
        this.f2395zL = countDownLatch;
    }

    /* renamed from: a */
    public final void mo3336a(C1976c c1976c, EntitiesData entitiesData) {
        List list = this.f2394zK;
        CountDownLatch countDownLatch = this.f2395zL;
        if (entitiesData.f995ur.f3128wh) {
            list.add(entitiesData);
            C0936x.m650e("-Obtained new entities");
        } else {
            C0936x.m653h("Unable to obtain new entities");
        }
        countDownLatch.countDown();
    }
}
