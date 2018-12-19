package com.google.android.libraries.launcherclient;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.WindowManager.LayoutParams;

public interface ILauncherOverlay extends IInterface {

    public abstract class Stub extends BaseStub implements ILauncherOverlay {
		public static final String DESCRIPTOR = "com.google.android.libraries.launcherclient.ILauncherOverlay";

        public class Proxy extends BaseProxy implements ILauncherOverlay {
            Proxy(IBinder iBinder) {
                super(iBinder, "com.google.android.libraries.launcherclient.ILauncherOverlay");
            }

            public final void startScroll() {
                transactOneway(1, obtainAndWriteInterfaceToken());
            }

            public final void onScroll(float f) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeFloat(f);
                transactOneway(2, obtainAndWriteInterfaceToken);
            }

            public final void endScroll() {
                transactOneway(3, obtainAndWriteInterfaceToken());
            }

            public final void windowAttached(LayoutParams layoutParams, ILauncherOverlayCallback iLauncherOverlayCallback, int i) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                C0679a.m2222a(obtainAndWriteInterfaceToken, (Parcelable) layoutParams);
                C0679a.m2221a(obtainAndWriteInterfaceToken, (IInterface) iLauncherOverlayCallback);
                obtainAndWriteInterfaceToken.writeInt(i);
                transactOneway(4, obtainAndWriteInterfaceToken);
            }

            public final void windowAttached2(Bundle bundle, ILauncherOverlayCallback iLauncherOverlayCallback) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                C0679a.m2222a(obtainAndWriteInterfaceToken, (Parcelable) bundle);
                C0679a.m2221a(obtainAndWriteInterfaceToken, (IInterface) iLauncherOverlayCallback);
                transactOneway(14, obtainAndWriteInterfaceToken);
            }

            public final void windowDetached(boolean z) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                C0679a.m2223a(obtainAndWriteInterfaceToken, z);
                transactOneway(5, obtainAndWriteInterfaceToken);
            }

            public final void closeOverlay(int i) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeInt(i);
                transactOneway(6, obtainAndWriteInterfaceToken);
            }

            public final void onPause() {
                transactOneway(7, obtainAndWriteInterfaceToken());
            }

            public final void onResume() {
                transactOneway(8, obtainAndWriteInterfaceToken());
            }

            public final void setActivityState(int i) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeInt(i);
                transactOneway(16, obtainAndWriteInterfaceToken);
            }

            public final void openOverlay(int i) {
                Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                obtainAndWriteInterfaceToken.writeInt(i);
                transactOneway(9, obtainAndWriteInterfaceToken);
            }

            public final boolean startSearch(byte[] bArr, Bundle bundle) {
				Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(bArr);
                    boolean _result = true;
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    asBinder().transact(17, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th) {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static ILauncherOverlay asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.libraries.launcherclient.ILauncherOverlay");
            if (queryLocalInterface instanceof ILauncherOverlay) {
                return (ILauncherOverlay) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    void closeOverlay(int i);

    void endScroll();

    void onPause();

    void onResume();

    void onScroll(float f);

    void openOverlay(int i);

    void setActivityState(int i);

    void startScroll();

    boolean startSearch(byte[] bArr, Bundle bundle);

    void windowAttached(LayoutParams layoutParams, ILauncherOverlayCallback iLauncherOverlayCallback, int i);

    void windowAttached2(Bundle bundle, ILauncherOverlayCallback iLauncherOverlayCallback);

    void windowDetached(boolean z);
}
