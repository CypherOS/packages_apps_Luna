package com.google.android.apps.miphone.aiai.matchmaker.api.p018a;

import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.a;
import com.google.protobuf.nano.b;
import com.google.protobuf.nano.f;
import com.google.protobuf.nano.g;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.api.a.y */
public final class C1988y extends b {
    /* renamed from: vQ */
    private static volatile C1988y[] f3257vQ;
    /* renamed from: id */
    public String f3258id;
    /* renamed from: uE */
    public String f3259uE;
    /* renamed from: vR */
    public String f3260vR;
    /* renamed from: vS */
    public String f3261vS;
    /* renamed from: vT */
    public String f3262vT;
    /* renamed from: vU */
    public C1971H f3263vU;
    /* renamed from: vV */
    public C1971H f3264vV;

    public final /* synthetic */ g mergeFrom(a aVar) {
        while (true) {
            int lk = aVar.lk();
            if (lk == 0) {
                return this;
            }
            if (lk == 10) {
                this.f3258id = aVar.readString();
            } else if (lk == 18) {
                this.f3260vR = aVar.readString();
            } else if (lk == 26) {
                this.f3261vS = aVar.readString();
            } else if (lk != 34) {
                g gVar;
                if (lk == 42) {
                    if (this.f3263vU == null) {
                        this.f3263vU = new C1971H();
                    }
                    gVar = this.f3263vU;
                } else if (lk == 50) {
                    if (this.f3264vV == null) {
                        this.f3264vV = new C1971H();
                    }
                    gVar = this.f3264vV;
                } else if (lk == 58) {
                    this.f3259uE = aVar.readString();
                } else if (!super.a(aVar, lk)) {
                    return this;
                }
                aVar.a(gVar);
            } else {
                this.f3262vT = aVar.readString();
            }
        }
    }

    /* renamed from: cm */
    public static C1988y[] m3211cm() {
        if (f3257vQ == null) {
            synchronized (f.ahE) {
                if (f3257vQ == null) {
                    f3257vQ = new C1988y[0];
                }
            }
        }
        return f3257vQ;
    }

    public C1988y() {
        this.f3258id = "";
        this.f3260vR = "";
        this.f3261vS = "";
        this.f3262vT = "";
        this.f3263vU = null;
        this.f3264vV = null;
        this.f3259uE = "";
        this.ahw = null;
        this.cachedSize = -1;
    }

    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (!(this.f3258id == null || this.f3258id.equals(""))) {
            codedOutputByteBufferNano.c(1, this.f3258id);
        }
        if (!(this.f3260vR == null || this.f3260vR.equals(""))) {
            codedOutputByteBufferNano.c(2, this.f3260vR);
        }
        if (!(this.f3261vS == null || this.f3261vS.equals(""))) {
            codedOutputByteBufferNano.c(3, this.f3261vS);
        }
        if (!(this.f3262vT == null || this.f3262vT.equals(""))) {
            codedOutputByteBufferNano.c(4, this.f3262vT);
        }
        if (this.f3263vU != null) {
            codedOutputByteBufferNano.a(5, this.f3263vU);
        }
        if (this.f3264vV != null) {
            codedOutputByteBufferNano.a(6, this.f3264vV);
        }
        if (!(this.f3259uE == null || this.f3259uE.equals(""))) {
            codedOutputByteBufferNano.c(7, this.f3259uE);
        }
        super.writeTo(codedOutputByteBufferNano);
    }

    protected final int computeSerializedSize() {
        int computeSerializedSize = super.computeSerializedSize();
        if (!(this.f3258id == null || this.f3258id.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(1, this.f3258id);
        }
        if (!(this.f3260vR == null || this.f3260vR.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(2, this.f3260vR);
        }
        if (!(this.f3261vS == null || this.f3261vS.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(3, this.f3261vS);
        }
        if (!(this.f3262vT == null || this.f3262vT.equals(""))) {
            computeSerializedSize += CodedOutputByteBufferNano.d(4, this.f3262vT);
        }
        if (this.f3263vU != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(5, this.f3263vU);
        }
        if (this.f3264vV != null) {
            computeSerializedSize += CodedOutputByteBufferNano.b(6, this.f3264vV);
        }
        if (this.f3259uE == null || this.f3259uE.equals("")) {
            return computeSerializedSize;
        }
        return computeSerializedSize + CodedOutputByteBufferNano.d(7, this.f3259uE);
    }
}
