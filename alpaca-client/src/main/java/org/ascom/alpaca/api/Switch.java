package org.ascom.alpaca.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.StringResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface Switch {

    @GET("api/v1/switch/{deviceNumber}/canwrite")
    Call<BooleanResponse> canWrite(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/switch/{deviceNumber}/maxswitch")
    Call<IntResponse> getMaxSwitch(@Path("deviceNumber") int deviceNumber,
                                   @Query("ClientID") int clientID,
                                   @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/switch/{deviceNumber}/getswitch")
    Call<BooleanResponse> getSwitch(@Path("deviceNumber") int deviceNumber,
                                    @Query("ClientID") int clientID,
                                    @Query("ClientTransactionID") long clientTransactionID,
                                    @Query("Id") int id);

    @FormUrlEncoded
    @PUT("api/v1/switch/{deviceNumber}/setswitch")
    Call<AlpacaResponse> setSwitch(@Path("deviceNumber") int deviceNumber,
                                   @Field("ClientID") int clientID,
                                   @Field("ClientTransactionID") long clientTransactionID,
                                   @Field("Id") int id,
                                   @Field("State") boolean state);

    @GET("api/v1/switch/{deviceNumber}/getswitchdescription")
    Call<StringResponse> getSwitchDescription(@Path("deviceNumber") int deviceNumber,
                                              @Query("ClientID") int clientID,
                                              @Query("ClientTransactionID") long clientTransactionID,
                                              @Query("Id") int id);

    @GET("api/v1/switch/{deviceNumber}/getswitchname")
    Call<StringResponse> getSwitchName(@Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID,
                                       @Query("Id") int id);

    @FormUrlEncoded
    @PUT("api/v1/switch/{deviceNumber}/setswitchname")
    Call<AlpacaResponse> setSwitchName(@Path("deviceNumber") int deviceNumber,
                                       @Field("ClientID") int clientID,
                                       @Field("ClientTransactionID") long clientTransactionID,
                                       @Field("Id") int id,
                                       @Field("Name") String name);

    @GET("api/v1/switch/{deviceNumber}/getswitchvalue")
    Call<DoubleResponse> getSwitchValue(@Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID,
                                        @Query("Id") int id);

    @FormUrlEncoded
    @PUT("api/v1/switch/{deviceNumber}/setswitchvalue")
    Call<AlpacaResponse> setSwitchValue(@Path("deviceNumber") int deviceNumber,
                                        @Field("ClientID") int clientID,
                                        @Field("ClientTransactionID") long clientTransactionID,
                                        @Field("Id") int id,
                                        @Field("Value") double value);
}