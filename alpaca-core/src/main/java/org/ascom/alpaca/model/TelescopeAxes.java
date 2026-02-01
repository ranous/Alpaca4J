package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The mechanicl axis of the mount.
 * @see <a href="https://ascom-standards.org/newdocs/telescope-axes.html">A full description of the mechanical axes is provided here</a>
 */
public enum TelescopeAxes {
    Primary(0),
    Secondary(1),
    Tertiary(2);

    private final int axis;

    TelescopeAxes(int axis) {
        this.axis = axis;
    }

    @JsonValue
    public int getAxis() {
        return axis;
    }

    @JsonCreator
    public static TelescopeAxes fromAxis(int axis) {
        return switch (axis) {
            case 0 -> Primary;
            case 1 -> Secondary;
            case 2 -> Tertiary;
            default -> throw new IllegalArgumentException("The axis value of " + axis + " is not a valid value");
        };
    }
}
