package org.ascom.alpaca.test;

import jakarta.enterprise.context.ApplicationScoped;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.SafetyMonitorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TestSafetyMonitorDevice extends BaseDevice implements SafetyMonitorDevice {
    private static final Logger log = LoggerFactory.getLogger(TestSafetyMonitorDevice.class);
    private boolean isSafe = true;

    // The version of the driver is injected from the microprofile-config.properties file and can be overridden
    // by the system property test.driver.version
    public TestSafetyMonitorDevice(@ConfigProperty(name="test.driver.version", defaultValue = "1.0") String deviceVersion) {
        super(DeviceType.SafetyMonitor, "Test Safety Monitor Driver", SafetyMonitorDevice.interfaceVersion, deviceVersion);
        setDescription("Test Safety Monitor Device");
        setEnforceConnection(false);  // Don't need to enforce connection for this device
        // Add a custom action that lets the client explicitly set the safety state
        super.addSupportedAction("setSafety", (parameters) -> {
            isSafe = Boolean.parseBoolean(parameters);
            return "Safety set to " + isSafe;
        });
    }

    @Override
    public boolean isSafe() {
        return isSafe;
    }
}
