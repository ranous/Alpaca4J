package org.ascom.alpaca.client;

import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import org.ascom.alpaca.api.Common;
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

    public List<StateValue> getDeviceState() {
        ListResponse<StateValue> response = call(getClient().getDeviceState(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getDeviceState");
        return response.getValue();
    }

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

    public boolean isConnecting() {
        BooleanResponse response = call(getClient().isConnecting(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "isConnecting");
        return response.getValue();
    }

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

    public void connect() {
        AlpacaResponse response = call(getClient().connect(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "connect");
    }

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

    public void disconnect() {
        AlpacaResponse response = call(getClient().disconnect(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "disconnect");
    }

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

    public boolean getConnectedState() {
        BooleanResponse response = call(getClient().isConnected(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "isConnected");
        return response.getValue();
    }

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

    public String getDescription() {
        StringResponse response = call(getClient().getDescription(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getDescription");
        return response.getValue();
    }

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

    public String getDriverInfo() {
        StringResponse response = call(getClient().getDriverInfo(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getDriverInfo");
        return response.getValue();
    }

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

    public String getDriverVersion() {
        StringResponse response = call(getClient().getDriverVersion(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getDriverVersion");
        return response.getValue();
    }

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

    public int getInterfaceVersion() {
        IntResponse response = call(getClient().getInterfaceVersion(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getInterfaceVersion");
        return response.getValue();
    }

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

    public String getName() {
        StringResponse response = call(getClient().getName(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getName");
        return response.getValue();
    }

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

    public List<String> getSupportedActions() {
        ListResponse<String> response = call(getClient().getSupportedActions(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID()), "getSupportedActions");
        return response.getValue();
    }

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

    public String executeAction(String action, String parameters) {
        StringResponse response = call(getClient().executeAction(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID(), action, parameters),
                "executeAction", action, parameters);
        return response.getValue();
    }

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
}
