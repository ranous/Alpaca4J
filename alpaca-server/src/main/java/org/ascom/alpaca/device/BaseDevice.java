package org.ascom.alpaca.device;

import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import org.ascom.alpaca.config.ConfigManager;
import org.ascom.alpaca.config.DefaultSetupPageRenderer;
import org.ascom.alpaca.config.SetupPageRenderer;
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
 * new devices should subclass this to avoid all the boilerplate connection management. In general,
 * it shouldn't be necessary to override any of the methods in this class. If your driver needs
 * to manage connections due to requirements of the resources it's managing, then you will need
 * to manage that in your device and override the connect/disconnect methods.
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
    private SetupPageRenderer pageRenderer = new DefaultSetupPageRenderer();
    private Class<?> pageRendererConfigClass = null;

    @Inject
    ConfigManager configManager;

    protected BaseDevice() {}

    /**
     * Constructs a new BaseDevice instance with the provided device type, device name, and interface version number.
     * @param deviceType the type of the device, represented by an instance of {@link DeviceType}
     * @param deviceName the name of the device
     * @param interfaceVersion the interface version number of the device
     */
    public BaseDevice(DeviceType deviceType, String deviceName, int interfaceVersion) {
        this.deviceType = deviceType;
        this.name = deviceName;
        // Note that we use an initial value of 0 for the device number.  If there are more than one
        // of this device type in the Alpaca server, the DeviceManger will assign unique device numbers during startup.
        this.deviceDescriptor = new DeviceDescriptor(deviceName, deviceType, 0);
        this.interfaceVersion = interfaceVersion;
    }

    /**
     * Constructs a new BaseDevice instance with the provided device type, device name, and interface version number.
     * @param deviceType the type of the device, represented by an instance of {@link DeviceType}
     * @param deviceName the name of the device
     * @param interfaceVersion the interface version number of the device
     * @param driverVersion the driver version number of the device.  This is optional and defaults to "1.0" if not provided.
     */
    public BaseDevice(DeviceType deviceType, String deviceName, int interfaceVersion, String driverVersion) {
        this(deviceType, deviceName, interfaceVersion);
        this.driverVersion = driverVersion;
    }

    protected void setPageRendererConfig(Class<?> config) {
        this.pageRendererConfigClass = config;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Returns the device ID. Device ids are assigned by the DeviceManager.
     * @return the device ID
     */
    public int getDeviceID() {
        return deviceID;
    }

    /**
     * Sets the device ID. Device ids are assigned by the DeviceManager and should not be set directly.
     * @param deviceID the device ID
     */
    public void setDeviceID(int deviceID) {
        deviceDescriptor.setDeviceNumber(deviceID);
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

    protected void setDriverInfo(String driverInfo) {
        this.driverInfo = driverInfo;
    }

    protected void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setInterfaceVersion(int interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    /**
     * Adds a supported action to the device.  The action name must be unique and the function
     * must be non-null.  The function to be called takes the action parameters as input and
     * returns a string result.
     * @param actionName the name of the action to add
     * @param function the function to execute when the action is invoked
     */
    protected void addSupportedAction(String actionName, Function<String,String> function) {
        this.supportedActions.put(actionName, function);
    }

    protected void checkFormParameters(int clientID) {
        if (clientID < 0) {
            throw new BadRequestException("ClientID must be greater than 0");
        }
    }

    /**
     * Returns whether the device enforces connection status for client operations.  If true, any operation
     * that requires a connection will throw a NotConnectedException if the client is not connected, otherwise
     * the connection state is ignored.  This can be useful during testing.
     * @return whether connection enforcement is enabled
     */
    protected boolean isEnforceConnection() {
        return enforceConnection;
    }

    /**
     * Sets whether the device enforces connection status for client operations.  If true, any operation
     * that requires a connection will throw a NotConnectedException if the client is not connected, otherwise
     * the connection state is ignored.  This can be useful during testing.
     * @param enforceConnection whether connection enforcement should be done
     */
    protected void setEnforceConnection(boolean enforceConnection) {
        this.enforceConnection = enforceConnection;
    }

    /**
     * Returns the page renderer for the device which renders an HTML page for the client to
     * edit device configuration attributes via the setup() method. The default renderer provided
     * simply prints a message indicating that no configuration is available.
     * @return the page renderer for the device.
     */
    protected SetupPageRenderer getPageRenderer() {
        return pageRenderer;
    }

    /**
     * Sets the page renderer for the device.  The page renderer renders an HTML page for the client to
     * edit device configuration attributes via the setup() method. If a device doesn't have any
     * client-updatable configuration, then the default render is used which prints a message indicating
     * that no configuration is available. If the device has state that is client-updatable, then the
     * device subclass should implement a custom renderer that implements the
     * {@link org.ascom.alpaca.config.SetupPageRenderer} interface can supply it to this method.
     * @param pageRenderer the page renderer to use for device configuration
     */
    protected void setPageRenderer(SetupPageRenderer pageRenderer) {
        this.pageRenderer = pageRenderer;
    }


    // The following methods implement the operations common to all Alpaca devices.  Each method
    // corresponds to the Alpca operartions called by clients.


    /**
     * Checks if the client is connected to this device.  If not, a NotConnectedException is thrown.
     * This check can be disabled by setting enforceConnection to false.  This can be useful during testing,
     * so clients can perform operations without being connected.  Also, your device may not need to
     * enforce connection as the device as there are no resources associated with the connection.
     * @param clientID the id of the calling client
     */
    public void checkConnectionStatus(int clientID) {
        Boolean connected = clientConnectedStates.get(clientID);
        if (isEnforceConnection() && (connected == null || !connected)) {
            throw new NotConnectedException(clientID + " is not connected to device " + deviceType + " deviceID=" + deviceID);
        }
    }

    /**
     * Connects the client to this device.  If the device is already connected, this operation does nothing.
     * This base class does not implement asynchronous connections. This method always puts the client into
     * a connected state and doesn't use the intermediate connecting state.  Connection management in this
     * base class assumes the implementing Alpaca device doesn't have anything it needs to do on its own, such as
     * connection to some hardware.  If the device has more complicated connection management requirements,
     * it should override the various connection management methods and manage that on its own.
     * @param clientID the id of the calling client
     */
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

    /**
     * Disconnects the client from this device.
     * @param clientID the id of the calling client
     */
    @Override
    public void disconnect(int clientID) {
        log.info("Disconnecting from device {}", deviceID);
        clientConnectedStates.remove(clientID);
    }

    /**
     * Returns whether the client is currently connecting to this device. The default behavior
     * this base class provides is that it doesn't support the Alpaca v7 notions of aynchrounous
     * connections that may take a while to complete, so this method always returns false.
     * @param clientID the id of the calling client
     * @return true if the connection is currently in progress, false otherwise.
     */
    @Override
    public boolean isConnecting(int clientID) {
        return connecting;
    }

    /**
     * Returns whether the client is currently connected to this device.
     * @param clientID the id of the calling client
     * @return true if the client is connected, false otherwise.
     */
    @Override
    public boolean isConnected(int clientID) {
        return clientConnectedStates.getOrDefault(clientID, Boolean.FALSE);
    }

    /**
     * Sets the connected state of the client. This was the way client's connected and disconnected
     * states were managed in Alpaca v6, and is provided for compatibility with older clients.  This
     * operation always completed the state transition before returning.  The aync connect/disconnect
     * operations in this base class implementation have the same behaviour.
     * @param clientID the id of the calling client
     * @param connectedState the new connected state of the client.
     */
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

    /**
     * Returns the description of the device.  This is a static property of the device and does not
     * change based on the client.  The description should be supplied by the implementing subclass device
     * by passing it in the constructor.
     * @return the description of the device.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the device state. The mechanism allows the device to return all the state values in a single
     * operation.  This implementation returns an empty list. The implementing device should override this and
     * provide the device's state.  The state values that should be returned are described in the Alpaca specification.
     * <a href="https://ascom-standards.org/api/#/ASCOM%20Methods%20Common%20To%20All%20Devices/get__device_type___device_number__devicestate">...</a>
     * @return a list of device state values.
     */
    @Override
    public List<StateValue> getDeviceState() {
        return emptyDeviceState;
    }

    /**
     * Returns the driver information.  This is a description of the device and can include the version.
     * If the driverInfo has not been set, it will be generated from the description and version.
     * @return the driver information.
     */
    @Override
    public String getDriverInfo() {
        if (driverInfo == null) {
            driverInfo = getDescription() + ". Version " + getDriverVersion();
        }
        return driverInfo;
    }

    /**
     * The major and minor version numbers of the driver.
     * @return the driver version string.
     */
    @Override
    public String getDriverVersion() {
        return driverVersion;
    }

    /**
     * The ASCOM version of this device's interface.  These versions are defined in the ASCOM specifiations.
     * This should be supplied by the device subclass in the constructor.
     * @return the interface version number.
     */
    @Override
    public int getInterfaceVersion() {
        return interfaceVersion;
    }

    /**
     * Returns the short name of the driver for display purposes.  This should be supplied by the device subclass
     * via the constructor.
     * @return the name of the device.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns a list of supported actions for this device.  Actions are operations that aren't part
     * of the Alpaca standard interface. If the device has additional functionality it wishes to
     * expose to a client, this operation lists all the non-standard actions that the device supports.
     * The client can invoke the Alpaa executeAction operation on the device, which in turn calls
     * {@link #executeAction(String, String)} to invoke the action.
     * @return a list of supported actions
     */
    @Override
    public List<String> getSupportedActions() {
        return supportedActions.keySet().stream().toList();
    }

    /**
     * Calls the method associated with the given action name, passing the parameters to it.  This method
     * needs to be registered using {@link #addSupportedAction(String, Function)} before it can be called.
     * Actions and SupportedActions are a standardised means for drivers to extend functionality beyond the
     * built-in capabilities of the ASCOM device interfaces.
     *
     * The key advantage of using Actions is that drivers can expose any device specific functionality required.
     * The downside is that, in order to use these unique features, every application author would need to
     * create bespoke code to present or exploit them.
     *
     * The Action parameter and return strings are deceptively simple, but can support transmission of arbitrarily
     * complex data structures, for example through JSON encoding.
     *
     * This capability will be of primary value to:
     * - bespoke software and hardware configurations where a single entity controls both the consuming application
     *   software and the hardware / driver environment
     * - a group of application and device authors who want to quickly formulate and try out new interface
     *   capabilities without requiring an immediate change to the ASCOM device interface, which will take a lot
     *   longer than just agreeing a name, input parameters and a standard response for an Action command
     *
     * The list of Action commands supported by a driver can be discovered through the SupportedActions property.
     * If the device has no device specific actions, it should throw a PropertyNotImplementedException.
     *
     * @param action the name of the action to execute
     * @param parameters the parameters to pass to the action function
     * @return the result of the action function
     * @throws PropertyNotImplementedException if the device doesn't support the requested action
     */
    @Override
    public String executeAction(String action, String parameters) {
        log.info("Executing action {} with parameters {}", action, parameters);
        return supportedActions.getOrDefault(action, (s) -> {throw new PropertyNotImplementedException("Action" + action + " not implemented");}).apply(parameters);
    }

    /**
     * Returns an HTML page that lets the client change configuration attributes supported by the device.
     * The generated HTML can then call PUT on the Alpaca endpoint '/setup/v1/{deviceType}/{deviceID}/setup'.
     * This resource will then call the device's {@link #update(Map)} method.
     * @return an HTML page that lets the client edit device configuration attributes.
     */
    @Override
    public String setup() {
        Object config = null;
        if (pageRendererConfigClass != null) {
            config = configManager.getConfig(pageRendererConfigClass);
        }
        return pageRenderer.renderSetupPage(name, deviceType, deviceID, config);
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
        configManager.updateConfig(updates);
    }
}
