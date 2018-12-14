package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.layers.C0950c;
import com.android.launcher3.reflection.research.layers.C0951e;
import com.android.launcher3.reflection.research.layers.C0956f;
import com.android.launcher3.reflection.research.layers.C0959i;
import com.android.launcher3.reflection.research.layers.C1255d;
import com.android.launcher3.reflection.research.layers.C1278g;
import com.android.launcher3.reflection.research.layers.InvalidValueException;
import com.android.launcher3.reflection.research.p017b.C0945f;
import com.android.launcher3.reflection.research.p017b.C0946l;
import com.android.launcher3.reflection.research.p017b.C1236a;
import com.android.launcher3.reflection.research.p017b.C1237c;
import com.android.launcher3.reflection.research.p017b.C1238e;
import com.android.launcher3.reflection.research.p017b.C1239g;
import com.android.launcher3.reflection.research.p017b.C1240h;
import com.android.launcher3.reflection.research.p017b.C1241i;
import com.android.launcher3.reflection.research.p017b.C1242j;
import com.android.launcher3.reflection.research.p017b.C1243k;
import com.android.launcher3.reflection.research.p017b.C1276b;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import com.android.launcher3.reflection.research.signal.ReflectionEvent.ReflectionEventType;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.android.launcher3.reflection.research.predictor.d */
public class C1281d extends AbstractEventEstimator implements C0946l {
    /* renamed from: dA */
    protected C0946l f621dA;
    /* renamed from: fA */
    protected C0956f f622fA;
    /* renamed from: fB */
    protected C0945f f623fB;
    /* renamed from: fC */
    protected C0951e f624fC = new C0951e(1, 100);

    public String getName() {
        return "neural_predictor";
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        C1281d c1281d = new C1281d();
        c1281d.f622fA = this.f622fA.clone();
        c1281d.f624fC = this.f624fC.clone();
        c1281d.f623fB = this.f623fB.clone();
        return c1281d;
    }

    public C1281d() {
        C1238e c1238e = new C1238e();
        c1238e.mo14292a((C0945f) new C1236a());
        c1238e.mo14292a((C0945f) new C1240h());
        c1238e.mo14292a((C0945f) new C1237c());
        c1238e.mo14292a((C0945f) new C1242j());
        c1238e.mo14292a((C0945f) new C1241i());
        c1238e.mo14292a((C0945f) new C1239g());
        c1238e.mo14292a((C0945f) new C1276b());
        c1238e.mo14292a((C0945f) new C1243k());
        C0959i.f342fj = false;
        this.f623fB = c1238e;
        c1238e.f281dA = this;
        this.f622fA = new C0956f(1);
        this.f622fA.mo9231b((C0950c) new C1255d(false, 1, 1, 1, c1238e.mo9182T(), 100, -1, -1, false, false, 0.0f));
        this.f622fA.mo9231b((C0950c) new C1278g(1, 1, 2, 1, 100, 100, -1, -1, false));
    }

    /* renamed from: a */
    public final void mo9193a(C0945f c0945f, int i) {
        if (this.f621dA != null) {
            this.f621dA.mo9193a(c0945f, i);
        }
        C0951e a = ((C1255d) this.f622fA.f338fb.get(0)).mo14297a(this.f622fA);
        int f = a.mo9222f(false);
        for (int i2 = 0; i2 < a.mo9223g(false); i2++) {
            double d = 0.0d;
            for (int i3 = 0; i3 < f; i3++) {
                d += a.mo9217b(false, i3, i2);
            }
            a.mo9220b(false, i, i2, d / ((double) f));
        }
    }

    /* renamed from: a */
    public final C0968k mo14301a(float[] fArr, ReflectionEvent reflectionEvent) {
        C0951e b = this.f623fB.mo9188b(this.f355fG.mo9238ar(), reflectionEvent);
        C0968k c0968k = new C0968k();
        if (mo14307aq().size() == 1) {
            fArr[0] = 1.0f;
        } else if (mo14307aq().size() > 1) {
            try {
                C0951e a = this.f622fA.mo9226a(false, null, b, true);
                for (int i = 0; i < fArr.length; i++) {
                    fArr[i] = (float) a.mo9217b(false, 0, i);
                }
            } catch (InvalidValueException unused) {
                return c0968k;
            }
        }
        c0968k.f365fP = fArr;
        c0968k.f364fO = b.f322eR;
        return c0968k;
    }

