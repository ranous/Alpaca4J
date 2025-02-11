package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.Dome;
import org.ascom.alpaca.device.DomeDevice;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DomeResource implements Dome {
    @Inject
    DeviceManager deviceManager;

    private DomeDevice getDevice(int deviceID, int clientID) {
        DomeDevice device = deviceManager.getDevice(deviceID, DeviceType.Dome);
        device.checkConnectionStatus(clientID);
        return device;
    }

    public DoubleResponse getAltitude(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAltitude(clientID));
    }

    public BooleanResponse atHome(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).atHome(clientID));
    }

    public BooleanResponse atPark(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).atPark(clientID));
    }

    public DoubleResponse getAzimuth(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAzimuth(clientID));
    }

    public BooleanResponse canFindHome(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canFindHome(clientID));
    }

    public BooleanResponse canPark(int deviceNumber,
                                   int clientID,
                                   long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPark(clientID));
    }

    public BooleanResponse canSetAltitude(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetAltitude(clientID));
    }

    public BooleanResponse canSetAzimuth(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetAzimuth(clientID));
    }

    public BooleanResponse canSetPark(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetPark(clientID));
    }

    public BooleanResponse canSetShutter(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetShutter(clientID));
    }

    public BooleanResponse canSlave(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlave(clientID));
    }

    public BooleanResponse canSyncAzimuth(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSyncAzimuth(clientID));
    }

    public IntResponse getShutterStatus(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getShutterStatus(clientID).ordinal());
    }

    public BooleanResponse isSlaved(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isSlaved(clientID));
    }

    public AlpacaResponse setSlaved(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID,
                                    boolean slaved) {
        getDevice(deviceNumber, clientID).setSlaved(clientID, slaved);
        return new AlpacaResponse(clientTransactionID);
    }

    public BooleanResponse isSlewing(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isSlewing(clientID));
    }

    public AlpacaResponse abortSlew(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        getDevice(deviceNumber, clientID).abortSlew(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse closeShutter(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        getDevice(deviceNumber, clientID).closeShutter(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse findHome(int deviceNumber,
                                   int clientID,
                                   long clientTransactionID) {
        getDevice(deviceNumber, clientID).findHome(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse openShutter(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        getDevice(deviceNumber, clientID).openShutter(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse park(int deviceNumber,
                               int clientID,
                               long clientTransactionID) {
        getDevice(deviceNumber, clientID).park(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse setPark(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        getDevice(deviceNumber, clientID).setPark(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse slewToAltitude(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID,
                                         double altitude) {
        if (altitude < 0 || altitude > 90) {
            throw new InvalidValueException("Invalid altitude, must be no less than zero or greater than 90");
        }
        getDevice(deviceNumber, clientID).slewToAltitude(clientID, altitude);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse slewToAzimuth(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID,
                                        double azimuth) {
        if (azimuth < 0 || azimuth > 360) {
            throw new InvalidValueException("Invalid azimuth, must be no less than zero or greater than 360");
        }
        getDevice(deviceNumber, clientID).slewToAzimuth(clientID, azimuth);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse syncToAzimuth(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID,
                                        double azimuth) {
        if (azimuth < 0 || azimuth > 360) {
            throw new InvalidValueException("Invalid azimuth, must be no less than zero or greater than 360");
        }
        getDevice(deviceNumber, clientID).syncToAzimuth(clientID, azimuth);
        return new AlpacaResponse(clientTransactionID);
    }
}
