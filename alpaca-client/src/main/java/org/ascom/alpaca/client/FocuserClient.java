package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Focuser;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class FocuserClient extends CommonClient {
    private final URI serverAddress;
    private Focuser client = null;

    public FocuserClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public FocuserClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Focuser getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Focuser.class);
                return client;
            } catch (Exception e) {
                logWarn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Focuser - " + e.getMessage());
            }
        }
        return client;
    }

    /**
     * Indicates whether the focuser is capable of absolute position.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Absolute">A full description of this member's behavior is provided here</a>
     */
    public boolean canAbsoluteFocus() {
        BooleanResponse response = call(getClient().canAbsoluteFocus(getDeviceID(), getClientID(), getTransactionID()), "canAbsoluteFocus");
        return response.getValue();
    }

    /**
     * Indicates whether the focuser is capable of absolute position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Absolute">A full description of this member's behavior is provided here</a>
     */
    public void canAbsoluteFocus(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canAbsoluteFocus(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canAbsoluteFocus");
    }

    /**
     * Indicates whether the focuser is currently moving.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.IsMoving">A full description of this member's behavior is provided here</a>
     */
    public boolean isMoving() {
        BooleanResponse response = call(getClient().isMoving(getDeviceID(), getClientID(), getTransactionID()), "isMoving");
        return response.getValue();
    }

    /**
     * Indicates whether the focuser is currently moving.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.IsMoving">A full description of this member's behavior is provided here</a>
     */
    public void isMoving(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isMoving(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isMoving");
    }

    /**
     * Returns the focuser's maximum increment size.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.MaxIncrement">A full description of this member's behavior is provided here</a>
     */
    public int getMaxIncrement() {
        IntResponse response = call(getClient().getMaxIncrement(getDeviceID(), getClientID(), getTransactionID()), "getMaxIncrement");
        return response.getValue();
    }

    /**
     * Returns the focuser's maximum increment size.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.MaxIncrement">A full description of this member's behavior is provided here</a>
     */
    public void getMaxIncrement(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getMaxIncrement(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMaxIncrement");
    }

    /**
     * Returns the focuser's maximum step size.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.MaxStep">A full description of this member's behavior is provided here</a>
     */
    public int getMaxStep() {
        IntResponse response = call(getClient().getMaxStep(getDeviceID(), getClientID(), getTransactionID()), "getMaxStep");
        return response.getValue();
    }

    /**
     * Returns the focuser's maximum step size.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.MaxStep">A full description of this member's behavior is provided here</a>
     */
    public void getMaxStep(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getMaxStep(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMaxStep");
    }

    /**
     * Returns the focuser's current position.
     *
     * @return The focuser's current position.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Position">A full description of this member's behavior is provided here</a>
     */
    public int getPosition() {
        IntResponse response = call(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), "getPosition");
        return response.getValue();
    }

    /**
     * Returns the focuser's current position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Position">A full description of this member's behavior is provided here</a>
     */
    public void getPosition(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getPosition");
    }

    /**
     * Returns the focuser's step size.
     *
     * @return The focuser's step size.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.StepSize">A full description of this member's behavior is provided here</a>
     */
    public double getStepSize() {
        DoubleResponse response = call(getClient().getStepSize(getDeviceID(), getClientID(), getTransactionID()), "getStepSize");
        return response.getValue();
    }

    /**
     * Returns the focuser's step size.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.StepSize">A full description of this member's behavior is provided here</a>
     */
    public void getStepSize(AlpacaCallback<Double> callback) {
        callAsync(getClient().getStepSize(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getStepSize");
    }

    /**
     * Retrieves the state of temperature compensation mode
     *
     * @return True if temperature compensation is enabled, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.TempComp">A full description of this member's behavior is provided here</a>
     */
    public boolean isTemperatureCompensating() {
        BooleanResponse response = call(getClient().isTemperatureCompensating(getDeviceID(), getClientID(), getTransactionID()), "isTemperatureCompensating");
        return response.getValue();
    }

    /**
     * Retrieves the state of temperature compensation mode
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.TempComp">A full description of this member's behavior is provided here</a>
     */
    public void isTemperatureCompensating(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isTemperatureCompensating(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isTemperatureCompensating");
    }

    /**
     * Sets the device's temperature compensation mode.
     *
     * @param tempCompState Set true to enable the focuser's temperature compensation mode, otherwise false for normal operation.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.TempComp">A full description of this member's behavior is provided here</a>
     */
    public void setTemperatureCompensation(boolean tempCompState) {
        AlpacaResponse response = call(getClient().setTemperatureCompensation(getDeviceID(), tempCompState, getClientID(), getTransactionID()), "setTemperatureCompensation", tempCompState);
    }

    /**
     * Sets the device's temperature compensation mode.
     *
     * @param tempCompState Set true to enable the focuser's temperature compensation mode, otherwise false for normal operation.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.TempComp">A full description of this member's behavior is provided here</a>
     */
    public void setTemperatureCompensation(boolean tempCompState, AlpacaCallback<Void> callback) {
        callAsync(getClient().setTemperatureCompensation(getDeviceID(), tempCompState, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setTemperatureCompensation", tempCompState);
    }

    /**
     * Indicates whether the focuser has temperature compensation.
     *
     * @return True if the focuser has temperature compensation, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.TempCompAvailable">A full description of this member's behavior is provided here</a>
     */
    public boolean hasTemperatureCompensation() {
        BooleanResponse response = call(getClient().hasTemperatureCompensation(getDeviceID(), getClientID(), getTransactionID()), "hasTemperatureCompensation");
        return response.getValue();
    }

    /**
     * Indicates whether the focuser has temperature compensation.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.TempCompAvailable">A full description of this member's behavior is provided here</a>
     */
    public void hasTemperatureCompensation(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().hasTemperatureCompensation(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "hasTemperatureCompensation");
    }

    /**
     * Returns the focuser's current temperature.
     *
     * @return The focuser's current temperature.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Temperature">A full description of this member's behavior is provided here</a>
     */
    public double getTemperature() {
        DoubleResponse response = call(getClient().getTemperature(getDeviceID(), getClientID(), getTransactionID()), "getTemperature");
        return response.getValue();
    }

    /**
     * Returns the focuser's current temperature.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Temperature">A full description of this member's behavior is provided here</a>
     */
    public void getTemperature(AlpacaCallback<Double> callback) {
        callAsync(getClient().getTemperature(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTemperature");
    }

    /**
     * Immediatley stops focuser motion.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Halt">A full description of this member's behavior is provided here</a>
     */
    public void haltFocuser() {
        AlpacaResponse response = call(getClient().haltFocuser(getDeviceID(), getClientID(), getTransactionID()), "haltFocuser");
    }

    /**
     * Immediatley stops focuser motion.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Halt">A full description of this member's behavior is provided here</a>
     */
    public void haltFocuser(AlpacaCallback<Void> callback) {
        callAsync(getClient().haltFocuser(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "haltFocuser");
    }

    /**
     * Moves the focuser to a new position.
     *
     * @param position Step distance or absolute position, depending on the value of the Absolute property
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Move">A full description of this member's behavior is provided here</a>
     */
    public void moveToPosition(int position) {
        AlpacaResponse response = call(getClient().moveToPosition(getDeviceID(), position, getClientID(), getTransactionID()), "moveToPosition", position);
    }

    /**
     * Moves the focuser to a new position.
     *
     * @param position Step distance or absolute position, depending on the value of the Absolute property
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/focuser.html#Focuser.Move">A full description of this member's behavior is provided here</a>
     */
    public void moveToPosition(int position, AlpacaCallback<Void> callback) {
        callAsync(getClient().moveToPosition(getDeviceID(), position, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "moveToPosition", position);
    }
}