package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.util.Log;
import com.google.android.apps.miphone.aiai.matchmaker.api.ContentData;
import com.google.android.apps.miphone.aiai.matchmaker.api.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1965A;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1967D;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1968E;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1969F;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1971H;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1972I;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1973K;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1975b;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1988y;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1989z;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.x */
public final class C0936x {
    /* renamed from: yh */
    private static boolean f1176yh = false;

    /* renamed from: e */
    public static void m650e(String str) {
        if (f1176yh) {
            Log.d("AiAiSuggestUi", str);
        }
    }

    /* renamed from: f */
    public static void m651f(String str) {
        if (f1176yh) {
            Log.v("AiAiSuggestUi", str);
        }
    }

    /* renamed from: g */
    public static void m652g(String str) {
        Log.i("AiAiSuggestUi", str);
    }

    /* renamed from: a */
    public static String m645a(ContentData contentData) {
        C1976c c1976c = contentData.f994uq;
        if (c1976c != null) {
            if (c1976c.f3174uB != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Found ");
                stringBuilder.append(c1976c.f3174uB.length);
                stringBuilder.append(" contents:");
                C1975b[] c1975bArr = c1976c.f3174uB;
                int length = c1975bArr.length;
                int i = 0;
                int i2 = i;
                while (i < length) {
                    C1975b c1975b = c1975bArr[i];
                    stringBuilder.append("__Content Group Index: ");
                    stringBuilder.append(i2);
                    stringBuilder.append("; Found ");
                    stringBuilder.append(c1975b.f3170uy.length);
                    stringBuilder.append(" rects:\n");
                    C1965A[] c1965aArr = c1975b.f3170uy;
                    int length2 = c1965aArr.length;
                    int i3 = 0;
                    int i4 = i3;
                    while (i3 < length2) {
                        C1965A c1965a = c1965aArr[i3];
                        stringBuilder.append("____Rect #");
                        stringBuilder.append(i4);
                        stringBuilder.append(":");
                        C1973K c1973k = c1965a.f3118wb;
                        stringBuilder.append('(');
                        stringBuilder.append(c1973k.left);
                        stringBuilder.append(',');
                        stringBuilder.append(c1973k.top);
                        stringBuilder.append(',');
                        stringBuilder.append(c1973k.width);
                        stringBuilder.append(',');
                        stringBuilder.append(c1973k.height);
                        stringBuilder.append(") -- \n_____");
                        stringBuilder.append(c1965a.f3119wc);
                        stringBuilder.append("\n");
                        i4++;
                        i3++;
                    }
                    i2++;
                    i++;
                }
                return stringBuilder.toString();
            }
        }
        return "";
    }

    /* renamed from: a */
    public static String m646a(EntitiesData entitiesData) {
        C1967D c1967d = entitiesData.f995ur;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-------------------------------------------\n");
        stringBuilder.append("id = ");
        stringBuilder.append(c1967d.f3124id);
        stringBuilder.append(10);
        stringBuilder.append("success = ");
        stringBuilder.append(c1967d.f3128wh);
        stringBuilder.append(10);
        stringBuilder.append("entities = ");
        stringBuilder.append(c1967d.f3129wi.length);
        stringBuilder.append(10);
        for (C1968E c : c1967d.f3129wi) {
            stringBuilder.append(C0936x.m649c(c));
        }
        stringBuilder.append("-------------------------------------------\n");
        return stringBuilder.toString();
    }

    /* renamed from: c */
    public static String m649c(C1968E c1968e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("_id = ");
        stringBuilder.append(c1968e.f3132id);
        stringBuilder.append(10);
        stringBuilder.append("_query = ");
        stringBuilder.append(c1968e.f3139wn);
        stringBuilder.append(10);
        stringBuilder.append("_contentGroupIndex = ");
        stringBuilder.append(c1968e.f3136wg);
        stringBuilder.append(10);
        stringBuilder.append("_selectionIndex = ");
        stringBuilder.append(c1968e.f3144ws);
        stringBuilder.append(10);
        if (c1968e.f3137wl != null) {
            for (C1989z c1989z : c1968e.f3137wl) {
                stringBuilder.append("__id = ");
                stringBuilder.append(c1989z.f3266id);
                stringBuilder.append(10);
                stringBuilder.append("__displayName = ");
                stringBuilder.append(c1989z.f3268vR);
                stringBuilder.append(10);
                C0936x.m648a(stringBuilder, c1989z.f3269vX);
                for (C1988y a : c1989z.f3270vY) {
                    C0936x.m648a(stringBuilder, a);
                }
            }
        }
        if (c1968e.f3138wm != null) {
            for (C1969F c1969f : c1968e.f3138wm) {
                stringBuilder.append("__view = ");
                for (C1965A c1965a : c1969f.f3150wx) {
                    C1973K c1973k = c1965a.f3118wb;
                    stringBuilder.append('(');
                    stringBuilder.append(c1973k.left);
                    stringBuilder.append(',');
                    stringBuilder.append(c1973k.top);
                    stringBuilder.append(',');
                    stringBuilder.append(c1973k.width);
                    stringBuilder.append(',');
                    stringBuilder.append(c1973k.height);
                    stringBuilder.append(')');
                }
            }
            stringBuilder.append(10);
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    static void m647a(String str, Throwable th) {
        Log.e("AiAiSuggestUi", str, th);
    }

    /* renamed from: h */
    static void m653h(String str) {
        C0936x.m647a(str, null);
    }

    /* renamed from: a */
    private static void m648a(StringBuilder stringBuilder, C1988y c1988y) {
        if (f1176yh) {
            stringBuilder.append("___id = ");
            stringBuilder.append(c1988y.f3258id);
            stringBuilder.append(10);
            stringBuilder.append("___displayName = ");
            stringBuilder.append(c1988y.f3260vR);
            stringBuilder.append(10);
            stringBuilder.append("___fullDisplayName = ");
            stringBuilder.append(c1988y.f3262vT);
            stringBuilder.append(10);
            C1971H c1971h = c1988y.f3264vV;
            if (c1971h != null) {
                stringBuilder.append("___action = ");
                stringBuilder.append(c1971h.f3155wC);
                stringBuilder.append(10);
                stringBuilder.append("___uri = ");
                stringBuilder.append(c1971h.f3156wD);
                stringBuilder.append(10);
                stringBuilder.append("___package = ");
                stringBuilder.append(c1971h.packageName);
                stringBuilder.append(10);
                stringBuilder.append("___class = ");
                stringBuilder.append(c1971h.className);
                stringBuilder.append(10);
                stringBuilder.append("___mime = ");
                stringBuilder.append(c1971h.mimeType);
                stringBuilder.append(10);
                stringBuilder.append("___flags = ");
                stringBuilder.append(c1971h.flags);
                stringBuilder.append(10);
                for (C1972I c1972i : c1971h.f3154wB) {
                    stringBuilder.append("____");
                    stringBuilder.append(c1972i.name);
                    stringBuilder.append(" = ");
                    stringBuilder.append(c1972i.f3158wF);
                    stringBuilder.append('|');
                    stringBuilder.append(c1972i.f3159wG);
                    stringBuilder.append('|');
                    stringBuilder.append(c1972i.f3160wH);
                    stringBuilder.append(10);
                }
            }
        }
    }
}
