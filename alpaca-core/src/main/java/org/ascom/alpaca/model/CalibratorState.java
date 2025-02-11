package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("unused")
public enum CalibratorState {
    NotPresent(0),
    Off(1),
    NotReady(2),
    Ready(3),
    Unknown(4),
    Error(5);

    private final int state;

    CalibratorState(int state) {
        this.state = state;
    }

    @JsonValue
    public int getState() {
        return state;
    }

    @JsonCreator
    public static CalibratorState fromState(int state) {
        return switch (state) {
            case 0 -> NotPresent;
            case 1 -> Off;
            case 2 -> NotReady;
            case 3 -> Ready;
            case 4 -> Unknown;
            case 5 -> Error;
            default ->
                    throw new IllegalArgumentException("The calibrator state value of " + state + " is not a valid state value");
        };
    }
}
