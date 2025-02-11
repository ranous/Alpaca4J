package org.ascom.alpaca.client;

import org.ascom.alpaca.api.ObservingConditions;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.StringResponse;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class ObservingConditionsClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(ObservingConditionsClient.class);
    private final URI serverAddress;
    private ObservingConditions client = null;

    public ObservingConditionsClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public ObservingConditionsClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private ObservingConditions getClient() {
        if (client == null) {
            try {
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(ObservingConditions.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for ObservingConditions - " + e.getMessage());
            }
        }
        return client;
    }

    public double getAveragePeriod() {
        DoubleResponse response = getClient().getAveragePeriod(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setAveragePeriod(double averagePeriod) {
        AlpacaResponse response = getClient().setAveragePeriod(getDeviceID(), averagePeriod, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public double getCloudCover() {
        DoubleResponse response = getClient().getCloudCover(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getDewPoint() {
        DoubleResponse response = getClient().getDewPoint(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getHumidity() {
        DoubleResponse response = getClient().getHumidity(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getPressure() {
        DoubleResponse response = getClient().getPressure(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getRainRate() {
        DoubleResponse response = getClient().getRainRate(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getSkyBrightness() {
        DoubleResponse response = getClient().getSkyBrightness(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getSkyQuality() {
        DoubleResponse response = getClient().getSkyQuality(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getSkyTemperature() {
        DoubleResponse response = getClient().getSkyTemperature(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getStarFWHM() {
        DoubleResponse response = getClient().getStarFWHM(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getTemperature() {
        DoubleResponse response = getClient().getTemperature(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getWindDirection() {
        DoubleResponse response = getClient().getWindDirection(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getWindGust() {
        DoubleResponse response = getClient().getWindGust(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getWindSpeed() {
        DoubleResponse response = getClient().getWindSpeed(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void refresh() {
        AlpacaResponse response = getClient().refresh(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public String getSensorDescription(String sensorName) {
        StringResponse response = getClient().getSensorDescription(getDeviceID(), sensorName, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getTimeSinceLastUpdate(String sensorName) {
        DoubleResponse response = getClient().getTimeSinceLastUpdate(getDeviceID(), sensorName, getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }
}
