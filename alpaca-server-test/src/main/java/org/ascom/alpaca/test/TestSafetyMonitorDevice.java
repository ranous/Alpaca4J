package org.ascom.alpaca.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.SafetyMonitorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TestSafetyMonitorDevice extends BaseDevice implements SafetyMonitorDevice {
    private static final Logger log = LoggerFactory.getLogger(TestSafetyMonitorDevice.class);

    public TestSafetyMonitorDevice() {
        super(DeviceType.SafetyMonitor, "Test Safety Monitor Driver", 3);
        setDescription("Test Safety Monitor Device");
        setDriverInfo(getDescription());
        super.addSupportedAction("testAction");
    }

    // The version of the driver is injected from the microprofile-config.properties file and can be overridden
    // by the system property test.driver.version
    @Inject
    public TestSafetyMonitorDevice(@ConfigProperty(name="test.driver.version", defaultValue = "1.0") String deviceVersion) {
        this();
        setDriverVersion(deviceVersion);
    }

    @Override
    public boolean isSafe(int clientID) {
        return true;
    }

}
