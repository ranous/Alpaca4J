package org.ascom.alpaca.device;

import org.ascom.alpaca.model.*;

import java.util.List;

/**
 * Interface for telescope devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * <a href="https://ascom-standards.org/api/#/Telescope%20Specific%20Methods"/>.
 */
@SuppressWarnings({"unused", "SpellCheckingInspection", "SameReturnValue", "EmptyMethod"})
public interface TelescopeDevice extends Device {

    AlignmentMode getAlignmentMode(int clientID);

    double getAltitude(int clientID);

    double getApertureArea(int clientID);

    double getApertureDiameter(int clientID);

    boolean isAtHome(int clientID);

    boolean isAtPark(int clientID);

    double getAzimuth(int clientID);

    boolean canFindHome(int clientID);

    boolean canPark(int clientID);

    boolean canPulseGuide(int clientID);

    boolean canSetDeclinationRate(int clientID);

    boolean canSetGuideRates(int clientID);

    boolean canSetPark(int clientID);

    boolean canSetPierSide(int clientID);

    boolean canSetRightAscensionRate(int clientID);

    boolean canSetTracking(int clientID);

    boolean canSlew(int clientID);

    boolean canSlewAltAz(int clientID);

    boolean canSlewAltAzAsync(int clientID);

    boolean canSlewAsync(int clientID);

    boolean canSync(int clientID);

    boolean canSyncAltAz(int clientID);

    boolean canUnpark(int clientID);

    double getDeclination(int clientID);

    double getDeclinationRate(int clientID);

    void setDeclinationRate(double declinationRate, int clientID);

    boolean doesRefraction(int clientID);

    void setDoesRefraction(boolean doesRefraction, int clientID);

    EquatorialCoordinateType getEquatorialSystem(int clientID);

    double getFocalLength(int clientID);

    double getGuideRateDeclination(int clientID);

    void setGuideRateDeclination(double guideRate, int clientID);

    double getGuideRateRightAscension(int clientID);

    void setGuideRateRightAscension(double guideRate, int clientID);

    boolean isPulseGuiding(int clientID);

    double getRightAscension(int clientID);

    double getRightAscensionRate(int clientID);

    void setRightAscensionRate(double rightAscensionRate, int clientID);

    PierSide getSideOfPier(int clientID);

    void setSideOfPier(PierSide sideOfPier, int clientID);

    double getSiderealTime(int clientID);

    double getSiteElevation(int clientID);

    void setSiteElevation(double siteElevation, int clientID);

    double getSiteLatitude(int clientID);

    void setSiteLatitude(double siteLatitude, int clientID);

    double getSiteLongitude(int clientID);

    void setSiteLongitude(double siteLongitude, int clientID);

    boolean isSlewing(int clientID);

    int getSlewSettleTime(int clientID);

    void setSlewSettleTime(int slewSettleTime, int clientID);

    double getTargetDeclination(int clientID);

    void setTargetDeclination(double targetDeclination, int clientID);

    double getTargetRightAscension(int clientID);

    void setTargetRightAscension(double targetRightAscension, int clientID);

    boolean isTracking(int clientID);

    void setTracking(boolean tracking, int clientID);

    DriveRate getTrackingRate(int clientID);

    void setTrackingRate(DriveRate trackingRate, int clientID);

    List<DriveRate> getTrackingRates(int clientID);

    String getUTCDate(int clientID);

    void setUTCDate(String utcDate, int clientID);

    void abortSlew(int clientID);

    List<AxisRate> getAxisRates(int axis, int clientID);

    boolean canMoveAxis(int axis, int clientID);

    int getDestinationSideOfPier(int clientID);

    void findHome(int clientID);

    void moveAxis(int axis,double rate, int clientID);

    void park(int clientID);

    void pulseguide(int direction, int duration, int clientID);

    void setPark(int clientID);

    void slewToAltAz(double direction, double altitude, int clientID);

    void slewToAltAzAsync(double direction, double altitude, int clientID);

    void slewToCoordinates(double rightAscension, double declination, int clientID);

    void slewToCoordinatesAsync(double rightAscension, double declination, int clientID);

    void slewToTarget(int clientID);

    void slewToTargetAsync(int clientID);

    void syncToAltAz(double direction, double altitude, int clientID);

    void syncToCoordinates(double rightAscension, double declination, int clientID);

    void syncToTarget(int clientID);

    void unpark(int clientID);
}