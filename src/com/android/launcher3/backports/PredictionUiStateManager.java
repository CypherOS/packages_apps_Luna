package com.android.launcher3.backports;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Message;
import android.text.TextUtils;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.android.launcher3.AppInfo;
import com.android.launcher3.IconCache;
import com.android.launcher3.IconCache.ItemInfoUpdateReceiver;
import com.android.launcher3.ItemInfoWithIcon;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.Utilities;
import com.android.launcher3.allapps.AllAppsContainerView;
import com.android.launcher3.backports.p020a.C0825a;
import com.android.launcher3.backports.p023d.C0847b;
import com.android.launcher3.backports.p024e.C0849a;
import com.android.launcher3.backports.p024e.C0850b;
import com.android.launcher3.backports.reflection.C0905k;
import com.android.launcher3.backports.reflection.p025a.C0877d;
import com.android.launcher3.util.ComponentKey;

import com.android.quickstep.InstantAppResolverImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PredictionUiStateManager implements OnSharedPreferenceChangeListener, OnGlobalLayoutListener, ItemInfoUpdateReceiver {
    /* renamed from: Aj */
    private static PredictionUiStateManager f2408Aj;
    /* renamed from: Ak */
    private final SharedPreferences f2409Ak;
    /* renamed from: Al */
    private final SharedPreferences f2410Al;
    /* renamed from: Am */
    private final C0847b f2411Am;
    /* renamed from: An */
    private final C0825a f2412An;
    /* renamed from: Ao */
    private final int f2413Ao;
    /* renamed from: Ap */
    public Client f2414Ap = Client.HOME;
    /* renamed from: Aq */
    private C0858i f2415Aq;
    /* renamed from: Ar */
    public C0858i f2416Ar = m2242cU();
    private AllAppsContainerView mAppsView;
    private final Context mContext;
    private final IconCache mIconCache;

    public enum Client {
        HOME("GEL"),
        OVERVIEW("OVERVIEW_GEL");
        
        /* renamed from: id */
        public final String f1180id;
        private final C0877d mKeyConfg;

        private Client(String str) {
            this.f1180id = str;
            this.mKeyConfg = C0877d.m746r(str);
        }
    }

    /* renamed from: a */
    public static PredictionUiStateManager m2238a(Context context) {
        if (f2408Aj == null) {
            f2408Aj = new PredictionUiStateManager(context.getApplicationContext());
        }
        return f2408Aj;
    }

    private PredictionUiStateManager(Context context) {
        this.mContext = context;
        this.f2409Ak = Utilities.getPrefs(context);
        this.f2410Al = C0905k.m790m(context);
        this.f2413Ao = LauncherAppState.getInstance(context).mInvariantDeviceProfile.numColumns;
        this.f2411Am = C0847b.m679h(context);
        this.f2412An = C0825a.m648b(context);
        this.mIconCache = LauncherAppState.getInstance(context).mIconCache;
        this.f2409Ak.registerOnSharedPreferenceChangeListener(this);
        this.f2410Al.registerOnSharedPreferenceChangeListener(this);
        this.f2411Am.f1266DA = new AppInfoPackageListener(this);
        this.f2412An.f1186AC = new AppInfoPackageListener(this);
    }

    /* renamed from: a */
    public final void mo5357a(Client client) {
        if (client != this.f2414Ap) {
            this.f2414Ap = client;
            m2243p(true);
        }
    }

    /* renamed from: a */
    public final void mo5356a(AllAppsContainerView allAppsContainerView) {
        this.mAppsView = allAppsContainerView;
        if (this.f2415Aq != null) {
            m2239a(this.f2415Aq);
            this.f2415Aq = null;
        } else {
            m2239a(this.f2416Ar);
        }
        m2240b(this.f2416Ar);
    }

    public final void reapplyItemInfo(ItemInfoWithIcon itemInfoWithIcon) {
    }

    public void onGlobalLayout() {
        if (this.mAppsView != null) {
            if (this.f2415Aq != null) {
                m2239a(this.f2415Aq);
                this.f2415Aq = null;
            }
            if (this.f2415Aq == null) {
                this.mAppsView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("pref_show_predictions".equals(str) || this.f2414Ap.mKeyConfg.f1322Gn.equals(str) || this.f2414Ap.mKeyConfg.f1325Gq.equals(str)) {
            m2243p(true);
        }
    }

    /* renamed from: a */
    private void m2239a(C0858i c0858i) {
        boolean z = this.f2416Ar.f1286Av;
        this.f2416Ar = c0858i;
        if (this.mAppsView != null) {
            ((PredicitonsHeaderListener) this.mAppsView.mHeader).mo3477a(this.f2416Ar.f1286Av, this.f2416Ar.f1287Aw);
            if (z != this.f2416Ar.f1286Av) {
                Launcher.getLauncher(this.mAppsView.getContext()).mStateManager.reapplyState(true);
            }
        }
    }

    /* renamed from: p */
    private void m2243p(boolean z) {
        C0858i cU = z ? m2242cU() : this.f2415Aq == null ? this.f2416Ar : this.f2415Aq;
        if (!z || this.mAppsView == null) {
            m2239a(cU);
            return;
        }
        Object obj = this.f2415Aq == null ? 1 : null;
        this.f2415Aq = cU;
        if (obj != null) {
            this.mAppsView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
    }

    /* renamed from: cU */
    private C0858i m2242cU() {
        C0858i c0858i = new C0858i();
        c0858i.f1286Av = this.f2409Ak.getBoolean("pref_show_predictions", true);
        if (c0858i.f1286Av) {
            int length;
            c0858i.f1287Aw = new ArrayList();
            c0858i.f1288Ax = new ArrayList();
            String string = this.f2410Al.getString(this.f2414Ap.mKeyConfg.f1325Gq, null);
            int i = 0;
            if (!TextUtils.isEmpty(string)) {
                for (String a : string.split(";")) {
                    ComponentKey a2 = C0850b.m687a(a, this.mContext);
                    if (a2 != null) {
                        c0858i.f1288Ax.add(a2);
                    }
                }
            }
            string = this.f2410Al.getString(this.f2414Ap.mKeyConfg.f1322Gn, null);
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(";");
                length = split.length;
                while (i < length) {
                    ComponentKey a3 = C0850b.m687a(split[i], this.mContext);
                    if (a3 != null) {
                        c0858i.f1287Aw.add(new C0849a(this.mContext, a3));
                    }
                    i++;
                }
            }
            m2240b(c0858i);
            return c0858i;
        }
        c0858i.f1287Aw = Collections.EMPTY_LIST;
        c0858i.f1288Ax = Collections.EMPTY_LIST;
        return c0858i;
    }

    /* renamed from: b */
    private void m2240b(C0858i c0858i) {
        if (c0858i.f1286Av && this.mAppsView != null) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            int size = c0858i.f1287Aw.size();
            int i = 0;
            int i2 = i;
            while (i < size && i2 < this.f2413Ao) {
                C0849a c0849a = (C0849a) c0858i.f1287Aw.get(i);
                if (InstantAppResolverImpl.COMPONENT_CLASS_MARKER.equals(c0849a.mo3458fk())) {
                    arrayList.add(c0849a.getPackage());
                } else {
                    AppInfo appInfo = (AppInfo) c0849a.mo3457a(this.mAppsView.mAllAppsStore);
                    if (appInfo == null) {
                        i++;
                    } else if (appInfo.usingLowResIcon) {
                        this.mIconCache.updateIconInBackground(this, appInfo);
                    }
                }
                i2++;
                i++;
            }
            C0847b c0847b = this.f2411Am;
            if (!arrayList.isEmpty()) {
                Message.obtain(c0847b.mWorker, 1, arrayList).sendToTarget();
            }
            C0825a c0825a = this.f2412An;
            if (!arrayList2.isEmpty()) {
                Message.obtain(c0825a.mWorker, 0, arrayList2).sendToTarget();
            }
        }
    }

    /* renamed from: cV */
    public void mo5358cV() {
        m2243p(false);
    }
}
