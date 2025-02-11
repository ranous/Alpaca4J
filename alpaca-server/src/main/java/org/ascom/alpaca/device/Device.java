package org.ascom.alpaca.device;

import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;

import java.util.List;

/**
 * The operations common to all Alpaca drivers
 */
@SuppressWarnings("unused")
public interface Device {
    // The following methods are only called internally by implementing devices
    int getDeviceID();
    void setDeviceID(int deviceID);
    DeviceType getDeviceType();
    DeviceDescriptor getDeviceDescriptor();
    void setDeviceDescriptor(DeviceDescriptor deviceDescriptor);
    void checkConnectionStatus(int clientID);

    // The following methods are the operations exported to calling clients.
    boolean isConnecting(int clientID);
    boolean isConnected(int clientID);
    void connect(int clientID);
    void disconnect(int clientID);
    void setConnectedState(int clientID, boolean state);
    String getDescription(int clientID);
    List<StateValue> getDeviceState(int clientID);
    String getDriverInfo(int clientID);
    String getDriverVersion(int clientID);
    int getInterfaceVersion(int clientID);
    String getName(int clientID);
    List<String> getSupportedActions(int clientID);
    String executeAction(int clientID, String action, String parameters);
}
