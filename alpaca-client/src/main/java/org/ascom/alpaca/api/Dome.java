package org.ascom.alpaca.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface Dome {

    @GET("api/v1/dome/{deviceNumber}/altitude")
    Call<DoubleResponse> getAltitude(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/athome")
    Call<BooleanResponse> atHome(@Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/atpark")
    Call<BooleanResponse> atPark(@Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/azimuth")
    Call<DoubleResponse> getAzimuth(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/canfindhome")
    Call<BooleanResponse> canFindHome(@Path("deviceNumber") int deviceNumber,
                                      @Query("ClientID") int clientID,
                                      @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/canpark")
    Call<BooleanResponse> canPark(@Path("deviceNumber") int deviceNumber,
                                  @Query("ClientID") int clientID,
                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/cansetaltitude")
    Call<BooleanResponse> canSetAltitude(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/cansetazimuth")
    Call<BooleanResponse> canSetAzimuth(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/cansetpark")
    Call<BooleanResponse> canSetPark(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/cansetshutter")
    Call<BooleanResponse> canSetShutter(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/canslave")
    Call<BooleanResponse> canSlave(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/cansyncazimuth")
    Call<BooleanResponse> canSyncAzimuth(@Path("deviceNumber") int deviceNumber,
                                         @Query("ClientID") int clientID,
                                         @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/shutterstatus")
    Call<IntResponse> getShutterStatus(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/dome/{deviceNumber}/slaved")
    Call<BooleanResponse> isSlaved(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/slaved")
    Call<AlpacaResponse> setSlaved(@Path("deviceNumber") int deviceNumber,
                                   @Field("ClientID") int clientID,
                                   @Field("ClientTransactionID") long clientTransactionID,
                                   @Field("Slaved") boolean slaved);

    @GET("api/v1/dome/{deviceNumber}/slewing")
    Call<BooleanResponse> isSlewing(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/abortslew")
    Call<AlpacaResponse> abortSlew(@Path("deviceNumber") int deviceNumber,
                                   @Field("ClientID") int clientID,
                                   @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/closeshutter")
    Call<AlpacaResponse> closeShutter(@Path("deviceNumber") int deviceNumber,
                                      @Field("ClientID") int clientID,
                                      @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/findhome")
    Call<AlpacaResponse> findHome(@Path("deviceNumber") int deviceNumber,
                                  @Field("ClientID") int clientID,
                                  @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/openshutter")
    Call<AlpacaResponse> openShutter(@Path("deviceNumber") int deviceNumber,
                                     @Field("ClientID") int clientID,
                                     @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/park")
    Call<AlpacaResponse> park(@Path("deviceNumber") int deviceNumber,
                              @Field("ClientID") int clientID,
                              @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/setpark")
    Call<AlpacaResponse> setPark(@Path("deviceNumber") int deviceNumber,
                                 @Field("ClientID") int clientID,
                                 @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/slewtoaltitude")
    Call<AlpacaResponse> slewToAltitude(@Path("deviceNumber") int deviceNumber,
                                        @Field("ClientID") int clientID,
                                        @Field("ClientTransactionID") long clientTransactionID,
                                        @Field("Altitude") double altitude);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/slewtoazimuth")
    Call<AlpacaResponse> slewToAzimuth(@Path("deviceNumber") int deviceNumber,
                                       @Field("ClientID") int clientID,
                                       @Field("ClientTransactionID") long clientTransactionID,
                                       @Field("Azimuth") double azimuth);

    @FormUrlEncoded
    @PUT("api/v1/dome/{deviceNumber}/synctoazimuth")
    Call<AlpacaResponse> syncToAzimuth(@Path("deviceNumber") int deviceNumber,
                                       @Field("ClientID") int clientID,
                                       @Field("ClientTransactionID") long clientTransactionID,
                                       @Field("Azimuth") double azimuth);
}