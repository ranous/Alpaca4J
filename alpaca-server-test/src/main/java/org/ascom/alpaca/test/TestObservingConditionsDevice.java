package org.ascom.alpaca.test;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.ObservingConditionsDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.PropertyNotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
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


    private final List<String> supportedMethods = List.of("averageperiod", "dewpoint", "humidity", "pressure", "rainrate",
            "temperature", "winddirection", "windgust", "windspeed");

    public TestObservingConditionsDevice() {
        super(DeviceType.ObservingConditions, "Test Observing Conditions", 2);
        setDescription("Test Observing Conditions Device");
        setDriverInfo(getDescription() + ". Version " + VERSION);
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
        throw new PropertyNotImplementedException("The cloudcover method is not implemented");
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
        throw new PropertyNotImplementedException("The skybrightness method is not implemented");
    }

    @Override
    public double getSkyQuality(int clientID) {
        throw new PropertyNotImplementedException("The skyquality method is not implemented");
    }

    @Override
    public double getSkyTemperature(int clientID) {
        throw new PropertyNotImplementedException("The skytemperature method is not implemented");
    }

    @Override
    public double getStarFWHM(int clientID) {
        throw new PropertyNotImplementedException("The starfwhm method is not implemented");
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
        throw new PropertyNotImplementedException("The time since last update was not supplied");
    }
}
