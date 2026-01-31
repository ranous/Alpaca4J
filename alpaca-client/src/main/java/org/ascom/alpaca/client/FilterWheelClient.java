package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.FilterWheel;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.ListResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;
import java.util.List;

@SuppressWarnings("unused")
public class FilterWheelClient extends CommonClient {
    private static final Logger log = Logger.getLogger(FilterWheelClient.class);
    private final URI serverAddress;
    private FilterWheel client = null;

    public FilterWheelClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public FilterWheelClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private FilterWheel getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(FilterWheel.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for FilterWheel - " + e.getMessage());
            }
        }
        return client;
    }

    /**
     * Returns an integer array of filter focus offsets.  The order is the same as the filter names.
     *
     * @return An integer array of filter focus offsets
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/filterwheel.html#FilterWheel.FocusOffsets">A full description of this member's behavior is provided here</a>
     */
    public List<Integer> getFocusOffsets() {
        ListResponse<Integer> response = call(getClient().getFocusOffsets(getDeviceID(), getClientID(), getTransactionID()), "getFocusOffsets");
        return response.getValue();
    }

    /**
     * Returns an integer array of filter focus offsets
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/filterwheel.html#FilterWheel.FocusOffsets">A full description of this member's behavior is provided here</a>
     */
    public void getFocusOffsets(AlpacaCallback<List<Integer>> callback) {
        callAsync(getClient().getFocusOffsets(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<Integer> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getFocusOffsets");
    }

    /**
     * Gets the names of the filters in the filter wheel.
     *
     * @return Filter wheel filter names
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/filterwheel.html#FilterWheel.Names">A full description of this member's behavior is provided here</a>
     */
    public List<String> getFilterNames() {
        ListResponse<String> response = call(getClient().getFilterNames(getDeviceID(), getClientID(), getTransactionID()), "getFilterNames");
        return response.getValue();
    }

    /**
     * Gets the names of the filters in the filter wheel.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/filterwheel.html#FilterWheel.Names">A full description of this member's behavior is provided here</a>
     */
    public void getFilterNames(AlpacaCallback<List<String>> callback) {
        callAsync(getClient().getFilterNames(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<String> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getFilterNames");
    }

    /**
     * Returns the current filter wheel position
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/filterwheel.html#FilterWheel.Position">A full description of this member's behavior is provided here</a>
     */
    public int getPosition() {
        IntResponse response = call(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), "getPosition");
        return response.getValue();
    }

    /**
     * Returns the current filter wheel position
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/filterwheel.html#FilterWheel.Position">A full description of this member's behavior is provided here</a>
     */
    public void getPosition(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getPosition(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getPosition");
    }

    /**
     * Sets the filter wheel position
     *
     * @param position The number of the filter wheel position to select
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/filterwheel.html#FilterWheel.Position">A full description of this member's behavior is provided here</a>
     */
    public void setPosition(int position) {
        AlpacaResponse response = call(getClient().setPosition(getDeviceID(), getClientID(), getTransactionID(), position), "setPosition", position);
    }

    /**
     * Sets the filter wheel position
     *
     * @param position The number of the filter wheel position to select
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/filterwheel.html#FilterWheel.Position">A full description of this member's behavior is provided here</a>
     */
    public void setPosition(int position, AlpacaCallback<Void> callback) {
        callAsync(getClient().setPosition(getDeviceID(), getClientID(), getTransactionID(), position), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setPosition", position);
    }
}