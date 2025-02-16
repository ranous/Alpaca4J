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
    int sensorWidth = 5;
    int sensorHeight = 5;
    int numX = sensorWidth;
    int numY = sensorHeight;
    int startx = 0;
    int starty=0;
    CameraState state = CameraState.CameraIdle;
    long exposureStartTime = 0;
    long exposureDuration = 0;
    long lastExposureDuration = 0;
    long lastExposureStartTime = 0;
    int maxDuration = 300;
    double subExposureDuration = 0;
    int readoutMode = 0;

    public TestCameraDevice() {
        super(DeviceType.Camera, "Test Camera Driver", 4);
        setDescription("Test Camera Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    @Override
    public int getBayerOffsetX(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getBayerOffsetY(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getBinX(int clientID) {
        return binx;
    }

    @Override
    public void setBinX(int clientID, int binX) {
        binx = binX;
    }

    @Override
    public int getBinY(int clientID) {
        return biny;
    }

    @Override
    public void setBinY(int clientID, int binY) {
        biny = binY;
    }

    @Override
    public CameraState getCameraState(int clientID) {
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
    public int getCameraXSize(int clientID) {
        return sensorWidth;
    }

    @Override
    public int getCameraYSize(int clientID) {
        return sensorHeight;
    }

    @Override
    public boolean canAbortExposure(int clientID) {
        return true;
    }

    @Override
    public boolean canAsymmetricBin(int clientID) {
        return false;
    }

    @Override
    public boolean canFastReadout(int clientID) {
        return false;
    }

    @Override
    public boolean canGetCoolerPower(int clientID) {
        return false;
    }

    @Override
    public boolean canPulseGuide(int clientID) {
        return false;
    }

    @Override
    public boolean canSetCCDTemperature(int clientID) {
        return true;
    }

    @Override
    public boolean canStopExposure(int clientID) {
        return false;
    }

    @Override
    public double getCCDTemperature(int clientID) {
        return ccdTemp;
    }

    @Override
    public boolean isCoolerOn(int clientID) {
        return coolerOn;
    }

    @Override
    public void setCoolerOn(int clientID, boolean coolerOn) {
        this.coolerOn = coolerOn;
    }

    @Override
    public int getCoolerPower(int clientID) {
        return 0;
    }

    @Override
    public double getElectronsPerADU(int clientID) {
        return 1.5;
    }

    @Override
    public double getExposureMax(int clientID) {
        return 600;
    }

    @Override
    public double getExposureMin(int clientID) {
        return 1;
    }

    @Override
    public double getExposureResolution(int clientID) {
        return 1;
    }

    @Override
    public boolean getFastReadout(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setFastReadout(int clientID, boolean fastReadout) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public double getFullWellCapacity(int clientID) {
        return 32000;
    }

    @Override
    public int getGain(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setGain(int clientID, int gain) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getGainMax(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getGainMin(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public List<String> getGains(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public boolean hasShutter(int clientID) {
        return false;
    }

    @Override
    public double getHeatSinkTemperature(int clientID) {
        return 14.1;
    }

    @Override
    public ImageArray getImageArray(int clientID) {
        getCameraState(clientID);
        if (!imageReady) {
            throw new InvalidOperationException("No image ready");
        }
        int[][] imagearray = { { 3, 5, 86, 8, 9}, { 3, 4, 4, 3, 99}, { 32, 2, 94, 22, 9},{ 32, 2, 94, 22, 9},{ 32, 2, 94, 22, 9}};
        return new ImageArray(ImageArray.Type.Integer, ImageArray.Rank.SinglePlane, imagearray);
    }

    @Override
    public ImageArray getImageArrayVariant(int clientID) {
        return getImageArray(clientID);
    }

    @Override
    public boolean isImageReady(int clientID) {
        getCameraState(clientID);
        return imageReady;
    }

    @Override
    public boolean isPulseGuiding(int clientID) {
        return false;
    }

    @Override
    public double getLastExposureDuration(int clientID) {
        if (lastExposureDuration == 0) {
            throw new InvalidOperationException("No last exposure");
        }
        return (double) lastExposureDuration /1000;
    }

    @Override
    public String getLastExposureStartTime(int clientID) {
        if (lastExposureStartTime == 0) {
            throw new InvalidOperationException("No last exposure");
        }
        Date startDate = new Date(lastExposureStartTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(startDate);
    }

    @Override
    public int getMaxADU(int clientID) {
        return 40000;
    }

    @Override
    public int getMaxBinX(int clientID) {
        return 1;
    }

    @Override
    public int getMaxBinY(int clientID) {
        return 1;
    }

    @Override
    public int getNumX(int clientID) {
        return numX;
    }

    @Override
    public void setNumX(int clientID, int numX) {
        this.numX = numX;
    }

    @Override
    public int getNumY(int clientID) {
        return numY;
    }

    @Override
    public void setNumY(int clientID, int numY) {
        this.numY = numY;
    }

    @Override
    public int getOffset(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setOffset(int clientID, int offset) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getOffsetMax(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getOffsetMin(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public List<String> getOffsets(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public int getPercentCompleted(int clientID) {
        if (exposureStartTime > 0) {
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - exposureStartTime;
            if (elapsed >= exposureDuration) {
                return 100;
            }
            return (int) ((double)elapsed/(double)exposureDuration*100);
        }
        return 0;
    }

    @Override
    public double getPixelSizeX(int clientID) {
        return 5;
    }

    @Override
    public double getPixelSizeY(int clientID) {
        return 5;
    }

    @Override
    public int getReadoutMode(int clientID) {
        return readoutMode;
    }

    @Override
    public void setReadoutMode(int clientID, int readoutMode) {
        this.readoutMode = readoutMode;
    }

    @Override
    public List<String> getReadoutModes(int clientID) {
        return List.of("Normal");
    }

    @Override
    public String getSensorName(int clientID) {
        return "XYZ2000";
    }

    @Override
    public SensorType getSensorType(int clientID) {
        return SensorType.Monochrome;
    }

    @Override
    public double getSetCCDTemperature(int clientID) {
        return setCCDTemp;
    }

    @Override
    public void setCCDTemperature(int clientID, double setCCDTemperature) {
        if (setCCDTemperature < -20 || setCCDTemperature > 50) {
            throw new InvalidValueException("Invalid temperature");
        }
        this.setCCDTemp = setCCDTemperature;
    }

    @Override
    public int getStartX(int clientID) {
        return startx;
    }

    @Override
    public void setStartX(int clientID, int startX) {
        this.startx = startX;
    }

    @Override
    public int getStartY(int clientID) {
        return starty;
    }

    @Override
    public void setStartY(int clientID, int startY) {
        this.starty = startY;
    }

    @Override
    public double getSubExposureDuration(int clientID) {
        return subExposureDuration;
    }

    @Override
    public void setSubExposureDuration(int clientID, double subExposureDuration) {
        if (subExposureDuration <=0 || subExposureDuration > exposureDuration) {
            throw new InvalidValueException("Invalid duration");
        }
        this.subExposureDuration = subExposureDuration;
    }

    @Override
    public void abortExposure(int clientID) {
        state = CameraState.CameraIdle;
    }

    @Override
    public void pulseGuide(int clientID, int direction, int duration) {
        throw new PropertyNotImplementedException("not implemented");
    }

    @Override
    public void startExposure(int clientID, int duration, boolean light) {
        if (duration < 0 || duration > this.maxDuration) {
            throw new InvalidValueException("invalid duration");
        }
        state = CameraState.CameraExposing;
        exposureStartTime = System.currentTimeMillis();
        exposureDuration = duration* 1000L;
        imageReady = false;
        lastExposureStartTime = 0;
        lastExposureDuration = 0;
        log.info("Starting exposure of {} seconds", duration);
    }

    @Override
    public void stopExposure(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }
}
