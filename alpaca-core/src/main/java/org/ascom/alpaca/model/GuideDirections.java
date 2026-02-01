package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The GuideDirections enum represents the possible directions in which a pulse-rate motion is to be made.
 * These are not mechanical axes, these are the direction in the equatorial coordinate system.
 * @see <a href=https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideDirections">A full description of is provided here</a>
 */
@SuppressWarnings("unused")
public enum GuideDirections {
    North(0),
    South(1),
    East(2),
    West(3);

    private final int direction;

    GuideDirections(int direction) {
        this.direction = direction;
    }

    @JsonValue
    public int getDirection() {
        return direction;
    }

    @JsonCreator
    public static GuideDirections fromDirection(int direction) {
        return switch (direction) {
            case 0 -> North;
            case 1 -> South;
            case 2 -> East;
            case 3 -> West;
            default ->
                    throw new IllegalArgumentException("The GuideDirections value of " + direction + " is not a valid direction value");
        };
    }
}
