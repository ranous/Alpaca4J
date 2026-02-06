package org.ascom.alpaca.example;

/**
 * Represents the different types of measurements that can be measured by an Alpaca ObservingConditions device.
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public enum MeasurementType {
    CloudCover("cloudcover"),
    DewPoint("dewpoint"),
    Humidity("humidity"),
    Pressure("pressure"),
    RainRate("rainrate"),
    SkyBrightness("skybrightness"),
    SkyQuality("skyquality"),
    SkyTemperature("skytemperature"),
    StarFWHM("starfwhm"),
    Temperature("temperature"),
    WindDirection("winddirection"),
    WindGust("windgust"),
    WindSpeed("windspeed");

    private final String name;

    MeasurementType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MeasurementType fromString(String name) {
        for (MeasurementType type : MeasurementType.values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
