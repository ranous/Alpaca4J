package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Dome;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

/**
 * Client for interacting with ASCOM Dome devices via the ALPACA protocol.
 */
@SuppressWarnings("unused")
public class DomeClient extends CommonClient {
    private static final Logger log = Logger.getLogger(DomeClient.class);
    private final URI serverAddress;
    private Dome client = null;

    public DomeClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public DomeClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Dome getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Dome.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Dome - " + e.getMessage());
            }
        }
        return client;
    }

    /**
     * The dome altitude (degrees - horizon zero, increasing positive to 90 zenith).
     *
     * @return The dome altitude
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Altitude">A full description of this member's behavior is provided here</a>
     */
    public double getAltitude() {
        DoubleResponse response = call(getClient().getAltitude(getDeviceID(), getClientID(), getTransactionID()), "getAltitude");
        return response.getValue();
    }

    /**
     * The dome altitude (degrees - horizon zero, increasing positive to 90 zenith).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Altitude">A full description of this member's behavior is provided here</a>
     */
    public void getAltitude(AlpacaCallback<Double> callback) {
        callAsync(getClient().getAltitude(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getAltitude");
    }

    /**
     * Indicates whether the dome is in the home position.
     *
     * @return true if the dome is in the home position, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.AtHome">A full description of this member's behavior is provided here</a>
     */
    public boolean atHome() {
        BooleanResponse response = call(getClient().atHome(getDeviceID(), getClientID(), getTransactionID()), "atHome");
        return response.getValue();
    }

    /**
     * Indicates whether the dome is in the home position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.AtHome">A full description of this member's behavior is provided here</a>
     */
    public void atHome(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().atHome(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "atHome");
    }

    /**
     * Indicates whether the dome is at the park position
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.AtPark">A full description of this member's behavior is provided here</a>
     */
    public boolean atPark() {
        BooleanResponse response = call(getClient().atPark(getDeviceID(), getClientID(), getTransactionID()), "atPark");
        return response.getValue();
    }

    /**
     * Indicates whether the dome is at the park position
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.AtPark">A full description of this member's behavior is provided here</a>
     */
    public void atPark(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().atPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "atPark");
    }

    /**
     * The dome azimuth (degrees - North zero, increasing clockwise: 90 East, 180 South, 270 West)
     *
     * @return The dome azimuth
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Azimuth">A full description of this member's behavior is provided here</a>
     */
    public double getAzimuth() {
        DoubleResponse response = call(getClient().getAzimuth(getDeviceID(), getClientID(), getTransactionID()), "getAzimuth");
        return response.getValue();
    }

    /**
     * The dome azimuth (degrees - North zero, increasing clockwise: 90 East, 180 South, 270 West)
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Azimuth">A full description of this member's behavior is provided here</a>
     */
    public void getAzimuth(AlpacaCallback<Double> callback) {
        callAsync(getClient().getAzimuth(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getAzimuth");
    }

    /**
     * Indicates whether the dome can find the home position.
     *
     * @return true if the dome can find the home position, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanFindHome">A full description of this member's behavior is provided here</a>
     */
    public boolean canFindHome() {
        BooleanResponse response = call(getClient().canFindHome(getDeviceID(), getClientID(), getTransactionID()), "canFindHome");
        return response.getValue();
    }

    /**
     * Indicates whether the dome can find the home position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanFindHome">A full description of this member's behavior is provided here</a>
     */
    public void canFindHome(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canFindHome(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canFindHome");
    }

    /**
     * Indicates whether the dome can be parked.
     *
     * @return true if the dome can be parked, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanPark">A full description of this member's behavior is provided here</a>
     */
    public boolean canPark() {
        BooleanResponse response = call(getClient().canPark(getDeviceID(), getClientID(), getTransactionID()), "canPark");
        return response.getValue();
    }

    /**
     * Indicates whether the dome can be parked.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanPark">A full description of this member's behavior is provided here</a>
     */
    public void canPark(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canPark");
    }

    /**
     * Indicates whether the dome altitude can be set
     *
     * @return true if the dome altitude can be set, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSetAltitude">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetAltitude() {
        BooleanResponse response = call(getClient().canSetAltitude(getDeviceID(), getClientID(), getTransactionID()), "canSetAltitude");
        return response.getValue();
    }

    /**
     * Indicates whether the dome altitude can be set
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSetAltitude">A full description of this member's behavior is provided here</a>
     */
    public void canSetAltitude(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetAltitude(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetAltitude");
    }

    /**
     * Indicates whether the dome azimuth can be set
     *
     * @return true if the dome azimuth can be set, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSetAzimuth">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetAzimuth() {
        BooleanResponse response = call(getClient().canSetAzimuth(getDeviceID(), getClientID(), getTransactionID()), "canSetAzimuth");
        return response.getValue();
    }

    /**
     * Indicates whether the dome azimuth can be set
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSetAzimuth">A full description of this member's behavior is provided here</a>
     */
    public void canSetAzimuth(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetAzimuth(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetAzimuth");
    }

    /**
     * Indicates whether the dome park position can be set
     *
     * @return true if the dome park position can be set, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSetPark">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetPark() {
        BooleanResponse response = call(getClient().canSetPark(getDeviceID(), getClientID(), getTransactionID()), "canSetPark");
        return response.getValue();
    }

    /**
     * Indicates whether the dome park position can be set
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSetPark">A full description of this member's behavior is provided here</a>
     */
    public void canSetPark(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetPark");
    }

    /**
     * Indicates whether the dome shutter can be opened
     *
     * @return true if the dome shutter can be opened, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSetShutter">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetShutter() {
        BooleanResponse response = call(getClient().canSetShutter(getDeviceID(), getClientID(), getTransactionID()), "canSetShutter");
        return response.getValue();
    }

    /**
     * Indicates whether the dome shutter can be opened
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSetShutter">A full description of this member's behavior is provided here</a>
     */
    public void canSetShutter(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetShutter(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetShutter");
    }

    /**
     * Indicates whether the dome supports slaving to a telescope
     *
     * @return true if the dome supports slaving to a telescope, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSlave">A full description of this member's behavior is provided here</a>
     */
    public boolean canSlave() {
        BooleanResponse response = call(getClient().canSlave(getDeviceID(), getClientID(), getTransactionID()), "canSlave");
        return response.getValue();
    }

    /**
     * Indicates whether the dome supports slaving to a telescope
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSlave">A full description of this member's behavior is provided here</a>
     */
    public void canSlave(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSlave(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSlave");
    }

    /**
     * Indicates whether the dome azimuth position can be synched
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSyncAzimuth">A full description of this member's behavior is provided here</a>
     */
    public boolean canSyncAzimuth() {
        BooleanResponse response = call(getClient().canSyncAzimuth(getDeviceID(), getClientID(), getTransactionID()), "canSyncAzimuth");
        return response.getValue();
    }

    /**
     * Indicates whether the dome azimuth position can be synched
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CanSyncAzimuth">A full description of this member's behavior is provided here</a>
     */
    public void canSyncAzimuth(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSyncAzimuth(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSyncAzimuth");
    }

    /**
     * Status of the dome shutter or roll-off roof
     *
     * @return The shutter status as an integer
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.ShutterStatus">A full description of this member's behavior is provided here</a>
     */
    public int getShutterStatus() {
        IntResponse response = call(getClient().getShutterStatus(getDeviceID(), getClientID(), getTransactionID()), "getShutterStatus");
        return response.getValue();
    }

    /**
     * Status of the dome shutter or roll-off roof
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.ShutterStatus">A full description of this member's behavior is provided here</a>
     */
    public void getShutterStatus(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getShutterStatus(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getShutterStatus");
    }

    /**
     * Indicates whether the dome is slaved to the telescope
     *
     * @return true if the dome is slaved to the telescope, false otherwise
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Slaved">A full description of this member's behavior is provided here</a>
     */
    public boolean isSlaved() {
        BooleanResponse response = call(getClient().isSlaved(getDeviceID(), getClientID(), getTransactionID()), "isSlaved");
        return response.getValue();
    }

    /**
     * Indicates whether the dome is slaved to the telescope
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Slaved">A full description of this member's behavior is provided here</a>
     */
    public void isSlaved(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isSlaved(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isSlaved");
    }

    /**
     * Sets whether the dome is slaved to the telescope
     *
     * @param slaved true to set the dome as slaved, false to set it as unslaved
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Slaved">A full description of this member's behavior is provided here</a>
     */
    public void setSlaved(boolean slaved) {
        call(getClient().setSlaved(getDeviceID(), getClientID(), getTransactionID(), slaved), "setSlaved", slaved);
    }

    /**
     * Sets whether the dome is slaved to the telescope
     *
     * @param slaved true to set the dome as slaved, false to set it as unslaved
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Slaved">A full description of this member's behavior is provided here</a>
     */
    public void setSlaved(boolean slaved, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSlaved(getDeviceID(), getClientID(), getTransactionID(), slaved), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSlaved", slaved);
    }

    /**
     * Indicates whether any part of the dome is moving
     *
     * @return true if any part of the dome is moving, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Slewing">A full description of this member's behavior is provided here</a>
     */
    public boolean isSlewing() {
        BooleanResponse response = call(getClient().isSlewing(getDeviceID(), getClientID(), getTransactionID()), "isSlewing");
        return response.getValue();
    }

    /**
     * Indicates whether any part of the dome is moving
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Slewing">A full description of this member's behavior is provided here</a>
     */
    public void isSlewing(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isSlewing(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isSlewing");
    }

    /**
     * Immediately cancel current dome operation.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.AbortSlew">A full description of this member's behavior is provided here</a>
     */
    public void abortSlew() {
        call(getClient().abortSlew(getDeviceID(), getClientID(), getTransactionID()), "abortSlew");
    }

    /**
     * Immediately cancel current dome operation.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.AbortSlew">A full description of this member's behavior is provided here</a>
     */
    public void abortSlew(AlpacaCallback<Void> callback) {
        callAsync(getClient().abortSlew(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "abortSlew");
    }

    /**
     * Close the shutter or otherwise shield the telescope from the sky.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CloseShutter">A full description of this member's behavior is provided here</a>
     */
    public void closeShutter() {
        call(getClient().closeShutter(getDeviceID(), getClientID(), getTransactionID()), "closeShutter");
    }

    /**
     * Close the shutter or otherwise shield the telescope from the sky.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.CloseShutter">A full description of this member's behavior is provided here</a>
     */
    public void closeShutter(AlpacaCallback<Void> callback) {
        callAsync(getClient().closeShutter(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "closeShutter");
    }

    /**
     * Start operation to search for the dome home position.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.FindHome">A full description of this member's behavior is provided here</a>
     */
    public void findHome() {
        call(getClient().findHome(getDeviceID(), getClientID(), getTransactionID()), "findHome");
    }

    /**
     * Start operation to search for the dome home position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.FindHome">A full description of this member's behavior is provided here</a>
     */
    public void findHome(AlpacaCallback<Void> callback) {
        callAsync(getClient().findHome(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "findHome");
    }

    /**
     * Open shutter or otherwise expose the telescope to the sky.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.OpenShutter">A full description of this member's behavior is provided here</a>
     */
    public void openShutter() {
        call(getClient().openShutter(getDeviceID(), getClientID(), getTransactionID()), "openShutter");
    }

    /**
     * Open shutter or otherwise expose the telescope to the sky.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.OpenShutter">A full description of this member's behavior is provided here</a>
     */
    public void openShutter(AlpacaCallback<Void> callback) {
        callAsync(getClient().openShutter(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "openShutter");
    }

    /**
     * Rotate dome in azimuth to park position.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Park">A full description of this member's behavior is provided here</a>
     */
    public void park() {
        call(getClient().park(getDeviceID(), getClientID(), getTransactionID()), "park");
    }

    /**
     * Rotate dome in azimuth to park position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.Park">A full description of this member's behavior is provided here</a>
     */
    public void park(AlpacaCallback<Void> callback) {
        callAsync(getClient().park(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "park");
    }

    /**
     * Set the current azimuth, altitude position of dome to be the park position
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.SetPark">A full description of this member's behavior is provided here</a>
     */
    public void setPark() {
        call(getClient().setPark(getDeviceID(), getClientID(), getTransactionID()), "setPark");
    }

    /**
     * Set the current azimuth, altitude position of dome to be the park position
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.SetPark">A full description of this member's behavior is provided here</a>
     */
    public void setPark(AlpacaCallback<Void> callback) {
        callAsync(getClient().setPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setPark");
    }

    /**
     * Slew the dome to the given altitude position.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.SlewToAltitude">A full description of this member's behavior is provided here</a>
     */
    public void slewToAltitude(double altitude) {
        call(getClient().slewToAltitude(getDeviceID(), getClientID(), getTransactionID(), altitude), "slewToAltitude", altitude);
    }

    /**
     * Slew the dome to the given altitude position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.SlewToAltitude">A full description of this member's behavior is provided here</a>
     */
    public void slewToAltitude(double altitude, AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToAltitude(getDeviceID(), getClientID(), getTransactionID(), altitude), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToAltitude", altitude);
    }

    /**
     * Slew the dome to the given azimuth position.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.SlewToAzimuth">A full description of this member's behavior is provided here</a>
     */
    public void slewToAzimuth(double azimuth) {
        call(getClient().slewToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth), "slewToAzimuth", azimuth);
    }

    /**
     * Slew the dome to the given azimuth position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.SlewToAzimuth">A full description of this member's behavior is provided here</a>
     */
    public void slewToAzimuth(double azimuth, AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToAzimuth", azimuth);
    }

    /**
     * Synchronize the current position of the dome to the given azimuth.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.SyncToAzimuth">A full description of this member's behavior is provided here</a>
     */
    public void syncToAzimuth(double azimuth) {
        call(getClient().syncToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth), "syncToAzimuth", azimuth);
    }

    /**
     * Synchronize the current position of the dome to the given azimuth.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/dome.html#Dome.SyncToAzimuth">A full description of this member's behavior is provided here</a>
     */
    public void syncToAzimuth(double azimuth, AlpacaCallback<Void> callback) {
        callAsync(getClient().syncToAzimuth(getDeviceID(), getClientID(), getTransactionID(), azimuth), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "syncToAzimuth", azimuth);
    }
}