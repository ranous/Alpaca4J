package org.ascom.alpaca.test;

import jakarta.enterprise.context.ApplicationScoped;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.SafetyMonitorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.ActionNotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TestSafetyMonitorDevice extends BaseDevice implements SafetyMonitorDevice {
    private static final Logger log = LoggerFactory.getLogger(TestSafetyMonitorDevice.class);

    public TestSafetyMonitorDevice() {
        super(DeviceType.SafetyMonitor, "Test Safety Monitor Driver", 3);
        setDescription("Test Safety Monitor Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
        super.addSupportedAction("testAction");
    }

    @Override
    public boolean isSafe(int clientID) {
        return true;
    }
    @Override
    public String executeAction(int clientID, String action, String parameters) {
        return switch (action) {
            case "testAction" -> testAction(clientID, parameters);
            default -> throw new ActionNotImplementedException("Unknown action: " + action);
        };
    }

    public String testAction(int clientID, String parameters) {
        log.info("Executing testAction from ClientID={} - {}", clientID, parameters);
        return "Hi there " + parameters;
    }
}
