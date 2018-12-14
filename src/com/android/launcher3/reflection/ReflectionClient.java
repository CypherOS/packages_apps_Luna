package com.google.android.apps.nexuslauncher.reflection;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.LauncherActivityInfo;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.LauncherAppsCompat;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.notification.NotificationListener;
import com.android.launcher3.shortcuts.ShortcutKey;
import com.android.launcher3.userevent.nano.LauncherLogProto$LauncherEvent;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.util.Preconditions;
import com.google.android.apps.nexuslauncher.reflection.C0837i.C08381;
import com.google.android.apps.nexuslauncher.reflection.p010a.C0789b;
import com.google.android.apps.nexuslauncher.reflection.p010a.C0792e;
import com.google.android.apps.nexuslauncher.reflection.p011b.C0794b;
import com.google.android.apps.nexuslauncher.reflection.p011b.C0796c;
import com.google.android.apps.nexuslauncher.reflection.p011b.C0796c.C0795a;
import com.google.android.apps.nexuslauncher.reflection.p011b.C0797d;
import com.google.android.apps.nexuslauncher.reflection.p014d.C0809d;
import com.google.android.apps.nexuslauncher.reflection.p014d.C0810e;
import com.google.android.apps.nexuslauncher.reflection.p014d.C1209a;
import com.google.android.apps.nexuslauncher.reflection.p014d.C1211c;
import com.google.android.apps.nexuslauncher.reflection.sensing.C1213a;
import com.google.android.apps.nexuslauncher.reflection.sensing.NotificationSensor;
import com.google.android.apps.nexuslauncher.reflection.sensing.UsageEventSensor;
import com.google.android.apps.nexuslauncher.reflection.signal.C1214a;
import com.google.research.reflection.p018c.C0948c;
import com.google.research.reflection.p018c.C1245d;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p021me.jfenn.attribouter.BuildConfig;

public class ReflectionClient implements Callback {
    static final int MSG_DESTROY = 1;
    static final int MSG_INIT = 0;
    static final int MSG_LAUNCH = 2;
    public static final String PREF_KEY_ENABLE = "pref_show_predictions";
    /* renamed from: af */
    private static final Object f93af = new Object();
    /* renamed from: ag */
    private static ReflectionClient f94ag;
    /* renamed from: ah */
    private final InvariantDeviceProfile f95ah;
    private final Context mContext;
    final Handler mMessageHandler;
    final HandlerThread mPlaceThread;
    C0836h mServiceHandler = null;
    final HandlerThread mWorkerThread;

    public void onShortcutLaunch(ShortcutKey shortcutKey, LauncherLogProto$LauncherEvent launcherLogProto$LauncherEvent) {
    }

    public static ReflectionClient getInstance(Context context) {
        synchronized (f93af) {
            if (f94ag == null) {
                ReflectionClient reflectionClient = new ReflectionClient(context.getApplicationContext());
                f94ag = reflectionClient;
                reflectionClient.setEnabled(Utilities.getPrefs(context).getBoolean(PREF_KEY_ENABLE, true));
            }
        }
        return f94ag;
    }

    private ReflectionClient(Context context) {
        this.mContext = context;
        this.mWorkerThread = new HandlerThread("reflection-thread");
        this.mWorkerThread.start();
        this.mMessageHandler = new Handler(this.mWorkerThread.getLooper(), this);
        this.mPlaceThread = new HandlerThread("reflection-place-thread");
        this.mPlaceThread.start();
        this.f95ah = LauncherAppState.getIDP(context);
    }

