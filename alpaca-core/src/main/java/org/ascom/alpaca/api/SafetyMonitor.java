package org.ascom.alpaca.api;

import org.ascom.alpaca.response.BooleanResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
public interface SafetyMonitor {
    @GET
    @Path("safetymonitor/{deviceNumber}/issafe")
    BooleanResponse isSafe(@PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);
}
