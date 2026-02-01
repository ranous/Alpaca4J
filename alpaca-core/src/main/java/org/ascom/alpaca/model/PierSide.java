package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The PierSide enum represents the pointing state of the mount.
 * @see <a href="https://ascom-standards.org/newdocs/ptgstate-faq.html#ptgstate-faq">A full description of is provided here</a>
 */
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
