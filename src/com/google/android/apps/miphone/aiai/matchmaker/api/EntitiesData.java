package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1967D;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.g;
import java.util.HashMap;
import java.util.Map;

public class EntitiesData implements Parcelable {
    public static final Creator CREATOR = new C0870b();
    /* renamed from: ur */
    public final C1967D f995ur;
    /* renamed from: us */
    public final Map f996us;
    /* renamed from: ut */
    public final Map f997ut;

    /* renamed from: c */
    public static EntitiesData m570c(Parcel parcel) {
        byte[] createByteArray = parcel.createByteArray();
        if (createByteArray != null) {
            try {
                C1967D c1967d = (C1967D) g.mergeFrom(new C1967D(), createByteArray);
                HashMap hashMap = new HashMap();
                if (c1967d.f3130wj != null && c1967d.f3130wj.f3152wA) {
                    parcel.readMap(hashMap, Bitmap.class.getClassLoader());
                }
                HashMap hashMap2 = new HashMap();
                if (c1967d.f3130wj != null && c1967d.f3130wj.f3153wz) {
                    parcel.readMap(hashMap2, PendingIntent.class.getClassLoader());
                }
                return new EntitiesData(c1967d, hashMap, hashMap2);
            } catch (InvalidProtocolBufferNanoException e) {
                String valueOf = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(38 + String.valueOf(valueOf).length());
                stringBuilder.append("Invalid attempt to read entities data ");
                stringBuilder.append(valueOf);
                throw new ParcelFormatException(stringBuilder.toString());
            }
        }
        throw new ParcelFormatException("Invalid attempt to read Entities proto");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(C1967D.toByteArray(this.f995ur));
        if (this.f995ur.f3130wj != null) {
            if (this.f995ur.f3130wj.f3152wA) {
                parcel.writeMap(this.f996us);
            }
            if (this.f995ur.f3130wj.f3153wz) {
                parcel.writeMap(this.f997ut);
            }
        }
    }

    private EntitiesData(C1967D c1967d, Map map, Map map2) {
        this.f995ur = c1967d;
        this.f996us = map;
        this.f997ut = map2;
    }
}
