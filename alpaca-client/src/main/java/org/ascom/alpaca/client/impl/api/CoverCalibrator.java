package org.ascom.alpaca.client.impl.api;

import org.ascom.alpaca.model.CalibratorState;
import org.ascom.alpaca.model.CoverState;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.ValueResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface CoverCalibrator {

    @GET("api/v1/covercalibrator/{deviceNumber}/brightness")
    Call<IntResponse> getBrightness(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/covercalibrator/{deviceNumber}/calibratorchanging")
    Call<BooleanResponse> isCalibratorChanging(@Path("deviceNumber") int deviceNumber,
                                               @Query("ClientID") int clientID,
                                               @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/covercalibrator/{deviceNumber}/calibratorstate")
    Call<ValueResponse<CalibratorState>> getCalibratorState(@Path("deviceNumber") int deviceNumber,
                                                            @Query("ClientID") int clientID,
                                                            @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/covercalibrator/{deviceNumber}/covermoving")
    Call<BooleanResponse> isCoverMoving(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/covercalibrator/{deviceNumber}/coverstate")
    Call<ValueResponse<CoverState>> getCoverState(@Path("deviceNumber") int deviceNumber,
                                                  @Query("ClientID") int clientID,
                                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/covercalibrator/{deviceNumber}/maxbrightness")
    Call<IntResponse> getMaxBrightness(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/covercalibrator/{deviceNumber}/calibratoroff")
    Call<AlpacaResponse> turnCalibratorOff(@Path("deviceNumber") int deviceNumber,
                                           @Field("ClientID") int clientID,
                                           @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/covercalibrator/{deviceNumber}/calibratoron")
    Call<AlpacaResponse> turnCalibratorOn(@Path("deviceNumber") int deviceNumber,
                                          @Field("ClientID") int clientID,
                                          @Field("ClientTransactionID") long clientTransactionID,
                                          @Field("Brightness") int brightness);

    @FormUrlEncoded
    @PUT("api/v1/covercalibrator/{deviceNumber}/closecover")
    Call<AlpacaResponse> closeCover(@Path("deviceNumber") int deviceNumber,
                                    @Field("ClientID") int clientID,
                                    @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/covercalibrator/{deviceNumber}/haltcover")
    Call<AlpacaResponse> haltCover(@Path("deviceNumber") int deviceNumber,
                                   @Field("ClientID") int clientID,
                                   @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/covercalibrator/{deviceNumber}/opencover")
    Call<AlpacaResponse> openCover(@Path("deviceNumber") int deviceNumber,
                                   @Field("ClientID") int clientID,
                                   @Field("ClientTransactionID") long clientTransactionID);
}