package com.android.launcher3.backports.allapps;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.Utilities;
import com.android.launcher3.backports.p024e.C0852d;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;
import com.android.launcher3.util.PackageManagerHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.d */
public class C0829d implements OnSharedPreferenceChangeListener, Callback {
    /* renamed from: AS */
    private static final Uri f1204AS = new Builder().scheme("content").authority("com.google.android.as.allapps.actionsuggestprovider").build();
    /* renamed from: AT */
    private static final Uri f1205AT = new Builder().scheme("content").authority("com.google.android.as.allapps.actionloggingprovider").build();
    /* renamed from: AU */
    private static final Uri f1206AU = new Builder().scheme("content").authority("com.google.android.as.allapps.actionsettingprovider").build();
    /* renamed from: AV */
    private static final String[] f1207AV = new String[]{"action_id", "shortcut_id", "expiration_time_millis", "publisher_package", "badge_package", "position"};
    /* renamed from: AW */
    private static C0829d f1208AW;
    /* renamed from: AX */
    private final SharedPreferences f1209AX;
    /* renamed from: AY */
    private final ArrayList f1210AY = new ArrayList();
    /* renamed from: AZ */
    final ArrayList f1211AZ = new ArrayList();
    /* renamed from: Ba */
    private final ContentObserver f1212Ba = new C0830e(this, this.mUiHandler);
    /* renamed from: Bb */
    private final Comparator f1213Bb = -$$Lambda$d$duEh0paxQHyQSV_x01aBnbrlM40.INSTANCE;
    /* renamed from: Bc */
    public final C0833h f1214Bc = new C0833h(this);
    /* renamed from: Bd */
    private final LauncherAppsCompat f1215Bd;
    /* renamed from: Be */
    C0836l f1216Be;
    /* renamed from: Bf */
    private C1538j f1217Bf = new C1538j(this, (byte) 0);
    private final Context mAppContext;
    private final SharedPreferences mPrefs;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper(), this);
    private final Handler mWorker = new Handler(LauncherModel.getWorkerLooper(), this);

    /* renamed from: c */
    public static C0829d m656c(Context context) {
        if (f1208AW == null) {
            f1208AW = new C0829d(context.getApplicationContext());
        }
        return f1208AW;
    }

    private C0829d(Context context) {
        this.mAppContext = context;
        this.mPrefs = Utilities.getPrefs(context);
        this.f1209AX = context.getSharedPreferences("pref_file_impressions", 0);
        this.mPrefs.registerOnSharedPreferenceChangeListener(this);
        m659cX();
        m660cY();
        this.f1215Bd = LauncherAppsCompat.getInstance(context);
        this.f1215Bd.addOnAppsChangedCallback(this.f1217Bf);
        context.registerReceiver(new C0831f(this), C0852d.m689a("com.google.android.as", "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_DATA_CLEARED", "android.intent.action.PACKAGE_RESTARTED"));
    }

    /* renamed from: cW */
    private boolean m658cW() {
        return this.mPrefs.getBoolean("pref_show_suggested_actions", true);
    }

    /* renamed from: cX */
    private void m659cX() {
        Message.obtain(this.mWorker, 2, Boolean.valueOf(m658cW())).sendToTarget();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("pref_show_suggested_actions".equals(str)) {
            m659cX();
            m661cZ();
        }
    }

    /* renamed from: cY */
    private void m660cY() {
        ContentResolver contentResolver = this.mAppContext.getContentResolver();
        contentResolver.unregisterContentObserver(this.f1212Ba);
        try {
            contentResolver.registerContentObserver(f1204AS, true, this.f1212Ba);
            m661cZ();
            Message.obtain(this.mWorker, 3).sendToTarget();
        } catch (Throwable e) {
            Log.w("ActionsController", "content provider not found", e);
        }
    }

    /* renamed from: c */
    private static ArrayList m657c(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0828a c0828a = (C0828a) it.next();
            if (c0828a.f1202Av) {
                arrayList2.add(c0828a);
            }
        }
        return arrayList2;
    }

    /* renamed from: cZ */
    private void m661cZ() {
        Message.obtain(this.mWorker, 1).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                try {
                    this.mAppContext.getContentResolver().insert(f1205AT, m650a((C0834i) message.obj));
                    break;
                } catch (Throwable e) {
                    Log.e("ActionsController", "write log failed", e);
                    break;
                }
            case 1:
                /*Message.obtain(this.mUiHandler, 4, 0, 0, C0829d.m657c(m664da())).sendToTarget();*/
                break;
            case 2:
                boolean booleanValue = ((Boolean) message.obj).booleanValue();
                ContentValues contentValues = new ContentValues();
                contentValues.put("enable_action_suggest", Boolean.valueOf(booleanValue));
                try {
                    this.mAppContext.getContentResolver().insert(f1206AU, contentValues);
                    break;
                } catch (Throwable e2) {
                    Log.e("ActionsController", "write setting failed", e2);
                    break;
                }
            case 3:
                m665db();
                break;
            case 4:
                this.f1211AZ.clear();
                this.f1211AZ.addAll((ArrayList) message.obj);
                if (this.f1216Be != null) {
                    this.f1216Be.mo3431e(this.f1211AZ);
                    break;
                }
                break;
            case 5:
                C0835k c0835k = (C0835k) message.obj;
                Iterator it = this.f1210AY.iterator();
                int i = 0;
                while (it.hasNext()) {
                    C0828a c0828a = (C0828a) it.next();
                    if (c0828a.f1199AK != null && c0828a.f1200AL != null && c0828a.f1197AI.equals(c0835k.packageName) && c0828a.f1199AK.mShortcutInfo.getUserHandle().equals(c0835k.user)) {
                        m652a(c0828a);
                        i = true;
                    }
                }
                if (i != 0) {
                    Message.obtain(this.mUiHandler, 4, 0, 0, C0829d.m657c(this.f1210AY)).sendToTarget();
                    break;
                }
                break;
        }
        return true;
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                return;
            }
        }
        autoCloseable.close();
    }

    /* renamed from: a */
    private ContentValues m650a(C0834i c0834i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(c0834i.f1230ts));
        contentValues.put("event_type", Integer.valueOf(c0834i.f1226Bl));
        contentValues.put("clicked_type", Integer.valueOf(0));
        contentValues.put("clicked_id", c0834i.f1227Bm);
        contentValues.put("clicked_position", Integer.valueOf(c0834i.f1228Bn));
        contentValues.put("top_suggestions", c0834i.f1229Bo);
        return contentValues;
    }

    /* renamed from: db */
    private void m665db() {
        try {
            Map all = this.f1209AX.getAll();
            C0834i c0834i = new C0834i();
            c0834i.f1226Bl = 2;
            Set<String> keySet = all.keySet();
            ArrayList arrayList = new ArrayList();
            for (String str : keySet) {
                String[] split = ((String) all.get(str)).split(",");
                c0834i.f1230ts = Long.parseLong(split[0]);
                c0834i.f1229Bo = str;
                arrayList.add(m650a(c0834i));
                for (int i = 1; i < split.length; i++) {
                    c0834i.f1230ts += Long.parseLong(split[i]);
                    arrayList.add(m650a(c0834i));
                }
            }
            this.mAppContext.getContentResolver().bulkInsert(f1205AT, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
        } catch (Throwable e) {
            Log.e("ActionsController", "write impression logs", e);
        } catch (Throwable th) {
            this.f1209AX.edit().clear().apply();
        }
        this.f1209AX.edit().clear().apply();
    }

    /* renamed from: d */
    private void m663d(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0828a c0828a = (C0828a) it.next();
            if (c0828a.f1199AK == null || c0828a.f1200AL == null) {
                it.remove();
            } else {
                m652a(c0828a);
            }
        }
        Collections.sort(arrayList, this.f1213Bb);
    }

    /* renamed from: a */
    private void m652a(C0828a c0828a) {
        c0828a.f1202Av = false;
        if (c0828a.f1199AK.mShortcutInfo.isEnabled()) {
            ApplicationInfo applicationInfo = this.f1215Bd.getApplicationInfo(c0828a.f1197AI, 0, c0828a.f1199AK.mShortcutInfo.getUserHandle());
            if (applicationInfo != null && applicationInfo.enabled && !PackageManagerHelper.isAppSuspended(applicationInfo)) {
                c0828a.f1202Av = true;
            }
        }
    }

    /* renamed from: a */
    private C0832g m651a(List list, ShortcutInfoCompat shortcutInfoCompat) {
        if (shortcutInfoCompat != null) {
            for (C0832g c0832g : list) {
                if (c0832g.shortcutId.equals(shortcutInfoCompat.mShortcutInfo.getId())) {
                    return c0832g;
                }
            }
        }
        return null;
    }
}
