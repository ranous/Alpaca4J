package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PierSide {
    East(0),
    West(1),
    Unknown(-1);

    private final int side;

    PierSide(int side) {
        this.side = side;
    }

    @JsonValue
    public int getSide() {
        return side;
    }

    @JsonCreator
    public static PierSide fromSide(int side) {
        return switch (side) {
            case 0 -> East;
            case 1 -> West;
            case -1 -> Unknown;
            default -> throw new IllegalArgumentException("The value " + side + " is not a valid pier side");
        };
    }
}
