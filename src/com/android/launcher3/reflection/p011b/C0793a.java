package com.google.android.apps.nexuslauncher.reflection.p011b;

import android.content.Context;
import com.google.research.reflection.predictor.C0968k.C0967a;
import java.util.List;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.b.a */
public class C0793a {
    /* renamed from: bd */
    public int f109bd;
    public Context mContext;

    public C0793a(Context context) {
        this.mContext = context;
    }

    /* renamed from: a */
    public final int mo8481a(List<C0967a> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (((C0967a) list.get(i)).f363id.startsWith(str)) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: e */
    public final boolean mo8482e(List<C0967a> list) {
        for (int i = 0; i < Math.min(list.size(), this.f109bd); i++) {
            if (((C0967a) list.get(i)).f363id.endsWith("@instantapp")) {
                return true;
            }
        }
        return false;
    }
}
