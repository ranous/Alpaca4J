package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The AlignmentMode enum represents the possible alignment modes of a mount.
 */
@SuppressWarnings("unused")
public enum AlignmentMode {
    AltAz(0),
    Polar(1),
    GermanPolar(2);

    private final int mode;

    AlignmentMode(int mode) {
        this.mode = mode;
    }

    @JsonValue
    public int getMode() {
        return mode;
    }

    @JsonCreator
    public static AlignmentMode fromMode(int mode) {
        return switch (mode) {
            case 0 -> AltAz;
            case 1 -> Polar;
            case 2 -> GermanPolar;
            default -> throw new IllegalArgumentException("The mode value of " + mode + " isn't a valid mode");
        };
    }
}
