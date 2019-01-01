package com.google.research.reflection.p017b;

import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.signal.ReflectionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.research.reflection.b.f */
public abstract class C0949f {
    /* renamed from: dA */
    public C0950l f281dA;
    /* renamed from: dB */
    protected Map<String, Boolean> f282dB = new HashMap();

    /* renamed from: T */
    public abstract int mo9200T();

    /* renamed from: U */
    public abstract C0949f clone();

    /* renamed from: a */
    protected abstract C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent);

    /* renamed from: a */
    public void mo9204a(DataInputStream dataInputStream) throws IOException {
    }

    /* renamed from: a */
    public void mo9205a(DataOutputStream dataOutputStream) throws IOException {
    }

    /* renamed from: g */
    public void mo9209g(List<String> list) {
    }

    public abstract String getFeatureName();

    public C0949f() {
        this.f282dB.put(getFeatureName(), Boolean.valueOf(true));
    }

    /* renamed from: b */
    public final C0955e mo9206b(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        C0955e a = mo9203a(c0942a, reflectionEvent);
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
        mo9208e(z);
        return a;
    }

    /* renamed from: b */
    public static String m2996b(C0949f c0949f) {
        if ((c0949f instanceof C1240a) && !(c0949f instanceof C1281b)) {
            return "app_launch_extractor";
        }
        if (c0949f instanceof C1281b) {
            return "app_usage_extractor";
        }
        if (c0949f instanceof C1282d) {
            return "dl_usage_extractor";
        }
        if (c0949f instanceof C1241c) {
            return "day_extractor";
        }
        if (c0949f instanceof C1242e) {
            return "feature_aggregator";
        }
        if (c0949f instanceof C1243g) {
            return "headset_extractor";
        }
        if (c0949f instanceof C1244h) {
            return "hour_extractor";
        }
        if (c0949f instanceof C1246j) {
            return "place_extractor";
        }
        if (c0949f instanceof C1247k) {
            return "public_place_extractor";
        }
        if (c0949f instanceof C1245i) {
            return "lat_lng_extractor";
        }
        return null;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: j */
    public static C0949f m2997j(String str) {
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
                return new C1240a();
            case 1:
                return new C1281b();
            case 2:
                return new C1282d();
            case 3:
                return new C1241c();
            case 4:
                return new C1242e();
            case 5:
                return new C1244h();
            case 6:
                return new C1243g();
            case 7:
                return new C1246j();
            case 8:
                return new C1247k();
            case 9:
                return new C1245i();
            default:
                return null;
        }
    }

    /* renamed from: e */
    protected void mo9208e(boolean z) {
        this.f282dB.put(getFeatureName(), Boolean.valueOf(z));
    }

    /* renamed from: Y */
    public final Map<String, Boolean> mo9202Y() {
        return this.f282dB;
    }
}
