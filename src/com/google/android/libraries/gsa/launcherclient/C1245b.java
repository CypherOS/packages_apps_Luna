package com.google.android.libraries.gsa.launcherclient;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* renamed from: com.google.android.libraries.gsa.launcherclient.b */
public class C1245b {
    private final C1246c[] aeo;
    private int aep = 0;
    private final String name;

    public C1245b(String str, int i) {
        this.name = str;
        this.aeo = new C1246c[i];
    }

    /* renamed from: f */
    public final void parse(String str, int i) {
        parse(2, str, (float) i);
    }

    /* renamed from: c */
    public final void mo4037c(String str, boolean z) {
        parse(z ? 3 : 4, str, 0.0f);
    }

    /* renamed from: a */
    public final void parse(int i, String str, float f) {
        int length = ((this.aep + this.aeo.length) - 1) % this.aeo.length;
        int length2 = ((this.aep + this.aeo.length) - 2) % this.aeo.length;
        if (C1245b.m3629a(this.aeo[length], i, str) && C1245b.m3629a(this.aeo[length2], i, str)) {
            this.aeo[length].mo4040b(i, str, f);
            C1246c c1246c = this.aeo[length2];
            c1246c.aer++;
            return;
        }
        if (this.aeo[this.aep] == null) {
            this.aeo[this.aep] = new C1246c();
        }
        this.aeo[this.aep].mo4040b(i, str, f);
        this.aep = (this.aep + 1) % this.aeo.length;
    }

    public final void dump(String str, PrintWriter printWriter) {
        String str2 = this.name;
        StringBuilder stringBuilder = new StringBuilder((15 + String.valueOf(str).length()) + String.valueOf(str2).length());
        stringBuilder.append(str);
        stringBuilder.append(str2);
        stringBuilder.append(" event history:");
        printWriter.println(stringBuilder.toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("  HH:mm:ss.SSSZ  ", Locale.US);
        Date date = new Date();
        for (int i = 0; i < this.aeo.length; i++) {
            C1246c c1246c = this.aeo[(((this.aep + this.aeo.length) - i) - 1) % this.aeo.length];
            if (c1246c != null) {
                date.setTime(c1246c.time);
                StringBuilder stringBuilder2 = new StringBuilder(str);
                stringBuilder2.append(simpleDateFormat.format(date));
                stringBuilder2.append(c1246c.f1603CW);
                switch (c1246c.type) {
                    case 1:
                        stringBuilder2.append(": ");
                        stringBuilder2.append(c1246c.aeq);
                        break;
                    case 2:
                        stringBuilder2.append(": ");
                        stringBuilder2.append((int) c1246c.aeq);
                        break;
                    case 3:
                        stringBuilder2.append(": true");
                        break;
                    case 4:
                        stringBuilder2.append(": false");
                        break;
                }
                if (c1246c.aer > 0) {
                    stringBuilder2.append(" & ");
                    stringBuilder2.append(c1246c.aer);
                    stringBuilder2.append(" similar events");
                }
                printWriter.println(stringBuilder2);
            }
        }
    }

    /* renamed from: a */
    private static boolean m3629a(C1246c c1246c, int i, String str) {
        return c1246c != null && c1246c.type == i && c1246c.f1603CW.equals(str);
    }
}
