package org.ascom.alpaca.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface Focuser {
    @GET
    @Path("focuser/{deviceNumber}/absolute")
    BooleanResponse canAbsoluteFocus(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("focuser/{deviceNumber}/ismoving")
    BooleanResponse isMoving(@PathParam("deviceNumber") int deviceNumber,
                             @QueryParam("ClientID") int clientID,
                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("focuser/{deviceNumber}/maxincrement")
    IntResponse getMaxIncrement(@PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("focuser/{deviceNumber}/maxstep")
    IntResponse getMaxStep(@PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("focuser/{deviceNumber}/position")
    IntResponse getPosition(@PathParam("deviceNumber") int deviceNumber,
                            @QueryParam("ClientID") int clientID,
                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("focuser/{deviceNumber}/stepsize")
    DoubleResponse getStepSize(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("focuser/{deviceNumber}/tempcomp")
    BooleanResponse isTemperatureCompensating(@PathParam("deviceNumber") int deviceNumber,
                                              @QueryParam("ClientID") int clientID,
                                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("focuser/{deviceNumber}/tempcomp")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse setTemperatureCompensation(@PathParam("deviceNumber") int deviceNumber,
                                              @FormParam("TempComp") boolean tempCompState,
                                              @FormParam("ClientID") int clientID,
                                              @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("focuser/{deviceNumber}/tempcompavailable")
    BooleanResponse hasTemperatureCompensation(@PathParam("deviceNumber") int deviceNumber,
                                               @QueryParam("ClientID") int clientID,
                                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("focuser/{deviceNumber}/temperature")
    DoubleResponse getTemperature(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("focuser/{deviceNumber}/halt")
    AlpacaResponse haltFocuser(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("focuser/{deviceNumber}/move")
    AlpacaResponse moveToPosition(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("Position") int position,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID);
}