    public boolean handleMessage(Message message) {
        Message message2 = message;
        C0809d c0809d = null;
        int i;
        String a;
        switch (message2.what) {
            case 0:
                if (this.mServiceHandler == null) {
                    Context context = this.mContext;
                    ArrayList arrayList = new ArrayList();
                    C0800b c1208a = new C1208a(context);
                    C0789b c0789b = new C0789b(context);
                    SharedPreferences prefs = Utilities.getPrefs(context);
                    C1211c c1211c = new C1211c(new C1209a(context, "reflection.events"), c0789b);
                    File file = new File(context.getCacheDir(), "client_actions");
                    if (prefs.getBoolean("pre_debug", false)) {
                        c0809d = new C0809d(file, 10485760);
                    } else if (file.exists()) {
                        file.delete();
                    }
                    SharedPreferences d = C0832f.m2649d(context);
                    C0794b c0794b = new C0794b(context);
                    C0797d c0797d = new C0797d(c0789b);
                    C0796c c0796c = new C0796c(context);
                    FirstPageComponentFilter firstPageComponentFilter = new FirstPageComponentFilter(context.getContentResolver(), d, c0809d, context);
                    firstPageComponentFilter.update();
                    C0834g c0834g = r7v11;
                    FirstPageComponentFilter firstPageComponentFilter2 = firstPageComponentFilter;
                    C0796c c0796c2 = c0796c;
                    C0797d c0797d2 = c0797d;
                    C0794b c0794b2 = c0794b;
                    SharedPreferences sharedPreferences = d;
                    C1211c c1211c2 = c1211c;
                    C0789b c0789b2 = c0789b;
                    C0834g c0834g2 = new C0834g(context, c1211c, c1211c, d, "foreground_evt_buf.properties", null, c0794b2);
                    C1245d c1245d = new C1245d();
                    C0837i c0837i = C0837i.f238aI;
                    r8v8 = new C0948c[3];
                    C0834g c0834g3 = c0834g;
                    r8v8[0] = c0834g3.f213al;
                    r8v8[1] = c0834g3.f214am;
                    r8v8[2] = c1245d;
                    ArrayList arrayList2 = new ArrayList(Arrays.asList(r8v8));
                    if (C0837i.f237aH) {
                        arrayList2.add(new C08381());
                    }
                    C1213a c1213a = new C1213a(context);
                    C0837i.m2669a(c1213a, arrayList2);
                    arrayList.add(c1213a);
                    if (C0837i.f237aH) {
                        Log.d(C0837i.TAG, "Registered HeadsetPlugReceiver");
                    }
                    UsageEventSensor e = UsageEventSensor.m4757e(context);
                    if (e != null) {
                        C0837i.m2669a(e, arrayList2);
                        if (C0837i.f237aH) {
                            Log.d(C0837i.TAG, "UsageEventSensor added.");
                        }
                    }
                    NotificationSensor notificationSensor = new NotificationSensor(context);
                    C0837i.m2669a(notificationSensor, arrayList2);
                    arrayList.add(notificationSensor);
                    NotificationListener.setStatusBarNotificationsChangedListener(notificationSensor);
                    if (C0837i.f237aH) {
                        Log.d(C0837i.TAG, "NotificationSensor added.");
                    }
                    if (C0837i.f237aH) {
                        Log.d(C0837i.TAG, "Sensors made and connected.");
                    }
                    File file2 = new File(context.getFilesDir(), "reflection.engine");
                    File file3 = new File(context.getFilesDir(), "reflection.engine.background");
                    SharedPreferences sharedPreferences2 = sharedPreferences;
                    C0840j c0840j = r7v20;
                    C1211c c1211c3 = c1211c2;
                    SharedPreferences sharedPreferences3 = sharedPreferences2;
                    FirstPageComponentFilter firstPageComponentFilter3 = new FirstPageComponentFilter(context.getContentResolver(), sharedPreferences2, c0809d, context);
                    C0834g c0834g4 = c0834g3;
                    C0834g c0834g5 = c0834g3;
                    FirstPageComponentFilter firstPageComponentFilter4 = firstPageComponentFilter3;
                    C1245d c1245d2 = c1245d;
                    C0840j c0840j2 = new C0840j(context, c1211c3, sharedPreferences2, file3, c0834g4, firstPageComponentFilter4, c0794b2);
                    new C0807c().mo8500a(file2, c0834g5, c0840j);
                    c0834g5.mo8511a(file2);
                    prefs = sharedPreferences3;
                    C0831e c0831e = new C0831e(prefs);
                    arrayList2 = new ArrayList();
                    for (String str : C0832f.ALL_FILES) {
                        String str2;
                        if (str2.startsWith("/")) {
                            str2 = context.getDir(str2.substring(1), 0).getAbsolutePath();
                        }
                        arrayList2.add(str2);
                    }
                    C0836h c0836h = r7v23;
                    C0810e c0810e = new C0810e(prefs, new File(context.getApplicationInfo().dataDir), arrayList2);
                    C0836h c0836h2 = new C0836h(context, c0834g5, c0840j, c1245d2, c0794b2, c0797d2, c0796c2, c0831e, c0810e, c0809d, c1208a, firstPageComponentFilter2);
                    C1212d c1212d = new C1212d(context, c0836h, c0794b2, c0789b2);
                    arrayList.add(c1212d);
                    c0836h.f231au.addAll(arrayList);
                    c1212d.initialize();
                    this.mServiceHandler = c0836h;
                    updatePredictionsNow("GEL");
                    updatePredictionsNow("OVERVIEW_GEL");
                }
                return true;
            case 1:
                if (this.mServiceHandler != null) {
                    this.mServiceHandler.mo8523a(true);
                    this.mServiceHandler = null;
                }
                return true;
            case 2:
            case 6:
            case 7:
                Pair pair = (Pair) message2.obj;
                ComponentKey componentKey = (ComponentKey) pair.first;
                LauncherLogProto$LauncherEvent launcherLogProto$LauncherEvent = (LauncherLogProto$LauncherEvent) pair.second;
                if (this.mServiceHandler != null) {
                    ReflectionEventType reflectionEventType;
                    long serialNumberForUser = UserManagerCompat.getInstance(this.mContext).getSerialNumberForUser(componentKey.user);
                    i = message2.what;
                    if (i == 2) {
                        a = C0792e.m2563a(componentKey.componentName, serialNumberForUser, this.mContext);
                        reflectionEventType = ReflectionEventType.APP_USAGE;
                    } else if (i == 6) {
                        ComponentName componentName = componentKey.componentName;
                        Context context2 = this.mContext;
                        a = String.format("%s%s", new Object[]{"_", C0792e.m2563a(componentName, serialNumberForUser, context2)});
                        reflectionEventType = ReflectionEventType.SHORTCUTS;
                    } else if (i == 7) {
                        a = C0792e.m2563a(componentKey.componentName, serialNumberForUser, this.mContext);
                        reflectionEventType = ReflectionEventType.INSTANT_APP_USAGE;
                    }
                    String str3 = a;
                    ReflectionEventType reflectionEventType2 = reflectionEventType;
                    C0836h c0836h3 = this.mServiceHandler;
                    Preconditions.assertNonUiThread();
                    if (str3 == null) {
                        Log.e("Reflection.SvcHandler", "Empty event string");
                    } else {
                        String str4 = BuildConfig.FLAVOR;
                        if (launcherLogProto$LauncherEvent.srcTarget.length > 1) {
                            str4 = Integer.toString(launcherLogProto$LauncherEvent.srcTarget[1].containerType);
                        }
                        C1214a a2 = C0792e.m2562a(reflectionEventType2, str3, str4, c0836h3.f225aD.mo8495g(), c0836h3.f232av.mo14296au());
                        c0836h3.mo8521a(a2, true);
                        c0836h3.mo8520a(a2, launcherLogProto$LauncherEvent);
                    }
                }
                return true;
            case 3:
            case 4:
                if (this.mServiceHandler != null) {
                    a = (String) message2.obj;
                    this.mServiceHandler.mo8522a(a, this.f95ah.numColumns);
                }
                return true;
            case 5:
                if (this.mServiceHandler != null) {
                    C0836h c0836h4 = this.mServiceHandler;
                    PackageUserKey packageUserKey = (PackageUserKey) message2.obj;
                    List activityList = LauncherAppsCompat.getInstance(c0836h4.mContext).getActivityList(packageUserKey.mPackageName, packageUserKey.mUser);
                    if (!activityList.isEmpty()) {
                        C0796c c0796c3 = c0836h4.f235ay;
                        c0796c3.f120bj.add(new C0795a(((LauncherActivityInfo) activityList.get(0)).getComponentName(), UserManagerCompat.getInstance(c0836h4.mContext).getSerialNumberForUser(packageUserKey.mUser), System.currentTimeMillis()));
                        c0796c3.mo8490o();
                    }
                }
                return true;
            case 8:
                if (this.mServiceHandler != null) {
                    this.mServiceHandler.f226aE.update();
                    i = this.f95ah.numColumns;
                    this.mServiceHandler.mo8522a("GEL", i);
                    this.mServiceHandler.mo8522a("OVERVIEW_GEL", i);
                }
                return true;
            default:
                return false;
        }
    }

