package com.google.android.apps.nexuslauncher.reflection.p010a;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.a.d */
public class C0791d {
    /* renamed from: aX */
    private static final C0791d f103aX = new C0791d("OVERVIEW_GEL");
    /* renamed from: aY */
    private static final C0791d f104aY = new C0791d("GEL");
    /* renamed from: aZ */
    public final String f105aZ;
    /* renamed from: ba */
    public final String f106ba;
    /* renamed from: bb */
    public final String f107bb;
    /* renamed from: bc */
    public final String f108bc;

    private C0791d(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_reflection_last_predictions");
        this.f105aZ = sb.toString();
        sb = new StringBuilder();
        sb.append(str);
        sb.append("_reflection_last_predictions_timestamp");
        this.f106ba = sb.toString();
        sb = new StringBuilder();
        sb.append(str);
        sb.append("_prediction_order");
        this.f107bb = sb.toString();
        sb = new StringBuilder();
        sb.append(str);
        sb.append("_prediction_order_by_rank");
        this.f108bc = sb.toString();
    }

    /* renamed from: d */
    public static C0791d m2561d(String str) {
        if ("OVERVIEW_GEL".equals(str)) {
            return f103aX;
        }
        return f104aY;
    }
}
