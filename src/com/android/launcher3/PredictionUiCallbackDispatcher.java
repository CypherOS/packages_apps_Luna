package com.android.launcher3;

public final class PredictionUiCallbackDispatcher implements PredictionUiCallback {

    private PredictionUiStateManager mUiStateManager;

    public PredictionUiCallbackDispatcher(PredictionUiStateManager predictionUiStateManager) {
        mUiStateManager = predictionUiStateManager;
    }

    public final void onUpdateUI() {
        mUiStateManager.dispatchOnChange();
    }
}
