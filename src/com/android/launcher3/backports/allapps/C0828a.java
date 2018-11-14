package com.android.launcher3.backports.allapps;

import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.a */
public class C0828a {
    /* renamed from: AG */
    public final long f1195AG;
    /* renamed from: AH */
    public final String f1196AH;
    /* renamed from: AI */
    public final String f1197AI;
    /* renamed from: AJ */
    public final CharSequence f1198AJ;
    /* renamed from: AK */
    public final ShortcutInfoCompat f1199AK;
    /* renamed from: AL */
    public final ShortcutInfo f1200AL;
    /* renamed from: AM */
    public final long f1201AM;
    /* renamed from: Av */
    public boolean f1202Av = false;
    public final CharSequence contentDescription;
    /* renamed from: id */
    public final String f1203id;
    public final String shortcutId;

    public C0828a(String str, String str2, long j, String str3, String str4, CharSequence charSequence, ShortcutInfoCompat shortcutInfoCompat, ShortcutInfo shortcutInfo, long j2) {
        this.f1203id = str;
        this.shortcutId = str2;
        this.f1195AG = j;
        this.f1196AH = str3;
        this.f1197AI = str4;
        this.f1198AJ = charSequence;
        this.f1199AK = shortcutInfoCompat;
        this.f1200AL = shortcutInfo;
        this.f1201AM = j2;
        this.contentDescription = shortcutInfo.contentDescription;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append(this.f1203id);
        stringBuilder.append(",");
        stringBuilder.append(this.f1199AK.mShortcutInfo.getShortLabel());
        stringBuilder.append(",");
        stringBuilder.append(this.f1201AM);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
