package org.ascom.alpaca.client;

import org.ascom.alpaca.api.FilterWheel;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

@SuppressWarnings("unused")
public class FilterWheelClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(FilterWheelClient.class);
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
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(FilterWheel.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Switch - " + e.getMessage());
            }
        }
        return client;
    }

    public List<Integer> getFocusOffsets() {
        ListResponse<Integer> response = getClient().getFocusOffsets(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public List<String> getFilterNames() {
        ListResponse<String> response = getClient().getFilterNames(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getPosition() {
        IntResponse response = getClient().getPosition(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setPosition(int position) {
        AlpacaResponse response = getClient().setPosition(getDeviceID(), getClientID(), getTransactionID(), position);
        checkResponse(response);
    }
}
