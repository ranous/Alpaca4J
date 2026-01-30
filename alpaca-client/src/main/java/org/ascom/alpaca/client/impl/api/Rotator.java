package org.ascom.alpaca.client.impl.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface Rotator {

    @GET("api/v1/rotator/{deviceNumber}/canreverse")
    Call<BooleanResponse> canReverse(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/rotator/{deviceNumber}/ismoving")
    Call<BooleanResponse> isMoving(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/rotator/{deviceNumber}/mechanicalposition")
    Call<DoubleResponse> getMechanicalPosition(@Path("deviceNumber") int deviceNumber,
                                               @Query("ClientID") int clientID,
                                               @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/rotator/{deviceNumber}/position")
    Call<DoubleResponse> getPosition(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/rotator/{deviceNumber}/reverse")
    Call<BooleanResponse> isReversed(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/rotator/{deviceNumber}/reverse")
    Call<AlpacaResponse> setReversed(@Path("deviceNumber") int deviceNumber,
                                     @Field("ClientID") int clientID,
                                     @Field("ClientTransactionID") long clientTransactionID,
                                     @Field("Slaved") boolean reversed);

    @GET("api/v1/rotator/{deviceNumber}/stepsize")
    Call<DoubleResponse> getStepSize(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/rotator/{deviceNumber}/targetposition")
    Call<DoubleResponse> getTargetPosition(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/rotator/{deviceNumber}/halt")
    Call<AlpacaResponse> halt(@Path("deviceNumber") int deviceNumber,
                              @Field("ClientID") int clientID,
                              @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/rotator/{deviceNumber}/move")
    Call<AlpacaResponse> move(@Path("deviceNumber") int deviceNumber,
                              @Field("ClientID") int clientID,
                              @Field("ClientTransactionID") long clientTransactionID,
                              @Field("Position") double position);

    @FormUrlEncoded
    @PUT("api/v1/rotator/{deviceNumber}/moveabsolute")
    Call<AlpacaResponse> moveAbsolute(@Path("deviceNumber") int deviceNumber,
                                      @Field("ClientID") int clientID,
                                      @Field("ClientTransactionID") long clientTransactionID,
                                      @Field("Position") double position);

    @FormUrlEncoded
    @PUT("api/v1/rotator/{deviceNumber}/movemechanical")
    Call<AlpacaResponse> moveMechanical(@Path("deviceNumber") int deviceNumber,
                                        @Field("ClientID") int clientID,
                                        @Field("ClientTransactionID") long clientTransactionID,
                                        @Field("Position") double position);

    @FormUrlEncoded
    @PUT("api/v1/rotator/{deviceNumber}/sync")
    Call<AlpacaResponse> sync(@Path("deviceNumber") int deviceNumber,
                              @Field("ClientID") int clientID,
                              @Field("ClientTransactionID") long clientTransactionID,
                              @Field("Position") double position);
}