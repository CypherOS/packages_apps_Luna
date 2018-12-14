package com.android.launcher3.reflection.p011b;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.UserHandle;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.reflection.C0832f;
import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/* renamed from: com.android.launcher3.reflection.b.f */
public class C0799f {
    /* renamed from: ac */
    private final UserManagerCompat f125ac;

    public C0799f(Context context) {
        this.f125ac = UserManagerCompat.getInstance(context);
    }

    /* renamed from: a */
    public final void mo8494a(List<C0967a> list, List<C0967a> list2) {
        if (VERSION.SDK_INT >= 24) {
            HashMap hashMap = new HashMap();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                C0967a c0967a = (C0967a) it.next();
                if (m2589a(c0967a.f363id, (Map) hashMap)) {
                    if (list2 != null) {
                        list2.add(c0967a);
                    }
                    it.remove();
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m2589a(String str, Map<String, Boolean> map) {
        Matcher matcher = C0832f.f209ai.matcher(str);
        boolean z = false;
        if (matcher.find()) {
            str = matcher.group(4);
            if (str != null) {
                if (map.containsKey(str)) {
                    return ((Boolean) map.get(str)).booleanValue();
                }
                try {
                    UserHandle userForSerialNumber = this.f125ac.getUserForSerialNumber(Long.parseLong(str));
                    if (userForSerialNumber == null) {
                        map.put(str, Boolean.valueOf(false));
                        return false;
                    }
                    if (!Process.myUserHandle().equals(userForSerialNumber) && this.f125ac.isQuietModeEnabled(userForSerialNumber)) {
                        z = true;
                    }
                    map.put(str, Boolean.valueOf(z));
                    return z;
                } catch (NumberFormatException unused) {
                    map.put(str, Boolean.valueOf(false));
                    return false;
                }
            }
        }
        return false;
    }
}
