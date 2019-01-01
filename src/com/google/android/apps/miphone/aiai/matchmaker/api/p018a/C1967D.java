package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.D */
public final class C1967D extends b {
    /* renamed from: id */
    public String f3124id;
    /* renamed from: uC */
    public C1974L f3125uC;
    /* renamed from: uD */
    public C1966C f3126uD;
    /* renamed from: uE */
    public String f3127uE;
    /* renamed from: wh */
    public boolean f3128wh;
    /* renamed from: wi */
    public C1968E[] f3129wi;
    /* renamed from: wj */
    public C1970G f3130wj;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                this.f3124id = aVar.readString();
            } else if (lk == 16) {
                this.f3128wh = aVar.readBool();
            } else if (lk != 26) {
                g gVar;
                if (lk == 34) {
                    if (this.f3125uC == null) {
                        this.f3125uC = new C1974L();
                    }
                    gVar = this.f3125uC;
                } else if (lk == 42) {
                    if (this.f3126uD == null) {
                        this.f3126uD = new C1966C();
                    }
                    gVar = this.f3126uD;
                } else if (lk == 50) {
                    if (this.f3130wj == null) {
                        this.f3130wj = new C1970G();
                    }
                    gVar = this.f3130wj;
                } else if (lk == 58) {
                    this.f3127uE = aVar.readString();
                } else if (!super.a(aVar, lk)) {
                    return this;
                }
                aVar.a(gVar);
            } else {
                lk = j.c(aVar, 26);
                int length = this.f3129wi == null ? 0 : this.f3129wi.length;
                C1968E[] c1968eArr = new C1968E[(lk + length)];
                if (length != 0) {
                    System.arraycopy(this.f3129wi, 0, c1968eArr, 0, length);
                }
                while (length < c1968eArr.length - 1) {
                    c1968eArr[length] = new C1968E();
                    aVar.a(c1968eArr[length]);
                    aVar.lk();
                    length++;
                }
                c1968eArr[length] = new C1968E();
                aVar.a(c1968eArr[length]);
                this.f3129wi = c1968eArr;
            }
        }
    }

    public C1967D() {
        this.f3124id = "";
        this.f3128wh = false;
        this.f3129wi = C1968E.m3197cp();
        this.f3125uC = null;
        this.f3126uD = null;
        this.f3130wj = null;
        this.f3127uE = "";
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (!(this.f3124id == null || this.f3124id.equals(""))) {
            codedOutputByteBufferNano.c(1, this.f3124id);
        }
        if (this.f3128wh) {
            codedOutputByteBufferNano.g(2, this.f3128wh);
        }
        if (this.f3129wi != null && this.f3129wi.length > 0) {
            for (g gVar : this.f3129wi) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(3, gVar);
                }
            }
        }
        if (this.f3125uC != null) {
            codedOutputByteBufferNano.a(4, this.f3125uC);
        }
        if (this.f3126uD != null) {
            codedOutputByteBufferNano.a(5, this.f3126uD);
        }
        if (this.f3130wj != null) {
            codedOutputByteBufferNano.a(6, this.f3130wj);
        }
        if (!(this.f3127uE == null || this.f3127uE.equals(""))) {
            codedOutputByteBufferNano.c(7, this.f3127uE);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (!(this.f3124id == null || this.f3124id.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(1, this.f3124id);
        }
        if (this.f3128wh) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(2) + 1;
        }
        if (this.f3129wi != null && this.f3129wi.length > 0) {
            for (g gVar : this.f3129wi) {
                if (gVar != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(3, gVar);
                }
            }
        }
        if (this.f3125uC != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(4, this.f3125uC);
        }
        if (this.f3126uD != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(5, this.f3126uD);
        }
        if (this.f3130wj != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(6, this.f3130wj);
        }
        if (this.f3127uE == null || this.f3127uE.equals("")) {
            return computeSerializedSize;
        }
        return computeSerializedSize + CodedOutputByteBufferNano.d(7, this.f3127uE);
    }
}
