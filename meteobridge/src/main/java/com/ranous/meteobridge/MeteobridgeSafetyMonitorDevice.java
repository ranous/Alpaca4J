package com.ranous.meteobridge;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.SafetyMonitorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("SpellCheckingInspection")
@ApplicationScoped
public class MeteobridgeSafetyMonitorDevice extends BaseDevice implements SafetyMonitorDevice {
    private static final Logger log = LoggerFactory.getLogger(MeteobridgeSafetyMonitorDevice.class);

    @Inject
    WeatherDB weatherDB;

    @Inject @ConfigProperty(name="meteobridge.max_safe_humidity", defaultValue = "0")
    double maxHumidityAllowed;
    @Inject @ConfigProperty(name="meteobridge.max_safe_windspeed", defaultValue = "0")
    double maxWindspeedAllowed;
    @Inject @ConfigProperty(name="meteobridge.max_safe_rainrate", defaultValue = "0")
    double maxRainrateAllowed;


    public MeteobridgeSafetyMonitorDevice() {
        super(DeviceType.SafetyMonitor, "Meteobridge Safety Monitor Driver", 3);
        setDescription("Test Safety Monitor Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    @Override
    public boolean isSafe(int clientID) {
        double humidity = weatherDB.getHumidity();
        if (maxHumidityAllowed > 0 && humidity > maxHumidityAllowed) {
            log.warn("Humidity of {} has exceeded max limit of {} - Signalling unsafe conditions", humidity, maxHumidityAllowed);
            return false;
        }

        double windspeed = weatherDB.getWindGust();
        if (maxWindspeedAllowed > 0 && windspeed > maxWindspeedAllowed) {
            log.warn("Windspeed of {} has exceeded max limit of {} - Signalling unsafe conditions", windspeed, maxWindspeedAllowed);
            return false;
        }

        double rainRate = weatherDB.getRainRate();
        if (maxRainrateAllowed > 0 && rainRate > maxRainrateAllowed) {
            log.warn("Rainrate of {} has exceeded max limit of {} - Signalling unsafe conditions", rainRate, maxRainrateAllowed);
            return false;
        }

        return true;
    }
}
