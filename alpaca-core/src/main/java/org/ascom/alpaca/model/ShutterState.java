package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ShutterState {
    Open(0), Closed(1), Opening(2), Closing(3), Error(4);

    private final int state;

    ShutterState(int state) {
        this.state = state;
    }

    @JsonValue
    public int getState() {
        return state;
    }

    @JsonCreator
    public static ShutterState fromState(int state) {
        return switch (state) {
            case 0 -> Open;
            case 1 -> Closed;
            case 2 -> Opening;
            case 3 -> Closing;
            case 4 -> Error;
            default -> throw new IllegalArgumentException("The value " + state + " is not a valid shutter state");
        };
    }
}
