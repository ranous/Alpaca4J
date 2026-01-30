package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Rotator;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class RotatorClient extends CommonClient {
    private static final Logger log = Logger.getLogger(RotatorClient.class);
    private final URI serverAddress;
    private Rotator client = null;

    public RotatorClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public RotatorClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Rotator getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Rotator.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Rotator - " + e.getMessage());
            }
        }
        return client;
    }

    public boolean canReverse() {
        BooleanResponse response = call(getClient().canReverse(getDeviceID(), getClientID(), getTransactionID()), "canReverse");
        return response.getValue();
    }

    public void canReverse(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canReverse(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canReverse");
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

    public double getMechanicalPosition() {
        DoubleResponse response = call(getClient().getMechanicalPosition(getDeviceID(), getClientID(), getTransactionID()), "getMechanicalPosition");
        return response.getValue();
    }

    public void getMechanicalPosition(AlpacaCallback<Double> callback) {
        callAsync(getClient().getMechanicalPosition(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMechanicalPosition");
    }

    public double getPosition() {
        DoubleResponse response = call(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), "getPosition");
        return response.getValue();
    }

    public void getPosition(AlpacaCallback<Double> callback) {
        callAsync(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getPosition");
    }

    public boolean isReversed() {
        BooleanResponse response = call(getClient().isReversed(getDeviceID(), getClientID(), getTransactionID()), "isReversed");
        return response.getValue();
    }

    public void isReversed(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isReversed(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isReversed");
    }

    public void setReversed(boolean reversed) {
        AlpacaResponse response = call(getClient().setReversed(getDeviceID(), getClientID(), getTransactionID(), reversed), "setReversed", reversed);
    }

    public void setReversed(boolean reversed, AlpacaCallback<Void> callback) {
        callAsync(getClient().setReversed(getDeviceID(), getClientID(), getTransactionID(), reversed), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setReversed", reversed);
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

    public double getTargetPosition() {
        DoubleResponse response = call(getClient().getTargetPosition(getDeviceID(), getClientID(), getTransactionID()), "getTargetPosition");
        return response.getValue();
    }

    public void getTargetPosition(AlpacaCallback<Double> callback) {
        callAsync(getClient().getTargetPosition(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTargetPosition");
    }

    public void halt() {
        AlpacaResponse response = call(getClient().halt(getDeviceID(), getClientID(), getTransactionID()), "halt");
    }

    public void halt(AlpacaCallback<Void> callback) {
        callAsync(getClient().halt(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "halt");
    }

    public void move(double position) {
        AlpacaResponse response = call(getClient().move(getDeviceID(), getClientID(), getTransactionID(), position), "move", position);
    }

    public void move(double position, AlpacaCallback<Void> callback) {
        callAsync(getClient().move(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "move", position);
    }

    public void moveAbsolute(double position) {
        AlpacaResponse response = call(getClient().moveAbsolute(getDeviceID(), getClientID(), getTransactionID(), position), "moveAbsolute", position);
    }

    public void moveAbsolute(double position, AlpacaCallback<Void> callback) {
        callAsync(getClient().moveAbsolute(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "moveAbsolute", position);
    }

    public void moveMechanical(double position) {
        AlpacaResponse response = call(getClient().moveMechanical(getDeviceID(), getClientID(), getTransactionID(), position), "moveMechanical", position);
    }

    public void moveMechanical(double position, AlpacaCallback<Void> callback) {
        callAsync(getClient().moveMechanical(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "moveMechanical", position);
    }

    public void sync(double position) {
        AlpacaResponse response = call(getClient().sync(getDeviceID(), getClientID(), getTransactionID(), position), "sync", position);
    }

    public void sync(double position, AlpacaCallback<Void> callback) {
        callAsync(getClient().sync(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "sync", position);
    }
}