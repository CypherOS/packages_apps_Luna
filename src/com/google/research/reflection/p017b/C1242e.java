package com.google.research.reflection.p017b;

import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.signal.ReflectionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* renamed from: com.google.research.reflection.b.e */
public final class C1242e extends C0949f implements C0950l {
    /* renamed from: dy */
    private List<C0949f> f541dy = new ArrayList();
    /* renamed from: dz */
    private int f542dz = 0;

    public final String getFeatureName() {
        return "aggregator";
    }

    public C1242e() {
        this.f282dB.clear();
    }

    /* renamed from: X */
    public final C1242e clone() {
        C1242e c1242e = new C1242e();
        for (C0949f U : this.f541dy) {
            c1242e.mo14333a(U.clone());
        }
        return c1242e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.f541dy.size(); i++) {
            sb.append(((C0949f) this.f541dy.get(i)).toString());
        }
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo14333a(C0949f c0949f) {
        this.f541dy.add(c0949f);
        c0949f.f281dA = this;
        this.f542dz += c0949f.mo9200T();
    }

    /* renamed from: a */
    protected final C0955e mo9203a(C0942a<ReflectionEvent> c0942a, ReflectionEvent reflectionEvent) {
        C0955e c0955e = new C0955e(1, this.f542dz);
        int i = 0;
        for (C0949f c0949f : this.f541dy) {
            double[] dArr = c0949f.mo9206b(c0942a, reflectionEvent).f322eR;
            for (int i2 = 0; i2 < dArr.length; i2++) {
                c0955e.f322eR[i2 + i] = dArr[i2];
            }
            i += c0949f.mo9200T();
        }
        return c0955e;
    }

    /* renamed from: T */
    public final int mo9200T() {
        return this.f542dz;
    }

    /* renamed from: a */
    public final void mo9211a(C0949f c0949f, int i) {
        int i2 = 0;
        for (C0949f c0949f2 : this.f541dy) {
            if (c0949f2 == c0949f) {
                break;
            }
            i2 += c0949f2.mo9200T();
        }
        i += i2;
        if (this.f281dA != null) {
            this.f281dA.mo9211a(this, i);
        }
    }

    /* renamed from: a */
    public final void mo9204a(DataInputStream dataInputStream) throws IOException {
        this.f541dy.clear();
        this.f542dz = 0;
        int readInt = dataInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            String readUTF = dataInputStream.readUTF();
            C0949f j = C0949f.m2997j(readUTF);
            if (j == null) {
                String str = "Cannot find extractor with ";
                String valueOf = String.valueOf(readUTF);
                throw new IOException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
            j.mo9204a(dataInputStream);
            mo14333a(j);
        }
    }

    /* renamed from: a */
    public final void mo9205a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.f541dy.size());
        for (C0949f c0949f : this.f541dy) {
            dataOutputStream.writeUTF(C0949f.m2996b(c0949f));
            c0949f.mo9205a(dataOutputStream);
        }
    }

    /* renamed from: g */
    public final void mo9209g(List<String> list) {
        if (list.size() >= 2) {
            Pattern compile = Pattern.compile((String) list.get(0));
            for (C0949f c0949f : this.f541dy) {
                if (compile.matcher(c0949f.getClass().getName()).matches()) {
                    c0949f.mo9209g(list.subList(1, list.size()));
                }
            }
        }
    }

    /* renamed from: e */
    protected final void mo9208e(boolean z) {
        for (C0949f Y : this.f541dy) {
            this.f282dB.putAll(Y.mo9202Y());
        }
    }
}
