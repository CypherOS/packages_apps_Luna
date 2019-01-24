/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.aoscp.lovegood.micode.biometrics;

import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.launcher3.Insettable;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.graphics.ColorScrim;
import com.android.launcher3.model.WidgetItem;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.views.AbstractSlideInView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * Bottom sheet for the "Widgets" system shortcut in the long-press popup.
 */
public class BiometricsPrompt extends AbstractSlideInView implements Insettable {

    private static final int DEFAULT_CLOSE_DURATION = 200;
    private Rect mInsets;

    private AuthenticationCallback mCallback;
    private Context mContext;
    private CancellationSignal mCancellationSignal;
    private KeyStore mKeyStore;
    private Cipher mCipher;

    protected final ColorScrim mColorScrim;
    private Runnable mStatusRunnable;
    private TextView mAuthStatus;

    public BiometricsPrompt(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BiometricsPrompt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mColorScrim = ColorScrim.createExtractedColorScrim(this);
        setWillNotDraw(false);
        mInsets = new Rect();
        mContent = this;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        setTranslationShift(mTranslationShift);
    }

    protected void setTranslationShift(float translationShift) {
        super.setTranslationShift(translationShift);
        mColorScrim.setProgress(1 - mTranslationShift);
    }

    public void setCallback(@NonNull final AuthenticationCallback callback) {
        mCallback = callback;
    }

    public void preCreateAndShow(String title) {
        ((TextView) findViewById(R.id.title)).setText(title);
        mAuthStatus = (TextView) findViewById(R.id.status);

        mLauncher.getDragLayer().addView(this);
        mIsOpen = false;
        animateOpen();
        beginAuth();
    }

    private void animateOpen() {
        if (mIsOpen || mOpenCloseAnimator.isRunning()) {
            return;
        }
        mIsOpen = true;
        setupNavBarColor();
        mOpenCloseAnimator.setValues(
                PropertyValuesHolder.ofFloat(TRANSLATION_SHIFT, TRANSLATION_SHIFT_OPENED));
        mOpenCloseAnimator.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        mOpenCloseAnimator.start();
    }

    @Override
    protected void handleClose(boolean animate) {
        handleClose(animate, DEFAULT_CLOSE_DURATION);
        if (mStatusRunnable != null) {
            new Handler().removeCallbacks(mStatusRunnable);
            mStatusRunnable = null;
        }
        if (mCancellationSignal != null) {
            mCancellationSignal.cancel();
            mCancellationSignal = null;
        }
    }

    @Override
    protected boolean isOfType(@FloatingViewType int type) {
        return (type & TYPE_WIDGETS_BOTTOM_SHEET) != 0;
    }

    @Override
    public void setInsets(Rect insets) {
        // Extend behind left, right, and bottom insets.
        int leftInset = insets.left - mInsets.left;
        int rightInset = insets.right - mInsets.right;
        int bottomInset = insets.bottom - mInsets.bottom;
        mInsets.set(insets);
        setPadding(getPaddingLeft() + leftInset, getPaddingTop(),
                getPaddingRight() + rightInset, getPaddingBottom() + bottomInset);
    }

    @Override
    protected int getElementsRowCount() {
        return 1;
    }

    @Override
    protected Pair<View, String> getAccessibilityTarget() {
        return Pair.create(findViewById(R.id.title), mIsOpen ? "BiometricsPrompt open" : "BiometricsPrompt closed");
    }

    protected void onCloseComplete() {
        super.onCloseComplete();
        clearNavBarColor();
    }

    protected void clearNavBarColor() {
        mLauncher.getSystemUiController().updateUiState(
                SystemUiController.UI_STATE_WIDGET_BOTTOM_SHEET, 0);
    }

    protected void setupNavBarColor() {
        boolean isSheetDark = Themes.getAttrBoolean(mLauncher, R.attr.isMainColorDark);
        mLauncher.getSystemUiController().updateUiState(
                SystemUiController.UI_STATE_WIDGET_BOTTOM_SHEET,
                isSheetDark ? SystemUiController.FLAG_DARK_NAV : SystemUiController.FLAG_LIGHT_NAV);
    }

    private void beginAuth() {
        FingerprintManager fingerprintManager = (FingerprintManager) mContext.getSystemService(Context.FINGERPRINT_SERVICE);
        final FingerprintManager.CryptoObject cryptoObject = getCryptoObject();
        if (cryptoObject != null) {
            final FingerprintManager.AuthenticationCallback authCallback = new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    mCallback.onAuthenticationError(errMsgId, errString);
                }

                @Override
                public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                    mCallback.onAuthenticationHelp(helpMsgId, helpString);
                }

                @Override
                public void onAuthenticationFailed() {
                    updateStatus("Not recognized");
                    mCallback.onAuthenticationFailed();
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    handleClose(true);
                    mCallback.onAuthenticationSucceeded();
                }
            };

            mCancellationSignal = new CancellationSignal();
            //noinspection MissingPermission
            fingerprintManager.authenticate(cryptoObject,
                    mCancellationSignal,
                    0,
                    authCallback,
                    new Handler(Looper.getMainLooper()));
        } else {
            handleClose(true);
        }
    }

    private boolean generateKey() {
        mKeyStore = null;
        KeyGenerator keyGenerator;

        //Get the instance of the key store.
        try {
            mKeyStore = KeyStore.getInstance("AndroidKeyStore");
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException | KeyStoreException e) {
            return false;
        }

        //generate key.
        try {
            mKeyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();

            return true;
        } catch (NoSuchAlgorithmException
                | InvalidAlgorithmParameterException
                | CertificateException
                | IOException e) {
            return false;
        }
    }

    private FingerprintManager.CryptoObject getCryptoObject() {
        return cipherInit() ? new FingerprintManager.CryptoObject(mCipher) : null;
    }

    private boolean cipherInit() {
        boolean isKeyGenerated = generateKey();

        if (!isKeyGenerated) return false;

        try {
            mCipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/"
                            + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException e) {
            return false;
        }

        try {
            mKeyStore.load(null);
            SecretKey key = (SecretKey) mKeyStore.getKey(KEY_NAME, null);
            mCipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyStoreException | CertificateException
                | UnrecoverableKeyException | IOException
                | NoSuchAlgorithmException | InvalidKeyException e) {
            return false;
        }
    }

    private void updateStatus(@NonNull final String status) {
        mAuthStatus.setText(status);
        mStatusRunnable = new Runnable() {
            @Override
            public void run() {
                if (mIsOpen) {
                    mAuthStatus.setText("");
                }
            }
        };
        new Handler().postDelayed(mStatusRunnable, 1000 /* 1 second */);
    }
}