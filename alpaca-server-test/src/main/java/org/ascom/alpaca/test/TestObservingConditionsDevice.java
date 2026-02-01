package org.ascom.alpaca.test;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.ObservingConditionsDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.ActionNotImplementedException;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.PropertyNotImplementedException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"FieldCanBeLocal", "SpellCheckingInspection"})
@Singleton
public class TestObservingConditionsDevice extends BaseDevice implements ObservingConditionsDevice {
    private static final Logger log = LoggerFactory.getLogger(TestObservingConditionsDevice.class);
    private final String VERSION = "1.0";
    private final double dewPoint = 15.7;
    private final double humidity = 63.4;
    private final double pressure = 1023;
    private final double rainRate = 0.0;
    private final double temperature = 16.4;
    private final double windDirection = 33.0;
    private final double windGust = 11.3;
    private final double windSpeed = 3.0;
    private final double cloudCoverage = 52;
    private final double skyBrightness = 0.00693304;
    private final double skyQuality =17.88;
    private final double skyTemperature = -6;
    private final double starFWHM = 2.2;
    private long lastUpdate = System.currentTimeMillis();


    private final List<String> supportedMethods = List.of("averageperiod", "cloudcover", "dewpoint", "humidity", "pressure", "rainrate",
            "skybrightness", "skyquality", "skytemperature", "starfwhm", "temperature", "winddirection", "windgust", "windspeed");

    public TestObservingConditionsDevice() {
        super(DeviceType.ObservingConditions, "Test Observing Conditions", 2);
        setDescription("Test Observing Conditions Device");
        setDriverInfo(getDescription() + ". Version " + VERSION);
    }

    // The version of the driver is injected from the microprofile-config.properties file and can be overridden
    // by the system property test.driver.version
    @Inject
    public TestObservingConditionsDevice(@ConfigProperty(name="test.driver.version", defaultValue = "1.0") String deviceVersion) {
        this();
        setDriverVersion(deviceVersion);
    }

    @Override
    public List<StateValue> getDeviceState() {
        List<StateValue> deviceState = new ArrayList<>();
        deviceState.add(new StateValue("DewPoint", getDewPoint()));
        deviceState.add(new StateValue("Humidity", getHumidity()));
        deviceState.add(new StateValue("Pressure", getPressure()));
        deviceState.add(new StateValue("RainRate", getRainRate()));
        deviceState.add(new StateValue("Temperature", getTemperature()));
        deviceState.add(new StateValue("WindDirection", getWindDirection()));
        deviceState.add(new StateValue("WindGust", getWindGust()));
        deviceState.add(new StateValue("WindSpeed", getWindSpeed()));
        return deviceState;
    }

    @Override
    public double getAveragePeriod() {
        return 300;
    }

    @Override
    public void setAveragePeriod(double averagePeriod) {
        log.info("Setting average interval to {}", averagePeriod);
    }

    @Override
    public double getCloudCover() {
        return cloudCoverage;
    }

    @Override
    public double getDewPoint() {
        return dewPoint;
    }

    @Override
    public double getHumidity() {
        return humidity;
    }

    @Override
    public double getPressure() {
        return pressure;
    }

    /**
     * The current rain rate in mm/h.
     * @return the rain rate
     */
    @Override
    public double getRainRate() {
        return rainRate;
    }

    /**
     * The current sky brightness in lux.
     * @return the sky brightness
     */
    @Override
    public double getSkyBrightness() {
        return skyBrightness;
    }

    /**
     * The current sky quality in magnitudes per square arcsecond (SQM).
     * @return the sky quality
     */
    @Override
    public double getSkyQuality() {
        return skyQuality;
    }

    /**
     * The current sky temperature in degrees Celsius.
     * @return
     */
    @Override
    public double getSkyTemperature() {
        return skyTemperature;
    }

    /**
     * The current star FWHM in arcseconds.
     * @return the star FWHM
     */
    @Override
    public double getStarFWHM() {
        return starFWHM;
    }

    /**
     * The current temperature in degrees Celsius.
     * @return the temperature
     */
    @Override
    public double getTemperature() {
        return temperature;
    }

    /**
     * The current wind direction in degrees from which the wind is blowing.
     * @return the wind direction
     */
    @Override
    public double getWindDirection() {
        return windDirection;
    }

    /**
     * The peak 3-second wind gust in m/s over the last 2 minutes.
     * @return the wind gust
     */
    @Override
    public double getWindGust() {
        return windGust;
    }

    /**
     * The current wind speed in m/s.
     * @return the wind speed
     */
    @Override
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Refresh the device state.
     */
    @Override
    public void refresh() {
        lastUpdate = System.currentTimeMillis();
    }

    /**
     * Get the description of a sensor.
     * @param sensorName the name of the sensor
     * @return the sensor description
     */
    @Override
    public String getSensorDescription(String sensorName) {
        if (sensorName == null || sensorName.isEmpty()) {
            throw new InvalidValueException("A sensor name was not supplied");
        }
        if (supportedMethods.stream().noneMatch(n -> n.equalsIgnoreCase(sensorName))) {
            throw new PropertyNotImplementedException("Property " + sensorName + " is not implemented");
        }

        return "Meteobridge " + sensorName + " sensor";
    }

    /**
     * Get the time since the last update of a sensor.
     * @param sensorName
     * @return
     */
    @Override
    public double getTimeSinceLastUpdate(String sensorName) {
        long curTime = System.currentTimeMillis();
        long duration = (curTime - lastUpdate)/1000;
        if (duration > 300) {
            lastUpdate = curTime;
        }
        return duration;
    }

    /**
     * Execute an action on the device.
     * @param action the name of the action to execute
     * @param parameters the parameters to pass to the action function
     * @return
     */
    @Override
    public String executeAction(String action, String parameters) {
        if (action.equalsIgnoreCase("testAction")) {
            return testAction(parameters);
        } else {
            throw new ActionNotImplementedException("Unknown action: " + action);
        }
    }

    public String testAction(String parameters) {
        log.info("Executing testAction {}", parameters);
        return "Hi there " + parameters;
    }
}
