package org.ascom.alpaca.client;

import org.ascom.alpaca.api.SafetyMonitor;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(SafetyMonitor.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for SafetyMonitor - " + e.getMessage());
            }
        }
        return client;
    }

    public boolean isSafe() {
        BooleanResponse response = getClient().isSafe(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }
}
