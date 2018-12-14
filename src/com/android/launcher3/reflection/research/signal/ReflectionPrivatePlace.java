package com.android.launcher3.reflection.research.signal;

import java.util.List;

public interface ReflectionPrivatePlace {

    public enum Alias {
        UNKNOWN,
        HOME,
        WORK,
        LOCAL,
        TRAVEL,
        TRIP,
        TRIP_STAY,
        COMMUTE_HOME_TO_WORK,
        COMMUTE_WORK_TO_HOME,
        FLIGHT_TAKEOFF,
        FLIGHT_LANDING,
        EXITING_VEHICLE
    }

    /* renamed from: M */
    List<Alias> mo9274M();

    long getTime();
}
