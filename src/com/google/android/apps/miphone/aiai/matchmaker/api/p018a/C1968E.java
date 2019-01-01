package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.android.launcher3.AbstractFloatingView;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.E */
public final class C1968E extends b {
    /* renamed from: wk */
    private static volatile C1968E[] f3131wk;
    /* renamed from: id */
    public String f3132id;
    /* renamed from: uE */
    public String f3133uE;
    /* renamed from: uH */
    public boolean f3134uH;
    /* renamed from: uI */
    public int f3135uI;
    /* renamed from: wg */
    public int f3136wg;
    /* renamed from: wl */
    public C1989z[] f3137wl;
    /* renamed from: wm */
    public C1969F[] f3138wm;
    /* renamed from: wn */
    public String f3139wn;
    /* renamed from: wo */
    public String f3140wo;
    /* renamed from: wp */
    public String f3141wp;
    /* renamed from: wq */
    public String f3142wq;
    /* renamed from: wr */
    public float f3143wr;
    /* renamed from: ws */
    public int f3144ws;
    /* renamed from: wt */
    public int f3145wt;
    /* renamed from: wu */
    public int f3146wu;
    /* renamed from: wv */
    public int f3147wv;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            int length;
            switch (lk) {
                case 0:
                    return this;
                case 10:
                    this.f3132id = aVar.readString();
                    break;
                case 18:
                    lk = j.c(aVar, 18);
                    length = this.f3137wl == null ? 0 : this.f3137wl.length;
                    C1989z[] c1989zArr = new C1989z[(lk + length)];
                    if (length != 0) {
                        System.arraycopy(this.f3137wl, 0, c1989zArr, 0, length);
                    }
                    while (length < c1989zArr.length - 1) {
                        c1989zArr[length] = new C1989z();
                        aVar.a(c1989zArr[length]);
                        aVar.lk();
                        length++;
                    }
                    c1989zArr[length] = new C1989z();
                    aVar.a(c1989zArr[length]);
                    this.f3137wl = c1989zArr;
                    break;
                case 26:
                    lk = j.c(aVar, 26);
                    length = this.f3138wm == null ? 0 : this.f3138wm.length;
                    C1969F[] c1969fArr = new C1969F[(lk + length)];
                    if (length != 0) {
                        System.arraycopy(this.f3138wm, 0, c1969fArr, 0, length);
                    }
                    while (length < c1969fArr.length - 1) {
                        c1969fArr[length] = new C1969F();
                        aVar.a(c1969fArr[length]);
                        aVar.lk();
                        length++;
                    }
                    c1969fArr[length] = new C1969F();
                    aVar.a(c1969fArr[length]);
                    this.f3138wm = c1969fArr;
                    break;
                case 34:
                    this.f3139wn = aVar.readString();
                    break;
                case 42:
                    this.f3140wo = aVar.readString();
                    break;
                case 50:
                    this.f3141wp = aVar.readString();
                    break;
                case 58:
                    this.f3142wq = aVar.readString();
                    break;
                case 69:
                    this.f3143wr = Float.intBitsToFloat(aVar.ln());
                    break;
                case 72:
                    this.f3136wg = aVar.ll();
                    break;
                case 80:
                    this.f3144ws = aVar.ll();
                    break;
                case 88:
                    this.f3134uH = aVar.readBool();
                    break;
                case AbstractFloatingView.TYPE_HIDE_BACK_BUTTON /*96*/:
                    this.f3135uI = aVar.ll();
                    break;
                case 104:
                    this.f3145wt = aVar.ll();
                    break;
                case AbstractFloatingView.TYPE_REBIND_SAFE /*112*/:
                    this.f3146wu = aVar.ll();
                    break;
                case 120:
                    this.f3147wv = aVar.ll();
                    break;
                case 130:
                    this.f3133uE = aVar.readString();
                    break;
                default:
                    if (super.a(aVar, lk)) {
                        break;
                    }
                    return this;
            }
        }
    }

    /* renamed from: cp */
    public static C1968E[] m3197cp() {
        if (f3131wk == null) {
            synchronized (f.ahE) {
                if (f3131wk == null) {
                    f3131wk = new C1968E[0];
                }
            }
        }
        return f3131wk;
    }

    public C1968E() {
        this.f3132id = "";
        this.f3137wl = C1989z.m3212cn();
        this.f3138wm = C1969F.m3198cq();
        this.f3139wn = "";
        this.f3140wo = "";
        this.f3141wp = "";
        this.f3142wq = "";
        this.f3143wr = 0.0f;
        this.f3136wg = 0;
        this.f3144ws = 0;
        this.f3134uH = false;
        this.f3135uI = 0;
        this.f3145wt = 0;
        this.f3146wu = 0;
        this.f3147wv = 0;
        this.f3133uE = "";
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (!(this.f3132id == null || this.f3132id.equals(""))) {
            codedOutputByteBufferNano.c(1, this.f3132id);
        }
        int i = 0;
        if (this.f3137wl != null && this.f3137wl.length > 0) {
            for (g gVar : this.f3137wl) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(2, gVar);
                }
            }
        }
        if (this.f3138wm != null && this.f3138wm.length > 0) {
            while (i < this.f3138wm.length) {
                g gVar2 = this.f3138wm[i];
                if (gVar2 != null) {
                    codedOutputByteBufferNano.a(3, gVar2);
                }
                i++;
            }
        }
        if (!(this.f3139wn == null || this.f3139wn.equals(""))) {
            codedOutputByteBufferNano.c(4, this.f3139wn);
        }
        if (!(this.f3140wo == null || this.f3140wo.equals(""))) {
            codedOutputByteBufferNano.c(5, this.f3140wo);
        }
        if (!(this.f3141wp == null || this.f3141wp.equals(""))) {
            codedOutputByteBufferNano.c(6, this.f3141wp);
        }
        if (!(this.f3142wq == null || this.f3142wq.equals(""))) {
            codedOutputByteBufferNano.c(7, this.f3142wq);
        }
        if (Float.floatToIntBits(this.f3143wr) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(8, this.f3143wr);
        }
        if (this.f3136wg != 0) {
            codedOutputByteBufferNano.R(9, this.f3136wg);
        }
        if (this.f3144ws != 0) {
            codedOutputByteBufferNano.R(10, this.f3144ws);
        }
        if (this.f3134uH) {
            codedOutputByteBufferNano.g(11, this.f3134uH);
        }
        if (this.f3135uI != 0) {
            codedOutputByteBufferNano.R(12, this.f3135uI);
        }
        if (this.f3145wt != 0) {
            codedOutputByteBufferNano.R(13, this.f3145wt);
        }
        if (this.f3146wu != 0) {
            codedOutputByteBufferNano.R(14, this.f3146wu);
        }
        if (this.f3147wv != 0) {
            codedOutputByteBufferNano.R(15, this.f3147wv);
        }
        if (!(this.f3133uE == null || this.f3133uE.equals(""))) {
            codedOutputByteBufferNano.c(16, this.f3133uE);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (!(this.f3132id == null || this.f3132id.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(1, this.f3132id);
        }
        int i = 0;
        if (this.f3137wl != null && this.f3137wl.length > 0) {
            int i2 = computeSerializedSize;
            for (g gVar : this.f3137wl) {
                if (gVar != null) {
                    i2 += CodedOutputByteBufferNano.b(2, gVar);
                }
            }
            computeSerializedSize = i2;
        }
        if (this.f3138wm != null && this.f3138wm.length > 0) {
            while (i < this.f3138wm.length) {
                g gVar2 = this.f3138wm[i];
                if (gVar2 != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(3, gVar2);
                }
                i++;
            }
        }
        if (!(this.f3139wn == null || this.f3139wn.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(4, this.f3139wn);
        }
        if (!(this.f3140wo == null || this.f3140wo.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(5, this.f3140wo);
        }
        if (!(this.f3141wp == null || this.f3141wp.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(6, this.f3141wp);
        }
        if (!(this.f3142wq == null || this.f3142wq.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(7, this.f3142wq);
        }
        if (Float.floatToIntBits(this.f3143wr) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(8) + 4;
        }
        if (this.f3136wg != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(9, this.f3136wg);
        }
        if (this.f3144ws != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(10, this.f3144ws);
        }
        if (this.f3134uH) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(11) + 1;
        }
        if (this.f3135uI != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(12, this.f3135uI);
        }
        if (this.f3145wt != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(13, this.f3145wt);
        }
        if (this.f3146wu != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(14, this.f3146wu);
        }
        if (this.f3147wv != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(15, this.f3147wv);
        }
        if (this.f3133uE == null || this.f3133uE.equals("")) {
            return computeSerializedSize;
        }
        return computeSerializedSize + CodedOutputByteBufferNano.d(16, this.f3133uE);
    }
}
