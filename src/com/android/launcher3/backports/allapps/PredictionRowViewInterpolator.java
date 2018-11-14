package com.android.launcher3.backports.allapps;

import android.view.animation.Interpolator;

public final class PredictionRowViewInterpolator implements Interpolator {

    public static final PredictionRowViewInterpolator INSTANCE = new PredictionRowViewInterpolator();

    private PredictionRowViewInterpolator() {
    }

    public final float getInterpolation(float f) {
        return PredictionRowView.lambda$static$0(f);
    }
}
