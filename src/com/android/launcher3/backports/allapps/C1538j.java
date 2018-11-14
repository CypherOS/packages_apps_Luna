package com.android.launcher3.backports.allapps;

import android.os.Message;
import android.os.UserHandle;
import com.android.launcher3.compat.LauncherAppsCompat.OnAppsChangedCallbackCompat;
import java.util.List;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.j */
class C1538j implements OnAppsChangedCallbackCompat {
    /* renamed from: Bg */
    final /* synthetic */ C0829d f2458Bg;

    private C1538j(C0829d c0829d) {
        this.f2458Bg = c0829d;
    }

    /* synthetic */ C1538j(C0829d c0829d, byte b) {
        this(c0829d);
    }

    public void onPackageRemoved(String str, UserHandle userHandle) {
        onPackageChanged(str, userHandle);
    }

    public void onPackageAdded(String str, UserHandle userHandle) {
        onPackageChanged(str, userHandle);
    }

    public void onPackageChanged(String str, UserHandle userHandle) {
        Message.obtain(this.f2458Bg.mWorker, 5, 0, 0, new C0835k(this, str, userHandle)).sendToTarget();
    }

    public void onPackagesAvailable(String[] strArr, UserHandle userHandle, boolean z) {
        for (String onPackageChanged : strArr) {
            onPackageChanged(onPackageChanged, userHandle);
        }
    }

    public void onPackagesUnavailable(String[] strArr, UserHandle userHandle, boolean z) {
        for (String onPackageChanged : strArr) {
            onPackageChanged(onPackageChanged, userHandle);
        }
    }

    public void onPackagesSuspended(String[] strArr, UserHandle userHandle) {
        for (String onPackageChanged : strArr) {
            onPackageChanged(onPackageChanged, userHandle);
        }
    }

    public void onPackagesUnsuspended(String[] strArr, UserHandle userHandle) {
        for (String onPackageChanged : strArr) {
            onPackageChanged(onPackageChanged, userHandle);
        }
    }

    public void onShortcutsChanged(String str, List list, UserHandle userHandle) {
    }
}
