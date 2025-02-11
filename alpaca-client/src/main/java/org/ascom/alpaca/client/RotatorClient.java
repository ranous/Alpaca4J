package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Rotator;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.DoubleResponse;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class RotatorClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(RotatorClient.class);
    private final URI serverAddress;
    private Rotator client = null;

    public RotatorClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public RotatorClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Rotator getClient() {
        if (client == null) {
            try {
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(Rotator.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Rotator - " + e.getMessage());
            }
        }
        return client;
    }

    public boolean canReverse() {
        BooleanResponse response = getClient().canReverse(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isMoving() {
        BooleanResponse response = getClient().isMoving(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getMechanicalPosition() {
        DoubleResponse response = getClient().getMechanicalPosition(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getPosition() {
        DoubleResponse response = getClient().getPosition(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isReversed() {
        BooleanResponse response = getClient().isReversed(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setReversed(boolean reversed) {
        AlpacaResponse response = getClient().setReversed(getDeviceID(), getClientID(), getTransactionID(), reversed);
        checkResponse(response);
    }

    public double getStepSize() {
        DoubleResponse response = getClient().getStepSize(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getTargetPosition() {
        DoubleResponse response = getClient().getTargetPosition(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void halt() {
        AlpacaResponse response = getClient().halt(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void move(double position) {
        AlpacaResponse response = getClient().move(getDeviceID(), getClientID(), getTransactionID(), position);
        checkResponse(response);
    }

    public void moveAbsolute(double position) {
        AlpacaResponse response = getClient().moveAbsolute(getDeviceID(), getClientID(), getTransactionID(), position);
        checkResponse(response);
    }

    public void moveMechanical(double position) {
        AlpacaResponse response = getClient().moveMechanical(getDeviceID(), getClientID(), getTransactionID(), position);
        checkResponse(response);
    }

    public void sync(double position) {
        AlpacaResponse response = getClient().sync(getDeviceID(), getClientID(), getTransactionID(), position);
        checkResponse(response);
    }
}
