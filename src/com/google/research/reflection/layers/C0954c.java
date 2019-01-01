package com.google.research.reflection.layers;

import com.google.research.reflection.p016a.C0942a;
import com.google.research.reflection.p016a.C0945d;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: com.google.research.reflection.layers.c */
public abstract class C0954c {
    /* renamed from: eo */
    static double f310eo = 0.1d;
    /* renamed from: dF */
    int f311dF;
    /* renamed from: dH */
    int f312dH;
    /* renamed from: ep */
    C0942a<C0955e> f313ep;
    /* renamed from: eq */
    public C0942a<C0955e> f314eq;
    /* renamed from: er */
    public C0942a<ArrayList<C0945d>[]> f315er;
    /* renamed from: es */
    C0955e f316es;
    /* renamed from: et */
    public C0955e f317et;
    /* renamed from: eu */
    public C0955e f318eu;
    /* renamed from: ev */
    public boolean f319ev;
    /* renamed from: ew */
    public int f320ew;
    /* renamed from: ex */
    boolean f321ex;

    /* renamed from: Z */
    public abstract C0954c clone();

    /* renamed from: a */
    public abstract C0955e mo9217a(boolean z, C0960f c0960f, ArrayList<C0945d>[] arrayListArr, C0955e c0955e) throws InvalidValueException;

    /* renamed from: a */
    public abstract void mo9219a(C0960f c0960f, int i, C0955e c0955e, C0955e c0955e2, C0955e c0955e3) throws InvalidValueException;

    public abstract String getName();

    public abstract void update() throws InvalidValueException;

    C0954c(boolean z, int i, int i2, int i3, int i4) {
        this.f313ep = new C0942a(i, true);
        this.f314eq = new C0942a(i, true);
        this.f315er = new C0942a(i, true);
        this.f311dF = i2;
        this.f312dH = i3;
        this.f320ew = i4;
        this.f321ex = z;
        this.f316es = new C0955e(i2, i4);
        this.f317et = new C0955e(i2, i3);
        this.f318eu = new C0955e(i2, i4);
        Arrays.fill(this.f317et.f322eR, 0.0d);
        Arrays.fill(this.f318eu.f322eR, 0.0d);
        Arrays.fill(this.f316es.f322eR, 0.0d);
    }

    C0954c() {
    }

    /* renamed from: b */
    public void mo9224b(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.f311dF);
        dataOutputStream.writeInt(this.f320ew);
        dataOutputStream.writeInt(this.f312dH);
        dataOutputStream.writeInt(this.f313ep.f275dl.length);
        dataOutputStream.writeBoolean(this.f321ex);
    }

    /* renamed from: b */
    public void mo9223b(DataInputStream dataInputStream) throws IOException {
        this.f311dF = dataInputStream.readInt();
        this.f320ew = dataInputStream.readInt();
        this.f312dH = dataInputStream.readInt();
        int readInt = dataInputStream.readInt();
        this.f321ex = dataInputStream.readBoolean();
        this.f313ep = new C0942a(readInt, true);
        this.f314eq = new C0942a(readInt, true);
        this.f315er = new C0942a(readInt, true);
        this.f316es = new C0955e(this.f311dF, this.f320ew);
        this.f317et = new C0955e(this.f311dF, this.f312dH);
        this.f318eu = new C0955e(this.f311dF, this.f320ew);
        Arrays.fill(this.f317et.f322eR, 0.0d);
        Arrays.fill(this.f318eu.f322eR, 0.0d);
        Arrays.fill(this.f316es.f322eR, 0.0d);
    }

    /* renamed from: a */
    public final void mo9218a(C0954c c0954c) {
        int length = this.f313ep.f275dl.length;
        c0954c.f313ep = new C0942a(length, true);
        c0954c.f314eq = new C0942a(length, true);
        c0954c.f315er = new C0942a(length, true);
        c0954c.f312dH = this.f312dH;
        c0954c.f320ew = this.f320ew;
        c0954c.f311dF = this.f311dF;
        c0954c.f321ex = this.f321ex;
        c0954c.f316es = new C0955e(this.f311dF, this.f320ew);
        c0954c.f317et = new C0955e(this.f311dF, this.f312dH);
        c0954c.f318eu = new C0955e(this.f311dF, this.f320ew);
        Arrays.fill(c0954c.f317et.f322eR, 0.0d);
        Arrays.fill(c0954c.f318eu.f322eR, 0.0d);
        Arrays.fill(c0954c.f316es.f322eR, 0.0d);
    }

    /* renamed from: ac */
    public final int mo9222ac() {
        return this.f313ep.f275dl.length;
    }

    /* renamed from: ab */
    public void mo9221ab() {
        this.f313ep.clear();
        this.f314eq.clear();
        this.f315er.clear();
    }

    /* renamed from: aa */
    void mo9220aa() {
        Arrays.fill(this.f317et.f322eR, 0.0d);
        Arrays.fill(this.f318eu.f322eR, 0.0d);
        Arrays.fill(this.f316es.f322eR, 0.0d);
    }

    /* renamed from: c */
    final void mo9226c(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(getName());
    }

    /* renamed from: c */
    final void mo9225c(DataInputStream dataInputStream) throws IOException {
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
    public static double m3018ad() {
        return f310eo;
    }
}
