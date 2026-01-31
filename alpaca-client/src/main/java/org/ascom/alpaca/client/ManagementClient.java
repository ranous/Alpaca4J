package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Management;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.ServerInfo;
import org.ascom.alpaca.response.ListResponse;
import org.ascom.alpaca.response.ServerInfoResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;
import java.util.List;
import java.util.Random;

/**
 * Client for interacting with the Alpaca Server.  It's used to get information on the server
 * and what devices are available.
 */
@SuppressWarnings("unused")
public class ManagementClient {
    private static final Logger log = Logger.getLogger(ManagementClient.class);
    private final URI serverAddress;
    private final int clientID;
    private Management client = null;

    public ManagementClient(URI serverURI) {
        this(serverURI, new Random().nextInt(Integer.MAX_VALUE));
    }

    public ManagementClient(URI serverURI, int clientID) {
        serverAddress = serverURI;
        this.clientID = clientID;
    }

    private synchronized Management getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toURL() + "/management/")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Management.class);
                return client;
            } catch (Throwable e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Management - " + e.getMessage());
            }
        }
        return client;
    }

    private int getTransactionID() {
        return CommonClient.getTransactionID();
    }

    public int getClientID() {
        return clientID;
    }

    /**
     * Returns an integer array of supported Alpaca API version numbers.
     *
     * @return the supported API versions
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     */
    public List<Integer> getApiVersions() {
        ListResponse<Integer> response = CommonClient.call(getClient().getApiVersions(getClientID(), getTransactionID()), "getApiVersions");
        return response.getValue();
    }

    /**
     * Returns an integer array of supported Alpaca API version numbers.
     *
     * @return the supported API versions
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     */
    public void getApiVersions(AlpacaCallback<List<Integer>> callback) {
        CommonClient.callAsync(getClient().getApiVersions(getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<Integer> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError e) {
                callback.error(e);
            }
        }, "getApiVersions");
    }

    /**
     * Returns an array of device description objects, providing unique information for each served device,
     * enabling them to be accessed through the Alpaca Device API.
     *
     * @return a list of devices
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     */
    public List<DeviceDescriptor> getConfiguredDevices() {
        ListResponse<DeviceDescriptor> response = CommonClient.call(getClient().getConfiguredDevices(getClientID(), getTransactionID()), "getConfiguredDevices");
        return response.getValue();
    }

    /**
     * Returns an array of device description objects, providing unique information for each served device,
     * enabling them to be accessed through the Alpaca Device API.
     *
     * @return a list of devices
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     */
    public void getConfiguredDevices(AlpacaCallback<List<DeviceDescriptor>> callback) {
        CommonClient.callAsync(getClient().getConfiguredDevices(getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<DeviceDescriptor> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError e) {
                callback.error(e);
            }
        }, "getConfiguredDevices");
    }

    /**
     * Returns cross-cutting information that applies to all devices available at this URL:Port.
     *
     * @return service description
     */
    public ServerInfo getDescription() {
        ServerInfoResponse response = CommonClient.call(getClient().getDescription(getClientID(), getTransactionID()), "getDescription");
        return response.getValue();
    }

    /**
     * Returns cross-cutting information that applies to all devices available at this URL:Port.
     *
     * @return service description
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     */
    public void getDescription(AlpacaCallback<ServerInfo> callback) {
        CommonClient.callAsync(getClient().getDescription(getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ServerInfoResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError e) {
                callback.error(e);
            }
        }, "getDescription");
    }
}
