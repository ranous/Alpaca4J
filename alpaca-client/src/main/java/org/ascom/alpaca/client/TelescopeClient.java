package org.ascom.alpaca.client;

import org.ascom.alpaca.client.impl.api.Telescope;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.model.ClientException;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Client for interacting with Alpaca Telescope devices.
 */
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class TelescopeClient extends CommonClient {
    private static final Logger log = Logger.getLogger(TelescopeClient.class);
    private final URI serverAddress;
    private Telescope client = null;

    public TelescopeClient(URI serverURI, DeviceDescriptor deviceDescriptor) {
        super(deviceDescriptor, serverURI);
        serverAddress = serverURI;
    }

    public TelescopeClient(URI serverURI, DeviceDescriptor deviceDescriptor, int clientID) {
        super(deviceDescriptor, serverURI, clientID);
        serverAddress = serverURI;
    }

    private Telescope getClient() {
        if (client == null) {
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(serverAddress.toString())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                client = retrofit.create(Telescope.class);
                return client;
            } catch (Exception e) {
                logWarn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Telescope - " + e.getMessage());
            }
        }
        return client;
    }

    /**
     * Returns the current mount alignment mode
     *
     * @return The current mount alignment mode
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AlignmentMode">A full description of this member's behavior is provided here</a>
     */
    public AlignmentMode getAlignmentMode() {
        IntResponse response = call(getClient().getAlignmentMode(getDeviceID(), getClientID(), getTransactionID()), "getAlignmentMode");
        return AlignmentMode.fromMode(response.getValue());
    }

    /**
     * Returns the current mount alignment mode
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AlignmentMode">A full description of this member's behavior is provided here</a>
     */
    public void getAlignmentMode(AlpacaCallback<AlignmentMode> callback) {
        callAsync(getClient().getAlignmentMode(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(AlignmentMode.fromMode(result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getAlignmentMode");
    }

    /**
     * Returns the mount's altitude above the horizon (degrees).
     *
     * @return The mount's altitude above the horizon (degrees).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Altitude">A full description of this member's behavior is provided here</a>
     */
    public double getAltitude() {
        DoubleResponse response = call(getClient().getAltitude(getDeviceID(), getClientID(), getTransactionID()), "getAltitude");
        return response.getValue();
    }

    /**
     * Returns the mount's altitude above the horizon (degrees).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Altitude">A full description of this member's behavior is provided here</a>
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
     * Returns the telescope's aperture (square meters).
     *
     * @return The telescope's aperture (square meters).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.ApertureArea">A full description of this member's behavior is provided here</a>
     */
    public double getApertureArea() {
        DoubleResponse response = call(getClient().getApertureArea(getDeviceID(), getClientID(), getTransactionID()), "getApertureArea");
        return response.getValue();
    }

    /**
     * Returns the telescope's aperture (square meters).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.ApertureArea">A full description of this member's behavior is provided here</a>
     */
    public void getApertureArea(AlpacaCallback<Double> callback) {
        callAsync(getClient().getApertureArea(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getApertureArea");
    }

    /**
     * Returns the telescope's effective aperture (meters).
     *
     * @return The telescope's effective aperture (meters).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.ApertureDiameter">A full description of this member's behavior is provided here</a>
     */
    public double getApertureDiameter() {
        DoubleResponse response = call(getClient().getApertureDiameter(getDeviceID(), getClientID(), getTransactionID()), "getApertureDiameter");
        return response.getValue();
    }

    /**
     * Returns the telescope's effective aperture (meters).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.ApertureDiameter">A full description of this member's behavior is provided here</a>
     */
    public void getApertureDiameter(AlpacaCallback<Double> callback) {
        callAsync(getClient().getApertureDiameter(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getApertureDiameter");
    }

    /**
     * Indicates whether the mount is at the home position.
     *
     * @return True if the mount is at the home position, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AtHome">A full description of this member's behavior is provided here</a>
     */
    public boolean isAtHome() {
        BooleanResponse response = call(getClient().isAtHome(getDeviceID(), getClientID(), getTransactionID()), "isAtHome");
        return response.getValue();
    }

    /**
     * Indicates whether the mount is at the home position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AtHome">A full description of this member's behavior is provided here</a>
     */
    public void isAtHome(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isAtHome(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isAtHome");
    }

    /**
     * Indicates whether the telescope is at the park position.
     *
     * @return True if the telescope is at the park position, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AtPark">A full description of this member's behavior is provided here</a>
     */
    public boolean isAtPark() {
        BooleanResponse response = call(getClient().isAtPark(getDeviceID(), getClientID(), getTransactionID()), "isAtPark");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope is at the park position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AtPark">A full description of this member's behavior is provided here</a>
     */
    public void isAtPark(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isAtPark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isAtPark");
    }

    /**
     * Returns the mount's azimuth (degrees, North-referenced, positive East/clockwise).
     *
     * @return The mount's azimuth (degrees, North-referenced, positive East/clockwise).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Azimuth">A full description of this member's behavior is provided here</a>
     */
    public double getAzimuth() {
        DoubleResponse response = call(getClient().getAzimuth(getDeviceID(), getClientID(), getTransactionID()), "getAzimuth");
        return response.getValue();
    }

    /**
     * Returns the mount's azimuth (degrees, North-referenced, positive East/clockwise).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Azimuth">A full description of this member's behavior is provided here</a>
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
     * Indicates whether the mount can find the home position.
     *
     * @return True if the mount can find the home position, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanFindHome">A full description of this member's behavior is provided here</a>
     */
    public boolean canFindHome() {
        BooleanResponse response = call(getClient().canFindHome(getDeviceID(), getClientID(), getTransactionID()), "canFindHome");
        return response.getValue();
    }

    /**
     * Indicates whether the mount can find the home position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanFindHome">A full description of this member's behavior is provided here</a>
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
     * Indicates whether the telescope can be parked.
     *
     * @return True if the telescope can be parked, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanPark">A full description of this member's behavior is provided here</a>
     */
    public boolean canPark() {
        BooleanResponse response = call(getClient().canPark(getDeviceID(), getClientID(), getTransactionID()), "canPark");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can be parked.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanPark">A full description of this member's behavior is provided here</a>
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
     * Indicates whether the telescope can be pulse guided.
     *
     * @return True if the telescope can be pulse guided, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanPulseGuide">A full description of this member's behavior is provided here</a>
     */
    public boolean canPulseGuide() {
        BooleanResponse response = call(getClient().canPulseGuide(getDeviceID(), getClientID(), getTransactionID()), "canPulseGuide");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can be pulse guided.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanPulseGuide">A full description of this member's behavior is provided here</a>
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
     * Indicates whether the DeclinationRate property can be changed.
     *
     * @return True if the DeclinationRate property can be changed, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetDeclinationRate">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetDeclinationRate() {
        BooleanResponse response = call(getClient().canSetDeclinationRate(getDeviceID(), getClientID(), getTransactionID()), "canSetDeclinationRate");
        return response.getValue();
    }

    /**
     * Indicates whether the DeclinationRate property can be changed.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetDeclinationRate">A full description of this member's behavior is provided here</a>
     */
    public void canSetDeclinationRate(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetDeclinationRate(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetDeclinationRate");
    }

    /**
     * Indicates whether the GuideRate property can be changed.
     *
     * @return True if the GuideRate property can be changed, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetGuideRates">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetGuideRates() {
        BooleanResponse response = call(getClient().canSetGuideRates(getDeviceID(), getClientID(), getTransactionID()), "canSetGuideRates");
        return response.getValue();
    }

    /**
     * Indicates whether the GuideRate property can be changed.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetGuideRates">A full description of this member's behavior is provided here</a>
     */
    public void canSetGuideRates(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetGuideRates(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetGuideRates");
    }

    /**
     * Indicates whether the telescope park position can be set.
     *
     * @return True if the telescope park position can be set, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetPark">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetPark() {
        BooleanResponse response = call(getClient().canSetPark(getDeviceID(), getClientID(), getTransactionID()), "canSetPark");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope park position can be set.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetPark">A full description of this member's behavior is provided here</a>
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
     * Indicates whether the telescope SideOfPier can be set.
     *
     * @return True if the telescope SideOfPier can be set, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetPierSide">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetPierSide() {
        BooleanResponse response = call(getClient().canSetPierSide(getDeviceID(), getClientID(), getTransactionID()), "canSetPierSide");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope SideOfPier can be set.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetPierSide">A full description of this member's behavior is provided here</a>
     */
    public void canSetPierSide(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetPierSide(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetPierSide");
    }

    /**
     * Indicates whether the RightAscensionRate can be changed.
     *
     * @return True if the RightAscensionRate can be changed, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetRightAscensionRate">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetRightAscensionRate() {
        BooleanResponse response = call(getClient().canSetRightAscensionRate(getDeviceID(), getClientID(), getTransactionID()), "canSetRightAscensionRate");
        return response.getValue();
    }

    /**
     * Indicates whether the RightAscensionRate can be changed.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetRightAscensionRate">A full description of this member's behavior is provided here</a>
     */
    public void canSetRightAscensionRate(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetRightAscensionRate(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetRightAscensionRate");
    }

    /**
     * Indicates whether the Tracking state can be changed.
     *
     * @return True if the Tracking state can be changed, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetTracking">A full description of this member's behavior is provided here</a>
     */
    public boolean canSetTracking() {
        BooleanResponse response = call(getClient().canSetTracking(getDeviceID(), getClientID(), getTransactionID()), "canSetTracking");
        return response.getValue();
    }

    /**
     * Indicates whether the Tracking state can be changed.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSetTracking">A full description of this member's behavior is provided here</a>
     */
    public void canSetTracking(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSetTracking(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSetTracking");
    }

    /**
     * Indicates whether the telescope can slew synchronously.
     *
     * @return True if the telescope can slew synchronously, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSlew">A full description of this member's behavior is provided here</a>
     */
    public boolean canSlew() {
        BooleanResponse response = call(getClient().canSlew(getDeviceID(), getClientID(), getTransactionID()), "canSlew");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can slew synchronously.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSlew">A full description of this member's behavior is provided here</a>
     */
    public void canSlew(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSlew(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSlew");
    }

    /**
     * Indicates whether the telescope can slew synchronously to AltAz coordinates.
     *
     * @return True if the telescope can slew synchronously to AltAz coordinates, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSlewAltAz">A full description of this member's behavior is provided here</a>
     */
    public boolean canSlewAltAz() {
        BooleanResponse response = call(getClient().canSlewAltAz(getDeviceID(), getClientID(), getTransactionID()), "canSlewAltAz");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can slew synchronously to AltAz coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSlewAltAz">A full description of this member's behavior is provided here</a>
     */
    public void canSlewAltAz(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSlewAltAz(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSlewAltAz");
    }

    /**
     * Indicates whether the telescope can slew asynchronously to AltAz coordinates.
     *
     * @return True if the telescope can slew asynchronously to AltAz coordinates, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSlewAltAzAsync">A full description of this member's behavior is provided here</a>
     */
    public boolean canSlewAltAzAsync() {
        BooleanResponse response = call(getClient().canSlewAltAzAsync(getDeviceID(), getClientID(), getTransactionID()), "canSlewAltAzAsync");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can slew asynchronously to AltAz coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSlewAltAzAsync">A full description of this member's behavior is provided here</a>
     */
    public void canSlewAltAzAsync(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSlewAltAzAsync(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSlewAltAzAsync");
    }

    /**
     * Indicates whether the telescope can slew asynchronously to equatorial coordinates.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSlewAsync">A full description of this member's behavior is provided here</a>
     */
    public boolean canSlewAsync() {
        BooleanResponse response = call(getClient().canSlewAsync(getDeviceID(), getClientID(), getTransactionID()), "canSlewAsync");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can slew asynchronously to equatorial coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSlewAsync">A full description of this member's behavior is provided here</a>
     */
    public void canSlewAsync(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSlewAsync(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSlewAsync");
    }

    /**
     * Indicates whether the telescope can sync to equatorial coordinates.
     *
     * @return True if the telescope can sync to equatorial coordinates, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSync">A full description of this member's behavior is provided here</a>
     */
    public boolean canSync() {
        BooleanResponse response = call(getClient().canSync(getDeviceID(), getClientID(), getTransactionID()), "canSync");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can sync to equatorial coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSync">A full description of this member's behavior is provided here</a>
     */
    public void canSync(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSync(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSync");
    }

    /**
     * Indicates whether the telescope can sync to Alt/Az coordinates.
     *
     * @return True if the telescope can sync to Alt/Az coordinates, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSyncAltAz">A full description of this member's behavior is provided here</a>
     */
    public boolean canSyncAltAz() {
        BooleanResponse response = call(getClient().canSyncAltAz(getDeviceID(), getClientID(), getTransactionID()), "canSyncAltAz");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can sync to Alt/Az coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanSyncAltAz">A full description of this member's behavior is provided here</a>
     */
    public void canSyncAltAz(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canSyncAltAz(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canSyncAltAz");
    }

    /**
     * Indicates whether the telescope can be unparked.
     *
     * @return True if the telescope can be unparked, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanUnpark">A full description of this member's behavior is provided here</a>
     */
    public boolean canUnpark() {
        BooleanResponse response = call(getClient().canUnpark(getDeviceID(), getClientID(), getTransactionID()), "canUnpark");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can be unparked.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanUnpark">A full description of this member's behavior is provided here</a>
     */
    public void canUnpark(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canUnpark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canUnpark");
    }

    /**
     * Returns the mount's declination (degrees).
     *
     * @return The mount's declination (degrees).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Declination">A full description of this member's behavior is provided here</a>
     */
    public double getDeclination() {
        DoubleResponse response = call(getClient().getDeclination(getDeviceID(), getClientID(), getTransactionID()), "getDeclination");
        return response.getValue();
    }

    /**
     * Returns the mount's declination (degrees).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Declination">A full description of this member's behavior is provided here</a>
     */
    public void getDeclination(AlpacaCallback<Double> callback) {
        callAsync(getClient().getDeclination(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getDeclination");
    }

    /**
     * Returns the telescope's declination tracking rate (arcseconds per SI second).
     *
     * @return The telescope's declination tracking rate (arcseconds per SI second).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DeclinationRate">A full description of this member's behavior is provided here</a>
     */
    public double getDeclinationRate() {
        DoubleResponse response = call(getClient().getDeclinationRate(getDeviceID(), getClientID(), getTransactionID()), "getDeclinationRate");
        return response.getValue();
    }

    /**
     * Returns the telescope's declination tracking rate (arcseconds per SI second).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DeclinationRate">A full description of this member's behavior is provided here</a>
     */
    public void getDeclinationRate(AlpacaCallback<Double> callback) {
        callAsync(getClient().getDeclinationRate(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getDeclinationRate");
    }

    /**
     * Sets the telescope's declination tracking rate (arcseconds per SI second).
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DeclinationRate">A full description of this member's behavior is provided here</a>
     */
    public void setDeclinationRate(double declinationRate) {
        AlpacaResponse response = call(getClient().setDeclinationRate(getDeviceID(), declinationRate, getClientID(), getTransactionID()), "setDeclinationRate", declinationRate);
    }

    /**
     * Sets the telescope's declination tracking rate (arcseconds per SI second).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DeclinationRate">A full description of this member's behavior is provided here</a>
     */
    public void setDeclinationRate(double declinationRate, AlpacaCallback<Void> callback) {
        callAsync(getClient().setDeclinationRate(getDeviceID(), declinationRate, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setDeclinationRate", declinationRate);
    }

    /**
     * Indicates whether atmospheric refraction correction is applied to coordinates.
     *
     * @return True if atmospheric refraction correction is applied to coordinates, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DoesRefraction">A full description of this member's behavior is provided here</a>
     */
    public boolean doesRefraction() {
        BooleanResponse response = call(getClient().doesRefraction(getDeviceID(), getClientID(), getTransactionID()), "doesRefraction");
        return response.getValue();
    }

    /**
     * Indicates whether atmospheric refraction correction is applied to coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DoesRefraction">A full description of this member's behavior is provided here</a>
     */
    public void doesRefraction(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().doesRefraction(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "doesRefraction");
    }

    /**
     * Determines whether atmospheric refraction correction is applied to coordinates.
     *
     * @param doesRefraction True if atmospheric refraction correction is applied to coordinates, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DoesRefraction">A full description of this member's behavior is provided here</a>
     */
    public void setDoesRefraction(boolean doesRefraction) {
        AlpacaResponse response = call(getClient().setDoesRefraction(getDeviceID(), doesRefraction, getClientID(), getTransactionID()), "setDoesRefraction", doesRefraction);
    }

    /**
     * Determines whether atmospheric refraction correction is applied to coordinates.
     *
     * @param doesRefraction True if atmospheric refraction correction is applied to coordinates, false otherwise.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DoesRefraction">A full description of this member's behavior is provided here</a>
     */
    public void setDoesRefraction(boolean doesRefraction, AlpacaCallback<Void> callback) {
        callAsync(getClient().setDoesRefraction(getDeviceID(), doesRefraction, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setDoesRefraction", doesRefraction);
    }

    /**
     * Returns the current equatorial coordinate system used by this telescope.
     *
     * @return The current equatorial coordinate system used by this telescope.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.EquatorialSystem">A full description of this member's behavior is provided here</a>
     */
    public EquatorialCoordinateType getEquatorialSystem() {
        IntResponse response = call(getClient().getEquatorialSystem(getDeviceID(), getClientID(), getTransactionID()), "getEquatorialSystem");
        return EquatorialCoordinateType.fromType(response.getValue());
    }

    /**
     * Returns the current equatorial coordinate system used by this telescope.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.EquatorialSystem">A full description of this member's behavior is provided here</a>
     */
    public void getEquatorialSystem(AlpacaCallback<EquatorialCoordinateType> callback) {
        callAsync(getClient().getEquatorialSystem(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(EquatorialCoordinateType.fromType(result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getEquatorialSystem");
    }

    /**
     * Returns the telescope's focal length (meters).
     *
     * @return The telescope's focal length (meters).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.FocalLength">A full description of this member's behavior is provided here</a>
     */
    public double getFocalLength() {
        DoubleResponse response = call(getClient().getFocalLength(getDeviceID(), getClientID(), getTransactionID()), "getFocalLength");
        return response.getValue();
    }

    /**
     * Returns the telescope's focal length (meters).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.FocalLength">A full description of this member's behavior is provided here</a>
     */
    public void getFocalLength(AlpacaCallback<Double> callback) {
        callAsync(getClient().getFocalLength(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getFocalLength");
    }

    /**
     * Returns the  current Declination rate offset for telescope guiding (degrees/sec).
     *
     * @return The  current Declination rate offset for telescope guiding (degrees/sec).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideRateDeclination">A full description of this member's behavior is provided here</a>
     */
    public double getGuideRateDeclination() {
        DoubleResponse response = call(getClient().getGuideRateDeclination(getDeviceID(), getClientID(), getTransactionID()), "getGuideRateDeclination");
        return response.getValue();
    }

    /**
     * Returns the  current Declination rate offset for telescope guiding (degrees/sec).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideRateDeclination">A full description of this member's behavior is provided here</a>
     */
    public void getGuideRateDeclination(AlpacaCallback<Double> callback) {
        callAsync(getClient().getGuideRateDeclination(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getGuideRateDeclination");
    }

    /**
     * Sets the  current Declination rate offset for telescope guiding (degrees/sec).
     *
     * @param guideRate The new Declination rate offset for telescope guiding (degrees/sec).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideRateDeclination">A full description of this member's behavior is provided here</a>
     */
    public void setGuideRateDeclination(double guideRate) {
        AlpacaResponse response = call(getClient().setGuideRateDeclination(getDeviceID(), guideRate, getClientID(), getTransactionID()), "setGuideRateDeclination", guideRate);
    }

    /**
     * Sets the  current Declination rate offset for telescope guiding (degrees/sec).
     *
     * @param guideRate The new Declination rate offset for telescope guiding (degrees/sec).
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideRateDeclination">A full description of this member's behavior is provided here</a>
     */
    public void setGuideRateDeclination(double guideRate, AlpacaCallback<Void> callback) {
        callAsync(getClient().setGuideRateDeclination(getDeviceID(), guideRate, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setGuideRateDeclination", guideRate);
    }

    /**
     * Returns the  current RightAscension rate offset for telescope guiding (degrees/sec).
     *
     * @return The  current RightAscension rate offset for telescope guiding (degrees/sec).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideRateRightAscension">A full description of this member's behavior is provided here</a>
     */
    public double getGuideRateRightAscension() {
        DoubleResponse response = call(getClient().getGuideRateRightAscension(getDeviceID(), getClientID(), getTransactionID()), "getGuideRateRightAscension");
        return response.getValue();
    }

    /**
     * Returns the  current RightAscension rate offset for telescope guiding (degrees/sec).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideRateRightAscension">A full description of this member's behavior is provided here</a>
     */
    public void getGuideRateRightAscension(AlpacaCallback<Double> callback) {
        callAsync(getClient().getGuideRateRightAscension(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getGuideRateRightAscension");
    }

    /**
     * Sets the current RightAscension rate offset for telescope guiding (degrees/sec).
     *
     * @param guideRate The new RightAscension rate offset for telescope guiding (degrees/sec).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideRateRightAscension">A full description of this member's behavior is provided here</a>
     */
    public void setGuideRateRightAscension(double guideRate) {
        AlpacaResponse response = call(getClient().setGuideRateRightAscension(getDeviceID(), guideRate, getClientID(), getTransactionID()), "setGuideRateRightAscension", guideRate);

    }

    /**
     * Sets the current RightAscension rate offset for telescope guiding (degrees/sec).
     *
     * @param guideRate The new RightAscension rate offset for telescope guiding (degrees/sec).
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.GuideRateRightAscension">A full description of this member's behavior is provided here</a>
     */
    public void setGuideRateRightAscension(double guideRate, AlpacaCallback<Void> callback) {
        callAsync(getClient().setGuideRateRightAscension(getDeviceID(), guideRate, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setGuideRateRightAscension", guideRate);
    }

    /**
     * Indicates whether the telescope is currently executing a PulseGuide command
     *
     * @return True if the telescope is currently executing a PulseGuide command, false otherwise.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.IsPulseGuiding">A full description of this member's behavior is provided here</a>
     */
    public boolean isPulseGuiding() {
        BooleanResponse response = call(getClient().isPulseGuiding(getDeviceID(), getClientID(), getTransactionID()), "isPulseGuiding");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope is currently executing a PulseGuide command
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.IsPulseGuiding">A full description of this member's behavior is provided here</a>
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
     * Returns the mount's right ascension coordinate (hours).
     *
     * @return The mount's right ascension coordinate (hours).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.RightAscension">A full description of this member's behavior is provided here</a>
     */
    public double getRightAscension() {
        DoubleResponse response = call(getClient().getRightAscension(getDeviceID(), getClientID(), getTransactionID()), "getRightAscension");
        return response.getValue();
    }

    /**
     * Returns the mount's right ascension coordinate (hours).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.RightAscension">A full description of this member's behavior is provided here</a>
     */
    public void getRightAscension(AlpacaCallback<Double> callback) {
        callAsync(getClient().getRightAscension(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getRightAscension");
    }

    /**
     * Returns the telescope's right ascension tracking rate (arcseconds per sidereal second).
     *
     * @return The telescope's right ascension tracking rate (arcseconds per sidereal second).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.RightAscensionRate">A full description of this member's behavior is provided here</a>
     */
    public double getRightAscensionRate() {
        DoubleResponse response = call(getClient().getRightAscensionRate(getDeviceID(), getClientID(), getTransactionID()), "getRightAscensionRate");
        return response.getValue();
    }

    /**
     * Returns the telescope's right ascension tracking rate (arcseconds per sidereal second).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.RightAscensionRate">A full description of this member's behavior is provided here</a>
     */
    public void getRightAscensionRate(AlpacaCallback<Double> callback) {
        callAsync(getClient().getRightAscensionRate(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getRightAscensionRate");
    }

    /**
     * Sets the telescope's right ascension tracking rate (arcseconds per sidereal second).
     *
     * @param rightAscensionRate The new telescope's right ascension tracking rate (arcseconds per sidereal second).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.RightAscensionRate">A full description of this member's behavior is provided here</a>
     */
    public void setRightAscensionRate(double rightAscensionRate) {
        AlpacaResponse response = call(getClient().setRightAscensionRate(getDeviceID(), rightAscensionRate, getClientID(), getTransactionID()), "setRightAscensionRate", rightAscensionRate);
    }

    /**
     * Sets the telescope's right ascension tracking rate (arcseconds per sidereal second).
     *
     * @param rightAscensionRate The new telescope's right ascension tracking rate (arcseconds per sidereal second).
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.RightAscensionRate">A full description of this member's behavior is provided here</a>
     */
    public void setRightAscensionRate(double rightAscensionRate, AlpacaCallback<Void> callback) {
        callAsync(getClient().setRightAscensionRate(getDeviceID(), rightAscensionRate, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setRightAscensionRate", rightAscensionRate);
    }

    /**
     * Returns the mount's pointing state.
     *
     * @return The mount's pointing state.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SideOfPier">A full description of this member's behavior is provided here</a>
     */
    public PierSide getSideOfPier() {
        IntResponse response = call(getClient().getSideOfPier(getDeviceID(), getClientID(), getTransactionID()), "getSideOfPier");
        return PierSide.fromSide(response.getValue());
    }

    /**
     * Returns the mount's pointing state.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SideOfPier">A full description of this member's behavior is provided here</a>
     */
    public void getSideOfPier(AlpacaCallback<PierSide> callback) {
        callAsync(getClient().getSideOfPier(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(PierSide.fromSide(result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSideOfPier");
    }

    /**
     * Sets the mount's pointing state.
     *
     * @return The mount's pointing state.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SideOfPier">A full description of this member's behavior is provided here</a>
     */
    public void setSideOfPier(PierSide sideOfPier) {
        AlpacaResponse response = call(getClient().setSideOfPier(getDeviceID(), sideOfPier.ordinal(), getClientID(), getTransactionID()), "setSideOfPier", sideOfPier.ordinal());
    }

    /**
     * Sets the mount's pointing state.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SideOfPier">A full description of this member's behavior is provided here</a>
     */
    public void setSideOfPier(PierSide sideOfPier, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSideOfPier(getDeviceID(), sideOfPier.ordinal(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSideOfPier", sideOfPier.ordinal());
    }

    /**
     * Returns the local apparent sidereal time.
     *
     * @return The local apparent sidereal time.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiderealTime">A full description of this member's behavior is provided here</a>
     */
    public double getSiderealTime() {
        DoubleResponse response = call(getClient().getSiderealTime(getDeviceID(), getClientID(), getTransactionID()), "getSiderealTime");
        return response.getValue();
    }

    /**
     * Returns the local apparent sidereal time.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiderealTime">A full description of this member's behavior is provided here</a>
     */
    public void getSiderealTime(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSiderealTime(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSiderealTime");
    }

    /**
     * Returns the observing site's elevation above mean sea level (metres).
     *
     * @return The observing site's elevation above mean sea level (metres).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteElevation">A full description of this member's behavior is provided here</a>
     */
    public double getSiteElevation() {
        DoubleResponse response = call(getClient().getSiteElevation(getDeviceID(), getClientID(), getTransactionID()), "getSiteElevation");
        return response.getValue();
    }

    /**
     * Returns the observing site's elevation above mean sea level (metres).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteElevation">A full description of this member's behavior is provided here</a>
     */
    public void getSiteElevation(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSiteElevation(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSiteElevation");
    }

    /**
     * Sets the observing site's elevation above mean sea level (metres).
     *
     * @param siteElevation The new observing site's elevation above mean sea level (metres).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteElevation">A full description of this member's behavior is provided here</a>
     */
    public void setSiteElevation(double siteElevation) {
        AlpacaResponse response = call(getClient().setSiteElevation(getDeviceID(), siteElevation, getClientID(), getTransactionID()), "setSiteElevation", siteElevation);
    }

    /**
     * Sets the observing site's elevation above mean sea level (metres).
     *
     * @param siteElevation The new observing site's elevation above mean sea level (metres).
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteElevation">A full description of this member's behavior is provided here</a>
     */
    public void setSiteElevation(double siteElevation, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSiteElevation(getDeviceID(), siteElevation, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSiteElevation", siteElevation);
    }

    /**
     * Returns the observing site's latitude (degrees).
     *
     * @return The observing site's latitude (degrees).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteLatitude">A full description of this member's behavior is provided here</a>
     */
    public double getSiteLatitude() {
        DoubleResponse response = call(getClient().getSiteLatitude(getDeviceID(), getClientID(), getTransactionID()), "getSiteLatitude");
        return response.getValue();
    }

    /**
     * Returns the observing site's latitude (degrees).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteLatitude">A full description of this member's behavior is provided here</a>
     */
    public void getSiteLatitude(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSiteLatitude(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSiteLatitude");
    }

    /**
     * Sets the observing site's latitude.
     *
     * @param siteLatitude The new observing site's latitude (degrees).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteLatitude">A full description of this member's behavior is provided here</a>
     */
    public void setSiteLatitude(double siteLatitude) {
        AlpacaResponse response = call(getClient().setSiteLatitude(getDeviceID(), siteLatitude, getClientID(), getTransactionID()), "setSiteLatitude", siteLatitude);
    }

    /**
     * Sets the observing site's latitude.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteLatitude">A full description of this member's behavior is provided here</a>
     */
    public void setSiteLatitude(double siteLatitude, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSiteLatitude(getDeviceID(), siteLatitude, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSiteLatitude", siteLatitude);
    }

    /**
     * Returns the observing site's longitude (degrees, positive East, WGS84).
     *
     * @return The observing site's longitude (degrees, positive East, WGS84).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteLongitude">A full description of this member's behavior is provided here</a>
     */
    public double getSiteLongitude() {
        DoubleResponse response = call(getClient().getSiteLongitude(getDeviceID(), getClientID(), getTransactionID()), "getSiteLongitude");
        return response.getValue();
    }

    /**
     * Returns the observing site's longitude (degrees, positive East, WGS84).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteLongitude">A full description of this member's behavior is provided here</a>
     */
    public void getSiteLongitude(AlpacaCallback<Double> callback) {
        callAsync(getClient().getSiteLongitude(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSiteLongitude");
    }

    /**
     * Sets the observing site's longitude.
     *
     * @param siteLongitude The new observing site's longitude (degrees, positive East, WGS84).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteLongitude">A full description of this member's behavior is provided here</a>
     */
    public void setSiteLongitude(double siteLongitude) {
        AlpacaResponse response = call(getClient().setSiteLatitude(getDeviceID(), siteLongitude, getClientID(), getTransactionID()), "setSiteLongitude", siteLongitude);
    }

    /**
     * Sets the observing site's longitude.
     *
     * @param siteLongitude The new observing site's longitude (degrees, positive East, WGS84).
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SiteLongitude">A full description of this member's behavior is provided here</a>
     */
    public void setSiteLongitude(double siteLongitude, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSiteLongitude(getDeviceID(), siteLongitude, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSiteLongitude", siteLongitude);
    }

    /**
     * Indicates whether the telescope is currently slewing.
     *
     * @return is the telescope slewing
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Slewing">A full description of this member's behavior is provided here</a>
     */
    public boolean isSlewing() {
        BooleanResponse response = call(getClient().isSlewing(getDeviceID(), getClientID(), getTransactionID()), "isSlewing");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope is currently slewing.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Slewing">A full description of this member's behavior is provided here</a>
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
     * Returns the post-slew settling time.
     *
     * @return The post-slew settling time.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewSettleTime">A full description of this member's behavior is provided here</a>
     */
    public int getSlewSettleTime() {
        IntResponse response = call(getClient().getSlewSettleTime(getDeviceID(), getClientID(), getTransactionID()), "getSlewSettleTime");
        return response.getValue();
    }

    /**
     * Returns the post-slew settling time.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewSettleTime">A full description of this member's behavior is provided here</a>
     */
    public void getSlewSettleTime(AlpacaCallback<Integer> callback) {
        callAsync(getClient().getSlewSettleTime(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getSlewSettleTime");
    }

    /**
     * Sets the post-slew settling time (seconds).
     *
     * @param slewSettleTime The new post-slew settling time (seconds).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewSettleTime">A full description of this member's behavior is provided here</a>
     */
    public void setSlewSettleTime(int slewSettleTime) {
        AlpacaResponse response = call(getClient().setSlewSettleTime(getDeviceID(), slewSettleTime, getClientID(), getTransactionID()), "setSlewSettleTime", slewSettleTime);
    }

    /**
     * Sets the post-slew settling time (seconds).
     *
     * @param slewSettleTime The new post-slew settling time (seconds).
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewSettleTime">A full description of this member's behavior is provided here</a>
     */
    public void setSlewSettleTime(int slewSettleTime, AlpacaCallback<Void> callback) {
        callAsync(getClient().setSlewSettleTime(getDeviceID(), slewSettleTime, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setSlewSettleTime", slewSettleTime);
    }

    /**
     * Returns the current target declination (degrees, positive North).
     *
     * @return The current target declination (degrees, positive North).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TargetDeclination">A full description of this member's behavior is provided here</a>
     */
    public double getTargetDeclination() {
        DoubleResponse response = call(getClient().getTargetDeclination(getDeviceID(), getClientID(), getTransactionID()), "getTargetDeclination");
        return response.getValue();
    }

    /**
     * Returns the current target declination (degrees, positive North).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TargetDeclination">A full description of this member's behavior is provided here</a>
     */
    public void getTargetDeclination(AlpacaCallback<Double> callback) {
        callAsync(getClient().getTargetDeclination(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTargetDeclination");
    }

    /**
     * Sets the target declination of a slew or sync (degrees, positive North)
     *
     * @param targetDeclination The new target declination (degrees, positive North).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TargetDeclination">A full description of this member's behavior is provided here</a>
     */
    public void setTargetDeclination(int targetDeclination) {
        AlpacaResponse response = call(getClient().setTargetDeclination(getDeviceID(), targetDeclination, getClientID(), getTransactionID()), "setTargetDeclination", targetDeclination);
    }

    /**
     * Sets the target declination of a slew or sync (degrees, positive North)
     *
     * @param targetDeclination The new target declination (degrees, positive North).
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TargetDeclination">A full description of this member's behavior is provided here</a>
     */
    public void setTargetDeclination(int targetDeclination, AlpacaCallback<Void> callback) {
        callAsync(getClient().setTargetDeclination(getDeviceID(), targetDeclination, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setTargetDeclination", targetDeclination);
    }

    /**
     * Returns the current target right ascension (hours).
     *
     * @return The current target right ascension (hours).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TargetRightAscension">A full description of this member's behavior is provided here</a>
     */
    public double getTargetRightAscension() {
        DoubleResponse response = call(getClient().getTargetRightAscension(getDeviceID(), getClientID(), getTransactionID()), "getTargetRightAscension");
        return response.getValue();
    }

    /**
     * Returns the current target right ascension (hours).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TargetRightAscension">A full description of this member's behavior is provided here</a>
     */
    public void getTargetRightAscension(AlpacaCallback<Double> callback) {
        callAsync(getClient().getTargetRightAscension(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(DoubleResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTargetRightAscension");
    }

    /**
     * Sets the target right ascension of a slew or sync (hours).
     *
     * @param targetRightAscension
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TargetRightAscension">A full description of this member's behavior is provided here</a>
     */
    public void setTargetRightAscension(int targetRightAscension) {
        AlpacaResponse response = call(getClient().setTargetRightAscension(getDeviceID(), targetRightAscension, getClientID(), getTransactionID()), "setTargetRightAscension", targetRightAscension);
    }

    /**
     * Sets the target right ascension of a slew or sync (hours).
     *
     * @param targetRightAscension
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TargetRightAscension">A full description of this member's behavior is provided here</a>
     */
    public void setTargetRightAscension(int targetRightAscension, AlpacaCallback<Void> callback) {
        callAsync(getClient().setTargetRightAscension(getDeviceID(), targetRightAscension, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setTargetRightAscension", targetRightAscension);
    }

    /**
     * Indicates whether the telescope is tracking.
     *
     * @return is the telescope tracking
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Tracking">A full description of this member's behavior is provided here</a>
     */
    public boolean isTracking() {
        BooleanResponse response = call(getClient().isTracking(getDeviceID(), getClientID(), getTransactionID()), "isTracking");
        return response.getValue();
    }

    /**
     * Indicates whether the telescope is tracking.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Tracking">A full description of this member's behavior is provided here</a>
     */
    public void isTracking(AlpacaCallback<Boolean> callback) {
        callAsync(getClient().isTracking(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "isTracking");
    }

    /**
     * Enables or disables telescope tracking.
     *
     * @param tracking enable or disable tracking
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Tracking">A full description of this member's behavior is provided here</a>
     */
    public void setTracking(boolean tracking) {
        AlpacaResponse response = call(getClient().setTracking(getDeviceID(), tracking, getClientID(), getTransactionID()), "setTracking", tracking);
    }

    /**
     * Enables or disables telescope tracking.
     *
     * @param tracking enable or disable tracking
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Tracking">A full description of this member's behavior is provided here</a>
     */
    public void setTracking(boolean tracking, AlpacaCallback<Void> callback) {
        callAsync(getClient().setTracking(getDeviceID(), tracking, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setTracking", tracking);
    }

    /**
     * Returns the current tracking rate.
     *
     * @return
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TrackingRate">A full description of this member's behavior is provided here</a>
     */
    public DriveRate getTrackingRate() {
        IntResponse response = call(getClient().getTrackingRate(getDeviceID(), getClientID(), getTransactionID()), "getTrackingRate");
        return DriveRate.fromRate(response.getValue());
    }

    /**
     * Returns the current tracking rate.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TrackingRate">A full description of this member's behavior is provided here</a>
     */
    public void getTrackingRate(AlpacaCallback<DriveRate> callback) {
        callAsync(getClient().getTrackingRate(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(DriveRate.fromRate(result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTargetRightAscension");
    }

    /**
     * Sets the mount's tracking rate.
     *
     * @param trackingRate The new tracking rate.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TrackingRate">A full description of this member's behavior is provided here</a>
     */
    public void setTrackingRate(DriveRate trackingRate) {
        AlpacaResponse response = call(getClient().setTrackingRate(getDeviceID(), trackingRate.getRate(), getClientID(), getTransactionID()), "setTrackingRate", trackingRate.getRate());
    }

    /**
     * Sets the mount's tracking rate.
     *
     * @param trackingRate The new tracking rate.
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TrackingRate">A full description of this member's behavior is provided here</a>
     */
    public void setTrackingRate(DriveRate trackingRate, AlpacaCallback<Void> callback) {
        callAsync(getClient().setTrackingRate(getDeviceID(), trackingRate.getRate(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setTrackingRate", trackingRate.getRate());
    }

    /**
     * Returns a list of supported DriveRates values.
     *
     * @return the list of supported DriveRates values.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TrackingRates">A full description of this member's behavior is provided here</a>
     */
    public List<DriveRate> getTrackingRates() {
        ListResponse<DriveRate> response = call(getClient().getTrackingRates(getDeviceID(), getClientID(), getTransactionID()), "getTrackingRates");
        return response.getValue();
    }

    /**
     * Returns a list of supported DriveRates values.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.TrackingRates">A full description of this member's behavior is provided here</a>
     */
    public void getTrackingRates(AlpacaCallback<List<DriveRate>> callback) {
        callAsync(getClient().getTrackingRates(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<DriveRate> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getTrackingRates");
    }

    /**
     * Returns the UTC date/time of the telescope's internal clock as a string.
     *
     * @return The UTC date/time of the telescope's internal clock as a string.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.UTCDate">A full description of this member's behavior is provided here</a>
     */
    public Date getUTCDate() {
        StringResponse response = call(getClient().getUTCDate(getDeviceID(), getClientID(), getTransactionID()), "getUTCDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return format.parse(response.getValue());
        } catch (ParseException e) {
            log.warn("Couldn't parse date: " + response.getValue());
            throw new InvalidValueException("Invalid date format " + e.getMessage());
        }
    }

    /**
     * Sets the UTC date/time of the telescope's internal clock.
     *
     * @param utcDate The UTC date/time to set
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.UTCDate">A full description of this member's behavior is provided here</a>
     */
    public void setUTCDate(Date utcDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        AlpacaResponse response = call(getClient().setUTCDate(getDeviceID(), format.format(utcDate), getClientID(), getTransactionID()), "setUTCDate", format.format(utcDate));
    }

    /**
     * Sets the UTC date/time of the telescope's internal clock.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.UTCDate">A full description of this member's behavior is provided here</a>
     */
    public void setUTCDate(Date utcDate, AlpacaCallback<Void> callback) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        callAsync(getClient().setUTCDate(getDeviceID(), format.format(utcDate), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "setUTCDate", format.format(utcDate));
    }

    /**
     * Immediatley stops a slew in progress.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AbortSlew">A full description of this member's behavior is provided here</a>
     */
    public void abortSlew() {
        AlpacaResponse response = call(getClient().abortSlew(getDeviceID(), getClientID(), getTransactionID()), "abortSlew");
    }

    /**
     * Immediatley stops a slew in progress.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AbortSlew">A full description of this member's behavior is provided here</a>
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
     * Returns the rates at which the telescope may be moved about the specified axis (degrees / second).
     *
     * @param axis
     * @return The rates at which the telescope may be moved about the specified axis (degrees / second).
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AxisRates">A full description of this member's behavior is provided here</a>
     */
    public List<AxisRate> getAxisRates(TelescopeAxes axis) {
        ListResponse<AxisRate> response = call(getClient().getAxisRates(getDeviceID(), axis.getAxis(), getClientID(), getTransactionID()), "getAxisRates");
        return response.getValue();
    }

    /**
     * Returns the rates at which the telescope may be moved about the specified axis (degrees / second).
     *
     * @param axis
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.AxisRates">A full description of this member's behavior is provided here</a>
     */
    public void getAxisRates(TelescopeAxes axis, AlpacaCallback<List<AxisRate>> callback) {
        callAsync(getClient().getAxisRates(getDeviceID(), axis.getAxis(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(ListResponse<AxisRate> result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getAxisRates", axis.getAxis());
    }

    /**
     * Indicates whether the telescope can move the requested axis.
     *
     * @param axis
     * @return can the telescope move the requested axis
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanMoveAxis">A full description of this member's behavior is provided here</a>
     */
    public boolean canMoveAxis(TelescopeAxes axis) {
        BooleanResponse response = call(getClient().canMoveAxis(getDeviceID(), axis.getAxis(), getClientID(), getTransactionID()), "canMoveAxis", axis.getAxis());
        return response.getValue();
    }

    /**
     * Indicates whether the telescope can move the requested axis.
     *
     * @param axis
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.CanMoveAxis">A full description of this member's behavior is provided here</a>
     */
    public void canMoveAxis(TelescopeAxes axis, AlpacaCallback<Boolean> callback) {
        callAsync(getClient().canMoveAxis(getDeviceID(), axis.getAxis(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(BooleanResponse result) {
                callback.success(result.getValue());
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "canMoveAxis", axis.getAxis());
    }

    /**
     * Predicts the pointing state after a German equatorial mount slews to given coordinates.
     *
     * @param rightAscension
     * @param declination
     * @return The predicted pointing state after it slews to given coordinates.
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DestinationSideOfPier">A full description of this member's behavior is provided here</a>
     */
    public PierSide getDestinationSideOfPier(double rightAscension, double declination) {
        IntResponse response = call(getClient().getDestinationSideOfPier(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), "getDestinationSideOfPier");
        return PierSide.fromSide(response.getValue());
    }

    /**
     * Predicts the pointing state after a German equatorial mount slews to given coordinates.
     *
     * @param rightAscension
     * @param declination
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.DestinationSideOfPier">A full description of this member's behavior is provided here</a>
     */
    public void getDestinationSideOfPier(double rightAscension, double declination, AlpacaCallback<PierSide> callback) {
        callAsync(getClient().getDestinationSideOfPier(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(IntResponse result) {
                callback.success(PierSide.fromSide(result.getValue()));
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "getDestinationSideOfPier");
    }

    /**
     * Moves the mount to its "home" position.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.FindHome">A full description of this member's behavior is provided here</a>
     */
    public void findHome() {
        AlpacaResponse response = call(getClient().findHome(getDeviceID(), getClientID(), getTransactionID()), "findHome");
    }

    /**
     * Moves the mount to its "home" position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.FindHome">A full description of this member's behavior is provided here</a>
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
     * Moves a telescope axis at the given rate (degrees / second).
     *
     * @param
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.MoveAxis">A full description of this member's behavior is provided here</a>
     */
    public void moveAxis(int axis, double rate) {
        AlpacaResponse response = call(getClient().moveAxis(getDeviceID(), axis, rate, getClientID(), getTransactionID()), "moveAxis", axis, rate);
    }

    /**
     * Moves a telescope axis at the given rate (degrees / second).
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.MoveAxis">A full description of this member's behavior is provided here</a>
     */
    public void moveAxis(int axis, double rate, AlpacaCallback<Void> callback) {
        callAsync(getClient().moveAxis(getDeviceID(), axis, rate, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "moveAxis", axis, rate);
    }

    /**
     * Parks the mount
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Park">A full description of this member's behavior is provided here</a>
     */
    public void park() {
        AlpacaResponse response = call(getClient().park(getDeviceID(), getClientID(), getTransactionID()), "park");
    }

    /**
     * Parks the mount
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Park">A full description of this member's behavior is provided here</a>
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
     * Moves the scope in the given direction for the given time.
     *
     * @param direction
     * @param duration
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.PulseGuide">A full description of this member's behavior is provided here</a>
     */
    public void pulseguide(int direction, int duration) {
        AlpacaResponse response = call(getClient().pulseguide(getDeviceID(), getClientID(), getTransactionID(), direction, duration), "pulseguide", direction, duration);
    }

    /**
     * Moves the scope in the given direction for the given time.
     *
     * @param direction the direction of movement
     * @param duration duration of movement (milliseconds)
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.PulseGuide">A full description of this member's behavior is provided here</a>
     */
    public void pulseguide(int direction, int duration, AlpacaCallback<Void> callback) {
        callAsync(getClient().pulseguide(getDeviceID(), getClientID(), getTransactionID(), direction, duration), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "pulseguide", direction, duration);
    }

    /**
     * Sets the current position as the telescope's park position.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SetPark">A full description of this member's behavior is provided here</a>
     */
    public void setPark() {
        AlpacaResponse response = call(getClient().setPark(getDeviceID(), getClientID(), getTransactionID()), "setPark");
    }

    /**
     * Sets the current position as the telescope's park position.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SetPark">A full description of this member's behavior is provided here</a>
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
     * Synchronously slew to the given Alt/Az coordinates.
     *
     * @param direction the azimuth to slew to
     * @param altitude the altitude to slew to
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToAltAz">A full description of this member's behavior is provided here</a>
     */
    public void slewToAltAz(double direction, double altitude) {
        AlpacaResponse response = call(getClient().slewToAltAz(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), "slewToAltAz", direction, altitude);
    }

    /**
     * Synchronously slew to the given Alt/Az coordinates.
     *
     * @param direction the azimuth to slew to
     * @param altitude the altitude to slew to
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToAltAz">A full description of this member's behavior is provided here</a>
     */
    public void slewToAltAz(double direction, double altitude, AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToAltAz(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToAltAz", direction, altitude);
    }

    /**
     * Asynchronously slew to the given Alt/Az coordinates. Note that this means the
     * device will return before the slew completes vs slewToAltAz which only returns
     * when the slew is complete.
     *
     * @param direction the azimuth to slew to
     * @param altitude the altitude to slew to
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToAltAzAsync">A full description of this member's behavior is provided here</a>
     */
    public void slewToAltAzAsync(double direction, double altitude) {
        AlpacaResponse response = call(getClient().slewToAltAzAsync(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), "slewToAltAzAsync", direction, altitude);
    }

    /**
     * Asynchronously slew to the given Alt/Az coordinates.
     *
     * @param direction the azimuth to slew to
     * @param altitude the altitude to slew to
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToAltAzAsync">A full description of this member's behavior is provided here</a>
     */
    public void slewToAltAzAsync(double direction, double altitude, AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToAltAzAsync(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToAltAzAsync", direction, altitude);
    }

    /**
     * Synchronously slew to the given equatorial coordinates.
     *
     * @param rightAscension the right ascension to slew to
     * @param declination the declination to slew to
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToCoordinates">A full description of this member's behavior is provided here</a>
     */
    public void slewToCoordinates(double rightAscension, double declination) {
        AlpacaResponse response = call(getClient().slewToCoordinates(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), "slewToCoordinates", rightAscension, declination);
    }

    /**
     * Synchronously slew to the given equatorial coordinates.
     *
     * @param rightAscension the right ascension to slew to
     * @param declination the declination to slew to
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToCoordinates">A full description of this member's behavior is provided here</a>
     */
    public void slewToCoordinates(double rightAscension, double declination, AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToCoordinates(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToCoordinates", rightAscension, declination);
    }

    /**
     * Asynchronously slew to the given equatorial coordinates. Note that this means the
     * device will return before the slew completes vs slewToCoordinates which only returns
      when the slew is complete.
     *
     * @param rightAscension the right ascension to slew to
     * @param declination the declination to slew to
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToCoordinatesAsync">A full description of this member's behavior is provided here</a>
     */
    public void slewToCoordinatesAsync(double rightAscension, double declination) {
        AlpacaResponse response = call(getClient().slewToCoordinatesAsync(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), "slewToCoordinatesAsync", rightAscension, declination);
    }

    /**
     * Asynchronously slew to the given equatorial coordinates.
     *
     * @param rightAscension the right ascension to slew to
     * @param declination the declination to slew to
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToCoordinatesAsync">A full description of this member's behavior is provided here</a>
     */
    public void slewToCoordinatesAsync(double rightAscension, double declination, AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToCoordinatesAsync(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToCoordinatesAsync", rightAscension, declination);
    }

    /**
     * Synchronously slew to the TargetRightAscension and TargetDeclination coordinates.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToTarget">A full description of this member's behavior is provided here</a>
     */
    public void slewToTarget() {
        AlpacaResponse response = call(getClient().slewToTarget(getDeviceID(), getClientID(), getTransactionID()), "slewToTarget");
    }

    /**
     * Synchronously slew to the TargetRightAscension and TargetDeclination coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToTarget">A full description of this member's behavior is provided here</a>
     */
    public void slewToTarget(AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToTarget(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToTarget");
    }

    /**
     * Asynchronously slew to the TargetRightAscension and TargetDeclination coordinates.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToTargetAsync">A full description of this member's behavior is provided here</a>
     */
    public void slewToTargetAsync() {
        AlpacaResponse response = call(getClient().slewToTargetAsync(getDeviceID(), getClientID(), getTransactionID()), "slewToTargetAsync");
    }

    /**
     * Asynchronously slew to the TargetRightAscension and TargetDeclination coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SlewToTargetAsync">A full description of this member's behavior is provided here</a>
     */
    public void slewToTargetAsync(AlpacaCallback<Void> callback) {
        callAsync(getClient().slewToTargetAsync(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "slewToTargetAsync");
    }

    /**
     * Sync to the given Alt/Az coordinates.
     *
     * @param direction the azimuth to sync to
     * @param altitude the altitude to sync to
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SyncToAltAz">A full description of this member's behavior is provided here</a>
     */
    public void syncToAltAz(double direction, double altitude) {
        AlpacaResponse response = call(getClient().slewToAltAz(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), "syncToAltAz", direction, altitude);
    }

    /**
     * Sync to the given Alt/Az coordinates.
     *
     * @param direction the azimuth to sync to
     * @param altitude the altitude to sync to
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SyncToAltAz">A full description of this member's behavior is provided here</a>
     */
    public void syncToAltAz(double direction, double altitude, AlpacaCallback<Void> callback) {
        callAsync(getClient().syncToAltAz(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "syncToAltAz", direction, altitude);
    }

    /**
     * Syncs to the given equatorial coordinates.
     *
     * @param rightAscension the right ascension to sync to
     * @param declination the declination to sync to
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SyncToCoordinates">A full description of this member's behavior is provided here</a>
     */
    public void syncToCoordinates(double rightAscension, double declination) {
        AlpacaResponse response = call(getClient().syncToCoordinates(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), "syncToCoordinates", rightAscension, declination);
    }

    /**
     * Syncs to the given equatorial coordinates.
     *
     * @param rightAscension the right ascension to sync to
     * @param declination the declination to sync to
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SyncToCoordinates">A full description of this member's behavior is provided here</a>
     */
    public void syncToCoordinates(double rightAscension, double declination, AlpacaCallback<Void> callback) {
        callAsync(getClient().syncToCoordinates(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "syncToCoordinates", rightAscension, declination);
    }

    /**
     * Syncs to the TargetRightAscension and TargetDeclination coordinates.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SyncToTarget">A full description of this member's behavior is provided here</a>
     */
    public void syncToTarget() {
        AlpacaResponse response = call(getClient().syncToTarget(getDeviceID(), getClientID(), getTransactionID()), "syncToTarget");
    }

    /**
     * Syncs to the TargetRightAscension and TargetDeclination coordinates.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.SyncToTarget">A full description of this member's behavior is provided here</a>
     */
    public void syncToTarget(AlpacaCallback<Void> callback) {
        callAsync(getClient().syncToTarget(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "syncToTarget");
    }

    /**
     * Unparks the mount.
     *
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Unpark">A full description of this member's behavior is provided here</a>
     */
    public void unpark() {
        AlpacaResponse response = call(getClient().unpark(getDeviceID(), getClientID(), getTransactionID()), "unpark");
    }

    /**
     * Unparks the mount.
     *
     * @param callback Callback to invoke when the operation completes
     * @throws ClientException If there is a problem communicating with the device
     * @throws org.ascom.alpaca.response.ServerException If there is an error returned by the device
     * @see <a href="https://ascom-standards.org/newdocs/telescope.html#Telescope.Unpark">A full description of this member's behavior is provided here</a>
     */
    public void unpark(AlpacaCallback<Void> callback) {
        callAsync(getClient().unpark(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
            @Override
            public void success(AlpacaResponse result) {
                callback.success(null);
            }

            @Override
            public void error(AlpacaClientError error) {
                callback.error(error);
            }
        }, "unpark");
    }
}
