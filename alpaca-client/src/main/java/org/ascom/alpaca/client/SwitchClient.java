package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Switch;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

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

    public boolean canWrite() {
        BooleanResponse response = call(getClient().canWrite(getDeviceID(), getClientID(), getTransactionID()), "canWrite");
        return response.getValue();
    }

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

    public int getMaxSwitch() {
        IntResponse response = call(getClient().getMaxSwitch(getDeviceID(), getClientID(), getTransactionID()), "getMaxSwitch");
        return response.getValue();
    }

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

    public boolean getSwitch(int id) {
        BooleanResponse response = call(getClient().getSwitch(getDeviceID(), getClientID(), getTransactionID(), id), "getSwitch", id);
        return response.getValue();
    }

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

    public void setSwitch(int id, boolean state) {
        AlpacaResponse response = call(getClient().setSwitch(getDeviceID(), getClientID(), getTransactionID(), id, state), "setSwitch", id, state);
    }

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

    public String getSwitchDescription(int id) {
        StringResponse response = call(getClient().getSwitchDescription(getDeviceID(), getClientID(), getTransactionID(), id), "getSwitchDescription", id);
        return response.getValue();
    }

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

    public String getSwitchName(int id) {
        StringResponse response = call(getClient().getSwitchName(getDeviceID(), getClientID(), getTransactionID(), id), "getSwitchName", id);
        return response.getValue();
    }

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

    public void setSwitchName(int id, String name) {
        AlpacaResponse response = call(getClient().setSwitchName(getDeviceID(), getClientID(), getTransactionID(), id, name), "setSwitchName", id, name);
    }

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

    public double getSwitchValue(int id) {
        DoubleResponse response = call(getClient().getSwitchValue(getDeviceID(), getClientID(), getTransactionID(), id), "getSwitchValue", id);
        return response.getValue();
    }

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

    public void setSwitchValue(int id, double value) {
        AlpacaResponse response = call(getClient().setSwitchValue(getDeviceID(), getClientID(), getTransactionID(), id, value), "setSwitchValue", id, value);
    }

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