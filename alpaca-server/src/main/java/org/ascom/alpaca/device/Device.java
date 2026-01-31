package org.ascom.alpaca.device;

import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;

import java.util.List;
import java.util.Map;

/**
 * Interface for operations that are common to all Alpaca devices.
 * The full documentation of the device interface can be found in the Alpaca documentation
 * @see <a href="https://ascom-standards.org/api/#/ASCOM%20Methods%20Common%20To%20All%20Devices">ASCOM Alpaca Secification</a>.
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

    // The following methods are the operations exported to calling clients.  The @{link BaseDevice}
    // provides an implementation of these methods that should be used by device subclasses.
    boolean isConnecting(int clientID);
    boolean isConnected(int clientID);
    void connect(int clientID);
    void disconnect(int clientID);
    void setConnectedState(int clientID, boolean state);
    String getDescription();
    List<StateValue> getDeviceState();
    String getDriverInfo();
    String getDriverVersion();
    int getInterfaceVersion();
    String getName();
    List<String> getSupportedActions();
    String executeAction(String action, String parameters);
    String setup();
    void update(Map<String, String> updates);
}
