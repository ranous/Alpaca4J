package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Indicates the state of the of a cover calibrator device.
 */
@SuppressWarnings("unused")
public enum CoverState {
    NotPresent(0),
    Closed(1),
    Moving(2),
    Open(3),
    Unknown(4),
    Error(5);

    private final int state;

    CoverState(int state) {
        this.state = state;
    }

    @JsonValue
    public int getState() {
        return state;
    }

    @JsonCreator
    public static CoverState fromState(int state) {
        return switch (state) {
            case 0 -> NotPresent;
            case 1 -> Closed;
            case 2 -> Moving;
            case 3 -> Open;
            case 4 -> Unknown;
            case 5 -> Error;
            default ->
                    throw new IllegalArgumentException("The cover state value of " + state + " is not a valid state value");
        };
    }
}
