package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.d */
public final class C1977d extends b {
    /* renamed from: uF */
    private static volatile C1977d[] f3178uF;
    /* renamed from: id */
    public String f3179id;
    /* renamed from: uE */
    public String f3180uE;
    /* renamed from: uG */
    public int[] f3181uG;
    /* renamed from: uH */
    public boolean f3182uH;
    /* renamed from: uI */
    public int f3183uI;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            int length;
            if (lk == 8) {
                lk = j.c(aVar, 8);
                length = this.f3181uG == null ? 0 : this.f3181uG.length;
                int[] iArr = new int[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3181uG, 0, iArr, 0, length);
                }
                while (length < iArr.length - 1) {
                    iArr[length] = aVar.ll();
                    aVar.lk();
                    length++;
                }
                iArr[length] = aVar.ll();
                this.f3181uG = iArr;
            } else if (lk == 10) {
                lk = aVar.bS(aVar.ll());
                length = aVar.getPosition();
                int i = 0;
                while (aVar.lq() > 0) {
                    aVar.ll();
                    i++;
                }
                aVar.bU(length);
                length = this.f3181uG == null ? 0 : this.f3181uG.length;
                int[] iArr2 = new int[(i + length)];
                if (length != 0) {
                    System.arraycopy(this.f3181uG, 0, iArr2, 0, length);
                }
                while (length < iArr2.length) {
                    iArr2[length] = aVar.ll();
                    length++;
                }
                this.f3181uG = iArr2;
                aVar.bT(lk);
            } else if (lk == 18) {
                this.f3179id = aVar.readString();
            } else if (lk == 24) {
                this.f3182uH = aVar.readBool();
            } else if (lk == 32) {
                this.f3183uI = aVar.ll();
            } else if (lk == 42) {
                this.f3180uE = aVar.readString();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    /* renamed from: cj */
    public static C1977d[] m3202cj() {
        if (f3178uF == null) {
            synchronized (f.ahE) {
                if (f3178uF == null) {
                    f3178uF = new C1977d[0];
                }
            }
        }
        return f3178uF;
    }

    public C1977d() {
        this.f3181uG = j.ahG;
        this.f3179id = "";
        this.f3182uH = false;
        this.f3183uI = 0;
        this.f3180uE = "";
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3181uG != null && this.f3181uG.length > 0) {
            for (int R : this.f3181uG) {
                codedOutputByteBufferNano.R(1, R);
            }
        }
        if (!(this.f3179id == null || this.f3179id.equals(""))) {
            codedOutputByteBufferNano.c(2, this.f3179id);
        }
        if (this.f3182uH) {
            codedOutputByteBufferNano.g(3, this.f3182uH);
        }
        if (this.f3183uI != 0) {
            codedOutputByteBufferNano.R(4, this.f3183uI);
        }
        if (!(this.f3180uE == null || this.f3180uE.equals(""))) {
            codedOutputByteBufferNano.c(5, this.f3180uE);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3181uG != null && this.f3181uG.length > 0) {
            int i = 0;
            int i2 = 0;
            while (i < this.f3181uG.length) {
                i2 += CodedOutputByteBufferNano.bX(this.f3181uG[i]);
                i++;
            }
            computeSerializedSize = (computeSerializedSize + i2) + (this.f3181uG.length * 1);
        }
        if (!(this.f3179id == null || this.f3179id.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(2, this.f3179id);
        }
        if (this.f3182uH) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(3) + 1;
        }
        if (this.f3183uI != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(4, this.f3183uI);
        }
        if (this.f3180uE == null || this.f3180uE.equals("")) {
            return computeSerializedSize;
        }
        return computeSerializedSize + CodedOutputByteBufferNano.d(5, this.f3180uE);
    }
}
