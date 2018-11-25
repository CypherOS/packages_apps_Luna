package com.android.launcher3.predictions;

import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;

public class C0759a {
    /* renamed from: sc */
    public final String f977sc;
    public final String shortcutId;
    /* renamed from: xs */
    public final long f978xs;
    /* renamed from: xt */
    public final String f979xt;
    /* renamed from: xu */
    public final String f980xu;
    /* renamed from: xv */
    public final ShortcutInfoCompat f981xv;
    /* renamed from: xw */
    public final ShortcutInfo f982xw;

    public C0759a(String str, String str2, long j, String str3, String str4, ShortcutInfoCompat shortcutInfoCompat, ShortcutInfo shortcutInfo) {
        this.f977sc = str;
        this.shortcutId = str2;
        this.f978xs = j;
        this.f979xt = str3;
        this.f980xu = str4;
        this.f981xv = shortcutInfoCompat;
        this.f982xw = shortcutInfo;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append(this.f977sc);
        stringBuilder.append(",");
        stringBuilder.append(this.f981xv.mShortcutInfo.getShortLabel());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
