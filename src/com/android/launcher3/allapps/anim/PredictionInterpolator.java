package com.android.launcher3.allapps.anim;

import android.view.animation.Interpolator;
import com.android.launcher3.allapps.PredictionRowView;

public final class PredictionInterpolator implements Interpolator {

    public static final PredictionInterpolator INSTANCE = new PredictionInterpolator();

    private PredictionInterpolator() {
    }

    public final float getInterpolation(float input) {
        return PredictionRowView.setInterpolation(input);
    }
}
