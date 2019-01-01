package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.android.launcher3.AbstractFloatingView;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;
import com.google.protobuf.nano.g;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.u */
public final class C1986u extends b {
    /* renamed from: vw */
    private static volatile C1986u[] f3235vw;
    public int action;
    public int edgeFlags;
    public float orientation;
    public float toolMajor;
    public float toolMinor;
    /* renamed from: vA */
    public int f3236vA;
    /* renamed from: vB */
    public int f3237vB;
    /* renamed from: vC */
    public long f3238vC;
    /* renamed from: vD */
    public int f3239vD;
    /* renamed from: vE */
    public float f3240vE;
    /* renamed from: vF */
    public float f3241vF;
    /* renamed from: vG */
    public int f3242vG;
    /* renamed from: vH */
    public float f3243vH;
    /* renamed from: vI */
    public float f3244vI;
    /* renamed from: vJ */
    public C1973K f3245vJ;
    /* renamed from: vK */
    public float f3246vK;
    /* renamed from: vL */
    public float f3247vL;
    /* renamed from: vM */
    public long f3248vM;
    /* renamed from: vx */
    public int f3249vx;
    /* renamed from: vy */
    public int f3250vy;
    /* renamed from: vz */
    public int f3251vz;
    /* renamed from: x */
    public float f3252x;
    /* renamed from: y */
    public float f3253y;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            switch (lk) {
                case 0:
                    return this;
                case 8:
                    this.action = aVar.ll();
                    break;
                case 16:
                    this.f3249vx = aVar.ll();
                    break;
                case 24:
                    this.f3250vy = aVar.ll();
                    break;
                case AbstractFloatingView.TYPE_ON_BOARD_POPUP /*32*/:
                    this.f3251vz = aVar.ll();
                    break;
                case 40:
                    this.f3236vA = aVar.ll();
                    break;
                case 48:
                    this.f3237vB = aVar.ll();
                    break;
                case 56:
                    this.f3238vC = aVar.lm();
                    break;
                case 64:
                    this.edgeFlags = aVar.ll();
                    break;
                case 72:
                    this.f3239vD = aVar.ll();
                    break;
                case 85:
                    this.orientation = Float.intBitsToFloat(aVar.ln());
                    break;
                case 93:
                    this.f3240vE = Float.intBitsToFloat(aVar.ln());
                    break;
                case 101:
                    this.f3241vF = Float.intBitsToFloat(aVar.ln());
                    break;
                case 104:
                    this.f3242vG = aVar.ll();
                    break;
                case 117:
                    this.toolMajor = Float.intBitsToFloat(aVar.ln());
                    break;
                case 125:
                    this.toolMinor = Float.intBitsToFloat(aVar.ln());
                    break;
                case 133:
                    this.f3252x = Float.intBitsToFloat(aVar.ln());
                    break;
                case 141:
                    this.f3253y = Float.intBitsToFloat(aVar.ln());
                    break;
                case 149:
                    this.f3243vH = Float.intBitsToFloat(aVar.ln());
                    break;
                case 157:
                    this.f3244vI = Float.intBitsToFloat(aVar.ln());
                    break;
                case 162:
                    if (this.f3245vJ == null) {
                        this.f3245vJ = new C1973K();
                    }
                    aVar.a(this.f3245vJ);
                    break;
                case 173:
                    this.f3246vK = Float.intBitsToFloat(aVar.ln());
                    break;
                case 181:
                    this.f3247vL = Float.intBitsToFloat(aVar.ln());
                    break;
                case 184:
                    this.f3248vM = aVar.lm();
                    break;
                default:
                    if (super.a(aVar, lk)) {
                        break;
                    }
                    return this;
            }
        }
    }

    /* renamed from: cl */
    public static C1986u[] m3210cl() {
        if (f3235vw == null) {
            synchronized (f.ahE) {
                if (f3235vw == null) {
                    f3235vw = new C1986u[0];
                }
            }
        }
        return f3235vw;
    }

    public C1986u() {
        this.action = 0;
        this.f3249vx = 0;
        this.f3250vy = 0;
        this.f3251vz = 0;
        this.f3236vA = 0;
        this.f3237vB = 0;
        this.f3238vC = 0;
        this.edgeFlags = 0;
        this.f3239vD = 0;
        this.orientation = 0.0f;
        this.f3240vE = 0.0f;
        this.f3241vF = 0.0f;
        this.f3242vG = 0;
        this.toolMajor = 0.0f;
        this.toolMinor = 0.0f;
        this.f3252x = 0.0f;
        this.f3253y = 0.0f;
        this.f3243vH = 0.0f;
        this.f3244vI = 0.0f;
        this.f3245vJ = null;
        this.f3246vK = 0.0f;
        this.f3247vL = 0.0f;
        this.f3248vM = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.action != 0) {
            codedOutputByteBufferNano.R(1, this.action);
        }
        if (this.f3249vx != 0) {
            codedOutputByteBufferNano.R(2, this.f3249vx);
        }
        if (this.f3250vy != 0) {
            codedOutputByteBufferNano.R(3, this.f3250vy);
        }
        if (this.f3251vz != 0) {
            codedOutputByteBufferNano.R(4, this.f3251vz);
        }
        if (this.f3236vA != 0) {
            codedOutputByteBufferNano.R(5, this.f3236vA);
        }
        if (this.f3237vB != 0) {
            codedOutputByteBufferNano.R(6, this.f3237vB);
        }
        if (this.f3238vC != 0) {
            codedOutputByteBufferNano.j(7, this.f3238vC);
        }
        if (this.edgeFlags != 0) {
            codedOutputByteBufferNano.R(8, this.edgeFlags);
        }
        if (this.f3239vD != 0) {
            codedOutputByteBufferNano.R(9, this.f3239vD);
        }
        if (Float.floatToIntBits(this.orientation) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(10, this.orientation);
        }
        if (Float.floatToIntBits(this.f3240vE) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(11, this.f3240vE);
        }
        if (Float.floatToIntBits(this.f3241vF) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(12, this.f3241vF);
        }
        if (this.f3242vG != 0) {
            codedOutputByteBufferNano.R(13, this.f3242vG);
        }
        if (Float.floatToIntBits(this.toolMajor) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(14, this.toolMajor);
        }
        if (Float.floatToIntBits(this.toolMinor) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(15, this.toolMinor);
        }
        if (Float.floatToIntBits(this.f3252x) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(16, this.f3252x);
        }
        if (Float.floatToIntBits(this.f3253y) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(17, this.f3253y);
        }
        if (Float.floatToIntBits(this.f3243vH) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(18, this.f3243vH);
        }
        if (Float.floatToIntBits(this.f3244vI) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(19, this.f3244vI);
        }
        if (this.f3245vJ != null) {
            codedOutputByteBufferNano.a(20, this.f3245vJ);
        }
        if (Float.floatToIntBits(this.f3246vK) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(21, this.f3246vK);
        }
        if (Float.floatToIntBits(this.f3247vL) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(22, this.f3247vL);
        }
        if (this.f3248vM != 0) {
            codedOutputByteBufferNano.j(23, this.f3248vM);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.action != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(1, this.action);
        }
        if (this.f3249vx != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(2, this.f3249vx);
        }
        if (this.f3250vy != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(3, this.f3250vy);
        }
        if (this.f3251vz != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(4, this.f3251vz);
        }
        if (this.f3236vA != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(5, this.f3236vA);
        }
        if (this.f3237vB != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(6, this.f3237vB);
        }
        if (this.f3238vC != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.l(7, this.f3238vC);
        }
        if (this.edgeFlags != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(8, this.edgeFlags);
        }
        if (this.f3239vD != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(9, this.f3239vD);
        }
        if (Float.floatToIntBits(this.orientation) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(10) + 4;
        }
        if (Float.floatToIntBits(this.f3240vE) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(11) + 4;
        }
        if (Float.floatToIntBits(this.f3241vF) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(12) + 4;
        }
        if (this.f3242vG != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(13, this.f3242vG);
        }
        if (Float.floatToIntBits(this.toolMajor) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(14) + 4;
        }
        if (Float.floatToIntBits(this.toolMinor) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(15) + 4;
        }
        if (Float.floatToIntBits(this.f3252x) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(16) + 4;
        }
        if (Float.floatToIntBits(this.f3253y) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(17) + 4;
        }
        if (Float.floatToIntBits(this.f3243vH) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(18) + 4;
        }
        if (Float.floatToIntBits(this.f3244vI) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(19) + 4;
        }
        if (this.f3245vJ != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(20, this.f3245vJ);
        }
        if (Float.floatToIntBits(this.f3246vK) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(21) + 4;
        }
        if (Float.floatToIntBits(this.f3247vL) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(22) + 4;
        }
        if (this.f3248vM != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.l(23, this.f3248vM);
        }
        return computeSerializedSize;
    }
}
