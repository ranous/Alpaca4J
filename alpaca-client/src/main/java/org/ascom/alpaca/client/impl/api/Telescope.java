package org.ascom.alpaca.client.impl.api;

import org.ascom.alpaca.model.AxisRate;
import org.ascom.alpaca.model.DriveRate;
import org.ascom.alpaca.response.*;
import retrofit2.Call;
import retrofit2.http.*;

public interface Telescope {

    @GET("api/v1/telescope/{deviceNumber}/alignmentmode")
    Call<IntResponse> getAlignmentMode(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/altitude")
    Call<DoubleResponse> getAltitude(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/aperturearea")
    Call<DoubleResponse> getApertureArea(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/aperturediameter")
    Call<DoubleResponse> getApertureDiameter(@Path("deviceNumber") int deviceNumber,
                                             @Query("ClientID") int clientID,
                                             @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/athome")
    Call<BooleanResponse> isAtHome(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/atpark")
    Call<BooleanResponse> isAtPark(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/azimuth")
    Call<DoubleResponse> getAzimuth(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/canfindhome")
    Call<BooleanResponse> canFindHome(@Path("deviceNumber") int deviceNumber,
                                      @Query("ClientID") int clientID,
                                      @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/canpark")
    Call<BooleanResponse> canPark(@Path("deviceNumber") int deviceNumber,
                                  @Query("ClientID") int clientID,
                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/canpulseguide")
    Call<BooleanResponse> canPulseGuide(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/cansetdeclinationrate")
    Call<BooleanResponse> canSetDeclinationRate(@Path("deviceNumber") int deviceNumber,
                                                @Query("ClientID") int clientID,
                                                @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/cansetguiderates")
    Call<BooleanResponse> canSetGuideRates(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/cansetpark")
    Call<BooleanResponse> canSetPark(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/cansetpierside")
    Call<BooleanResponse> canSetPierSide(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/cansetrightascensionrate")
    Call<BooleanResponse> canSetRightAscensionRate(@Path("deviceNumber") int deviceNumber,
                                                   @Query("ClientID") int clientID,
                                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/cansettracking")
    Call<BooleanResponse> canSetTracking(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/canslew")
    Call<BooleanResponse> canSlew(@Path("deviceNumber") int deviceNumber,
                                  @Query("ClientID") int clientID,
                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/canslewaltaz")
    Call<BooleanResponse> canSlewAltAz(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/canslewaltazasync")
    Call<BooleanResponse> canSlewAltAzAsync(@Path("deviceNumber") int deviceNumber,
                                            @Query("ClientID") int clientID,
                                            @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/canslewasync")
    Call<BooleanResponse> canSlewAsync(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/cansync")
    Call<BooleanResponse> canSync(@Path("deviceNumber") int deviceNumber,
                                  @Query("ClientID") int clientID,
                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/cansyncaltaz")
    Call<BooleanResponse> canSyncAltAz(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/canunpark")
    Call<BooleanResponse> canUnpark(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/declination")
    Call<DoubleResponse> getDeclination(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/declinationrate")
    Call<DoubleResponse> getDeclinationRate(@Path("deviceNumber") int deviceNumber,
                                            @Query("ClientID") int clientID,
                                            @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/declinationrate")
    Call<AlpacaResponse> setDeclinationRate(@Path("deviceNumber") int deviceNumber,
                                            @Field("DeclinationRate") double declinationRate,
                                            @Field("ClientID") int clientID,
                                            @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/doesrefraction")
    Call<BooleanResponse> doesRefraction(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/doesrefraction")
    Call<AlpacaResponse> setDoesRefraction(@Path("deviceNumber") int deviceNumber,
                                           @Field("DoesRefraction") boolean doesRefraction,
                                           @Field("ClientID") int clientID,
                                           @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/equatorialsystem")
    Call<IntResponse> getEquatorialSystem(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/focallength")
    Call<DoubleResponse> getFocalLength(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/guideratedeclination")
    Call<DoubleResponse> getGuideRateDeclination(@Path("deviceNumber") int deviceNumber,
                                                 @Query("ClientID") int clientID,
                                                 @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/guideratedeclination")
    Call<AlpacaResponse> setGuideRateDeclination(@Path("deviceNumber") int deviceNumber,
                                                 @Field("GuideRateDeclination") double guideRate,
                                                 @Field("ClientID") int clientID,
                                                 @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/guideraterightascension")
    Call<DoubleResponse> getGuideRateRightAscension(@Path("deviceNumber") int deviceNumber,
                                                    @Query("ClientID") int clientID,
                                                    @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/guideraterightascension")
    Call<AlpacaResponse> setGuideRateRightAscension(@Path("deviceNumber") int deviceNumber,
                                                    @Field("GuideRateRightAscension") double guideRate,
                                                    @Field("ClientID") int clientID,
                                                    @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/ispulseguiding")
    Call<BooleanResponse> isPulseGuiding(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/rightascension")
    Call<DoubleResponse> getRightAscension(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/rightascensionrate")
    Call<DoubleResponse> getRightAscensionRate(@Path("deviceNumber") int deviceNumber,
                                               @Query("ClientID") int clientID,
                                               @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/rightascensionrate")
    Call<AlpacaResponse> setRightAscensionRate(@Path("deviceNumber") int deviceNumber,
                                               @Field("RightAscensionRate") double rightAscensionRate,
                                               @Field("ClientID") int clientID,
                                               @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/sideofpier")
    Call<IntResponse> getSideOfPier(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/sideofpier")
    Call<AlpacaResponse> setSideOfPier(@Path("deviceNumber") int deviceNumber,
                                       @Field("SideOfPier") int sideOfPier,
                                       @Field("ClientID") int clientID,
                                       @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/siderealtime")
    Call<DoubleResponse> getSiderealTime(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/siteelevation")
    Call<DoubleResponse> getSiteElevation(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/siteelevation")
    Call<AlpacaResponse> setSiteElevation(@Path("deviceNumber") int deviceNumber,
                                          @Field("SiteElevation") double siteElevation,
                                          @Field("ClientID") int clientID,
                                          @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/sitelatitude")
    Call<DoubleResponse> getSiteLatitude(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/sitelatitude")
    Call<AlpacaResponse> setSiteLatitude(@Path("deviceNumber") int deviceNumber,
                                         @Field("SiteLatitude") double siteLatitude,
                                         @Field("ClientID") int clientID,
                                         @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/sitelongitude")
    Call<DoubleResponse> getSiteLongitude(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/sitelongitude")
    Call<AlpacaResponse> setSiteLongitude(@Path("deviceNumber") int deviceNumber,
                                          @Field("SiteLongitude") double siteLongitude,
                                          @Field("ClientID") int clientID,
                                          @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/slewing")
    Call<BooleanResponse> isSlewing(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/slewsettletime")
    Call<IntResponse> getSlewSettleTime(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/slewsettletime")
    Call<AlpacaResponse> setSlewSettleTime(@Path("deviceNumber") int deviceNumber,
                                           @Field("SlewSettleTime") int slewSettleTime,
                                           @Field("ClientID") int clientID,
                                           @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/targetdeclination")
    Call<DoubleResponse> getTargetDeclination(@Path("deviceNumber") int deviceNumber,
                                              @Query("ClientID") int clientID,
                                              @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/targetdeclination")
    Call<AlpacaResponse> setTargetDeclination(@Path("deviceNumber") int deviceNumber,
                                              @Field("TargetDeclination") double targetDeclination,
                                              @Field("ClientID") int clientID,
                                              @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/targetrightascension")
    Call<DoubleResponse> getTargetRightAscension(@Path("deviceNumber") int deviceNumber,
                                                 @Query("ClientID") int clientID,
                                                 @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/targetrightascension")
    Call<AlpacaResponse> setTargetRightAscension(@Path("deviceNumber") int deviceNumber,
                                                 @Field("TargetRightAscension") double targetRightAscension,
                                                 @Field("ClientID") int clientID,
                                                 @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/tracking")
    Call<BooleanResponse> isTracking(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/tracking")
    Call<AlpacaResponse> setTracking(@Path("deviceNumber") int deviceNumber,
                                     @Field("Tracking") boolean tracking,
                                     @Field("ClientID") int clientID,
                                     @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/trackingrate")
    Call<IntResponse> getTrackingRate(@Path("deviceNumber") int deviceNumber,
                                      @Query("ClientID") int clientID,
                                      @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/trackingrate")
    Call<AlpacaResponse> setTrackingRate(@Path("deviceNumber") int deviceNumber,
                                         @Field("TrackingRate") int trackingRate,
                                         @Field("ClientID") int clientID,
                                         @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/trackingrates")
    Call<ListResponse<DriveRate>> getTrackingRates(@Path("deviceNumber") int deviceNumber,
                                                   @Query("ClientID") int clientID,
                                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/utcdate")
    Call<StringResponse> getUTCDate(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/telescope/{deviceNumber}/utcdate")
    Call<AlpacaResponse> setUTCDate(@Path("deviceNumber") int deviceNumber,
                                    @Field("UTCDate") String utcDate,
                                    @Field("ClientID") int clientID,
                                    @Field("ClientTransactionID") long clientTransactionID);

    @PUT
    ("api/v1/telescope/{deviceNumber}/abortslew")
    Call<AlpacaResponse> abortSlew(@Path("deviceNumber") int deviceNumber,
                             @Field("ClientID") int clientID,
                             @Field("ClientTransactionID") long clientTransactionID);

    @GET
    ("api/v1/telescope/{deviceNumber}/axisrates")
    Call<ListResponse<AxisRate>> getAxisRates(@Path("deviceNumber") int deviceNumber,
                                        @Query("Axis") int axis,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET
    ("api/v1/telescope/{deviceNumber}/canmoveaxis")
    Call<BooleanResponse> canMoveAxis(@Path("deviceNumber") int deviceNumber,
                                @Query("Axis") int axis,
                                @Query("ClientID") int clientID,
                                @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/telescope/{deviceNumber}/destinationsideofpier")
    Call<IntResponse> getDestinationSideOfPier(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/findhome")
    Call<AlpacaResponse> findHome(@Path("deviceNumber") int deviceNumber,
                            @Field("ClientID") int clientID,
                            @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/moveaxis")
    Call<AlpacaResponse> moveAxis(@Path("deviceNumber") int deviceNumber,
                            @Field("Axis") int axis,
                            @Field("Rate") double rate,
                            @Field("ClientID") int clientID,
                            @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/park")
    Call<AlpacaResponse> park(@Path("deviceNumber") int deviceNumber,
                        @Field("ClientID") int clientID,
                        @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/pulseguide")
    Call<AlpacaResponse> pulseguide(@Path("deviceNumber") int deviceNumber,
                              @Field("Direction") int direction,
                              @Field("Duration") int duration,
                              @Field("ClientID") int clientID,
                              @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/setpark")
    Call<AlpacaResponse> setPark(@Path("deviceNumber") int deviceNumber,
                           @Field("ClientID") int clientID,
                           @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/slewtoaltaz")
    Call<AlpacaResponse> slewToAltAz(@Path("deviceNumber") int deviceNumber,
                               @Field("Azimuth") double direction,
                               @Field("Altitude") double altitude,
                               @Field("ClientID") int clientID,
                               @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/slewtoaltazasync")
    Call<AlpacaResponse> slewToAltAzAsync(@Path("deviceNumber") int deviceNumber,
                                    @Field("Azimuth") double direction,
                                    @Field("Altitude") double altitude,
                                    @Field("ClientID") int clientID,
                                    @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/slewtocoordinates")
    Call<AlpacaResponse> slewToCoordinates(@Path("deviceNumber") int deviceNumber,
                                     @Field("RightAscension") double rightAscension,
                                     @Field("Declination") double declination,
                                     @Field("ClientID") int clientID,
                                     @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/slewtocoordinatesasync")
    Call<AlpacaResponse> slewToCoordinatesAsync(@Path("deviceNumber") int deviceNumber,
                                          @Field("RightAscension") double rightAscension,
                                          @Field("Declination") double declination,
                                          @Field("ClientID") int clientID,
                                          @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/slewtotarget")
    Call<AlpacaResponse> slewToTarget(@Path("deviceNumber") int deviceNumber,
                                @Field("ClientID") int clientID,
                                @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/slewtotargetasync")
    Call<AlpacaResponse> slewToTargetAsync(@Path("deviceNumber") int deviceNumber,
                                @Field("ClientID") int clientID,
                                @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/synctoaltaz")
    Call<AlpacaResponse> syncToAltAz(@Path("deviceNumber") int deviceNumber,
                               @Field("Azimuth") double direction,
                               @Field("Altitude") double altitude,
                               @Field("ClientID") int clientID,
                               @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/synctocoordinates")
    Call<AlpacaResponse> syncToCoordinates(@Path("deviceNumber") int deviceNumber,
                                     @Field("RightAscension") double rightAscension,
                                     @Field("Declination") double declination,
                                     @Field("ClientID") int clientID,
                                     @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/synctotarget")
    Call<AlpacaResponse> syncToTarget(@Path("deviceNumber") int deviceNumber,
                                @Field("ClientID") int clientID,
                                @Field("ClientTransactionID") long clientTransactionID);

    @PUT("api/v1/telescope/{deviceNumber}/unpark")
    Call<AlpacaResponse> unpark(@Path("deviceNumber") int deviceNumber,
                          @Field("ClientID") int clientID,
                          @Field("ClientTransactionID") long clientTransactionID);
}