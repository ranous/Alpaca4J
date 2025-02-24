package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Focuser;
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

    public boolean canAbsoluteFocus() {
        BooleanResponse response = call(getClient().canAbsoluteFocus(getDeviceID(), getClientID(), getTransactionID()), "canAbsoluteFocus");
        return response.getValue();
    }

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

    public boolean isMoving() {
        BooleanResponse response = call(getClient().isMoving(getDeviceID(), getClientID(), getTransactionID()), "isMoving");
        return response.getValue();
    }

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

    public int getMaxIncrement() {
        IntResponse response = call(getClient().getMaxIncrement(getDeviceID(), getClientID(), getTransactionID()), "getMaxIncrement");
        return response.getValue();
    }

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

    public int getMaxStep() {
        IntResponse response = call(getClient().getMaxStep(getDeviceID(), getClientID(), getTransactionID()), "getMaxStep");
        return response.getValue();
    }

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

    public int getPosition() {
        IntResponse response = call(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), "getPosition");
        return response.getValue();
    }

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

    public double getStepSize() {
        DoubleResponse response = call(getClient().getStepSize(getDeviceID(), getClientID(), getTransactionID()), "getStepSize");
        return response.getValue();
    }

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

    public boolean isTemperatureCompensating() {
        BooleanResponse response = call(getClient().isTemperatureCompensating(getDeviceID(), getClientID(), getTransactionID()), "isTemperatureCompensating");
        return response.getValue();
    }

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

    public void setTemperatureCompensation(boolean tempCompState) {
        AlpacaResponse response = call(getClient().setTemperatureCompensation(getDeviceID(), tempCompState, getClientID(), getTransactionID()), "setTemperatureCompensation", tempCompState);
    }

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

    public boolean hasTemperatureCompensation() {
        BooleanResponse response = call(getClient().hasTemperatureCompensation(getDeviceID(), getClientID(), getTransactionID()), "hasTemperatureCompensation");
        return response.getValue();
    }

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

    public double getTemperature() {
        DoubleResponse response = call(getClient().getTemperature(getDeviceID(), getClientID(), getTransactionID()), "getTemperature");
        return response.getValue();
    }

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

    public void haltFocuser() {
        AlpacaResponse response = call(getClient().haltFocuser(getDeviceID(), getClientID(), getTransactionID()), "haltFocuser");
    }

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

    public void moveToPosition(int position) {
        AlpacaResponse response = call(getClient().moveToPosition(getDeviceID(), position, getClientID(), getTransactionID()), "moveToPosition", position);
    }

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