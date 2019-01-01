package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.NanoEnumValue;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.h */
public final class C1979h extends b {
    @NanoEnumValue(legacy = false, value = C0864q.class)
    /* renamed from: uJ */
    public int f3191uJ;
    /* renamed from: uK */
    public C1968E f3192uK;
    @NanoEnumValue(legacy = false, value = C0861i.class)
    /* renamed from: uN */
    public int f3193uN;
    /* renamed from: uP */
    public C1983n f3194uP;
    /* renamed from: uQ */
    public C1989z[] f3195uQ;
    /* renamed from: uR */
    public C1989z f3196uR;
    /* renamed from: uS */
    public int f3197uS;

    public C1979h() {
        this.f3191uJ = 0;
        this.f3192uK = null;
        this.f3195uQ = C1989z.m3212cn();
        this.f3196uR = null;
        this.f3193uN = 0;
        this.f3197uS = 0;
        this.f3194uP = null;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3191uJ != 0) {
            codedOutputByteBufferNano.R(1, this.f3191uJ);
        }
        if (this.f3192uK != null) {
            codedOutputByteBufferNano.a(2, this.f3192uK);
        }
        if (this.f3195uQ != null && this.f3195uQ.length > 0) {
            for (g gVar : this.f3195uQ) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(3, gVar);
                }
            }
        }
        if (this.f3196uR != null) {
            codedOutputByteBufferNano.a(4, this.f3196uR);
        }
        if (this.f3193uN != 0) {
            codedOutputByteBufferNano.R(5, this.f3193uN);
        }
        if (this.f3197uS != 0) {
            codedOutputByteBufferNano.R(6, this.f3197uS);
        }
        if (this.f3194uP != null) {
            codedOutputByteBufferNano.a(7, this.f3194uP);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3191uJ != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(1, this.f3191uJ);
        }
        if (this.f3192uK != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(2, this.f3192uK);
        }
        if (this.f3195uQ != null && this.f3195uQ.length > 0) {
            for (g gVar : this.f3195uQ) {
                if (gVar != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(3, gVar);
                }
            }
        }
        if (this.f3196uR != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(4, this.f3196uR);
        }
        if (this.f3193uN != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(5, this.f3193uN);
        }
        if (this.f3197uS != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(6, this.f3197uS);
        }
        if (this.f3194uP != null) {
            return computeSerializedSize + CodedOutputByteBufferNano.b(7, this.f3194uP);
        }
        return computeSerializedSize;
    }

    /* renamed from: b */
    public final C1979h mergeFrom(a aVar) {
        int position;
        int ll;
        StringBuilder stringBuilder;
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 8) {
                position = aVar.getPosition();
                try {
                    this.f3191uJ = C1984o.m3208aq(aVar.ll());
                } catch (IllegalArgumentException e) {
                    aVar.bU(position);
                    a(aVar, lk);
                }
            } else if (lk == 18) {
                if (this.f3192uK == null) {
                    this.f3192uK = new C1968E();
                }
                aVar.a(this.f3192uK);
            } else if (lk == 26) {
                lk = j.c(aVar, 26);
                if (this.f3195uQ == null) {
                    position = 0;
                } else {
                    position = this.f3195uQ.length;
                }
                C1989z[] c1989zArr = new C1989z[(lk + position)];
                if (position != 0) {
                    System.arraycopy(this.f3195uQ, 0, c1989zArr, 0, position);
                }
                while (position < c1989zArr.length - 1) {
                    c1989zArr[position] = new C1989z();
                    aVar.a(c1989zArr[position]);
                    aVar.lk();
                    position++;
                }
                c1989zArr[position] = new C1989z();
                aVar.a(c1989zArr[position]);
                this.f3195uQ = c1989zArr;
            } else if (lk == 34) {
                if (this.f3196uR == null) {
                    this.f3196uR = new C1989z();
                }
                aVar.a(this.f3196uR);
            } else if (lk == 40) {
                try {
                    ll = aVar.ll();
                    if (ll < 0 || ll > 3) {
                        stringBuilder = new StringBuilder(54);
                        stringBuilder.append(ll);
                        stringBuilder.append(" is not a valid enum ActionGroupInteraction");
                    } else {
                        this.f3193uN = ll;
                    }
                } catch (IllegalArgumentException e2) {
                    aVar.bU(aVar.getPosition());
                    a(aVar, lk);
                }
            } else if (lk == 48) {
                this.f3197uS = aVar.readInt32();
            } else if (lk == 58) {
                if (this.f3194uP == null) {
                    this.f3194uP = new C1983n();
                }
                aVar.a(this.f3194uP);
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(54);
        stringBuilder.append(ll);
        stringBuilder.append(" is not a valid enum ActionGroupInteraction");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
