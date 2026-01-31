package org.ascom.alpaca.client;

import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import org.ascom.alpaca.client.impl.api.Common;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The base client all devices must extend.  All the operations that
 * are common to all devices are implemented here.
 */
@SuppressWarnings("unused")
public class CommonClient {
    private final DeviceDescriptor deviceDescriptor;
    private final DeviceType deviceType;
    private final int clientID;
    private final URI serverURI;
    private Common client;

    public CommonClient(DeviceDescriptor deviceDescriptor, URI serverURI) {
        this(deviceDescriptor, serverURI, new Random().nextInt(Integer.MAX_VALUE));
    }

    public CommonClient(DeviceDescriptor deviceDescriptor, URI serverURI, int clientID) {
        this.deviceDescriptor = deviceDescriptor;
        this.deviceType = deviceDescriptor.getDeviceType();
        this.clientID = clientID;
        this.serverURI = serverURI;
    }

    private Common getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverURI.toURL() + "/api/v1/")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Common.class);
            } catch (Exception e) {
                client = null;
                // Should probably be fatal
                logWarn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for " + deviceType.getTypeName() + " - " + e.getMessage());
            }
        }
        return client;
    }

    protected URI getServerAddress() {
        return serverURI;
    }

    static void log(String msg) {
        Platform.get().log(Platform.INFO, msg, null);
    }

    static void logWarn(String msg) {
        Platform.get().log(Platform.WARN, msg, null);
    }

    static void logWarn(String msg, Throwable t) {
        Platform.get().log(Platform.WARN, msg, t);
    }

    /**
     * Checks the response from the device server for error conditions.  Will throw the corresponding exception for
     * any error conditions returned by the server.
     */
    static protected void checkResponse(AlpacaResponse response) {
        if (response == null) {
            throw new ClientException("Received a null response from the server");
        }
        switch (response.getErrorNumber()) {
            case 0 -> { } // no error
            case 1024 -> throw new PropertyNotImplementedException(response.getClientTransactionID(), response.getErrorMessage());
            case 1025 -> throw new InvalidValueException(response.getClientTransactionID(), response.getErrorMessage());
            case 1026 -> throw new ValueNotSetException(response.getClientTransactionID(), response.getErrorMessage());
            case 1031 -> throw new NotConnectedException(response.getClientTransactionID(), response.getErrorMessage());
            case 1032 -> throw new InvalidWhileParkedException(response.getClientTransactionID(), response.getErrorMessage());
            case 1033 -> throw new InvalidWhileSlavedException(response.getClientTransactionID(), response.getErrorMessage());
            case 1035 -> throw new InvalidOperationException(response.getClientTransactionID(), response.getErrorMessage());
            case 1036 -> throw new ActionNotImplementedException(response.getClientTransactionID(), response.getErrorMessage());
            default -> {
                logWarn("Received an unknown error type of " + response.getErrorNumber() + " with message: " + response.getErrorMessage());
                throw new UnknownErrorException(response.getClientTransactionID(), response.getErrorNumber(), response.getErrorMessage());
            }
        }
    }

    static protected  <T extends AlpacaResponse> T call(Call<T> call, String methodName, Object... methodArgs) {
        try {
            Response<T> response = call.execute();
            if (!response.isSuccessful()) {
                try (ResponseBody errorResponse = response.errorBody()) {
                    String responseBody = errorResponse != null ? " - " + errorResponse : "";
                    switch (response.code()) {
                        case 400:
                            throw new ServerException("Error calling " + getMethodSignature(methodName, methodArgs) + " - Status=" + response.code() + responseBody);
                        case 404:
                            throw new ServerException("Server has no resource called " + getMethodSignature(methodName, methodArgs) + " - Status=" + response.code() + responseBody);
                        default:
                            throw new ServerException("Error calling " + getMethodSignature(methodName, methodArgs) + " - Status=" + response.code() + responseBody);
                    }
                }
            }
            T callResponse = response.body();
            checkResponse(callResponse);
            return response.body();
        } catch (AlpacaException e) {
            throw e;
        } catch (IOException e) {
            logWarn("Problem calling " + getMethodSignature(methodName, methodArgs) + " - " + e.getMessage(), e);
            throw new ClientException.CommunicationsError("Error calling " + getMethodSignature(methodName, methodArgs) + " - " + e.getMessage());
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg == null || msg.isEmpty()) {
                msg = e.toString();
            }
            logWarn("Problem calling " + getMethodSignature(methodName, methodArgs) + " - " + msg, e);
            throw new ClientException("Error calling " + getMethodSignature(methodName, methodArgs) + " - " + msg, e);
        }
    }

    static protected <T extends AlpacaResponse> void callAsync(Call<T> call, final AlpacaCallback<T> callback, final String methodName, final Object... methodArgs) {
        call.enqueue(new Callback<>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (!response.isSuccessful()) {
                    String errMsg = "Error calling " + getMethodSignature(methodName, methodArgs) + " - Status=" + response.code();
                    try (ResponseBody errorResponse = response.errorBody()) {
                        String responseBody = errorResponse != null ? " - " + errorResponse : "";
                        errMsg = errMsg + responseBody;
                    } catch (Exception ignore) {
                        // best effort to get the error body
                    }
                    AlpacaClientError error = new AlpacaClientError.ServerError(errMsg, response.code());
                    callback.error(error);
                    return;
                }
                T alpacaResponse = response.body();
                if (alpacaResponse == null) {
                    callback.error(new AlpacaClientError("Error calling " + getMethodSignature(methodName, methodArgs) + " - No response body"));
                    return;
                }
                ErrorCode errorType = ErrorCode.fromCode(alpacaResponse.getErrorNumber());
                if (errorType != ErrorCode.Success) {
                    callback.error(new AlpacaClientError(alpacaResponse.getClientTransactionID(), alpacaResponse.getServerTransactionID(),
                            ErrorCode.PropertyNotImplemented, alpacaResponse.getErrorMessage()));
                    return;
                }
                callback.success(response.body());
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                String msg = "Error calling " + getMethodSignature(methodName, methodArgs) + " - " + t.getMessage();
                callback.error(new AlpacaClientError(msg));
            }
        });
    }

    private static String getMethodSignature(String name, Object[] args) {
        String sig = name + "(";
        if (args != null && args.length > 0) {
            sig = sig + joiner(Arrays.asList(args));
        }

        return sig + ")";
    }

    private static String joiner(List<Object> items) {

        if (items == null || items.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(items.get(0).toString());
        if (items.size() > 1) {
            for (int i = 1 ; i < items.size() ; i++) {
                sb.append(",").append(items.get(i).toString());
            }
        }
        return sb.toString();
    }


    static  int getTransactionID() {
        return TransactionIDFactory.getClientTransactionID();
    }

    int getClientID() {
        return clientID;
    }


    public DeviceType getDeviceType() {
        return deviceDescriptor.getDeviceType();
    }

    public int getDeviceID() {
        return deviceDescriptor.getDeviceNumber();
    }

    public DeviceDescriptor getDeviceDescriptor() {
        return deviceDescriptor;
    }


    // The following methods are the actual Alpaca API methods that are implemented in the CommonClient class.

    /**
     * Returns the device's operational state in a single call.
     *
     * @return a list of the device's operational state values
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.DeviceState">A full description of this member's behavior is provided here</a>
     */
    public List<StateValue> getDeviceState() {
        ListResponse<StateValue> response = call(getClient().getDeviceState(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getDeviceState");
        return response.getValue();
    }

    /**
     * Returns the device's operational state in a single call.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.DeviceState">A full description of this member's behavior is provided here</a>
     */
    public void getDeviceState(AlpacaCallback<List<StateValue>> callback) {
        callAsync(getClient().getDeviceState(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<StateValue> response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getDeviceState");
    }

    /**
     * Completion variable for the asynchronous Connect() and Disconnect() methods.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Connecting">A full description of this member's behavior is provided here</a>
     */
    public boolean isConnecting() {
        BooleanResponse response = call(getClient().isConnecting(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "isConnecting");
        return response.getValue();
    }

    /**
     * Completion variable for the asynchronous Connect() and Disconnect() methods.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Connecting">A full description of this member's behavior is provided here</a>
     */
    public void isConnecting(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isConnecting(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isConnecting");
    }

    /**
     * Starts an asynchronous connection to the device.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Connect">A full description of this member's behavior is provided here</a>
     */
    public void connect() {
        AlpacaResponse response = call(getClient().connect(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "connect");
    }

    /**
     * Starts an asynchronous connection to the device.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Connect">A full description of this member's behavior is provided here</a>
     */
    public void connect(AlpacaCallback<Void> callback) {
        callAsync(getClient().connect(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse response) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "connect");
    }

    /**
     * Starts an asynchronous disconnect from the device.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Disconnect">A full description of this member's behavior is provided here</a>
     */
    public void disconnect() {
        AlpacaResponse response = call(getClient().disconnect(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "disconnect");
    }

    /**
     * Starts an asynchronous disconnect from the device.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Disconnect">A full description of this member's behavior is provided here</a>
     */
    public void disconnect(AlpacaCallback<Void> callback) {
        callAsync(getClient().disconnect(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse response) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "disconnect");
    }

    /**
     * Retrieves the connected state of the device
     * @return is the client connected to the device
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Connected">A full description of this member's behavior is provided here</a>
     */
    public boolean getConnectedState() {
        BooleanResponse response = call(getClient().isConnected(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "isConnected");
        return response.getValue();
    }

    /**
     * Retrieves the connected state of the device.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Connected">A full description of this member's behavior is provided here</a>
     */
    public void getConnectedState(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isConnected(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isConnected");
    }

    public void setConnectedState(boolean state) {
        AlpacaResponse response = call(getClient().setConnectedState(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID(), state), "setConnectedState", state);
    }

    public void setConnectedState(boolean state, AlpacaCallback<Void> callback) {
        callAsync(getClient().setConnectedState(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID(), state), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse response) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setConnectedState", state);
    }

    /**
     * Device description
     *
     * @return device description
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Description">A full description of this member's behavior is provided here</a>
     */
    public String getDescription() {
        StringResponse response = call(getClient().getDescription(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getDescription");
        return response.getValue();
    }

    /**
     * Device description
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Description">A full description of this member's behavior is provided here</a>
     */
    public void getDescription(AlpacaCallback<String> callback) {
        callAsync(getClient().getDescription(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getDescription");
    }

    /**
     * Device driver description
     *
     * @return device driver description
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.DriverInfo">A full description of this member's behavior is provided here</a>
     */
    public String getDriverInfo() {
        StringResponse response = call(getClient().getDriverInfo(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getDriverInfo");
        return response.getValue();
    }

    /**
     * Device driver description
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.DriverInfo">A full description of this member's behavior is provided here</a>
     */
    public void getDriverInfo(AlpacaCallback<String> callback) {
        callAsync(getClient().getDriverInfo(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getDriverInfo");
    }

    /**
     * Returns the version number of the driver that the client is connected to.
     *
     * @return The version number of the driver
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.DriverVersion">A full description of this member's behavior is provided here</a>
     */
    public String getDriverVersion() {
        StringResponse response = call(getClient().getDriverVersion(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getDriverVersion");
        return response.getValue();
    }

    /**
     * Returns the version number of the driver that the client is connected to.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.DriverVersion">A full description of this member's behavior is provided here</a>
     */
    public void getDriverVersion(AlpacaCallback<String> callback) {
        callAsync(getClient().getDriverVersion(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getDriverVersion");
    }

    /**
     * The ASCOM Device interface version number that this device supports.  This indicates
     * the capabilities of the device and is used to determine which methods are supported.
     * It is not the version number of the driver itself.
     *
     * @return The ASCOM Device interface version number
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.InterfaceVersion">A full description of this member's behavior is provided here</a>
     */
    public int getInterfaceVersion() {
        IntResponse response = call(getClient().getInterfaceVersion(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getInterfaceVersion");
        return response.getValue();
    }

    /**
     * The ASCOM Device interface version number that this device supports.  This indicates
     * the capabilities of the device and is used to determine which methods are supported.
     * It is not the version number of the driver itself.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.InterfaceVersion">A full description of this member's behavior is provided here</a>
     */
    public void getInterfaceVersion(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getInterfaceVersion(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getInterfaceVersion");
    }

    /**
     * Returns the name of the device the client is connected to.
     *
     * @return device name
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Name">A full description of this member's behavior is provided here</a>
     */
    public String getName() {
        StringResponse response = call(getClient().getName(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getName");
        return response.getValue();
    }

    /**
     * Returns the name of the device the client is connected to.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Name">A full description of this member's behavior is provided here</a>
     */
    public void getName(AlpacaCallback<String> callback) {
        callAsync(getClient().getName(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getName");
    }

    /**
     * Returns the list of action names supported by this driver.
     *
     * @return A list of the names of the driver-specific actions supported by this driver.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SupportedActions">A full description of this member's behavior is provided here</a>
     */
    public List<String> getSupportedActions() {
        ListResponse<String> response = call(getClient().getSupportedActions(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getSupportedActions");
        return response.getValue();
    }

    /**
     * Returns the list of action names supported by this driver.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SupportedActions">A full description of this member's behavior is provided here</a>
     */
    public void getSupportedActions(AlpacaCallback<List<String>> callback) {
        callAsync(getClient().getSupportedActions(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<String> response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSupportedActions");
    }

    /**
     * Invokes the named device-specific action.
     *
     * @param action One of the recognised device actions
     * @param parameters A string containing the parameters for the action
     * @return An action-specific string containing the result of the action
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Action">A full description of this member's behavior is provided here</a>
     */
    public String executeAction(String action, String parameters) {
        StringResponse response = call(getClient().executeAction(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID(), action, parameters),
                "executeAction", action, parameters);
        return response.getValue();
    }

    /**
     * Invokes the named device-specific action.
     *
     * @param action One of the recognised device actions
     * @param parameters A string containing the parameters for the action
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Action">A full description of this member's behavior is provided here</a>
     */
    public void executeAction(String action, String parameters, AlpacaCallback<String> callback) {
        callAsync(getClient().executeAction(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID(), action, parameters), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse response) {
                callback.success(response.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "executeAction", action, parameters);
    }

    public void setDebug(String debug) {
        if (Boolean.parseBoolean(debug)) {

        } else {

        }
    }
}
