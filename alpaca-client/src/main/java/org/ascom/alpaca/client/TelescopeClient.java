package org.ascom.alpaca.client;

import org.ascom.alpaca.api.Telescope;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class TelescopeClient extends CommonClient {
    private static final Logger log = LoggerFactory.getLogger(TelescopeClient.class);
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
                client = RestClientBuilder.newBuilder()
                        .baseUri(getServerAddress())
                        .build(Telescope.class);
                return client;
            } catch (Exception e) {
                log.warn("Problem constructing the client", e);
                throw new RuntimeException("Cannot build a client for Telescope - " + e.getMessage());
            }
        }
        return client;
    }

    public AlignmentMode getAlignmentMode() {
        IntResponse response = getClient().getAlignmentMode(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return AlignmentMode.fromMode(response.getValue());
    }

    public double getAltitude() {
        DoubleResponse response = getClient().getAltitude(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getApertureArea() {
        DoubleResponse response = getClient().getApertureArea(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getApertureDiameter() {
        DoubleResponse response = getClient().getApertureDiameter(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isAtHome() {
        BooleanResponse response = getClient().isAtHome(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean isAtPark() {
        BooleanResponse response = getClient().isAtPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getAzimuth() {
        DoubleResponse response = getClient().getAzimuth(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canFindHome() {
        BooleanResponse response = getClient().canFindHome(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canPark() {
        BooleanResponse response = getClient().canPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canPulseGuide() {
        BooleanResponse response = getClient().canPulseGuide(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetDeclinationRate() {
        BooleanResponse response = getClient().canSetDeclinationRate(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetGuideRates() {
        BooleanResponse response = getClient().canSetGuideRates(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetPark() {
        BooleanResponse response = getClient().canSetPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetPierSide() {
        BooleanResponse response = getClient().canSetPierSide(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetRightAscensionRate() {
        BooleanResponse response = getClient().canSetRightAscensionRate(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSetTracking() {
        BooleanResponse response = getClient().canSetTracking(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSlew() {
        BooleanResponse response = getClient().canSlew(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSlewAltAz() {
        BooleanResponse response = getClient().canSlewAltAz(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSlewAltAzAsync() {
        BooleanResponse response = getClient().canSlewAltAzAsync(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSlewAsync() {
        BooleanResponse response = getClient().canSlewAsync(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSync() {
        BooleanResponse response = getClient().canSync(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canSyncAltAz() {
        BooleanResponse response = getClient().canSyncAltAz(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canUnpark() {
        BooleanResponse response = getClient().canUnpark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getDeclination() {
        DoubleResponse response = getClient().getDeclination(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getDeclinationRate() {
        DoubleResponse response = getClient().getDeclinationRate(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setDeclinationRate(double declinationRate) {
        AlpacaResponse response = getClient().setDeclinationRate(getDeviceID(), declinationRate ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public boolean doesRefraction() {
        BooleanResponse response = getClient().doesRefraction(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setDoesRefraction(boolean doesRefraction) {
        AlpacaResponse response = getClient().setDoesRefraction(getDeviceID(), doesRefraction ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public EquatorialCoordinateType getEquatorialSystem() {
        IntResponse response = getClient().getEquatorialSystem(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return EquatorialCoordinateType.fromType(response.getValue());
    }

    public double getFocalLength() {
        DoubleResponse response = getClient().getFocalLength(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getGuideRateDeclination() {
        DoubleResponse response = getClient().getGuideRateDeclination(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setGuideRateDeclination(double guideRate) {
        AlpacaResponse response = getClient().setGuideRateDeclination(getDeviceID(), guideRate ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public double getGuideRateRightAscension() {
        DoubleResponse response = getClient().getGuideRateRightAscension(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setGuideRateRightAscension(double guideRate) {
        AlpacaResponse response = getClient().setGuideRateRightAscension(getDeviceID(), guideRate ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public boolean isPulseGuiding() {
        BooleanResponse response = getClient().isPulseGuiding(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getRightAscension() {
        DoubleResponse response = getClient().getRightAscension(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getRightAscensionRate() {
        DoubleResponse response = getClient().getRightAscensionRate(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setRightAscensionRate(double rightAscensionRate) {
        AlpacaResponse response = getClient().setRightAscensionRate(getDeviceID(), rightAscensionRate ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public PierSide getSideOfPier() {
        IntResponse response = getClient().getSideOfPier(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return PierSide.fromSide(response.getValue());
    }

    public void setSideOfPier(PierSide sideOfPier) {
        AlpacaResponse response = getClient().setSideOfPier(getDeviceID(), sideOfPier.ordinal() ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public double getSiderealTime() {
        DoubleResponse response = getClient().getSiderealTime(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public double getSiteElevation() {
        DoubleResponse response = getClient().getSiteElevation(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setSiteElevation(double siteElevation) {
        AlpacaResponse response = getClient().setSiteElevation(getDeviceID(), siteElevation ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public double getSiteLatitude() {
        DoubleResponse response = getClient().getSiteLatitude(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setSiteLatitude(double siteLatitude) {
        AlpacaResponse response = getClient().setSiteLatitude(getDeviceID(), siteLatitude ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public double getSiteLongitude() {
        DoubleResponse response = getClient().getSiteLongitude(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setSiteLongitude(double siteLongitude) {
        AlpacaResponse response = getClient().setSiteLatitude(getDeviceID(), siteLongitude ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public boolean isSlewing() {
        BooleanResponse response = getClient().isSlewing(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public int getSlewSettleTime() {
        IntResponse response = getClient().getSlewSettleTime(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setSlewSettleTime(int slewSettleTime) {
        AlpacaResponse response = getClient().setSlewSettleTime(getDeviceID(), slewSettleTime ,getClientID(), getTransactionID());
        checkResponse(response);
    }

    public double getTargetDeclination() {
        DoubleResponse response = getClient().getTargetDeclination(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setTargetDeclination(int targetDeclination) {
        AlpacaResponse response = getClient().setTargetDeclination(getDeviceID(), targetDeclination, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public double getTargetRightAscension() {
        DoubleResponse response = getClient().getTargetRightAscension(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setTargetRightAscension(int targetRightAscension) {
        AlpacaResponse response = getClient().setTargetRightAscension(getDeviceID(), targetRightAscension, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public boolean isTracking() {
        BooleanResponse response = getClient().isTracking(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public void setTracking(boolean tracking) {
        AlpacaResponse response = getClient().setTracking(getDeviceID(), tracking, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public DriveRate getTrackingRate() {
        IntResponse response = getClient().getTrackingRate(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return DriveRate.fromRate(response.getValue());
    }

    public void setTrackingRate(DriveRate trackingRate) {
        AlpacaResponse response = getClient().setTrackingRate(getDeviceID(), trackingRate.getRate(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public List<DriveRate> getTrackingRates() {
        ListResponse<DriveRate> response = getClient().getTrackingRates(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public Date getUTCDate() {
        StringResponse response = getClient().getUTCDate(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);

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
        AlpacaResponse response = getClient().setUTCDate(getDeviceID(), format.format(utcDate), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void abortSlew() {
        AlpacaResponse response = getClient().abortSlew(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public List<AxisRate> getAxisRates(TelescopeAxes axis) {
        ListResponse<AxisRate> response = getClient().getAxisRates(getDeviceID(), axis.getAxis(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public boolean canMoveAxis(TelescopeAxes axis) {
        BooleanResponse response = getClient().canMoveAxis(getDeviceID(), axis.getAxis(), getClientID(), getTransactionID());
        checkResponse(response);
        return response.getValue();
    }

    public PierSide getDestinationSideOfPier() {
        IntResponse response = getClient().getDestinationSideOfPier(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
        return PierSide.fromSide(response.getValue());
    }

    public void findHome() {
        AlpacaResponse response = getClient().findHome(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void moveAxis(int axis, double rate) {
        AlpacaResponse response = getClient().moveAxis(getDeviceID(), axis, rate, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void park() {
        AlpacaResponse response = getClient().park(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void pulseguide(int direction, int duration) {
        AlpacaResponse response = getClient().pulseguide(getDeviceID(), getClientID(), getTransactionID(), direction, duration);
        checkResponse(response);
    }

    public void setPark() {
        AlpacaResponse response = getClient().setPark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void slewToAltAz(double direction, double altitude) {
        AlpacaResponse response = getClient().slewToAltAz(getDeviceID(), direction, altitude, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void slewToAltAzAsync(double direction, double altitude) {
        AlpacaResponse response = getClient().slewToAltAzAsync(getDeviceID(), direction, altitude, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void slewToCoordinates(double rightAscension, double declination) {
        AlpacaResponse response = getClient().slewToCoordinates(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void slewToCoordinatesAsync(double rightAscension, double declination) {
        AlpacaResponse response = getClient().slewToCoordinatesAsync(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void slewToTarget() {
        AlpacaResponse response = getClient().slewToTarget(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void slewToTargetAsync() {
        AlpacaResponse response = getClient().slewToTargetAsync(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void syncToAltAz(double direction, double altitude) {
        AlpacaResponse response = getClient().slewToAltAz(getDeviceID(), direction, altitude, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void syncToCoordinates(double rightAscension, double declination) {
        AlpacaResponse response = getClient().syncToCoordinates(getDeviceID(), rightAscension, declination, getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void syncToTarget() {
        AlpacaResponse response = getClient().syncToTarget(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }

    public void unpark() {
        AlpacaResponse response = getClient().unpark(getDeviceID(), getClientID(), getTransactionID());
        checkResponse(response);
    }
}
