package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.api.Telescope;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.device.TelescopeDevice;
import org.ascom.alpaca.model.AxisRate;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.DriveRate;
import org.ascom.alpaca.model.PierSide;
import org.ascom.alpaca.response.*;

@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
    @GET
    @Path("telescope/{deviceNumber}/alignmentmode")
    public IntResponse getAlignmentMode(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getAlignmentMode(clientID).getMode());
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/altitude")
    public DoubleResponse getAltitude(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAltitude(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/aperturearea")
    public DoubleResponse getApertureArea(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getApertureArea(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/aperturediameter")
    public DoubleResponse getApertureDiameter(@PathParam("deviceNumber") int deviceNumber,
                                              @QueryParam("ClientID") int clientID,
                                              @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getApertureDiameter(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/athome")
    public BooleanResponse isAtHome(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isAtHome(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/atpark")
    public BooleanResponse isAtPark(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isAtPark(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/azimuth")
    public DoubleResponse getAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAzimuth(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canfindhome")
    public BooleanResponse canFindHome(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canFindHome(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canpark")
    public BooleanResponse canPark(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPark(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canpulseguide")
    public BooleanResponse canPulseGuide(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPulseGuide(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/cansetdeclinationrate")
    public BooleanResponse canSetDeclinationRate(@PathParam("deviceNumber") int deviceNumber,
                                                 @QueryParam("ClientID") int clientID,
                                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetDeclinationRate(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/cansetguiderates")
    public BooleanResponse canSetGuideRates(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetGuideRates(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/cansetpark")
    public BooleanResponse canSetPark(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetPark(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/cansetpierside")
    public BooleanResponse canSetPierSide(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetPierSide(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/cansetrightascensionrate")
    public BooleanResponse canSetRightAscensionRate(@PathParam("deviceNumber") int deviceNumber,
                                                    @QueryParam("ClientID") int clientID,
                                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetRightAscensionRate(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/cansettracking")
    public BooleanResponse canSetTracking(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetTracking(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canslew")
    public BooleanResponse canSlew(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlew(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canslewaltaz")
    public BooleanResponse canSlewAltAz(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlewAltAz(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canslewaltazasync")
    public BooleanResponse canSlewAltAzAsync(@PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlewAltAzAsync(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canslewasync")
    public BooleanResponse canSlewAsync(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlewAsync(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/cansync")
    public BooleanResponse canSync(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSync(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/cansyncaltaz")
    public BooleanResponse canSyncAltAz(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSyncAltAz(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canunpark")
    public BooleanResponse canUnpark(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canUnpark(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/declination")
    public DoubleResponse getDeclination(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getDeclination(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/declinationrate")
    public DoubleResponse getDeclinationRate(@PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getDeclinationRate(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/declinationrate")
    public AlpacaResponse setDeclinationRate(@PathParam("deviceNumber") int deviceNumber,
                                             @FormParam("DeclinationRate") double declinationRate,
                                             @FormParam("ClientID") int clientID,
                                             @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setDeclinationRate(declinationRate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/doesrefraction")
    public BooleanResponse doesRefraction(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).doesRefraction(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/doesrefraction")
    public AlpacaResponse setDoesRefraction(@PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("DoesRefraction") boolean doesRefraction,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setDoesRefraction(doesRefraction, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("telescope/{deviceNumber}/equatorialsystem")
    public IntResponse getEquatorialSystem(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getEquatorialSystem(clientID).ordinal());
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/focallength")
    public DoubleResponse getFocalLength(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getFocalLength(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/guideratedeclination")
    public DoubleResponse getGuideRateDeclination(@PathParam("deviceNumber") int deviceNumber,
                                                  @QueryParam("ClientID") int clientID,
                                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getGuideRateDeclination(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/guideratedeclination")
    public AlpacaResponse setGuideRateDeclination(@PathParam("deviceNumber") int deviceNumber,
                                                  @FormParam("GuideRateDeclination") double guideRate,
                                                  @FormParam("ClientID") int clientID,
                                                  @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setGuideRateDeclination(guideRate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/guideraterightascension")
    public DoubleResponse getGuideRateRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                                     @QueryParam("ClientID") int clientID,
                                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getGuideRateRightAscension(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/guideraterightascension")
    public AlpacaResponse setGuideRateRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                                     @FormParam("GuideRateRightAscension") double guideRate,
                                                     @FormParam("ClientID") int clientID,
                                                     @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setGuideRateRightAscension(guideRate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/ispulseguiding")
    public BooleanResponse isPulseGuiding(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isPulseGuiding(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/rightascension")
    public DoubleResponse getRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getRightAscension(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/rightascensionrate")
    public DoubleResponse getRightAscensionRate(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getRightAscensionRate(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/rightascensionrate")
    public AlpacaResponse setRightAscensionRate(@PathParam("deviceNumber") int deviceNumber,
                                                @FormParam("RightAscensionRate") double rightAscensionRate,
                                                @FormParam("ClientID") int clientID,
                                                @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setRightAscensionRate(rightAscensionRate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/sideofpier")
    public IntResponse getSideOfPier(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getSideOfPier(clientID).getSide());
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/sideofpier")
    public AlpacaResponse setSideOfPier(@PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("SideOfPier") int sideOfPier,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setSideOfPier(PierSide.fromSide(sideOfPier), clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/siderealtime")
    public DoubleResponse getSiderealTime(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSiderealTime(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/siteelevation")
    public DoubleResponse getSiteElevation(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSiteElevation(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/siteelevation")
    public AlpacaResponse setSiteElevation(@PathParam("deviceNumber") int deviceNumber,
                                           @FormParam("SiteElevation") double siteElevation,
                                           @FormParam("ClientID") int clientID,
                                           @FormParam("ClientTransactionID") long clientTransactionID) {
        if (siteElevation < -300 || siteElevation > 10000) {
            throw new InvalidValueException("An elevation of " + siteElevation + " is out of bounds");
        }
        getDevice(deviceNumber, clientID).setSiteElevation(siteElevation, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/sitelatitude")
    public DoubleResponse getSiteLatitude(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSiteLatitude(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/sitelatitude")
    public AlpacaResponse setSiteLatitude(@PathParam("deviceNumber") int deviceNumber,
                                          @FormParam("SiteLatitude") double siteLatitude,
                                          @FormParam("ClientID") int clientID,
                                          @FormParam("ClientTransactionID") long clientTransactionID) {
        if (siteLatitude < -90 || siteLatitude > 90) {
            throw new InvalidValueException("A latitude of " + siteLatitude + " is invalid");
        }
        getDevice(deviceNumber, clientID).setSiteLatitude(siteLatitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/sitelongitude")
    public DoubleResponse getSiteLongitude(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSiteLongitude(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/sitelongitude")
    public AlpacaResponse setSiteLongitude(@PathParam("deviceNumber") int deviceNumber,
                                           @FormParam("SiteLongitude") double siteLongitude,
                                           @FormParam("ClientID") int clientID,
                                           @FormParam("ClientTransactionID") long clientTransactionID) {
        if (siteLongitude < -180 || siteLongitude > 180) {
            throw new InvalidValueException("A longitude of " + siteLongitude + " is invalid");
        }
        getDevice(deviceNumber, clientID).setSiteLongitude(siteLongitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/slewing")
    public BooleanResponse isSlewing(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isSlewing(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/slewsettletime")
    public IntResponse getSlewSettleTime(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getSlewSettleTime(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/slewsettletime")
    public AlpacaResponse setSlewSettleTime(@PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("SlewSettleTime") int slewSettleTime,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID) {
        if (slewSettleTime < 0) {
            throw new InvalidValueException("the slew settle time cannot be less that zero");
        }
        getDevice(deviceNumber, clientID).setSlewSettleTime(slewSettleTime, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/targetdeclination")
    public DoubleResponse getTargetDeclination(@PathParam("deviceNumber") int deviceNumber,
                                               @QueryParam("ClientID") int clientID,
                                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTargetDeclination(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/targetdeclination")
    public AlpacaResponse setTargetDeclination(@PathParam("deviceNumber") int deviceNumber,
                                               @FormParam("TargetDeclination") double targetDeclination,
                                               @FormParam("ClientID") int clientID,
                                               @FormParam("ClientTransactionID") long clientTransactionID) {
        if (targetDeclination < -90 || targetDeclination > 90) {
            throw new InvalidValueException("The declination value of " + targetDeclination + " is invalid");
        }
        getDevice(deviceNumber, clientID).setTargetDeclination(targetDeclination, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/targetrightascension")
    public DoubleResponse getTargetRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                                  @QueryParam("ClientID") int clientID,
                                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTargetRightAscension(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/targetrightascension")
    public AlpacaResponse setTargetRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                                  @FormParam("TargetRightAscension") double targetRightAscension,
                                                  @FormParam("ClientID") int clientID,
                                                  @FormParam("ClientTransactionID") long clientTransactionID) {
        if (targetRightAscension < 0 || targetRightAscension > 24) {
            throw new InvalidValueException("The right ascension value of " + targetRightAscension + " is invalid");
        }
        getDevice(deviceNumber, clientID).setTargetRightAscension(targetRightAscension, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/tracking")
    public BooleanResponse isTracking(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isTracking(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/tracking")
    public AlpacaResponse setTracking(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("Tracking") boolean tracking,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setTracking(tracking, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/trackingrate")
    public IntResponse getTrackingRate(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getTrackingRate(clientID).getRate());
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/trackingrate")
    public AlpacaResponse setTrackingRate(@PathParam("deviceNumber") int deviceNumber,
                                          @FormParam("TrackingRate") int trackingRate,
                                          @FormParam("ClientID") int clientID,
                                          @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setTrackingRate(DriveRate.fromRate(trackingRate), clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/trackingrates")
    public ListResponse<DriveRate> getTrackingRates(@PathParam("deviceNumber") int deviceNumber,
                                                    @QueryParam("ClientID") int clientID,
                                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getTrackingRates(clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/utcdate")
    public StringResponse getUTCDate(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getUTCDate(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/utcdate")
    public AlpacaResponse setUTCDate(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("UTCDate") String utcDate,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setUTCDate(utcDate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/abortslew")
    public AlpacaResponse abortSlew(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).abortSlew(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/axisrates")
    public ListResponse<AxisRate> getAxisRates(@PathParam("deviceNumber") int deviceNumber,
                                               @QueryParam("Axis") int axis,
                                               @QueryParam("ClientID") int clientID,
                                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getAxisRates(axis, clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/canmoveaxis")
    public BooleanResponse canMoveAxis(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("Axis") int axis,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canMoveAxis(axis, clientID));
    }

    @Override
    @GET
    @Path("telescope/{deviceNumber}/destinationsideofpier")
    public IntResponse getDestinationSideOfPier(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getDestinationSideOfPier(clientID));
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/findhome")
    public AlpacaResponse findHome(@PathParam("deviceNumber") int deviceNumber,
                                   @FormParam("ClientID") int clientID,
                                   @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).findHome(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/moveaxis")
    public AlpacaResponse moveAxis(@PathParam("deviceNumber") int deviceNumber,
                                   @FormParam("Axis") int axis,
                                   @FormParam("Rate") double rate,
                                   @FormParam("ClientID") int clientID,
                                   @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).moveAxis(axis, rate, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/park")
    public AlpacaResponse park(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).park(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/pulseguide")
    public AlpacaResponse pulseguide(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("Direction") int direction,
                                     @FormParam("Duration") int duration,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).pulseguide(direction, duration, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/setpark")
    public AlpacaResponse setPark(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setPark(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/slewtoaltaz")
    public AlpacaResponse slewToAltAz(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("Azimuth") double direction,
                                      @FormParam("Altitude") double altitude,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Cannot slew to AltAz while mount is parked");
        }

        getDevice(deviceNumber, clientID).slewToAltAz(direction, altitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/slewtoaltazasync")
    public AlpacaResponse slewToAltAzAsync(@PathParam("deviceNumber") int deviceNumber,
                                           @FormParam("Azimuth") double direction,
                                           @FormParam("Altitude") double altitude,
                                           @FormParam("ClientID") int clientID,
                                           @FormParam("ClientTransactionID") long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Cannot slew to AltAz while mount is parked");
        }
        getDevice(deviceNumber, clientID).slewToAltAzAsync(direction, altitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/slewtocoordinates")
    public AlpacaResponse slewToCoordinates(@PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("RightAscension") double rightAscension,
                                            @FormParam("Declination") double declination,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID) {
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
    @PUT
    @Path("telescope/{deviceNumber}/slewtocoordinatesasync")
    public AlpacaResponse slewToCoordinatesAsync(@PathParam("deviceNumber") int deviceNumber,
                                                 @FormParam("RightAscension") double rightAscension,
                                                 @FormParam("Declination") double declination,
                                                 @FormParam("ClientID") int clientID,
                                                 @FormParam("ClientTransactionID") long clientTransactionID) {
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
    @PUT
    @Path("telescope/{deviceNumber}/slewtotarget")
    public AlpacaResponse slewToTarget(@PathParam("deviceNumber") int deviceNumber,
                                       @FormParam("ClientID") int clientID,
                                       @FormParam("ClientTransactionID") long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Mount parked");
        }
        getDevice(deviceNumber, clientID).slewToTarget(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/slewtotargetasync")
    public AlpacaResponse slewToTargetAsync(@PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID) {
        if (getDevice(deviceNumber, clientID).isAtPark(clientID)) {
            throw new InvalidWhileParkedException("Mount parked");
        }
        getDevice(deviceNumber, clientID).slewToTargetAsync(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/synctoaltaz")
    public AlpacaResponse syncToAltAz(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("Azimuth") double direction,
                                      @FormParam("Altitude") double altitude,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).slewToAltAz(direction, altitude, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/synctocoordinates")
    public AlpacaResponse syncToCoordinates(@PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("RightAscension") double rightAscension,
                                            @FormParam("Declination") double declination,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID) {
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
    @PUT
    @Path("telescope/{deviceNumber}/synctotarget")
    public AlpacaResponse syncToTarget(@PathParam("deviceNumber") int deviceNumber,
                                       @FormParam("ClientID") int clientID,
                                       @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).syncToTarget(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    @PUT
    @Path("telescope/{deviceNumber}/unpark")
    public AlpacaResponse unpark(@PathParam("deviceNumber") int deviceNumber,
                                 @FormParam("ClientID") int clientID,
                                 @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).unpark(clientID);
        return new AlpacaResponse(clientTransactionID);
    }
}
