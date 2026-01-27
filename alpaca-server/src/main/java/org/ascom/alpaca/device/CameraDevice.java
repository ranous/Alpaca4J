package org.ascom.alpaca.device;

import org.ascom.alpaca.model.CameraState;
import org.ascom.alpaca.model.ImageArray;
import org.ascom.alpaca.model.SensorType;

import java.util.List;

/**
 * Interface for camera devices.  The full documentation of the device interface can be found in the
 * Alpaca documentation.
 * @see <a href="https://ascom-standards.org/api/#/Camera%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public interface CameraDevice extends Device {

    int getBayerOffsetX(int clientID);

    int getBayerOffsetY(int clientID);

    int getBinX(int clientID);

    void setBinX(int clientID, int binX);


    int getBinY(int clientID);

    void setBinY(int clientID, int binY);


    CameraState getCameraState(int clientID);

    int getCameraXSize(int clientID);

    int getCameraYSize(int clientID);

    boolean canAbortExposure(int clientID);

    boolean canAsymmetricBin(int clientID);

    boolean canFastReadout(int clientID);

    boolean canGetCoolerPower(int clientID);

    boolean canPulseGuide(int clientID);

    boolean canSetCCDTemperature(int clientID);

    boolean canStopExposure(int clientID);

    double getCCDTemperature(int clientID);

    boolean isCoolerOn(int clientID);

    void setCoolerOn(int clientID, boolean coolerOn);

    int getCoolerPower(int clientID);

    double getElectronsPerADU(int clientID);

    double getExposureMax(int clientID);

    double getExposureMin(int clientID);

    double getExposureResolution(int clientID);

    boolean getFastReadout(int clientID);

    void setFastReadout(int clientID, boolean fastReadout);

    double getFullWellCapacity(int clientID);

    int getGain(int clientID);

    void setGain(int clientID, int gain);

    int getGainMax(int clientID);

    int getGainMin(int clientID);

    List<String> getGains(int clientID);

    boolean hasShutter(int clientID);

    double getHeatSinkTemperature(int clientID);

    ImageArray getImageArray(int clientID);

    ImageArray getImageArrayVariant(int clientID);

    boolean isImageReady(int clientID);

    boolean isPulseGuiding(int clientID);

    double getLastExposureDuration(int clientID);

    String getLastExposureStartTime(int clientID);

    int getMaxADU(int clientID);

    int getMaxBinX(int clientID);

    int getMaxBinY(int clientID);

    int getNumX(int clientID);

    void setNumX(int clientID, int numX);

    int getNumY(int clientID);

    void setNumY(int clientID, int numY);

    int getOffset(int clientID);

    void setOffset(int clientID, int offset);

    int getOffsetMax(int clientID);

    int getOffsetMin(int clientID);

    List<String> getOffsets(int clientID);

    int getPercentCompleted(int clientID);

    double getPixelSizeX(int clientID);

    double getPixelSizeY(int clientID);

    int getReadoutMode(int clientID);

    void setReadoutMode(int clientID, int readoutMode);

    List<String> getReadoutModes(int clientID);

    String getSensorName(int clientID);

    SensorType getSensorType(int clientID);

    double getSetCCDTemperature(int clientID);

    void setCCDTemperature(int clientID, double setCCDTemperature);

    int getStartX(int clientID);

    void setStartX(int clientID, int startX);

    int getStartY(int clientID);

    void setStartY(int clientID, int startY);

    double getSubExposureDuration(int clientID);

    void setSubExposureDuration(int clientID, double subExposureDuration);

    void abortExposure(int clientID);

    void pulseGuide(int clientID, int direction, int duration);

    void startExposure(int clientID, double duration, boolean light);

    void stopExposure(int clientID);
}
