package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The movement rate limits of an axis of the mount.
 */
@SuppressWarnings("unused")
public class AxisRate {
    double min;
    double max;

    public AxisRate() {

    }

    public AxisRate(double min, double max) {
        this.min = min;
        this.max = max;
    }

    /**
     * The minimum rate (degrees per second). This must always be a positive number.
     * It indicates the minimum rate in either direction about the axis.
     *
     * @return The minimum rate (degrees per second).
     */
    @JsonProperty("Minimum")
    public double getMin() {
        return min;
    }


    /**
     * The minimum rate (degrees per second). This must always be a positive number.
     * It indicates the minimum rate in either direction about the axis.
     *
     * @param min The minimum rate (degrees per second).
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * The maximum rate (degrees per second). This must always be a positive number.
     * It indicates the maximum rate in either direction about the axis.
     *
     * @return The maximum rate (degrees per second).
     */
    @JsonProperty("Maximum")
    public double getMax() {
        return max;
    }

    /**
     * The maximum rate (degrees per second). This must always be a positive number.
     * It indicates the maximum rate in either direction about the axis.
     *
     * @param max The maximum rate (degrees per second).
     */
    public void setMax(double max) {
        this.max = max;
    }
}
