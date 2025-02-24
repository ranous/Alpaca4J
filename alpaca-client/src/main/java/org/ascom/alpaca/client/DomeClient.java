package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Dome;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class DomeClient extends CommonClient {
    private static final Logger log = Logger.getLogger(DomeClient.class);
    private final URI serverAddress;
    private Dome client = null;

    public DomeClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public DomeClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Dome getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Dome.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Dome - " + e.getMessage());
            }
        }
        return client;
    }

    public double getAltitude() {
        DoubleResponse response = call(getClient().getAltitude(getDeviceID(), getClientID(), getTransactionID()), "getAltitude");
        return response.getValue();
    }

    public void getAltitude(AlpacaCallback<Double> callback) {
        callAsync(getClient().getAltitude(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getAltitude");
    }

    public boolean atHome() {
        BooleanResponse response = call(getClient().atHome(getDeviceID(), getClientID(), getTransactionID()), "atHome");
        return response.getValue();
    }

    public void atHome(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().atHome(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "atHome");
    }

    public boolean atPark() {
        BooleanResponse response = call(getClient().atPark(getDeviceID(), getClientID(), getTransactionID()), "atPark");
        return response.getValue();
    }

    public void atPark(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().atPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "atPark");
    }

    public double getAzimuth() {
        DoubleResponse response = call(getClient().getAzimuth(getDeviceID(), getClientID(), getTransactionID()), "getAzimuth");
        return response.getValue();
    }

    public void getAzimuth(AlpacaCallback<Double> callback) {
        callAsync(getClient().getAzimuth(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getAzimuth");
    }

    public boolean canFindHome() {
        BooleanResponse response = call(getClient().canFindHome(getDeviceID(), getClientID(), getTransactionID()), "canFindHome");
        return response.getValue();
    }

    public void canFindHome(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canFindHome(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canFindHome");
    }

    public boolean canPark() {
        BooleanResponse response = call(getClient().canPark(getDeviceID(), getClientID(), getTransactionID()), "canPark");
        return response.getValue();
    }

    public void canPark(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canPark");
    }

    public boolean canSetAltitude() {
        BooleanResponse response = call(getClient().canSetAltitude(getDeviceID(), getClientID(), getTransactionID()), "canSetAltitude");
        return response.getValue();
    }

    public void canSetAltitude(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetAltitude(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetAltitude");
    }

    public boolean canSetAzimuth() {
        BooleanResponse response = call(getClient().canSetAzimuth(getDeviceID(), getClientID(), getTransactionID()), "canSetAzimuth");
        return response.getValue();
    }

    public void canSetAzimuth(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetAzimuth(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetAzimuth");
    }

    public boolean canSetPark() {
        BooleanResponse response = call(getClient().canSetPark(getDeviceID(), getClientID(), getTransactionID()), "canSetPark");
        return response.getValue();
    }

    public void canSetPark(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetPark");
    }

    public boolean canSetShutter() {
        BooleanResponse response = call(getClient().canSetShutter(getDeviceID(), getClientID(), getTransactionID()), "canSetShutter");
        return response.getValue();
    }

    public void canSetShutter(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetShutter(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetShutter");
    }

    public boolean canSlave() {
        BooleanResponse response = call(getClient().canSlave(getDeviceID(), getClientID(), getTransactionID()), "canSlave");
        return response.getValue();
    }

    public void canSlave(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSlave(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSlave");
    }

    public boolean canSyncAzimuth() {
        BooleanResponse response = call(getClient().canSyncAzimuth(getDeviceID(), getClientID(), getTransactionID()), "canSyncAzimuth");
        return response.getValue();
    }

    public void canSyncAzimuth(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSyncAzimuth(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSyncAzimuth");
    }

    public int getShutterStatus() {
        IntResponse response = call(getClient().getShutterStatus(getDeviceID(), getClientID(), getTransactionID()), "getShutterStatus");
        return response.getValue();
    }

    public void getShutterStatus(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getShutterStatus(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getShutterStatus");
    }

    public boolean isSlaved() {
        BooleanResponse response = call(getClient().isSlaved(getDeviceID(), getClientID(), getTransactionID()), "isSlaved");
        return response.getValue();
    }

    public void isSlaved(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isSlaved(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isSlaved");
    }

    public void setSlaved(boolean slaved) {
        call(getClient().setSlaved(getDeviceID(), getClientID(), getTransactionID(), slaved), "setSlaved", slaved);
    }

    public void setSlaved(boolean slaved, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSlaved(getDeviceID(), getClientID(), getTransactionID(), slaved), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSlaved", slaved);
    }

    public boolean isSlewing() {
        BooleanResponse response = call(getClient().isSlewing(getDeviceID(), getClientID(), getTransactionID()), "isSlewing");
        return response.getValue();
    }

    public void isSlewing(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isSlewing(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isSlewing");
    }

    public void abortSlew() {
        call(getClient().abortSlew(getDeviceID(), getClientID(), getTransactionID()), "abortSlew");
    }

    public void abortSlew(AlpacaCallback<Void> callback) {
        callAsync(getClient().abortSlew(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "abortSlew");
    }

    public void closeShutter() {
        call(getClient().closeShutter(getDeviceID(), getClientID(), getTransactionID()), "closeShutter");
    }

    public void closeShutter(AlpacaCallback<Void> callback) {
        callAsync(getClient().closeShutter(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "closeShutter");
    }

    public void findHome() {
        call(getClient().findHome(getDeviceID(), getClientID(), getTransactionID()), "findHome");
    }

    public void findHome(AlpacaCallback<Void> callback) {
        callAsync(getClient().findHome(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "findHome");
    }

    public void openShutter() {
        call(getClient().openShutter(getDeviceID(), getClientID(), getTransactionID()), "openShutter");
    }

    public void openShutter(AlpacaCallback<Void> callback) {
        callAsync(getClient().openShutter(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "openShutter");
    }

    public void park() {
        call(getClient().park(getDeviceID(), getClientID(), getTransactionID()), "park");
    }

    public void park(AlpacaCallback<Void> callback) {
        callAsync(getClient().park(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "park");
    }

    public void setPark() {
        call(getClient().setPark(getDeviceID(), getClientID(), getTransactionID()), "setPark");
    }

    public void setPark(AlpacaCallback<Void> callback) {
        callAsync(getClient().setPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setPark");
    }

    public void slewToAltitude(double altitude) {
        call(getClient().slewToAltitude(getDeviceID(), getClientID(), getTransactionID(), altitude), "slewToAltitude", altitude);
    }

    public void slewToAltitude(double altitude, AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToAltitude(getDeviceID(), getClientID(), getTransactionID(), altitude), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToAltitude", altitude);
    }

    public void slewToAzimuth(double azimuth) {
        call(getClient().slewToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth), "slewToAzimuth", azimuth);
    }

    public void slewToAzimuth(double azimuth, AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToAzimuth", azimuth);
    }

    public void syncToAzimuth(double azimuth) {
        call(getClient().syncToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth), "syncToAzimuth", azimuth);
    }

    public void syncToAzimuth(double azimuth, AlpacaCallback<Void> callback) {
        callAsync(getClient().syncToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "syncToAzimuth", azimuth);
    }
}