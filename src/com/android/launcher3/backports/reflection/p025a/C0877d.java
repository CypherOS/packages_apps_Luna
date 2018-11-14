package com.android.launcher3.backports.reflection.p025a;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.a.d */
public class C0877d {
    /* renamed from: Gl */
    private static final C0877d f1320Gl = new C0877d("OVERVIEW_GEL");
    /* renamed from: Gm */
    private static final C0877d f1321Gm = new C0877d("GEL");
    /* renamed from: Gn */
    public final String f1322Gn;
    /* renamed from: Go */
    public final String f1323Go;
    /* renamed from: Gp */
    public final String f1324Gp;
    /* renamed from: Gq */
    public final String f1325Gq;

    private C0877d(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_reflection_last_predictions");
        this.f1322Gn = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_reflection_last_predictions_timestamp");
        this.f1323Go = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_prediction_order");
        this.f1324Gp = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_prediction_order_by_rank");
        this.f1325Gq = stringBuilder.toString();
    }

    /* renamed from: r */
    public static C0877d m746r(String str) {
        if ("OVERVIEW_GEL".equals(str)) {
            return f1320Gl;
        }
        return f1321Gm;
    }
}
