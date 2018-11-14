package com.android.launcher3.backports.allapps;

public final class AnimatedFloatListener implements Runnable {

    private final PredictionRowView mPredictionRowView;

    public AnimatedFloatListener(PredictionRowView predictionRowView) {
        mPredictionRowView = predictionRowView;
    }

    public final void run() {
        mPredictionRowView.mo5375di();
    }
}
