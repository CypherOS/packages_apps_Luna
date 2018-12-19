package com.google.android.libraries.launcherclient;

import android.os.IInterface;
import android.os.Parcel;

public interface ILauncherOverlayCallback extends IInterface {

    public abstract class Stub extends BaseStub implements ILauncherOverlayCallback {
        public Stub() {
            super("com.google.android.libraries.launcherclient.ILauncherOverlayCallback");
        }

		@Override
        protected final boolean dispatchTransaction$3d31fa39(int i, Parcel parcel) {
            switch (i) {
                case 1:
                    overlayScrollChanged(parcel.readFloat());
                    break;
                case 2:
                    overlayStatusChanged(parcel.readInt());
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    void overlayScrollChanged(float f);

    void overlayStatusChanged(int i);
}
