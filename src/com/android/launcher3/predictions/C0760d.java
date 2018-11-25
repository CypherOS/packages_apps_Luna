package com.android.launcher3.predictions;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
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
import com.android.launcher3.predictions.backport.C0786d;
import com.android.launcher3.shortcuts.ShortcutInfoCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.apps.nexuslauncher.allapps.d */
public class C0760d implements OnSharedPreferenceChangeListener, Callback {
    /* renamed from: xC */
    private static final Uri f983xC = new Builder().scheme("content").authority("com.google.android.apps.miphone.aiai.allapps.actionsuggestprovider").build();
    /* renamed from: xD */
    private static final Uri f984xD = new Builder().scheme("content").authority("com.google.android.apps.miphone.aiai.allapps.actionloggingprovider").build();
    /* renamed from: xE */
    private static final Uri f985xE = new Builder().scheme("content").authority("com.google.android.apps.miphone.aiai.allapps.actionsettingprovider").build();
    /* renamed from: xF */
    private static final String[] f986xF = new String[]{"action_id", "shortcut_id", "expiration_time_millis", "publisher_package", "badge_package"};
    /* renamed from: xG */
    private static C0760d f987xG;
    /* renamed from: gL */
    final ArrayList f988gL = new ArrayList();
    private final Context mAppContext;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper(), this);
    public final Handler mWorker = new Handler(LauncherModel.getWorkerLooper(), this);
    /* renamed from: xH */
    private final SharedPreferences f989xH;
    /* renamed from: xI */
    private final SharedPreferences f990xI;
    /* renamed from: xJ */
    private final ContentObserver f991xJ = new C0761e(this, this.mUiHandler);
    /* renamed from: xK */
    private final Comparator f992xK = RankComparator.INSTANCE;
    /* renamed from: xL */
    public final C0764h f993xL = new C0764h(this);
    /* renamed from: xM */
    C0766j f994xM;

    /* renamed from: b */
    public static C0760d m2434b(Context context) {
        if (f987xG == null) {
            f987xG = new C0760d(context.getApplicationContext());
        }
        return f987xG;
    }

    private C0760d(Context context) {
        this.mAppContext = context;
        this.f989xH = Utilities.getPrefs(context);
        this.f990xI = context.getSharedPreferences("pref_file_impressions", 0);
        this.f989xH.registerOnSharedPreferenceChangeListener(this);
        m2439cJ();
        m2440cK();
        context.registerReceiver(new C0762f(this), C0786d.m2495a("com.google.android.apps.miphone.aiai", "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_DATA_CLEARED"));
    }

    /* renamed from: cI */
    private boolean m2438cI() {
        return this.f989xH.getBoolean("pref_show_suggested_actions", true);
    }

    /* renamed from: cJ */
    private void m2439cJ() {
        Message.obtain(this.mWorker, 2, Boolean.valueOf(m2438cI())).sendToTarget();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("pref_show_suggested_actions".equals(str)) {
            m2439cJ();
            m2441cL();
        }
    }

    /* renamed from: cK */
    public void m2440cK() {
        ContentResolver contentResolver = this.mAppContext.getContentResolver();
        contentResolver.unregisterContentObserver(this.f991xJ);
        try {
            contentResolver.registerContentObserver(f983xC, true, this.f991xJ);
            m2441cL();
            Message.obtain(this.mWorker, 3).sendToTarget();
        } catch (SecurityException e) {
            Log.w("ActionsController", "content provider not found", e);
        }
    }

    /* renamed from: cL */
    private void m2441cL() {
        Message.obtain(this.mWorker, 1).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                try {
                    this.mAppContext.getContentResolver().insert(f984xD, m2431a((C0765i) message.obj));
                    break;
                } catch (Exception e) {
                    Log.e("ActionsController", "write log failed", e);
                    break;
                }
            case 1:
                //Message.obtain(this.mUiHandler, 4, 0, 0, m2442cM()).sendToTarget();
                break;
            case 2:
                boolean booleanValue = ((Boolean) message.obj).booleanValue();
                ContentValues contentValues = new ContentValues();
                contentValues.put("enable_action_suggest", Boolean.valueOf(booleanValue));
                try {
                    this.mAppContext.getContentResolver().insert(f985xE, contentValues);
                    break;
                } catch (Exception e2) {
                    Log.e("ActionsController", "write setting failed", e2);
                    break;
                }
            case 3:
                m2443cN();
                break;
            case 4:
                this.f988gL.clear();
                this.f988gL.addAll((ArrayList) message.obj);
                if (this.f994xM != null) {
                    this.f994xM.mo3114d(this.f988gL);
                    break;
                }
                break;
        }
        return true;
    }

    /* renamed from: a */
    private ContentValues m2431a(C0765i c0765i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(c0765i.f1002ts));
        contentValues.put("event_type", Integer.valueOf(c0765i.f1003xS ? 1 : 2));
        contentValues.put("clicked_type", Integer.valueOf(0));
        contentValues.put("clicked_id", c0765i.f1004xT);
        contentValues.put("clicked_position", Integer.valueOf(c0765i.f1005xU));
        contentValues.put("top_suggestions", c0765i.f1006xV);
        return contentValues;
    }

    /* renamed from: cN */
    private void m2443cN() {
        try {
            Map all = this.f990xI.getAll();
            C0765i c0765i = new C0765i(0);
            Set<String> keySet = all.keySet();
            ArrayList arrayList = new ArrayList();
            for (String str : keySet) {
                String[] split = ((String) all.get(str)).split(",");
                c0765i.f1002ts = Long.parseLong(split[0]);
                c0765i.f1006xV = str;
                arrayList.add(m2431a(c0765i));
                for (int i = 1; i < split.length; i++) {
                    c0765i.f1002ts += Long.parseLong(split[i]);
                    arrayList.add(m2431a(c0765i));
                }
            }
            this.mAppContext.getContentResolver().bulkInsert(f984xD, (ContentValues[]) arrayList.toArray(new ContentValues[arrayList.size()]));
        } catch (Exception e) {
            Log.e("ActionsController", "write impression logs", e);
        } catch (Throwable th) {
            this.f990xI.edit().clear().apply();
        }
        this.f990xI.edit().clear().apply();
    }

    /* renamed from: c */
    private void m2437c(ArrayList arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0759a c0759a = (C0759a) it.next();
            if (c0759a.f981xv == null || c0759a.f982xw == null) {
                it.remove();
            }
        }
        Collections.sort(arrayList, this.f992xK);
    }

    /* renamed from: a */
    private C0763g m2432a(List list, ShortcutInfoCompat shortcutInfoCompat) {
        if (shortcutInfoCompat != null) {
            for (C0763g c0763g : list) {
                if (c0763g.shortcutId.equals(shortcutInfoCompat.mShortcutInfo.getId())) {
                    return c0763g;
                }
            }
        }
        return null;
    }
}
