package com.android.launcher3.predictions;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.i */
class C0765i {
    /* renamed from: ts */
    long f1002ts;
    /* renamed from: xS */
    boolean f1003xS;
    /* renamed from: xT */
    String f1004xT;
    /* renamed from: xU */
    int f1005xU;
    /* renamed from: xV */
    String f1006xV;

    private C0765i() {
    }

    C0765i(byte b) {
        this();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("isClick:");
        stringBuilder.append(this.f1003xS);
        stringBuilder.append(" clickedId:");
        stringBuilder.append(this.f1004xT);
        stringBuilder.append(" clickedPos:");
        stringBuilder.append(this.f1005xU);
        stringBuilder.append(" top:");
        stringBuilder.append(this.f1006xV);
        return stringBuilder.toString();
    }
}
