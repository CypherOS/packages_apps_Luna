package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1985t;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.g;

public class InteractionContextData implements Parcelable {
    public static final Creator CREATOR = new C0872d();
    /* renamed from: uv */
    private final C1985t f999uv;

    /* renamed from: a */
    public static final InteractionContextData m573a(C1985t c1985t) {
        return new InteractionContextData(c1985t);
    }

    /* renamed from: e */
    public static final InteractionContextData m574e(Parcel parcel) {
        byte[] createByteArray = parcel.createByteArray();
        if (createByteArray != null) {
            try {
                return m573a((C1985t) g.mergeFrom(new C1985t(), createByteArray));
            } catch (InvalidProtocolBufferNanoException e) {
                String valueOf = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(54 + String.valueOf(valueOf).length());
                stringBuilder.append("Invalid attempt to read proto interaction context data");
                stringBuilder.append(valueOf);
                throw new ParcelFormatException(stringBuilder.toString());
            }
        }
        throw new ParcelFormatException("Invalid attempt to read InteractionContext proto");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(C1985t.toByteArray(this.f999uv));
    }

    private InteractionContextData(C1985t c1985t) {
        this.f999uv = c1985t;
    }
}
