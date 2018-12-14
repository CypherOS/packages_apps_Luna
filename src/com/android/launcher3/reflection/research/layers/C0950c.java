package com.android.launcher3.reflection.research.layers;

import com.android.launcher3.reflection.research.p016a.C0938a;
import com.android.launcher3.reflection.research.p016a.C0941d;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: com.android.launcher3.reflection.research.layers.c */
public abstract class C0950c {
    /* renamed from: eo */
    static double f310eo = 0.1d;
    /* renamed from: dF */
    int f311dF;
    /* renamed from: dH */
    int f312dH;
    /* renamed from: ep */
    C0938a<C0951e> f313ep;
    /* renamed from: eq */
    public C0938a<C0951e> f314eq;
    /* renamed from: er */
    public C0938a<ArrayList<C0941d>[]> f315er;
    /* renamed from: es */
    C0951e f316es;
    /* renamed from: et */
    public C0951e f317et;
    /* renamed from: eu */
    public C0951e f318eu;
    /* renamed from: ev */
    public boolean f319ev;
    /* renamed from: ew */
    public int f320ew;
    /* renamed from: ex */
    boolean f321ex;

    /* renamed from: Z */
    public abstract C0950c clone();

    /* renamed from: a */
    public abstract C0951e mo9199a(boolean z, C0956f c0956f, ArrayList<C0941d>[] arrayListArr, C0951e c0951e) throws InvalidValueException;

    /* renamed from: a */
    public abstract void mo9201a(C0956f c0956f, int i, C0951e c0951e, C0951e c0951e2, C0951e c0951e3) throws InvalidValueException;

    public abstract String getName();

    public abstract void update() throws InvalidValueException;

    C0950c(boolean z, int i, int i2, int i3, int i4) {
        this.f313ep = new C0938a(i, true);
        this.f314eq = new C0938a(i, true);
        this.f315er = new C0938a(i, true);
        this.f311dF = i2;
        this.f312dH = i3;
        this.f320ew = i4;
        this.f321ex = z;
        this.f316es = new C0951e(i2, i4);
        this.f317et = new C0951e(i2, i3);
        this.f318eu = new C0951e(i2, i4);
        Arrays.fill(this.f317et.f322eR, 0.0d);
        Arrays.fill(this.f318eu.f322eR, 0.0d);
        Arrays.fill(this.f316es.f322eR, 0.0d);
    }

    C0950c() {
    }

    /* renamed from: b */
    public void mo9206b(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.f311dF);
        dataOutputStream.writeInt(this.f320ew);
        dataOutputStream.writeInt(this.f312dH);
        dataOutputStream.writeInt(this.f313ep.f275dl.length);
        dataOutputStream.writeBoolean(this.f321ex);
    }

    /* renamed from: b */
    public void mo9205b(DataInputStream dataInputStream) throws IOException {
        this.f311dF = dataInputStream.readInt();
        this.f320ew = dataInputStream.readInt();
        this.f312dH = dataInputStream.readInt();
        int readInt = dataInputStream.readInt();
        this.f321ex = dataInputStream.readBoolean();
        this.f313ep = new C0938a(readInt, true);
        this.f314eq = new C0938a(readInt, true);
        this.f315er = new C0938a(readInt, true);
        this.f316es = new C0951e(this.f311dF, this.f320ew);
        this.f317et = new C0951e(this.f311dF, this.f312dH);
        this.f318eu = new C0951e(this.f311dF, this.f320ew);
        Arrays.fill(this.f317et.f322eR, 0.0d);
        Arrays.fill(this.f318eu.f322eR, 0.0d);
        Arrays.fill(this.f316es.f322eR, 0.0d);
    }

    /* renamed from: a */
    public final void mo9200a(C0950c c0950c) {
        int length = this.f313ep.f275dl.length;
        c0950c.f313ep = new C0938a(length, true);
        c0950c.f314eq = new C0938a(length, true);
        c0950c.f315er = new C0938a(length, true);
        c0950c.f312dH = this.f312dH;
        c0950c.f320ew = this.f320ew;
        c0950c.f311dF = this.f311dF;
        c0950c.f321ex = this.f321ex;
        c0950c.f316es = new C0951e(this.f311dF, this.f320ew);
        c0950c.f317et = new C0951e(this.f311dF, this.f312dH);
        c0950c.f318eu = new C0951e(this.f311dF, this.f320ew);
        Arrays.fill(c0950c.f317et.f322eR, 0.0d);
        Arrays.fill(c0950c.f318eu.f322eR, 0.0d);
        Arrays.fill(c0950c.f316es.f322eR, 0.0d);
    }

    /* renamed from: ac */
    public final int mo9204ac() {
        return this.f313ep.f275dl.length;
    }

    /* renamed from: ab */
    public void mo9203ab() {
        this.f313ep.clear();
        this.f314eq.clear();
        this.f315er.clear();
    }

    /* renamed from: aa */
    void mo9202aa() {
        Arrays.fill(this.f317et.f322eR, 0.0d);
        Arrays.fill(this.f318eu.f322eR, 0.0d);
        Arrays.fill(this.f316es.f322eR, 0.0d);
    }

    /* renamed from: c */
    final void mo9208c(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(getName());
    }

    /* renamed from: c */
    final void mo9207c(DataInputStream dataInputStream) throws IOException {
        String readUTF = dataInputStream.readUTF();
        if (!readUTF.equals(getName())) {
            String name = getName();
            StringBuilder sb = new StringBuilder((String.valueOf(name).length() + 19) + String.valueOf(readUTF).length());
            sb.append("Expected ");
            sb.append(name);
            sb.append(" acquired ");
            sb.append(readUTF);
            throw new RuntimeException(sb.toString());
        }
    }

    /* renamed from: ad */
    public static double m3003ad() {
        return f310eo;
    }
}
