package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.C0855a;

public interface IContentCallback extends IInterface {

    public abstract class Stub extends BaseStub implements IContentCallback {
        public Stub() {
            super("com.google.android.apps.miphone.aiai.matchmaker.api.IContentCallback");
        }

        protected final boolean dispatchTransaction$3d31fa39(int i, Parcel parcel) {
            if (i != 1) {
                return false;
            }
            onFinished((ContentData) C0855a.m563a(parcel, ContentData.CREATOR));
            return true;
        }
    }

    void onFinished(ContentData contentData);
}
