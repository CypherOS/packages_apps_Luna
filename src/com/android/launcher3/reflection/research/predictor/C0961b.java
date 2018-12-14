package com.android.launcher3.reflection.research.predictor;

import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0942e;
import com.android.launcher3.reflection.research.signal.ReflectionEvent;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/* renamed from: com.android.launcher3.reflection.research.predictor.b */
public class C0961b {
    /* renamed from: fv */
    private static final long f347fv = TimeUnit.DAYS.toMillis(1);
    /* renamed from: fw */
    private long f348fw = f347fv;
    /* renamed from: fx */
    private int f349fx = 1000;
    /* renamed from: fy */
    protected C0938a<ReflectionEvent> f350fy = new C0938a(this.f349fx, false);
    /* renamed from: fz */
    protected boolean f351fz;

    /* renamed from: c */
    public final void mo9239c(ReflectionEvent reflectionEvent) {
        while (this.f350fy.f274dk > 0 && C0942e.m2973a((ReflectionEvent) this.f350fy.mo9169a(0), reflectionEvent) > this.f348fw) {
            C0938a<ReflectionEvent> c0938a = this.f350fy;
            if (c0938a.f274dk != 0) {
                int i = c0938a.f273dj - (c0938a.f274dk - 1);
                if (i < 0) {
                    i += c0938a.f275dl.length;
                }
                if (c0938a.mo9168R()) {
                    c0938a.f276dm.add(c0938a.f275dl[i]);
                }
                c0938a.f275dl[i] = null;
                c0938a.f274dk--;
                c0938a.f277dn--;
            }
        }
        this.f350fy.mo9170a((Object) reflectionEvent);
        this.f351fz = true;
    }

    /* renamed from: ar */
    public final C0938a<ReflectionEvent> mo9238ar() {
        return this.f350fy;
    }

    public final void clear() {
        this.f350fy.clear();
    }

    public final int size() {
        return this.f350fy.f274dk;
    }

    /* renamed from: d */
    public final void mo9241d(DataOutputStream dataOutputStream) throws IOException {
        int i = this.f350fy.f274dk;
        dataOutputStream.writeInt(i);
        for (int i2 = 0; i2 < i; i2++) {
            byte[] G = ((ReflectionEvent) this.f350fy.mo9169a(i2)).mo9267G();
            dataOutputStream.writeInt(G.length);
            dataOutputStream.write(G, 0, G.length);
        }
        this.f351fz = false;
    }

    /* renamed from: a */
    public final <T extends ReflectionEvent> void mo9237a(DataInputStream dataInputStream, T t) throws IOException {
        this.f350fy.clear();
        int readInt = dataInputStream.readInt();
        byte[] bArr = null;
        for (int i = 0; i < readInt; i++) {
            int readInt2 = dataInputStream.readInt();
            if (bArr == null || bArr.length < readInt2) {
                bArr = new byte[readInt2];
            }
            dataInputStream.read(bArr, 0, readInt2);
            mo9239c(t.mo9271a(bArr, readInt2));
        }
    }

    /* renamed from: a */
    public static <T extends ReflectionEvent> C0961b m3061a(byte[] bArr, T t) {
        C0961b c0961b = new C0961b();
        try {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            c0961b.mo9237a(dataInputStream, (ReflectionEvent) t);
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c0961b;
    }

    /* renamed from: a */
    public static byte[] m3062a(C0961b c0961b) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            c0961b.mo9241d(dataOutputStream);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static <T extends ReflectionEvent> C0961b m3060a(String str, T t) {
        return str != null ? C0961b.m3061a(str.getBytes(StandardCharsets.ISO_8859_1), (ReflectionEvent) t) : new C0961b();
    }

    /* renamed from: b */
    public static String m3063b(C0961b c0961b) {
        if (c0961b != null) {
            return new String(C0961b.m3062a(c0961b), StandardCharsets.ISO_8859_1);
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(size ");
        sb.append(this.f350fy.f274dk);
        sb.append("): ");
        for (int i = 0; i < this.f350fy.f274dk; i++) {
            sb.append(((ReflectionEvent) this.f350fy.mo9169a(i)).getId());
            sb.append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
