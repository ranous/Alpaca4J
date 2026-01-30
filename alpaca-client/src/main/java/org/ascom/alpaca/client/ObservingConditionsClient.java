package org.ascom.alpaca.client;

import org.ascom.alpaca.api.ObservingConditions;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.StringResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

/**
 * Client for interacting with an ASCOM Alpaca ObservingConditions device.
 */
@SuppressWarnings("unused")
public class ObservingConditionsClient extends CommonClient {
    private static final Logger log = Logger.getLogger(ObservingConditionsClient.class);
    private final URI serverAddress;
    private ObservingConditions client = null;

    public ObservingConditionsClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public ObservingConditionsClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private ObservingConditions getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(ObservingConditions.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for ObservingConditions - " + e.getMessage());
            }
        }
        return client;
    }

    /**
     * Returns the time period over which observations will be averaged
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.AveragePeriod">A full description of this member's behavior is provided here</a>
     */
    public double getAveragePeriod() {
        DoubleResponse response = call(getClient().getAveragePeriod(getDeviceID(), getClientID(), getTransactionID()), "getAveragePeriod");
        return response.getValue();
    }

    /**
     * Returns the time period over which observations will be averaged
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.AveragePeriod">A full description of this member's behavior is provided here</a>
     */
    public void getAveragePeriod(AlpacaCallback<Double> callback) {
        callAsync(getClient().getAveragePeriod(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getAveragePeriod");
    }

    /**
     * Sets the time period over which observations will be averaged
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.AveragePeriod">A full description of this member's behavior is provided here</a>
     */
    public void setAveragePeriod(double averagePeriod) {
        AlpacaResponse response = call(getClient().setAveragePeriod(getDeviceID(), averagePeriod, getClientID(), getTransactionID()), "setAveragePeriod", averagePeriod);
    }

    /**
     * Sets the time period over which observations will be averaged
     *
     * @param averagePeriod The new average period
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.AveragePeriod">A full description of this member's behavior is provided here</a>
     */
    public void setAveragePeriod(double averagePeriod, AlpacaCallback<Void> callback) {
        callAsync(getClient().setAveragePeriod(getDeviceID(), averagePeriod, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setAveragePeriod", averagePeriod);
    }

    /**
     * Returns the amount of sky obscured by cloud
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.CloudCover">A full description of this member's behavior is provided here</a>
     */
    public double getCloudCover() {
        DoubleResponse response = call(getClient().getCloudCover(getDeviceID(), getClientID(), getTransactionID()), "getCloudCover");
        return response.getValue();
    }

    /**
     * Returns the amount of sky obscured by cloud
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.CloudCover">A full description of this member's behavior is provided here</a>
     */
    public void getCloudCover(AlpacaCallback<Double> callback) {
        callAsync(getClient().getCloudCover(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getCloudCover");
    }

    /**
     * Returns the atmospheric dew point at the observatory
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.DewPoint">A full description of this member's behavior is provided here</a>
     */
    public double getDewPoint() {
        DoubleResponse response = call(getClient().getDewPoint(getDeviceID(), getClientID(), getTransactionID()), "getDewPoint");
        return response.getValue();
    }

    /**
     * Returns the atmospheric dew point at the observatory
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.DewPoint">A full description of this member's behavior is provided here</a>
     */
    public void getDewPoint(AlpacaCallback<Double> callback) {
        callAsync(getClient().getDewPoint(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getDewPoint");
    }

    /**
     * Returns the atmospheric humidity at the observatory
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Humidity">A full description of this member's behavior is provided here</a>
     */
    public double getHumidity() {
        DoubleResponse response = call(getClient().getHumidity(getDeviceID(), getClientID(), getTransactionID()), "getHumidity");
        return response.getValue();
    }

    /**
     * Returns the atmospheric humidity at the observatory
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Humidity">A full description of this member's behavior is provided here</a>
     */
    public void getHumidity(AlpacaCallback<Double> callback) {
        callAsync(getClient().getHumidity(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getHumidity");
    }

    /**
     * Returns the atmospheric pressure at the observatory.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Pressure">A full description of this member's behavior is provided here</a>
     */
    public double getPressure() {
        DoubleResponse response = call(getClient().getPressure(getDeviceID(), getClientID(), getTransactionID()), "getPressure");
        return response.getValue();
    }

    /**
     * Returns the atmospheric pressure at the observatory.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Pressure">A full description of this member's behavior is provided here</a>
     */
    public void getPressure(AlpacaCallback<Double> callback) {
        callAsync(getClient().getPressure(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getPressure");
    }

    /**
     * Returns the rain rate at the observatory.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.RainRate">A full description of this member's behavior is provided here</a>
     */
    public double getRainRate() {
        DoubleResponse response = call(getClient().getRainRate(getDeviceID(), getClientID(), getTransactionID()), "getRainRate");
        return response.getValue();
    }

    /**
     * Returns the rain rate at the observatory.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.RainRate">A full description of this member's behavior is provided here</a>
     */
    public void getRainRate(AlpacaCallback<Double> callback) {
        callAsync(getClient().getRainRate(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getRainRate");
    }

    /**
     * Returns the sky brightness at the observatory
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyBrightness">A full description of this member's behavior is provided here</a>
     */
    public double getSkyBrightness() {
        DoubleResponse response = call(getClient().getSkyBrightness(getDeviceID(), getClientID(), getTransactionID()), "getSkyBrightness");
        return response.getValue();
    }

    /**
     * Returns the sky brightness at the observatory
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyBrightness">A full description of this member's behavior is provided here</a>
     */
    public void getSkyBrightness(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSkyBrightness(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSkyBrightness");
    }

    /**
     * Returns the sky quality at the observatory
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyQuality">A full description of this member's behavior is provided here</a>
     */
    public double getSkyQuality() {
        DoubleResponse response = call(getClient().getSkyQuality(getDeviceID(), getClientID(), getTransactionID()), "getSkyQuality");
        return response.getValue();
    }

    /**
     * Returns the sky quality at the observatory
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyQuality">A full description of this member's behavior is provided here</a>
     */
    public void getSkyQuality(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSkyQuality(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSkyQuality");
    }

    /**
     * Returns the sky temperature at the observatory
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyTemperature">A full description of this member's behavior is provided here</a>
     */
    public double getSkyTemperature() {
        DoubleResponse response = call(getClient().getSkyTemperature(getDeviceID(), getClientID(), getTransactionID()), "getSkyTemperature");
        return response.getValue();
    }

    /**
     * Returns the sky temperature at the observatory
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SkyTemperature">A full description of this member's behavior is provided here</a>
     */
    public void getSkyTemperature(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSkyTemperature(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSkyTemperature");
    }

    /**
     * Returns the seeing at the observatory
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.StarFWHM">A full description of this member's behavior is provided here</a>
     */
    public double getStarFWHM() {
        DoubleResponse response = call(getClient().getStarFWHM(getDeviceID(), getClientID(), getTransactionID()), "getStarFWHM");
        return response.getValue();
    }

    /**
     * Returns the seeing at the observatory
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.StarFWHM">A full description of this member's behavior is provided here</a>
     */
    public void getStarFWHM(AlpacaCallback<Double> callback) {
        callAsync(getClient().getStarFWHM(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getStarFWHM");
    }

    /**
     * Returns the temperature at the observatory (°C)
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Temperature">A full description of this member's behavior is provided here</a>
     */
    public double getTemperature() {
        DoubleResponse response = call(getClient().getTemperature(getDeviceID(), getClientID(), getTransactionID()), "getTemperature");
        return response.getValue();
    }

    /**
     * Returns the temperature at the observatory (°C)
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Temperature">A full description of this member's behavior is provided here</a>
     */
    public void getTemperature(AlpacaCallback<Double> callback) {
        callAsync(getClient().getTemperature(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTemperature");
    }

    /**
     * Returns the wind direction at the observatory
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindDirection">A full description of this member's behavior is provided here</a>
     */
    public double getWindDirection() {
        DoubleResponse response = call(getClient().getWindDirection(getDeviceID(), getClientID(), getTransactionID()), "getWindDirection");
        return response.getValue();
    }

    /**
     * Returns the wind direction at the observatory
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindDirection">A full description of this member's behavior is provided here</a>
     */
    public void getWindDirection(AlpacaCallback<Double> callback) {
        callAsync(getClient().getWindDirection(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getWindDirection");
    }

    /**
     * Returns the peak 3 second wind gust at the observatory over the last 2 minutes
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindGust">A full description of this member's behavior is provided here</a>
     */
    public double getWindGust() {
        DoubleResponse response = call(getClient().getWindGust(getDeviceID(), getClientID(), getTransactionID()), "getWindGust");
        return response.getValue();
    }

    /**
     * Returns the peak 3 second wind gust at the observatory over the last 2 minutes
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindGust">A full description of this member's behavior is provided here</a>
     */
    public void getWindGust(AlpacaCallback<Double> callback) {
        callAsync(getClient().getWindGust(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getWindGust");
    }

    /**
     * Returns the wind speed at the observatory.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindSpeed">A full description of this member's behavior is provided here</a>
     */
    public double getWindSpeed() {
        DoubleResponse response = call(getClient().getWindSpeed(getDeviceID(), getClientID(), getTransactionID()), "getWindSpeed");
        return response.getValue();
    }

    /**
     * Returns the wind speed at the observatory.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.WindSpeed">A full description of this member's behavior is provided here</a>
     */
    public void getWindSpeed(AlpacaCallback<Double> callback) {
        callAsync(getClient().getWindSpeed(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getWindSpeed");
    }

    /**
     * Refreshes sensor values from hardware.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Refresh">A full description of this member's behavior is provided here</a>
     */
    public void refresh() {
        AlpacaResponse response = call(getClient().refresh(getDeviceID(), getClientID(), getTransactionID()), "refresh");
    }

    /**
     * Refreshes sensor values from hardware.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.Refresh">A full description of this member's behavior is provided here</a>
     */
    public void refresh(AlpacaCallback<Void> callback) {
        callAsync(getClient().refresh(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "refresh");
    }

    /**
     * Return a sensor description
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SensorDescription">A full description of this member's behavior is provided here</a>
     */
    public String getSensorDescription(String sensorName) {
        StringResponse response = call(getClient().getSensorDescription(getDeviceID(), sensorName, getClientID(), getTransactionID()), "getSensorDescription", sensorName);
        return response.getValue();
    }

    /**
     * Return a sensor description
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.SensorDescription">A full description of this member's behavior is provided here</a>
     */
    public void getSensorDescription(String sensorName, AlpacaCallback<String> callback) {
        callAsync(getClient().getSensorDescription(getDeviceID(), sensorName, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(StringResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSensorDescription", sensorName);
    }

    /**
     * Return the time since the sensor value was last updated
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.TimeSinceLastUpdate">A full description of this member's behavior is provided here</a>
     */
    public double getTimeSinceLastUpdate(String sensorName) {
        DoubleResponse response = call(getClient().getTimeSinceLastUpdate(getDeviceID(), sensorName, getClientID(), getTransactionID()), "getTimeSinceLastUpdate", sensorName);
        return response.getValue();
    }

    /**
     * Return the time since the sensor value was last updated
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/observingconditions.html#ObservingConditions.TimeSinceLastUpdate">A full description of this member's behavior is provided here</a>
     */
    public void getTimeSinceLastUpdate(String sensorName, AlpacaCallback<Double> callback) {
        callAsync(getClient().getTimeSinceLastUpdate(getDeviceID(), sensorName, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTimeSinceLastUpdate", sensorName);
    }
}