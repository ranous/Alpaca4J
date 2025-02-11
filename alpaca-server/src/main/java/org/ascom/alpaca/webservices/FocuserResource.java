package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.Focuser;
import org.ascom.alpaca.device.FocuserDevice;
import org.ascom.alpaca.model.*;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;

@Singleton
public class FocuserResource implements Focuser {
    //private DeviceManager deviceManager = DeviceManager.getDeviceManager();
    @Inject
    DeviceManager deviceManager;

    private FocuserDevice getDevice(int deviceID, int clientID) {
        FocuserDevice device = deviceManager.getDevice(deviceID, DeviceType.Focuser);
        device.checkConnectionStatus(clientID);
        return device;
    }

    public BooleanResponse canAbsoluteFocus(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canAbsoluteFocus(clientID));
    }

    public BooleanResponse isMoving(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isMoving(clientID));
    }

    public IntResponse getMaxIncrement(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxIncrement(clientID));
    }

    public IntResponse getMaxStep(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxStep(clientID));
    }

    public IntResponse getPosition(int deviceNumber,
                                   int clientID,
                                   long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getPosition(clientID));
    }

    public DoubleResponse getStepSize(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getStepSize(clientID));
    }

    public BooleanResponse isTemperatureCompensating(int deviceNumber,
                                                     int clientID,
                                                     long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isTemperatureCompensating(clientID));
    }

    public AlpacaResponse setTemperatureCompensation(int deviceNumber,
                                                     boolean tempCompState,
                                                     int clientID,
                                                     long clientTransactionID) {
        getDevice(deviceNumber, clientID).setTemperatureCompensation(clientID, tempCompState);
        return new AlpacaResponse(clientTransactionID);
    }

    public BooleanResponse hasTemperatureCompensation(int deviceNumber,
                                                      int clientID,
                                                      long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).hasTemperatureCompensation(clientID));
    }

    public DoubleResponse getTemperature(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTemperature(clientID));
    }

    public AlpacaResponse haltFocuser(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        getDevice(deviceNumber, clientID).haltFocuser(clientID);
        return new AlpacaResponse(clientTransactionID);

    }

    public AlpacaResponse moveToPosition(int deviceNumber,
                                         int position,
                                         int clientID,
                                         long clientTransactionID) {
        getDevice(deviceNumber, clientID).moveToPosition(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }
}
