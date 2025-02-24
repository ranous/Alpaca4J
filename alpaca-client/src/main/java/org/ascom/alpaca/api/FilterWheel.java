package org.ascom.alpaca.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.ListResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface FilterWheel {

    @GET("api/v1/filterwheel/{deviceNumber}/focusoffsets")
    Call<ListResponse<Integer>> getFocusOffsets(@Path("deviceNumber") int deviceNumber,
                                                @Query("ClientID") int clientID,
                                                @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/filterwheel/{deviceNumber}/names")
    Call<ListResponse<String>> getFilterNames(@Path("deviceNumber") int deviceNumber,
                                              @Query("ClientID") int clientID,
                                              @Query("ClientTransactionID") long clientTransactionID);

    @GET("api/v1/filterwheel/{deviceNumber}/position")
    Call<IntResponse> getPosition(@Path("deviceNumber") int deviceNumber,
                                  @Query("ClientID") int clientID,
                                  @Query("ClientTransactionID") long clientTransactionID);

    @FormUrlEncoded
    @PUT("api/v1/filterwheel/{deviceNumber}/position")
    Call<AlpacaResponse> setPosition(@Path("deviceNumber") int deviceNumber,
                                     @Field("ClientID") int clientID,
                                     @Field("ClientTransactionID") long clientTransactionID,
                                     @Field("Position") int position);
}