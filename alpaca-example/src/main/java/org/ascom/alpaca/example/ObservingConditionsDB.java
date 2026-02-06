package org.ascom.alpaca.example;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.ascom.alpaca.config.ConfigManager;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.PropertyNotImplementedException;
import org.ascom.alpaca.response.ValueNotSetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The ObservingConditionsDB class is responsible for storing and managing the weather measurements that
 * an ObservingConditions or SafetyMonitor device can use. Some other object is responsible for periodically
 * polling the measurements file and adding the measurements to the ObservingConditionsDB.
 */
@SuppressWarnings("unused")
@Singleton
public class ObservingConditionsDB {
    private static final Logger log = LoggerFactory.getLogger(ObservingConditionsDB.class);
    private final Map<MeasurementType, LinkedList<Measurement>> measurements = new ConcurrentHashMap<>();
    private final Set<MeasurementType> supportedMethods = new TreeSet<>();
    private long lastUpdateTime = 0;
    private long averageInterval = 10000;

    @Inject
    ConfigManager configManager;

    ObservingConditionsDB() {
    }

    /**
     * Register a list of supported measurement types with the ObservingConditionsDB.  This can be used
     * by an ObservingConditions device to determine which measurements are available to the device.  This
     * can be called by different measurement collection mechanisms to merge results from different sources.
     * @param supportedMethods The list of supported measurement types
     */
    public void registerSupportedMeasurements(List<MeasurementType> supportedMethods) {
        this.supportedMethods.addAll(supportedMethods);
    }

    /**
     * Get the set of supported measurement types.  This can be used by an ObservingConditions device to
     * determine which measurements are available to the device.
     *
     * @return The set of supported measurement types
     */
    public Set<MeasurementType> getSupportedMeasurements() {
        return supportedMethods;
    }

    /**
     * Is the given measurement type supported by this ObservingConditionsDB?
     * @param type the device type to check
     * @return
     */
    public boolean isSupportedMeasurement(MeasurementType type) {
        return supportedMethods.contains(type);
    }

    /**
     * Is the given measurement type supported by this ObservingConditionsDB?
     * @param typeName the name of device type to check
     * @return
     */
    public boolean isSupportedMeasurement(String typeName) {
        MeasurementType type = MeasurementType.fromString(typeName);
        return type != null && isSupportedMeasurement(type);
    }

    public synchronized Measurement getMeasurement(MeasurementType type) {
        if (!supportedMethods.contains(type)) {
            throw new PropertyNotImplementedException("No support for measurement " + type);
        }
        List<Measurement> measurement = measurements.get(type);
        if (measurement == null) {
            return null;
        }
        return measurement.get(measurement.size() - 1);
    }

    /**
     * Get all measurements of the given type. More than one measurement may be returned if there
     * is an average interval set.
     * @param type The type of measurement to return
     * @return A list of measurements of the given type, or null if there are no measurements of that type.
     */
    public synchronized List<Measurement> getMeasurements(MeasurementType type) {
        return measurements.get(type);
    }

    /**
     * Get the value of the most recently named measurement.  If there are no measurements with the given name, return 0.
     * If there is an average interval set, then the average value of any measurements received in that interval is returned.
     * @param type The type of the measurement
     * @return The value of the measurement
     */
    public synchronized double getMeasurementValue(MeasurementType type) {
        if (!supportedMethods.contains(type)) {
            throw new PropertyNotImplementedException("No support for measurement " + type);
        }
        List<Measurement> measurements = getMeasurements(type);
        if (measurements != null) {
            double result = 0;
            for (Measurement measurement: measurements) {
                result += measurement.value();
            }
            return result / measurements.size();
        } else {
            throw new ValueNotSetException("No measurements available for sensor " + type.name());
        }
    }

    /**
     * Add a measurement to the database.  If there is an average interval set, then the measurement is maintained
     * for the average interval so that the average value can be calculated over multiple measurements.  Any existing
     * measurements older than the average interval are removed.  The timestamp for the measurement is set to the current time.
     * @param type The type of measurement
     * @param value The value of the measurement
     */
    public void addMeasurement(MeasurementType type, double value) {
        addMeasurement(type, value, System.currentTimeMillis());
    }


    /**
     * Add a measurement to the database.  If there is an average interval set, then the measurement is maintained
     * for the average interval so that the average value can be calculated over multiple measurements.  Any existing
     * measurements older than the average interval are removed.
     * @param type The type of measurement
     * @param value The value of the measurement
     * @param timestamp The timestamp of the measurement
     */
    public synchronized void addMeasurement(MeasurementType type, double value, long timestamp) {
        List<Measurement> list = measurements.computeIfAbsent(type, k -> new LinkedList<>());
        list.add(new Measurement(type, value, timestamp));
        // The average interval is specified as hours
        long expireTime = timestamp - (long) (getAverageInterval()*3600000);
        int size = list.size();
        if (list.removeIf(m -> (m.timestamp() < expireTime))) {
            log.trace("Removed {} measurements for {} due to expiration", size - list.size(), type);
        }
        lastUpdateTime = System.currentTimeMillis();
    }

    /**
     * The elapsed time in seconds since the last data update of any measurement in seconds.
     */
    public long getDurationSinceLastUpdated() {
        return (System.currentTimeMillis() - lastUpdateTime) / 1000;
    }

    /**
     * The timestamp of the last data update.
     * @return the timestamp of the last data update in milliseconds since the epoch.
     */
    public long getLastUpdated() {
        return lastUpdateTime;
    }

    /**
     * The average interval in hours.
     * @return
     */
    public double getAverageInterval() {
        ExampleSafetyMonitorConfig config = configManager.getConfig(ExampleSafetyMonitorConfig.class);
        return averageInterval / 3600.0;
    }

    /**
     * Set the average interval in hours.  If the interval is less than zero, an InvalidValueException is thrown.
     * @param interval
     */
    public void setAverageInterval(double interval) {
        if (interval < 0.0) {
            throw new InvalidValueException("The interval of " + interval + " is invalid");
        }
        ExampleSafetyMonitorConfig config = configManager.getConfig(ExampleSafetyMonitorConfig.class);
        averageInterval = (long)(interval*3600);
    }
}
