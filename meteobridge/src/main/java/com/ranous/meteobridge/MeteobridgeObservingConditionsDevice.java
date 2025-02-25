package com.ranous.meteobridge;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
@ApplicationScoped
public class MeteobridgeObservingConditionsDevice extends BaseDevice implements ObservingConditionsDevice {
    private static final Logger log = LoggerFactory.getLogger(MeteobridgeObservingConditionsDevice.class);
    private final String VERSION = "1.0";

    @Inject
    WeatherDB weatherDB;

    private final List<String> supportedMethods = List.of("averageperiod", "dewpoint", "humidity", "pressure", "rainrate",
            "temperature", "winddirection", "windgust", "windspeed");

    public MeteobridgeObservingConditionsDevice() {
        super(DeviceType.ObservingConditions, "Meteobridge Observing Conditions", 2);
        setDescription("Meteobridge Observing Conditions Device");
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
        deviceState.add(new StateValue("TimeStamp", Instant.ofEpochMilli(weatherDB.getLastUpdated()).toString()));
        return deviceState;
    }

    @Override
    public double getAveragePeriod(int clientID) {
        return weatherDB.getAverageInterval();
    }

    @Override
    public void setAveragePeriod(int clientID, double averagePeriod) {
        log.info("Setting average interval to {}", averagePeriod);
        weatherDB.setAverageInterval(averagePeriod);
    }

    @Override
    public double getCloudCover(int clientID) {
        throw new PropertyNotImplementedException("The cloudcover method is not implemented");
    }

    @Override
    public double getDewPoint(int clientID) {
        return weatherDB.getDewPoint();
    }

    @Override
    public double getHumidity(int clientID) {
        return weatherDB.getHumidity();
    }

    @Override
    public double getPressure(int clientID) {
        return weatherDB.getPressure();
    }

    @Override
    public double getRainRate(int clientID) {
        return weatherDB.getRainRate();
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
        return weatherDB.getTemperature();
    }

    @Override
    public double getWindDirection(int clientID) {
        if (getWindSpeed(clientID) == 0) {
            return 0;
        }
        return weatherDB.getWindDirection();
    }

    @Override
    public double getWindGust(int clientID) {
        return weatherDB.getWindGust();
    }

    @Override
    public double getWindSpeed(int clientID) {
        return weatherDB.getWindSpeed();
    }

    @Override
    public void refresh(int clientID) {
        weatherDB.refresh();
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
        if (sensorName != null && !sensorName.isEmpty() && supportedMethods.stream().noneMatch(n -> n.equalsIgnoreCase(sensorName))) {
            throw new PropertyNotImplementedException("Property " + sensorName + " is not implemented");
        }

        // We get all the attributes updated at once so the sensor doesn't matter.
        return weatherDB.getDurationSinceLastUpdated();
    }
}
