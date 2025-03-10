package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Camera;
import org.ascom.alpaca.client.util.ImageBytesConverterFactory;
import org.ascom.alpaca.model.CameraState;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.ImageArray;
import org.ascom.alpaca.model.SensorType;
import org.ascom.alpaca.response.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;
import java.util.List;

@SuppressWarnings("unused")
public class CameraClient extends CommonClient {
    private static final Logger log = Logger.getLogger(CameraClient.class);
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
                JacksonConverterFactory jacksonConverterFactory = JacksonConverterFactory.create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(getServerAddress().toURL())
                        .addConverterFactory(ImageBytesConverterFactory.create(jacksonConverterFactory))
                        .addConverterFactory(jacksonConverterFactory)
                        .build();
                client = retrofit.create(Camera.class);
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Camera - " + e.getMessage());
            }
        }
        return client;
    }

    public int getBayerOffsetX() {
        IntResponse response = call(getClient().getBayerOffsetX(getDeviceID(), getClientID(), getTransactionID()), "getBayerOffsetX");
        return response.getValue();
    }

    public void getBayerOffsetX(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getBayerOffsetX(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getBayerOffsetX");
    }

    public int getBayerOffsetY() {
        IntResponse response = call(getClient().getBayerOffsetY(getDeviceID(), getClientID(), getTransactionID()), "getBayerOffsetY");
        return response.getValue();
    }

    public void getBayerOffsetY(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getBayerOffsetY(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getBayerOffsetY");
    }

    public int getBinX() {
        IntResponse response = call(getClient().getBinX(getDeviceID(), getClientID(), getTransactionID()), "getBinX");
        return response.getValue();
    }

    public void getBinX(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getBinX(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getBinX");
    }

    public void setBinX(int binX) {
        AlpacaResponse response = call(getClient().setBinX(getDeviceID(), getClientID(), getTransactionID(), binX), "setBinX", binX);
    }

    public void setBinX(int binX, AlpacaCallback<Void> callback) {
        callAsync(getClient().setBinX(getDeviceID(), getClientID(), getTransactionID(), binX), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setBinX", binX);
    }

    public int getBinY() {
        IntResponse response = call(getClient().getBinY(getDeviceID(), getClientID(), getTransactionID()), "getBinY");
        return response.getValue();
    }

    public void getBinY(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getBinY(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getBinY");
    }

    public void setBinY(int binY) {
        AlpacaResponse response = call(getClient().setBinY(getDeviceID(), getClientID(), getTransactionID(), binY), "setBinY", binY);
    }

    public void setBinY(int binY, AlpacaCallback<Void> callback) {
        callAsync(getClient().setBinY(getDeviceID(), getClientID(), getTransactionID(), binY), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setBinY", binY);
    }

    public CameraState getCameraState() {
        IntResponse response = call(getClient().getCameraState(getDeviceID(), getClientID(), getTransactionID()), "getCameraState");
        return CameraState.fromState(response.getValue());
    }

    public void getCameraState(AlpacaCallback<CameraState> callback) {
        callAsync(getClient().getCameraState(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(CameraState.fromState(result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCameraState");
    }

    public int getCameraXSize() {
        IntResponse response = call(getClient().getCameraXSize(getDeviceID(), getClientID(), getTransactionID()), "getCameraXSize");
        return response.getValue();
    }

    public void getCameraXSize(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getCameraXSize(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCameraXSize");
    }

    public int getCameraYSize() {
        IntResponse response = call(getClient().getCameraYSize(getDeviceID(), getClientID(), getTransactionID()), "getCameraYSize");
        return response.getValue();
    }

    public void getCameraYSize(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getCameraYSize(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCameraYSize");
    }

    public boolean canAbortExposure() {
        BooleanResponse response = call(getClient().canAbortExposure(getDeviceID(), getClientID(), getTransactionID()), "canAbortExposure");
        return response.getValue();
    }

    public void canAbortExposure(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canAbortExposure(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canAbortExposure");
    }

    public boolean canAsymmetricBin() {
        BooleanResponse response = call(getClient().canAsymmetricBin(getDeviceID(), getClientID(), getTransactionID()), "canAsymmetricBin");
        return response.getValue();
    }

    public void canAsymmetricBin(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canAsymmetricBin(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canAsymmetricBin");
    }

    public boolean canFastReadout() {
        BooleanResponse response = call(getClient().canFastReadout(getDeviceID(), getClientID(), getTransactionID()), "canFastReadout");
        return response.getValue();
    }

    public void canFastReadout(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canFastReadout(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canFastReadout");
    }

    public boolean canGetCoolerPower() {
        BooleanResponse response = call(getClient().canGetCoolerPower(getDeviceID(), getClientID(), getTransactionID()), "canGetCoolerPower");
        return response.getValue();
    }

    public void canGetCoolerPower(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canGetCoolerPower(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canGetCoolerPower");
    }

    public boolean canPulseGuide() {
        BooleanResponse response = call(getClient().canPulseGuide(getDeviceID(), getClientID(), getTransactionID()), "canPulseGuide");
        return response.getValue();
    }

    public void canPulseGuide(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canPulseGuide(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canPulseGuide");
    }

    public boolean canSetCCDTemperature() {
        BooleanResponse response = call(getClient().canSetCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), "canSetCCDTemperature");
        return response.getValue();
    }

    public void canSetCCDTemperature(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetCCDTemperature");
    }

    public boolean canStopExposure() {
        BooleanResponse response = call(getClient().canStopExposure(getDeviceID(), getClientID(), getTransactionID()), "canStopExposure");
        return response.getValue();
    }

    public void canStopExposure(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canStopExposure(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canStopExposure");
    }

    public double getCCDTemperature() {
        DoubleResponse response = call(getClient().getCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), "getCCDTemperature");
        return response.getValue();
    }

    public void getCCDTemperature(AlpacaCallback<Double> callback) {
        callAsync(getClient().getCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCCDTemperature");
    }

    public boolean isCoolerOn() {
        BooleanResponse response = call(getClient().isCoolerOn(getDeviceID(), getClientID(), getTransactionID()), "isCoolerOn");
        return response.getValue();
    }

    public void isCoolerOn(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isCoolerOn(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isCoolerOn");
    }

    public void setCoolerOn(boolean coolerOn) {
        AlpacaResponse response = call(getClient().setCoolerOn(getDeviceID(), getClientID(), getTransactionID(), coolerOn), "setCoolerOn", coolerOn);
    }

    public void setCoolerOn(boolean coolerOn, AlpacaCallback<Void> callback) {
        callAsync(getClient().setCoolerOn(getDeviceID(), getClientID(), getTransactionID(), coolerOn), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setCoolerOn", coolerOn);
    }

    public int getCoolerPower() {
        IntResponse response = call(getClient().getCoolerPower(getDeviceID(), getClientID(), getTransactionID()), "getCoolerPower");
        return response.getValue();
    }

    public void getCoolerPower(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getCoolerPower(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCoolerPower");
    }

    public double getElectronsPerADU() {
        DoubleResponse response = call(getClient().getElectronsPerADU(getDeviceID(), getClientID(), getTransactionID()), "getElectronsPerADU");
        return response.getValue();
    }

    public void getElectronsPerADU(AlpacaCallback<Double> callback) {
        callAsync(getClient().getElectronsPerADU(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getElectronsPerADU");
    }

    public double getExposureMax() {
        DoubleResponse response = call(getClient().getExposureMax(getDeviceID(), getClientID(), getTransactionID()), "getExposureMax");
        return response.getValue();
    }

    public void getExposureMax(AlpacaCallback<Double> callback) {
        callAsync(getClient().getExposureMax(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getExposureMax");
    }

    public double getExposureMin() {
        DoubleResponse response = call(getClient().getExposureMin(getDeviceID(), getClientID(), getTransactionID()), "getExposureMin");
        return response.getValue();
    }

    public void getExposureMin(AlpacaCallback<Double> callback) {
        callAsync(getClient().getExposureMin(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getExposureMin");
    }

    public double getExposureResolution() {
        DoubleResponse response = call(getClient().getExposureResolution(getDeviceID(), getClientID(), getTransactionID()), "getExposureResolution");
        return response.getValue();
    }

    public void getExposureResolution(AlpacaCallback<Double> callback) {
        callAsync(getClient().getExposureResolution(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getExposureResolution");
    }

    public boolean getFastReadout() {
        BooleanResponse response = call(getClient().getFastReadout(getDeviceID(), getClientID(), getTransactionID()), "getFastReadout");
        return response.getValue();
    }

    public void getFastReadout(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().getFastReadout(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getFastReadout");
    }

    public void setFastReadout(boolean fastReadout) {
        AlpacaResponse response = call(getClient().setFastReadout(getDeviceID(), getClientID(), getTransactionID(), fastReadout), "setFastReadout", fastReadout);
    }

    public void setFastReadout(boolean fastReadout, AlpacaCallback<Void> callback) {
        callAsync(getClient().setFastReadout(getDeviceID(), getClientID(), getTransactionID(), fastReadout), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setFastReadout", fastReadout);
    }

    public double getFullWellCapacity() {
        DoubleResponse response = call(getClient().getFullWellCapacity(getDeviceID(), getClientID(), getTransactionID()), "getFullWellCapacity");
        return response.getValue();
    }

    public void getFullWellCapacity(AlpacaCallback<Double> callback) {
        callAsync(getClient().getFullWellCapacity(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getFullWellCapacity");
    }

    public int getGain() {
        IntResponse response = call(getClient().getGain(getDeviceID(), getClientID(), getTransactionID()), "getGain");
        return response.getValue();
    }

    public void getGain(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getGain(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getGain");
    }

    public void setGain(int gain) {
        AlpacaResponse response = call(getClient().setGain(getDeviceID(), getClientID(), getTransactionID(), gain), "setGain", gain);
    }

    public void setGain(int gain, AlpacaCallback<Void> callback) {
        callAsync(getClient().setGain(getDeviceID(), getClientID(), getTransactionID(), gain), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setGain", gain);
    }

    public int getGainMax() {
        IntResponse response = call(getClient().getGainMax(getDeviceID(), getClientID(), getTransactionID()), "getGainMax");
        return response.getValue();
    }

    public void getGainMax(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getGainMax(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getGainMax");
    }

    public int getGainMin() {
        IntResponse response = call(getClient().getGainMin(getDeviceID(), getClientID(), getTransactionID()), "getGainMin");
        return response.getValue();
    }

    public void getGainMin(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getGainMin(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getGainMin");
    }

    public List<String> getGains() {
        ListResponse<String> response = call(getClient().getGains(getDeviceID(), getClientID(), getTransactionID()), "getGains");
        return response.getValue();
    }

    public void getGains(AlpacaCallback<List<String>> callback) {
        callAsync(getClient().getGains(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<String> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getGains");
    }

    public boolean hasShutter() {
        BooleanResponse response = call(getClient().hasShutter(getDeviceID(), getClientID(), getTransactionID()), "hasShutter");
        return response.getValue();
    }

    public void hasShutter(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().hasShutter(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "hasShutter");
    }

    public double getHeatSinkTemperature() {
        DoubleResponse response = call(getClient().getHeatSinkTemperature(getDeviceID(), getClientID(), getTransactionID()), "getHeatSinkTemperature");
        return response.getValue();
    }

    public void getHeatSinkTemperature(AlpacaCallback<Double> callback) {
        callAsync(getClient().getHeatSinkTemperature(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getHeatSinkTemperature");
    }

    public ImageArray getImageArray(String mediaType) {
        ImageArrayResponse response = call(getClient().getImageArray(mediaType, getDeviceID(), getClientID(), getTransactionID()), "getImageArray");
        return new ImageArray(response.getType(), response.getRank(), response.getValue());
    }

    public ImageArray getImageArray() {
        return getImageArray("application/json");
    }

    public void getImageArray(String mediaType, AlpacaCallback<ImageArray> callback) {
        callAsync(getClient().getImageArray(mediaType, getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ImageArrayResponse result) {
                callback.success(new ImageArray(result.getType(), result.getRank(), result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getImageArray");
    }

    public void getImageArray(AlpacaCallback<ImageArray> callback) {
        getImageArray("application/json", callback);
    }

    public ImageArray getImageArrayVariant() {
        ImageArrayResponse response = call(getClient().getImageArrayVariant(getDeviceID(), getClientID(), getTransactionID()), "getImageArrayVariant");
        return new ImageArray(response.getType(), response.getRank(), response.getValue());
    }

    public void getImageArrayVariant(AlpacaCallback<ImageArray> callback) {
        callAsync(getClient().getImageArrayVariant(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ImageArrayResponse result) {
                callback.success(new ImageArray(result.getType(), result.getRank(), result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getImageArrayVariant");
    }

    public ImageArray getImageBytes() {
        return getImageArray("application/imagebytes");
    }

    public void getImageBytes(AlpacaCallback<ImageArray> callback) {
        getImageArray("application/imagebytes", callback);
    }

    public boolean isImageReady() {
        BooleanResponse response = call(getClient().isImageReady(getDeviceID(), getClientID(), getTransactionID()), "isImageReady");
        return response.getValue();
    }

    public void isImageReady(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isImageReady(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isImageReady");
    }

    public boolean isPulseGuiding() {
        BooleanResponse response = call(getClient().isPulseGuiding(getDeviceID(), getClientID(), getTransactionID()), "isPulseGuiding");
        return response.getValue();
    }

    public void isPulseGuiding(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isPulseGuiding(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isPulseGuiding");
    }

    public double getLastExposureDuration() {
        DoubleResponse response = call(getClient().getLastExposureDuration(getDeviceID(), getClientID(), getTransactionID()), "getLastExposureDuration");
        return response.getValue();
    }

    public void getLastExposureDuration(AlpacaCallback<Double> callback) {
        callAsync(getClient().getLastExposureDuration(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getLastExposureDuration");
    }

    public String getLastExposureStartTime() {
        StringResponse response = call(getClient().getLastExposureStartTime(getDeviceID(), getClientID(), getTransactionID()), "getLastExposureStartTime");
        return response.getValue();
    }

    public void getLastExposureStartTime(AlpacaCallback<String> callback) {
        callAsync(getClient().getLastExposureStartTime(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getLastExposureStartTime");
    }

    public int getMaxADU() {
        IntResponse response = call(getClient().getMaxADU(getDeviceID(), getClientID(), getTransactionID()), "getMaxADU");
        return response.getValue();
    }

    public void getMaxADU(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getMaxADU(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMaxADU");
    }

    public int getMaxBinX() {
        IntResponse response = call(getClient().getMaxBinX(getDeviceID(), getClientID(), getTransactionID()), "getMaxBinX");
        return response.getValue();
    }

    public void getMaxBinX(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getMaxBinX(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMaxBinX");
    }

    public int getMaxBinY() {
        IntResponse response = call(getClient().getMaxBinY(getDeviceID(), getClientID(), getTransactionID()), "getMaxBinY");
        return response.getValue();
    }

    public void getMaxBinY(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getMaxBinY(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMaxBinY");
    }

    public int getNumX() {
        IntResponse response = call(getClient().getNumX(getDeviceID(), getClientID(), getTransactionID()), "getNumX");
        return response.getValue();
    }

    public void getNumX(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getNumX(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getNumX");
    }

    public void setNumX(int numX) {
        AlpacaResponse response = call(getClient().setNumX(getDeviceID(), getClientID(), getTransactionID(), numX), "setNumX", numX);
    }

    public void setNumX(int numX, AlpacaCallback<Void> callback) {
        callAsync(getClient().setNumX(getDeviceID(), getClientID(), getTransactionID(), numX), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setNumX", numX);
    }

    public int getNumY() {
        IntResponse response = call(getClient().getNumY(getDeviceID(), getClientID(), getTransactionID()), "getNumY");
        return response.getValue();
    }

    public void getNumY(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getNumY(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getNumY");
    }

    public void setNumY(int numY) {
        AlpacaResponse response = call(getClient().setNumY(getDeviceID(), getClientID(), getTransactionID(), numY), "setNumY", numY);
    }

    public void setNumY(int numY, AlpacaCallback<Void> callback) {
        callAsync(getClient().setNumY(getDeviceID(), getClientID(), getTransactionID(), numY), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setNumY", numY);
    }

    public int getOffset() {
        IntResponse response = call(getClient().getOffset(getDeviceID(), getClientID(), getTransactionID()), "getOffset");
        return response.getValue();
    }

    public void getOffset(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getOffset(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getOffset");
    }

    public void setOffset(int offset) {
        AlpacaResponse response = call(getClient().setOffset(getDeviceID(), getClientID(), getTransactionID(), offset), "setOffset", offset);
    }

    public void setOffset(int offset, AlpacaCallback<Void> callback) {
        callAsync(getClient().setOffset(getDeviceID(), getClientID(), getTransactionID(), offset), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setOffset", offset);
    }

    public int getOffsetMax() {
        IntResponse response = call(getClient().getOffsetMax(getDeviceID(), getClientID(), getTransactionID()), "getOffsetMax");
        return response.getValue();
    }

    public void getOffsetMax(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getOffsetMax(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getOffsetMax");
    }

    public int getOffsetMin() {
        IntResponse response = call(getClient().getOffsetMin(getDeviceID(), getClientID(), getTransactionID()), "getOffsetMin");
        return response.getValue();
    }

    public void getOffsetMin(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getOffsetMin(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getOffsetMin");
    }

    public List<String> getOffsets() {
        ListResponse<String> response = call(getClient().getOffsets(getDeviceID(), getClientID(), getTransactionID()), "getOffsets");
        return response.getValue();
    }

    public void getOffsets(AlpacaCallback<List<String>> callback) {
        callAsync(getClient().getOffsets(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<String> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getOffsets");
    }

    public int getPercentCompleted() {
        IntResponse response = call(getClient().getPercentCompleted(getDeviceID(), getClientID(), getTransactionID()), "getPercentCompleted");
        return response.getValue();
    }

    public void getPercentCompleted(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getPercentCompleted(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getPercentCompleted");
    }

    public double getPixelSizeX() {
        DoubleResponse response = call(getClient().getPixelSizeX(getDeviceID(), getClientID(), getTransactionID()), "getPixelSizeX");
        return response.getValue();
    }

    public void getPixelSizeX(AlpacaCallback<Double> callback) {
        callAsync(getClient().getPixelSizeX(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getPixelSizeX");
    }

    public double getPixelSizeY() {
        DoubleResponse response = call(getClient().getPixelSizeY(getDeviceID(), getClientID(), getTransactionID()), "getPixelSizeY");
        return response.getValue();
    }

    public void getPixelSizeY(AlpacaCallback<Double> callback) {
        callAsync(getClient().getPixelSizeY(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getPixelSizeY");
    }

    public int getReadoutMode() {
        IntResponse response = call(getClient().getReadoutMode(getDeviceID(), getClientID(), getTransactionID()), "getReadoutMode");
        return response.getValue();
    }

    public void getReadoutMode(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getReadoutMode(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getReadoutMode");
    }

    public void setReadoutMode(int readoutMode) {
        AlpacaResponse response = call(getClient().setReadoutMode(getDeviceID(), getClientID(), getTransactionID(), readoutMode), "setReadoutMode", readoutMode);
    }

    public void setReadoutMode(int readoutMode, AlpacaCallback<Void> callback) {
        callAsync(getClient().setReadoutMode(getDeviceID(), getClientID(), getTransactionID(), readoutMode), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setReadoutMode", readoutMode);
    }

    public List<String> getReadoutModes() {
        ListResponse<String> response = call(getClient().getReadoutModes(getDeviceID(), getClientID(), getTransactionID()), "getReadoutModes");
        return response.getValue();
    }

    public void getReadoutModes(AlpacaCallback<List<String>> callback) {
        callAsync(getClient().getReadoutModes(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<String> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getReadoutModes");
    }

    public String getSensorName() {
        StringResponse response = call(getClient().getSensorName(getDeviceID(), getClientID(), getTransactionID()), "getSensorName");
        return response.getValue();
    }

    public void getSensorName(AlpacaCallback<String> callback) {
        callAsync(getClient().getSensorName(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSensorName");
    }

    public SensorType getSensorType() {
        IntResponse response = call(getClient().getSensorType(getDeviceID(), getClientID(), getTransactionID()), "getSensorType");
        return SensorType.fromType(response.getValue());
    }

    public void getSensorType(AlpacaCallback<SensorType> callback) {
        callAsync(getClient().getSensorType(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(SensorType.fromType(result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSensorType");
    }

    public double getSetCCDTemperature() {
        DoubleResponse response = call(getClient().getSetCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), "getSetCCDTemperature");
        return response.getValue();
    }

    public void getSetCCDTemperature(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSetCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSetCCDTemperature");
    }

    public void setCCDTemperature(double setCCDTemperature) {
        AlpacaResponse response = call(getClient().setCCDTemperature(getDeviceID(), getClientID(), getTransactionID(), setCCDTemperature), "setCCDTemperature", setCCDTemperature);
    }

    public void setCCDTemperature(double setCCDTemperature, AlpacaCallback<Void> callback) {
        callAsync(getClient().setCCDTemperature(getDeviceID(), getClientID(), getTransactionID(), setCCDTemperature), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setCCDTemperature", setCCDTemperature);
    }

    public int getStartX() {
        IntResponse response = call(getClient().getStartX(getDeviceID(), getClientID(), getTransactionID()), "getStartX");
        return response.getValue();
    }

    public void getStartX(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getStartX(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getStartX");
    }

    public void setStartX(int startX) {
        AlpacaResponse response = call(getClient().setStartX(getDeviceID(), getClientID(), getTransactionID(), startX), "setStartX", startX);
    }

    public void setStartX(int startX, AlpacaCallback<Void> callback) {
        callAsync(getClient().setStartX(getDeviceID(), getClientID(), getTransactionID(), startX), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setStartX", startX);
    }

    public int getStartY() {
        IntResponse response = call(getClient().getStartY(getDeviceID(), getClientID(), getTransactionID()), "getStartY");
        return response.getValue();
    }

    public void getStartY(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getStartY(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getStartY");
    }

    public void setStartY(int startY) {
        AlpacaResponse response = call(getClient().setStartY(getDeviceID(), getClientID(), getTransactionID(), startY), "setStartY", startY);
    }

    public void setStartY(int startY, AlpacaCallback<Void> callback) {
        callAsync(getClient().setStartY(getDeviceID(), getClientID(), getTransactionID(), startY), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setStartY", startY);
    }

    public double getSubExposureDuration() {
        DoubleResponse response = call(getClient().getSubExposureDuration(getDeviceID(), getClientID(), getTransactionID()), "getSubExposureDuration");
        return response.getValue();
    }

    public void getSubExposureDuration(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSubExposureDuration(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSubExposureDuration");
    }

    public void setSubExposureDuration(double subExposureDuration) {
        AlpacaResponse response = call(getClient().setSubExposureDuration(getDeviceID(), getClientID(), getTransactionID(), subExposureDuration), "setSubExposureDuration", subExposureDuration);
    }

    public void setSubExposureDuration(double subExposureDuration, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSubExposureDuration(getDeviceID(), getClientID(), getTransactionID(), subExposureDuration), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSubExposureDuration", subExposureDuration);
    }

    public void abortExposure() {
        AlpacaResponse response = call(getClient().abortExposure(getDeviceID(), getClientID(), getTransactionID()), "abortExposure");
    }

    public void abortExposure(AlpacaCallback<Void> callback) {
        callAsync(getClient().abortExposure(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "abortExposure");
    }

    public void pulseGuide(int direction, int duration) {
        AlpacaResponse response = call(getClient().pulseGuide(getDeviceID(), getClientID(), getTransactionID(), direction, duration), "pulseGuide", direction, duration);
    }

    public void pulseGuide(int direction, int duration, AlpacaCallback<Void> callback) {
        callAsync(getClient().pulseGuide(getDeviceID(), getClientID(), getTransactionID(), direction, duration), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "pulseGuide", direction, duration);
    }

    public void startExposure(double duration, boolean light) {
        AlpacaResponse response = call(getClient().startExposure(getDeviceID(), getClientID(), getTransactionID(), duration, light), "startExposure", duration, light);
    }

    public void startExposure(int duration, boolean light, AlpacaCallback<Void> callback) {
        callAsync(getClient().startExposure(getDeviceID(), getClientID(), getTransactionID(), duration, light), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "startExposure", duration, light);
    }

    public void stopExposure() {
        AlpacaResponse response = call(getClient().stopExposure(getDeviceID(), getClientID(), getTransactionID()), "stopExposure");
    }

    public void stopExposure(AlpacaCallback<Void> callback) {
        callAsync(getClient().stopExposure(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "stopExposure");
    }
}
