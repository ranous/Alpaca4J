package org.ascom.alpaca.api;

import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.ListResponse;
import org.ascom.alpaca.response.ServerInfoResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@SuppressWarnings("SpellCheckingInspection")
@Path("/management")
@Produces(MediaType.APPLICATION_JSON)
public interface Management {

    @Path("apiversions")
    @GET
    ListResponse<Integer> getApiVersions(@QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID);

    @Path("v1/configureddevices")
    @GET
    ListResponse<DeviceDescriptor> getConfiguredDevices(@QueryParam("ClientID") int clientID,
                                                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @Path("v1/description")
    @GET
    ServerInfoResponse getDescription(@QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID);
}
