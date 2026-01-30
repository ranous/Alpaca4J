package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.CoverCalibrator;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.CalibratorState;
import org.ascom.alpaca.model.CoverState;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.ValueResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class CoverCalibratorClient extends CommonClient {
    private static final Logger log = Logger.getLogger(CoverCalibratorClient.class);
    private final URI serverAddress;
    private CoverCalibrator client = null;

    public CoverCalibratorClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public CoverCalibratorClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private CoverCalibrator getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(CoverCalibrator.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for CoverCalibrator - " + e.getMessage());
            }
        }
        return client;
    }

    public int getBrightness() {
        IntResponse response = call(getClient().getBrightness(getDeviceID(), getClientID(), getTransactionID()), "getBrightness");
        return response.getValue();
    }

    public void getBrightness(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getBrightness(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getBrightness");
    }

    public boolean isCalibratorChanging() {
        BooleanResponse response = call(getClient().isCalibratorChanging(getDeviceID(), getClientID(), getTransactionID()), "isCalibratorChanging");
        return response.getValue();
    }

    public void isCalibratorChanging(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isCalibratorChanging(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isCalibratorChanging");
    }

    public CalibratorState getCalibratorState() {
        ValueResponse<CalibratorState> response = call(getClient().getCalibratorState(getDeviceID(), getClientID(), getTransactionID()), "getCalibratorState");
        return response.getValue();
    }

    public void getCalibratorState(AlpacaCallback<CalibratorState> callback) {
        callAsync(getClient().getCalibratorState(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ValueResponse<CalibratorState> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCalibratorState");
    }

    public boolean isCoverMoving() {
        BooleanResponse response = call(getClient().isCoverMoving(getDeviceID(), getClientID(), getTransactionID()), "isCoverMoving");
        return response.getValue();
    }

    public void isCoverMoving(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isCoverMoving(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isCoverMoving");
    }

    public CoverState getCoverState() {
        ValueResponse<CoverState> response = call(getClient().getCoverState(getDeviceID(), getClientID(), getTransactionID()), "getCoverState");
        return response.getValue();
    }

    public void getCoverState(AlpacaCallback<CoverState> callback) {
        callAsync(getClient().getCoverState(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ValueResponse<CoverState> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCoverState");
    }

    public int getMaxBrightness() {
        IntResponse response = call(getClient().getMaxBrightness(getDeviceID(), getClientID(), getTransactionID()), "getMaxBrightness");
        return response.getValue();
    }

    public void getMaxBrightness(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getMaxBrightness(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMaxBrightness");
    }

    public void turnCalibratorOff() {
        AlpacaResponse response = call(getClient().turnCalibratorOff(getDeviceID(), getClientID(), getTransactionID()), "turnCalibratorOff");
    }

    public void turnCalibratorOff(AlpacaCallback<Void> callback) {
        callAsync(getClient().turnCalibratorOff(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "turnCalibratorOff");
    }

    public void turnCalibratorOn(int brightness) {
        AlpacaResponse response = call(getClient().turnCalibratorOn(getDeviceID(), getClientID(), getTransactionID(), brightness), "turnCalibratorOn", brightness);
    }

    public void turnCalibratorOn(int brightness, AlpacaCallback<Void> callback) {
        callAsync(getClient().turnCalibratorOn(getDeviceID(), getClientID(), getTransactionID(), brightness), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "turnCalibratorOn", brightness);
    }

    public void closeCover() {
        AlpacaResponse response = call(getClient().closeCover(getDeviceID(), getClientID(), getTransactionID()), "closeCover");
    }

    public void closeCover(AlpacaCallback<Void> callback) {
        callAsync(getClient().closeCover(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "closeCover");
    }

    public void haltCover() {
        AlpacaResponse response = call(getClient().haltCover(getDeviceID(), getClientID(), getTransactionID()), "haltCover");
    }

    public void haltCover(AlpacaCallback<Void> callback) {
        callAsync(getClient().haltCover(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "haltCover");
    }

    public void openCover() {
        AlpacaResponse response = call(getClient().openCover(getDeviceID(), getClientID(), getTransactionID()), "openCover");
    }

    public void openCover(AlpacaCallback<Void> callback) {
        callAsync(getClient().openCover(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "openCover");
    }
}