    public Looper getPlaceLooperForGoogleApi() {
        return this.mPlaceThread.getLooper();
    }

    public void updatePredictionsNow(String str) {
        this.mMessageHandler.removeMessages(3, str);
        Message.obtain(this.mMessageHandler, 3, str).sendToTarget();
    }

    public void setEnabled(boolean z) {
        if (z) {
            this.mMessageHandler.removeMessages(1);
            this.mMessageHandler.sendEmptyMessage(0);
            return;
        }
        this.mMessageHandler.removeMessages(0);
        this.mMessageHandler.sendEmptyMessage(1);
    }

    public void onAppLaunch(ComponentKey componentKey, LauncherLogProto$LauncherEvent launcherLogProto$LauncherEvent) {
        Message.obtain(this.mMessageHandler, 2, Pair.create(componentKey, launcherLogProto$LauncherEvent)).sendToTarget();
        long j = C0832f.f211ak;
        this.mMessageHandler.sendMessageDelayed(Message.obtain(this.mMessageHandler, 4, "OVERVIEW_GEL"), j);
    }

    public void onNewInstall(PackageUserKey packageUserKey) {
        Message.obtain(this.mMessageHandler, 5, packageUserKey).sendToTarget();
    }

    public void onInstantAppLaunch(ComponentKey componentKey, LauncherLogProto$LauncherEvent launcherLogProto$LauncherEvent) {
        Message.obtain(this.mMessageHandler, 7, Pair.create(componentKey, launcherLogProto$LauncherEvent)).sendToTarget();
    }

    public void postNotificationEvent(Runnable runnable) {
        this.mMessageHandler.post(runnable);
    }

    public void onUsageEventTarget(C1214a c1214a) {
        c1214a.mo9263C();
        ReflectionEventType reflectionEventType = ReflectionEventType.SHORTCUTS;
        if (c1214a.mo9263C() == ReflectionEventType.INSTANT_APP_USAGE) {
            this.mServiceHandler.mo8520a(c1214a, null);
        }
    }

    public void onProviderChanged() {
        this.mMessageHandler.removeMessages(8);
        this.mMessageHandler.sendEmptyMessageDelayed(8, C0832f.f210aj);
    }
}
