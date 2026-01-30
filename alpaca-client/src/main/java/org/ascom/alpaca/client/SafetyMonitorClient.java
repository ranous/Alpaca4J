package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.SafetyMonitor;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.BooleanResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

/**
 * Client for interacting with an ASCOM Alpaca SafetyMonitor device.
 */
@SuppressWarnings("unused")
public class SafetyMonitorClient extends CommonClient {
    private static final Logger log = Logger.getLogger(SafetyMonitorClient.class);
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

    /**
     * Indicates whether the monitored state is safe for use.
     *
     * @return true if the monitored state is safe for use, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/safetymonitor.html#SafetyMonitor.IsSafe">A full description of this member's behavior is provided here</a>
     */
    public boolean isSafe() {
        BooleanResponse response = call(getClient().isSafe(getDeviceID(), getClientID(), getTransactionID()), "isSafe");
        checkResponse(response);
        return response.getValue();
    }

    /**
     * Indicates whether the monitored state is safe for use.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/safetymonitor.html#SafetyMonitor.IsSafe">A full description of this member's behavior is provided here</a>
     */
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
