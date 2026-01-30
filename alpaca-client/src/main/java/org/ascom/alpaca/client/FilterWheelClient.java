package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.FilterWheel;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.ListResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;
import java.util.List;

@SuppressWarnings("unused")
public class FilterWheelClient extends CommonClient {
    private static final Logger log = Logger.getLogger(FilterWheelClient.class);
    private final URI serverAddress;
    private FilterWheel client = null;

    public FilterWheelClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public FilterWheelClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private FilterWheel getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(FilterWheel.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for FilterWheel - " + e.getMessage());
            }
        }
        return client;
    }

    public List<Integer> getFocusOffsets() {
        ListResponse<Integer> response = call(getClient().getFocusOffsets(getDeviceID(), getClientID(), getTransactionID()), "getFocusOffsets");
        return response.getValue();
    }

    public void getFocusOffsets(AlpacaCallback<List<Integer>> callback) {
        callAsync(getClient().getFocusOffsets(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<Integer> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getFocusOffsets");
    }

    public List<String> getFilterNames() {
        ListResponse<String> response = call(getClient().getFilterNames(getDeviceID(), getClientID(), getTransactionID()), "getFilterNames");
        return response.getValue();
    }

    public void getFilterNames(AlpacaCallback<List<String>> callback) {
        callAsync(getClient().getFilterNames(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<String> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getFilterNames");
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

    public void setPosition(int position) {
        AlpacaResponse response = call(getClient().setPosition(getDeviceID(), getClientID(), getTransactionID(), position), "setPosition", position);
    }

    public void setPosition(int position, AlpacaCallback<Void> callback) {
        callAsync(getClient().setPosition(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setPosition", position);
    }
}