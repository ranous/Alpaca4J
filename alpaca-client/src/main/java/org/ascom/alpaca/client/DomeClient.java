package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Dome;
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
public class DomeClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(DomeClient.class);
    private final URI serverAddress;
    private Dome client = null;

    public DomeClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public DomeClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Dome getClient() {
        if (client == null) {
            try {
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(Dome.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Dome - " + e.getMessage());
            }
        }
        return client;
    }

    public double getAltitude() {
        DoubleResponse response = getClient().getAltitude(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean atHome() {
        BooleanResponse response = getClient().atHome(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean atPark() {
        BooleanResponse response = getClient().atPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getAzimuth() {
        DoubleResponse response = getClient().getAzimuth(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canFindHome() {
        BooleanResponse response = getClient().canFindHome(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canPark() {
        BooleanResponse response = getClient().canPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetAltitude() {
        BooleanResponse response = getClient().canSetAltitude(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetAzimuth() {
        BooleanResponse response = getClient().canSetAzimuth(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetPark() {
        BooleanResponse response = getClient().canSetPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetShutter() {
        BooleanResponse response = getClient().canSetShutter(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSlave() {
        BooleanResponse response = getClient().canSlave(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSyncAzimuth() {
        BooleanResponse response = getClient().canSyncAzimuth(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public ShutterState getShutterStatus() {
        IntResponse response = getClient().getShutterStatus(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return ShutterState.fromState(response.getValue());
    }

    public boolean isSlaved() {
        BooleanResponse response = getClient().atPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setSlaved(boolean slaved) {
        AlpacaResponse response = getClient().setSlaved(getDeviceID(), getClientID(), getTransactionID(), slaved);
        checkResponse(response);
    }

    public boolean isSlewing() {
        BooleanResponse response = getClient().atPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void abortSlew() {
        AlpacaResponse response = getClient().abortSlew(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void closeShutter() {
        AlpacaResponse response = getClient().closeShutter(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void findHome() {
        AlpacaResponse response = getClient().findHome(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void openShutter() {
        AlpacaResponse response = getClient().openShutter(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void park() {
        AlpacaResponse response = getClient().park(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void setPark() {
        AlpacaResponse response = getClient().setPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void slewToAltitude(double altitude) {
        AlpacaResponse response = getClient().slewToAltitude(getDeviceID(), getClientID(), getTransactionID(), altitude);
        checkResponse(response);
    }

    public void slewToAzimuth(double azimuth) {
        AlpacaResponse response = getClient().slewToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth);
        checkResponse(response);
    }

    public void syncToAzimuth(double azimuth) {
        AlpacaResponse response = getClient().syncToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth);
        checkResponse(response);
    }
}
