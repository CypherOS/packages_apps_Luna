package com.android.launcher3.reflection.research.p016a;

/* renamed from: com.android.launcher3.reflection.research.a.d */
public class C0941d implements Comparable<C0941d> {
    public int index;
    public float value;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C0941d(this.index, this.value);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return Float.compare(this.value, ((C0941d) obj).value);
    }

    public C0941d(int i) {
        this.index = i;
        this.value = 1.0f;
    }

    public C0941d(int i, float f) {
        this.index = i;
        this.value = f;
    }

    public int hashCode() {
        return ((this.index * 31) + 17) + Float.floatToIntBits(this.value);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0941d)) {
            return false;
        }
        C0941d c0941d = (C0941d) obj;
        if (this.index == c0941d.index && this.value == c0941d.value) {
            return true;
        }
        return false;
    }

    public String toString() {
        int i = this.index;
        float f = this.value;
        StringBuilder sb = new StringBuilder(27);
        sb.append(i);
        sb.append("=");
        sb.append(f);
        return sb.toString();
    }
}
