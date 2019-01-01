package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.k */
public final class C1981k extends b {
    /* renamed from: uA */
    public long f3208uA;
    /* renamed from: vb */
    public C1980j[] f3209vb;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                lk = j.c(aVar, 10);
                int length = this.f3209vb == null ? 0 : this.f3209vb.length;
                C1980j[] c1980jArr = new C1980j[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3209vb, 0, c1980jArr, 0, length);
                }
                while (length < c1980jArr.length - 1) {
                    c1980jArr[length] = new C1980j();
                    aVar.a(c1980jArr[length]);
                    aVar.lk();
                    length++;
                }
                c1980jArr[length] = new C1980j();
                aVar.a(c1980jArr[length]);
                this.f3209vb = c1980jArr;
            } else if (lk == 16) {
                this.f3208uA = aVar.lm();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    public C1981k() {
        this.f3209vb = C1980j.m3205ck();
        this.f3208uA = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3209vb != null && this.f3209vb.length > 0) {
            for (g gVar : this.f3209vb) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(1, gVar);
                }
            }
        }
        if (this.f3208uA != 0) {
            codedOutputByteBufferNano.j(2, this.f3208uA);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3209vb != null && this.f3209vb.length > 0) {
            for (g gVar : this.f3209vb) {
                if (gVar != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(1, gVar);
                }
            }
        }
        if (this.f3208uA != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.l(2, this.f3208uA);
        }
        return computeSerializedSize;
    }
}
