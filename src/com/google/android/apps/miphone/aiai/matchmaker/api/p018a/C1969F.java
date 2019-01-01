package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.F */
public final class C1969F extends b {
    /* renamed from: ww */
    private static volatile C1969F[] f3148ww;
    /* renamed from: uG */
    public int[] f3149uG;
    /* renamed from: wx */
    public C1965A[] f3150wx;
    /* renamed from: wy */
    public String f3151wy;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            int length;
            if (lk == 10) {
                lk = j.c(aVar, 10);
                length = this.f3150wx == null ? 0 : this.f3150wx.length;
                C1965A[] c1965aArr = new C1965A[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3150wx, 0, c1965aArr, 0, length);
                }
                while (length < c1965aArr.length - 1) {
                    c1965aArr[length] = new C1965A();
                    aVar.a(c1965aArr[length]);
                    aVar.lk();
                    length++;
                }
                c1965aArr[length] = new C1965A();
                aVar.a(c1965aArr[length]);
                this.f3150wx = c1965aArr;
            } else if (lk == 18) {
                this.f3151wy = aVar.readString();
            } else if (lk == 24) {
                lk = j.c(aVar, 24);
                length = this.f3149uG == null ? 0 : this.f3149uG.length;
                int[] iArr = new int[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3149uG, 0, iArr, 0, length);
                }
                while (length < iArr.length - 1) {
                    iArr[length] = aVar.ll();
                    aVar.lk();
                    length++;
                }
                iArr[length] = aVar.ll();
                this.f3149uG = iArr;
            } else if (lk == 26) {
                lk = aVar.bS(aVar.ll());
                length = aVar.getPosition();
                int i = 0;
                while (aVar.lq() > 0) {
                    aVar.ll();
                    i++;
                }
                aVar.bU(length);
                length = this.f3149uG == null ? 0 : this.f3149uG.length;
                int[] iArr2 = new int[(i + length)];
                if (length != 0) {
                    System.arraycopy(this.f3149uG, 0, iArr2, 0, length);
                }
                while (length < iArr2.length) {
                    iArr2[length] = aVar.ll();
                    length++;
                }
                this.f3149uG = iArr2;
                aVar.bT(lk);
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    /* renamed from: cq */
    public static C1969F[] m3198cq() {
        if (f3148ww == null) {
            synchronized (f.ahE) {
                if (f3148ww == null) {
                    f3148ww = new C1969F[0];
                }
            }
        }
        return f3148ww;
    }

    public C1969F() {
        this.f3150wx = C1965A.m3195co();
        this.f3151wy = "";
        this.f3149uG = j.ahG;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = 0;
        if (this.f3150wx != null && this.f3150wx.length > 0) {
            for (g gVar : this.f3150wx) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(1, gVar);
                }
            }
        }
        if (!(this.f3151wy == null || this.f3151wy.equals(""))) {
            codedOutputByteBufferNano.c(2, this.f3151wy);
        }
        if (this.f3149uG != null && this.f3149uG.length > 0) {
            while (i < this.f3149uG.length) {
                codedOutputByteBufferNano.R(3, this.f3149uG[i]);
                i++;
            }
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int i;
        int computeSerializedSize = super.computeSerializedSize();
        int i2 = 0;
        if (this.f3150wx != null && this.f3150wx.length > 0) {
            i = computeSerializedSize;
            for (g gVar : this.f3150wx) {
                if (gVar != null) {
                    i += CodedOutputByteBufferNano.b(1, gVar);
                }
            }
            computeSerializedSize = i;
        }
        if (!(this.f3151wy == null || this.f3151wy.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(2, this.f3151wy);
        }
        if (this.f3149uG == null || this.f3149uG.length <= 0) {
            return computeSerializedSize;
        }
        i = 0;
        while (i2 < this.f3149uG.length) {
            i += CodedOutputByteBufferNano.bX(this.f3149uG[i2]);
            i2++;
        }
        return (computeSerializedSize + i) + (1 * this.f3149uG.length);
    }
}
