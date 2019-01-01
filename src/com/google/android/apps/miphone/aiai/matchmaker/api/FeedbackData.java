package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.apps.miphone.aiai.matchmaker.api.p018a.C1981k;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.g;

public class FeedbackData implements Parcelable {
    public static final Creator CREATOR = new C0871c();
    /* renamed from: uu */
    public final C1981k f998uu;

    /* renamed from: a */
    public static final FeedbackData m571a(C1981k c1981k) {
        return new FeedbackData(c1981k);
    }

    /* renamed from: d */
    public static final FeedbackData m572d(Parcel parcel) {
        byte[] createByteArray = parcel.createByteArray();
        if (createByteArray != null) {
            try {
                return m571a((C1981k) g.mergeFrom(new C1981k(), createByteArray));
            } catch (InvalidProtocolBufferNanoException e) {
                String valueOf = String.valueOf(e);
                StringBuilder stringBuilder = new StringBuilder(43 + String.valueOf(valueOf).length());
                stringBuilder.append("Invalid attempt to read proto feedback data");
                stringBuilder.append(valueOf);
                throw new ParcelFormatException(stringBuilder.toString());
            }
        }
        throw new ParcelFormatException("Invalid attempt to read FeedbackBatch proto");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(C1981k.toByteArray(this.f998uu));
    }

    private FeedbackData(C1981k c1981k) {
        this.f998uu = c1981k;
    }
}
