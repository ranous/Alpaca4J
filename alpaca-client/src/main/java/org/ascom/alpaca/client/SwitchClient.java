package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Switch;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

/**
 * Client for interacting with ASCOM Switch devices via the ALPACA protocol.
 */
@SuppressWarnings("unused")
public class SwitchClient extends CommonClient {
    private static final Logger log = Logger.getLogger(SwitchClient.class);
    private final URI serverAddress;
    private Switch client = null;

    public SwitchClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public SwitchClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Switch getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Switch.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Switch - " + e.getMessage());
            }
        }
        return client;
    }

    /**
     * Indicates whether the specified switch device can be written to
     *
     * @return true if the device can be written to, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.CanWrite">A full description of this member's behavior is provided here</a>
     */
    public boolean canWrite() {
        BooleanResponse response = call(getClient().canWrite(getDeviceID(), getClientID(), getTransactionID()), "canWrite");
        return response.getValue();
    }

    /**
     * Indicates whether the specified switch device can be written to
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.CanWrite">A full description of this member's behavior is provided here</a>
     */
    public void canWrite(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canWrite(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canWrite");
    }

    /**
     * The number of switch devices managed by this driver
     *
     * @return The number of switch devices managed by this driver
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.MaxSwitch">A full description of this member's behavior is provided here</a>
     */
    public int getMaxSwitch() {
        IntResponse response = call(getClient().getMaxSwitch(getDeviceID(), getClientID(), getTransactionID()), "getMaxSwitch");
        return response.getValue();
    }

    /**
     * The number of switch devices managed by this driver
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.MaxSwitch">A full description of this member's behavior is provided here</a>
     */
    public void getMaxSwitch(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getMaxSwitch(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMaxSwitch");
    }

    /**
     * Return the state of the specified switch device as a boolean
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.GetSwitch">A full description of this member's behavior is provided here</a>
     */
    public boolean getSwitch(int id) {
        BooleanResponse response = call(getClient().getSwitch(getDeviceID(), getClientID(), getTransactionID(), id), "getSwitch", id);
        return response.getValue();
    }

    /**
     * Return the state of the specified switch device as a boolean
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.GetSwitch">A full description of this member's behavior is provided here</a>
     */
    public void getSwitch(int id, AlpacaCallback<Boolean> callback) {
        callAsync(getClient().getSwitch(getDeviceID(), getClientID(), getTransactionID(), id), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSwitch", id);
    }

    /**
     * Sets a switch device to the specified boolean state
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param state The boolean state to set the switch to
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.SetSwitch">A full description of this member's behavior is provided here</a>
     */
    public void setSwitch(int id, boolean state) {
        AlpacaResponse response = call(getClient().setSwitch(getDeviceID(), getClientID(), getTransactionID(), id, state), "setSwitch", id, state);
    }

    /**
     * Sets a switch device to the specified boolean state
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param state The boolean state to set the switch to
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.SetSwitch">A full description of this member's behavior is provided here</a>
     */
    public void setSwitch(int id, boolean state, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSwitch(getDeviceID(), getClientID(), getTransactionID(), id, state), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSwitch", id, state);
    }

    /**
     * Returns the description of the specified switch device
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @return The description of the specified switch device
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.GetSwitchDescription">A full description of this member's behavior is provided here</a>
     */
    public String getSwitchDescription(int id) {
        StringResponse response = call(getClient().getSwitchDescription(getDeviceID(), getClientID(), getTransactionID(), id), "getSwitchDescription", id);
        return response.getValue();
    }

    /**
     * Returns the description of the specified switch device
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.GetSwitchDescription">A full description of this member's behavior is provided here</a>
     */
    public void getSwitchDescription(int id, AlpacaCallback<String> callback) {
        callAsync(getClient().getSwitchDescription(getDeviceID(), getClientID(), getTransactionID(), id), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSwitchDescription", id);
    }

    /**
     * Returns the name of the specified switch device
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @return The name of the specified switch device
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.GetSwitchName">A full description of this member's behavior is provided here</a>
     */
    public String getSwitchName(int id) {
        StringResponse response = call(getClient().getSwitchName(getDeviceID(), getClientID(), getTransactionID(), id), "getSwitchName", id);
        return response.getValue();
    }

    /**
     * Returns the name of the specified switch device
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.GetSwitchName">A full description of this member's behavior is provided here</a>
     */
    public void getSwitchName(int id, AlpacaCallback<String> callback) {
        callAsync(getClient().getSwitchName(getDeviceID(), getClientID(), getTransactionID(), id), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSwitchName", id);
    }

    /**
     * Sets a switch device name to the specified value
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param name The new name for the switch device
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.SetSwitchName">A full description of this member's behavior is provided here</a>
     */
    public void setSwitchName(int id, String name) {
        AlpacaResponse response = call(getClient().setSwitchName(getDeviceID(), getClientID(), getTransactionID(), id, name), "setSwitchName", id, name);
    }

    /**
     * Sets a switch device name to the specified value
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param name The new name for the switch device
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.SetSwitchName">A full description of this member's behavior is provided here</a>
     */
    public void setSwitchName(int id, String name, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSwitchName(getDeviceID(), getClientID(), getTransactionID(), id, name), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSwitchName", id, name);
    }

    /**
     * Returns the value of the specified switch device.
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @return The value of the specified switch device as a double
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.GetSwitchValue">A full description of this member's behavior is provided here</a>
     */
    public double getSwitchValue(int id) {
        DoubleResponse response = call(getClient().getSwitchValue(getDeviceID(), getClientID(), getTransactionID(), id), "getSwitchValue", id);
        return response.getValue();
    }

    /**
     * Returns the value of the specified switch device as a double
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.GetSwitchValue">A full description of this member's behavior is provided here</a>
     */
    public void getSwitchValue(int id, AlpacaCallback<Double> callback) {
        callAsync(getClient().getSwitchValue(getDeviceID(), getClientID(), getTransactionID(), id), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSwitchValue", id);
    }

    /**
     * Sets a switch device to the specified double value
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param value The double value to set the switch to
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.SetSwitchValue">A full description of this member's behavior is provided here</a>
     */
    public void setSwitchValue(int id, double value) {
        AlpacaResponse response = call(getClient().setSwitchValue(getDeviceID(), getClientID(), getTransactionID(), id, value), "setSwitchValue", id, value);
    }

    /**
     * Sets a switch device to the specified double value
     *
     * @param id The device number (0 to MaxSwitch - 1)
     * @param value The double value to set the switch to
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/switch.html#Switch.SetSwitchValue">A full description of this member's behavior is provided here</a>
     */
    public void setSwitchValue(int id, double value, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSwitchValue(getDeviceID(), getClientID(), getTransactionID(), id, value), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSwitchValue", id, value);
    }
}