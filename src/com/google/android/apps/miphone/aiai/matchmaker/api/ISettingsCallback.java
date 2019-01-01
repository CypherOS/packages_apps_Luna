package com.google.android.apps.miphone.aiai.matchmaker.api;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.C0855a;

public interface ISettingsCallback extends IInterface {

    public abstract class Stub extends BaseStub implements ISettingsCallback {
        public Stub() {
            super("com.google.android.apps.miphone.aiai.matchmaker.api.ISettingsCallback");
        }

        protected final boolean dispatchTransaction$3d31fa39(int i, Parcel parcel) {
            if (i != 1) {
                return false;
            }
            onUpdatedSettings((SettingsData) C0855a.m563a(parcel, SettingsData.CREATOR));
            return true;
        }
    }

    void onUpdatedSettings(SettingsData settingsData);
}
