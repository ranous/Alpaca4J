package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.ObservingConditions;
import org.ascom.alpaca.device.ObservingConditionsDevice;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.StringResponse;
import org.ascom.alpaca.response.InvalidValueException;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ObservingConditionsResource implements ObservingConditions {
    @Inject
    DeviceManager deviceManager;

    // This version will check if the client has a valid connection before returning the device
    private ObservingConditionsDevice getDevice(int deviceID, int clientID) {
        ObservingConditionsDevice device = deviceManager.getDevice(deviceID, DeviceType.ObservingConditions);
        device.checkConnectionStatus(clientID);
        return device;
    }

    public DoubleResponse getAveragePeriod(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAveragePeriod(clientID));
    }

    public AlpacaResponse setAveragePeriod(int deviceNumber,
                                           double averagePeriod,
                                           int clientID,
                                           long clientTransactionID) {
        if (averagePeriod < 0) {
            throw new InvalidValueException("The average period must be greater than 0");
        }
        getDevice(deviceNumber, clientID).setAveragePeriod(clientID, averagePeriod);
        return new AlpacaResponse(clientTransactionID);
    }

    public DoubleResponse getCloudCover(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getCloudCover(clientID));
    }

    public DoubleResponse getDewPoint(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getDewPoint(clientID));
    }

    public DoubleResponse getHumidity(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getHumidity(clientID));
    }

    public DoubleResponse getPressure(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPressure(clientID));
    }

    public DoubleResponse getRainRate(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getRainRate(clientID));
    }

    public DoubleResponse getSkyBrightness(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSkyBrightness(clientID));
    }

    public DoubleResponse getSkyQuality(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSkyQuality(clientID));
    }

    public DoubleResponse getSkyTemperature(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSkyTemperature(clientID));
    }

    public DoubleResponse getStarFWHM(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getStarFWHM(clientID));
    }

    public DoubleResponse getTemperature(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTemperature(clientID));
    }

    public DoubleResponse getWindDirection(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getWindDirection(clientID));
    }

    public DoubleResponse getWindGust(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getWindGust(clientID));
    }

    public DoubleResponse getWindSpeed(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getWindSpeed(clientID));
    }

    public AlpacaResponse refresh(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        getDevice(deviceNumber, clientID).refresh(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public StringResponse getSensorDescription(int deviceNumber,
                                               String sensorName,
                                               int clientID,
                                               long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getSensorDescription(clientID, sensorName));
    }

    public DoubleResponse getTimeSinceLastUpdate(int deviceNumber,
                                                 String sensorName,
                                                 int clientID,
                                                 long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTimeSinceLastUpdate(clientID, sensorName));
    }
}
