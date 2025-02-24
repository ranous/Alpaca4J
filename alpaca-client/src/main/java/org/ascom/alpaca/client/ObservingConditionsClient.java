package org.ascom.alpaca.client;

import org.ascom.alpaca.api.ObservingConditions;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.StringResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@SuppressWarnings("unused")
public class ObservingConditionsClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(ObservingConditionsClient.class);
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

    public double getAveragePeriod() {
        DoubleResponse response = call(getClient().getAveragePeriod(getDeviceID(), getClientID(), getTransactionID()), "getAveragePeriod");
        return response.getValue();
    }

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

    public void setAveragePeriod(double averagePeriod) {
        AlpacaResponse response = call(getClient().setAveragePeriod(getDeviceID(), averagePeriod, getClientID(), getTransactionID()), "setAveragePeriod", averagePeriod);
    }

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

    public double getCloudCover() {
        DoubleResponse response = call(getClient().getCloudCover(getDeviceID(), getClientID(), getTransactionID()), "getCloudCover");
        return response.getValue();
    }

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

    public double getDewPoint() {
        DoubleResponse response = call(getClient().getDewPoint(getDeviceID(), getClientID(), getTransactionID()), "getDewPoint");
        return response.getValue();
    }

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

    public double getHumidity() {
        DoubleResponse response = call(getClient().getHumidity(getDeviceID(), getClientID(), getTransactionID()), "getHumidity");
        return response.getValue();
    }

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

    public double getPressure() {
        DoubleResponse response = call(getClient().getPressure(getDeviceID(), getClientID(), getTransactionID()), "getPressure");
        return response.getValue();
    }

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

    public double getRainRate() {
        DoubleResponse response = call(getClient().getRainRate(getDeviceID(), getClientID(), getTransactionID()), "getRainRate");
        return response.getValue();
    }

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

    public double getSkyBrightness() {
        DoubleResponse response = call(getClient().getSkyBrightness(getDeviceID(), getClientID(), getTransactionID()), "getSkyBrightness");
        return response.getValue();
    }

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

    public double getSkyQuality() {
        DoubleResponse response = call(getClient().getSkyQuality(getDeviceID(), getClientID(), getTransactionID()), "getSkyQuality");
        return response.getValue();
    }

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

    public double getSkyTemperature() {
        DoubleResponse response = call(getClient().getSkyTemperature(getDeviceID(), getClientID(), getTransactionID()), "getSkyTemperature");
        return response.getValue();
    }

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

    public double getStarFWHM() {
        DoubleResponse response = call(getClient().getStarFWHM(getDeviceID(), getClientID(), getTransactionID()), "getStarFWHM");
        return response.getValue();
    }

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

    public double getTemperature() {
        DoubleResponse response = call(getClient().getTemperature(getDeviceID(), getClientID(), getTransactionID()), "getTemperature");
        return response.getValue();
    }

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

    public double getWindDirection() {
        DoubleResponse response = call(getClient().getWindDirection(getDeviceID(), getClientID(), getTransactionID()), "getWindDirection");
        return response.getValue();
    }

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

    public double getWindGust() {
        DoubleResponse response = call(getClient().getWindGust(getDeviceID(), getClientID(), getTransactionID()), "getWindGust");
        return response.getValue();
    }

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

    public double getWindSpeed() {
        DoubleResponse response = call(getClient().getWindSpeed(getDeviceID(), getClientID(), getTransactionID()), "getWindSpeed");
        return response.getValue();
    }

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

    public void refresh() {
        AlpacaResponse response = call(getClient().refresh(getDeviceID(), getClientID(), getTransactionID()), "refresh");
    }

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

    public String getSensorDescription(String sensorName) {
        StringResponse response = call(getClient().getSensorDescription(getDeviceID(), sensorName, getClientID(), getTransactionID()), "getSensorDescription", sensorName);
        return response.getValue();
    }

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

    public double getTimeSinceLastUpdate(String sensorName) {
        DoubleResponse response = call(getClient().getTimeSinceLastUpdate(getDeviceID(), sensorName, getClientID(), getTransactionID()), "getTimeSinceLastUpdate", sensorName);
        return response.getValue();
    }

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