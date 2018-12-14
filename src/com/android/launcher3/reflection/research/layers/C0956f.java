package com.android.launcher3.reflection.research.layers;

import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0941d;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.launcher3.reflection.research.layers.f */
public class C0956f {
    /* renamed from: eZ */
    public C0938a<C0951e> f336eZ;
    /* renamed from: fa */
    public C0938a<C0951e> f337fa;
    /* renamed from: fb */
    public List<C0950c> f338fb = new ArrayList();

    public C0956f(int i) {
        this.f336eZ = new C0938a(i, true);
        this.f337fa = new C0938a(i, true);
    }

    /* renamed from: ag */
    public final C0956f clone() {
        C0956f c0956f = new C0956f(this.f336eZ.f275dl.length);
        for (C0950c Z : this.f338fb) {
            c0956f.f338fb.add(Z.clone());
        }
        return c0956f;
    }

    /* renamed from: b */
    public final void mo9231b(C0950c c0950c) {
        if (c0950c.mo9204ac() == this.f336eZ.f275dl.length) {
            if (c0950c instanceof C1255d) {
                if (((C1255d) c0950c).f589eG == this.f338fb.size()) {
                    throw new RuntimeException();
                }
            }
            this.f338fb.add(c0950c);
            return;
        }
        int length = this.f336eZ.f275dl.length;
        int ac = c0950c.mo9204ac();
        StringBuilder sb = new StringBuilder(102);
        sb.append("Inconsistent framebuffer size with the added layer: targetsize=");
        sb.append(length);
        sb.append(" layerbuffersize=");
        sb.append(ac);
        throw new RuntimeException(sb.toString());
    }

    /* renamed from: a */
    public final C0951e mo9226a(boolean z, ArrayList<C0941d>[] arrayListArr, C0951e c0951e, boolean z2) throws InvalidValueException {
        if (z2) {
            if (!(this.f338fb.get(this.f338fb.size() - 1) instanceof C1278g)) {
                throw new RuntimeException("Lacks outputlayer");
            }
        }
        int i = 0;
        C0951e c0951e2 = null;
        while (i < this.f338fb.size()) {
            c0951e2 = ((C0950c) this.f338fb.get(i)).mo9199a(z, this, arrayListArr, c0951e);
            i++;
            arrayListArr = null;
            c0951e = c0951e2;
        }
        return c0951e2;
    }

    /* renamed from: aa */
    public final void mo9227aa() {
        for (int i = 0; i < this.f338fb.size(); i++) {
            ((C0950c) this.f338fb.get(i)).mo9202aa();
        }
    }

    public final void update() throws InvalidValueException {
        for (int size = this.f338fb.size() - 1; size >= 0; size--) {
            ((C0950c) this.f338fb.get(size)).update();
        }
    }

    /* renamed from: ah */
    public final int mo9229ah() {
        return ((C0950c) this.f338fb.get(0)).f312dH;
    }

    /* renamed from: b */
    public final void mo9232b(DataInputStream dataInputStream) throws IOException {
        this.f338fb.clear();
        int readInt = dataInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            C0950c c1255d;
            String readUTF = dataInputStream.readUTF();
            if (readUTF.equals("LinearLayer")) {
                c1255d = new C1255d();
            } else if (readUTF.equals("OutputLayer")) {
                c1255d = new C1278g();
            } else if (readUTF.equals("LSTMLayer")) {
                c1255d = new C1248b();
            } else {
                String str = "Unsupported layer type: ";
                String valueOf = String.valueOf(readUTF);
                throw new IOException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
            c1255d.mo9205b(dataInputStream);
            this.f338fb.add(c1255d);
        }
        String readUTF2 = dataInputStream.readUTF();
        if (!readUTF2.equals("NeuralNet")) {
            StringBuilder sb = new StringBuilder(String.valueOf(readUTF2).length() + 45);
            sb.append("Inconsistent ending: [");
            sb.append(readUTF2);
            sb.append("] expected: [NeuralNet]");
            throw new IOException(sb.toString());
        }
    }

    /* renamed from: ai */
    public final C0950c mo9230ai() {
        return (C0950c) this.f338fb.get(this.f338fb.size() - 1);
    }
}
