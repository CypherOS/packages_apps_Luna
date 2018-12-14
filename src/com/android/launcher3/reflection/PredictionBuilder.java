package com.android.launcher3.reflection;

public class PredictionBuilder {

    private static final PredictionBuilder sOverviewGEL = new PredictionBuilder("OVERVIEW_GEL");
    private static final PredictionBuilder sGEL = new PredictionBuilder("GEL");

    public final String lastPredictions;
    public final String lastTimestamp;
    public final String order;
    public final String orderByRank;

    private PredictionBuilder(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_reflection_last_predictions");
        this.lastPredictions = sb.toString();
        sb = new StringBuilder();
        sb.append(str);
        sb.append("_reflection_last_predictions_timestamp");
        this.lastTimestamp = sb.toString();
        sb = new StringBuilder();
        sb.append(str);
        sb.append("_prediction_order");
        this.order = sb.toString();
        sb = new StringBuilder();
        sb.append(str);
        sb.append("_prediction_order_by_rank");
        this.orderByRank = sb.toString();
    }

    public static PredictionBuilder getKey(String str) {
        if ("OVERVIEW_GEL".equals(str)) {
            return sOverviewGEL;
        }
        return sGEL;
    }
}
