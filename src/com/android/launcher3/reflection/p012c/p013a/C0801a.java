package com.google.android.apps.nexuslauncher.reflection.p012c.p013a;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import p021me.jfenn.attribouter.BuildConfig;

/* renamed from: com.google.android.apps.nexuslauncher.reflection.c.a.a */
public interface C0801a {

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.c.a.a$a */
    public static final class C0802a extends MessageNano {
        /* renamed from: bp */
        public String f126bp;
        /* renamed from: bq */
        public String f127bq;
        /* renamed from: br */
        public long f128br;
        /* renamed from: bs */
        public long f129bs;
        /* renamed from: bt */
        public String f130bt;
        /* renamed from: bu */
        public long f131bu;
        /* renamed from: bv */
        public C0806e[] f132bv;
        /* renamed from: bw */
        public String[] f133bw;
        /* renamed from: bx */
        public C0805d f134bx;
        /* renamed from: by */
        public C0803b f135by;
        /* renamed from: bz */
        public C0804c f136bz;
        public long duration;
        /* renamed from: id */
        public String f137id;
        public long time;
        public String type;

        public C0802a() {
            this.f137id = BuildConfig.FLAVOR;
            this.type = BuildConfig.FLAVOR;
            this.f126bp = BuildConfig.FLAVOR;
            this.f127bq = BuildConfig.FLAVOR;
            this.time = 0;
            this.f128br = 0;
            this.f129bs = 0;
            this.f130bt = BuildConfig.FLAVOR;
            this.f131bu = 0;
            this.duration = 0;
            this.f132bv = C0806e.m2601p();
            this.f133bw = WireFormatNano.EMPTY_STRING_ARRAY;
            this.f134bx = null;
            this.f135by = null;
            this.f136bz = null;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f137id.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(1, this.f137id);
            }
            if (!this.type.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(2, this.type);
            }
            if (!this.f126bp.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(3, this.f126bp);
            }
            if (!this.f127bq.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(4, this.f127bq);
            }
            if (this.time != 0) {
                codedOutputByteBufferNano.writeInt64(5, this.time);
            }
            if (this.f128br != 0) {
                codedOutputByteBufferNano.writeInt64(6, this.f128br);
            }
            if (this.f129bs != 0) {
                codedOutputByteBufferNano.writeInt64(7, this.f129bs);
            }
            if (!this.f130bt.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(8, this.f130bt);
            }
            if (this.f131bu != 0) {
                codedOutputByteBufferNano.writeInt64(9, this.f131bu);
            }
            if (this.duration != 0) {
                codedOutputByteBufferNano.writeInt64(10, this.duration);
            }
            if (this.f132bv != null && this.f132bv.length > 0) {
                for (MessageNano messageNano : this.f132bv) {
                    if (messageNano != null) {
                        codedOutputByteBufferNano.writeMessage(11, messageNano);
                    }
                }
            }
            if (this.f133bw != null && this.f133bw.length > 0) {
                for (String str : this.f133bw) {
                    if (str != null) {
                        codedOutputByteBufferNano.writeString(12, str);
                    }
                }
            }
            if (this.f134bx != null) {
                codedOutputByteBufferNano.writeMessage(13, this.f134bx);
            }
            if (this.f135by != null) {
                codedOutputByteBufferNano.writeMessage(14, this.f135by);
            }
            if (this.f136bz != null) {
                codedOutputByteBufferNano.writeMessage(15, this.f136bz);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int i;
            int computeSerializedSize = super.computeSerializedSize();
            if (!this.f137id.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f137id);
            }
            if (!this.type.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.type);
            }
            if (!this.f126bp.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(3, this.f126bp);
            }
            if (!this.f127bq.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(4, this.f127bq);
            }
            if (this.time != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(5, this.time);
            }
            if (this.f128br != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(6, this.f128br);
            }
            if (this.f129bs != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(7, this.f129bs);
            }
            if (!this.f130bt.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(8, this.f130bt);
            }
            if (this.f131bu != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(9, this.f131bu);
            }
            if (this.duration != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(10, this.duration);
            }
            if (this.f132bv != null && this.f132bv.length > 0) {
                i = computeSerializedSize;
                for (MessageNano messageNano : this.f132bv) {
                    if (messageNano != null) {
                        i += CodedOutputByteBufferNano.computeMessageSize(11, messageNano);
                    }
                }
                computeSerializedSize = i;
            }
            if (this.f133bw != null && this.f133bw.length > 0) {
                i = 0;
                int i2 = 0;
                for (String str : this.f133bw) {
                    if (str != null) {
                        i2++;
                        i += CodedOutputByteBufferNano.computeStringSizeNoTag(str);
                    }
                }
                computeSerializedSize = (computeSerializedSize + i) + (i2 * 1);
            }
            if (this.f134bx != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(13, this.f134bx);
            }
            if (this.f135by != null) {
                computeSerializedSize += CodedOutputByteBufferNano.computeMessageSize(14, this.f135by);
            }
            return this.f136bz != null ? computeSerializedSize + CodedOutputByteBufferNano.computeMessageSize(15, this.f136bz) : computeSerializedSize;
        }

        /* renamed from: a */
        public final C0802a mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                int length;
                switch (readTag) {
                    case 0:
                        return this;
                    case 10:
                        this.f137id = codedInputByteBufferNano.readString();
                        break;
                    case 18:
                        this.type = codedInputByteBufferNano.readString();
                        break;
                    case 26:
                        this.f126bp = codedInputByteBufferNano.readString();
                        break;
                    case 34:
                        this.f127bq = codedInputByteBufferNano.readString();
                        break;
                    case 40:
                        this.time = codedInputByteBufferNano.readInt64();
                        break;
                    case 48:
                        this.f128br = codedInputByteBufferNano.readInt64();
                        break;
                    case 56:
                        this.f129bs = codedInputByteBufferNano.readInt64();
                        break;
                    case 66:
                        this.f130bt = codedInputByteBufferNano.readString();
                        break;
                    case 72:
                        this.f131bu = codedInputByteBufferNano.readInt64();
                        break;
                    case 80:
                        this.duration = codedInputByteBufferNano.readInt64();
                        break;
                    case 90:
                        readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 90);
                        length = this.f132bv == null ? 0 : this.f132bv.length;
                        C0806e[] c0806eArr = new C0806e[(readTag + length)];
                        if (length != 0) {
                            System.arraycopy(this.f132bv, 0, c0806eArr, 0, length);
                        }
                        while (length < c0806eArr.length - 1) {
                            c0806eArr[length] = new C0806e();
                            codedInputByteBufferNano.readMessage(c0806eArr[length]);
                            codedInputByteBufferNano.readTag();
                            length++;
                        }
                        c0806eArr[length] = new C0806e();
                        codedInputByteBufferNano.readMessage(c0806eArr[length]);
                        this.f132bv = c0806eArr;
                        break;
                    case 98:
                        readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 98);
                        length = this.f133bw == null ? 0 : this.f133bw.length;
                        String[] strArr = new String[(readTag + length)];
                        if (length != 0) {
                            System.arraycopy(this.f133bw, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = codedInputByteBufferNano.readString();
                            codedInputByteBufferNano.readTag();
                            length++;
                        }
                        strArr[length] = codedInputByteBufferNano.readString();
                        this.f133bw = strArr;
                        break;
                    case 106:
                        if (this.f134bx == null) {
                            this.f134bx = new C0805d();
                        }
                        codedInputByteBufferNano.readMessage(this.f134bx);
                        break;
                    case 114:
                        if (this.f135by == null) {
                            this.f135by = new C0803b();
                        }
                        codedInputByteBufferNano.readMessage(this.f135by);
                        break;
                    case 122:
                        if (this.f136bz == null) {
                            this.f136bz = new C0804c();
                        }
                        codedInputByteBufferNano.readMessage(this.f136bz);
                        break;
                    default:
                        if (WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                            break;
                        }
                        return this;
                }
            }
        }

        /* renamed from: a */
        public static C0802a m2593a(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (C0802a) MessageNano.mergeFrom(new C0802a(), bArr);
        }

        /* renamed from: b */
        public static C0802a m2594b(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            return new C0802a().mergeFrom(codedInputByteBufferNano);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.c.a.a$b */
    public static final class C0803b extends MessageNano {
        /* renamed from: bA */
        public String f138bA;
        public long time;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 8) {
                    this.time = codedInputByteBufferNano.readInt64();
                } else if (readTag == 18) {
                    this.f138bA = codedInputByteBufferNano.readString();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0803b() {
            this.time = 0;
            this.f138bA = BuildConfig.FLAVOR;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.time != 0) {
                codedOutputByteBufferNano.writeInt64(1, this.time);
            }
            if (!this.f138bA.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(2, this.f138bA);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.time != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.time);
            }
            return !this.f138bA.equals(BuildConfig.FLAVOR) ? computeSerializedSize + CodedOutputByteBufferNano.computeStringSize(2, this.f138bA) : computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.c.a.a$c */
    public static final class C0804c extends MessageNano {
        /* renamed from: bB */
        public double f139bB;
        /* renamed from: bC */
        public double f140bC;
        public long time;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                if (readTag == 8) {
                    this.time = codedInputByteBufferNano.readInt64();
                } else if (readTag == 17) {
                    this.f139bB = codedInputByteBufferNano.readDouble();
                } else if (readTag == 25) {
                    this.f140bC = codedInputByteBufferNano.readDouble();
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0804c() {
            this.time = 0;
            this.f139bB = 0.0d;
            this.f140bC = 0.0d;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.time != 0) {
                codedOutputByteBufferNano.writeInt64(1, this.time);
            }
            if (Double.doubleToLongBits(this.f139bB) != Double.doubleToLongBits(0.0d)) {
                codedOutputByteBufferNano.writeDouble(2, this.f139bB);
            }
            if (Double.doubleToLongBits(this.f140bC) != Double.doubleToLongBits(0.0d)) {
                codedOutputByteBufferNano.writeDouble(3, this.f140bC);
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.time != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.time);
            }
            if (Double.doubleToLongBits(this.f139bB) != Double.doubleToLongBits(0.0d)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeDoubleSize(2, this.f139bB);
            }
            return Double.doubleToLongBits(this.f140bC) != Double.doubleToLongBits(0.0d) ? computeSerializedSize + CodedOutputByteBufferNano.computeDoubleSize(3, this.f140bC) : computeSerializedSize;
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.c.a.a$d */
    public static final class C0805d extends MessageNano {
        /* renamed from: bD */
        public int[] f141bD;
        public long time;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                int i;
                int i2;
                if (readTag == 8) {
                    this.time = codedInputByteBufferNano.readInt64();
                } else if (readTag == 16) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 16);
                    int[] iArr = new int[readTag];
                    i = 0;
                    for (i2 = 0; i2 < readTag; i2++) {
                        if (i2 != 0) {
                            codedInputByteBufferNano.readTag();
                        }
                        int readInt32 = codedInputByteBufferNano.readInt32();
                        switch (readInt32) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                                int i3 = i + 1;
                                iArr[i] = readInt32;
                                i = i3;
                                break;
                        }
                    }
                    if (i != 0) {
                        i2 = this.f141bD == null ? 0 : this.f141bD.length;
                        if (i2 == 0 && i == readTag) {
                            this.f141bD = iArr;
                        } else {
                            int[] iArr2 = new int[(i2 + i)];
                            if (i2 != 0) {
                                System.arraycopy(this.f141bD, 0, iArr2, 0, i2);
                            }
                            System.arraycopy(iArr, 0, iArr2, i2, i);
                            this.f141bD = iArr2;
                        }
                    }
                } else if (readTag == 18) {
                    readTag = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                    int position = codedInputByteBufferNano.getPosition();
                    i2 = 0;
                    while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                        switch (codedInputByteBufferNano.readInt32()) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                                i2++;
                                break;
                        }
                    }
                    if (i2 != 0) {
                        codedInputByteBufferNano.rewindToPosition(position);
                        position = this.f141bD == null ? 0 : this.f141bD.length;
                        int[] iArr3 = new int[(i2 + position)];
                        if (position != 0) {
                            System.arraycopy(this.f141bD, 0, iArr3, 0, position);
                        }
                        while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                            int readInt322 = codedInputByteBufferNano.readInt32();
                            switch (readInt322) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                    i = position + 1;
                                    iArr3[position] = readInt322;
                                    position = i;
                                    break;
                            }
                        }
                        this.f141bD = iArr3;
                    }
                    codedInputByteBufferNano.popLimit(readTag);
                } else if (!WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                    return this;
                }
            }
        }

        public C0805d() {
            this.time = 0;
            this.f141bD = WireFormatNano.EMPTY_INT_ARRAY;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (this.time != 0) {
                codedOutputByteBufferNano.writeInt64(1, this.time);
            }
            if (this.f141bD != null && this.f141bD.length > 0) {
                for (int writeInt32 : this.f141bD) {
                    codedOutputByteBufferNano.writeInt32(2, writeInt32);
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int computeSerializedSize = super.computeSerializedSize();
            if (this.time != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(1, this.time);
            }
            if (this.f141bD == null || this.f141bD.length <= 0) {
                return computeSerializedSize;
            }
            int i = 0;
            for (int computeInt32SizeNoTag : this.f141bD) {
                i += CodedOutputByteBufferNano.computeInt32SizeNoTag(computeInt32SizeNoTag);
            }
            return (computeSerializedSize + i) + (this.f141bD.length * 1);
        }
    }

    /* renamed from: com.google.android.apps.nexuslauncher.reflection.c.a.a$e */
    public static final class C0806e extends MessageNano {
        /* renamed from: bE */
        private static volatile C0806e[] f142bE;
        /* renamed from: bF */
        public String[] f143bF;
        /* renamed from: bG */
        public double[] f144bG;
        /* renamed from: bH */
        public long[] f145bH;
        /* renamed from: id */
        public String f146id;
        public long time;
        public String type;

        public final /* synthetic */ MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
            while (true) {
                int readTag = codedInputByteBufferNano.readTag();
                if (readTag == 0) {
                    return this;
                }
                int length;
                int length2;
                if (readTag == 10) {
                    this.f146id = codedInputByteBufferNano.readString();
                } else if (readTag == 18) {
                    this.type = codedInputByteBufferNano.readString();
                } else if (readTag == 24) {
                    this.time = codedInputByteBufferNano.readInt64();
                } else if (readTag == 34) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 34);
                    length = this.f143bF == null ? 0 : this.f143bF.length;
                    String[] strArr = new String[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f143bF, 0, strArr, 0, length);
                    }
                    while (length < strArr.length - 1) {
                        strArr[length] = codedInputByteBufferNano.readString();
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    strArr[length] = codedInputByteBufferNano.readString();
                    this.f143bF = strArr;
                } else if (readTag == 48) {
                    readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 48);
                    length = this.f145bH == null ? 0 : this.f145bH.length;
                    long[] jArr = new long[(readTag + length)];
                    if (length != 0) {
                        System.arraycopy(this.f145bH, 0, jArr, 0, length);
                    }
                    while (length < jArr.length - 1) {
                        jArr[length] = codedInputByteBufferNano.readInt64();
                        codedInputByteBufferNano.readTag();
                        length++;
                    }
                    jArr[length] = codedInputByteBufferNano.readInt64();
                    this.f145bH = jArr;
                } else if (readTag != 50) {
                    double[] dArr;
                    switch (readTag) {
                        case 41:
                            readTag = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 41);
                            length = this.f144bG == null ? 0 : this.f144bG.length;
                            dArr = new double[(readTag + length)];
                            if (length != 0) {
                                System.arraycopy(this.f144bG, 0, dArr, 0, length);
                            }
                            while (length < dArr.length - 1) {
                                dArr[length] = codedInputByteBufferNano.readDouble();
                                codedInputByteBufferNano.readTag();
                                length++;
                            }
                            dArr[length] = codedInputByteBufferNano.readDouble();
                            this.f144bG = dArr;
                            break;
                        case 42:
                            readTag = codedInputByteBufferNano.readRawVarint32();
                            length = codedInputByteBufferNano.pushLimit(readTag);
                            readTag /= 8;
                            length2 = this.f144bG == null ? 0 : this.f144bG.length;
                            dArr = new double[(readTag + length2)];
                            if (length2 != 0) {
                                System.arraycopy(this.f144bG, 0, dArr, 0, length2);
                            }
                            while (length2 < dArr.length) {
                                dArr[length2] = codedInputByteBufferNano.readDouble();
                                length2++;
                            }
                            this.f144bG = dArr;
                            codedInputByteBufferNano.popLimit(length);
                            break;
                        default:
                            if (WireFormatNano.parseUnknownField(codedInputByteBufferNano, readTag)) {
                                break;
                            }
                            return this;
                    }
                } else {
                    readTag = codedInputByteBufferNano.pushLimit(codedInputByteBufferNano.readRawVarint32());
                    length = codedInputByteBufferNano.getPosition();
                    length2 = 0;
                    while (codedInputByteBufferNano.getBytesUntilLimit() > 0) {
                        codedInputByteBufferNano.readInt64();
                        length2++;
                    }
                    codedInputByteBufferNano.rewindToPosition(length);
                    length = this.f145bH == null ? 0 : this.f145bH.length;
                    long[] jArr2 = new long[(length2 + length)];
                    if (length != 0) {
                        System.arraycopy(this.f145bH, 0, jArr2, 0, length);
                    }
                    while (length < jArr2.length) {
                        jArr2[length] = codedInputByteBufferNano.readInt64();
                        length++;
                    }
                    this.f145bH = jArr2;
                    codedInputByteBufferNano.popLimit(readTag);
                }
            }
        }

        /* renamed from: p */
        public static C0806e[] m2601p() {
            if (f142bE == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (f142bE == null) {
                        f142bE = new C0806e[0];
                    }
                }
            }
            return f142bE;
        }

        public C0806e() {
            this.f146id = BuildConfig.FLAVOR;
            this.type = BuildConfig.FLAVOR;
            this.time = 0;
            this.f143bF = WireFormatNano.EMPTY_STRING_ARRAY;
            this.f144bG = WireFormatNano.EMPTY_DOUBLE_ARRAY;
            this.f145bH = WireFormatNano.EMPTY_LONG_ARRAY;
            this.cachedSize = -1;
        }

        public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
            if (!this.f146id.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(1, this.f146id);
            }
            if (!this.type.equals(BuildConfig.FLAVOR)) {
                codedOutputByteBufferNano.writeString(2, this.type);
            }
            if (this.time != 0) {
                codedOutputByteBufferNano.writeInt64(3, this.time);
            }
            if (this.f143bF != null && this.f143bF.length > 0) {
                for (String str : this.f143bF) {
                    if (str != null) {
                        codedOutputByteBufferNano.writeString(4, str);
                    }
                }
            }
            if (this.f144bG != null && this.f144bG.length > 0) {
                for (double writeDouble : this.f144bG) {
                    codedOutputByteBufferNano.writeDouble(5, writeDouble);
                }
            }
            if (this.f145bH != null && this.f145bH.length > 0) {
                for (long writeInt64 : this.f145bH) {
                    codedOutputByteBufferNano.writeInt64(6, writeInt64);
                }
            }
            super.writeTo(codedOutputByteBufferNano);
        }

        protected final int computeSerializedSize() {
            int i;
            int computeSerializedSize = super.computeSerializedSize();
            if (!this.f146id.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(1, this.f146id);
            }
            if (!this.type.equals(BuildConfig.FLAVOR)) {
                computeSerializedSize += CodedOutputByteBufferNano.computeStringSize(2, this.type);
            }
            if (this.time != 0) {
                computeSerializedSize += CodedOutputByteBufferNano.computeInt64Size(3, this.time);
            }
            if (this.f143bF != null && this.f143bF.length > 0) {
                int i2 = 0;
                int i3 = 0;
                for (String str : this.f143bF) {
                    if (str != null) {
                        i3++;
                        i2 += CodedOutputByteBufferNano.computeStringSizeNoTag(str);
                    }
                }
                computeSerializedSize = (computeSerializedSize + i2) + (i3 * 1);
            }
            if (this.f144bG != null && this.f144bG.length > 0) {
                computeSerializedSize = (computeSerializedSize + (this.f144bG.length * 8)) + (this.f144bG.length * 1);
            }
            if (this.f145bH == null || this.f145bH.length <= 0) {
                return computeSerializedSize;
            }
            i = 0;
            for (long computeInt64SizeNoTag : this.f145bH) {
                i += CodedOutputByteBufferNano.computeInt64SizeNoTag(computeInt64SizeNoTag);
            }
            return (computeSerializedSize + i) + (this.f145bH.length * 1);
        }

        /* renamed from: b */
        public static C0806e m2600b(byte[] bArr) throws InvalidProtocolBufferNanoException {
            return (C0806e) MessageNano.mergeFrom(new C0806e(), bArr);
        }
    }
}
