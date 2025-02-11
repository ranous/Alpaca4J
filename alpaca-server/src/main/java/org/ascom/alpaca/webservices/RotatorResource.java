package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.Rotator;
import org.ascom.alpaca.device.RotatorDevice;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.InvalidValueException;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class RotatorResource implements Rotator {
    @Inject
    DeviceManager deviceManager;

    private RotatorDevice getDevice(int deviceID, int clientID) {
        RotatorDevice device = deviceManager.getDevice(deviceID, DeviceType.Rotator);
        device.checkConnectionStatus(clientID);
        return device;
    }

    public BooleanResponse canReverse(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canReverse(clientID));
    }

    public BooleanResponse isMoving(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isMoving(clientID));
    }

    public DoubleResponse getMechanicalPosition(int deviceNumber,
                                                int clientID,
                                                long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getMechanicalPosition(clientID));
    }

    public DoubleResponse getPosition(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPosition(clientID));
    }

    public BooleanResponse isReversed(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isReversed(clientID));
    }

    public AlpacaResponse setReversed(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID,
                                      boolean reversed) {
        getDevice(deviceNumber, clientID).setReversed(clientID, reversed);
        return new AlpacaResponse(clientTransactionID);
    }

    public DoubleResponse getStepSize(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getStepSize(clientID));
    }

    public DoubleResponse getTargetPosition(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTargetPosition(clientID));
    }

    public AlpacaResponse halt(int deviceNumber,
                               int clientID,
                               long clientTransactionID) {
        getDevice(deviceNumber, clientID).halt(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse move(int deviceNumber,
                               int clientID,
                               long clientTransactionID,
                               double position) {
        if (position > 360) {
            throw new InvalidValueException("The position cannot be moved more than 360 degrees");
        }
        getDevice(deviceNumber, clientID).move(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse moveAbsolute(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID,
                                       double position) {
        if (position < 0 || position> 360) {
            throw new InvalidValueException("Invalid position, must be no less than zero or greater than 360");
        }

        getDevice(deviceNumber, clientID).moveAbsolute(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse moveMechanical(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID,
                                         double position) {
        if (position < 0 || position> 360) {
            throw new InvalidValueException("Invalid position, must be no less than zero or greater than 360");
        }
        getDevice(deviceNumber, clientID).moveMechanical(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse sync(int deviceNumber,
                               int clientID,
                               long clientTransactionID,
                               double position) {
        if (position < 0 || position> 360) {
            throw new InvalidValueException("Invalid position, must be no less than zero or greater than 360");
        }
       getDevice(deviceNumber, clientID).sync(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }
}
