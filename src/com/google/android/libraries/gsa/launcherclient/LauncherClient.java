package com.google.android.libraries.gsa.launcherclient;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.android.launcher3.AbstractFloatingView;
import com.google.android.libraries.launcherclient.ILauncherOverlay;
import com.google.android.libraries.launcherclient.ILauncherOverlayCallback.Stub;
import java.lang.ref.WeakReference;

public class LauncherClient {
    protected ILauncherOverlay mOverlay;
    public int mState = 0;
    public boolean mDestroyed = false;
    public int mServiceStatus = 0;
    public int mFlags;
    public LayoutParams mLayoutParams;
    public OverlayCallbacks mOverlayCallback;
    private Bundle mBundle;
    public final Activity mActivity;
    private final LauncherClientCallbacks mClientCallbacks;
    public final C1245b mEvent = new C1245b("Client", 20);
    public final C1245b mEventService = new C1245b("Service", 10);
    public final C1251i aex;
    public final ClientService mClientService;
    public final BroadcastReceiver mInstallListener = new C1247d(this);

    public class OverlayCallbacks extends Stub implements Callback {
        public LauncherClient mClient;
        private final Handler mUiHandler = new Handler(Looper.getMainLooper(), this);
        public Window mWindow;
        private boolean mWindowHidden = false;
        public WindowManager mWindowManager;
        public int mWindowShift;

        OverlayCallbacks() {
        }

        public void overlayScrollChanged(float f) {
            mUiHandler.removeMessages(2);
            Message.obtain(mUiHandler, 2, Float.valueOf(f)).sendToTarget();
            if (f > 0.0f && mWindowHidden) {
                mWindowHidden = false;
            }
        }

        public void overlayStatusChanged(int i) {
            Message.obtain(mUiHandler, 4, i, 0).sendToTarget();
        }

        public boolean handleMessage(Message message) {
            if (mClient == null) {
                return true;
            }
            switch (message.what) {
                case 2:
                    if ((mClient.mServiceStatus & 1) != 0) {
                        float floatValue = ((Float) message.obj).floatValue();
                        mClient.mClientCallbacks.onOverlayScrollChanged(floatValue);
                        if (floatValue <= 0.0f) {
                            mClient.mEventService.parse(0, "onScroll 0, overlay closed", 0.0f);
                        } else if (floatValue >= 1.0f) {
                            mClient.mEventService.parse(0, "onScroll 1, overlay opened", 0.0f);
                        } else {
                            mClient.mEventService.parse(1, "onScroll", floatValue);
                        }
                    }
                    return true;
                case 3:
                    LayoutParams attributes = mWindow.getAttributes();
                    if (((Boolean) message.obj).booleanValue()) {
                        attributes.x = mWindowShift;
                        attributes.flags |= 512;
                    } else {
                        attributes.x = 0;
                        attributes.flags &= -513;
                    }
                    mWindowManager.updateViewLayout(mWindow.getDecorView(), attributes);
                    return true;
                case 4:
                    mClient.m3611bm(message.arg1);
                    mClient.mEventService.parse("stateChanged", message.arg1);
                    if (mClient.mClientCallbacks instanceof C1634h) {
                        ((C1634h) mClient.mClientCallbacks).mo5125aw(message.arg1);
                    }
                    return true;
                default:
                    return false;
            }
        }
    }

    public LauncherClient(Activity activity, LauncherClientCallbacks c1250g, ClientOptions c1249f) {
        mActivity = activity;
        mClientCallbacks = c1250g;
        this.aex = new C1251i(activity, 65);
        mFlags = c1249f.options;
        mClientService = ClientService.m5340I((Context) activity);
        ClientService c1633a = mClientService;
        c1633a.mWeakReference = new WeakReference(this);
        mOverlay = c1633a.ael;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        if (VERSION.SDK_INT >= 19) {
            intentFilter.addDataSchemeSpecificPart("com.google.android.googlequicksearchbox", 0);
        }
        mActivity.registerReceiver(mInstallListener, intentFilter);
        reconnect();
        if (VERSION.SDK_INT >= 19 && mActivity.getWindow() != null && mActivity.getWindow().peekDecorView() != null && mActivity.getWindow().peekDecorView().isAttachedToWindow()) {
            onAttachedToWindow();
        }
    }

    public void onAttachedToWindow() {
        if (!mDestroyed) {
            mEvent.parse(0, "attachedToWindow", 0.0f);
            setParams(mActivity.getWindow().getAttributes());
        }
    }

    public void onResume() {
        if (!mDestroyed) {
            mState |= 2;
            if (!(mOverlay == null || mLayoutParams == null)) {
                try {
                    mOverlay.onResume();
                } catch (RemoteException e) {
                }
            }
            mEvent.parse("stateChanged ", mState);
        }
    }

    public void onPause() {
        if (!mDestroyed) {
            mState &= -3;
            if (!(mOverlay == null || mLayoutParams == null)) {
                try {
                    mOverlay.onPause();
                } catch (RemoteException e) {
                }
            }
            mEvent.parse("stateChanged ", mState);
        }
    }

    public void onStart() {
        if (!mDestroyed) {
            mClientService.mo5123I(false);
            reconnect();
            mState |= 1;
            if (!(mOverlay == null || mLayoutParams == null)) {
                try {
                    mOverlay.setActivityState(mState);
                } catch (RemoteException e) {
                }
            }
            mEvent.parse("stateChanged ", mState);
        }
    }

    public void onStop() {
        if (!mDestroyed) {
            mClientService.mo5123I(true);
            this.aex.disconnect();
            mState &= -2;
            if (!(mOverlay == null || mLayoutParams == null)) {
                try {
                    mOverlay.setActivityState(mState);
                } catch (RemoteException e) {
                }
            }
            mEvent.parse("stateChanged ", mState);
        }
    }

