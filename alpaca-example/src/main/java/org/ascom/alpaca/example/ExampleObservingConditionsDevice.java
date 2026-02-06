package org.ascom.alpaca.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.PropertyNotImplementedException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * This ASCOM Alpaca Observing Conditions Device uses an ObservingConditionsDB as the source of weather measurements.
 * A separate polling thread is used to get new measurements and update the ObservingConditionsDB.  In this way,
 * the device can provide weather measurements that can come from a variety of sources.
 */
@ApplicationScoped
public class ExampleObservingConditionsDevice extends BaseDevice implements org.ascom.alpaca.device.ObservingConditionsDevice {
    private static final Logger log = LoggerFactory.getLogger(ExampleObservingConditionsDevice.class);

    @Inject
    ObservingConditionsDB observingConditionsDB;

    @Inject
    public ExampleObservingConditionsDevice(@ConfigProperty(name = "Boltwood.driver.version", defaultValue = "1.0") String deviceVersion) {
        super(DeviceType.ObservingConditions, "Boltwood Observing Conditions", org.ascom.alpaca.device.ObservingConditionsDevice.interfaceVersion, deviceVersion);
        setDescription("Boltwood Observing Conditions Device");
        setEnforceConnection(false);
    }

    void onStart(@Observes Startup ev) {
        log.info("Boltwood Observing Conditions Device Initialized");
        // Register the PageRenderer for the setup page
    }

    @Override
    public List<StateValue> getDeviceState() {
        List<StateValue> deviceState = new ArrayList<>();
        for (MeasurementType type : observingConditionsDB.getSupportedMeasurements()) {
            Measurement measurement = observingConditionsDB.getMeasurement(type);
            if (measurement != null) {
                deviceState.add(new StateValue(type.name(), measurement.value()));
            }
        }
        deviceState.add(new StateValue("TimeStamp", Instant.ofEpochMilli(observingConditionsDB.getLastUpdated()).atZone(ZoneOffset.UTC).toString()));
        return deviceState;
    }

    @Override
    public double getAveragePeriod() {
        return observingConditionsDB.getAverageInterval();
    }

    @Override
    public void setAveragePeriod(double averagePeriod) {
        log.info("Setting average interval to {}", averagePeriod);
        observingConditionsDB.setAverageInterval(averagePeriod);
    }

    @Override
    public double getCloudCover() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.CloudCover);
    }

    @Override
    public double getDewPoint() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.DewPoint);
    }

    @Override
    public double getHumidity() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.Humidity);
    }

    @Override
    public double getPressure() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.Pressure);
    }

    @Override
    public double getRainRate() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.RainRate);
    }

    @Override
    public double getSkyBrightness() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.SkyBrightness);
    }

    @Override
    public double getSkyQuality() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.SkyQuality);
    }

    @Override
    public double getSkyTemperature() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.SkyTemperature);
    }

    @Override
    public double getStarFWHM() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.StarFWHM);
    }

    @Override
    public double getTemperature() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.Temperature);
    }

    @Override
    public double getWindDirection() {
        if (getWindSpeed() == 0) {
            return 0;
        }
        return observingConditionsDB.getMeasurementValue(MeasurementType.WindDirection);
    }

    @Override
    public double getWindGust() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.WindGust);
    }

    @Override
    public double getWindSpeed() {
        return observingConditionsDB.getMeasurementValue(MeasurementType.WindSpeed);
    }

    @Override
    public void refresh() {
        // Do nothing, the data is updated via polling automatically
    }

    @Override
    public String getSensorDescription(String sensorName) {
        if (sensorName == null || sensorName.isEmpty()) {
            throw new InvalidValueException("A sensor name was not supplied");
        }

        MeasurementType type = MeasurementType.fromString(sensorName);
        if (type == null || !observingConditionsDB.getSupportedMeasurements().contains(type)) {
            throw new PropertyNotImplementedException("Property " + sensorName + " is not implemented");
        }

        return "Boltwood " + type.name() + " Sensor";
    }

    @Override
    public double getTimeSinceLastUpdate(String sensorName) {
        if (sensorName == null || sensorName.isEmpty()) {
            return observingConditionsDB.getDurationSinceLastUpdated();
        }
        MeasurementType type = MeasurementType.fromString(sensorName);
        if (type == null || !observingConditionsDB.getSupportedMeasurements().contains(type)) {
            throw new PropertyNotImplementedException("Property " + sensorName + " is not implemented");
        }

        // We get all the attributes updated at once so the sensor doesn't matter.
        return observingConditionsDB.getDurationSinceLastUpdated();
    }
}
