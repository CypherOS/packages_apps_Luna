package com.google.research.reflection.utils;

import java.util.LinkedHashMap;

class PredictorFactory$1 extends LinkedHashMap<String, Integer> {
    PredictorFactory$1() {
        put("recency_event_predictor", Integer.valueOf(1));
        put("Rule_Based_Predictor", Integer.valueOf(1));
        put("shortcut_neural_predictor", Integer.valueOf(1));
        put("neural_predictor", Integer.valueOf(Integer.MAX_VALUE));
    }
}
