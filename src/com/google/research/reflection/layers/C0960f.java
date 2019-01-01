package com.google.research.reflection.layers;

import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0945d;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.research.reflection.layers.f */
public class C0960f {
    /* renamed from: eZ */
    public C0942a<C0955e> f336eZ;
    /* renamed from: fa */
    public C0942a<C0955e> f337fa;
    /* renamed from: fb */
    public List<C0954c> f338fb = new ArrayList();

    public C0960f(int i) {
        this.f336eZ = new C0942a(i, true);
        this.f337fa = new C0942a(i, true);
    }

    /* renamed from: ag */
    public final C0960f clone() {
        C0960f c0960f = new C0960f(this.f336eZ.f275dl.length);
        for (C0954c Z : this.f338fb) {
            c0960f.f338fb.add(Z.clone());
        }
        return c0960f;
    }

    /* renamed from: b */
    public final void mo9249b(C0954c c0954c) {
        if (c0954c.mo9222ac() == this.f336eZ.f275dl.length) {
            if (c0954c instanceof C1259d) {
                if (((C1259d) c0954c).f588eG == this.f338fb.size()) {
                    throw new RuntimeException();
                }
            }
            this.f338fb.add(c0954c);
            return;
        }
        int length = this.f336eZ.f275dl.length;
        int ac = c0954c.mo9222ac();
        StringBuilder sb = new StringBuilder(102);
        sb.append("Inconsistent framebuffer size with the added layer: targetsize=");
        sb.append(length);
        sb.append(" layerbuffersize=");
        sb.append(ac);
        throw new RuntimeException(sb.toString());
    }

    /* renamed from: a */
    public final C0955e mo9244a(boolean z, ArrayList<C0945d>[] arrayListArr, C0955e c0955e, boolean z2) throws InvalidValueException {
        if (z2) {
            if (!(this.f338fb.get(this.f338fb.size() - 1) instanceof C1283g)) {
                throw new RuntimeException("Lacks outputlayer");
            }
        }
        int i = 0;
        C0955e c0955e2 = null;
        while (i < this.f338fb.size()) {
            c0955e2 = ((C0954c) this.f338fb.get(i)).mo9217a(z, this, arrayListArr, c0955e);
            i++;
            arrayListArr = null;
            c0955e = c0955e2;
        }
        return c0955e2;
    }

    /* renamed from: aa */
    public final void mo9245aa() {
        for (int i = 0; i < this.f338fb.size(); i++) {
            ((C0954c) this.f338fb.get(i)).mo9220aa();
        }
    }

    public final void update() throws InvalidValueException {
        for (int size = this.f338fb.size() - 1; size >= 0; size--) {
            ((C0954c) this.f338fb.get(size)).update();
        }
    }

    /* renamed from: ah */
    public final int mo9247ah() {
        return ((C0954c) this.f338fb.get(0)).f312dH;
    }

    /* renamed from: b */
    public final void mo9250b(DataInputStream dataInputStream) throws IOException {
        this.f338fb.clear();
        int readInt = dataInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            C0954c c1259d;
            String readUTF = dataInputStream.readUTF();
            if (readUTF.equals("LinearLayer")) {
                c1259d = new C1259d();
            } else if (readUTF.equals("OutputLayer")) {
                c1259d = new C1283g();
            } else if (readUTF.equals("LSTMLayer")) {
                c1259d = new C1252b();
            } else {
                String str = "Unsupported layer type: ";
                String valueOf = String.valueOf(readUTF);
                throw new IOException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
            c1259d.mo9223b(dataInputStream);
            this.f338fb.add(c1259d);
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
    public final C0954c mo9248ai() {
        return (C0954c) this.f338fb.get(this.f338fb.size() - 1);
    }
}
