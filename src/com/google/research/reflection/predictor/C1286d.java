package com.google.research.reflection.predictor;

import com.google.research.reflection.layers.C0954c;
import com.google.research.reflection.layers.C0955e;
import com.google.research.reflection.layers.C0960f;
import com.google.research.reflection.layers.C0963i;
import com.google.research.reflection.layers.C1259d;
import com.google.research.reflection.layers.C1283g;
import com.google.research.reflection.layers.InvalidValueException;
import com.google.research.reflection.p017b.C0949f;
import com.google.research.reflection.p017b.C0950l;
import com.google.research.reflection.p017b.C1240a;
import com.google.research.reflection.p017b.C1241c;
import com.google.research.reflection.p017b.C1242e;
import com.google.research.reflection.p017b.C1243g;
import com.google.research.reflection.p017b.C1244h;
import com.google.research.reflection.p017b.C1245i;
import com.google.research.reflection.p017b.C1246j;
import com.google.research.reflection.p017b.C1247k;
import com.google.research.reflection.p017b.C1281b;
import com.google.research.reflection.signal.ReflectionEvent;
import com.google.research.reflection.signal.ReflectionEvent.ReflectionEventType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.research.reflection.predictor.d */
public class C1286d extends AbstractEventEstimator implements C0950l {
    /* renamed from: dA */
    protected C0950l f620dA;
    /* renamed from: fA */
    protected C0960f f621fA;
    /* renamed from: fB */
    protected C0949f f622fB;
    /* renamed from: fC */
    protected C0955e f623fC = new C0955e(1, 100);

    public String getName() {
        return "neural_predictor";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        C1286d c1286d = new C1286d();
        c1286d.f621fA = this.f621fA.clone();
        c1286d.f623fC = this.f623fC.clone();
        c1286d.f622fB = this.f622fB.clone();
        return c1286d;
    }

    public C1286d() {
        C1242e c1242e = new C1242e();
        c1242e.mo14333a((C0949f) new C1240a());
        c1242e.mo14333a((C0949f) new C1244h());
        c1242e.mo14333a((C0949f) new C1241c());
        c1242e.mo14333a((C0949f) new C1246j());
        c1242e.mo14333a((C0949f) new C1245i());
        c1242e.mo14333a((C0949f) new C1243g());
        c1242e.mo14333a((C0949f) new C1281b());
        c1242e.mo14333a((C0949f) new C1247k());
        C0963i.f342fj = false;
        this.f622fB = c1242e;
        c1242e.f281dA = this;
        this.f621fA = new C0960f(1);
        this.f621fA.mo9249b((C0954c) new C1259d(false, 1, 1, 1, c1242e.mo9200T(), 100, -1, -1, false, false, 0.0f));
        this.f621fA.mo9249b((C0954c) new C1283g(1, 1, 2, 1, 100, 100, -1, -1, false));
    }

    /* renamed from: a */
    public final void mo9211a(C0949f c0949f, int i) {
        if (this.f620dA != null) {
            this.f620dA.mo9211a(c0949f, i);
        }
        C0955e a = ((C1259d) this.f621fA.f338fb.get(0)).mo14338a(this.f621fA);
        int f = a.mo9240f(false);
        for (int i2 = 0; i2 < a.mo9241g(false); i2++) {
            double d = 0.0d;
            for (int i3 = 0; i3 < f; i3++) {
                d += a.mo9235b(false, i3, i2);
            }
            a.mo9238b(false, i, i2, d / ((double) f));
        }
    }

    /* renamed from: a */
    public final C0972k mo14342a(float[] fArr, ReflectionEvent reflectionEvent) {
        C0955e b = this.f622fB.mo9206b(this.f355fG.mo9256ar(), reflectionEvent);
        C0972k c0972k = new C0972k();
        if (mo14348aq().size() == 1) {
            fArr[0] = 1.0f;
        } else if (mo14348aq().size() > 1) {
            try {
                C0955e a = this.f621fA.mo9244a(false, null, b, true);
                for (int i = 0; i < fArr.length; i++) {
                    fArr[i] = (float) a.mo9235b(false, 0, i);
                }
            } catch (InvalidValueException unused) {
                return c0972k;
            }
        }
        c0972k.f365fP = fArr;
        c0972k.f364fO = b.f322eR;
        return c0972k;
    }

