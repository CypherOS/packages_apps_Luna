package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import com.google.android.apps.miphone.aiai.matchmaker.api.FeedbackData;
import com.google.android.apps.miphone.aiai.matchmaker.api.IScreenMatchmaker;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.P */
final /* synthetic */ class C0887P implements Runnable {
    /* renamed from: yV */
    private final IScreenMatchmaker f1063yV;
    /* renamed from: yW */
    private final String f1064yW;
    /* renamed from: yX */
    private final FeedbackData f1065yX;

    C0887P(IScreenMatchmaker iScreenMatchmaker, String str, FeedbackData feedbackData) {
        this.f1063yV = iScreenMatchmaker;
        this.f1064yW = str;
        this.f1065yX = feedbackData;
    }

    public final void run() {
        IScreenMatchmaker iScreenMatchmaker = this.f1063yV;
        try {
            ((IScreenMatchmaker) C0915av.m613t(iScreenMatchmaker)).reportFeedback(this.f1064yW, this.f1065yX);
        } catch (Throwable e) {
            C0936x.m647a("Failed to call service - report metrics/feedback.", e);
        }
    }
}
