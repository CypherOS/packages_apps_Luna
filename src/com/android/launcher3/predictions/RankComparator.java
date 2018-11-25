package com.android.launcher3.predictions;

import java.util.Comparator;

public final class RankComparator implements Comparator {

    public static final RankComparator INSTANCE = new RankComparator();

    private RankComparator() {
    }

    public final int compare(Object obj, Object obj2) {
        return Integer.valueOf(((C0759a) obj).f981xv.mShortcutInfo.getRank()).compareTo(Integer.valueOf(((C0759a) obj2).f981xv.mShortcutInfo.getRank()));
    }
}
