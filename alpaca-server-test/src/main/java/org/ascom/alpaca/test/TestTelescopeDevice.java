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
    public AlignmentMode getAlignmentMode(int clientID) {
        return AlignmentMode.GermanPolar;
    }

    @Override
    public double getAltitude(int clientID) {
        return alt;
    }

    @Override
    public double getApertureArea(int clientID) {
        return .013266F;
    }

    @Override
    public double getApertureDiameter(int clientID) {
        return .130F;
    }

    @Override
    public boolean isAtHome(int clientID) {
        return atHome;
    }

    @Override
    public boolean isAtPark(int clientID) {
        return atPark;
    }

    @Override
    public double getAzimuth(int clientID) {
        return az;
    }

    @Override
    public boolean canFindHome(int clientID) {
        return true;
    }

    @Override
    public boolean canPark(int clientID) {
        return true;
    }

    @Override
    public boolean canPulseGuide(int clientID) {
        return true;
    }

    @Override
    public boolean canSetDeclinationRate(int clientID) {
        return false;
    }

    @Override
    public boolean canSetGuideRates(int clientID) {
        return false;
    }

    @Override
    public boolean canSetPark(int clientID) {
        return false;
    }

    @Override
    public boolean canSetPierSide(int clientID) {
        return true;
    }

    @Override
    public boolean canSetRightAscensionRate(int clientID) {
        return false;
    }

    @Override
    public boolean canSetTracking(int clientID) {
        return true;
    }

    @Override
    public boolean canSlew(int clientID) {
        return true;
    }

    @Override
    public boolean canSlewAltAz(int clientID) {
        return false;
    }

    @Override
    public boolean canSlewAltAzAsync(int clientID) {
        return false;
    }

    @Override
    public boolean canSlewAsync(int clientID) {
        return true;
    }

    @Override
    public boolean canSync(int clientID) {
        return true;
    }

    @Override
    public boolean canSyncAltAz(int clientID) {
        return false;
    }

    @Override
    public boolean canUnpark(int clientID) {
        return true;
    }

    @Override
    public double getDeclination(int clientID) {
        return dec;
    }

    @Override
    public double getDeclinationRate(int clientID) {
        return 0.0F;
    }

    @Override
    public void setDeclinationRate(double declinationRate, int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public boolean doesRefraction(int clientID) {
        return doesRefraction;
    }

    @Override
    public void setDoesRefraction(boolean doesRefraction, int clientID) {
        this.doesRefraction = doesRefraction;
    }

    @Override
    public EquatorialCoordinateType getEquatorialSystem(int clientID) {
        return EquatorialCoordinateType.J2000;
    }

    @Override
    public double getFocalLength(int clientID) {
        return .819F;
    }

    @Override
    public double getGuideRateDeclination(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setGuideRateDeclination(double guideRate, int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public double getGuideRateRightAscension(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void setGuideRateRightAscension(double guideRate, int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public boolean isPulseGuiding(int clientID) {
        return false;
    }

    @Override
    public double getRightAscension(int clientID) {
        return ra;
    }

    @Override
    public double getRightAscensionRate(int clientID) {
        return 0.0F;
    }

    @Override
    public void setRightAscensionRate(double rightAscensionRate, int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public PierSide getSideOfPier(int clientID) {
        return pierSide;
    }

    @Override
    public void setSideOfPier(PierSide sideOfPier, int clientID) {
        pierSide = sideOfPier;
    }

    @Override
    public double getSiderealTime(int clientID) {
        return meanSiderealTime(new Date(), longitude);
    }

    @Override
    public double getSiteElevation(int clientID) {
        return siteElevation;
    }

    @Override
    public void setSiteElevation(double siteElevation, int clientID) {
        this.siteElevation = siteElevation;
    }

    @Override
    public double getSiteLatitude(int clientID) {
        return lat;
    }

    @Override
    public void setSiteLatitude(double siteLatitude, int clientID) {
        lat = siteLatitude;
    }

    @Override
    public double getSiteLongitude(int clientID) {
        return longitude;
    }

    @Override
    public void setSiteLongitude(double siteLongitude, int clientID) {
        longitude = siteLongitude;
    }

    @Override
    public boolean isSlewing(int clientID) {
        return false;
    }

    @Override
    public int getSlewSettleTime(int clientID) {
        return slewSettleTime;
    }

    @Override
    public void setSlewSettleTime(int slewSettleTime, int clientID) {
        this.slewSettleTime = slewSettleTime;
    }

    @Override
    public double getTargetDeclination(int clientID) {
        if (targetDec == null) {
            throw new InvalidOperationException("Target Declination not set");
        }
        return targetDec;
    }

    @Override
    public void setTargetDeclination(double targetDeclination, int clientID) {
        targetDec = targetDeclination;
    }

    @Override
    public double getTargetRightAscension(int clientID) {
        if (targetRa == null) {
            throw new InvalidOperationException("Target Right Ascension not set");
        }
        return targetRa;
    }

    @Override
    public void setTargetRightAscension(double targetRightAscension, int clientID) {
        this.targetRa = targetRightAscension;
    }

    @Override
    public boolean isTracking(int clientID) {
        return isTracking;
    }

    @Override
    public void setTracking(boolean tracking, int clientID) {
        if (atPark && tracking) {
            throw new InvalidWhileParkedException("Cannot set tracking while mount is parked");
        }
        isTracking = tracking;
    }

    @Override
    public DriveRate getTrackingRate(int clientID) {
        return currentDriveRate;
    }

    @Override
    public void setTrackingRate(DriveRate trackingRate, int clientID) {
        if (!supportedDriveRates.contains(trackingRate)) {
            throw new InvalidValueException("The tracking rate " + trackingRate + " is not supported");
        }
        currentDriveRate = trackingRate;
    }

    @Override
    public List<DriveRate> getTrackingRates(int clientID) {
        return supportedDriveRates;
    }

    @Override
    public String getUTCDate(int clientID) {
        return ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    }

    @Override
    public void setUTCDate(String utcDate, int clientID) {
        // Ignore this
    }

    @Override
    public void abortSlew(int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot abort slew when parked");
        }
    }

    @Override
    public List<AxisRate> getAxisRates(int axis, int clientID) {
        if (axis < 0 || axis > 1) {
            throw new InvalidValueException("The axis " + axis + " is not supported");
        }
        return new ArrayList<>(List.of(axisRates.get(axis)));
    }

    @Override
    public boolean canMoveAxis(int axis, int clientID) {
        return axis == 0 || axis == 1;
    }

    @Override
    public int getDestinationSideOfPier(int clientID) {
        return 0;
    }

    @Override
    public void findHome(int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Mount parked");
        }
        atHome = true;
    }

    @Override
    public void moveAxis(int axis, double rate, int clientID) {
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
    public void park(int clientID) {
        atPark = true;
        atHome = true;
        isTracking = false;
    }

    @Override
    public void pulseguide(int direction, int duration, int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot pulse guide while mount is parked");
        }
    }

    @Override
    public void setPark(int clientID) {
        throw new PropertyNotImplementedException("Not implemented");
    }

    @Override
    public void slewToAltAz(double direction, double altitude, int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew AltAz while mount is parked");
        }
    }

    @Override
    public void slewToAltAzAsync(double direction, double altitude, int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew AltAz while mount is parked");
        }
    }

    @Override
    public void slewToCoordinates(double rightAscension, double declination, int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew to coordinates while mount is parked");
        }
    }

    @Override
    public void slewToCoordinatesAsync(double rightAscension, double declination, int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew to coordinates while mount is parked");
        }
    }

    @Override
    public void slewToTarget(int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew to target while mount is parked");
        }
    }

    @Override
    public void slewToTargetAsync(int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot slew to target while mount is parked");
        }
    }

    @Override
    public void syncToAltAz(double direction, double altitude, int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot sync to AltAz while mount is parked");
        }
    }

    @Override
    public void syncToCoordinates(double rightAscension, double declination, int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot sync to coordinates while mount is parked");
        }
    }

    @Override
    public void syncToTarget(int clientID) {
        if (atPark) {
            throw new InvalidWhileParkedException("Cannot sync to target while mount is parked");
        }
    }

    @Override
    public void unpark(int clientID) {
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
