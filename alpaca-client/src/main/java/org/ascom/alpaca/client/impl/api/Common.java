package org.ascom.alpaca.client.impl.api;


import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.*;
import retrofit2.Call;
import retrofit2.http.*;

@SuppressWarnings("SpellCheckingInspection")
public interface Common {

    @FormUrlEncoded
    @PUT("{deviceType}/{deviceNumber}/connect")
    Call<AlpacaResponse> connect(@Path("deviceType") String deviceType,
                                 @Path("deviceNumber") int deviceNumber,
                                 @Field("ClientID") int clientID,
                                 @Field("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("{deviceType}/{deviceNumber}/disconnect")
    Call<AlpacaResponse> disconnect(@Path("deviceType") String deviceType,
                                    @Path("deviceNumber") int deviceNumber,
                                    @Field("ClientID") int clientID,
                                    @Field("ClientTransactionID") long clientTransactionID);

    @GET("{deviceType}/{deviceNumber}/connecting")
    Call<BooleanResponse> isConnecting(@Path("deviceType") String deviceType,
                                       @Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("{deviceType}/{deviceNumber}/connected")
    Call<BooleanResponse> isConnected(@Path("deviceType") String deviceType,
                                      @Path("deviceNumber") int deviceNumber,
                                      @Query("ClientID") int clientID,
                                      @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("{deviceType}/{deviceNumber}/connected")
    Call<AlpacaResponse> setConnectedState(@Path("deviceType") String deviceType,
                                           @Path("deviceNumber") int deviceNumber,
                                           @Field("ClientID") int clientID,
                                           @Field("ClientTransactionID") long clientTransactionID,
                                           @Field("Connected") Boolean connectedState);

    @GET("{deviceType}/{deviceNumber}/description")
    Call<StringResponse> getDescription(@Path("deviceType") String deviceType,
                                        @Path("deviceNumber") int deviceNumber,
                                        @Query("ClientID") int clientID,
                                        @Query("ClientTransactionID") long clientTransactionID);

    @GET("{deviceType}/{deviceNumber}/devicestate")
    Call<ListResponse<StateValue>> getDeviceState(@Path("deviceType") String deviceType,
                                                  @Path("deviceNumber") int deviceNumber,
                                                  @Query("ClientID") int clientID,
                                                  @Query("ClientTransactionID") long clientTransactionID);

    @GET("{deviceType}/{deviceNumber}/driverinfo")
    Call<StringResponse> getDriverInfo(@Path("deviceType") String deviceType,
                                       @Path("deviceNumber") int deviceNumber,
                                       @Query("ClientID") int clientID,
                                       @Query("ClientTransactionID") long clientTransactionID);

    @GET("{deviceType}/{deviceNumber}/driverversion")
    Call<StringResponse> getDriverVersion(@Path("deviceType") String deviceType,
                                          @Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @GET("{deviceType}/{deviceNumber}/interfaceversion")
    Call<IntResponse> getInterfaceVersion(@Path("deviceType") String deviceType,
                                          @Path("deviceNumber") int deviceNumber,
                                          @Query("ClientID") int clientID,
                                          @Query("ClientTransactionID") long clientTransactionID);

    @GET("{deviceType}/{deviceNumber}/name")
    Call<StringResponse> getName(@Path("deviceType") String deviceType,
                                 @Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);

    @GET("{deviceType}/{deviceNumber}/supportedactions")
    Call<ListResponse<String>> getSupportedActions(@Path("deviceType") String deviceType,
                                                   @Path("deviceNumber") int deviceNumber,
                                                   @Query("ClientID") int clientID,
                                                   @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("{deviceType}/{deviceNumber}/action")
    Call<StringResponse> executeAction(@Path("deviceType") String deviceType,
                                       @Path("deviceNumber") int deviceNumber,
                                       @Field("ClientID") int clientID,
                                       @Field("ClientTransactionID") long clientTransactionID,
                                       @Field("Action") String action,
                                       @Field("Parameters") String parameters);
}
