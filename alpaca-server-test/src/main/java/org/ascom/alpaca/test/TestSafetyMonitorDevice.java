package org.ascom.alpaca.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.SafetyMonitorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.InvalidValueException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TestSafetyMonitorDevice extends BaseDevice implements SafetyMonitorDevice {
    private static final Logger log = LoggerFactory.getLogger(TestSafetyMonitorDevice.class);
    private boolean isSafe = true;

    public TestSafetyMonitorDevice() {
        super(DeviceType.SafetyMonitor, "Test Safety Monitor Driver", 3);
        setDescription("Test Safety Monitor Device");
        setDriverInfo(getDescription());
        super.addSupportedAction("setSafety", (parameters) -> {
            isSafe = Boolean.parseBoolean(parameters);
            return "Safety set to " + isSafe;
        });
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
        return isSafe;
    }

    //@Override
    public String executeActions(int clientID, String actionName, String actionParameters) {
        if (actionName.equals("setSafety")) {
            log.info("Executing setSafety action with parameters: {}", actionParameters);
            if (actionParameters.equals("true")) {
                isSafe = true;
            } else if (actionParameters.equals("false")) {
                isSafe = false;
            } else {
                throw new InvalidValueException("Invalid parameter value for setSafety action: " + actionParameters);
            }
            return "Test action executed successfully";
        }
        return super.executeAction(clientID, actionName, actionParameters);
    }

}
