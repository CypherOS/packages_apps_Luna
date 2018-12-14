package com.android.launcher3.reflection.p011b;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.os.Process;
import android.os.UserHandle;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.shortcuts.DeepShortcutManager;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.InstantAppResolver;
import com.android.launcher3.util.PackageManagerHelper;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.reflection.C0832f;
import com.android.launcher3.reflection.research.predictor.C0968k.C0967a;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

/* renamed from: com.android.launcher3.reflection.b.b */
public class C0794b {
    /* renamed from: ac */
    private final UserManagerCompat f110ac;
    /* renamed from: be */
    private final DeepShortcutManager f111be;
    /* renamed from: bf */
    private final Map<ComponentKey, Boolean> f112bf;
    private final InstantAppResolver mInstantAppResolver;
    /* renamed from: v */
    private final LauncherAppsCompat f113v;

    protected C0794b() {
        this.f110ac = null;
        this.f113v = null;
        this.f111be = null;
        this.mInstantAppResolver = null;
        this.f112bf = null;
    }

    public C0794b(Context context) {
        this.f110ac = UserManagerCompat.getInstance(context);
        this.f113v = LauncherAppsCompat.getInstance(context);
        this.f111be = DeepShortcutManager.getInstance(context);
        this.mInstantAppResolver = InstantAppResolver.newInstance(context);
        this.f112bf = Collections.synchronizedMap(new HashMap());
    }

    /* renamed from: a */
    public final void mo8485a(List<C0967a> list, List<C0967a> list2) {
        Set n = mo8488n();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C0967a c0967a = (C0967a) it.next();
            if (!mo8487a(c0967a.f363id, n)) {
                if (list2 != null) {
                    list2.add(c0967a);
                }
                it.remove();
            }
        }
    }

    /* renamed from: n */
    public final Set<String> mo8488n() {
        List<ApplicationInfo> instantApps = this.mInstantAppResolver.getInstantApps();
        HashSet hashSet = new HashSet();
        for (ApplicationInfo applicationInfo : instantApps) {
            hashSet.add(applicationInfo.packageName);
        }
        return hashSet;
    }

    /* renamed from: a */
    public final boolean mo8487a(String str, Set<String> set) {
        Matcher matcher = C0832f.f209ai.matcher(str);
        if (!matcher.find()) {
            return false;
        }
        UserHandle userForSerialNumber;
        String group = matcher.group(1);
        String group2 = matcher.group(2);
        str = matcher.group(4);
        if (str != null) {
            try {
                userForSerialNumber = this.f110ac.getUserForSerialNumber(Long.parseLong(str));
                if (userForSerialNumber == null) {
                    return false;
                }
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        userForSerialNumber = Process.myUserHandle();
        if (group.startsWith("_")) {
            List queryForFullDetails = this.f111be.queryForFullDetails(group.substring(1), Collections.singletonList(group2), userForSerialNumber);
            return !queryForFullDetails.isEmpty() && ((ShortcutInfoCompat) queryForFullDetails.get(0)).isEnabled();
        } else if (group2.equals("@instantapp")) {
            return set.contains(group);
        } else {
            ComponentName componentName = new ComponentName(group, group2);
            ComponentKey componentKey = new ComponentKey(componentName, userForSerialNumber);
            Boolean bool = (Boolean) this.f112bf.get(componentKey);
            if (bool == null) {
                Boolean valueOf = Boolean.valueOf(false);
                bool = valueOf;
                for (LauncherActivityInfo launcherActivityInfo : this.f113v.getActivityList(group, userForSerialNumber)) {
                    mo8483a(launcherActivityInfo);
                    if (componentName.equals(launcherActivityInfo.getComponentName())) {
                        bool = Boolean.valueOf(PackageManagerHelper.isAppSuspended(launcherActivityInfo.getApplicationInfo()) ^ true);
                    }
                }
            }
            this.f112bf.put(componentKey, bool);
            return bool.booleanValue();
        }
    }

    /* renamed from: a */
    public final void mo8483a(LauncherActivityInfo launcherActivityInfo) {
        this.f112bf.put(new ComponentKey(launcherActivityInfo.getComponentName(), launcherActivityInfo.getUser()), Boolean.valueOf(PackageManagerHelper.isAppSuspended(launcherActivityInfo.getApplicationInfo()) ^ true));
    }

    /* renamed from: a */
    public final void mo8484a(String str, UserHandle userHandle) {
        Preconditions.assertNonUiThread();
        Iterator it = this.f112bf.keySet().iterator();
        while (it.hasNext()) {
            ComponentKey componentKey = (ComponentKey) it.next();
            if (str.equals(componentKey.componentName.getPackageName()) && userHandle.equals(componentKey.user)) {
                it.remove();
            }
        }
    }

    /* renamed from: a */
    public final void mo8486a(String[] strArr, UserHandle userHandle) {
        for (String a : strArr) {
            mo8484a(a, userHandle);
        }
    }
}
