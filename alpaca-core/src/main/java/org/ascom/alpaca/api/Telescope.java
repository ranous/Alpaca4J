package org.ascom.alpaca.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.model.AxisRate;
import org.ascom.alpaca.model.DriveRate;
import org.ascom.alpaca.response.*;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface Telescope {

    @GET
    @Path("telescope/{deviceNumber}/alignmentmode")
    IntResponse getAlignmentMode(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/altitude")
    DoubleResponse getAltitude(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/aperturearea")
    DoubleResponse getApertureArea(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/aperturediameter")
    DoubleResponse getApertureDiameter(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/athome")
    BooleanResponse isAtHome(@PathParam("deviceNumber") int deviceNumber,
                             @QueryParam("ClientID") int clientID,
                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/atpark")
    BooleanResponse isAtPark(@PathParam("deviceNumber") int deviceNumber,
                             @QueryParam("ClientID") int clientID,
                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/azimuth")
    DoubleResponse getAzimuth(@PathParam("deviceNumber") int deviceNumber,
                              @QueryParam("ClientID") int clientID,
                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canfindhome")
    BooleanResponse canFindHome(@PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canpark")
    BooleanResponse canPark(@PathParam("deviceNumber") int deviceNumber,
                            @QueryParam("ClientID") int clientID,
                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canpulseguide")
    BooleanResponse canPulseGuide(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/cansetdeclinationrate")
    BooleanResponse canSetDeclinationRate(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/cansetguiderates")
    BooleanResponse canSetGuideRates(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/cansetpark")
    BooleanResponse canSetPark(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/cansetpierside")
    BooleanResponse canSetPierSide(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/cansetrightascensionrate")
    BooleanResponse canSetRightAscensionRate(@PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/cansettracking")
    BooleanResponse canSetTracking(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canslew")
    BooleanResponse canSlew(@PathParam("deviceNumber") int deviceNumber,
                            @QueryParam("ClientID") int clientID,
                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canslewaltaz")
    BooleanResponse canSlewAltAz(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canslewaltazasync")
    BooleanResponse canSlewAltAzAsync(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canslewasync")
    BooleanResponse canSlewAsync(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/cansync")
    BooleanResponse canSync(@PathParam("deviceNumber") int deviceNumber,
                            @QueryParam("ClientID") int clientID,
                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/cansyncaltaz")
    BooleanResponse canSyncAltAz(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canunpark")
    BooleanResponse canUnpark(@PathParam("deviceNumber") int deviceNumber,
                            @QueryParam("ClientID") int clientID,
                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/declination")
    DoubleResponse getDeclination(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/declinationrate")
    DoubleResponse getDeclinationRate(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/declinationrate")
    AlpacaResponse setDeclinationRate(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("DeclinationRate") double declinationRate,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/doesrefraction")
    BooleanResponse doesRefraction(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/doesrefraction")
    AlpacaResponse setDoesRefraction(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("DoesRefraction") boolean doesRefraction,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/equatorialsystem")
    IntResponse getEquatorialSystem(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/focallength")
    DoubleResponse getFocalLength(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/guideratedeclination")
    DoubleResponse getGuideRateDeclination(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/guideratedeclination")
    AlpacaResponse setGuideRateDeclination(@PathParam("deviceNumber") int deviceNumber,
                                           @FormParam("GuideRateDeclination") double guideRate,
                                           @FormParam("ClientID") int clientID,
                                           @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/guideraterightascension")
    DoubleResponse getGuideRateRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                              @QueryParam("ClientID") int clientID,
                                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/guideraterightascension")
    AlpacaResponse setGuideRateRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                              @FormParam("GuideRateRightAscension") double guideRate,
                                              @FormParam("ClientID") int clientID,
                                              @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/ispulseguiding")
    BooleanResponse isPulseGuiding(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/rightascension")
    DoubleResponse getRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/rightascensionrate")
    DoubleResponse getRightAscensionRate(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/rightascensionrate")
    AlpacaResponse setRightAscensionRate(@PathParam("deviceNumber") int deviceNumber,
                                         @FormParam("RightAscensionRate") double rightAscensionRate,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/sideofpier")
    IntResponse getSideOfPier(@PathParam("deviceNumber") int deviceNumber,
                              @QueryParam("ClientID") int clientID,
                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/sideofpier")
    AlpacaResponse setSideOfPier(@PathParam("deviceNumber") int deviceNumber,
                                 @FormParam("SideOfPier") int sideOfPier,
                                 @FormParam("ClientID") int clientID,
                                 @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/siderealtime")
    DoubleResponse getSiderealTime(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/siteelevation")
    DoubleResponse getSiteElevation(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/siteelevation")
    AlpacaResponse setSiteElevation(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("SiteElevation") double siteElevation,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/sitelatitude")
    DoubleResponse getSiteLatitude(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/sitelatitude")
    AlpacaResponse setSiteLatitude(@PathParam("deviceNumber") int deviceNumber,
                                   @FormParam("SiteLatitude") double siteLatitude,
                                   @FormParam("ClientID") int clientID,
                                   @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/sitelongitude")
    DoubleResponse getSiteLongitude(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/sitelongitude")
    AlpacaResponse setSiteLongitude(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("SiteLongitude") double siteLongitude,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/slewing")
    BooleanResponse isSlewing(@PathParam("deviceNumber") int deviceNumber,
                              @QueryParam("ClientID") int clientID,
                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/slewsettletime")
    IntResponse getSlewSettleTime(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/slewsettletime")
    AlpacaResponse setSlewSettleTime(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("SlewSettleTime") int slewSettleTime,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/targetdeclination")
    DoubleResponse getTargetDeclination(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/targetdeclination")
    AlpacaResponse setTargetDeclination(@PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("TargetDeclination") double targetDeclination,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/targetrightascension")
    DoubleResponse getTargetRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/targetrightascension")
    AlpacaResponse setTargetRightAscension(@PathParam("deviceNumber") int deviceNumber,
                                           @FormParam("TargetRightAscension") double targetRightAscension,
                                           @FormParam("ClientID") int clientID,
                                           @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/tracking")
    BooleanResponse isTracking(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/tracking")
    AlpacaResponse setTracking(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("Tracking") boolean tracking,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/trackingrate")
    IntResponse getTrackingRate(@PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/trackingrate")
    AlpacaResponse setTrackingRate(@PathParam("deviceNumber") int deviceNumber,
                                   @FormParam("TrackingRate") int trackingRate,
                                   @FormParam("ClientID") int clientID,
                                   @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/trackingrates")
    ListResponse<DriveRate> getTrackingRates(@PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/utcdate")
    StringResponse getUTCDate(@PathParam("deviceNumber") int deviceNumber,
                              @QueryParam("ClientID") int clientID,
                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/utcdate")
    AlpacaResponse setUTCDate(@PathParam("deviceNumber") int deviceNumber,
                              @FormParam("UTCDate") String utcDate,
                              @FormParam("ClientID") int clientID,
                              @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/abortslew")
    AlpacaResponse abortSlew(@PathParam("deviceNumber") int deviceNumber,
                             @FormParam("ClientID") int clientID,
                             @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/axisrates")
    ListResponse<AxisRate> getAxisRates(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("Axis") @DefaultValue("0") int axis,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/canmoveaxis")
    BooleanResponse canMoveAxis(@PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("Axis") @DefaultValue("0") int axis,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("telescope/{deviceNumber}/destinationsideofpier")
    IntResponse getDestinationSideOfPier(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/findhome")
    AlpacaResponse findHome(@PathParam("deviceNumber") int deviceNumber,
                            @FormParam("ClientID") int clientID,
                            @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/moveaxis")
    AlpacaResponse moveAxis(@PathParam("deviceNumber") int deviceNumber,
                            @FormParam("Axis") int axis,
                            @FormParam("Rate") double rate,
                            @FormParam("ClientID") int clientID,
                            @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/park")
    AlpacaResponse park(@PathParam("deviceNumber") int deviceNumber,
                        @FormParam("ClientID") int clientID,
                        @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/pulseguide")
    AlpacaResponse pulseguide(@PathParam("deviceNumber") int deviceNumber,
                              @FormParam("Direction") int direction,
                              @FormParam("Duration") int duration,
                              @FormParam("ClientID") int clientID,
                              @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/setpark")
    AlpacaResponse setPark(@PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/slewtoaltaz")
    AlpacaResponse slewToAltAz(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("Azimuth") double direction,
                               @FormParam("Altitude") double altitude,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/slewtoaltazasync")
    AlpacaResponse slewToAltAzAsync(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("Azimuth") double direction,
                                    @FormParam("Altitude") double altitude,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/slewtocoordinates")
    AlpacaResponse slewToCoordinates(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("RightAscension") double rightAscension,
                                     @FormParam("Declination") double declination,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/slewtocoordinatesasync")
    AlpacaResponse slewToCoordinatesAsync(@PathParam("deviceNumber") int deviceNumber,
                                          @FormParam("RightAscension") double rightAscension,
                                          @FormParam("Declination") double declination,
                                          @FormParam("ClientID") int clientID,
                                          @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/slewtotarget")
    AlpacaResponse slewToTarget(@PathParam("deviceNumber") int deviceNumber,
                                @FormParam("ClientID") int clientID,
                                @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/slewtotargetasync")
    AlpacaResponse slewToTargetAsync(@PathParam("deviceNumber") int deviceNumber,
                                @FormParam("ClientID") int clientID,
                                @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/synctoaltaz")
    AlpacaResponse syncToAltAz(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("Azimuth") double direction,
                               @FormParam("Altitude") double altitude,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/synctocoordinates")
    AlpacaResponse syncToCoordinates(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("RightAscension") double rightAscension,
                                     @FormParam("Declination") double declination,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/synctotarget")
    AlpacaResponse syncToTarget(@PathParam("deviceNumber") int deviceNumber,
                                @FormParam("ClientID") int clientID,
                                @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("telescope/{deviceNumber}/unpark")
    AlpacaResponse unpark(@PathParam("deviceNumber") int deviceNumber,
                          @FormParam("ClientID") int clientID,
                          @FormParam("ClientTransactionID") long clientTransactionID);
}