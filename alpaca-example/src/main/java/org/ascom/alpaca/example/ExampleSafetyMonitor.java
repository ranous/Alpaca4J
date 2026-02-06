package org.ascom.alpaca.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import org.ascom.alpaca.config.ConfigManager;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.SafetyMonitorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A safety monitor device that uses weather observations to determine if it is safe to observe the sky.
 * It can use the wind speed and rain rate measurements to determine if it is safe to observe the sky.
 * This device uses an ObservingConditionsDB as the source of weather measurements.  A separate polling
 * thread is used to get new measurements and update the ObservingConditionsDB.  In this way,
 * the device can use weather measurements that different sources to determine if it is safe to observe the sky.
 */
@ApplicationScoped
public class ExampleSafetyMonitor extends BaseDevice implements SafetyMonitorDevice {
    private static final Logger log = LoggerFactory.getLogger(ExampleSafetyMonitor.class);
    private ExampleSafetyMonitorConfig config;

    @Inject
    ObservingConditionsDB observingConditionsDB;
    @Inject
    ConfigManager configManager;
    @Inject
    QutePageRenderer setupPageRenderer;

    public ExampleSafetyMonitor(@ConfigProperty(name="boltwood.driver.version", defaultValue = "1.0") String deviceVersion) {
        super(DeviceType.SafetyMonitor, "Boltwood Safety Monitor", SafetyMonitorDevice.interfaceVersion, deviceVersion);
        setEnforceConnection(false); // We don't need a connection to read a file
    }

    void onStart(@Observes Startup ev) {
        log.info("Boltwood Observing Conditions Device Initialized");
        // Register the PageRenderer for the setup page
        this.setPageRenderer(setupPageRenderer);
        // Register the configuration class for the setup page so it can be looked up by the page renderer
        this.setPageRendererConfig(ExampleSafetyMonitorConfig.class);
    }

    @Override
    public boolean isSafe() {
        // ConfigurationManager.getConfig will instantiate the config if it doesn't exist
        // This configuration can be updated at runtime via the Alpca setup mechanism.
        ExampleSafetyMonitorConfig config = configManager.getConfig(ExampleSafetyMonitorConfig.class);

        // If the max safe wind speed is configured, consider it for safety
        if (config.maxSafeWindSpeed > 0) {
            Measurement windSpeed = observingConditionsDB.getMeasurement(MeasurementType.WindSpeed);
            if (windSpeed == null || windSpeed.value() > config.maxSafeWindSpeed) {
                return false;
            }
        }

        Measurement rainRate = observingConditionsDB.getMeasurement(MeasurementType.RainRate);
        return rainRate != null && rainRate.value() == 0;
    }
}
