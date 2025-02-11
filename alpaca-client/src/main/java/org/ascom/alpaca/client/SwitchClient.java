package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Switch;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class SwitchClient extends CommonClient  {
    private static final Logger log = LoggerFactory.getLogger(SwitchClient.class);
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
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(Switch.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Switch - " + e.getMessage());
            }
        }
        return client;
    }

    public int getMaxSwitch() {
        IntResponse response = getClient().getMaxSwitch(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canWrite(int switchID) {
        BooleanResponse response = getClient().canWrite(getDeviceID(), switchID, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean getSwitchState(int switchID) {
        BooleanResponse response = getClient().getSwitchState(getDeviceID(), switchID, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public String getSwitchDescription(int switchID) {
        StringResponse response = getClient().getSwitchDescription(getDeviceID(), switchID, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public String getSwitchName(int switchID) {
        StringResponse response = getClient().getSwitchName(getDeviceID(), switchID, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getSwitchValue(int switchID) {
        DoubleResponse response = getClient().getSwitchValue(getDeviceID(), switchID, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getMinSwitchValue(int switchID) {
        DoubleResponse response = getClient().getMinSwitchValue(getDeviceID(), switchID, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getMaxSwitchValue(int switchID) {
        DoubleResponse response = getClient().getMaxSwitchValue(getDeviceID(), switchID, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void cancelAsync(int switchID, boolean state) {
        AlpacaResponse response = getClient().setSwitchState(getDeviceID(), switchID, state, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void setSwitchState(int switchID, boolean state) {
        AlpacaResponse response = getClient().setSwitchState(getDeviceID(), switchID, state, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void setSwitchName(int switchID, String switchName) {
        AlpacaResponse response = getClient().setSwitchName(getDeviceID(), switchID, switchName, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void setSwitchValue(int switchID, double value) {
        AlpacaResponse response = getClient().setSwitchValue(getDeviceID(), switchID, value, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public double getSwitchStep(int switchID) {
        DoubleResponse response = getClient().getSwitchStep(getDeviceID(), switchID, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }
}
