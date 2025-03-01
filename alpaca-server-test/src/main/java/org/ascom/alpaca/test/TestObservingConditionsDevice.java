package org.ascom.alpaca.test;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.ObservingConditionsDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;
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
    public List<StateValue> getDeviceState(int clientID) {
        checkConnectionStatus(clientID);
        List<StateValue> deviceState = new ArrayList<>();
        deviceState.add(new StateValue("DewPoint", getDewPoint(clientID)));
        deviceState.add(new StateValue("Humidity", getHumidity(clientID)));
        deviceState.add(new StateValue("Pressure", getPressure(clientID)));
        deviceState.add(new StateValue("RainRate", getRainRate(clientID)));
        deviceState.add(new StateValue("Temperature", getTemperature(clientID)));
        deviceState.add(new StateValue("WindDirection", getWindDirection(clientID)));
        deviceState.add(new StateValue("WindGust", getWindGust(clientID)));
        deviceState.add(new StateValue("WindSpeed", getWindSpeed(clientID)));
        return deviceState;
    }

    @Override
    public double getAveragePeriod(int clientID) {
        return 300;
    }

    @Override
    public void setAveragePeriod(int clientID, double averagePeriod) {
        log.info("Setting average interval to {}", averagePeriod);
    }

    @Override
    public double getCloudCover(int clientID) {
        return cloudCoverage;
    }

    @Override
    public double getDewPoint(int clientID) {
        return dewPoint;
    }

    @Override
    public double getHumidity(int clientID) {
        return humidity;
    }

    @Override
    public double getPressure(int clientID) {
        return pressure;
    }

    @Override
    public double getRainRate(int clientID) {
        return rainRate;
    }

    @Override
    public double getSkyBrightness(int clientID) {
        return skyBrightness;
    }

    @Override
    public double getSkyQuality(int clientID) {
        return skyQuality;
    }

    @Override
    public double getSkyTemperature(int clientID) {
        return skyTemperature;
    }

    @Override
    public double getStarFWHM(int clientID) {
        return starFWHM;
    }

    @Override
    public double getTemperature(int clientID) {
        return temperature;
    }

    @Override
    public double getWindDirection(int clientID) {
        return windDirection;
    }

    @Override
    public double getWindGust(int clientID) {
        return windGust;
    }

    @Override
    public double getWindSpeed(int clientID) {
        return windSpeed;
    }

    @Override
    public void refresh(int clientID) {
        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public String getSensorDescription(int clientID, String sensorName) {
        if (sensorName == null || sensorName.isEmpty()) {
            throw new InvalidValueException("A sensor name was not supplied");
        }
        if (supportedMethods.stream().noneMatch(n -> n.equalsIgnoreCase(sensorName))) {
            throw new PropertyNotImplementedException("Property " + sensorName + " is not implemented");
        }

        return "Meteobridge " + sensorName + " sensor";
    }

    @Override
    public double getTimeSinceLastUpdate(int clientID, String sensorName) {
        long curTime = System.currentTimeMillis();
        long duration = (curTime - lastUpdate)/1000;
        if (duration > 300) {
            lastUpdate = curTime;
        }
        return duration;
    }
}
