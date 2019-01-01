package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.b */
public final class C1975b extends b {
    /* renamed from: ux */
    private static volatile C1975b[] f3169ux;
    /* renamed from: uy */
    public C1965A[] f3170uy;
    /* renamed from: uz */
    public C1977d[] f3171uz;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            int length;
            if (lk == 10) {
                lk = j.c(aVar, 10);
                length = this.f3170uy == null ? 0 : this.f3170uy.length;
                C1965A[] c1965aArr = new C1965A[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3170uy, 0, c1965aArr, 0, length);
                }
                while (length < c1965aArr.length - 1) {
                    c1965aArr[length] = new C1965A();
                    aVar.a(c1965aArr[length]);
                    aVar.lk();
                    length++;
                }
                c1965aArr[length] = new C1965A();
                aVar.a(c1965aArr[length]);
                this.f3170uy = c1965aArr;
            } else if (lk == 18) {
                lk = j.c(aVar, 18);
                length = this.f3171uz == null ? 0 : this.f3171uz.length;
                C1977d[] c1977dArr = new C1977d[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3171uz, 0, c1977dArr, 0, length);
                }
                while (length < c1977dArr.length - 1) {
                    c1977dArr[length] = new C1977d();
                    aVar.a(c1977dArr[length]);
                    aVar.lk();
                    length++;
                }
                c1977dArr[length] = new C1977d();
                aVar.a(c1977dArr[length]);
                this.f3171uz = c1977dArr;
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    /* renamed from: ci */
    public static C1975b[] m3201ci() {
        if (f3169ux == null) {
            synchronized (f.ahE) {
                if (f3169ux == null) {
                    f3169ux = new C1975b[0];
                }
            }
        }
        return f3169ux;
    }

    public C1975b() {
        this.f3170uy = C1965A.m3195co();
        this.f3171uz = C1977d.m3202cj();
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = 0;
        if (this.f3170uy != null && this.f3170uy.length > 0) {
            for (g gVar : this.f3170uy) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(1, gVar);
                }
            }
        }
        if (this.f3171uz != null && this.f3171uz.length > 0) {
            while (i < this.f3171uz.length) {
                g gVar2 = this.f3171uz[i];
                if (gVar2 != null) {
                    codedOutputByteBufferNano.a(2, gVar2);
                }
                i++;
            }
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        int i = 0;
        if (this.f3170uy != null && this.f3170uy.length > 0) {
            int i2 = computeSerializedSize;
            for (g gVar : this.f3170uy) {
                if (gVar != null) {
                    i2 += CodedOutputByteBufferNano.b(1, gVar);
                }
            }
            computeSerializedSize = i2;
        }
        if (this.f3171uz != null && this.f3171uz.length > 0) {
            while (i < this.f3171uz.length) {
                g gVar2 = this.f3171uz[i];
                if (gVar2 != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(2, gVar2);
                }
                i++;
            }
        }
        return computeSerializedSize;
    }
}
