package org.ascom.alpaca.client.impl.api;

import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.ListResponse;
import org.ascom.alpaca.response.ServerInfoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


@SuppressWarnings("SpellCheckingInspection")
public interface Management {

    @GET("apiversions")
    Call<ListResponse<Integer>> getApiVersions(@Query("ClientID") int clientID,
                                               @Query("ClientTransactionID") long clientTransactionID);

    @GET("v1/configureddevices")
    Call<ListResponse<DeviceDescriptor>> getConfiguredDevices(@Query("ClientID") int clientID,
                                                              @Query("ClientTransactionID") long clientTransactionID);

    @GET("v1/description")
    Call<ServerInfoResponse> getDescription(@Query("ClientID") int clientID,
                                            @Query("ClientTransactionID") long clientTransactionID);
}
