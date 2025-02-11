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
public interface Dome {
    @GET
    @Path("dome/{deviceNumber}/altitude")
    DoubleResponse getAltitude(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/athome")
    BooleanResponse atHome(@PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/atpark")
    BooleanResponse atPark(@PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/azimuth")
    DoubleResponse getAzimuth(@PathParam("deviceNumber") int deviceNumber,
                              @QueryParam("ClientID") int clientID,
                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/canfindhome")
    BooleanResponse canFindHome(@PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/canpark")
    BooleanResponse canPark(@PathParam("deviceNumber") int deviceNumber,
                            @QueryParam("ClientID") int clientID,
                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/cansetaltitude")
    BooleanResponse canSetAltitude(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/cansetazimuth")
    BooleanResponse canSetAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/cansetpark")
    BooleanResponse canSetPark(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/cansetshutter")
    BooleanResponse canSetShutter(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/canslave")
    BooleanResponse canSlave(@PathParam("deviceNumber") int deviceNumber,
                             @QueryParam("ClientID") int clientID,
                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/cansyncazimuth")
    BooleanResponse canSyncAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/shutterstatus")
    IntResponse getShutterStatus(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("dome/{deviceNumber}/slaved")
    BooleanResponse isSlaved(@PathParam("deviceNumber") int deviceNumber,
                             @QueryParam("ClientID") int clientID,
                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("dome/{deviceNumber}/slaved")
    AlpacaResponse setSlaved(@PathParam("deviceNumber") int deviceNumber,
                             @FormParam("ClientID") int clientID,
                             @FormParam("ClientTransactionID") long clientTransactionID,
                             @FormParam("Slaved") boolean slaved);

    @GET
    @Path("dome/{deviceNumber}/slewing")
    BooleanResponse isSlewing(@PathParam("deviceNumber") int deviceNumber,
                              @QueryParam("ClientID") int clientID,
                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("dome/{deviceNumber}/abortslew")
    AlpacaResponse abortSlew(@PathParam("deviceNumber") int deviceNumber,
                             @FormParam("ClientID") int clientID,
                             @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("dome/{deviceNumber}/closeshutter")
    AlpacaResponse closeShutter(@PathParam("deviceNumber") int deviceNumber,
                                @FormParam("ClientID") int clientID,
                                @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("dome/{deviceNumber}/findhome")
    AlpacaResponse findHome(@PathParam("deviceNumber") int deviceNumber,
                            @FormParam("ClientID") int clientID,
                            @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("dome/{deviceNumber}/openshutter")
    AlpacaResponse openShutter(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("dome/{deviceNumber}/park")
    AlpacaResponse park(@PathParam("deviceNumber") int deviceNumber,
                        @FormParam("ClientID") int clientID,
                        @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("dome/{deviceNumber}/setpark")
    AlpacaResponse setPark(@PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("dome/{deviceNumber}/slewtoaltitude")
    AlpacaResponse slewToAltitude(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("Altitude") double altitude);

    @PUT
    @Path("dome/{deviceNumber}/slewtoazimuth")
    AlpacaResponse slewToAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                 @FormParam("ClientID") int clientID,
                                 @FormParam("ClientTransactionID") long clientTransactionID,
                                 @FormParam("Azimuth") double azimuth);

    @PUT
    @Path("dome/{deviceNumber}/synctoazimuth")
    AlpacaResponse syncToAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                 @FormParam("ClientID") int clientID,
                                 @FormParam("ClientTransactionID") long clientTransactionID,
                                 @FormParam("Azimuth") double azimuth);
}