    /* renamed from: k */
    public boolean mo9274k(ReflectionEvent reflectionEvent) {
        if (reflectionEvent.mo9281C() != ReflectionEventType.APP_USAGE) {
            if (reflectionEvent.mo9281C() != ReflectionEventType.INSTANT_APP_USAGE) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: i */
    public final C0972k mo14352i(ReflectionEvent reflectionEvent) {
        Integer valueOf = Integer.valueOf(mo14351g(reflectionEvent));
        C0972k c0972k = new C0972k();
        try {
            C0955e b = this.f622fB.mo9206b(this.f355fG.mo9256ar(), reflectionEvent);
            C0960f c0960f = this.f621fA;
            c0960f.f336eZ.clear();
            c0960f.f337fa.clear();
            for (C0954c ab : c0960f.f338fb) {
                ab.mo9221ab();
            }
            this.f621fA.mo9244a(true, null, b, true);
            Arrays.fill(this.f623fC.f322eR, 0.0d);
            this.f623fC.mo9238b(false, 0, valueOf.intValue(), 1.0d);
            C0960f c0960f2 = this.f621fA;
            C0955e c0955e = this.f623fC;
            if (c0960f2.f338fb.get(c0960f2.f338fb.size() - 1) instanceof C1283g) {
                if (c0955e == null) {
                    c0955e = new C0955e(1, c0960f2.mo9248ai().f320ew);
                    C0955e c0955e2 = new C0955e(1, c0960f2.mo9248ai().f320ew);
                    c0960f2.f336eZ.mo9188a((Object) c0955e);
                    c0960f2.f337fa.mo9188a((Object) c0955e2);
                } else {
                    C0954c c0954c;
                    c0960f2.f336eZ.mo9188a((Object) c0955e);
                    c0960f2.f337fa.mo9188a(null);
                    int i = c0960f2.f336eZ.f277dn;
                    c0960f2.mo9245aa();
                    int i2 = c0960f2.f336eZ.f274dk;
                    int size = c0960f2.f338fb.size() - 1;
                    for (int i3 = size; i3 >= 0; i3--) {
                        c0954c = (C0954c) c0960f2.f338fb.get(i3);
                        if (c0954c.f319ev) {
                            if (c0954c.f315er.f277dn != i) {
                                throw new RuntimeException("backward: sparse input vector has a different frame index from the target frame index");
                            }
                        } else if (c0954c.f314eq.f277dn != i) {
                            int i4 = c0954c.f314eq.f277dn;
                            StringBuilder sb = new StringBuilder(110);
                            sb.append("backward: dense input vector has a different frame index from the target frame index: ");
                            sb.append(i4);
                            sb.append("!=");
                            sb.append(i);
                            throw new RuntimeException(sb.toString());
                        }
                    }
                    i2--;
                    i = i2;
                    while (i >= 0 && i2 - i <= 0) {
                        C0955e c0955e3 = (C0955e) c0960f2.f337fa.mo9187a(i);
                        C0955e c0955e4 = (C0955e) c0960f2.f336eZ.mo9187a(i);
                        int i5 = size;
                        while (i5 >= 0) {
                            c0954c = (C0954c) c0960f2.f338fb.get(i5);
                            c0954c.mo9219a(c0960f2, i, c0955e4, c0954c.f318eu, c0955e3);
                            i5--;
                            c0955e4 = c0954c.f317et;
                        }
                        i--;
                    }
                }
                this.f621fA.update();
                c0972k.f364fO = b.f322eR;
                return c0972k;
            }
            throw new RuntimeException("Lacks outputlayer");
        } catch (InvalidValueException unused) {
        }
    }

    /* renamed from: b */
    public final void mo9269b(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(C0949f.m2996b(this.f622fB));
        this.f622fB.mo9205a(dataOutputStream);
        C0960f c0960f = this.f621fA;
        dataOutputStream.writeInt(c0960f.f338fb.size());
        for (C0954c c0954c : c0960f.f338fb) {
            dataOutputStream.writeUTF(c0954c.getName());
            c0954c.mo9224b(dataOutputStream);
        }
        dataOutputStream.writeUTF("NeuralNet");
    }

    /* renamed from: a */
    public final void mo9266a(DataInputStream dataInputStream, ReflectionEvent reflectionEvent) throws IOException {
        String readUTF = dataInputStream.readUTF();
        C0949f j = C0949f.m2997j(readUTF);
        if (j == null) {
            String str = "Cannot find extractor with ";
            readUTF = String.valueOf(readUTF);
            throw new IOException(readUTF.length() != 0 ? str.concat(readUTF) : new String(str));
        }
        j.mo9204a(dataInputStream);
        j.f281dA = this;
        this.f621fA = new C0960f(1);
        this.f621fA.mo9250b(dataInputStream);
        int ah;
        int T;
        StringBuilder sb;
        if (this.f621fA.mo9247ah() != j.mo9200T()) {
            ah = this.f621fA.mo9247ah();
            T = j.mo9200T();
            sb = new StringBuilder(76);
            sb.append("Model to be loaded has an inconsistent input size:");
            sb.append(ah);
            sb.append(" != ");
            sb.append(T);
            throw new IOException(sb.toString());
        } else if (this.f621fA.mo9248ai().f320ew != mo14346ao()) {
            ah = this.f621fA.mo9248ai().f320ew;
            T = mo14346ao();
            sb = new StringBuilder(57);
            sb.append("Inconsistent model output size...");
            sb.append(ah);
            sb.append("!=");
            sb.append(T);
            throw new IOException(sb.toString());
        }
    }

    /* renamed from: k */
    public final void mo14353k(String str) {
        this.f622fB.mo9209g(Arrays.asList(new String[]{".*", str}));
    }

    /* renamed from: a */
    public final void mo9267a(Integer num, Integer num2, String str) {
        double d;
        C1283g c1283g = (C1283g) this.f621fA.mo9248ai();
        C0955e a = c1283g.mo14338a(this.f621fA);
        int g = a.mo9241g(false);
        int i = 0;
        while (true) {
            d = 0.0d;
            if (i >= a.mo9240f(false)) {
                break;
            }
            for (int i2 = 0; i2 < g; i2++) {
                d += a.mo9235b(false, i, i2);
            }
            double d2 = d / ((double) g);
            if (!num.equals(num2)) {
                a.mo9238b(false, i, num.intValue(), a.mo9235b(false, i, num2.intValue()));
            }
            a.mo9238b(false, i, num2.intValue(), d2);
            i++;
        }
        C0955e c0955e = c1283g.f586eE;
        for (int i3 = 0; i3 < g; i3++) {
            d += c0955e.f322eR[i3];
        }
        d /= (double) g;
        if (!num.equals(num2)) {
            c0955e.f322eR[num.intValue()] = c0955e.f322eR[num2.intValue()];
        }
        c0955e.f322eR[num2.intValue()] = d;
    }

    /* renamed from: Y */
    public final Map<String, Boolean> mo9265Y() {
        return new HashMap(this.f622fB.mo9202Y());
    }
}
