package org.ascom.alpaca.example;

/**
 * Represents a measurement taken by an Alpaca ObservingConditions device.
 */
public record Measurement(MeasurementType type, double value, long timestamp) {}
