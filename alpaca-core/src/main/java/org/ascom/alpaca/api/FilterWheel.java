package org.ascom.alpaca.api;

import org.ascom.alpaca.response.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface FilterWheel {

    @GET
    @Path("filterwheel/{deviceNumber}/focusoffsets")
    ListResponse<Integer> getFocusOffsets(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("filterwheel/{deviceNumber}/names")
    ListResponse<String> getFilterNames(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("filterwheel/{deviceNumber}/position")
    IntResponse getPosition(@PathParam("deviceNumber") int deviceNumber,
                            @QueryParam("ClientID") int clientID,
                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("filterwheel/{deviceNumber}/position")
    AlpacaResponse setPosition(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID,
                               @FormParam("Position") int position);
}
