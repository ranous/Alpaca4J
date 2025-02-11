package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Camera;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

@SuppressWarnings("unused")
public class CameraClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(CameraClient.class);
    private Camera client = null;

    public CameraClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
    }

    public CameraClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
    }

    private Camera getClient() {
        if (client == null) {
            try {
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(Camera.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Camera - " + e.getMessage());
            }
        }
        return client;
    }

    public int getBayerOffsetX() {
        IntResponse response = getClient().getBayerOffsetX(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getBayerOffsetY() {
        IntResponse response = getClient().getBayerOffsetY(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getBinX() {
        IntResponse response = getClient().getBinX(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setBinX(int binX) {
        AlpacaResponse response = getClient().setBinX(getDeviceID(), getClientID(), getTransactionID(), binX);
        checkResponse(response);
    }

    public int getBinY() {
        IntResponse response = getClient().getBinY(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setBinY(int binY) {
        AlpacaResponse response = getClient().setBinY(getDeviceID(), getClientID(), getTransactionID(), binY);
        checkResponse(response);
    }

    public CameraState getCameraState() {
        IntResponse response = getClient().getCameraState(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return CameraState.fromState(response.getValue());
    }

    public int getCameraXSize() {
        IntResponse response = getClient().getCameraXSize(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getCameraYSize() {
        IntResponse response = getClient().getCameraYSize(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canAbortExposure() {
        BooleanResponse response = getClient().canAbortExposure(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canAsymmetricBin() {
        BooleanResponse response = getClient().canAsymmetricBin(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canFastReadout() {
        BooleanResponse response = getClient().canFastReadout(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canGetCoolerPower() {
        BooleanResponse response = getClient().canGetCoolerPower(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canPulseGuide() {
        BooleanResponse response = getClient().canPulseGuide(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetCCDTemperature() {
        BooleanResponse response = getClient().canSetCCDTemperature(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canStopExposure() {
        BooleanResponse response = getClient().canStopExposure(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getCCDTemperature() {
        DoubleResponse response = getClient().getCCDTemperature(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isCoolerOn() {
        BooleanResponse response = getClient().isCoolerOn(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setCoolerOn(boolean coolerOn) {
        AlpacaResponse response = getClient().setCoolerOn(getDeviceID(), getClientID(), getTransactionID(), coolerOn);
        checkResponse(response);
    }

    public int getCoolerPower() {
        IntResponse response = getClient().getCoolerPower(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getElectronsPerADU() {
        DoubleResponse response = getClient().getElectronsPerADU(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getExposureMax() {
        DoubleResponse response = getClient().getExposureMax(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getExposureMin() {
        DoubleResponse response = getClient().getExposureMin(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getExposureResolution() {
        DoubleResponse response = getClient().getExposureResolution(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean getFastReadout() {
        BooleanResponse response = getClient().getFastReadout(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setFastReadout(boolean fastReadout) {
        AlpacaResponse response = getClient().setFastReadout(getDeviceID(), getClientID(), getTransactionID(), fastReadout);
        checkResponse(response);
    }

    public double getFullWellCapacity() {
        DoubleResponse response = getClient().getFullWellCapacity(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getGain() {
        IntResponse response = getClient().getGain(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setGain(int gain) {
        AlpacaResponse response = getClient().setGain(getDeviceID(), getClientID(), getTransactionID(), gain);
        checkResponse(response);
    }

    public int getGainMax() {
        IntResponse response = getClient().getGainMax(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getGainMin() {
        IntResponse response = getClient().getGainMin(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public List<String> getGains() {
        ListResponse<String> response = getClient().getGains(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean hasShutter() {
        BooleanResponse response = getClient().hasShutter(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getHeatSinkTemperature() {
        DoubleResponse response = getClient().getHeatSinkTemperature(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public ImageArray getImageArray() {
        ImageArrayResponse response = getClient().getImageArray(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return new ImageArray(response.getType(), response.getRank(), response.getValue());
    }

    public ImageArray getImageArrayVariant() {
        ImageArrayResponse response = getClient().getImageArrayVariant(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return new ImageArray(response.getType(), response.getRank(), response.getValue());
    }

    public boolean isImageReady() {
        BooleanResponse response = getClient().isImageReady(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isPulseGuiding() {
        BooleanResponse response = getClient().isPulseGuiding(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getLastExposureDuration() {
        DoubleResponse response = getClient().getLastExposureDuration(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public String getLastExposureStartTime() {
        StringResponse response = getClient().getLastExposureStartTime(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getMaxADU() {
        IntResponse response = getClient().getMaxADU(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getMaxBinX() {
        IntResponse response = getClient().getMaxBinX(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getMaxBinY() {
        IntResponse response = getClient().getMaxBinY(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getNumX() {
        IntResponse response = getClient().getNumX(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setNumX(int numX) {
        AlpacaResponse response = getClient().setNumX(getDeviceID(), getClientID(), getTransactionID(), numX);
        checkResponse(response);
    }

    public int getNumY() {
        IntResponse response = getClient().getNumY(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setNumY(int numY) {
        AlpacaResponse response = getClient().setNumY(getDeviceID(), getClientID(), getTransactionID(), numY);
        checkResponse(response);
    }

    public int getOffset() {
        IntResponse response = getClient().getOffset(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setOffset(int offset) {
        AlpacaResponse response = getClient().setOffset(getDeviceID(), getClientID(), getTransactionID(), offset);
        checkResponse(response);
    }

    public int getOffsetMax() {
        IntResponse response = getClient().getOffsetMax(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getOffsetMin() {
        IntResponse response = getClient().getOffsetMin(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public List<String> getOffsets() {
        ListResponse<String> response = getClient().getOffsets(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getPercentCompleted() {
        IntResponse response = getClient().getPercentCompleted(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getPixelSizeX() {
        DoubleResponse response = getClient().getPixelSizeX(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getPixelSizeY() {
        DoubleResponse response = getClient().getPixelSizeY(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getReadoutMode() {
        IntResponse response = getClient().getReadoutMode(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setReadoutMode(int readoutMode) {
        AlpacaResponse response = getClient().setOffset(getDeviceID(), getClientID(), getTransactionID(), readoutMode);
        checkResponse(response);
    }

    public List<String> getReadoutModes() {
        ListResponse<String> response = getClient().getReadoutModes(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public String getSensorName() {
        StringResponse response = getClient().getSensorName(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public SensorType getSensorType() {
        IntResponse response = getClient().getSensorType(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return SensorType.fromType(response.getValue());
    }

    public double getSetCCDTemperature() {
        DoubleResponse response = getClient().getSetCCDTemperature(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setCCDTemperature(double setCCDTemperature) {
        AlpacaResponse response = getClient().setCCDTemperature(getDeviceID(), getClientID(), getTransactionID(), setCCDTemperature);
        checkResponse(response);
    }

    public int getStartX() {
        IntResponse response = getClient().getStartX(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setStartX(int startX) {
        AlpacaResponse response = getClient().setStartX(getDeviceID(), getClientID(), getTransactionID(), startX);
        checkResponse(response);
    }

    public int getStartY() {
        IntResponse response = getClient().getStartY(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setStartY(int startY) {
        AlpacaResponse response = getClient().setStartY(getDeviceID(), getClientID(), getTransactionID(), startY);
        checkResponse(response);
    }

    public double getSubExposureDuration() {
        DoubleResponse response = getClient().getSubExposureDuration(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setSubExposureDuration(double subExposureDuration) {
        AlpacaResponse response = getClient().setSubExposureDuration(getDeviceID(), getClientID(), getTransactionID(), subExposureDuration);
        checkResponse(response);
    }

    public void abortExposure() {
        AlpacaResponse response = getClient().abortExposure(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void pulseGuide(int direction,
                           int duration) {
        AlpacaResponse response = getClient().pulseGuide(getDeviceID(), getClientID(), getTransactionID(), direction, duration);
        checkResponse(response);
    }

    public void startExposure(int duration,
                              boolean light) {
        AlpacaResponse response = getClient().startExposure(getDeviceID(), getClientID(), getTransactionID(), duration, light);
        checkResponse(response);
    }

    public void stopExposure() {
        AlpacaResponse response = getClient().stopExposure(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }
}
