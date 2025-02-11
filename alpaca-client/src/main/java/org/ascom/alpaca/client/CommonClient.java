package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Common;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.*;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("unused")
public class CommonClient {
    private static final Logger log = LoggerFactory.getLogger(CommonClient.class);

    private final DeviceDescriptor deviceDescriptor;
    private final DeviceType deviceType;
    private final int clientID;
    private final URI serverURI;
    private Common client;

    public CommonClient(DeviceDescriptor deviceDescriptor, URI serverURI) {
        this(deviceDescriptor, serverURI, new Random().nextInt(Integer.MAX_VALUE));
    }

    public CommonClient(DeviceDescriptor deviceDescriptor, URI serverURI, int clientID) {
        this.deviceDescriptor = deviceDescriptor;
        this.deviceType = deviceDescriptor.getDeviceType();
        this.clientID = clientID;
        this.serverURI = serverURI;

        try {
            client = RestClientBuilder.newBuilder().baseUri(serverURI).build(Common.class);
        } catch (Exception e) {
            client = null;
            // Should probably be fatal
            log.warn("Problem constructing the client");
        }
    }

    protected URI getServerAddress() {
        return serverURI;
    }

    /**
     * Checks the response from the device server for error conditions.  Will throw the corresponding exception for
     * any error conditions returned by the server.
     */
    static protected void checkResponse(AlpacaResponse response) {
        switch (response.getErrorNumber()) {
            case 0 -> {
                // no error
            }
            case 1024 -> throw new PropertyNotImplementedException(response.getClientTransactionID(), response.getErrorMessage());
            case 1025 -> throw new InvalidValueException(response.getClientTransactionID(), response.getErrorMessage());
            case 1026 -> throw new ValueNotSetException(response.getClientTransactionID(), response.getErrorMessage());
            case 1031 -> throw new NotConnectedException(response.getClientTransactionID(), response.getErrorMessage());
            case 1032 -> throw new InvalidWhileParkedException(response.getClientTransactionID(), response.getErrorMessage());
            case 1033 -> throw new InvalidWhileSlavedException(response.getClientTransactionID(), response.getErrorMessage());
            case 1035 -> throw new InvalidOperationException(response.getClientTransactionID(), response.getErrorMessage());
            case 1036 -> throw new ActionNotImplementedException(response.getClientTransactionID(), response.getErrorMessage());
            default -> {
                log.warn("Received an unknown error type of {} with message: {}", response.getErrorNumber(), response.getErrorMessage());
                // TODO: define a new exception in this case
                throw new RuntimeException(response.getErrorMessage());
            }
        }
    }

   static  int getTransactionID() {
        return TransactionIDFactory.getClientTransactionID();
    }

    int getClientID() {
        return clientID;
    }

    public DeviceType getDeviceType() {
        return deviceDescriptor.getDeviceType();
    }

    public int getDeviceID() {
        return deviceDescriptor.getDeviceNumber();
    }

    public String executeAction(String action,
                              String parameters) {
        StringResponse response = client.executeAction(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID(), action, parameters);
        checkResponse(response);
        return response.getValue();
    }

    public Map<String, Object> getDeviceState() {
        return Map.of();
    }

    public boolean isConnecting() {
        BooleanResponse response = client.isConnecting(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void connect() {
        AlpacaResponse response = client.connect(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
    }

    public void disconnect() {
        AlpacaResponse response = client.disconnect(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
    }

    public boolean getConnectedState() {
        BooleanResponse response = client.isConnected(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setConnectedState(boolean state) {
        AlpacaResponse response = client.setConnectedState(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID(), state);
        checkResponse(response);
    }

    public String getDescription() {
        StringResponse response = client.getDescription(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public DeviceDescriptor getDeviceDescriptor() {
        return deviceDescriptor;
    }

    public String getDriverInfo() {
        StringResponse response = client.getDriverInfo(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public String getDriverVersion() {
        StringResponse response = client.getDriverVersion(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getInterfaceVersion() {
        IntResponse response = client.getInterfaceVersion(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public String getName() {
        StringResponse response = client.getName(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public List<String> getSupportedActions() {
        ListResponse<String> response = client.getSupportedActions(deviceType.getTypeName(), getDeviceID(), clientID, getTransactionID());
        checkResponse(response);
        return response.getValue();
    }
}
