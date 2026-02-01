package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The EquatorialCoordinateType enum represents the possible coordinate types for equatorial coordinates by the mount.
 */
@SuppressWarnings("SpellCheckingInspection")
public enum EquatorialCoordinateType {
    Other(0),
    Topocentric(1),
    J2000(2),
    J2050(3),
    B1950(4);

    private final int type;

    EquatorialCoordinateType(int type) {
        this.type = type;
    }

    @JsonValue
    public int getType() {
        return type;
    }

    @JsonCreator
    public static EquatorialCoordinateType fromType(int type) {
        return switch (type) {
            case 0 -> Other;
            case 1 -> Topocentric;
            case 2 -> J2000;
            case 3 -> J2050;
            case 4 -> B1950;
            default ->
                    throw new IllegalArgumentException("The value of " + type + " is not a valid EquatorialCoordinateType");
        };
    }
}
