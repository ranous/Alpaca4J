package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.Camera;
import org.ascom.alpaca.device.CameraDevice;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CameraResource implements Camera {
    @Inject
    DeviceManager deviceManager;

    private CameraDevice getDevice(int deviceID, int clientID) {
        CameraDevice device = deviceManager.getDevice(deviceID, DeviceType.Camera);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @Override
    public IntResponse getBayerOffsetX(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBayerOffsetX(clientID));
    }

    @Override
    public IntResponse getBayerOffsetY(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBayerOffsetY(clientID));
    }

    @Override
    public IntResponse getBinX(int deviceNumber,
                               int clientID,
                               long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBinX(clientID));
    }

    @Override
    public AlpacaResponse setBinX(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID,
                                  int binX) {
        if (binX < 1 || binX > getDevice(deviceNumber, clientID).getMaxBinX(clientID)) {
            throw new InvalidValueException("The bin x value out of supported range");
        }
        getDevice(deviceNumber, clientID).setBinX(clientID, binX);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getBinY(int deviceNumber,
                               int clientID,
                               long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBinY(clientID));
    }

    @Override
    public AlpacaResponse setBinY(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID,
                                  int binY) {
        if (binY < 1 || binY > getDevice(deviceNumber, clientID).getMaxBinX(clientID)) {
            throw new InvalidValueException("The bin x value out of supported range");
        }
        getDevice(deviceNumber, clientID).setBinY(clientID, binY);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getCameraState(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraState(clientID).ordinal());
    }

    @Override
    public IntResponse getCameraXSize(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraXSize(clientID));
    }

    @Override
    public IntResponse getCameraYSize(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCameraYSize(clientID));
    }

    @Override
    public BooleanResponse canAbortExposure(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canAbortExposure(clientID));
    }

    @Override
    public BooleanResponse canAsymmetricBin(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canAsymmetricBin(clientID));
    }

    @Override
    public BooleanResponse canFastReadout(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canFastReadout(clientID));
    }

    @Override
    public BooleanResponse canGetCoolerPower(int deviceNumber,
                                             int clientID,
                                             long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canGetCoolerPower(clientID));
    }

    @Override
    public BooleanResponse canPulseGuide(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPulseGuide(clientID));
    }

    @Override
    public BooleanResponse canSetCCDTemperature(int deviceNumber,
                                                int clientID,
                                                long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetCCDTemperature(clientID));
    }

    @Override
    public BooleanResponse canStopExposure(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canStopExposure(clientID));
    }

    @Override
    public DoubleResponse getCCDTemperature(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getCCDTemperature(clientID));
    }

    @Override
    public BooleanResponse isCoolerOn(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isCoolerOn(clientID));
    }

    @Override
    public AlpacaResponse setCoolerOn(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID,
                                      boolean coolerOn) {
        getDevice(deviceNumber, clientID).setCoolerOn(clientID, coolerOn);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getCoolerPower(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getCoolerPower(clientID));
    }

    @Override
    public DoubleResponse getElectronsPerADU(int deviceNumber,
                                             int clientID,
                                             long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getElectronsPerADU(clientID));
    }

    @Override
    public DoubleResponse getExposureMax(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureMax(clientID));
    }

    @Override
    public DoubleResponse getExposureMin(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureMin(clientID));
    }

    @Override
    public DoubleResponse getExposureResolution(int deviceNumber,
                                                int clientID,
                                                long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getExposureResolution(clientID));
    }

    @Override
    public BooleanResponse getFastReadout(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).getFastReadout(clientID));
    }

    @Override
    public AlpacaResponse setFastReadout(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID,
                                         boolean fastReadout) {
        getDevice(deviceNumber, clientID).setFastReadout(clientID, fastReadout);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public DoubleResponse getFullWellCapacity(int deviceNumber,
                                              int clientID,
                                              long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getFullWellCapacity(clientID));
    }

    @Override
    public IntResponse getGain(int deviceNumber,
                               int clientID,
                               long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGain(clientID));
    }

    @Override
    public AlpacaResponse setGain(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID,
                                  int gain) {
        getDevice(deviceNumber, clientID).setGain(clientID, gain);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getGainMax(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGainMax(clientID));
    }

    @Override
    public IntResponse getGainMin(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getGainMin(clientID));
    }

    @Override
    public ListResponse<String> getGains(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getGains(clientID));
    }

    @Override
    public BooleanResponse hasShutter(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).hasShutter(clientID));
    }

    @Override
    public DoubleResponse getHeatSinkTemperature(int deviceNumber,
                                                 int clientID,
                                                 long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getHeatSinkTemperature(clientID));
    }

    @Override
    public ImageArrayResponse getImageArray(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        ImageArray image = getDevice(deviceNumber, clientID).getImageArray(clientID);
        return new ImageArrayResponse(clientTransactionID, image);
    }

    @Override
    public ImageArrayResponse getImageArrayVariant(int deviceNumber,
                                                   int clientID,
                                                   long clientTransactionID) {
        ImageArray image = getDevice(deviceNumber, clientID).getImageArrayVariant(clientID);
        return new ImageArrayResponse(clientTransactionID, image);
    }

    @Override
    public BooleanResponse isImageReady(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isImageReady(clientID));
    }

    @Override
    public BooleanResponse isPulseGuiding(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isPulseGuiding(clientID));
    }

    @Override
    public DoubleResponse getLastExposureDuration(int deviceNumber,
                                                  int clientID,
                                                  long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getLastExposureDuration(clientID));
    }

    @Override
    public StringResponse getLastExposureStartTime(int deviceNumber,
                                                   int clientID,
                                                   long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getLastExposureStartTime(clientID));
    }

    @Override
    public IntResponse getMaxADU(int deviceNumber,
                                 int clientID,
                                 long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxADU(clientID));
    }

    @Override
    public IntResponse getMaxBinX(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxBinX(clientID));
    }

    @Override
    public IntResponse getMaxBinY(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxBinY(clientID));
    }

    @Override
    public IntResponse getNumX(int deviceNumber,
                               int clientID,
                               long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getNumX(clientID));
    }

    @Override
    public AlpacaResponse setNumX(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID,
                                  int numX) {
        getDevice(deviceNumber, clientID).setNumX(clientID, numX);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getNumY(int deviceNumber,
                               int clientID,
                               long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getNumY(clientID));
    }

    @Override
    public AlpacaResponse setNumY(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID,
                                  int numY) {
        getDevice(deviceNumber, clientID).setNumY(clientID, numY);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getOffset(int deviceNumber,
                                 int clientID,
                                 long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffset(clientID));
    }

    @Override
    public AlpacaResponse setOffset(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID,
                                    int offset) {
        getDevice(deviceNumber, clientID).setOffset(clientID, offset);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getOffsetMax(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffsetMax(clientID));
    }

    @Override
    public IntResponse getOffsetMin(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getOffsetMin(clientID));
    }

    @Override
    public ListResponse<String> getOffsets(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getOffsets(clientID));
    }

    @Override
    public IntResponse getPercentCompleted(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getPercentCompleted(clientID));
    }

    @Override
    public DoubleResponse getPixelSizeX(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPixelSizeX(clientID));
    }

    @Override
    public DoubleResponse getPixelSizeY(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPixelSizeY(clientID));
    }

    @Override
    public IntResponse getReadoutMode(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getReadoutMode(clientID));
    }

    @Override
    public AlpacaResponse setReadoutMode(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID,
                                         int readoutMode) {
        getDevice(deviceNumber, clientID).setReadoutMode(clientID, readoutMode);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public ListResponse<String> getReadoutModes(int deviceNumber,
                                              int clientID,
                                              long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getReadoutModes(clientID));
    }

    @Override
    public StringResponse getSensorName(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getSensorName(clientID));
    }

    @Override
    public IntResponse getSensorType(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getSensorType(clientID).getType());
    }

    @Override
    public DoubleResponse getSetCCDTemperature(int deviceNumber,
                                               int clientID,
                                               long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSetCCDTemperature(clientID));
    }

    @Override
    public AlpacaResponse setCCDTemperature(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID,
                                            double setCCDTemperature) {
        getDevice(deviceNumber, clientID).setCCDTemperature(clientID, setCCDTemperature);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getStartX(int deviceNumber,
                                 int clientID,
                                 long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getStartX(clientID));
    }

    @Override
    public AlpacaResponse setStartX(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID,
                                    int startX) {
        getDevice(deviceNumber, clientID).setStartX(clientID, startX);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getStartY(int deviceNumber,
                                 int clientID,
                                 long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getStartY(clientID));
    }

    @Override
    public AlpacaResponse setStartY(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID,
                                    int startY) {
        getDevice(deviceNumber, clientID).setStartY(clientID, startY);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public DoubleResponse getSubExposureDuration(int deviceNumber,
                                                 int clientID,
                                                 long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSubExposureDuration(clientID));
    }

    @Override
    public AlpacaResponse setSubExposureDuration(int deviceNumber,
                                                 int clientID,
                                                 long clientTransactionID,
                                                 double subExposureDuration) {
        getDevice(deviceNumber, clientID).setSubExposureDuration(clientID, subExposureDuration);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse abortExposure(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        getDevice(deviceNumber, clientID).abortExposure(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse pulseGuide(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID,
                                     int direction,
                                     int duration) {
        getDevice(deviceNumber, clientID).pulseGuide(clientID, direction, duration);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse startExposure(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID,
                                        int duration,
                                        boolean light) {
        getDevice(deviceNumber, clientID).startExposure(clientID, duration, light);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse stopExposure(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        getDevice(deviceNumber, clientID).stopExposure(clientID);
        return new AlpacaResponse(clientTransactionID);
    }
}
