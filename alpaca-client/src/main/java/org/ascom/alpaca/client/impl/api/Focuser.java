package org.ascom.alpaca.client.impl.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface Focuser {

    @GET("api/v1/focuser/{deviceNumber}/absolute")
    Call<BooleanResponse> canAbsoluteFocus(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/focuser/{deviceNumber}/ismoving")
    Call<BooleanResponse> isMoving(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/focuser/{deviceNumber}/maxincrement")
    Call<IntResponse> getMaxIncrement(@Path("deviceNumber") int deviceNumber,
                                      @Query("ClientID") int clientID,
                                      @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/focuser/{deviceNumber}/maxstep")
    Call<IntResponse> getMaxStep(@Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/focuser/{deviceNumber}/position")
    Call<IntResponse> getPosition(@Path("deviceNumber") int deviceNumber,
                                  @Query("ClientID") int clientID,
                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/focuser/{deviceNumber}/stepsize")
    Call<DoubleResponse> getStepSize(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/focuser/{deviceNumber}/tempcomp")
    Call<BooleanResponse> isTemperatureCompensating(@Path("deviceNumber") int deviceNumber,
                                                    @Query("ClientID") int clientID,
                                                    @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/focuser/{deviceNumber}/tempcomp")
    Call<AlpacaResponse> setTemperatureCompensation(@Path("deviceNumber") int deviceNumber,
                                                    @Field("TempComp") boolean tempCompState,
                                                    @Field("ClientID") int clientID,
                                                    @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/focuser/{deviceNumber}/tempcompavailable")
    Call<BooleanResponse> hasTemperatureCompensation(@Path("deviceNumber") int deviceNumber,
                                                     @Query("ClientID") int clientID,
                                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/focuser/{deviceNumber}/temperature")
    Call<DoubleResponse> getTemperature(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/focuser/{deviceNumber}/halt")
    Call<AlpacaResponse> haltFocuser(@Path("deviceNumber") int deviceNumber,
                                     @Field("ClientID") int clientID,
                                     @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/focuser/{deviceNumber}/move")
    Call<AlpacaResponse> moveToPosition(@Path("deviceNumber") int deviceNumber,
                                        @Field("Position") int position,
                                        @Field("ClientID") int clientID,
                                        @Field("ClientTransactionID") long clientTransactionID);
}