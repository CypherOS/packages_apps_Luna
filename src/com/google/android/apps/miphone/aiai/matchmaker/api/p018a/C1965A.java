package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.NanoEnumValue;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.A */
public final class C1965A extends b {
    /* renamed from: wa */
    private static volatile C1965A[] f3117wa;
    /* renamed from: wb */
    public C1973K f3118wb;
    /* renamed from: wc */
    public String f3119wc;
    @NanoEnumValue(legacy = false, value = C0856B.class)
    /* renamed from: wd */
    public int f3120wd;
    /* renamed from: we */
    public int f3121we;
    /* renamed from: wf */
    public String f3122wf;
    /* renamed from: wg */
    public int f3123wg;

    /* renamed from: co */
    public static C1965A[] m3195co() {
        if (f3117wa == null) {
            synchronized (f.ahE) {
                if (f3117wa == null) {
                    f3117wa = new C1965A[0];
                }
            }
        }
        return f3117wa;
    }

    public C1965A() {
        this.f3118wb = null;
        this.f3119wc = "";
        this.f3120wd = 0;
        this.f3121we = 0;
        this.f3122wf = "";
        this.f3123wg = 0;
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3118wb != null) {
            codedOutputByteBufferNano.a(1, this.f3118wb);
        }
        if (!(this.f3119wc == null || this.f3119wc.equals(""))) {
            codedOutputByteBufferNano.c(2, this.f3119wc);
        }
        if (this.f3120wd != 0) {
            codedOutputByteBufferNano.R(3, this.f3120wd);
        }
        if (this.f3121we != 0) {
            codedOutputByteBufferNano.R(4, this.f3121we);
        }
        if (!(this.f3122wf == null || this.f3122wf.equals(""))) {
            codedOutputByteBufferNano.c(5, this.f3122wf);
        }
        if (this.f3123wg != 0) {
            codedOutputByteBufferNano.R(6, this.f3123wg);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3118wb != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(1, this.f3118wb);
        }
        if (!(this.f3119wc == null || this.f3119wc.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(2, this.f3119wc);
        }
        if (this.f3120wd != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(3, this.f3120wd);
        }
        if (this.f3121we != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(4, this.f3121we);
        }
        if (!(this.f3122wf == null || this.f3122wf.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(5, this.f3122wf);
        }
        if (this.f3123wg != 0) {
            return computeSerializedSize + CodedOutputByteBufferNano.S(6, this.f3123wg);
        }
        return computeSerializedSize;
    }

    /* renamed from: f */
    public final C1965A mergeFrom(a aVar) {
        int ll;
        StringBuilder stringBuilder;
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                if (this.f3118wb == null) {
                    this.f3118wb = new C1973K();
                }
                aVar.a(this.f3118wb);
            } else if (lk == 18) {
                this.f3119wc = aVar.readString();
            } else if (lk == 24) {
                try {
                    ll = aVar.ll();
                    if (ll < 0 || ll > 2) {
                        stringBuilder = new StringBuilder(43);
                        stringBuilder.append(ll);
                        stringBuilder.append(" is not a valid enum ContentType");
                    } else {
                        this.f3120wd = ll;
                    }
                } catch (IllegalArgumentException e) {
                    aVar.bU(aVar.getPosition());
                    a(aVar, lk);
                }
            } else if (lk == 32) {
                this.f3121we = aVar.readInt32();
            } else if (lk == 42) {
                this.f3122wf = aVar.readString();
            } else if (lk == 48) {
                this.f3123wg = aVar.readInt32();
            } else if (!super.a(aVar, lk)) {
                return this;
            }
        }
        stringBuilder = new StringBuilder(43);
        stringBuilder.append(ll);
        stringBuilder.append(" is not a valid enum ContentType");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
