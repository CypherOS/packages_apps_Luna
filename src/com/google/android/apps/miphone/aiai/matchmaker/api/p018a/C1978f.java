package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.NanoEnumValue;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.f */
public final class C1978f extends b {
    @NanoEnumValue(legacy = false, value = C0864q.class)
    /* renamed from: uJ */
    public int f3184uJ;
    /* renamed from: uK */
    public C1968E f3185uK;
    /* renamed from: uL */
    public C1988y[] f3186uL;
    /* renamed from: uM */
    public C1988y f3187uM;
    @NanoEnumValue(legacy = false, value = C0860g.class)
    /* renamed from: uN */
    public int f3188uN;
    /* renamed from: uO */
    public int f3189uO;
    /* renamed from: uP */
    public C1983n f3190uP;

    public C1978f() {
        this.f3184uJ = 0;
        this.f3185uK = null;
        this.f3186uL = C1988y.m3211cm();
        this.f3187uM = null;
        this.f3188uN = 0;
        this.f3189uO = 0;
        this.f3190uP = null;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3184uJ != 0) {
            codedOutputByteBufferNano.R(1, this.f3184uJ);
        }
        if (this.f3185uK != null) {
            codedOutputByteBufferNano.a(2, this.f3185uK);
        }
        if (this.f3186uL != null && this.f3186uL.length > 0) {
            for (g gVar : this.f3186uL) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(3, gVar);
                }
            }
        }
        if (this.f3187uM != null) {
            codedOutputByteBufferNano.a(4, this.f3187uM);
        }
        if (this.f3188uN != 0) {
            codedOutputByteBufferNano.R(5, this.f3188uN);
        }
        if (this.f3189uO != 0) {
            codedOutputByteBufferNano.R(6, this.f3189uO);
        }
        if (this.f3190uP != null) {
            codedOutputByteBufferNano.a(7, this.f3190uP);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3184uJ != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(1, this.f3184uJ);
        }
        if (this.f3185uK != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(2, this.f3185uK);
        }
        if (this.f3186uL != null && this.f3186uL.length > 0) {
            for (g gVar : this.f3186uL) {
                if (gVar != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(3, gVar);
                }
            }
        }
        if (this.f3187uM != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(4, this.f3187uM);
        }
        if (this.f3188uN != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(5, this.f3188uN);
        }
        if (this.f3189uO != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(6, this.f3189uO);
        }
        if (this.f3190uP != null) {
            return computeSerializedSize + CodedOutputByteBufferNano.b(7, this.f3190uP);
        }
        return computeSerializedSize;
    }

    /* renamed from: a */
    public final C1978f mergeFrom(a aVar) {
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
                    this.f3184uJ = C1984o.m3208aq(aVar.ll());
                } catch (IllegalArgumentException e) {
                    aVar.bU(position);
                    a(aVar, lk);
                }
            } else if (lk == 18) {
                if (this.f3185uK == null) {
                    this.f3185uK = new C1968E();
                }
                aVar.a(this.f3185uK);
            } else if (lk == 26) {
                lk = j.c(aVar, 26);
                if (this.f3186uL == null) {
                    position = 0;
                } else {
                    position = this.f3186uL.length;
                }
                C1988y[] c1988yArr = new C1988y[(lk + position)];
                if (position != 0) {
                    System.arraycopy(this.f3186uL, 0, c1988yArr, 0, position);
                }
                while (position < c1988yArr.length - 1) {
                    c1988yArr[position] = new C1988y();
                    aVar.a(c1988yArr[position]);
                    aVar.lk();
                    position++;
                }
                c1988yArr[position] = new C1988y();
                aVar.a(c1988yArr[position]);
                this.f3186uL = c1988yArr;
            } else if (lk == 34) {
                if (this.f3187uM == null) {
                    this.f3187uM = new C1988y();
                }
                aVar.a(this.f3187uM);
            } else if (lk == 40) {
                try {
                    ll = aVar.ll();
                    if (ll < 0 || ll > 3) {
                        stringBuilder = new StringBuilder(49);
                        stringBuilder.append(ll);
                        stringBuilder.append(" is not a valid enum ActionInteraction");
                    } else {
                        this.f3188uN = ll;
                    }
                } catch (IllegalArgumentException e2) {
                    aVar.bU(aVar.getPosition());
                    a(aVar, lk);
                }
            } else if (lk == 48) {
                this.f3189uO = aVar.readInt32();
            } else if (lk == 58) {
                if (this.f3190uP == null) {
                    this.f3190uP = new C1983n();
                }
                aVar.a(this.f3190uP);
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(49);
        stringBuilder.append(ll);
        stringBuilder.append(" is not a valid enum ActionInteraction");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
