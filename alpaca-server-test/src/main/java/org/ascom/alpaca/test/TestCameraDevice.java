package org.ascom.alpaca.test;

import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.CameraDevice;
import org.ascom.alpaca.model.CameraState;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.ImageArray;
import org.ascom.alpaca.model.SensorType;
import org.ascom.alpaca.response.InvalidOperationException;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.PropertyNotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
@Singleton
public class TestCameraDevice extends BaseDevice implements CameraDevice {
    private static final Logger log = LoggerFactory.getLogger(TestCameraDevice.class);

    int binx = 1;
    int biny = 1;
    double ccdTemp = -20.1;
    double setCCDTemp = 20;
    boolean coolerOn = true;
    boolean imageReady = false;
    int subframeStartX = 0;
    int subframeStartY = 0;
    final int sensorWidth = 5;
    final int sensorHeight = 5;
    final int maxBinX = 1;
    final int maxBinY = 1;
    int numX = sensorWidth;
    int numY = sensorHeight;
    int startx = 0;
    int starty=0;
    CameraState state = CameraState.CameraIdle;
    long exposureStartTime = 0;
    double exposureDuration = 0;
    double lastExposureDuration = 0;
    long lastExposureStartTime = 0;
    final int maxDuration = 300;
    double subExposureDuration = 0;
    int readoutMode = 0;

