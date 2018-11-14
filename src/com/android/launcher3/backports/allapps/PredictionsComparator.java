package com.android.launcher3.backports.allapps;

import java.util.Comparator;

public final class PredictionsComparator implements Comparator {

    public static final PredictionsComparator INSTANCE = new PredictionsComparator();

    private PredictionsComparator() {
    }

    public final int compare(Object obj, Object obj2) {
        return Long.compare(((C0828a) obj).f1201AM, ((C0828a) obj2).f1201AM);
    }
}
