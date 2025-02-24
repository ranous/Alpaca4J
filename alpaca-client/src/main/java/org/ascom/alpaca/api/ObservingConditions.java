package org.ascom.alpaca.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.StringResponse;

import retrofit2.Call;
import retrofit2.http.*;

public interface ObservingConditions {

    @GET("api/v1/observingconditions/{deviceNumber}/averageperiod")
    Call<DoubleResponse> getAveragePeriod(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/observingconditions/{deviceNumber}/averageperiod")
    Call<AlpacaResponse> setAveragePeriod(@Path("deviceNumber") int deviceNumber,
                                          @Field("AveragePeriod") double averagePeriod,
                                          @Field("ClientID") int clientID,
                                          @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/cloudcover")
    Call<DoubleResponse> getCloudCover(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/dewpoint")
    Call<DoubleResponse> getDewPoint(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/humidity")
    Call<DoubleResponse> getHumidity(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/pressure")
    Call<DoubleResponse> getPressure(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/rainrate")
    Call<DoubleResponse> getRainRate(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/skybrightness")
    Call<DoubleResponse> getSkyBrightness(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/skyquality")
    Call<DoubleResponse> getSkyQuality(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/skytemperature")
    Call<DoubleResponse> getSkyTemperature(@Path("deviceNumber") int deviceNumber,
                                           @Query("ClientID") int clientID,
                                           @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/starfwhm")
    Call<DoubleResponse> getStarFWHM(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/temperature")
    Call<DoubleResponse> getTemperature(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/winddirection")
    Call<DoubleResponse> getWindDirection(@Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/windgust")
    Call<DoubleResponse> getWindGust(@Path("deviceNumber") int deviceNumber,
                                     @Query("ClientID") int clientID,
                                     @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/windspeed")
    Call<DoubleResponse> getWindSpeed(@Path("deviceNumber") int deviceNumber,
                                      @Query("ClientID") int clientID,
                                      @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/observingconditions/{deviceNumber}/refresh")
    Call<AlpacaResponse> refresh(@Path("deviceNumber") int deviceNumber,
                                 @Field("ClientID") int clientID,
                                 @Field("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/sensordescription")
    Call<StringResponse> getSensorDescription(@Path("deviceNumber") int deviceNumber,
                                              @Query("SensorName") String sensorName,
                                              @Query("ClientID") int clientID,
                                              @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/observingconditions/{deviceNumber}/timesincelastupdate")
    Call<DoubleResponse> getTimeSinceLastUpdate(@Path("deviceNumber") int deviceNumber,
                                                @Query("SensorName") String sensorName,
                                                @Query("ClientID") int clientID,
                                                @Query("ClientTransactionID") long clientTransactionID);
}
