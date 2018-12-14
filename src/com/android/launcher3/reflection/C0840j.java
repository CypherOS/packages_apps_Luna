package com.google.android.apps.nexuslauncher.reflection;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.launcher3.Utilities;
import com.android.launcher3.util.Preconditions;
import com.google.android.apps.nexuslauncher.reflection.p011b.C0794b;
import com.google.android.apps.nexuslauncher.reflection.p014d.C1211c;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.j */
public class C0840j {
    static final String PREF_KEY_BACKGROUND_MODEL_VERSION = "background_model_version";
    static final String PREF_KEY_PROGRESS = "staged_batch_training_progress";
    /* renamed from: aK */
    private static final Pattern f242aK = Pattern.compile("^InProgress:(.+)$");
    /* renamed from: aL */
    private final C1211c f243aL;
    /* renamed from: aM */
    private final SharedPreferences f244aM;
    /* renamed from: aN */
    private final C0834g f245aN;
    /* renamed from: aO */
    private final File f246aO;
    /* renamed from: aP */
    private final FirstPageComponentFilter f247aP;
    /* renamed from: aQ */
    private C0839a f248aQ = null;
    /* renamed from: ae */
    private final C0794b f249ae;
    private final Context mContext;

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.j$a */
    private class C0839a implements Runnable {
        /* renamed from: aR */
        private C0834g f240aR;

        private C0839a() {
        }

        /* synthetic */ C0839a(C0840j c0840j, byte b) {
            this();
        }

        public void run() {
            try {
                C0840j.this.m2678a(m2674m(), this);
            } catch (Throwable unused) {
                C0840j.this.m2679a(this);
            }
        }

        private C0834g m2674m() {
            Preconditions.assertNonUiThread();
            synchronized (C0840j.this) {
                String string = C0840j.this.f244aM.getString(C0840j.PREF_KEY_PROGRESS, "Success");
                if ("Success".equals(string)) {
                    return null;
                }
                C0840j.this.f247aP;
                C0834g c0834g = new C0834g(C0840j.this.mContext, null, C0840j.this.f243aL, C0840j.this.f244aM, "background_evt_buf.properties", null, C0840j.this.f249ae);
                this.f240aR = c0834g;
                this.f240aR.mo8511a(C0840j.this.f246aO);
                long j;
                if ("New".equals(string)) {
                    j = 0;
                } else {
                    Matcher matcher = C0840j.f242aK.matcher(string);
                    if (matcher.find()) {
                        try {
                            j = Long.parseLong(matcher.group(1));
                            //this.f240aR.mo8516i();
                        } catch (NumberFormatException e) {
                            Log.e("Reflection.StBatchTrain", "Invalid progress string.", e);
                            return null;
                        }
                    }
                    Log.e("Reflection.StBatchTrain", "Invalid progress string.");
                    return null;
                }
            }
        }
    }

    public C0840j(Context context, C1211c c1211c, SharedPreferences sharedPreferences, File file, C0834g c0834g, FirstPageComponentFilter firstPageComponentFilter, C0794b c0794b) {
        this.mContext = context;
        this.f243aL = c1211c;
        this.f244aM = sharedPreferences;
        this.f246aO = file;
        this.f245aN = c0834g;
        this.f249ae = c0794b;
        this.f247aP = firstPageComponentFilter;
        this.f247aP.update();
    }

    public final synchronized boolean isInProgress() {
        boolean z;
        String string = this.f244aM.getString(PREF_KEY_PROGRESS, null);
        z = string != null && f242aK.matcher(string).find();
        return z;
    }

    /* renamed from: k */
    public final synchronized int mo8530k() {
        return this.f244aM.getInt(PREF_KEY_BACKGROUND_MODEL_VERSION, 0);
    }

    /* renamed from: b */
    public final synchronized void mo8528b(boolean z) {
        if (z) {
            try {
                this.f244aM.edit().putString(PREF_KEY_PROGRESS, "New").putInt(PREF_KEY_BACKGROUND_MODEL_VERSION, 42).apply();
                this.f246aO.delete();
            } catch (Throwable th) {
            }
        } else if (this.f248aQ != null) {
            return;
        }
        this.f248aQ = new C0839a(this, (byte) 0);
        Utilities.THREAD_POOL_EXECUTOR.execute(this.f248aQ);
    }

    /* renamed from: a */
    private synchronized void m2678a(C0834g c0834g, C0839a c0839a) {
        if (this.f248aQ == c0839a) {
            this.f245aN.mo8510a(c0834g);
            this.f245aN.mo8517j();
            this.f246aO.delete();
            this.f248aQ = null;
        }
    }

    /* renamed from: a */
    private synchronized void m2679a(C0839a c0839a) {
        if (this.f248aQ == c0839a) {
            this.f244aM.edit().remove(PREF_KEY_BACKGROUND_MODEL_VERSION).remove(PREF_KEY_PROGRESS).apply();
            this.f246aO.delete();
            this.f248aQ = null;
        }
    }
}
