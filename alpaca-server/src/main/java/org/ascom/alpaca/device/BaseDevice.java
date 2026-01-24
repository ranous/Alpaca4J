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
import java.util.function.Function;

/**
 * The BaseDevice implements the functionality common to all Alpaca devices.  Implementers of
 * new devices should subclass this. The BaseDevice implements client-based connection management.
 * If your device doesn't have any connection state to manage, then the device subclassing BaseDevice
 * can disable connection enforcement.
 *
 * If your driver needs to manage connections to what it is managing, then you'll to manage that
 * separately.
 */
@SuppressWarnings("unused")
public class BaseDevice implements Device {
    private static final Logger log = LoggerFactory.getLogger(BaseDevice.class);
    private int deviceID;
    private int interfaceVersion;
    private DeviceType deviceType;
    private String description;
    private String driverInfo;
    private String driverVersion = "1.0";
    private String name;
    private DeviceDescriptor deviceDescriptor;
    private boolean connecting = false;
    private boolean enforceConnection = true;
    private final Map<String, Function<String,String>> supportedActions = new HashMap<>();
    private final Map<Integer, Boolean> clientConnectedStates = new HashMap<>();
    private final List<StateValue> emptyDeviceState = new ArrayList<>();

    /**
     * Constructs a new BaseDevice instance with the provided device type, device name, and interface version number.
     * @param deviceType the type of the device, represented by an instance of {@link DeviceType}
     * @param deviceName the name of the device
     * @param interfaceVersion the interface version number of the device
     */
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

    /**
     * Returns the device ID. Device ids are assigned by the DeviceManager.
     * @return
     */
    public int getDeviceID() {
        return deviceID;
    }

    /**
     * Sets the device ID. Device ids are assigned by the DeviceManager and should not be set directly.
     * @param deviceID
     */
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

    /**
     * Adds a supported action to the device.  The action name must be unique and the function
     * must be non-null.  The function to be called takes the action parameters as input and
     * returns a string result.
     * @param actionName
     * @param function
     */
    public void addSupportedAction(String actionName, Function<String,String> function) {
        this.supportedActions.put(actionName, function);
    }

    protected void checkFormParameters(int clientID) {
        if (clientID < 0) {
            throw new BadRequestException("ClientID must be greater than 0");
        }
    }

    public boolean isEnforceConnection() {
        return enforceConnection;
    }

    /**
     * Sets whether the device enforces connection status for client operations.  If true, any operation
     * that requires a connection will throw a NotConnectedException if the client is not connected, otherwise
     * the connection state is ignored.  This can be useful during testing.
     * @param enforceConnection
     */
    public void setEnforceConnection(boolean enforceConnection) {
        this.enforceConnection = enforceConnection;
    }

    // The following methods implement the operations common to all Alpaca devices.  Each method
    // corresponds to the Alpca operartions called by clients.
    /**
     * Checks if the client is connected to this device.  If not, a NotConnectedException is thrown.
     *
     * @param clientID the id of the calling client
     */
    public void checkConnectionStatus(int clientID) {
        Boolean connected = clientConnectedStates.get(clientID);
        if (isEnforceConnection() && (connected == null || !connected)) {
            throw new NotConnectedException(clientID + " is not connected to device " + deviceType + " deviceID=" + deviceID);
        }
    }

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

    // Default device state. Subclasses should overload and return the device-specific properties
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
        return supportedActions.keySet().stream().toList();
    }

    /**
     * Calls the method associated with the given action name, passing the parameters to it.  This method
     * needs to be registered using {@link #addSupportedAction(String, Function)} before it can be called.
     */
    @Override
    public String executeAction(int clientID, String action, String parameters) {
        log.info("Executing action {} with parameters {}", action, parameters);
        return supportedActions.getOrDefault(action, (s) -> {throw new PropertyNotImplementedException("Action" + action + " not implemented");}).apply(parameters);
    }

    /**
     * Returns an HTML page that lets the client change configuration attributes supported by the device.
     * The generated HTML can then call PUT on the Alpaca endpoint '/setup/v1/{deviceType}/{deviceID}/setup'.
     * This resource will then call the device's {@link #update(Map)} method.
     * @return
     */
    @Override
    public String setup() {
        return "Device " + name  + " doesn't have any setup parameters";
    }

    /**
     * Updates the device's configuration using the provided key-value pairs.
     * This method is typically used to apply settings or modifications to the device's properties.
     *
     * @param updates a map containing keys and their corresponding values to update the device properties.
     *                The supported keys and their respective values are device-specific.
     */
    @Override
    public void update(Map<String, String> updates) {

    }
}
