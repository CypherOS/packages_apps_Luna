package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.c */
public final class C1976c extends b {
    /* renamed from: id */
    public String f3172id;
    /* renamed from: uA */
    public long f3173uA;
    /* renamed from: uB */
    public C1975b[] f3174uB;
    /* renamed from: uC */
    public C1974L f3175uC;
    /* renamed from: uD */
    public C1966C f3176uD;
    /* renamed from: uE */
    public String f3177uE;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                this.f3172id = aVar.readString();
            } else if (lk != 18) {
                g gVar;
                if (lk == 26) {
                    if (this.f3175uC == null) {
                        this.f3175uC = new C1974L();
                    }
                    gVar = this.f3175uC;
                } else if (lk == 34) {
                    if (this.f3176uD == null) {
                        this.f3176uD = new C1966C();
                    }
                    gVar = this.f3176uD;
                } else if (lk == 40) {
                    this.f3173uA = aVar.lm();
                } else if (lk == 50) {
                    this.f3177uE = aVar.readString();
                } else if (!super.a(aVar, lk)) {
                    return this;
                }
                aVar.a(gVar);
            } else {
                lk = j.c(aVar, 18);
                int length = this.f3174uB == null ? 0 : this.f3174uB.length;
                C1975b[] c1975bArr = new C1975b[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3174uB, 0, c1975bArr, 0, length);
                }
                while (length < c1975bArr.length - 1) {
                    c1975bArr[length] = new C1975b();
                    aVar.a(c1975bArr[length]);
                    aVar.lk();
                    length++;
                }
                c1975bArr[length] = new C1975b();
                aVar.a(c1975bArr[length]);
                this.f3174uB = c1975bArr;
            }
        }
    }

    public C1976c() {
        this.f3172id = "";
        this.f3173uA = 0;
        this.f3174uB = C1975b.m3201ci();
        this.f3175uC = null;
        this.f3176uD = null;
        this.f3177uE = "";
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (!(this.f3172id == null || this.f3172id.equals(""))) {
            codedOutputByteBufferNano.c(1, this.f3172id);
        }
        if (this.f3174uB != null && this.f3174uB.length > 0) {
            for (g gVar : this.f3174uB) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(2, gVar);
                }
            }
        }
        if (this.f3175uC != null) {
            codedOutputByteBufferNano.a(3, this.f3175uC);
        }
        if (this.f3176uD != null) {
            codedOutputByteBufferNano.a(4, this.f3176uD);
        }
        if (this.f3173uA != 0) {
            codedOutputByteBufferNano.j(5, this.f3173uA);
        }
        if (!(this.f3177uE == null || this.f3177uE.equals(""))) {
            codedOutputByteBufferNano.c(6, this.f3177uE);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (!(this.f3172id == null || this.f3172id.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(1, this.f3172id);
        }
        if (this.f3174uB != null && this.f3174uB.length > 0) {
            for (g gVar : this.f3174uB) {
                if (gVar != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(2, gVar);
                }
            }
        }
        if (this.f3175uC != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(3, this.f3175uC);
        }
        if (this.f3176uD != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(4, this.f3176uD);
        }
        if (this.f3173uA != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.l(5, this.f3173uA);
        }
        if (this.f3177uE == null || this.f3177uE.equals("")) {
            return computeSerializedSize;
        }
        return computeSerializedSize + CodedOutputByteBufferNano.d(6, this.f3177uE);
    }
}
