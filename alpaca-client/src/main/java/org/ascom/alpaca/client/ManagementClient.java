package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Management;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.ListResponse;
import org.ascom.alpaca.model.ServerInfo;
import org.ascom.alpaca.response.ServerInfoResponse;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;
import java.util.Random;

@SuppressWarnings("unused")
public class ManagementClient {
    private static final Logger log = LoggerFactory.getLogger(ManagementClient.class);
    private final URI serverAddress;
    private final int clientID;
    private Management client = null;

    public ManagementClient(URI serverURI) {
        serverAddress = serverURI;
        clientID = new Random().nextInt(Integer.MAX_VALUE);
    }

    private Management getClient() {
        if (client == null) {
            try {
                client = RestClientBuilder.newBuilder()
                        .baseUri(serverAddress)
                        .build(Management.class);
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

    private void checkResponse(AlpacaResponse response) {
        CommonClient.checkResponse(response);
    }

    public int getClientID() {
        return clientID;
    }

    public List<Integer> getApiVersions() {
        ListResponse<Integer> response = getClient().getApiVersions(getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public List<DeviceDescriptor> getConfiguredDevices() {
        ListResponse<DeviceDescriptor> response = getClient().getConfiguredDevices(getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public ServerInfo getDescription() {
        ServerInfoResponse response = getClient().getDescription(getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }
}
