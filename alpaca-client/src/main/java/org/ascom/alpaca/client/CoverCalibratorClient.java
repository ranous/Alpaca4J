package org.ascom.alpaca.client;

import org.ascom.alpaca.api.CoverCalibrator;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.ValueResponse;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class CoverCalibratorClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(CoverCalibratorClient.class);
    private final URI serverAddress;
    private CoverCalibrator client = null;

    public CoverCalibratorClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public CoverCalibratorClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private CoverCalibrator getClient() {
        if (client == null) {
            try {
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(CoverCalibrator.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for CoverCalibrator - " + e.getMessage());
            }
        }
        return client;
    }

    public int getBrightness() {
        IntResponse response = getClient().getBrightness(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isCalibratorChanging() {
        BooleanResponse response = getClient().isCalibratorChanging(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public CalibratorState getCalibratorState() {
        ValueResponse<CalibratorState> response = getClient().getCalibratorState(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isCoverMoving() {
        BooleanResponse response = getClient().isCoverMoving(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public CoverState getCoverState() {
        ValueResponse<CoverState> response = getClient().getCoverState(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getMaxBrightness() {
        IntResponse response = getClient().getMaxBrightness(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void turnCalibratorOff() {
        AlpacaResponse response = getClient().turnCalibratorOff(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void turnCalibratorOn(int brightness) {
        AlpacaResponse response = getClient().turnCalibratorOn(getDeviceID(), getClientID(), getTransactionID(), brightness);
        checkResponse(response);
    }

    public void closeCover() {
        AlpacaResponse response = getClient().closeCover(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void haltCover() {
        AlpacaResponse response = getClient().haltCover(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void openCover() {
        AlpacaResponse response = getClient().openCover(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }
}
