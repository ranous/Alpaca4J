package org.ascom.alpaca.client;

import org.ascom.alpaca.api.SafetyMonitor;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.BooleanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class SafetyMonitorClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(SafetyMonitorClient.class);
    private final URI serverAddress;
    private SafetyMonitor client = null;

    public SafetyMonitorClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public SafetyMonitorClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private SafetyMonitor getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toURL() + "/api/v1/")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(SafetyMonitor.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for SafetyMonitor - " + e.getMessage());
            }
        }
        return client;
    }

    public boolean isSafe() {
        BooleanResponse response = call(getClient().isSafe(getDeviceID(), getClientID(), getTransactionID()), "isSafe");
        checkResponse(response);
        return response.getValue();
    }

    public void isSafe(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isSafe(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isSafe");
    }
}
