package com.google.research.reflection.predictor;

import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0946e;
import com.google.research.reflection.signal.ReflectionEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/* renamed from: com.google.research.reflection.predictor.b */
public class C0965b {
    /* renamed from: fv */
    private static final long f347fv = TimeUnit.DAYS.toMillis(1);
    /* renamed from: fw */
    private long f348fw = f347fv;
    /* renamed from: fx */
    private int f349fx = 1000;
    /* renamed from: fy */
    protected C0942a<ReflectionEvent> f350fy = new C0942a(this.f349fx, false);
    /* renamed from: fz */
    protected boolean f351fz;

    /* renamed from: c */
    public final void mo9257c(ReflectionEvent reflectionEvent) {
        while (this.f350fy.f274dk > 0 && C0946e.m2988a((ReflectionEvent) this.f350fy.mo9187a(0), reflectionEvent) > this.f348fw) {
            C0942a<ReflectionEvent> c0942a = this.f350fy;
            if (c0942a.f274dk != 0) {
                int i = c0942a.f273dj - (c0942a.f274dk - 1);
                if (i < 0) {
                    i += c0942a.f275dl.length;
                }
                if (c0942a.mo9186R()) {
                    c0942a.f276dm.add(c0942a.f275dl[i]);
                }
                c0942a.f275dl[i] = null;
                c0942a.f274dk--;
                c0942a.f277dn--;
            }
        }
        this.f350fy.mo9188a((Object) reflectionEvent);
        this.f351fz = true;
    }

    /* renamed from: ar */
    public final C0942a<ReflectionEvent> mo9256ar() {
        return this.f350fy;
    }

    public final void clear() {
        this.f350fy.clear();
    }

    public final int size() {
        return this.f350fy.f274dk;
    }

    /* renamed from: d */
    public final void mo9259d(DataOutputStream dataOutputStream) throws IOException {
        int i = this.f350fy.f274dk;
        dataOutputStream.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            byte[] G = ((ReflectionEvent) this.f350fy.mo9187a(i2)).mo9285G();
            dataOutputStream.writeInt(G.length);
            dataOutputStream.write(G, 0, G.length);
        }
        this.f351fz = false;
    }

    /* renamed from: a */
    public final <T extends ReflectionEvent> void mo9255a(DataInputStream dataInputStream, T t) throws IOException {
        this.f350fy.clear();
        int readInt = dataInputStream.readInt();
        byte[] bArr = null;
        for (int i = 0; i < readInt; i++) {
            int readInt2 = dataInputStream.readInt();
            if (bArr == null || bArr.length < readInt2) {
                bArr = new byte[readInt2];
            }
            dataInputStream.read(bArr, 0, readInt2);
            mo9257c(t.mo9289a(bArr, readInt2));
        }
    }

    /* renamed from: a */
    public static <T extends ReflectionEvent> C0965b m3076a(byte[] bArr, T t) {
        C0965b c0965b = new C0965b();
        try {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            c0965b.mo9255a(dataInputStream, (ReflectionEvent) t);
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c0965b;
    }

    /* renamed from: a */
    public static byte[] m3077a(C0965b c0965b) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            c0965b.mo9259d(dataOutputStream);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static <T extends ReflectionEvent> C0965b m3075a(String str, T t) {
        return str != null ? C0965b.m3076a(str.getBytes(StandardCharsets.ISO_8859_1), (ReflectionEvent) t) : new C0965b();
    }

    /* renamed from: b */
    public static String m3078b(C0965b c0965b) {
        if (c0965b != null) {
            return new String(C0965b.m3077a(c0965b), StandardCharsets.ISO_8859_1);
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(size ");
        sb.append(this.f350fy.f274dk);
        sb.append("): ");
        for (int i = 0; i < this.f350fy.f274dk; i++) {
            sb.append(((ReflectionEvent) this.f350fy.mo9187a(i)).getId());
            sb.append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
