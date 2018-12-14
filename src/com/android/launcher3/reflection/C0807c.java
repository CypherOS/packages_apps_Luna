package com.android.launcher3.reflection;

import com.android.launcher3.util.IOUtils;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.reflection.p015e.C0824c.C0827c;
import java.io.File;
import java.io.IOException;

/* renamed from: com.android.launcher3.reflection.c */
public class C0807c {
    /* renamed from: a */
    public final void mo8500a(File file, C0834g c0834g, C0840j c0840j) {
        int i;
        Preconditions.assertNonUiThread();
        c0834g.mo8511a(file);
        try {
            i = C0827c.m2636g(IOUtils.toByteArray(file)).version;
        } catch (IOException unused) {
            i = -1;
        }
        if (i >= 42) {
            //c0834g.mo8516i();
        } else if (c0840j.isInProgress() && c0840j.mo8530k() == 42) {
            c0840j.mo8528b(false);
        } else {
            c0840j.mo8528b(true);
        }
    }
}
