package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.NanoEnumValue;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.I */
public final class C1972I extends b {
    /* renamed from: wE */
    private static volatile C1972I[] f3157wE;
    public String name;
    @NanoEnumValue(legacy = false, value = C0857J.class)
    public int type;
    /* renamed from: wF */
    public String f3158wF;
    /* renamed from: wG */
    public int f3159wG;
    /* renamed from: wH */
    public float f3160wH;
    /* renamed from: wI */
    public long f3161wI;
    /* renamed from: wJ */
    public C1971H f3162wJ;
    /* renamed from: wf */
    public String f3163wf;

    /* renamed from: cr */
    public static C1972I[] m3199cr() {
        if (f3157wE == null) {
            synchronized (f.ahE) {
                if (f3157wE == null) {
                    f3157wE = new C1972I[0];
                }
            }
        }
        return f3157wE;
    }

    public C1972I() {
        this.name = "";
        this.type = 0;
        this.f3158wF = "";
        this.f3159wG = 0;
        this.f3160wH = 0.0f;
        this.f3161wI = 0;
        this.f3162wJ = null;
        this.f3163wf = "";
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (!(this.name == null || this.name.equals(""))) {
            codedOutputByteBufferNano.c(1, this.name);
        }
        if (!(this.f3158wF == null || this.f3158wF.equals(""))) {
            codedOutputByteBufferNano.c(2, this.f3158wF);
        }
        if (this.f3159wG != 0) {
            codedOutputByteBufferNano.R(3, this.f3159wG);
        }
        if (Float.floatToIntBits(this.f3160wH) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.c(4, this.f3160wH);
        }
        if (this.f3161wI != 0) {
            codedOutputByteBufferNano.j(5, this.f3161wI);
        }
        if (this.f3162wJ != null) {
            codedOutputByteBufferNano.a(6, this.f3162wJ);
        }
        if (this.type != 0) {
            codedOutputByteBufferNano.R(7, this.type);
        }
        if (!(this.f3163wf == null || this.f3163wf.equals(""))) {
            codedOutputByteBufferNano.c(9, this.f3163wf);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (!(this.name == null || this.name.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(1, this.name);
        }
        if (!(this.f3158wF == null || this.f3158wF.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(2, this.f3158wF);
        }
        if (this.f3159wG != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(3, this.f3159wG);
        }
        if (Float.floatToIntBits(this.f3160wH) != Float.floatToIntBits(0.0f)) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(4) + 4;
        }
        if (this.f3161wI != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.l(5, this.f3161wI);
        }
        if (this.f3162wJ != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(6, this.f3162wJ);
        }
        if (this.type != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(7, this.type);
        }
        if (this.f3163wf == null || this.f3163wf.equals("")) {
            return computeSerializedSize;
        }
        return computeSerializedSize + CodedOutputByteBufferNano.d(9, this.f3163wf);
    }

    /* renamed from: g */
    public final C1972I mergeFrom(a aVar) {
        int ll;
        StringBuilder stringBuilder;
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                this.name = aVar.readString();
            } else if (lk == 18) {
                this.f3158wF = aVar.readString();
            } else if (lk == 24) {
                this.f3159wG = aVar.ll();
            } else if (lk == 37) {
                this.f3160wH = Float.intBitsToFloat(aVar.ln());
            } else if (lk == 40) {
                this.f3161wI = aVar.lm();
            } else if (lk == 50) {
                if (this.f3162wJ == null) {
                    this.f3162wJ = new C1971H();
                }
                aVar.a(this.f3162wJ);
            } else if (lk == 56) {
                try {
                    ll = aVar.ll();
                    if (ll < 0 || ll > 6) {
                        stringBuilder = new StringBuilder(47);
                        stringBuilder.append(ll);
                        stringBuilder.append(" is not a valid enum IntentParamType");
                    } else {
                        this.type = ll;
                    }
                } catch (IllegalArgumentException e) {
                    aVar.bU(aVar.getPosition());
                    a(aVar, lk);
                }
            } else if (lk == 74) {
                this.f3163wf = aVar.readString();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(47);
        stringBuilder.append(ll);
        stringBuilder.append(" is not a valid enum IntentParamType");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
