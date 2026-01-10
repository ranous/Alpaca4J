package org.ascom.alpaca.device;

import jakarta.ws.rs.BadRequestException;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.NotConnectedException;
import org.ascom.alpaca.response.PropertyNotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The BaseDevice implements the functionality common to all Alpaca devices.  Implementers of
 * new devices should subclass this. The BaseDevice implements client based connection management.
 * If your driver needs to manage connections to what it is managing, then you'll to manage that
 * separately.
 */
@SuppressWarnings("unused")
public class BaseDevice implements Device {
    private static final Logger log = LoggerFactory.getLogger(BaseDevice.class);
    private int deviceID;
    private DeviceType deviceType;
    private String description;
    private String driverInfo;
    private String driverVersion = "1.0";
    private int interfaceVersion = 1;
    private String name;
    private List<String> supportedActions = new ArrayList<>();
    private DeviceDescriptor deviceDescriptor;
    private boolean connecting = false;
    private final Map<Integer, Boolean> clientConnectedStates = new HashMap<>();
    private final List<StateValue> emptyDeviceState = new ArrayList<>();

    public BaseDevice(DeviceType deviceType, String deviceName) {
        this.deviceType = deviceType;
        this.name = deviceName;
        this.deviceDescriptor = new DeviceDescriptor(deviceName, deviceType, 0);
    }

    public BaseDevice(DeviceType deviceType, String deviceName, int interfaceVersion) {
        this.deviceType = deviceType;
        this.name = deviceName;
        this.deviceDescriptor = new DeviceDescriptor(deviceName, deviceType, 0);
        this.interfaceVersion = interfaceVersion;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        deviceDescriptor.setDeviceNumber(deviceID);
    }

    protected String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeviceDescriptor getDeviceDescriptor() {
        return deviceDescriptor;
    }

    public void setDeviceDescriptor(DeviceDescriptor deviceDescriptor) {
        this.deviceDescriptor = deviceDescriptor;
    }

    public void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }

    protected void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInterfaceVersion(int interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public void setSupportedActions(List<String> supportedActions) {
        this.supportedActions = supportedActions;
    }

    public void addSupportedAction(String supportAction) {
        this.supportedActions.add(supportAction);
    }

    protected void checkFormParameters(int clientID) {
        if (clientID < 0) {
            throw new BadRequestException("ClientID must be greater than 0");
        }
    }

    // The following methods implement the operations common to all Alpaca devices.
    /**
     * Checks if the client is connected to this device.  If not, a NotConnectedException is thrown.
     *
     * @param clientID the id of the calling client
     */
    public void checkConnectionStatus(int clientID) {
        Boolean connected = clientConnectedStates.get(clientID);
        if (connected == null || !connected) {
            throw new NotConnectedException(clientID + " is not connected to device " + deviceType + " deviceID=" + deviceID);
        }
    }


    // The following methods are called via Alpaca clients
    @Override
    public void connect(int clientID) {
        connecting = true;
        log.info("Connecting to device {}", deviceID);
        if (clientConnectedStates.getOrDefault(clientID, Boolean.FALSE)) {
            log.info("Client {} is already connected to device {}", clientID, deviceID);
            return;
        }
        clientConnectedStates.put(clientID, Boolean.TRUE);
        connecting = false;
    }

    @Override
    public void disconnect(int clientID) {
        log.info("Disconnecting from device {}", deviceID);
        clientConnectedStates.remove(clientID);
    }

    @Override
    public boolean isConnecting(int clientID) {
        return connecting;
    }

    @Override
    public boolean isConnected(int clientID) {
        return clientConnectedStates.getOrDefault(clientID, Boolean.FALSE);
    }

    @Override
    public void setConnectedState(int clientID, boolean connectedState) {
        checkFormParameters(clientID);
        if (connectedState) {
            clientConnectedStates.put(clientID, Boolean.TRUE);
        } else {
            clientConnectedStates.remove(clientID);
        }
        connecting = false;
    }

    @Override
    public String getDescription(int clientID) {
        return description;
    }

    // Default device state.  Subclasses should overload and return the device specific properties
    @Override
    public List<StateValue> getDeviceState(int clientID) {
        return emptyDeviceState;
    }

    @Override
    public String getDriverInfo(int clientID) {
        return driverInfo;
    }

    @Override
    public String getDriverVersion(int clientID) {
        return driverVersion;
    }

    @Override
    public int getInterfaceVersion(int clientID) {
        return interfaceVersion;
    }

    @Override
    public String getName(int clientID) {
        return name;
    }

    @Override
    public List<String> getSupportedActions(int clientID) {
        return supportedActions;
    }

    /**
     * If a given device implements any additional actions, they must override this method to dispatch the request to
     * the correct method.
     */
    @Override
    public String executeAction(int clientID, String action, String parameters) {
        throw new PropertyNotImplementedException("Action" + action + " not implemented");
    }

    @Override
    public String setup() {
        return "Device " + name  + " doesn't have any setup parameters";
    }
}
