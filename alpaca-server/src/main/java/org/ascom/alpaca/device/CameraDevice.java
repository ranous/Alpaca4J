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

    int getBayerOffsetX();

    int getBayerOffsetY();

    int getBinX();

    void setBinX(int binX);


    int getBinY();

    void setBinY(int binY);


    CameraState getCameraState();

    int getCameraXSize();

    int getCameraYSize();

    boolean canAbortExposure();

    boolean canAsymmetricBin();

    boolean canFastReadout();

    boolean canGetCoolerPower();

    boolean canPulseGuide();

    boolean canSetCCDTemperature();

    boolean canStopExposure();

    double getCCDTemperature();

    boolean isCoolerOn();

    void setCoolerOn(boolean coolerOn);

    int getCoolerPower();

    double getElectronsPerADU();

    double getExposureMax();

    double getExposureMin();

    double getExposureResolution();

    boolean getFastReadout();

    void setFastReadout(boolean fastReadout);

    double getFullWellCapacity();

    int getGain();

    void setGain(int gain);

    int getGainMax();

    int getGainMin();

    List<String> getGains();

    boolean hasShutter();

    double getHeatSinkTemperature();

    ImageArray getImageArray();

    byte[] getImageBytes();

    boolean isImageReady();

    boolean isPulseGuiding();

    double getLastExposureDuration();

    String getLastExposureStartTime();

    int getMaxADU();

    int getMaxBinX();

    int getMaxBinY();

    int getNumX();

    void setNumX(int numX);

    int getNumY();

    void setNumY(int numY);

    int getOffset();

    void setOffset(int offset);

    int getOffsetMax();

    int getOffsetMin();

    List<String> getOffsets();

    int getPercentCompleted();

    double getPixelSizeX();

    double getPixelSizeY();

    int getReadoutMode();

    void setReadoutMode(int readoutMode);

    List<String> getReadoutModes();

    String getSensorName();

    SensorType getSensorType();

    double getSetCCDTemperature();

    void setCCDTemperature(double setCCDTemperature);

    int getStartX();

    void setStartX(int startX);

    int getStartY();

    void setStartY(int startY);

    double getSubExposureDuration();

    void setSubExposureDuration(double subExposureDuration);

    void abortExposure();

    void pulseGuide(int direction, int duration);

    void startExposure(double duration, boolean light);

    void stopExposure();
}
