package org.ascom.alpaca.device;

/**
 * Interface for observing conditions devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * @see <a href="https://ascom-standards.org/api/#/ObservingConditions%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings("unused")
public interface ObservingConditionsDevice extends Device {
    /**
     * The version of the Alpaca ObservingConditions interface. Classes implementing this interface
     * should return this from the {@link #getInterfaceVersion}.
     */
    int interfaceVersion = 2;

    /**
     * Returns the time period in hours over which observations will be averaged.
     * @return the average period
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.AveragePeriod">A full description of this member's behavior is provided here</a>
     */
    double getAveragePeriod();

    /**
     * Sets the time period in hours over which observations will be averaged.
     * @param averagePeriod the average period specified, in hours.
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.AveragePeriod">A full description of this member's behavior is provided here</a>
     */
    void setAveragePeriod(double averagePeriod);

    /**
     * Returns the amount of sky obscured by cloud
     * @return the cloud cover in percent
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.CloudCover">A full description of this member's behavior is provided here</a>
     */
    double getCloudCover();

    /**
     * Returns the atmospheric dew point at the observatory
     * @return the dew point in degrees Celsius
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.DewPoint">A full description of this member's behavior is provided here</a>
     */
    double getDewPoint();

    /**
     * Returns the atmospheric humidity at the observatory
     * @return the humidity in percent
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Humidity">A full description of this member's behavior is provided here</a>
     */
    double getHumidity();

    /**
     * Returns the atmospheric pressure at the observatory.
     * @return the pressure in hPa
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Pressure">A full description of this member's behavior is provided here</a>
     */
    double getPressure();

    /**
     * Returns the rain rate at the observatory.
     * @return the rain rate in mm/h
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.RainRate">A full description of this member's behavior is provided here</a>
     */
    double getRainRate();

    /**
     * Returns the sky brightness at the observatory
     * @return the sky brightness in lux
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyBrightness">A full description of this member's behavior is provided here</a>
     */
    double getSkyBrightness();

    /**
     * Returns the sky quality at the observatory.
     * @return the sky quality in SQM
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyQuality">A full description of this member's behavior is provided here</a>
     */
    double getSkyQuality();

    /**
     * Returns the sky temperature at the observatory
     * @return the sky temperature in degrees Celsius
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyTemperature">A full description of this member's behavior is provided here</a>
     */
    double getSkyTemperature();

    /**
     * Returns the seeing at the observatory
     * @return the seeing in arcseconds
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.StarFWHM">A full description of this member's behavior is provided here</a>
     */
    double getStarFWHM();

    /**
     * Returns the temperature at the observatory (°C)
     * @return the temperature in degrees Celsius
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Temperature">A full description of this member's behavior is provided here</a>
     */
    double getTemperature();

    /**
     * Returns the wind direction at the observatory
     * @return the wind direction in degrees
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindDirection">A full description of this member's behavior is provided here</a>
     */
    double getWindDirection();

    /**
     * Returns the peak 3 second wind gust at the observatory over the last 2 minutes
     * @return the wind gust in km/h
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindGust">A full description of this member's behavior is provided here</a>
     */
    double getWindGust();

    /**
     * Returns the wind speed at the observatory.
     * @return the wind speed in km/h
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindSpeed">A full description of this member's behavior is provided here</a>
     */
    double getWindSpeed();

    /**
     * Refreshes sensor values from hardware.
     *
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Refresh">A full description of this member's behavior is provided here</a>
     */
    void refresh();

    /**
     * Return a sensor description
     *
     * @param sensorName Sensor name
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SensorDescription">A full description of this member's behavior is provided here</a>
     */
    String getSensorDescription(String sensorName);

    /**
     * Return the time since the sensor value was last updated
     *
     * @param sensorName name of the sensor
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.TimeSinceLastUpdate">A full description of this member's behavior is provided here</a>
     */
    double getTimeSinceLastUpdate(String sensorName);
}
