package com.android.launcher3.reflection.p014d;

import android.content.SharedPreferences;
import com.android.launcher3.util.Preconditions;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.android.launcher3.reflection.d.e */
public class C0810e {
    /* renamed from: bQ */
    private final File f151bQ;
    /* renamed from: bR */
    private final Set<String> f152bR;
    private final SharedPreferences mPrivatePrefs;

    public C0810e(SharedPreferences sharedPreferences, File file, List<String> list) {
        this.mPrivatePrefs = sharedPreferences;
        this.f151bQ = file;
        this.f152bR = new HashSet(list);
    }

    /* renamed from: u */
    public final synchronized void mo8504u() {
        Preconditions.assertNonUiThread();
        this.mPrivatePrefs.edit().clear().apply();
        if (this.f151bQ.exists() && this.f151bQ.isDirectory()) {
            for (File b : this.f151bQ.listFiles()) {
                m2609b(b);
            }
        }
    }

    /* renamed from: b */
    private void m2609b(File file) {
        if (file.isDirectory()) {
            for (File b : file.listFiles()) {
                m2609b(b);
            }
            if (file.list().length == 0 && this.f152bR.contains(file.getAbsolutePath())) {
                file.delete();
            }
        } else if (this.f152bR.contains(file.getName()) || (file.getParentFile() != null && this.f152bR.contains(file.getParentFile().getAbsolutePath()))) {
            file.delete();
        }
    }
}
