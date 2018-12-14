package com.android.launcher3.reflection;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.os.UserHandle;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.reflection.C0836h.C0835a;
import com.android.launcher3.reflection.p010a.C0789b;
import com.android.launcher3.reflection.p011b.C0794b;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.d */
public class C1212d implements OnAppsChangedCallbackCompat, C0835a {
    /* renamed from: ac */
    private final UserManagerCompat f436ac;
    /* renamed from: ad */
    private final C0789b f437ad;
    /* renamed from: ae */
    private final C0794b f438ae;
    private final C0836h mServiceHandler;
    /* renamed from: v */
    private final LauncherAppsCompat f439v;

    public void onShortcutsChanged(String str, List<ShortcutInfoCompat> list, UserHandle userHandle) {
    }

    public C1212d(Context context, C0836h c0836h, C0794b c0794b, C0789b c0789b) {
        this.f436ac = UserManagerCompat.getInstance(context);
        this.f439v = LauncherAppsCompat.getInstance(context);
        this.mServiceHandler = c0836h;
        this.f438ae = c0794b;
        this.f437ad = c0789b;
    }

    public final void initialize() {
        Preconditions.assertNonUiThread();
        for (UserHandle activityList : this.f436ac.getUserProfiles()) {
            m4739a(this.f439v.getActivityList(null, activityList));
        }
        this.f439v.addOnAppsChangedCallback(this);
    }

    public void onPackageAdded(String str, UserHandle userHandle) {
        m4739a(this.f439v.getActivityList(str, userHandle));
    }

    /* renamed from: a */
    private void m4739a(List<LauncherActivityInfo> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            LauncherActivityInfo launcherActivityInfo = (LauncherActivityInfo) list.get(size);
            this.f437ad.mo8478a(1, launcherActivityInfo);
            this.f438ae.mo8483a(launcherActivityInfo);
        }
    }

    public void onPackageRemoved(String str, UserHandle userHandle) {
        this.f437ad.mo8479a(0, str, userHandle);
        this.f438ae.mo8484a(str, userHandle);
        C0836h c0836h = this.mServiceHandler;
        this.f436ac.getSerialNumberForUser(userHandle);
        Preconditions.assertNonUiThread();
        c0836h.mEngine.mo8513b("system", String.format("%s/", new Object[]{str}));
        c0836h.mEngine.mo8513b("system", String.format("%s%s/", new Object[]{"_", str}));
    }

    public void onPackageChanged(String str, UserHandle userHandle) {
        this.f437ad.mo8479a(-1, str, userHandle);
        this.f438ae.mo8484a(str, userHandle);
    }

    public void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z) {
        this.f437ad.mo8480a(-1, strArr, userHandle);
        this.f438ae.mo8486a(strArr, userHandle);
    }

    public void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z) {
        this.f437ad.mo8480a(0, strArr, userHandle);
        this.f438ae.mo8486a(strArr, userHandle);
    }

    public void onPackagesSuspended(String[] strArr, UserHandle userHandle) {
        this.f437ad.mo8480a(0, strArr, userHandle);
        this.f438ae.mo8486a(strArr, userHandle);
    }

    public void onPackagesUnsuspended(String[] strArr, UserHandle userHandle) {
        this.f437ad.mo8480a(-1, strArr, userHandle);
        this.f438ae.mo8486a(strArr, userHandle);
    }

    /* renamed from: h */
    public final void mo8519h() {
        this.f439v.removeOnAppsChangedCallback(this);
    }
}
