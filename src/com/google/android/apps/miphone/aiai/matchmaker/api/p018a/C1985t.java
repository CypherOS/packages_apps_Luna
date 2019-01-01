package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.android.launcher3.AbstractFloatingView;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.g;
import com.google.protobuf.nano.j;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.t */
public final class C1985t extends b {
    /* renamed from: uA */
    public long f3225uA;
    public int versionCode;
    /* renamed from: vn */
    public C1973K f3226vn;
    /* renamed from: vo */
    public boolean f3227vo;
    /* renamed from: vp */
    public int f3228vp;
    /* renamed from: vq */
    public C1976c f3229vq;
    /* renamed from: vr */
    public boolean f3230vr;
    /* renamed from: vs */
    public boolean f3231vs;
    /* renamed from: vt */
    public boolean f3232vt;
    /* renamed from: vu */
    public boolean f3233vu;
    /* renamed from: vv */
    public C1986u[] f3234vv;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            g gVar;
            int lk = aVar.lk();
            switch (lk) {
                case 0:
                    return this;
                case 10:
                    if (this.f3226vn == null) {
                        this.f3226vn = new C1973K();
                    }
                    gVar = this.f3226vn;
                    break;
                case 16:
                    this.f3227vo = aVar.readBool();
                    continue;
                case 26:
                    if (this.f3229vq == null) {
                        this.f3229vq = new C1976c();
                    }
                    gVar = this.f3229vq;
                    break;
                case AbstractFloatingView.TYPE_ON_BOARD_POPUP /*32*/:
                    this.f3230vr = aVar.readBool();
                    continue;
                case 40:
                    this.f3231vs = aVar.readBool();
                    continue;
                case 48:
                    this.f3232vt = aVar.readBool();
                    continue;
                case 56:
                    this.f3228vp = aVar.ll();
                    continue;
                case 64:
                    this.f3225uA = aVar.lm();
                    continue;
                case 72:
                    this.f3233vu = aVar.readBool();
                    continue;
                case 80:
                    this.versionCode = aVar.ll();
                    continue;
                case 90:
                    lk = j.c(aVar, 90);
                    int length = this.f3234vv == null ? 0 : this.f3234vv.length;
                    C1986u[] c1986uArr = new C1986u[(lk + length)];
                    if (length != 0) {
                        System.arraycopy(this.f3234vv, 0, c1986uArr, 0, length);
                    }
                    while (length < c1986uArr.length - 1) {
                        c1986uArr[length] = new C1986u();
                        aVar.a(c1986uArr[length]);
                        aVar.lk();
                        length++;
                    }
                    c1986uArr[length] = new C1986u();
                    aVar.a(c1986uArr[length]);
                    this.f3234vv = c1986uArr;
                    continue;
                default:
                    if (!super.a(aVar, lk)) {
                        return this;
                    }
                    continue;
            }
            aVar.a(gVar);
        }
    }

    public C1985t() {
        this.f3225uA = 0;
        this.f3226vn = null;
        this.f3227vo = false;
        this.f3228vp = 0;
        this.f3229vq = null;
        this.f3230vr = false;
        this.f3231vs = false;
        this.f3232vt = false;
        this.f3233vu = false;
        this.versionCode = 0;
        this.f3234vv = C1986u.m3210cl();
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.f3226vn != null) {
            codedOutputByteBufferNano.a(1, this.f3226vn);
        }
        if (this.f3227vo) {
            codedOutputByteBufferNano.g(2, this.f3227vo);
        }
        if (this.f3229vq != null) {
            codedOutputByteBufferNano.a(3, this.f3229vq);
        }
        if (this.f3230vr) {
            codedOutputByteBufferNano.g(4, this.f3230vr);
        }
        if (this.f3231vs) {
            codedOutputByteBufferNano.g(5, this.f3231vs);
        }
        if (this.f3232vt) {
            codedOutputByteBufferNano.g(6, this.f3232vt);
        }
        if (this.f3228vp != 0) {
            codedOutputByteBufferNano.R(7, this.f3228vp);
        }
        if (this.f3225uA != 0) {
            codedOutputByteBufferNano.j(8, this.f3225uA);
        }
        if (this.f3233vu) {
            codedOutputByteBufferNano.g(9, this.f3233vu);
        }
        if (this.versionCode != 0) {
            codedOutputByteBufferNano.R(10, this.versionCode);
        }
        if (this.f3234vv != null && this.f3234vv.length > 0) {
            for (g gVar : this.f3234vv) {
                if (gVar != null) {
                    codedOutputByteBufferNano.a(11, gVar);
                }
            }
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (this.f3226vn != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(1, this.f3226vn);
        }
        if (this.f3227vo) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(2) + 1;
        }
        if (this.f3229vq != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(3, this.f3229vq);
        }
        if (this.f3230vr) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(4) + 1;
        }
        if (this.f3231vs) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(5) + 1;
        }
        if (this.f3232vt) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(6) + 1;
        }
        if (this.f3228vp != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(7, this.f3228vp);
        }
        if (this.f3225uA != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.l(8, this.f3225uA);
        }
        if (this.f3233vu) {
            computeSerializedSize += CodedOutputByteBufferNano.bZ(9) + 1;
        }
        if (this.versionCode != 0) {
            computeSerializedSize += CodedOutputByteBufferNano.S(10, this.versionCode);
        }
        if (this.f3234vv != null && this.f3234vv.length > 0) {
            for (g gVar : this.f3234vv) {
                if (gVar != null) {
                    computeSerializedSize += CodedOutputByteBufferNano.b(11, gVar);
                }
            }
        }
        return computeSerializedSize;
    }
}
