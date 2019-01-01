package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1976c;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.g;

public class ContentData implements Parcelable {
    public static final Creator CREATOR = new C0869a();
    /* renamed from: uq */
    public final C1976c f994uq;

    /* renamed from: a */
    public static final ContentData m568a(C1976c c1976c) {
        return new ContentData(c1976c);
    }

    /* renamed from: b */
    public static final ContentData m569b(Parcel parcel) {
        byte[] createByteArray = parcel.createByteArray();
        if (createByteArray != null) {
            try {
                return m568a((C1976c) g.mergeFrom(new C1976c(), createByteArray));
            } catch (InvalidProtocolBufferNanoException e) {
                String valueOf = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(37 + String.valueOf(valueOf).length());
                stringBuilder.append("Invalid attempt to read content data ");
                stringBuilder.append(valueOf);
                throw new ParcelFormatException(stringBuilder.toString());
            }
        }
        throw new ParcelFormatException("Invalid attempt to read Content proto");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(C1976c.toByteArray(this.f994uq));
    }

    private ContentData(C1976c c1976c) {
        this.f994uq = c1976c;
    }
}
