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

    @Override
    public double getRainRate() {
        return rainRate;
    }

    @Override
    public double getSkyBrightness() {
        return skyBrightness;
    }

    @Override
    public double getSkyQuality() {
        return skyQuality;
    }

    @Override
    public double getSkyTemperature() {
        return skyTemperature;
    }

    @Override
    public double getStarFWHM() {
        return starFWHM;
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public double getWindDirection() {
        return windDirection;
    }

    @Override
    public double getWindGust() {
        return windGust;
    }

    @Override
    public double getWindSpeed() {
        return windSpeed;
    }

    @Override
    public void refresh() {
        lastUpdate = System.currentTimeMillis();
    }

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

    @Override
    public double getTimeSinceLastUpdate(String sensorName) {
        long curTime = System.currentTimeMillis();
        long duration = (curTime - lastUpdate)/1000;
        if (duration > 300) {
            lastUpdate = curTime;
        }
        return duration;
    }

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
