package org.ascom.alpaca.test;

import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.TelescopeDevice;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.InvalidOperationException;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.InvalidWhileParkedException;
import org.ascom.alpaca.response.PropertyNotImplementedException;

import jakarta.inject.Singleton;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
@Singleton
public class TestTelescopeDevice extends BaseDevice implements TelescopeDevice {
    private double lat = 37.48F;
    private double longitude = -122.24;
    private double siteElevation = 10.0F;
    private int slewSettleTime = 1;
    private final double dec = 0;
    private final double ra = 0;
    private Double targetDec = null;
    private Double targetRa = null;
    private final double alt = 10;
    private final double az = 0;
    private boolean atHome = true;
    private boolean atPark = true;
    private boolean isTracking = false;
    private boolean doesRefraction = false;
    private final List<AxisRate> axisRates = List.of(new AxisRate(1,5), new AxisRate(1,5));
    private PierSide pierSide = PierSide.West;
    private DriveRate currentDriveRate = DriveRate.Sidereal;
    private final List<DriveRate> supportedDriveRates = Arrays.asList(DriveRate.Sidereal, DriveRate.Lunar);

    public TestTelescopeDevice() {
        super(DeviceType.Telescope, "Test Telescope Driver", 4);
        setDescription("Test Telescope Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    @Override
    public AlignmentMode getAlignmentMode() {
        return AlignmentMode.GermanPolar;
    }

    @Override
    public double getAltitude() {
        return alt;
    }

    @Override
    public double getApertureArea() {
        return .013266F;
    }

    @Override
    public double getApertureDiameter() {
        return .130F;
    }

    @Override
    public boolean isAtHome() {
        return atHome;
    }

    @Override
    public boolean isAtPark() {
        return atPark;
    }

    @Override
    public double getAzimuth() {
        return az;
    }

    @Override
    public boolean canFindHome() {
        return true;
    }

    @Override
    public boolean canPark() {
        return true;
    }

    @Override
    public boolean canPulseGuide() {
        return true;
    }

    @Override
    public boolean canSetDeclinationRate() {
        return false;
    }

    @Override
    public boolean canSetGuideRates() {
        return false;
    }

    @Override
    public boolean canSetPark() {
        return false;
    }

    @Override
    public boolean canSetPierSide() {
        return true;
    }

    @Override
    public boolean canSetRightAscensionRate() {
        return false;
    }

    @Override
    public boolean canSetTracking() {
        return true;
    }

    @Override
    public boolean canSlew() {
        return true;
    }

    @Override
    public boolean canSlewAltAz() {
        return false;
    }

    @Override
    public boolean canSlewAltAzAsync() {
        return false;
    }

    @Override
    public boolean canSlewAsync() {
        return true;
    }

    @Override
    public boolean canSync() {
        return true;
    }

    @Override
    public boolean canSyncAltAz() {
        return false;
    }

    @Override
    public boolean canUnpark() {
        return true;
    }

    @Override
    public double getDeclination() {
        return dec;
    }

    @Override
    public double getDeclinationRate() {
        return 0.0F;
    }

    @Override
    public void setDeclinationRate(double declinationRate) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public boolean doesRefraction() {
        return doesRefraction;
    }

    @Override
    public void setDoesRefraction(boolean doesRefraction) {
        this.doesRefraction = doesRefraction;
    }

    @Override
    public EquatorialCoordinateType getEquatorialSystem() {
        return EquatorialCoordinateType.J2000;
    }

    @Override
    public double getFocalLength() {
        return .819F;
    }

    @Override
    public double getGuideRateDeclination() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setGuideRateDeclination(double guideRate) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public double getGuideRateRightAscension() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setGuideRateRightAscension(double guideRate) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public boolean isPulseGuiding() {
        return false;
    }

    @Override
    public double getRightAscension() {
        return ra;
    }

    @Override
    public double getRightAscensionRate() {
        return 0.0F;
    }

    @Override
    public void setRightAscensionRate(double rightAscensionRate) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public PierSide getSideOfPier() {
        return pierSide;
    }

    @Override
    public void setSideOfPier(PierSide sideOfPier) {
        pierSide = sideOfPier;
    }

    @Override
    public double getSiderealTime() {
        return meanSiderealTime(new Date(), longitude);
    }

    @Override
    public double getSiteElevation() {
        return siteElevation;
    }

    @Override
    public void setSiteElevation(double siteElevation) {
        this.siteElevation = siteElevation;
    }

    @Override
    public double getSiteLatitude() {
        return lat;
    }

    @Override
    public void setSiteLatitude(double siteLatitude) {
        lat = siteLatitude;
    }

    @Override
    public double getSiteLongitude() {
        return longitude;
    }

    @Override
    public void setSiteLongitude(double siteLongitude) {
        longitude = siteLongitude;
    }

    @Override
    public boolean isSlewing() {
        return false;
    }

    @Override
    public int getSlewSettleTime() {
        return slewSettleTime;
    }

    @Override
    public void setSlewSettleTime(int slewSettleTime) {
        this.slewSettleTime = slewSettleTime;
    }

    @Override
    public double getTargetDeclination() {
        if (targetDec == null) {
            throw new InvalidOperationException("Target Declination not set");
        }
        return targetDec;
    }

    @Override
    public void setTargetDeclination(double targetDeclination) {
        targetDec = targetDeclination;
    }

    @Override
    public double getTargetRightAscension() {
        if (targetRa == null) {
            throw new InvalidOperationException("Target Right Ascension not set");
        }
        return targetRa;
    }

    @Override
    public void setTargetRightAscension(double targetRightAscension) {
        this.targetRa = targetRightAscension;
    }

    @Override
    public boolean isTracking() {
        return isTracking;
    }

    @Override
    public void setTracking(boolean tracking) {
        if (atPark && tracking) {
            throw new InvalidWhileParkedException("Cannot set tracking while mount is parked");
        }
        isTracking = tracking;
    }

    @Override
    public DriveRate getTrackingRate() {
        return currentDriveRate;
    }

    @Override
    public void setTrackingRate(DriveRate trackingRate) {
        if (!supportedDriveRates.contains(trackingRate)) {
            throw new InvalidValueException("The tracking rate " + trackingRate + " is not supported");
        }
        currentDriveRate = trackingRate;
    }

    @Override
    public List<DriveRate> getTrackingRates() {
        return supportedDriveRates;
    }

    @Override
    public String getUTCDate() {
        return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    }

    @Override
    public void setUTCDate(String utcDate) {
        // Ignore this
    }

    @Override
    public void abortSlew() {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot abort slew when parked");
        }
    }

    @Override
    public List<AxisRate> getAxisRates(int axis) {
        if (axis < 0 || axis > 1) {
            throw new InvalidValueException("The axis " + axis + " is not supported");
        }
        return new ArrayList<>(List.of(axisRates.get(axis)));
    }

    @Override
    public boolean canMoveAxis(int axis) {
        return axis == 0 || axis == 1;
    }

    @Override
    public PierSide getDestinationSideOfPier(double rightAscension, double declinatino) {
        return PierSide.West;
    }

    @Override
    public void findHome() {
        if (atPark) {
            throw new InvalidWhileParkedException("Mount parked");
        }
        atHome = true;
    }

    @Override
    public void moveAxis(int axis, double rate) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot move mount axis while parked");
        }
        if (axis > 1) {
            throw new InvalidValueException("Axis " + axis + " is not supported by this device");
        }
        // rate of 0 stops the movement in that axis
        if (rate == 0) {
            return;
        }
        AxisRate axisRate = axisRates.get(axis);
        if (axisRate != null) {
            if (rate < axisRate.getMin()) {
                throw new InvalidValueException("Rate " + rate + " is less than the minimum supported rate of " + axisRate.getMin() + " for Axis " + axis);
            } else if (rate > axisRate.getMax()) {
                throw new InvalidValueException("Rate " + rate + " is more than the maximum supported rate of " + axisRate.getMax() + " for Axis " + axis);
            }
        }
    }

    @Override
    public void park() {
        atPark = true;
        atHome = true;
        isTracking = false;
    }

    @Override
    public void pulseguide(int direction, int duration) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot pulse guide while mount is parked");
        }
    }

    @Override
    public void setPark() {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void slewToAltAz(double direction, double altitude) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew AltAz while mount is parked");
        }
    }

    @Override
    public void slewToAltAzAsync(double direction, double altitude) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew AltAz while mount is parked");
        }
    }

    @Override
    public void slewToCoordinates(double rightAscension, double declination) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew to coordinates while mount is parked");
        }
    }

    @Override
    public void slewToCoordinatesAsync(double rightAscension, double declination) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew to coordinates while mount is parked");
        }
    }

    @Override
    public void slewToTarget() {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew to target while mount is parked");
        }
    }

    @Override
    public void slewToTargetAsync() {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew to target while mount is parked");
        }
    }

    @Override
    public void syncToAltAz(double direction, double altitude) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot sync to AltAz while mount is parked");
        }
    }

    @Override
    public void syncToCoordinates(double rightAscension, double declination) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot sync to coordinates while mount is parked");
        }
    }

    @Override
    public void syncToTarget() {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot sync to target while mount is parked");
        }
    }

    @Override
    public void unpark() {
        atPark = false;
        isTracking = true;
    }

    public static double meanSiderealTime(Date date, double longitude) {
        // First, calculate number of Julian days since J2000.0.
        double jd = calculateJulianDay(date);
        double delta = jd - 2451545.0f;

        // Calculate the global and local sidereal times
        double gst = 280.461f + 360.98564737f * delta;
        double lst = normalizeAngle(gst + longitude);

        return lst/360*24;
    }

    /**
     * Calculate the Julian Day for a given date using the following formula:
     * JD = 367 * Y - INT(7 * (Y + INT((M + 9)/12))/4) + INT(275 * M / 9)
     *      + D + 1721013.5 + UT/24
     * Note that this is only valid for the year range 1900 - 2099.
     * @param date the date to calculate the Julian Day for
     */
    public static double calculateJulianDay(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.setTime(date);

        double hour = cal.get(Calendar.HOUR_OF_DAY)
                + cal.get(Calendar.MINUTE) / 60.0f
                + cal.get(Calendar.SECOND) / 3600.0f;

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return 367.0 * year - Math.floor(7.0 * (year + Math.floor((month + 9.0) / 12.0)) / 4.0)
                        + Math.floor(275.0 * month / 9.0) + day + 1721013.5 + hour / 24.0;
    }

    /**
     * Normalize the angle to the range between 0 and 360.
     * @param angle the angle to normalize
     */
    public static double normalizeAngle(double angle) {
        double remainder = angle % 360;
        if (remainder < 0)
            remainder += 360;
        return remainder;
    }
}
