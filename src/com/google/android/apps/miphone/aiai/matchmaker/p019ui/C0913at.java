package com.google.android.apps.miphone.aiai.matchmaker.p019ui;

import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import android.view.textclassifier.TextSelection.Request;

/* renamed from: com.google.android.apps.miphone.aiai.matchmaker.ui.at */
class C0913at implements TextClassifier {
    /* renamed from: zM */
    final /* synthetic */ C0902ah f1113zM;

    C0913at(C0902ah c0902ah) {
        this.f1113zM = c0902ah;
    }

    public TextSelection suggestSelection(Request request) {
        return this.f1113zM.mo3343b(request.getText(), request.getStartIndex(), request.getEndIndex());
    }

    public TextClassification classifyText(TextClassification.Request request) {
        return this.f1113zM.mo3342a(request.getText(), request.getStartIndex(), request.getEndIndex());
    }
}
