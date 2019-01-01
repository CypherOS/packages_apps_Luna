package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.C0855a;

public interface IEntitiesCallback extends IInterface {

    public abstract class Stub extends BaseStub implements IEntitiesCallback {
        public Stub() {
            super("com.google.android.apps.miphone.aiai.matchmaker.api.IEntitiesCallback");
        }

        protected final boolean dispatchTransaction$3d31fa39(int i, Parcel parcel) {
            if (i != 1) {
                return false;
            }
            onFinished((EntitiesData) C0855a.m563a(parcel, EntitiesData.CREATOR));
            return true;
        }
    }

    void onFinished(EntitiesData entitiesData);
}