    public TestCameraDevice() {
        super(DeviceType.Camera, "Test Camera Driver", 4);
        setDescription("Test Camera Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    @Override
    public int getBayerOffsetX() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getBayerOffsetY() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getBinX() {
        return binx;
    }

    @Override
    public void setBinX(int binX) {
        binx = binX;
    }

    @Override
    public int getBinY() {
        return biny;
    }

    @Override
    public void setBinY(int binY) {
        biny = binY;
    }

    @Override
    public CameraState getCameraState() {
        if (state == CameraState.CameraExposing && System.currentTimeMillis() > exposureStartTime + exposureDuration) {
            state = CameraState.CameraReading;
            return CameraState.CameraExposing;
        }
        if (state == CameraState.CameraReading) {
            state = CameraState.CameraDownload;
            return CameraState.CameraReading;
        }
        if (state == CameraState.CameraDownload) {
            state = CameraState.CameraIdle;
            imageReady = true;
            lastExposureStartTime = exposureStartTime;
            exposureStartTime = 0;
            lastExposureDuration = exposureDuration;
            exposureDuration = 0;
            return CameraState.CameraDownload;
        }
        return state;
    }

    @Override
    public int getCameraXSize() {
        return sensorWidth;
    }

    @Override
    public int getCameraYSize() {
        return sensorHeight;
    }

    @Override
    public boolean canAbortExposure() {
        return true;
    }

    @Override
    public boolean canAsymmetricBin() {
        return false;
    }

    @Override
    public boolean canFastReadout() {
        return false;
    }

    @Override
    public boolean canGetCoolerPower() {
        return false;
    }

    @Override
    public boolean canPulseGuide() {
        return false;
    }

    @Override
    public boolean canSetCCDTemperature() {
        return true;
    }

    @Override
    public boolean canStopExposure() {
        return false;
    }

    @Override
    public double getCCDTemperature() {
        return ccdTemp;
    }

    @Override
    public boolean isCoolerOn() {
        return coolerOn;
    }

    @Override
    public void setCoolerOn(boolean coolerOn) {
        this.coolerOn = coolerOn;
    }

    @Override
    public int getCoolerPower() {
        return 0;
    }

    @Override
    public double getElectronsPerADU() {
        return 1.5;
    }

    @Override
    public double getExposureMax() {
        return 600;
    }

    @Override
    public double getExposureMin() {
        return 1;
    }

    @Override
    public double getExposureResolution() {
        return 1;
    }

    @Override
    public boolean getFastReadout() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setFastReadout(boolean fastReadout) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public double getFullWellCapacity() {
        return 32000;
    }

    @Override
    public int getGain() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setGain(int gain) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getGainMax() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getGainMin() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public List<String> getGains() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public boolean hasShutter() {
        return false;
    }

    @Override
    public double getHeatSinkTemperature() {
        return 14.1;
    }

    @Override
    public ImageArray getImageArray() {
        getCameraState();
        if (!imageReady) {
            throw new InvalidOperationException("No image ready");
        }
        int[][] imagearray = { { 3, 5, 86, 8, 9}, { 3, 4, 4, 3, 99}, { 32, 2, 94, 22, 9},{ 32, 2, 94, 22, 9},{ 32, 2, 94, 22, 9}};
        return new ImageArray(ImageArray.Type.Integer, ImageArray.Rank.SinglePlane, imagearray);
    }

    @Override
    public byte[] getImageBytes() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public boolean isImageReady() {
        getCameraState();
        return imageReady;
    }

    @Override
    public boolean isPulseGuiding() {
        return false;
    }

    @Override
    public double getLastExposureDuration() {
        if (lastExposureDuration == 0) {
            throw new InvalidOperationException("No last exposure");
        }
        return lastExposureDuration /1000;
    }

    @Override
    public String getLastExposureStartTime() {
        if (lastExposureStartTime == 0) {
            throw new InvalidOperationException("No last exposure");
        }
        Date startDate = new Date(lastExposureStartTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(startDate);
    }

    @Override
    public int getMaxADU() {
        return 40000;
    }

    @Override
    public int getMaxBinX() {
        return maxBinX;
    }

    @Override
    public int getMaxBinY() {
        return maxBinY;
    }

    @Override
    public int getNumX() {
        return numX;
    }

    @Override
    public void setNumX(int numX) {
        this.numX = numX;
    }

    @Override
    public int getNumY() {
        return numY;
    }

    @Override
    public void setNumY(int numY) {
        this.numY = numY;
    }

    @Override
    public int getOffset() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setOffset(int offset) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getOffsetMax() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getOffsetMin() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public List<String> getOffsets() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getPercentCompleted() {
        if (exposureStartTime > 0) {
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - exposureStartTime;
            if (elapsed >= exposureDuration) {
                return 100;
            }
            return (int) ((double)elapsed/exposureDuration*100);
        }
        return 0;
    }

    @Override
    public double getPixelSizeX() {
        return 5;
    }

    @Override
    public double getPixelSizeY() {
        return 5;
    }

    @Override
    public int getReadoutMode() {
        return readoutMode;
    }

    @Override
    public void setReadoutMode(int readoutMode) {
        this.readoutMode = readoutMode;
    }

    @Override
    public List<String> getReadoutModes() {
        return List.of("Normal");
    }

    @Override
    public String getSensorName() {
        return "XYZ2000";
    }

    @Override
    public SensorType getSensorType() {
        return SensorType.Monochrome;
    }

    @Override
    public double getSetCCDTemperature() {
        return setCCDTemp;
    }

    @Override
    public void setCCDTemperature(double setCCDTemperature) {
        if (setCCDTemperature < -20 || setCCDTemperature > 50) {
            throw new InvalidValueException("Invalid temperature");
        }
        this.setCCDTemp = setCCDTemperature;
        this.ccdTemp = setCCDTemp + .01;
    }

    @Override
    public int getStartX() {
        return startx;
    }

    @Override
    public void setStartX(int startX) {
        this.startx = startX;
    }

    @Override
    public int getStartY() {
        return starty;
    }

    @Override
    public void setStartY(int startY) {
        this.starty = startY;
    }

    @Override
    public double getSubExposureDuration() {
        return subExposureDuration;
    }

    @Override
    public void setSubExposureDuration(double subExposureDuration) {
        if (subExposureDuration <=0 || subExposureDuration > exposureDuration) {
            throw new InvalidValueException("Invalid duration");
        }
        this.subExposureDuration = subExposureDuration;
    }

    @Override
    public void abortExposure() {
        state = CameraState.CameraIdle;
    }

    @Override
    public void pulseGuide(int direction, int duration) {
        throw new PropertyNotImplementedException("not implemented");
    }

    @Override
    public void startExposure(double duration, boolean light) {
        if (duration < 0 || duration > this.maxDuration) {
            throw new InvalidValueException("invalid duration");
        }
        if (binx > maxBinX) {
            throw new InvalidValueException("invalid binX");
        }
        if (biny > maxBinY) {
            throw new InvalidValueException("invalid binY");
        }
        if (startx > sensorWidth) {
            throw new InvalidValueException("invalid startX");
        }
        if (starty > sensorHeight) {
            throw new InvalidValueException("invalid startY");
        }
        if (numX > sensorWidth - startx) {
            throw new InvalidValueException("invalid numX");
        }
        if (numY > sensorHeight - starty) {
            throw new InvalidValueException("invalid numX");
        }
        state = CameraState.CameraExposing;
        exposureStartTime = System.currentTimeMillis();
        exposureDuration = duration* 1000.0F;
        imageReady = false;
        lastExposureStartTime = 0;
        lastExposureDuration = 0;
        log.info("Starting exposure of {} seconds", duration);
    }

    @Override
    public void stopExposure() {
        throw new PropertyNotImplementedException("Not implemented");
    }
}
