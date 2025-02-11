package org.ascom.alpaca.webservices;


import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.Telescope;
import org.ascom.alpaca.device.TelescopeDevice;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TelescopeResource implements Telescope {
    @Inject
    DeviceManager deviceManager;

    private TelescopeDevice getDevice(int deviceID, int clientID) {
        TelescopeDevice device = deviceManager.getDevice(deviceID, DeviceType.Telescope);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @Override
    public IntResponse getAlignmentMode(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getAlignmentMode(clientID).getMode());
    }

    @Override
    public DoubleResponse getAltitude(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAltitude(clientID));
    }

    @Override
    public DoubleResponse getApertureArea(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getApertureArea(clientID));
    }

    @Override
    public DoubleResponse getApertureDiameter(int deviceNumber,
                                              int clientID,
                                              long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getApertureDiameter(clientID));
    }

    @Override
    public BooleanResponse isAtHome(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isAtHome(clientID));
    }

    @Override
    public BooleanResponse isAtPark(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isAtPark(clientID));
    }

    @Override
    public DoubleResponse getAzimuth(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAzimuth(clientID));
    }

    @Override
    public BooleanResponse canFindHome(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canFindHome(clientID));
    }

    @Override
    public BooleanResponse canPark(int deviceNumber,
                                   int clientID,
                                   long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPark(clientID));
    }

    @Override
    public BooleanResponse canPulseGuide(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPulseGuide(clientID));
    }

    @Override
    public BooleanResponse canSetDeclinationRate(int deviceNumber,
                                                 int clientID,
                                                 long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetDeclinationRate(clientID));
    }

    @Override
    public BooleanResponse canSetGuideRates(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetGuideRates(clientID));
    }

    @Override
    public BooleanResponse canSetPark(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetPark(clientID));
    }

    @Override
    public BooleanResponse canSetPierSide(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetPierSide(clientID));
    }

    @Override
    public BooleanResponse canSetRightAscensionRate(int deviceNumber,
                                                    int clientID,
                                                    long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetRightAscensionRate(clientID));
    }

    @Override
    public BooleanResponse canSetTracking(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetTracking(clientID));
    }

    @Override
    public BooleanResponse canSlew(int deviceNumber,
                                   int clientID,
                                   long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlew(clientID));
    }

    @Override
    public BooleanResponse canSlewAltAz(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlewAltAz(clientID));
    }

    @Override
    public BooleanResponse canSlewAltAzAsync(int deviceNumber,
                                             int clientID,
                                             long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlewAltAzAsync(clientID));
    }

    @Override
    public BooleanResponse canSlewAsync(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlewAsync(clientID));
    }

    @Override
    public BooleanResponse canSync(int deviceNumber,
                                   int clientID,
                                   long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSync(clientID));
    }

    @Override
    public BooleanResponse canSyncAltAz(int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSyncAltAz(clientID));
    }

    @Override
    public BooleanResponse canUnpark(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canUnpark(clientID));
    }

    @Override
    public DoubleResponse getDeclination(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getDeclination(clientID));
    }

    @Override
    public DoubleResponse getDeclinationRate(int deviceNumber,
                                             int clientID,
                                             long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getDeclinationRate(clientID));
    }

    @Override
    public AlpacaResponse setDeclinationRate(int deviceNumber,
                                             double declinationRate,
                                             int clientID,
                                             long clientTransactionID) {
        getDevice(deviceNumber, clientID).setDeclinationRate(declinationRate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public BooleanResponse doesRefraction(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).doesRefraction(clientID));
    }

    @Override
    public AlpacaResponse setDoesRefraction(int deviceNumber,
                                            boolean doesRefraction,
                                            int clientID,
                                            long clientTransactionID) {
        getDevice(deviceNumber, clientID).setDoesRefraction(doesRefraction, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public IntResponse getEquatorialSystem(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getEquatorialSystem(clientID).ordinal());
    }

    @Override
    public DoubleResponse getFocalLength(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getFocalLength(clientID));
    }

    @Override
    public DoubleResponse getGuideRateDeclination(int deviceNumber,
                                                  int clientID,
                                                  long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getGuideRateDeclination(clientID));
    }

    @Override
    public AlpacaResponse setGuideRateDeclination(int deviceNumber,
                                                  double guideRate,
                                                  int clientID,
                                                  long clientTransactionID) {
        getDevice(deviceNumber, clientID).setGuideRateDeclination(guideRate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public DoubleResponse getGuideRateRightAscension(int deviceNumber,
                                                     int clientID,
                                                     long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getGuideRateRightAscension(clientID));
    }

    @Override
    public AlpacaResponse setGuideRateRightAscension(int deviceNumber,
                                                     double guideRate,
                                                     int clientID,
                                                     long clientTransactionID) {
        getDevice(deviceNumber, clientID).setGuideRateRightAscension(guideRate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public BooleanResponse isPulseGuiding(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isPulseGuiding(clientID));
    }

    @Override
    public DoubleResponse getRightAscension(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getRightAscension(clientID));
    }

    @Override
    public DoubleResponse getRightAscensionRate(int deviceNumber,
                                                int clientID,
                                                long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getRightAscensionRate(clientID));
    }

    @Override
    public AlpacaResponse setRightAscensionRate(int deviceNumber,
                                                double rightAscensionRate,
                                                int clientID,
                                                long clientTransactionID) {
        getDevice(deviceNumber, clientID).setRightAscensionRate(rightAscensionRate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getSideOfPier(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getSideOfPier(clientID).getSide());
    }

    @Override
    public AlpacaResponse setSideOfPier(int deviceNumber,
                                        int sideOfPier,
                                        int clientID,
                                        long clientTransactionID) {
        getDevice(deviceNumber, clientID).setSideOfPier(PierSide.fromSide(sideOfPier), clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public DoubleResponse getSiderealTime(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSiderealTime(clientID));
    }

    @Override
    public DoubleResponse getSiteElevation(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSiteElevation(clientID));
    }

    @Override
    public AlpacaResponse setSiteElevation(int deviceNumber,
                                           double siteElevation,
                                           int clientID,
                                           long clientTransactionID) {
        if (siteElevation < -300 || siteElevation > 10000) {
            throw new InvalidValueException("An elevation of " + siteElevation + " is out of bounds");
        }
        getDevice(deviceNumber, clientID).setSiteElevation(siteElevation, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public DoubleResponse getSiteLatitude(int deviceNumber,
                                          int clientID,
                                          long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSiteLatitude(clientID));
    }

    @Override
    public AlpacaResponse setSiteLatitude(int deviceNumber,
                                          double siteLatitude,
                                          int clientID,
                                          long clientTransactionID) {
        if (siteLatitude < -90 || siteLatitude > 90) {
            throw new InvalidValueException("A latitude of " + siteLatitude + " is invalid");
        }
        getDevice(deviceNumber, clientID).setSiteLatitude(siteLatitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public DoubleResponse getSiteLongitude(int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSiteLongitude(clientID));
    }

    @Override
    public AlpacaResponse setSiteLongitude(int deviceNumber,
                                           double siteLongitude,
                                           int clientID,
                                           long clientTransactionID) {
        if (siteLongitude < -180 || siteLongitude > 180) {
            throw new InvalidValueException("A longitude of " + siteLongitude + " is invalid");
        }
        getDevice(deviceNumber, clientID).setSiteLongitude(siteLongitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public BooleanResponse isSlewing(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isSlewing(clientID));
    }

    @Override
    public IntResponse getSlewSettleTime(int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getSlewSettleTime(clientID));
    }

    @Override
    public AlpacaResponse setSlewSettleTime(int deviceNumber,
                                            int slewSettleTime,
                                            int clientID,
                                            long clientTransactionID) {
        if (slewSettleTime < 0) {
            throw new InvalidValueException("the slew settle time cannot be less that zero");
        }
        getDevice(deviceNumber, clientID).setSlewSettleTime(slewSettleTime, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public DoubleResponse getTargetDeclination(int deviceNumber,
                                               int clientID,
                                               long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTargetDeclination(clientID));
    }

    @Override
    public AlpacaResponse setTargetDeclination(int deviceNumber,
                                               double targetDeclination,
                                               int clientID,
                                               long clientTransactionID) {
        if (targetDeclination < -90 || targetDeclination > 90) {
            throw new InvalidValueException("The declination value of " + targetDeclination + " is invalid");
        }
        getDevice(deviceNumber, clientID).setTargetDeclination(targetDeclination, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public DoubleResponse getTargetRightAscension(int deviceNumber,
                                                  int clientID,
                                                  long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTargetRightAscension(clientID));
    }

    @Override
    public AlpacaResponse setTargetRightAscension(int deviceNumber,
                                                  double targetRightAscension,
                                                  int clientID,
                                                  long clientTransactionID) {
        if (targetRightAscension < 0 || targetRightAscension > 24) {
            throw new InvalidValueException("The right ascension value of " + targetRightAscension + " is invalid");
        }
        getDevice(deviceNumber, clientID).setTargetRightAscension(targetRightAscension, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public BooleanResponse isTracking(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isTracking(clientID));
    }

    @Override
    public AlpacaResponse setTracking(int deviceNumber,
                                      boolean tracking,
                                      int clientID,
                                      long clientTransactionID) {
        getDevice(deviceNumber, clientID).setTracking(tracking, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public IntResponse getTrackingRate(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getTrackingRate(clientID).getRate());
    }

    @Override
    public AlpacaResponse setTrackingRate(int deviceNumber,
                                          int trackingRate,
                                          int clientID,
                                          long clientTransactionID) {
        getDevice(deviceNumber, clientID).setTrackingRate(DriveRate.fromRate(trackingRate), clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public ListResponse<DriveRate> getTrackingRates(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getTrackingRates(clientID));
    }

    @Override
    public StringResponse getUTCDate(int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getUTCDate(clientID));
    }

    @Override
    public AlpacaResponse setUTCDate(int deviceNumber,
                                     String utcDate,
                                     int clientID,
                                     long clientTransactionID) {
        getDevice(deviceNumber, clientID).setUTCDate(utcDate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse abortSlew(int deviceNumber,
                                    int clientID,
                                    long clientTransactionID) {
        getDevice(deviceNumber, clientID).abortSlew(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public ListResponse<AxisRate> getAxisRates(int deviceNumber,
                                          int axis,
                                          int clientID,
                                          long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getAxisRates(axis, clientID));
    }

    @Override
    public BooleanResponse canMoveAxis(int deviceNumber,
                                       int axis,
                                       int clientID,
                                       long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canMoveAxis(axis, clientID));
    }

    @Override
    public IntResponse getDestinationSideOfPier(int deviceNumber,
                                                int clientID,
                                                long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getDestinationSideOfPier(clientID));
    }

    @Override
    public AlpacaResponse findHome(int deviceNumber,
                                   int clientID,
                                   long clientTransactionID) {
        getDevice(deviceNumber, clientID).findHome(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse moveAxis(int deviceNumber,
                                   int axis,
                                   double rate,
                                   int clientID,
                                   long clientTransactionID) {
        getDevice(deviceNumber, clientID).moveAxis(axis, rate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse park(int deviceNumber,
                               int clientID,
                               long clientTransactionID) {
        getDevice(deviceNumber, clientID).park(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse pulseguide(int deviceNumber,
                                     int direction,
                                     int duration,
                                     int clientID,
                                     long clientTransactionID) {
        getDevice(deviceNumber, clientID).pulseguide(direction, duration, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse setPark(int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        getDevice(deviceNumber, clientID).setPark(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse slewToAltAz(int deviceNumber,
                                      double direction,
                                      double altitude,
                                      int clientID,
                                      long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Cannot slew to AltAz while mount is parked");
        }

        getDevice(deviceNumber, clientID).slewToAltAz(direction, altitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse slewToAltAzAsync(int deviceNumber,
                                           double direction,
                                           double altitude,
                                           int clientID,
                                           long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Cannot slew to AltAz while mount is parked");
        }
        getDevice(deviceNumber, clientID).slewToAltAzAsync(direction, altitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse slewToCoordinates(int deviceNumber,
                                            double rightAscension,
                                            double declination,
                                            int clientID,
                                            long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Cannot slew to coordinates while mount is parked");
        }
        if (declination < -90 || declination > 90) {
            throw new InvalidValueException("The declination value of " + declination + " is invalid");
        }
        if (rightAscension < 0 || rightAscension > 24) {
            throw new InvalidValueException("The right ascension value of " + rightAscension + " is invalid");
        }
        getDevice(deviceNumber, clientID).slewToCoordinates(rightAscension, declination, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse slewToCoordinatesAsync(int deviceNumber,
                                                 double rightAscension,
                                                 double declination,
                                                 int clientID,
                                                 long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Mount parked");
        }
        if (declination < -90 || declination > 90) {
            throw new InvalidValueException("The declination value of " + declination + " is invalid");
        }
        if (rightAscension < 0 || rightAscension > 24) {
            throw new InvalidValueException("The right ascension value of " + rightAscension + " is invalid");
        }
        getDevice(deviceNumber, clientID).slewToCoordinatesAsync(rightAscension, declination, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse slewToTarget(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Mount parked");
        }
        getDevice(deviceNumber, clientID).slewToTarget(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse slewToTargetAsync(int deviceNumber,
                                            int clientID,
                                            long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Mount parked");
        }
        getDevice(deviceNumber, clientID).slewToTargetAsync(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse syncToAltAz(int deviceNumber,
                                      double direction,
                                      double altitude,
                                      int clientID,
                                      long clientTransactionID) {
        getDevice(deviceNumber, clientID).slewToAltAz(direction, altitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse syncToCoordinates(int deviceNumber,
                                            double rightAscension,
                                            double declination,
                                            int clientID,
                                            long clientTransactionID) {
        if (declination < -90 || declination > 90) {
            throw new InvalidValueException("The declination value of " + declination + " is invalid");
        }
        if (rightAscension < 0 || rightAscension > 24) {
            throw new InvalidValueException("The right ascension value of " + rightAscension + " is invalid");
        }
        getDevice(deviceNumber, clientID).syncToCoordinates(rightAscension, declination, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse syncToTarget(int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {
        getDevice(deviceNumber, clientID).syncToTarget(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse unpark(int deviceNumber,
                                 int clientID,
                                 long clientTransactionID) {
        getDevice(deviceNumber, clientID).unpark(clientID);
        return new AlpacaResponse(clientTransactionID);
    }
}
