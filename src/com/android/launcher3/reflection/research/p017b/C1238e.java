package com.android.launcher3.reflection.research.p017b;

import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* renamed from: com.android.launcher3.reflection.research.b.e */
public final class C1238e extends C0945f implements C0946l {
    /* renamed from: dy */
    private List<C0945f> f542dy = new ArrayList();
    /* renamed from: dz */
    private int f543dz = 0;

    public final String getFeatureName() {
        return "aggregator";
    }

    public C1238e() {
        this.f282dB.clear();
    }

    /* renamed from: X */
    public final C1238e clone() {
        C1238e c1238e = new C1238e();
        for (C0945f U : this.f542dy) {
            c1238e.mo14292a(U.clone());
        }
        return c1238e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.f542dy.size(); i++) {
            sb.append(((C0945f) this.f542dy.get(i)).toString());
        }
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo14292a(C0945f c0945f) {
        this.f542dy.add(c0945f);
        c0945f.f281dA = this;
        this.f543dz += c0945f.mo9182T();
    }

    /* renamed from: a */
    protected final C0951e mo9185a(C0938a<ReflectionEvent> c0938a, ReflectionEvent reflectionEvent) {
        C0951e c0951e = new C0951e(1, this.f543dz);
        int i = 0;
        for (C0945f c0945f : this.f542dy) {
            double[] dArr = c0945f.mo9188b(c0938a, reflectionEvent).f322eR;
            for (int i2 = 0; i2 < dArr.length; i2++) {
                c0951e.f322eR[i2 + i] = dArr[i2];
            }
            i += c0945f.mo9182T();
        }
        return c0951e;
    }

    /* renamed from: T */
    public final int mo9182T() {
        return this.f543dz;
    }

    /* renamed from: a */
    public final void mo9193a(C0945f c0945f, int i) {
        int i2 = 0;
        for (C0945f c0945f2 : this.f542dy) {
            if (c0945f2 == c0945f) {
                break;
            }
            i2 += c0945f2.mo9182T();
        }
        i += i2;
        if (this.f281dA != null) {
            this.f281dA.mo9193a(this, i);
        }
    }

    /* renamed from: a */
    public final void mo9186a(DataInputStream dataInputStream) throws IOException {
        this.f542dy.clear();
        this.f543dz = 0;
        int readInt = dataInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            String readUTF = dataInputStream.readUTF();
            C0945f j = C0945f.m2982j(readUTF);
            if (j == null) {
                String str = "Cannot find extractor with ";
                String valueOf = String.valueOf(readUTF);
                throw new IOException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
            j.mo9186a(dataInputStream);
            mo14292a(j);
        }
    }

    /* renamed from: a */
    public final void mo9187a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.f542dy.size());
        for (C0945f c0945f : this.f542dy) {
            dataOutputStream.writeUTF(C0945f.m2981b(c0945f));
            c0945f.mo9187a(dataOutputStream);
        }
    }

    /* renamed from: g */
    public final void mo9191g(List<String> list) {
        if (list.size() >= 2) {
            Pattern compile = Pattern.compile((String) list.get(0));
            for (C0945f c0945f : this.f542dy) {
                if (compile.matcher(c0945f.getClass().getName()).matches()) {
                    c0945f.mo9191g(list.subList(1, list.size()));
                }
            }
        }
    }

    /* renamed from: e */
    protected final void mo9190e(boolean z) {
        for (C0945f Y : this.f542dy) {
            this.f282dB.putAll(Y.mo9184Y());
        }
    }
}
