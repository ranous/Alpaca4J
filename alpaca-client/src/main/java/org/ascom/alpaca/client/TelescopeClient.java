package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Telescope;
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

    public AlignmentMode getAlignmentMode() {
        IntResponse response = call(getClient().getAlignmentMode(getDeviceID(), getClientID(), getTransactionID()), "getAlignmentMode");
        return AlignmentMode.fromMode(response.getValue());
    }

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

    public double getAltitude() {
        DoubleResponse response = call(getClient().getAltitude(getDeviceID(), getClientID(), getTransactionID()), "getAltitude");
        return response.getValue();
    }

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

    public double getApertureArea() {
        DoubleResponse response = call(getClient().getApertureArea(getDeviceID(), getClientID(), getTransactionID()), "getApertureArea");
        return response.getValue();
    }

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

    public double getApertureDiameter() {
        DoubleResponse response = call(getClient().getApertureDiameter(getDeviceID(), getClientID(), getTransactionID()), "getApertureDiameter");
        return response.getValue();
    }

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

    public boolean isAtHome() {
        BooleanResponse response = call(getClient().isAtHome(getDeviceID(), getClientID(), getTransactionID()), "isAtHome");
        return response.getValue();
    }

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

    public boolean isAtPark() {
        BooleanResponse response = call(getClient().isAtPark(getDeviceID(), getClientID(), getTransactionID()), "isAtPark");
        return response.getValue();
    }

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

    public double getAzimuth() {
        DoubleResponse response = call(getClient().getAzimuth(getDeviceID(), getClientID(), getTransactionID()), "getAzimuth");
        return response.getValue();
    }

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

    public boolean canFindHome() {
        BooleanResponse response = call(getClient().canFindHome(getDeviceID(), getClientID(), getTransactionID()), "canFindHome");
        return response.getValue();
    }

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

    public boolean canPark() {
        BooleanResponse response = call(getClient().canPark(getDeviceID(), getClientID(), getTransactionID()), "canPark");
        return response.getValue();
    }

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

    public boolean canSetDeclinationRate() {
        BooleanResponse response = call(getClient().canSetDeclinationRate(getDeviceID(), getClientID(), getTransactionID()), "canSetDeclinationRate");
        return response.getValue();
    }

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

    public boolean canSetGuideRates() {
        BooleanResponse response = call(getClient().canSetGuideRates(getDeviceID(), getClientID(), getTransactionID()), "canSetGuideRates");
        return response.getValue();
    }

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

    public boolean canSetPark() {
        BooleanResponse response = call(getClient().canSetPark(getDeviceID(), getClientID(), getTransactionID()), "canSetPark");
        return response.getValue();
    }

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

    public boolean canSetPierSide() {
        BooleanResponse response = call(getClient().canSetPierSide(getDeviceID(), getClientID(), getTransactionID()), "canSetPierSide");
        return response.getValue();
    }

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

    public boolean canSetRightAscensionRate() {
        BooleanResponse response = call(getClient().canSetRightAscensionRate(getDeviceID(), getClientID(), getTransactionID()), "canSetRightAscensionRate");
        return response.getValue();
    }

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

    public boolean canSetTracking() {
        BooleanResponse response = call(getClient().canSetTracking(getDeviceID(), getClientID(), getTransactionID()), "canSetTracking");
        return response.getValue();
    }

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

    public boolean canSlew() {
        BooleanResponse response = call(getClient().canSlew(getDeviceID(), getClientID(), getTransactionID()), "canSlew");
        return response.getValue();
    }

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

    public boolean canSlewAltAz() {
        BooleanResponse response = call(getClient().canSlewAltAz(getDeviceID(), getClientID(), getTransactionID()), "canSlewAltAz");
        return response.getValue();
    }

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

    public boolean canSlewAltAzAsync() {
        BooleanResponse response = call(getClient().canSlewAltAzAsync(getDeviceID(), getClientID(), getTransactionID()), "canSlewAltAzAsync");
        return response.getValue();
    }

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

    public boolean canSlewAsync() {
        BooleanResponse response = call(getClient().canSlewAsync(getDeviceID(), getClientID(), getTransactionID()), "canSlewAsync");
        return response.getValue();
    }

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

    public boolean canSync() {
        BooleanResponse response = call(getClient().canSync(getDeviceID(), getClientID(), getTransactionID()), "canSync");
        return response.getValue();
    }

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

    public boolean canSyncAltAz() {
        BooleanResponse response = call(getClient().canSyncAltAz(getDeviceID(), getClientID(), getTransactionID()), "canSyncAltAz");
        return response.getValue();
    }

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

    public boolean canUnpark() {
        BooleanResponse response = call(getClient().canUnpark(getDeviceID(), getClientID(), getTransactionID()), "canUnpark");
        return response.getValue();
    }

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

    public double getDeclination() {
        DoubleResponse response = call(getClient().getDeclination(getDeviceID(), getClientID(), getTransactionID()), "getDeclination");
        return response.getValue();
    }

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

    public double getDeclinationRate() {
        DoubleResponse response = call(getClient().getDeclinationRate(getDeviceID(), getClientID(), getTransactionID()), "getDeclinationRate");
        return response.getValue();
    }

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

    public void setDeclinationRate(double declinationRate) {
        AlpacaResponse response = call(getClient().setDeclinationRate(getDeviceID(), declinationRate, getClientID(), getTransactionID()), "setDeclinationRate", declinationRate);
    }

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

    public boolean doesRefraction() {
        BooleanResponse response = call(getClient().doesRefraction(getDeviceID(), getClientID(), getTransactionID()), "doesRefraction");
        return response.getValue();
    }

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

    public void setDoesRefraction(boolean doesRefraction) {
        AlpacaResponse response = call(getClient().setDoesRefraction(getDeviceID(), doesRefraction, getClientID(), getTransactionID()), "setDoesRefraction", doesRefraction);
    }

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

    public EquatorialCoordinateType getEquatorialSystem() {
        IntResponse response = call(getClient().getEquatorialSystem(getDeviceID(), getClientID(), getTransactionID()), "getEquatorialSystem");
        return EquatorialCoordinateType.fromType(response.getValue());
    }

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

    public double getFocalLength() {
        DoubleResponse response = call(getClient().getFocalLength(getDeviceID(), getClientID(), getTransactionID()), "getFocalLength");
        return response.getValue();
    }

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

    public double getGuideRateDeclination() {
        DoubleResponse response = call(getClient().getGuideRateDeclination(getDeviceID(), getClientID(), getTransactionID()), "getGuideRateDeclination");
        return response.getValue();
    }

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

    public void setGuideRateDeclination(double guideRate) {
        AlpacaResponse response = call(getClient().setGuideRateDeclination(getDeviceID(), guideRate, getClientID(), getTransactionID()), "setGuideRateDeclination", guideRate);
    }

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

    public double getGuideRateRightAscension() {
        DoubleResponse response = call(getClient().getGuideRateRightAscension(getDeviceID(), getClientID(), getTransactionID()), "getGuideRateRightAscension");
        return response.getValue();
    }

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

    public void setGuideRateRightAscension(double guideRate) {
        AlpacaResponse response = call(getClient().setGuideRateRightAscension(getDeviceID(), guideRate, getClientID(), getTransactionID()), "setGuideRateRightAscension", guideRate);

    }

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

    public double getRightAscension() {
        DoubleResponse response = call(getClient().getRightAscension(getDeviceID(), getClientID(), getTransactionID()), "getRightAscension");
        return response.getValue();
    }

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

    public double getRightAscensionRate() {
        DoubleResponse response = call(getClient().getRightAscensionRate(getDeviceID(), getClientID(), getTransactionID()), "getRightAscensionRate");
        return response.getValue();
    }

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

    public void setRightAscensionRate(double rightAscensionRate) {
        AlpacaResponse response = call(getClient().setRightAscensionRate(getDeviceID(), rightAscensionRate, getClientID(), getTransactionID()), "setRightAscensionRate", rightAscensionRate);
    }

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

    public PierSide getSideOfPier() {
        IntResponse response = call(getClient().getSideOfPier(getDeviceID(), getClientID(), getTransactionID()), "getSideOfPier");
        return PierSide.fromSide(response.getValue());
    }

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

    public void setSideOfPier(PierSide sideOfPier) {
        AlpacaResponse response = call(getClient().setSideOfPier(getDeviceID(), sideOfPier.ordinal(), getClientID(), getTransactionID()), "setSideOfPier", sideOfPier.ordinal());
    }

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

    public double getSiderealTime() {
        DoubleResponse response = call(getClient().getSiderealTime(getDeviceID(), getClientID(), getTransactionID()), "getSiderealTime");
        return response.getValue();
    }

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

    public double getSiteElevation() {
        DoubleResponse response = call(getClient().getSiteElevation(getDeviceID(), getClientID(), getTransactionID()), "getSiteElevation");
        return response.getValue();
    }

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

    public void setSiteElevation(double siteElevation) {
        AlpacaResponse response = call(getClient().setSiteElevation(getDeviceID(), siteElevation, getClientID(), getTransactionID()), "setSiteElevation", siteElevation);
    }

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

    public double getSiteLatitude() {
        DoubleResponse response = call(getClient().getSiteLatitude(getDeviceID(), getClientID(), getTransactionID()), "getSiteLatitude");
        return response.getValue();
    }

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

    public void setSiteLatitude(double siteLatitude) {
        AlpacaResponse response = call(getClient().setSiteLatitude(getDeviceID(), siteLatitude, getClientID(), getTransactionID()), "setSiteLatitude", siteLatitude);
    }

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

    public double getSiteLongitude() {
        DoubleResponse response = call(getClient().getSiteLongitude(getDeviceID(), getClientID(), getTransactionID()), "getSiteLongitude");
        return response.getValue();
    }

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

    public void setSiteLongitude(double siteLongitude) {
        AlpacaResponse response = call(getClient().setSiteLatitude(getDeviceID(), siteLongitude, getClientID(), getTransactionID()), "setSiteLongitude", siteLongitude);
    }

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

    public boolean isSlewing() {
        BooleanResponse response = call(getClient().isSlewing(getDeviceID(), getClientID(), getTransactionID()), "isSlewing");
        return response.getValue();
    }

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

    public int getSlewSettleTime() {
        IntResponse response = call(getClient().getSlewSettleTime(getDeviceID(), getClientID(), getTransactionID()), "getSlewSettleTime");
        return response.getValue();
    }

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

    public void setSlewSettleTime(int slewSettleTime) {
        AlpacaResponse response = call(getClient().setSlewSettleTime(getDeviceID(), slewSettleTime, getClientID(), getTransactionID()), "setSlewSettleTime", slewSettleTime);
    }

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

    public double getTargetDeclination() {
        DoubleResponse response = call(getClient().getTargetDeclination(getDeviceID(), getClientID(), getTransactionID()), "getTargetDeclination");
        return response.getValue();
    }

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

    public void setTargetDeclination(int targetDeclination) {
        AlpacaResponse response = call(getClient().setTargetDeclination(getDeviceID(), targetDeclination, getClientID(), getTransactionID()), "setTargetDeclination", targetDeclination);
    }

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

    public double getTargetRightAscension() {
        DoubleResponse response = call(getClient().getTargetRightAscension(getDeviceID(), getClientID(), getTransactionID()), "getTargetRightAscension");
        return response.getValue();
    }

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

    public void setTargetRightAscension(int targetRightAscension) {
        AlpacaResponse response = call(getClient().setTargetRightAscension(getDeviceID(), targetRightAscension, getClientID(), getTransactionID()), "setTargetRightAscension", targetRightAscension);
    }

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

    public boolean isTracking() {
        BooleanResponse response = call(getClient().isTracking(getDeviceID(), getClientID(), getTransactionID()), "isTracking");
        return response.getValue();
    }

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

    public void setTracking(boolean tracking) {
        AlpacaResponse response = call(getClient().setTracking(getDeviceID(), tracking, getClientID(), getTransactionID()), "setTracking", tracking);
    }

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

    public DriveRate getTrackingRate() {
        IntResponse response = call(getClient().getTrackingRate(getDeviceID(), getClientID(), getTransactionID()), "getTrackingRate");
        return DriveRate.fromRate(response.getValue());
    }

    public void setTrackingRate(DriveRate trackingRate) {
        AlpacaResponse response = call(getClient().setTrackingRate(getDeviceID(), trackingRate.getRate(), getClientID(), getTransactionID()), "setTrackingRate", trackingRate.getRate());
    }

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

    public List<DriveRate> getTrackingRates() {
        ListResponse<DriveRate> response = call(getClient().getTrackingRates(getDeviceID(), getClientID(), getTransactionID()), "getTrackingRates");
        return response.getValue();
    }

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

    public Date getUTCDate() {
        StringResponse response = call(getClient().getUTCDate(getDeviceID(), getClientID(), getTransactionID()), "getUTCDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return format.parse(response.getValue());
        } catch (ParseException e) {
            log.warn("Couldn't parse date: {}", response.getValue());
            throw new InvalidValueException("Invalid date format " + e.getMessage());
        }
    }

    public void setUTCDate(Date utcDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        AlpacaResponse response = call(getClient().setUTCDate(getDeviceID(), format.format(utcDate), getClientID(), getTransactionID()), "setUTCDate", format.format(utcDate));
    }

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

    public void abortSlew() {
        AlpacaResponse response = call(getClient().abortSlew(getDeviceID(), getClientID(), getTransactionID()), "abortSlew");
    }

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

    public List<AxisRate> getAxisRates(TelescopeAxes axis) {
        ListResponse<AxisRate> response = call(getClient().getAxisRates(getDeviceID(), axis.getAxis(), getClientID(), getTransactionID()), "getAxisRates");
        return response.getValue();
    }

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

    public boolean canMoveAxis(TelescopeAxes axis) {
        BooleanResponse response = call(getClient().canMoveAxis(getDeviceID(), axis.getAxis(), getClientID(), getTransactionID()), "canMoveAxis", axis.getAxis());
        return response.getValue();
    }

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

    public PierSide getDestinationSideOfPier() {
        IntResponse response = call(getClient().getDestinationSideOfPier(getDeviceID(), getClientID(), getTransactionID()), "getDestinationSideOfPier");
        return PierSide.fromSide(response.getValue());
    }

    public void getDestinationSideOfPier(AlpacaCallback<PierSide> callback) {
        callAsync(getClient().getDestinationSideOfPier(getDeviceID(), getClientID(), getTransactionID()), new AlpacaCallback<>() {
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

    public void findHome() {
        AlpacaResponse response = call(getClient().findHome(getDeviceID(), getClientID(), getTransactionID()), "findHome");
    }

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

    public void moveAxis(int axis, double rate) {
        AlpacaResponse response = call(getClient().moveAxis(getDeviceID(), axis, rate, getClientID(), getTransactionID()), "moveAxis", axis, rate);
    }

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

    public void park() {
        AlpacaResponse response = call(getClient().park(getDeviceID(), getClientID(), getTransactionID()), "park");
    }

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

    public void pulseguide(int direction, int duration) {
        AlpacaResponse response = call(getClient().pulseguide(getDeviceID(), getClientID(), getTransactionID(), direction, duration), "pulseguide", direction, duration);
    }

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

    public void setPark() {
        AlpacaResponse response = call(getClient().setPark(getDeviceID(), getClientID(), getTransactionID()), "setPark");
    }

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

    public void slewToAltAz(double direction, double altitude) {
        AlpacaResponse response = call(getClient().slewToAltAz(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), "slewToAltAz", direction, altitude);
    }

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

    public void slewToAltAzAsync(double direction, double altitude) {
        AlpacaResponse response = call(getClient().slewToAltAzAsync(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), "slewToAltAzAsync", direction, altitude);
    }

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

    public void slewToCoordinates(double rightAscension, double declination) {
        AlpacaResponse response = call(getClient().slewToCoordinates(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), "slewToCoordinates", rightAscension, declination);
    }

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

    public void slewToCoordinatesAsync(double rightAscension, double declination) {
        AlpacaResponse response = call(getClient().slewToCoordinatesAsync(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), "slewToCoordinatesAsync", rightAscension, declination);
    }

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

    public void slewToTarget() {
        AlpacaResponse response = call(getClient().slewToTarget(getDeviceID(), getClientID(), getTransactionID()), "slewToTarget");
    }

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

    public void slewToTargetAsync() {
        AlpacaResponse response = call(getClient().slewToTargetAsync(getDeviceID(), getClientID(), getTransactionID()), "slewToTargetAsync");
    }

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

    public void syncToAltAz(double direction, double altitude) {
        AlpacaResponse response = call(getClient().slewToAltAz(getDeviceID(), direction, altitude, getClientID(), getTransactionID()), "syncToAltAz", direction, altitude);
    }

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

    public void syncToCoordinates(double rightAscension, double declination) {
        AlpacaResponse response = call(getClient().syncToCoordinates(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID()), "syncToCoordinates", rightAscension, declination);
    }

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

    public void syncToTarget() {
        AlpacaResponse response = call(getClient().syncToTarget(getDeviceID(), getClientID(), getTransactionID()), "syncToTarget");
    }

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

    public void unpark() {
        AlpacaResponse response = call(getClient().unpark(getDeviceID(), getClientID(), getTransactionID()), "unpark");
    }

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
