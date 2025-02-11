package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("Minimum")
    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    @JsonProperty("Maximum")
    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
