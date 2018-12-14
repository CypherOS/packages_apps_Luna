package com.android.launcher3.reflection.p014d;

import android.util.Log;
import com.android.launcher3.Utilities;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.reflection.p015e.C0811a.C0813b;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.d.d */
public class C0809d {
    /* renamed from: bO */
    private final File f149bO;
    /* renamed from: bP */
    private final long f150bP;

    public C0809d(File file, long j) {
        this.f149bO = file;
        this.f150bP = j;
    }

    /* renamed from: a */
    public final synchronized void mo8501a(C0813b c0813b) {
        log(Arrays.asList(new C0813b[]{c0813b}));
        if (this.f149bO.exists() && this.f149bO.length() > this.f150bP) {
            List t = mo8503t();
            if (this.f149bO.delete()) {
                log(t.subList(t.size() / 2, t.size()));
            }
        }
    }

    void log(List<C0813b> list) {
        Throwable e;
        Preconditions.assertNonUiThread();
        int r0v0 = 0;
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.f149bO, true)));
            try {
                for (C0813b c0813b : list) {
                    int serializedSize = c0813b.getSerializedSize();
                    if (r0v0 == 0 || r0v0.length < serializedSize) {
                        r0v0 = new byte[serializedSize];
                    }
                    MessageNano.toByteArray(c0813b, r0v0, 0, serializedSize);
                    dataOutputStream.writeInt(serializedSize);
                    dataOutputStream.write(r0v0, 0, serializedSize);
                }
                Utilities.closeSilently(dataOutputStream);
            } catch (IOException e2) {
                e = e2;
                r0v0 = dataOutputStream;
                try {
                    Log.e("Reflection.ClientActLog", "Failed to write the client action log file", e);
                    Utilities.closeSilently(r0v0);
                } catch (Throwable th) {
                    e = th;
                    Utilities.closeSilently(r0v0);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                r0v0 = dataOutputStream;
                Utilities.closeSilently(r0v0);
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            Log.e("Reflection.ClientActLog", "Failed to write the client action log file", e);
            Utilities.closeSilently(r0v0);
        }
    }

    /* renamed from: t */
    public final synchronized List<C0813b> mo8503t() {
        ArrayList arrayList;
        Throwable e;
        Throwable th;
        Preconditions.assertNonUiThread();
        arrayList = new ArrayList();
        int r1v0 = 0;
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(this.f149bO)));
            while (true) {
                try {
                    int readInt = dataInputStream.readInt();
                    if (r1v0 == 0 || r1v0.length < readInt) {
                        r1v0 = new byte[readInt];
                    }
                    dataInputStream.read(r1v0, 0, readInt);
                    C0813b d = C0813b.m2613d(CodedInputByteBufferNano.newInstance(r1v0, 0, readInt));
                    if (d != null) {
                        arrayList.add(d);
                    }
                } catch (EOFException unused) {
                    r1v0 = dataInputStream;
                    Utilities.closeSilently(r1v0);
                    return arrayList;
                } catch (IOException e2) {
                    e = e2;
                    try {
                        Log.e("Reflection.ClientActLog", "Failed in loading the log file", e);
                        Utilities.closeSilently(dataInputStream);
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        Utilities.closeSilently(dataInputStream);
                        throw th;
                    }
                }
            }
        } catch (EOFException unused2) {
            Utilities.closeSilently(r1v0);
            return arrayList;
        } catch (Throwable e3) {
            Throwable th3 = e3;
            dataInputStream = 0;
            e = th3;
            Log.e("Reflection.ClientActLog", "Failed in loading the log file", e);
            Utilities.closeSilently(dataInputStream);
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            dataInputStream = 0;
            Utilities.closeSilently(dataInputStream);
            throw th;
        }
        return arrayList;
    }
}
