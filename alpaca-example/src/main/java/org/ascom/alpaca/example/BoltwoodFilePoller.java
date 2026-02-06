package org.ascom.alpaca.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Shutdown;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * The BoltwoodFilePoller class is responsible for periodically polling the measurements file from a Boltwood device or
 * other device that produces the same format file.  The measurements are then added to the ObservingConditionsDB to
 * make it available to the ObservingConditions and SafetyMonitor devices.
 */
@ApplicationScoped
public class BoltwoodFilePoller {
    private static final Logger log = LoggerFactory.getLogger(BoltwoodFilePoller.class);
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final ScheduledExecutorService scheduler = java.util.concurrent.Executors.newScheduledThreadPool(2);
    private ScheduledFuture<?> pollingTask;

    @Inject @ConfigProperty(name="boltwood.file.polling_interval", defaultValue = "60")
    long boltwoodPollingInterval;
    @Inject @ConfigProperty(name="boltwood.file.path")
    String boltwoodFile;

    @Inject
    ObservingConditionsDB observingConditionsDB;

    BoltwoodFilePoller() {

    }

    void onStart(@Observes Startup ev) {
        log.info("Initializing boltwood polling");

        // Register the supported measurements
        observingConditionsDB.registerSupportedMeasurements(List.of(MeasurementType.DewPoint, MeasurementType.Humidity,
                MeasurementType.RainRate, MeasurementType.Temperature, MeasurementType.SkyTemperature, MeasurementType.WindSpeed));

        if (pollingTask != null) {
            log.warn("boltwood poller already running");
            return;
        }

        log.info("Starting boltwood poller");
        schedulePollingJob(boltwoodPollingInterval);
        log.info("Finished initializing boltwood polling");
    }

    void onStop(@Observes Shutdown ev) {
        log.info("Shutting down the polling thread...");
        pollingTask.cancel(true);
        scheduler.shutdown();
    }

    void schedulePollingJob(long delay) {
        boltwoodPollingInterval = delay;
        pollingTask = scheduler.scheduleAtFixedRate(this::poll, 0, delay, java.util.concurrent.TimeUnit.SECONDS);
    }

    void reschedulePollingJob(long delay) {
        pollingTask.cancel(true);
        schedulePollingJob(delay);
    }

    public void poll() {
        try {
            log.debug("Polling boltwood to get weather data");

            File boltwoodFile = new File(this.boltwoodFile);
            if (!boltwoodFile.exists()) {
                log.warn("boltwood file does not exist: {}", boltwoodFile.getAbsolutePath());
                return;
            }

            List<String> lines = Files.readAllLines(boltwoodFile.toPath());
            if (lines.isEmpty()) {
                throw new IllegalArgumentException("Boltwood file is empty");
            }

            String line = lines.get(0).trim();
            String[] tokens = line.split("\\s+");

            // Boltwood II files typically have 20–21 tokens
            if (tokens.length < 20) {
                throw new IllegalArgumentException(
                        "Invalid Boltwood line, expected >= 20 fields, got " + tokens.length);
            }

            /*
             * Boltwood Field positions (0-based):
             * 0  Date
             * 1  Time (may include fractional seconds)
             * 2  Temp scale (C/F)
             * 3  Wind scale
             * 4  Sky temp
             * 5  Ambient temp
             * 6  Sensor temp
             * 7  Wind speed
             * 8  Humidity
             * 9  Dew point
             * 10 Heater %
             * 11 Rain flag
             * 12 Wet flag
             * ...
             */

            long timestamp = parseTimestamp(tokens[0], tokens[1]);
            double skytemp = Double.parseDouble(tokens[4]);
            double temp = Double.parseDouble(tokens[5]);
            double humidity = Double.parseDouble(tokens[8]);
            double dewpoint = Double.parseDouble(tokens[9]);
            double windspeed = Double.parseDouble(tokens[7]);
            boolean raining = Integer.parseInt(tokens[11]) != 0 || Integer.parseInt(tokens[12]) != 0;
            double rainrate = raining ? 1.0 : 0.0;

            observingConditionsDB.addMeasurement(MeasurementType.SkyTemperature, skytemp, timestamp);
            observingConditionsDB.addMeasurement(MeasurementType.Temperature, temp, timestamp);
            observingConditionsDB.addMeasurement(MeasurementType.Humidity, humidity, timestamp);
            observingConditionsDB.addMeasurement(MeasurementType.DewPoint, dewpoint, timestamp);
            observingConditionsDB.addMeasurement(MeasurementType.WindSpeed, windspeed, timestamp);
            observingConditionsDB.addMeasurement(MeasurementType.RainRate, rainrate, timestamp);
        } catch (Exception e) {
            log.warn("Got an exception polling boltwood: {}", e.getMessage(), e);
        }
    }

    private static long parseTimestamp(String date, String time) {
        // Strip fractional seconds if present
        String cleanTime = time.contains(".") ? time.substring(0, time.indexOf('.')) : time;
        LocalDateTime ldt = LocalDateTime.parse(date + " " + cleanTime, DATE_TIME);

        return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
