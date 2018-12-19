package com.google.android.aidl;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* renamed from: com.google.android.aidl.a */
public class C0679a {
    /* renamed from: rR */
    private static final ClassLoader f814rR = C0679a.class.getClassLoader();

    private C0679a() {
    }

    /* renamed from: a */
    public static boolean m2224a(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    /* renamed from: a */
    public static void m2223a(Parcel parcel, boolean z) {
        parcel.writeInt(z);
    }

    /* renamed from: a */
    public static Parcelable m2220a(Parcel parcel, Creator creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }

    /* renamed from: a */
    public static void m2222a(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }

    /* renamed from: a */
    public static void m2221a(Parcel parcel, IInterface iInterface) {
        if (iInterface == null) {
            parcel.writeStrongBinder(null);
        } else {
            parcel.writeStrongBinder(iInterface.asBinder());
        }
    }
}
