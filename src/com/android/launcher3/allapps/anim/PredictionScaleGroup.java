package com.android.launcher3.allapps.anim;

import com.android.launcher3.allapps.PredictionRowView;

public final class PredictionScaleGroup implements Runnable {

    private final PredictionRowView mRowView;

    public PredictionScaleGroup(PredictionRowView predictionRowView) {
        mRowView = predictionRowView;
    }

    public final void run() {
        mRowView.updateTranslationAndAlpha();
    }
}
