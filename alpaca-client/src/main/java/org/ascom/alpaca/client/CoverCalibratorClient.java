package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.CoverCalibrator;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.CalibratorState;
import org.ascom.alpaca.model.CoverState;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.ValueResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class CoverCalibratorClient extends CommonClient {
    private static final Logger log = Logger.getLogger(CoverCalibratorClient.class);
    private final URI serverAddress;
    private CoverCalibrator client = null;

    public CoverCalibratorClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public CoverCalibratorClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private CoverCalibrator getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(CoverCalibrator.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for CoverCalibrator - " + e.getMessage());
            }
        }
        return client;
    }

    /**
     * Returns the current calibrator brightness
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.Brightness">A full description of this member's behavior is provided here</a>
     */
    public int getBrightness() {
        IntResponse response = call(getClient().getBrightness(getDeviceID(), getClientID(), getTransactionID()), "getBrightness");
        return response.getValue();
    }

    /**
     * Returns the current calibrator brightness
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.Brightness">A full description of this member's behavior is provided here</a>
     */
    public void getBrightness(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getBrightness(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getBrightness");
    }

    /**
     * Completion variable for calibrator brightness changes.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CalibratorChanging">A full description of this member's behavior is provided here</a>
     */
    public boolean isCalibratorChanging() {
        BooleanResponse response = call(getClient().isCalibratorChanging(getDeviceID(), getClientID(), getTransactionID()), "isCalibratorChanging");
        return response.getValue();
    }

    /**
     * Completion variable for calibrator brightness changes.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CalibratorChanging">A full description of this member's behavior is provided here</a>
     */
    public void isCalibratorChanging(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isCalibratorChanging(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isCalibratorChanging");
    }

    /**
     * Returns the state of the calibration device
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CalibratorState">A full description of this member's behavior is provided here</a>
     */
    public CalibratorState getCalibratorState() {
        ValueResponse<CalibratorState> response = call(getClient().getCalibratorState(getDeviceID(), getClientID(), getTransactionID()), "getCalibratorState");
        return response.getValue();
    }

    /**
     * Returns the state of the calibration device
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CalibratorState">A full description of this member's behavior is provided here</a>
     */
    public void getCalibratorState(AlpacaCallback<CalibratorState> callback) {
        callAsync(getClient().getCalibratorState(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ValueResponse<CalibratorState> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCalibratorState");
    }

    /**
     * Completion variable for cover open and close operations.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CoverMoving">A full description of this member's behavior is provided here</a>
     */
    public boolean isCoverMoving() {
        BooleanResponse response = call(getClient().isCoverMoving(getDeviceID(), getClientID(), getTransactionID()), "isCoverMoving");
        return response.getValue();
    }

    /**
     * Completion variable for cover open and close operations.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CoverMoving">A full description of this member's behavior is provided here</a>
     */
    public void isCoverMoving(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isCoverMoving(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isCoverMoving");
    }

    /**
     * Returns the state of the device cover"
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CoverState">A full description of this member's behavior is provided here</a>
     */
    public CoverState getCoverState() {
        ValueResponse<CoverState> response = call(getClient().getCoverState(getDeviceID(), getClientID(), getTransactionID()), "getCoverState");
        return response.getValue();
    }

    /**
     * Returns the state of the device cover"
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CoverState">A full description of this member's behavior is provided here</a>
     */
    public void getCoverState(AlpacaCallback<CoverState> callback) {
        callAsync(getClient().getCoverState(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ValueResponse<CoverState> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCoverState");
    }

    /**
     * Returns the calibrator's maximum Brightness value.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.MaxBrightness">A full description of this member's behavior is provided here</a>
     */
    public int getMaxBrightness() {
        IntResponse response = call(getClient().getMaxBrightness(getDeviceID(), getClientID(), getTransactionID()), "getMaxBrightness");
        return response.getValue();
    }

    /**
     * Returns the calibrator's maximum Brightness value.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.MaxBrightness">A full description of this member's behavior is provided here</a>
     */
    public void getMaxBrightness(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getMaxBrightness(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getMaxBrightness");
    }

    /**
     * Turns the calibrator off
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CalibratorOff">A full description of this member's behavior is provided here</a>
     */
    public void turnCalibratorOff() {
        AlpacaResponse response = call(getClient().turnCalibratorOff(getDeviceID(), getClientID(), getTransactionID()), "turnCalibratorOff");
    }

    /**
     * Turns the calibrator off
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CalibratorOff">A full description of this member's behavior is provided here</a>
     */
    public void turnCalibratorOff(AlpacaCallback<Void> callback) {
        callAsync(getClient().turnCalibratorOff(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "turnCalibratorOff");
    }

    /**
     * Turns the calibrator on at the specified brightness
     *
     * @param brightness The required brightness in the range 0 to MaxBrightness
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CalibratorOn">A full description of this member's behavior is provided here</a>
     */
    public void turnCalibratorOn(int brightness) {
        AlpacaResponse response = call(getClient().turnCalibratorOn(getDeviceID(), getClientID(), getTransactionID(), brightness), "turnCalibratorOn", brightness);
    }

    /**
     * Turns the calibrator on at the specified brightness
     *
     * @param brightness The required brightness in the range 0 to MaxBrightness
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CalibratorOn">A full description of this member's behavior is provided here</a>
     */
    public void turnCalibratorOn(int brightness, AlpacaCallback<Void> callback) {
        callAsync(getClient().turnCalibratorOn(getDeviceID(), getClientID(), getTransactionID(), brightness), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "turnCalibratorOn", brightness);
    }

    /**
     * Initiates cover closing
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CloseCover">A full description of this member's behavior is provided here</a>
     */
    public void closeCover() {
        AlpacaResponse response = call(getClient().closeCover(getDeviceID(), getClientID(), getTransactionID()), "closeCover");
    }

    /**
     * Initiates cover closing
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.CloseCover">A full description of this member's behavior is provided here</a>
     */
    public void closeCover(AlpacaCallback<Void> callback) {
        callAsync(getClient().closeCover(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "closeCover");
    }

    /**
     * Stops any cover movement that may be in progress
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.HaltCover">A full description of this member's behavior is provided here</a>
     */
    public void haltCover() {
        AlpacaResponse response = call(getClient().haltCover(getDeviceID(), getClientID(), getTransactionID()), "haltCover");
    }

    /**
     * Stops any cover movement that may be in progress
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.HaltCover">A full description of this member's behavior is provided here</a>
     */
    public void haltCover(AlpacaCallback<Void> callback) {
        callAsync(getClient().haltCover(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "haltCover");
    }

    /**
     * Initiates cover opening.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.OpenCover">A full description of this member's behavior is provided here</a>
     */
    public void openCover() {
        AlpacaResponse response = call(getClient().openCover(getDeviceID(), getClientID(), getTransactionID()), "openCover");
    }

    /**
     * Initiates cover opening.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/covercalibrator.html#CoverCalibrator.OpenCover">A full description of this member's behavior is provided here</a>
     */
    public void openCover(AlpacaCallback<Void> callback) {
        callAsync(getClient().openCover(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "openCover");
    }
}