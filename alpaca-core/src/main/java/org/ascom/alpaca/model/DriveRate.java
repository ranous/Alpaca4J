package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DriveRate {
    Sidereal(0),
    Lunar(1),
    Solar(2),
    King(3);

    private final int rate;

    DriveRate(int rate) {
        this.rate = rate;
    }

    @JsonValue
    public int getRate() {
        return rate;
    }

    @JsonCreator
    public static DriveRate fromRate(Integer rate) {
        return switch (rate) {
            case 0 -> Sidereal;
            case 1 -> Lunar;
            case 2 -> Solar;
            case 3 -> King;
            default -> throw new IllegalArgumentException("The drive rate value of " + rate + " is not a valid rate");
        };
    }
}
