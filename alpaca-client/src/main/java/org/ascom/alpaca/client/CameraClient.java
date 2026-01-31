package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Camera;
import org.ascom.alpaca.client.impl.ImageBytesConverterFactory;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.client.util.Logger;
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

    /**
     * Returns the X offset of the Bayer matrix.
     *
     * @return The X offset of the Bayer matrix.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BayerOffsetX">A full description of this member's behavior is provided here</a>
     */
    public int getBayerOffsetX() {
        IntResponse response = call(getClient().getBayerOffsetX(getDeviceID(), getClientID(), getTransactionID()), "getBayerOffsetX");
        return response.getValue();
    }

    /**
     * Returns the X offset of the Bayer matrix.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BayerOffsetX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the Y offset of the Bayer matrix.
     *
     * @return The Y offset of the Bayer matrix.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BayerOffsetY">A full description of this member's behavior is provided here</a>
     */
    public int getBayerOffsetY() {
        IntResponse response = call(getClient().getBayerOffsetY(getDeviceID(), getClientID(), getTransactionID()), "getBayerOffsetY");
        return response.getValue();
    }

    /**
     * Returns the Y offset of the Bayer matrix.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BayerOffsetY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the binning factor for the X axis.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BinX">A full description of this member's behavior is provided here</a>
     */
    public int getBinX() {
        IntResponse response = call(getClient().getBinX(getDeviceID(), getClientID(), getTransactionID()), "getBinX");
        return response.getValue();
    }

    /**
     * Returns the binning factor for the X axis.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BinX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the binning factor for the X axis.
     *
     * @param binX The X binning value
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BinX">A full description of this member's behavior is provided here</a>
     */
    public void setBinX(int binX) {
        AlpacaResponse response = call(getClient().setBinX(getDeviceID(), getClientID(), getTransactionID(), binX), "setBinX", binX);
    }

    /**
     * Sets the binning factor for the X axis.
     *
     * @param binX The X binning value
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BinX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the binning factor for the Y axis.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BinY">A full description of this member's behavior is provided here</a>
     */
    public int getBinY() {
        IntResponse response = call(getClient().getBinY(getDeviceID(), getClientID(), getTransactionID()), "getBinY");
        return response.getValue();
    }

    /**
     * Returns the binning factor for the Y axis.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BinY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the binning factor for the Y axis.
     *
     * @param binY The Y binning value
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BinY">A full description of this member's behavior is provided here</a>
     */
    public void setBinY(int binY) {
        AlpacaResponse response = call(getClient().setBinY(getDeviceID(), getClientID(), getTransactionID(), binY), "setBinY", binY);
    }

    /**
     * Sets the binning factor for the Y axis.
     *
     * @param binY The Y binning value
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.BinY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the camera operational state.
     *
     * @return The camera operational state.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CameraState">A full description of this member's behavior is provided here</a>
     */
    public CameraState getCameraState() {
        IntResponse response = call(getClient().getCameraState(getDeviceID(), getClientID(), getTransactionID()), "getCameraState");
        return CameraState.fromState(response.getValue());
    }

    /**
     * Returns the camera operational state.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CameraState">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the width of the CCD camera chip.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CameraXSize">A full description of this member's behavior is provided here</a>
     */
    public int getCameraXSize() {
        IntResponse response = call(getClient().getCameraXSize(getDeviceID(), getClientID(), getTransactionID()), "getCameraXSize");
        return response.getValue();
    }

    /**
     * Returns the width of the CCD camera chip.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CameraXSize">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the height of the CCD camera chip.
     *
     * @return The height of the CCD camera chip.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CameraYSize">A full description of this member's behavior is provided here</a>
     */
    public int getCameraYSize() {
        IntResponse response = call(getClient().getCameraYSize(getDeviceID(), getClientID(), getTransactionID()), "getCameraYSize");
        return response.getValue();
    }

    /**
     * Returns the height of the CCD camera chip.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CameraYSize">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates whether the camera can abort exposures.
     *
     * @return if the camera can abort exposures.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanAbortExposure">A full description of this member's behavior is provided here</a>
     */
    public boolean canAbortExposure() {
        BooleanResponse response = call(getClient().canAbortExposure(getDeviceID(), getClientID(), getTransactionID()), "canAbortExposure");
        return response.getValue();
    }

    /**
     * Indicates whether the camera can abort exposures.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanAbortExposure">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates whether the camera supports asymmetric binning.
     *
     * @return if the camera supports asymmetric binning.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanAsymmetricBin">A full description of this member's behavior is provided here</a>
     */
    public boolean canAsymmetricBin() {
        BooleanResponse response = call(getClient().canAsymmetricBin(getDeviceID(), getClientID(), getTransactionID()), "canAsymmetricBin");
        return response.getValue();
    }

    /**
     * Indicates whether the camera supports asymmetric binning.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanAsymmetricBin">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates whether the camera has a fast readout mode.
     *
     * @return if the camera has a fast readout mode.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanFastReadout">A full description of this member's behavior is provided here</a>
     */
    public boolean canFastReadout() {
        BooleanResponse response = call(getClient().canFastReadout(getDeviceID(), getClientID(), getTransactionID()), "canFastReadout");
        return response.getValue();
    }

    /**
     * Indicates whether the camera has a fast readout mode.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanFastReadout">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates whether the camera's cooler power setting can be read.
     *
     * @return if the camera's cooler power setting can be read.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanGetCoolerPower">A full description of this member's behavior is provided here</a>
     */
    public boolean canGetCoolerPower() {
        BooleanResponse response = call(getClient().canGetCoolerPower(getDeviceID(), getClientID(), getTransactionID()), "canGetCoolerPower");
        return response.getValue();
    }

    /**
     * Indicates whether the camera's cooler power setting can be read.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanGetCoolerPower">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates whether this camera supports pulse guiding.
     *
     * @return if this camera supports pulse guiding.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanPulseGuide">A full description of this member's behavior is provided here</a>
     */
    public boolean canPulseGuide() {
        BooleanResponse response = call(getClient().canPulseGuide(getDeviceID(), getClientID(), getTransactionID()), "canPulseGuide");
        return response.getValue();
    }

    /**
     * Indicates whether this camera supports pulse guiding.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanPulseGuide">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates whether this camera supports setting the CCD temperature.
     *
     * @return if this camera supports setting the CCD temperature.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanSetCCDTemperature">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetCCDTemperature() {
        BooleanResponse response = call(getClient().canSetCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), "canSetCCDTemperature");
        return response.getValue();
    }

    /**
     * Indicates whether this camera supports setting the CCD temperature.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanSetCCDTemperature">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates whether this camera can stop an exposure that is in progress.
     *
     * @return
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanStopExposure">A full description of this member's behavior is provided here</a>
     */
    public boolean canStopExposure() {
        BooleanResponse response = call(getClient().canStopExposure(getDeviceID(), getClientID(), getTransactionID()), "canStopExposure");
        return response.getValue();
    }

    /**
     * Indicates whether this camera can stop an exposure that is in progress.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CanStopExposure">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the current CCD temperature in degrees Celsius
     *
     * @return The current CCD temperature in degrees Celsius
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CCDTemperature">A full description of this member's behavior is provided here</a>
     */
    public double getCCDTemperature() {
        DoubleResponse response = call(getClient().getCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), "getCCDTemperature");
        return response.getValue();
    }

    /**
     * Returns the current CCD temperature in degrees Celsius
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CCDTemperature">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the current cooler on/off state.
     *
     * @return The current cooler on/off state.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CoolerOn">A full description of this member's behavior is provided here</a>
     */
    public boolean isCoolerOn() {
        BooleanResponse response = call(getClient().isCoolerOn(getDeviceID(), getClientID(), getTransactionID()), "isCoolerOn");
        return response.getValue();
    }

    /**
     * Returns the current cooler on/off state.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CoolerOn">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Turns the camera cooler on and off
     *
     * @param coolerOn Cooler state
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CoolerOn">A full description of this member's behavior is provided here</a>
     */
    public void setCoolerOn(boolean coolerOn) {
        AlpacaResponse response = call(getClient().setCoolerOn(getDeviceID(), getClientID(), getTransactionID(), coolerOn), "setCoolerOn", coolerOn);
    }

    /**
     * Turns the camera cooler on and off
     *
     * @param coolerOn Cooler state
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CoolerOn">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the present cooler power level
     *
     * @return The present cooler power level
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CoolerPower">A full description of this member's behavior is provided here</a>
     */
    public int getCoolerPower() {
        IntResponse response = call(getClient().getCoolerPower(getDeviceID(), getClientID(), getTransactionID()), "getCoolerPower");
        return response.getValue();
    }

    /**
     * Returns the present cooler power level
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.CoolerPower">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the gain of the camera in photoelectrons per A/D unit.
     *
     * @return The gain of the camera in photoelectrons per A/D unit.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ElectronsPerADU">A full description of this member's behavior is provided here</a>
     */
    public double getElectronsPerADU() {
        DoubleResponse response = call(getClient().getElectronsPerADU(getDeviceID(), getClientID(), getTransactionID()), "getElectronsPerADU");
        return response.getValue();
    }

    /**
     * Returns the gain of the camera in photoelectrons per A/D unit.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ElectronsPerADU">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the maximum exposure time supported by StartExposure.
     *
     * @return The maximum exposure time supported by StartExposure.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ExposureMax">A full description of this member's behavior is provided here</a>
     */
    public double getExposureMax() {
        DoubleResponse response = call(getClient().getExposureMax(getDeviceID(), getClientID(), getTransactionID()), "getExposureMax");
        return response.getValue();
    }

    /**
     * Returns the maximum exposure time supported by StartExposure.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ExposureMax">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the Minimium exposure time in seconds supported by StartExposure.
     *
     * @return The Minimium exposure time supported by StartExposure.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ExposureMin">A full description of this member's behavior is provided here</a>
     */
    public double getExposureMin() {
        DoubleResponse response = call(getClient().getExposureMin(getDeviceID(), getClientID(), getTransactionID()), "getExposureMin");
        return response.getValue();
    }

    /**
     * Returns the Minimium exposure time in seconds supported by StartExposure.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ExposureMin">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the smallest increment in exposure time in seconds supported by StartExposure.
     *
     * @return The smallest increment in exposure time supported by StartExposure.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ExposureResolution">A full description of this member's behavior is provided here</a>
     */
    public double getExposureResolution() {
        DoubleResponse response = call(getClient().getExposureResolution(getDeviceID(), getClientID(), getTransactionID()), "getExposureResolution");
        return response.getValue();
    }

    /**
     * Returns the smallest increment in exposure time in seconds supported by StartExposure.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ExposureResolution">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns whether Fast Readout Mode is enabled.
     *
     * @return Whether Fast Readout Mode is enabled.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.FastReadout">A full description of this member's behavior is provided here</a>
     */
    public boolean getFastReadout() {
        BooleanResponse response = call(getClient().getFastReadout(getDeviceID(), getClientID(), getTransactionID()), "getFastReadout");
        return response.getValue();
    }

    /**
     * Returns whenther Fast Readout Mode is enabled.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.FastReadout">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets whether Fast Readout Mode is enabled.
     *
     * @param fastReadout True to enable fast readout mode
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.FastReadout">A full description of this member's behavior is provided here</a>
     */
    public void setFastReadout(boolean fastReadout) {
        AlpacaResponse response = call(getClient().setFastReadout(getDeviceID(), getClientID(), getTransactionID(), fastReadout), "setFastReadout", fastReadout);
    }

    /**
     * Sets whether Fast Readout Mode is enabled.
     *
     * @param fastReadout True to enable fast readout mode
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.FastReadout">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Reports the full well capacity of the camera in electrons, at the current camera settings.
     *
     * @return The full well capacity of the camera in electrons, at the current camera settings.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.FullWellCapacity">A full description of this member's behavior is provided here</a>
     */
    public double getFullWellCapacity() {
        DoubleResponse response = call(getClient().getFullWellCapacity(getDeviceID(), getClientID(), getTransactionID()), "getFullWellCapacity");
        return response.getValue();
    }

    /**
     * Reports the full well capacity of the camera in electrons, at the current camera settings.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.FullWellCapacity">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the camera's gain (GAIN VALUE MODE) OR the index of the selected camera gain description in the Gains array (GAINS INDEX MODE).
     *
     * @return The camera's gain
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Gain">A full description of this member's behavior is provided here</a>
     */
    public int getGain() {
        IntResponse response = call(getClient().getGain(getDeviceID(), getClientID(), getTransactionID()), "getGain");
        return response.getValue();
    }

    /**
     * Returns the camera's gain (GAIN VALUE MODE) OR the index of the selected camera gain description in the Gains array (GAINS INDEX MODE).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Gain">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the camera's gain (GAIN VALUE MODE) OR the index of the selected camera gain description in the Gains array (GAINS INDEX MODE).
     *
     * @param gain Index of the current camera gain in the Gains string array.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Gain">A full description of this member's behavior is provided here</a>
     */
    public void setGain(int gain) {
        AlpacaResponse response = call(getClient().setGain(getDeviceID(), getClientID(), getTransactionID(), gain), "setGain", gain);
    }

    /**
     * Sets the camera's gain (GAIN VALUE MODE) OR the index of the selected camera gain description in the Gains array (GAINS INDEX MODE).
     *
     * @param gain Index of the current camera gain in the Gains string array.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Gain">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Maximum Gain value of that this camera supports
     *
     * @return Maximum Gain value of that this camera supports
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.GainMax">A full description of this member's behavior is provided here</a>
     */
    public int getGainMax() {
        IntResponse response = call(getClient().getGainMax(getDeviceID(), getClientID(), getTransactionID()), "getGainMax");
        return response.getValue();
    }

    /**
     * Maximum Gain value of that this camera supports
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.GainMax">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Minimum Gain value of that this camera supports
     *
     * @return Minimum Gain value of that this camera supports
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.GainMin">A full description of this member's behavior is provided here</a>
     */
    public int getGainMin() {
        IntResponse response = call(getClient().getGainMin(getDeviceID(), getClientID(), getTransactionID()), "getGainMin");
        return response.getValue();
    }

    /**
     * Minimum Gain value of that this camera supports
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.GainMin">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * List of Gain names supported by the camera
     *
     * @return List of Gain names supported by the camera
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Gains">A full description of this member's behavior is provided here</a>
     */
    public List<String> getGains() {
        ListResponse<String> response = call(getClient().getGains(getDeviceID(), getClientID(), getTransactionID()), "getGains");
        return response.getValue();
    }

    /**
     * List of Gain names supported by the camera
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Gains">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates whether the camera has a mechanical shutter
     *
     * @return True if the camera has a mechanical shutter, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.HasShutter">A full description of this member's behavior is provided here</a>
     */
    public boolean hasShutter() {
        BooleanResponse response = call(getClient().hasShutter(getDeviceID(), getClientID(), getTransactionID()), "hasShutter");
        return response.getValue();
    }

    /**
     * Indicates whether the camera has a mechanical shutter
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.HasShutter">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the current heat sink temperature in degrees Celsius.
     *
     * @return Current heat sink temperature in degrees Celsius
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.HeatSinkTemperature">A full description of this member's behavior is provided here</a>
     */
    public double getHeatSinkTemperature() {
        DoubleResponse response = call(getClient().getHeatSinkTemperature(getDeviceID(), getClientID(), getTransactionID()), "getHeatSinkTemperature");
        return response.getValue();
    }

    /**
     * Returns the current heat sink temperature in degrees Celsius.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.HeatSinkTemperature">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns an array of integers containing the exposure pixel values. If the media
     * type is
     * @param mediaType the type of array to return
     * @return the image array
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ImageArray">A full description of this member's behavior is provided here</a>
     * // TODO:  figure out this mediaType stuff
     */
    ImageArray getImageArray(String mediaType) {
        ImageArrayResponse response = call(getClient().getImageArray(mediaType, getDeviceID(), getClientID(), getTransactionID()), "getImageArray");
        return new ImageArray(response.getType(), response.getRank(), response.getValue());
    }

    /**
     * Returns an array of integers containing the exposure pixel values
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ImageArray">A full description of this member's behavior is provided here</a>
     * @see <a href="https://ascom-standards.org/api/#/Camera%20Specific%20Methods/get_camera__device_number__imagearray">Alpaca specific details is provided here</a>
     */
    public ImageArray getImageArray() {
        return getImageArray("application/json");
    }

    /**
     * Returns an array of integers containing the exposure pixel values
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ImageArray">A full description of this member's behavior is provided here</a>
     * @see <a href="https://ascom-standards.org/api/#/Camera%20Specific%20Methods/get_camera__device_number__imagearray">Alpaca specific details is provided here</a>
     */
    void getImageArray(String mediaType, AlpacaCallback<ImageArray> callback) {
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

    /**
     * Returns an array of integers containing the exposure pixel values
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ImageArray">A full description of this member's behavior is provided here</a>
     * @see <a href="https://ascom-standards.org/api/#/Camera%20Specific%20Methods/get_camera__device_number__imagearray">Alpaca specific details is provided here</a>
     */
    public void getImageArray(AlpacaCallback<ImageArray> callback) {
        getImageArray("application/json", callback);
    }

    // TODO:  flesh this out
    public ImageArray getImageBytes() {
        return getImageArray("application/imagebytes");
    }

    public void getImageBytes(AlpacaCallback<ImageArray> callback) {
        getImageArray("application/imagebytes", callback);
    }

    /**
     * Indicates that an image is ready to be downloaded
     *
     * @return True if an image is ready to be downloaded, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ImageReady">A full description of this member's behavior is provided here</a>
     */
    public boolean isImageReady() {
        BooleanResponse response = call(getClient().isImageReady(getDeviceID(), getClientID(), getTransactionID()), "isImageReady");
        return response.getValue();
    }

    /**
     * Indicates that an image is ready to be downloaded
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ImageReady">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates that the camera is pulse guideing.
     *
     * @return True if the camera is pulse guideing, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.IsPulseGuiding">A full description of this member's behavior is provided here</a>
     */
    public boolean isPulseGuiding() {
        BooleanResponse response = call(getClient().isPulseGuiding(getDeviceID(), getClientID(), getTransactionID()), "isPulseGuiding");
        return response.getValue();
    }

    /**
     * Indicates that the camera is pulse guideing.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.IsPulseGuiding">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Duration of the last exposure (seconds)
     *
     * @return The duration of the last exposure in seconds
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.LastExposureDuration">A full description of this member's behavior is provided here</a>
     */
    public double getLastExposureDuration() {
        DoubleResponse response = call(getClient().getLastExposureDuration(getDeviceID(), getClientID(), getTransactionID()), "getLastExposureDuration");
        return response.getValue();
    }

    /**
     * Duration of the last exposure (seconds)
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.LastExposureDuration">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Start time of the last exposure in FITS standard format CCYY-MM-DDThh:mm:ss[.sss...]. The time must be UTC.
     *
     * @return The start time of the last exposure in FITS standard format CCYY-MM-DDThh:mm:ss[.sss...]
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.LastExposureStartTime">A full description of this member's behavior is provided here</a>
     */
    public String getLastExposureStartTime() {
        StringResponse response = call(getClient().getLastExposureStartTime(getDeviceID(), getClientID(), getTransactionID()), "getLastExposureStartTime");
        return response.getValue();
    }

    /**
     * Start time of the last exposure in FITS standard format CCYY-MM-DDThh:mm:ss[.sss...]. The time must be UTC.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.LastExposureStartTime">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Camera's maximum ADU value
     *
     * @return Camera's maximum ADU value
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.MaxADU">A full description of this member's behavior is provided here</a>
     */
    public int getMaxADU() {
        IntResponse response = call(getClient().getMaxADU(getDeviceID(), getClientID(), getTransactionID()), "getMaxADU");
        return response.getValue();
    }

    /**
     * Camera's maximum ADU value
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.MaxADU">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Maximum binning for the camera X axis
     *
     * @return Maximum binning for the camera X axis
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.MaxBinX">A full description of this member's behavior is provided here</a>
     */
    public int getMaxBinX() {
        IntResponse response = call(getClient().getMaxBinX(getDeviceID(), getClientID(), getTransactionID()), "getMaxBinX");
        return response.getValue();
    }

    /**
     * Maximum binning for the camera X axis
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.MaxBinX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Maximum binning for the camera Y axis
     *
     * @return Maximum binning for the camera Y axis
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.MaxBinY">A full description of this member's behavior is provided here</a>
     */
    public int getMaxBinY() {
        IntResponse response = call(getClient().getMaxBinY(getDeviceID(), getClientID(), getTransactionID()), "getMaxBinY");
        return response.getValue();
    }

    /**
     * Maximum binning for the camera Y axis
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.MaxBinY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the current subframe width in binned pixels.
     *
     * @return The current subframe width in binned pixels.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.NumX">A full description of this member's behavior is provided here</a>
     */
    public int getNumX() {
        IntResponse response = call(getClient().getNumX(getDeviceID(), getClientID(), getTransactionID()), "getNumX");
        return response.getValue();
    }

    /**
     * Returns the current subframe width in binned pixels.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.NumX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the current subframe width in binned pixels.
     *
     * @param numX Sets the subframe width, if binning is active, value is in binned pixels.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.NumX">A full description of this member's behavior is provided here</a>
     */
    public void setNumX(int numX) {
        AlpacaResponse response = call(getClient().setNumX(getDeviceID(), getClientID(), getTransactionID(), numX), "setNumX", numX);
    }

    /**
     * Sets the current subframe width in binned pixels.
     *
     * @param numX Sets the subframe width, if binning is active, value is in binned pixels.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.NumX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the current subframe height in binned pixels.
     *
     * @return The current subframe height in binned pixels.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.NumY">A full description of this member's behavior is provided here</a>
     */
    public int getNumY() {
        IntResponse response = call(getClient().getNumY(getDeviceID(), getClientID(), getTransactionID()), "getNumY");
        return response.getValue();
    }

    /**
     * Returns the current subframe height in binned pixels.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.NumY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the current subframe height in binned pixels.
     *
     * @param numY Sets the subframe height, if binning is active, value is in binned pixels.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.NumY">A full description of this member's behavior is provided here</a>
     */
    public void setNumY(int numY) {
        AlpacaResponse response = call(getClient().setNumY(getDeviceID(), getClientID(), getTransactionID(), numY), "setNumY", numY);
    }

    /**
     * Sets the current subframe height in binned pixels.
     *
     * @param numY Sets the subframe height, if binning is active, value is in binned pixels.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.NumY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the camera's offset (OFFSET VALUE MODE) OR the index of the selected camera offset description in the offsets array (OFFSETS INDEX MODE).
     *
     * @return The camera's offset
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Offset">A full description of this member's behavior is provided here</a>
     */
    public int getOffset() {
        IntResponse response = call(getClient().getOffset(getDeviceID(), getClientID(), getTransactionID()), "getOffset");
        return response.getValue();
    }

    /**
     * Returns the camera's offset (OFFSET VALUE MODE) OR the index of the selected camera offset description in the offsets array (OFFSETS INDEX MODE).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Offset">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the camera's offset (OFFSET VALUE MODE) OR the index of the selected camera offset description in the offsets array (OFFSETS INDEX MODE).
     *
     * @param offset Index of the current camera offset in the offsets string array.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Offset">A full description of this member's behavior is provided here</a>
     */
    public void setOffset(int offset) {
        AlpacaResponse response = call(getClient().setOffset(getDeviceID(), getClientID(), getTransactionID(), offset), "setOffset", offset);
    }

    /**
     * Sets the camera's offset (OFFSET VALUE MODE) OR the index of the selected camera offset description in the offsets array (OFFSETS INDEX MODE).
     *
     * @param offset Index of the current camera offset in the offsets string array.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Offset">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Maximum offset value of that this camera supports
     *
     * @return Maximum offset value of that this camera supports
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.OffsetMax">A full description of this member's behavior is provided here</a>
     */
    public int getOffsetMax() {
        IntResponse response = call(getClient().getOffsetMax(getDeviceID(), getClientID(), getTransactionID()), "getOffsetMax");
        return response.getValue();
    }

    /**
     * Maximum offset value of that this camera supports
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.OffsetMax">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Minimum offset value of that this camera supports
     *
     * @return Minimum offset value of that this camera supports
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.OffsetMin">A full description of this member's behavior is provided here</a>
     */
    public int getOffsetMin() {
        IntResponse response = call(getClient().getOffsetMin(getDeviceID(), getClientID(), getTransactionID()), "getOffsetMin");
        return response.getValue();
    }

    /**
     * Minimum offset value of that this camera supports
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.OffsetMin">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * List of offset names supported by the camera
     *
     * @return List of offset names supported by the camera
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Offsets">A full description of this member's behavior is provided here</a>
     */
    public List<String> getOffsets() {
        ListResponse<String> response = call(getClient().getOffsets(getDeviceID(), getClientID(), getTransactionID()), "getOffsets");
        return response.getValue();
    }

    /**
     * List of offset names supported by the camera
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.Offsets">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates percentage completeness of the current operation
     *
     * @return Indicates percentage completeness of the current operation
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.PercentCompleted">A full description of this member's behavior is provided here</a>
     */
    public int getPercentCompleted() {
        IntResponse response = call(getClient().getPercentCompleted(getDeviceID(), getClientID(), getTransactionID()), "getPercentCompleted");
        return response.getValue();
    }

    /**
     * Indicates percentage completeness of the current operation
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.PercentCompleted">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Width of CCD chip pixels (microns)
     *
     * @return Width of CCD chip pixels (microns)
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.PixelSizeX">A full description of this member's behavior is provided here</a>
     */
    public double getPixelSizeX() {
        DoubleResponse response = call(getClient().getPixelSizeX(getDeviceID(), getClientID(), getTransactionID()), "getPixelSizeX");
        return response.getValue();
    }

    /**
     * Width of CCD chip pixels (microns)
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.PixelSizeX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Height of CCD chip pixels (microns)
     *
     * @return Height of CCD chip pixels (microns)
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.PixelSizeY">A full description of this member's behavior is provided here</a>
     */
    public double getPixelSizeY() {
        DoubleResponse response = call(getClient().getPixelSizeY(getDeviceID(), getClientID(), getTransactionID()), "getPixelSizeY");
        return response.getValue();
    }

    /**
     * Height of CCD chip pixels (microns)
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.PixelSizeY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Indicates the canera's readout mode as an index into the array ReadoutModes
     *
     * @return Indicates the canera's readout mode as an index into the array ReadoutModes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ReadoutMode">A full description of this member's behavior is provided here</a>
     */
    public int getReadoutMode() {
        IntResponse response = call(getClient().getReadoutMode(getDeviceID(), getClientID(), getTransactionID()), "getReadoutMode");
        return response.getValue();
    }

    /**
     * Indicates the canera's readout mode as an index into the array ReadoutModes
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ReadoutMode">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Set the camera's readout mode
     *
     * @param readoutMode Index into the ReadoutModes array of string readout mode names indicating the camera's current readout mode.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ReadoutMode">A full description of this member's behavior is provided here</a>
     */
    public void setReadoutMode(int readoutMode) {
        AlpacaResponse response = call(getClient().setReadoutMode(getDeviceID(), getClientID(), getTransactionID(), readoutMode), "setReadoutMode", readoutMode);
    }

    /**
     * Set the camera's readout mode
     *
     * @param readoutMode Index into the ReadoutModes array of string readout mode names indicating the camera's current readout mode.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ReadoutMode">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns a string list of available readout modes
     *
     * @return A string list of available readout modes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ReadoutModes">A full description of this member's behavior is provided here</a>
     */
    public List<String> getReadoutModes() {
        ListResponse<String> response = call(getClient().getReadoutModes(getDeviceID(), getClientID(), getTransactionID()), "getReadoutModes");
        return response.getValue();
    }

    /**
     * Returns a string list of available readout modes
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.ReadoutModes">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the camera's sensor name
     *
     * @return The camera's sensor name
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SensorName">A full description of this member's behavior is provided here</a>
     */
    public String getSensorName() {
        StringResponse response = call(getClient().getSensorName(getDeviceID(), getClientID(), getTransactionID()), "getSensorName");
        return response.getValue();
    }

    /**
     * Returns the camera's sensor name
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SensorName">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns a value indicating whether the sensor is monochrome, or what Bayer matrix it encodes.
     *
     * @return the sensor type
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SensorType">A full description of this member's behavior is provided here</a>
     */
    public SensorType getSensorType() {
        IntResponse response = call(getClient().getSensorType(getDeviceID(), getClientID(), getTransactionID()), "getSensorType");
        return SensorType.fromType(response.getValue());
    }

    /**
     * Returns a value indicating whether the sensor is monochrome, or what Bayer matrix it encodes.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SensorType">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the current camera cooler setpoint in degrees Celsius.
     *
     * @return The current camera cooler setpoint in degrees Celsius
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SetCCDTemperature">A full description of this member's behavior is provided here</a>
     */
    public double getSetCCDTemperature() {
        DoubleResponse response = call(getClient().getSetCCDTemperature(getDeviceID(), getClientID(), getTransactionID()), "getSetCCDTemperature");
        return response.getValue();
    }

    /**
     * Returns the current camera cooler setpoint in degrees Celsius.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SetCCDTemperature">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Set the camera's cooler setpoint (degrees Celsius).
     *
     * @param setCCDTemperature Temperature set point (degrees Celsius).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SetCCDTemperature">A full description of this member's behavior is provided here</a>
     */
    public void setCCDTemperature(double setCCDTemperature) {
        AlpacaResponse response = call(getClient().setCCDTemperature(getDeviceID(), getClientID(), getTransactionID(), setCCDTemperature), "setCCDTemperature", setCCDTemperature);
    }

    /**
     * Set the camera's cooler setpoint (degrees Celsius).
     *
     * @param setCCDTemperature Temperature set point (degrees Celsius).
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SetCCDTemperature">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the current subframe X axis start position in binned pixels
     *
     * @return The current subframe X axis start position in binned pixels
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartX">A full description of this member's behavior is provided here</a>
     */
    public int getStartX() {
        IntResponse response = call(getClient().getStartX(getDeviceID(), getClientID(), getTransactionID()), "getStartX");
        return response.getValue();
    }

    /**
     * Returns the current subframe X axis start position in binned pixels
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the current subframe X axis start position in binned pixels
     *
     * @param startX The subframe X axis start position in binned pixels.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartX">A full description of this member's behavior is provided here</a>
     */
    public void setStartX(int startX) {
        AlpacaResponse response = call(getClient().setStartX(getDeviceID(), getClientID(), getTransactionID(), startX), "setStartX", startX);
    }

    /**
     * Sets the current subframe X axis start position in binned pixels
     *
     * @param startX The subframe X axis start position in binned pixels.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartX">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Returns the current subframe Y axis start position in binned pixels
     *
     * @return The current subframe Y axis start position in binned pixels
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartY">A full description of this member's behavior is provided here</a>
     */
    public int getStartY() {
        IntResponse response = call(getClient().getStartY(getDeviceID(), getClientID(), getTransactionID()), "getStartY");
        return response.getValue();
    }

    /**
     * Returns the current subframe Y axis start position in binned pixels
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the current subframe Y axis start position in bined pixels
     *
     * @param startY The subframe Y axis start position in binned pixels.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartY">A full description of this member's behavior is provided here</a>
     */
    public void setStartY(int startY) {
        AlpacaResponse response = call(getClient().setStartY(getDeviceID(), getClientID(), getTransactionID(), startY), "setStartY", startY);
    }

    /**
     * Sets the current subframe Y axis start position in bined pixels
     *
     * @param startY The subframe Y axis start position in binned pixels.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartY">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Camera's sub-exposure interval
     *
     * @return Camera's sub-exposure interval
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SubExposureDuration">A full description of this member's behavior is provided here</a>
     */
    public double getSubExposureDuration() {
        DoubleResponse response = call(getClient().getSubExposureDuration(getDeviceID(), getClientID(), getTransactionID()), "getSubExposureDuration");
        return response.getValue();
    }

    /**
     * Camera's sub-exposure interval
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SubExposureDuration">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Sets the current Sub Exposure Duration
     *
     * @param subExposureDuration The request sub exposure duration in seconds
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SubExposureDuration">A full description of this member's behavior is provided here</a>
     */
    public void setSubExposureDuration(double subExposureDuration) {
        AlpacaResponse response = call(getClient().setSubExposureDuration(getDeviceID(), getClientID(), getTransactionID(), subExposureDuration), "setSubExposureDuration", subExposureDuration);
    }

    /**
     * Sets the current Sub Exposure Duration
     *
     * @param subExposureDuration The request sub exposure duration in seconds
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.SubExposureDuration">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Aborts the current exposure and returns the camera to Idle state.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.AbortExposure">A full description of this member's behavior is provided here</a>
     */
    public void abortExposure() {
        AlpacaResponse response = call(getClient().abortExposure(getDeviceID(), getClientID(), getTransactionID()), "abortExposure");
    }

    /**
     * Aborts the current exposure and returns the camera to Idle state.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.AbortExposure">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Pulse guide in the specified direction for the specified time.
     *
     * @param direction
     * @param duration Duration of movement in milli-seconds
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.PulseGuide">A full description of this member's behavior is provided here</a>
     */
    public void pulseGuide(int direction, int duration) {
        AlpacaResponse response = call(getClient().pulseGuide(getDeviceID(), getClientID(), getTransactionID(), direction, duration), "pulseGuide", direction, duration);
    }

    /**
     * Pulse guide in the specified direction for the specified time.
     *
     * @param direction
     * @param duration Duration of movement in milli-seconds
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.PulseGuide">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Starts an exposure
     *
     * @param duration Duration of exposure in seconds
     * @param light True if light frame, false if dark frame.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartExposure">A full description of this member's behavior is provided here</a>
     */
    public void startExposure(double duration, boolean light) {
        AlpacaResponse response = call(getClient().startExposure(getDeviceID(), getClientID(), getTransactionID(), duration, light), "startExposure", duration, light);
    }

    /**
     * Starts an exposure
     *
     * @param duration Duration of exposure in seconds
     * @param light True if light frame, false if dark frame.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StartExposure">A full description of this member's behavior is provided here</a>
     */
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

    /**
     * Stops the current exposure
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StopExposure">A full description of this member's behavior is provided here</a>
     */
    public void stopExposure() {
        AlpacaResponse response = call(getClient().stopExposure(getDeviceID(), getClientID(), getTransactionID()), "stopExposure");
    }

    /**
     * Stops the current exposure
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/camera.html#Camera.StopExposure">A full description of this member's behavior is provided here</a>
     */
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
