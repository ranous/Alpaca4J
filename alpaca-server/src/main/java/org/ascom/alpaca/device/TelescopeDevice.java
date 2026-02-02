package org.ascom.alpaca.device;

import org.ascom.alpaca.model.*;

import java.util.List;

/**
 * Interface for telescope devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * @see <a href="https://ascom-standards.org/api/#/Telescope%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings({"unused", "SpellCheckingInspection", "SameReturnValue", "EmptyMethod"})
public interface TelescopeDevice extends Device {
    /**
     * The version of the Alpaca Switch interface. Classes implementing this interface
     * should return this from the {@link #getInterfaceVersion}.
     */
    int interfaceVersion = 4;

    AlignmentMode getAlignmentMode();

    double getAltitude();

    double getApertureArea();

    double getApertureDiameter();

    boolean isAtHome();

    boolean isAtPark();

    double getAzimuth();

    boolean canFindHome();

    boolean canPark();

    boolean canPulseGuide();

    boolean canSetDeclinationRate();

    boolean canSetGuideRates();

    boolean canSetPark();

    boolean canSetPierSide();

    boolean canSetRightAscensionRate();

    boolean canSetTracking();

    boolean canSlew();

    boolean canSlewAltAz();

    boolean canSlewAltAzAsync();

    boolean canSlewAsync();

    boolean canSync();

    boolean canSyncAltAz();

    boolean canUnpark();

    double getDeclination();

    double getDeclinationRate();

    void setDeclinationRate(double declinationRate);

    boolean doesRefraction();

    void setDoesRefraction(boolean doesRefraction);

    EquatorialCoordinateType getEquatorialSystem();

    double getFocalLength();

    double getGuideRateDeclination();

    void setGuideRateDeclination(double guideRate);

    double getGuideRateRightAscension();

    void setGuideRateRightAscension(double guideRate);

    boolean isPulseGuiding();

    double getRightAscension();

    double getRightAscensionRate();

    void setRightAscensionRate(double rightAscensionRate);

    PierSide getSideOfPier();

    void setSideOfPier(PierSide sideOfPier);

    double getSiderealTime();

    double getSiteElevation();

    void setSiteElevation(double siteElevation);

    double getSiteLatitude();

    void setSiteLatitude(double siteLatitude);

    double getSiteLongitude();

    void setSiteLongitude(double siteLongitude);

    boolean isSlewing();

    int getSlewSettleTime();

    void setSlewSettleTime(int slewSettleTime);

    double getTargetDeclination();

    void setTargetDeclination(double targetDeclination);

    double getTargetRightAscension();

    void setTargetRightAscension(double targetRightAscension);

    boolean isTracking();

    void setTracking(boolean tracking);

    DriveRate getTrackingRate();

    void setTrackingRate(DriveRate trackingRate);

    List<DriveRate> getTrackingRates();

    String getUTCDate();

    void setUTCDate(String utcDate);

    void abortSlew();

    List<AxisRate> getAxisRates(int axis);

    boolean canMoveAxis(int axis);

    PierSide getDestinationSideOfPier(double rightAscension, double declination);

    void findHome();

    void moveAxis(int axis,double rate);

    void park();

    void pulseguide(int direction, int duration);

    void setPark();

    void slewToAltAz(double direction, double altitude);

    void slewToAltAzAsync(double direction, double altitude);

    void slewToCoordinates(double rightAscension, double declination);

    void slewToCoordinatesAsync(double rightAscension, double declination);

    void slewToTarget();

    void slewToTargetAsync();

    void syncToAltAz(double direction, double altitude);

    void syncToCoordinates(double rightAscension, double declination);

    void syncToTarget();

    void unpark();
}