    public void reconnect() {
        if (!mDestroyed) {
            if (!(mClientService.mo4046im() && this.aex.mo4046im())) {
                mActivity.runOnUiThread(new C1248e(this));
            }
        }
    }

    /* renamed from: a */
    public void setParams(LayoutParams layoutParams) {
        if (mLayoutParams != layoutParams) {
            mLayoutParams = layoutParams;
            if (mLayoutParams != null) {
                updateConfiguration();
                return;
            }
            if (mOverlay != null) {
                try {
                    mOverlay.onDetachedFromWindow(mActivity.isChangingConfigurations());
                } catch (RemoteException e) {
                }
                mOverlay = null;
            }
        }
    }

    /* renamed from: ih */
    public void updateConfiguration() {
        if (mOverlay != null) {
            try {
                if (mOverlayCallback == null) {
                    mOverlayCallback = new OverlayCallbacks();
                }
                OverlayCallbacks overlayCallbacks = mOverlayCallback;
                overlayCallbacks.mClient = this;
                overlayCallbacks.mWindowManager = mActivity.getWindowManager();
                Point point = new Point();
                overlayCallbacks.mWindowManager.getDefaultDisplay().getRealSize(point);
                overlayCallbacks.mWindowShift = -Math.max(point.x, point.y);
                overlayCallbacks.mWindow = mActivity.getWindow();
                mOverlay.windowAttached(mLayoutParams, mOverlayCallback, mFlags);
                if ((mState & 2) != 0) {
                    mOverlay.onResume();
                } else {
                    mOverlay.onPause();
                }
            } catch (RemoteException e) {
            }
        }
    }

    public boolean isConnected() {
        return mOverlay != null;
    }

    /* renamed from: ii */
    public void startMove() {
        mEvent.parse(0, "startMove", 0.0f);
        if (isConnected()) {
            try {
                mOverlay.startScroll();
            } catch (RemoteException e) {
            }
        }
    }

    /* renamed from: ij */
    public void endMove() {
        mEvent.parse(0, "endMove", 0.0f);
        if (isConnected()) {
            try {
                mOverlay.endScroll();
            } catch (RemoteException e) {
            }
        }
    }

    /* renamed from: i */
    public void updateMove(float f) {
        mEvent.parse(1, "updateMove", f);
        if (isConnected()) {
            try {
                mOverlay.onScroll(f);
            } catch (RemoteException e) {
            }
        }
    }

    /* renamed from: J */
    public void hideOverlay(boolean z) {
        mEvent.mo4037c("hideOverlay", z);
        if (mOverlay != null) {
            try {
                mOverlay.closeOverlay(z);
            } catch (RemoteException e) {
            }
        }
    }

    public boolean startSearch(byte[] bArr, Bundle bundle) {
        mEvent.parse(0, "startSearch", 0.0f);
        if (mOverlay != null) {
            try {
                return mOverlay.startSearch(bArr, bundle);
            } catch (RemoteException e) {
                Log.e("DrawerOverlayClient", "Error starting session for search", e);
            }
        }
        return false;
    }

    /* renamed from: i */
    public void mo4020i(Bundle bundle) {
        C1245b c1245b = mEvent;
        String str = "setPrivateOptions : ";
        String valueOf = String.valueOf(bundle == null ? "null" : TextUtils.join(",", bundle.keySet()));
        c1245b.parse(0, valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0.0f);
        mBundle = bundle;
        mo4024ik();
    }

    /* renamed from: ik */
    public void mo4024ik() {
        if (mLayoutParams != null) {
            updateConfiguration();
        }
    }

    /* renamed from: b */
    final void mo4017b(ILauncherOverlay iLauncherOverlay) {
        mEventService.mo4037c("Connected", iLauncherOverlay != null);
        mOverlay = iLauncherOverlay;
        if (mOverlay == null) {
            m3611bm(0);
            return;
        }
        if (mLayoutParams != null) {
            updateConfiguration();
        }
    }

    /* renamed from: bm */
    public void m3611bm(int i) {
        if (mServiceStatus != i) {
            mServiceStatus = i;
            LauncherClientCallbacks c1250g = mClientCallbacks;
            boolean attached = true;
            if ((i & 1) == 0) {
                attached = false;
            }
            c1250g.onServiceStateChanged(attached);
        }
    }

    /* renamed from: J */
    static Intent m3605J(Context context) {
        String packageName = context.getPackageName();
        int myUid = Process.myUid();
        StringBuilder stringBuilder = new StringBuilder(18 + String.valueOf(packageName).length());
        stringBuilder.append("app://");
        stringBuilder.append(packageName);
        stringBuilder.append(":");
        stringBuilder.append(myUid);
        return new Intent("com.android.launcher3.WINDOW_OVERLAY").setPackage("com.google.android.googlequicksearchbox").setData(Uri.parse(stringBuilder.toString()).buildUpon().appendQueryParameter("v", Integer.toString(9)).appendQueryParameter("cv", Integer.toString(14)).build());
    }

	public Activity getActivity() {
		return mActivity;
	}

	public C1251i getBaseService() {
		return aex;
	}

	public int getClientOptions() {
		return mFlags;
	}

	public ClientService getClientService() {
		return mClientService;
	}

	public C1245b getEventInfo() {
		return mEvent;
	}

	public OverlayCallbacks getOverlayCallback() {
		return mOverlayCallback;
	}

	public void setOverlayCallback(OverlayCallbacks callback) {
		mOverlayCallback = callback;
	}

	public LayoutParams getParams() {
		return mLayoutParams;
	}

	public boolean isDestroyed() {
		return mDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		mDestroyed = isDestroyed;
	}
}
