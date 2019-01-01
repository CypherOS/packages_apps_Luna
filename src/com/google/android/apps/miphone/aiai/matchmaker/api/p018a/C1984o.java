package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.NanoEnumValue;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.o */
public final class C1984o extends b {
    @NanoEnumValue(legacy = false, value = C0864q.class)
    public int type;
    /* renamed from: uK */
    public C1968E f3221uK;
    @NanoEnumValue(legacy = false, value = C0863p.class)
    /* renamed from: uN */
    public int f3222uN;
    /* renamed from: uP */
    public C1983n f3223uP;
    /* renamed from: vm */
    public int f3224vm;

    @NanoEnumValue(legacy = false, value = C0864q.class)
    /* renamed from: aq */
    public static int m3208aq(int i) {
        if (i >= 0 && i <= 2) {
            return i;
        }
        StringBuilder stringBuilder = new StringBuilder(45);
        stringBuilder.append(i);
        stringBuilder.append(" is not a valid enum SelectionType");
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public C1984o() {
        this.type = 0;
        this.f3221uK = null;
        this.f3223uP = null;
        this.f3222uN = 0;
        this.f3224vm = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.type != 0) {
            codedOutputByteBufferNano.R(1, this.type);
        }
        if (this.f3221uK != null) {
            codedOutputByteBufferNano.a(2, this.f3221uK);
        }
        if (this.f3223uP != null) {
            codedOutputByteBufferNano.a(3, this.f3223uP);
        }
        if (this.f3222uN != 0) {
            codedOutputByteBufferNano.R(4, this.f3222uN);
        }
        if (this.f3224vm != 0) {
            codedOutputByteBufferNano.R(5, this.f3224vm);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.type != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(1, this.type);
        }
        if (this.f3221uK != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(2, this.f3221uK);
        }
        if (this.f3223uP != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(3, this.f3223uP);
        }
        if (this.f3222uN != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(4, this.f3222uN);
        }
        if (this.f3224vm != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.S(5, this.f3224vm);
        }
        return computeSerializedSize;
    }

    /* renamed from: e */
    public final C1984o mergeFrom(a aVar) {
        int ll;
        StringBuilder stringBuilder;
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 8) {
                int position = aVar.getPosition();
                try {
                    this.type = C1984o.m3208aq(aVar.ll());
                } catch (IllegalArgumentException e) {
                    aVar.bU(position);
                    a(aVar, lk);
                }
            } else if (lk == 18) {
                if (this.f3221uK == null) {
                    this.f3221uK = new C1968E();
                }
                aVar.a(this.f3221uK);
            } else if (lk == 26) {
                if (this.f3223uP == null) {
                    this.f3223uP = new C1983n();
                }
                aVar.a(this.f3223uP);
            } else if (lk == 32) {
                try {
                    ll = aVar.ll();
                    if (ll < 0 || ll > 6) {
                        stringBuilder = new StringBuilder(52);
                        stringBuilder.append(ll);
                        stringBuilder.append(" is not a valid enum SelectionInteraction");
                    } else {
                        this.f3222uN = ll;
                    }
                } catch (IllegalArgumentException e2) {
                    aVar.bU(aVar.getPosition());
                    a(aVar, lk);
                }
            } else if (lk == 40) {
                this.f3224vm = aVar.readInt32();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(52);
        stringBuilder.append(ll);
        stringBuilder.append(" is not a valid enum SelectionInteraction");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
