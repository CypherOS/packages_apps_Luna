package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.android.launcher3.reflection.research.b.f */
public abstract class C0945f {
    /* renamed from: dA */
    public C0946l f281dA;
    /* renamed from: dB */
    protected Map<String, Boolean> f282dB = new HashMap();

    /* renamed from: T */
    public abstract int mo9182T();

    /* renamed from: U */
    public abstract C0945f clone();

    /* renamed from: a */
    protected abstract C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent);

    /* renamed from: a */
    public void mo9186a(DataInputStream dataInputStream) throws IOException {
    }

    /* renamed from: a */
    public void mo9187a(DataOutputStream dataOutputStream) throws IOException {
    }

    /* renamed from: g */
    public void mo9191g(List<String> list) {
    }

    public abstract String getFeatureName();

    public C0945f() {
        this.f282dB.put(getFeatureName(), Boolean.valueOf(true));
    }

    /* renamed from: b */
    public final C0951e mo9188b(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        C0951e a = mo9185a(c0938a, reflectionEvent);
        boolean z = false;
        loop0:
        for (int i = 0; i < a.rowCount; i++) {
            for (int i2 = 0; i2 < a.columnCount; i2++) {
                if (Math.abs(a.f322eR[(a.columnCount * i) + i2]) > 1.0E-9d) {
                    break loop0;
                }
            }
        }
        z = true;
        mo9190e(z);
        return a;
    }

    /* renamed from: b */
    public static String m2981b(C0945f c0945f) {
        if ((c0945f instanceof C1236a) && !(c0945f instanceof C1276b)) {
            return "app_launch_extractor";
        }
        if (c0945f instanceof C1276b) {
            return "app_usage_extractor";
        }
        if (c0945f instanceof C1277d) {
            return "dl_usage_extractor";
        }
        if (c0945f instanceof C1237c) {
            return "day_extractor";
        }
        if (c0945f instanceof C1238e) {
            return "feature_aggregator";
        }
        if (c0945f instanceof C1239g) {
            return "headset_extractor";
        }
        if (c0945f instanceof C1240h) {
            return "hour_extractor";
        }
        if (c0945f instanceof C1242j) {
            return "place_extractor";
        }
        if (c0945f instanceof C1243k) {
            return "public_place_extractor";
        }
        if (c0945f instanceof C1241i) {
            return "lat_lng_extractor";
        }
        return null;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: j */
    public static C0945f m2982j(String str) {
        int i;
        switch (str.hashCode()) {
            case -1759465079:
                if (str.equals("hour_extractor")) {
                    i = 5;
                    break;
                }
            case -1462077706:
                if (str.equals("app_launch_extractor")) {
                    i = 0;
                    break;
                }
            case -600286442:
                if (str.equals("public_place_extractor")) {
                    i = 8;
                    break;
                }
            case -600259761:
                if (str.equals("dl_usage_extractor")) {
                    i = 2;
                    break;
                }
            case 231045441:
                if (str.equals("day_extractor")) {
                    i = 3;
                    break;
                }
            case 1128652520:
                if (str.equals("app_usage_extractor")) {
                    i = 1;
                    break;
                }
            case 1209446567:
                if (str.equals("headset_extractor")) {
                    i = 6;
                    break;
                }
            case 1230062828:
                if (str.equals("place_extractor")) {
                    i = 7;
                    break;
                }
            case 1395605682:
                if (str.equals("feature_aggregator")) {
                    i = 4;
                    break;
                }
            case 1987523306:
                if (str.equals("lat_lng_extractor")) {
                    i = 9;
                    break;
                }
        }
        i = -1;
        switch (i) {
            case 0:
                return new C1236a();
            case 1:
                return new C1276b();
            case 2:
                return new C1277d();
            case 3:
                return new C1237c();
            case 4:
                return new C1238e();
            case 5:
                return new C1240h();
            case 6:
                return new C1239g();
            case 7:
                return new C1242j();
            case 8:
                return new C1243k();
            case 9:
                return new C1241i();
            default:
                return null;
        }
    }

    /* renamed from: e */
    protected void mo9190e(boolean z) {
        this.f282dB.put(getFeatureName(), Boolean.valueOf(z));
    }

    /* renamed from: Y */
    public final Map<String, Boolean> mo9184Y() {
        return this.f282dB;
    }
}
