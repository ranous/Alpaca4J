package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The type of sensor the camera is using.
 */
@SuppressWarnings("SpellCheckingInspection")
public enum SensorType {
    Monochrome(0), Color(1), RGGB(2), CMYG(3), CMYG2(4), LRGB(5);

    private final int type;

    SensorType(int state) {
        this.type = state;
    }

    @JsonValue
    public int getType() {
        return type;
    }

    @JsonValue
    public static SensorType fromType(int type) {
        return switch (type) {
            case 0 -> Monochrome;
            case 1 -> Color;
            case 2 -> RGGB;
            case 3 -> CMYG;
            case 4 -> CMYG2;
            case 5 -> LRGB;
            default -> throw new IllegalArgumentException("The value " + type + " is not a valid sensor type");
        };
    }
}
