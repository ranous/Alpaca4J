package org.ascom.alpaca.api;

import org.ascom.alpaca.response.BooleanResponse;
import retrofit2.Call;
import retrofit2.http.*;

@SuppressWarnings("SpellCheckingInspection")

public interface SafetyMonitor {
    @GET("safetymonitor/{deviceNumber}/issafe")
    Call<BooleanResponse> isSafe(@Path("deviceNumber") int deviceNumber,
                                 @Query("ClientID") int clientID,
                                 @Query("ClientTransactionID") long clientTransactionID);
}
