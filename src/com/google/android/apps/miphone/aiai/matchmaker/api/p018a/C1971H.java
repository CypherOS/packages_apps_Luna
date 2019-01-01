package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.H */
public final class C1971H extends b {
    public String className;
    public int flags;
    public String mimeType;
    public String packageName;
    /* renamed from: wB */
    public C1972I[] f3154wB;
    /* renamed from: wC */
    public String f3155wC;
    /* renamed from: wD */
    public String f3156wD;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                lk = j.c(aVar, 10);
                int length = this.f3154wB == null ? 0 : this.f3154wB.length;
                C1972I[] c1972iArr = new C1972I[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3154wB, 0, c1972iArr, 0, length);
                }
                while (length < c1972iArr.length - 1) {
                    c1972iArr[length] = new C1972I();
                    aVar.a(c1972iArr[length]);
                    aVar.lk();
                    length++;
                }
                c1972iArr[length] = new C1972I();
                aVar.a(c1972iArr[length]);
                this.f3154wB = c1972iArr;
            } else if (lk == 18) {
                this.packageName = aVar.readString();
            } else if (lk == 26) {
                this.className = aVar.readString();
            } else if (lk == 34) {
                this.f3155wC = aVar.readString();
            } else if (lk == 42) {
                this.f3156wD = aVar.readString();
            } else if (lk == 50) {
                this.mimeType = aVar.readString();
            } else if (lk == 56) {
                this.flags = aVar.ll();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
    }

    public C1971H() {
        this.f3154wB = C1972I.m3199cr();
        this.packageName = "";
        this.className = "";
        this.f3155wC = "";
        this.f3156wD = "";
        this.mimeType = "";
        this.flags = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3154wB != null && this.f3154wB.length > 0) {
            for (g gVar : this.f3154wB) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(1, gVar);
                }
            }
        }
        if (!(this.packageName == null || this.packageName.equals(""))) {
            codedOutputByteBufferNano.c(2, this.packageName);
        }
        if (!(this.className == null || this.className.equals(""))) {
            codedOutputByteBufferNano.c(3, this.className);
        }
        if (!(this.f3155wC == null || this.f3155wC.equals(""))) {
            codedOutputByteBufferNano.c(4, this.f3155wC);
        }
        if (!(this.f3156wD == null || this.f3156wD.equals(""))) {
            codedOutputByteBufferNano.c(5, this.f3156wD);
        }
        if (!(this.mimeType == null || this.mimeType.equals(""))) {
            codedOutputByteBufferNano.c(6, this.mimeType);
        }
        if (this.flags != 0) {
            codedOutputByteBufferNano.R(7, this.flags);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3154wB != null && this.f3154wB.length > 0) {
            for (g gVar : this.f3154wB) {
                if (gVar != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(1, gVar);
                }
            }
        }
        if (!(this.packageName == null || this.packageName.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(2, this.packageName);
        }
        if (!(this.className == null || this.className.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(3, this.className);
        }
        if (!(this.f3155wC == null || this.f3155wC.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(4, this.f3155wC);
        }
        if (!(this.f3156wD == null || this.f3156wD.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(5, this.f3156wD);
        }
        if (!(this.mimeType == null || this.mimeType.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(6, this.mimeType);
        }
        if (this.flags != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.S(7, this.flags);
        }
        return computeSerializedSize;
    }
}
