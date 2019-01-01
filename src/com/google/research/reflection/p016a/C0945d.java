package com.google.research.reflection.p016a;

/* renamed from: com.google.research.reflection.a.d */
public class C0945d implements Comparable<C0945d> {
    public int index;
    public float value;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new C0945d(this.index, this.value);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return Float.compare(this.value, ((C0945d) obj).value);
    }

    public C0945d(int i) {
        this.index = i;
        this.value = 1.0f;
    }

    public C0945d(int i, float f) {
        this.index = i;
        this.value = f;
    }

    public int hashCode() {
        return ((this.index * 31) + 17) + Float.floatToIntBits(this.value);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0945d)) {
            return false;
        }
        C0945d c0945d = (C0945d) obj;
        if (this.index == c0945d.index && this.value == c0945d.value) {
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