    /* renamed from: k */
    public boolean mo9256k(ReflectionEvent reflectionEvent) {
        if (reflectionEvent.mo9263C() != ReflectionEventType.APP_USAGE) {
            if (reflectionEvent.mo9263C() != ReflectionEventType.INSTANT_APP_USAGE) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: i */
    public final C0968k mo14311i(ReflectionEvent reflectionEvent) {
        Integer valueOf = Integer.valueOf(mo14310g(reflectionEvent));
        C0968k c0968k = new C0968k();
        try {
            C0951e b = this.f623fB.mo9188b(this.f355fG.mo9238ar(), reflectionEvent);
            C0956f c0956f = this.f622fA;
            c0956f.f336eZ.clear();
            c0956f.f337fa.clear();
            for (C0950c ab : c0956f.f338fb) {
                ab.mo9203ab();
            }
            this.f622fA.mo9226a(true, null, b, true);
            Arrays.fill(this.f624fC.f322eR, 0.0d);
            this.f624fC.mo9220b(false, 0, valueOf.intValue(), 1.0d);
            C0956f c0956f2 = this.f622fA;
            C0951e c0951e = this.f624fC;
            if (c0956f2.f338fb.get(c0956f2.f338fb.size() - 1) instanceof C1278g) {
                if (c0951e == null) {
                    c0951e = new C0951e(1, c0956f2.mo9230ai().f320ew);
                    C0951e c0951e2 = new C0951e(1, c0956f2.mo9230ai().f320ew);
                    c0956f2.f336eZ.mo9170a((Object) c0951e);
                    c0956f2.f337fa.mo9170a((Object) c0951e2);
                } else {
                    C0950c c0950c;
                    c0956f2.f336eZ.mo9170a((Object) c0951e);
                    c0956f2.f337fa.mo9170a(null);
                    int i = c0956f2.f336eZ.f277dn;
                    c0956f2.mo9227aa();
                    int i2 = c0956f2.f336eZ.f274dk;
                    int size = c0956f2.f338fb.size() - 1;
                    for (int i3 = size; i3 >= 0; i3--) {
                        c0950c = (C0950c) c0956f2.f338fb.get(i3);
                        if (c0950c.f319ev) {
                            if (c0950c.f315er.f277dn != i) {
                                throw new RuntimeException("backward: sparse input vector has a different frame index from the target frame index");
                            }
                        } else if (c0950c.f314eq.f277dn != i) {
                            int i4 = c0950c.f314eq.f277dn;
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
                        C0951e c0951e3 = (C0951e) c0956f2.f337fa.mo9169a(i);
                        C0951e c0951e4 = (C0951e) c0956f2.f336eZ.mo9169a(i);
                        int i5 = size;
                        while (i5 >= 0) {
                            c0950c = (C0950c) c0956f2.f338fb.get(i5);
                            c0950c.mo9201a(c0956f2, i, c0951e4, c0950c.f318eu, c0951e3);
                            i5--;
                            c0951e4 = c0950c.f317et;
                        }
                        i--;
                    }
                }
                this.f622fA.update();
                c0968k.f364fO = b.f322eR;
                return c0968k;
            }
            throw new RuntimeException("Lacks outputlayer");
        } catch (InvalidValueException unused) {
        }
    }

    /* renamed from: b */
    public final void mo9251b(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(C0945f.m2981b(this.f623fB));
        this.f623fB.mo9187a(dataOutputStream);
        C0956f c0956f = this.f622fA;
        dataOutputStream.writeInt(c0956f.f338fb.size());
        for (C0950c c0950c : c0956f.f338fb) {
            dataOutputStream.writeUTF(c0950c.getName());
            c0950c.mo9206b(dataOutputStream);
        }
        dataOutputStream.writeUTF("NeuralNet");
    }

    /* renamed from: a */
    public final void mo9248a(DataInputStream dataInputStream, ReflectionEvent reflectionEvent) throws IOException {
        String readUTF = dataInputStream.readUTF();
        C0945f j = C0945f.m2982j(readUTF);
        if (j == null) {
            String str = "Cannot find extractor with ";
            readUTF = String.valueOf(readUTF);
            throw new IOException(readUTF.length() != 0 ? str.concat(readUTF) : new String(str));
        }
        j.mo9186a(dataInputStream);
        j.f281dA = this;
        this.f622fA = new C0956f(1);
        this.f622fA.mo9232b(dataInputStream);
        int ah;
        int T;
        StringBuilder sb;
        if (this.f622fA.mo9229ah() != j.mo9182T()) {
            ah = this.f622fA.mo9229ah();
            T = j.mo9182T();
            sb = new StringBuilder(76);
            sb.append("Model to be loaded has an inconsistent input size:");
            sb.append(ah);
            sb.append(" != ");
            sb.append(T);
            throw new IOException(sb.toString());
        } else if (this.f622fA.mo9230ai().f320ew != mo14305ao()) {
            ah = this.f622fA.mo9230ai().f320ew;
            T = mo14305ao();
            sb = new StringBuilder(57);
            sb.append("Inconsistent model output size...");
            sb.append(ah);
            sb.append("!=");
            sb.append(T);
            throw new IOException(sb.toString());
        }
    }

    /* renamed from: k */
    public final void mo14312k(String str) {
        this.f623fB.mo9191g(Arrays.asList(new String[]{".*", str}));
    }

    /* renamed from: a */
    public final void mo9249a(Integer num, Integer num2, String str) {
        double d;
        C1278g c1278g = (C1278g) this.f622fA.mo9230ai();
        C0951e a = c1278g.mo14297a(this.f622fA);
        int g = a.mo9223g(false);
        int i = 0;
        while (true) {
            d = 0.0d;
            if (i >= a.mo9222f(false)) {
                break;
            }
            for (int i2 = 0; i2 < g; i2++) {
                d += a.mo9217b(false, i, i2);
            }
            double d2 = d / ((double) g);
            if (!num.equals(num2)) {
                a.mo9220b(false, i, num.intValue(), a.mo9217b(false, i, num2.intValue()));
            }
            a.mo9220b(false, i, num2.intValue(), d2);
            i++;
        }
        C0951e c0951e = c1278g.f587eE;
        for (int i3 = 0; i3 < g; i3++) {
            d += c0951e.f322eR[i3];
        }
        d /= (double) g;
        if (!num.equals(num2)) {
            c0951e.f322eR[num.intValue()] = c0951e.f322eR[num2.intValue()];
        }
        c0951e.f322eR[num2.intValue()] = d;
    }

    /* renamed from: Y */
    public final Map<String, Boolean> mo9247Y() {
        return new HashMap(this.f623fB.mo9184Y());
    }
}
