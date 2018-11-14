package com.android.launcher3.backports;

public final class AppInfoPackageListener implements C0861l {

    private final PredictionUiStateManager mPredictionUiStateManager;

    public AppInfoPackageListener(PredictionUiStateManager predictionUiStateManager) {
        mPredictionUiStateManager = predictionUiStateManager;
    }

    public final void onUpdateUI() {
        mPredictionUiStateManager.mo5358cV();
    }
}
