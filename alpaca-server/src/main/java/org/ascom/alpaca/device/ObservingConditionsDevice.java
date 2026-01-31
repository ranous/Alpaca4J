package org.ascom.alpaca.device;

/**
 * Interface for observing conditions devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * @see <a href="https://ascom-standards.org/api/#/ObservingConditions%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings("unused")
public interface ObservingConditionsDevice extends Device {
    /**
     * Returns the time period over which observations will be averaged.
     * @return the average period
     * @throws org.ascom.alpaca.response.NotConnectedException if the client is not connected
     */
    double getAveragePeriod();

    /**
     * Sets the time period over which observations will be averaged.
     * @param averagePeriod the average period specified, in hours.
     */
    void setAveragePeriod(double averagePeriod);

    /**
     * Returns the cloud cover percentage.
     * @return the cloud cover percentage
     * @throws org.ascom.alpaca.response.InvalidValueException if clientID is negative
     * @throws org.ascom.alpaca.response.NotConnectedException if the client is not connected
     */
    double getCloudCover();
    double getDewPoint();
    double getHumidity();
    double getPressure();
    double getRainRate();
    double getSkyBrightness();
    double getSkyQuality();
    double getSkyTemperature();
    double getStarFWHM();
    double getTemperature();
    double getWindDirection();
    double getWindGust();
    double getWindSpeed();
    void refresh();
    String getSensorDescription(String sensorName);
    double getTimeSinceLastUpdate(String sensorName);
}
