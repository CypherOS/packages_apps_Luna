package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.NanoEnumValue;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.j */
public final class C1980j extends b {
    /* renamed from: uU */
    private static volatile C1980j[] f3198uU;
    /* renamed from: id */
    public String f3199id;
    /* renamed from: uT */
    public int f3200uT;
    /* renamed from: uV */
    public long f3201uV;
    @NanoEnumValue(legacy = false, value = C0865r.class)
    /* renamed from: uW */
    public int f3202uW;
    /* renamed from: uX */
    public C1982l f3203uX;
    /* renamed from: uY */
    public C1984o f3204uY;
    /* renamed from: uZ */
    public C1978f f3205uZ;
    /* renamed from: uv */
    public C1985t f3206uv;
    /* renamed from: va */
    private C1979h f3207va;

    /* renamed from: ck */
    public static C1980j[] m3205ck() {
        if (f3198uU == null) {
            synchronized (f.ahE) {
                if (f3198uU == null) {
                    f3198uU = new C1980j[0];
                }
            }
        }
        return f3198uU;
    }

    public C1980j() {
        this.f3200uT = -1;
        this.f3199id = "";
        this.f3201uV = 0;
        this.f3202uW = 0;
        this.f3206uv = null;
        this.f3200uT = -1;
        this.f3203uX = null;
        this.f3200uT = -1;
        this.f3204uY = null;
        this.f3200uT = -1;
        this.f3205uZ = null;
        this.f3200uT = -1;
        this.f3207va = null;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (!(this.f3199id == null || this.f3199id.equals(""))) {
            codedOutputByteBufferNano.c(1, this.f3199id);
        }
        if (this.f3201uV != 0) {
            codedOutputByteBufferNano.i(3, this.f3201uV);
        }
        if (this.f3202uW != 0) {
            codedOutputByteBufferNano.R(4, this.f3202uW);
        }
        if (this.f3206uv != null) {
            codedOutputByteBufferNano.a(5, this.f3206uv);
        }
        if (this.f3200uT == 0) {
            codedOutputByteBufferNano.a(6, this.f3203uX);
        }
        if (this.f3200uT == 1) {
            codedOutputByteBufferNano.a(7, this.f3204uY);
        }
        if (this.f3200uT == 2) {
            codedOutputByteBufferNano.a(8, this.f3205uZ);
        }
        if (this.f3200uT == 3) {
            codedOutputByteBufferNano.a(9, this.f3207va);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (!(this.f3199id == null || this.f3199id.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(1, this.f3199id);
        }
        if (this.f3201uV != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.k(3, this.f3201uV);
        }
        if (this.f3202uW != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(4, this.f3202uW);
        }
        if (this.f3206uv != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(5, this.f3206uv);
        }
        if (this.f3200uT == 0) {
            computeSerializedSize += CodedOutputByteBufferNano.b(6, this.f3203uX);
        }
        if (this.f3200uT == 1) {
            computeSerializedSize += CodedOutputByteBufferNano.b(7, this.f3204uY);
        }
        if (this.f3200uT == 2) {
            computeSerializedSize += CodedOutputByteBufferNano.b(8, this.f3205uZ);
        }
        if (this.f3200uT == 3) {
            return computeSerializedSize + CodedOutputByteBufferNano.b(9, this.f3207va);
        }
        return computeSerializedSize;
    }

    /* renamed from: c */
    public final C1980j mergeFrom(a aVar) {
        int ll;
        StringBuilder stringBuilder;
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                this.f3199id = aVar.readString();
            } else if (lk == 24) {
                this.f3201uV = aVar.lm();
            } else if (lk == 32) {
                try {
                    ll = aVar.ll();
                    if (ll < 0 || ll > 4) {
                        stringBuilder = new StringBuilder(48);
                        stringBuilder.append(ll);
                        stringBuilder.append(" is not a valid enum SuggestionAction");
                    } else {
                        this.f3202uW = ll;
                    }
                } catch (IllegalArgumentException e) {
                    aVar.bU(aVar.getPosition());
                    a(aVar, lk);
                }
            } else if (lk == 42) {
                if (this.f3206uv == null) {
                    this.f3206uv = new C1985t();
                }
                aVar.a(this.f3206uv);
            } else if (lk == 50) {
                if (this.f3203uX == null) {
                    this.f3203uX = new C1982l();
                }
                aVar.a(this.f3203uX);
                this.f3200uT = 0;
            } else if (lk == 58) {
                if (this.f3204uY == null) {
                    this.f3204uY = new C1984o();
                }
                aVar.a(this.f3204uY);
                this.f3200uT = 1;
            } else if (lk == 66) {
                if (this.f3205uZ == null) {
                    this.f3205uZ = new C1978f();
                }
                aVar.a(this.f3205uZ);
                this.f3200uT = 2;
            } else if (lk == 74) {
                if (this.f3207va == null) {
                    this.f3207va = new C1979h();
                }
                aVar.a(this.f3207va);
                this.f3200uT = 3;
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(48);
        stringBuilder.append(ll);
        stringBuilder.append(" is not a valid enum SuggestionAction");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
