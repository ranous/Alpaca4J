package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Management;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.ServerInfo;
import org.ascom.alpaca.response.ListResponse;
import org.ascom.alpaca.response.ServerInfoResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;
import java.util.List;
import java.util.Random;

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

    public List<Integer> getApiVersions() {
        ListResponse<Integer> response = CommonClient.call(getClient().getApiVersions(getClientID(), getTransactionID()), "getApiVersions");
        return response.getValue();
    }

    public List<DeviceDescriptor> getConfiguredDevices() {
        ListResponse<DeviceDescriptor> response = CommonClient.call(getClient().getConfiguredDevices(getClientID(), getTransactionID()), "getConfiguredDevices");
        return response.getValue();
    }

    public ServerInfo getDescription() {
        ServerInfoResponse response = CommonClient.call(getClient().getDescription(getClientID(), getTransactionID()), "getDescription");
        return response.getValue();
    }
}
