package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.SafetyMonitor;
import org.ascom.alpaca.device.SafetyMonitorDevice;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.model.DeviceType;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SafetyMonitorResource implements SafetyMonitor {
    @Inject
    DeviceManager deviceManager;

    private SafetyMonitorDevice getDevice(int deviceID, int clientID) {
        SafetyMonitorDevice device = deviceManager.getDevice(deviceID, DeviceType.SafetyMonitor);
        device.checkConnectionStatus(clientID);
        return device;
    }

    public BooleanResponse isSafe(int deviceNumber, int clientID, long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isSafe(clientID));
    }
}
