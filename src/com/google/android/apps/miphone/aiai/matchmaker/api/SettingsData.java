package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1987w;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.g;

public class SettingsData implements Parcelable {
    public static final Creator CREATOR = new C0873e();
    /* renamed from: uw */
    public C1987w f1000uw;

    /* renamed from: a */
    public static final SettingsData m575a(C1987w c1987w) {
        return new SettingsData(c1987w);
    }

    /* renamed from: f */
    public static final SettingsData m576f(Parcel parcel) {
        byte[] createByteArray = parcel.createByteArray();
        if (createByteArray != null) {
            try {
                return m575a((C1987w) g.mergeFrom(new C1987w(), createByteArray));
            } catch (InvalidProtocolBufferNanoException e) {
                String valueOf = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(38 + String.valueOf(valueOf).length());
                stringBuilder.append("Invalid attempt to read settings data ");
                stringBuilder.append(valueOf);
                throw new ParcelFormatException(stringBuilder.toString());
            }
        }
        throw new ParcelFormatException("Invalid attempt to read settings proto");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(C1987w.toByteArray(this.f1000uw));
    }

    private SettingsData(C1987w c1987w) {
        this.f1000uw = c1987w;
    }
}
