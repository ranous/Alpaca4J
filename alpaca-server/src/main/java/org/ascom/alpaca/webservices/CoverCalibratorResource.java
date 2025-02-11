package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.CoverCalibrator;
import org.ascom.alpaca.device.CoverCalibratorDevice;
import org.ascom.alpaca.model.CalibratorState;
import org.ascom.alpaca.model.CoverState;
import org.ascom.alpaca.response.*;
import org.ascom.alpaca.model.DeviceType;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CoverCalibratorResource implements CoverCalibrator {
    //private DeviceManager deviceManager = DeviceManager.getDeviceManager();
    @Inject
    DeviceManager deviceManager;

    private CoverCalibratorDevice getDevice(int deviceID, int clientID) {
        CoverCalibratorDevice device = deviceManager.getDevice(deviceID, DeviceType.CoverCalibrator);
        device.checkConnectionStatus(clientID);
        return device;
    }

    public IntResponse getBrightness(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBrightness(clientID));
    }

    @Override
    public BooleanResponse isCalibratorChanging(int deviceNumber,
                                                int clientID,
                                                long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isCalibratorChanging(clientID));
    }

    public ValueResponse<CalibratorState> getCalibratorState(int deviceNumber,
                                                             int clientID,
                                                             long clientTransactionID) {
        return new ValueResponse<>(getDevice(deviceNumber, clientID).getCalibratorState(clientID));
    }

    @Override
    public BooleanResponse isCoverMoving(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isCoverMoving(clientID));
    }

    public ValueResponse<CoverState> getCoverState(int deviceNumber,
                                                   int clientID,
                                                   long clientTransactionID) {
        return new ValueResponse<>(getDevice(deviceNumber, clientID).getCoverState(clientID));
    }

    public IntResponse getMaxBrightness(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxBrightness(clientID));
    }

    public AlpacaResponse turnCalibratorOff(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        getDevice(deviceNumber, clientID).turnCalibratorOff(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse turnCalibratorOn(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID,
                                           int brightness) {
        if (brightness < 0) {
            throw new InvalidValueException("Invalid altitude, must be no less than zero or greater than 90");
        }
        getDevice(deviceNumber, clientID).turnCalibratorOn(clientID, brightness);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse closeCover(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        getDevice(deviceNumber, clientID).closeCover(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse haltCover(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        getDevice(deviceNumber, clientID).haltCover(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse openCover(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        getDevice(deviceNumber, clientID).openCover(clientID);
        return new AlpacaResponse(clientTransactionID);
    }
}
