package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Focuser;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class FocuserClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(FocuserClient.class);
    private final URI serverAddress;
    private Focuser client = null;

    public FocuserClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public FocuserClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Focuser getClient() {
        if (client == null) {
            try {
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(Focuser.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Focuser - " + e.getMessage());
            }
        }
        return client;
    }

    public boolean canAbsoluteFocus() {
        BooleanResponse response = getClient().canAbsoluteFocus(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isMoving() {
        BooleanResponse response = getClient().isMoving(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getMaxIncrement() {
        IntResponse response = getClient().getMaxIncrement(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getMaxStep() {
        IntResponse response = getClient().getMaxStep(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getPosition() {
        IntResponse response = getClient().getPosition(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getStepSize() {
        DoubleResponse response = getClient().getStepSize(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isTemperatureCompensating() {
        BooleanResponse response = getClient().isTemperatureCompensating(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setTemperatureCompensation(boolean state) {
        AlpacaResponse response = getClient().setTemperatureCompensation(getDeviceID(), state, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public boolean hasTemperatureCompensation() {
        BooleanResponse response = getClient().hasTemperatureCompensation(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getTemperature() {
        DoubleResponse response = getClient().getStepSize(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void haltFocuser() {
        AlpacaResponse response = getClient().haltFocuser(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void moveToPosition(int position) {
        AlpacaResponse response = getClient().moveToPosition(getDeviceID(), position, getClientID(), getTransactionID());
        checkResponse(response);
    }
}
