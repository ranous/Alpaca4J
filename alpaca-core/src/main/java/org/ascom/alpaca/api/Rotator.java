package org.ascom.alpaca.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;

import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public interface Rotator {
    @GET
    @Path("rotator/{deviceNumber}/canreverse")
    BooleanResponse canReverse(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("rotator/{deviceNumber}/ismoving")
    BooleanResponse isMoving(@PathParam("deviceNumber") int deviceNumber,
                             @QueryParam("ClientID") int clientID,
                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("rotator/{deviceNumber}/mechanicalposition")
    DoubleResponse getMechanicalPosition(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("rotator/{deviceNumber}/position")
    DoubleResponse getPosition(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("rotator/{deviceNumber}/reverse")
    BooleanResponse isReversed(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("rotator/{deviceNumber}/reverse")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse setReversed(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID,
                               @FormParam("Slaved") boolean reversed);

    @GET
    @Path("rotator/{deviceNumber}/stepsize")
    DoubleResponse getStepSize(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("rotator/{deviceNumber}/targetposition")
    DoubleResponse getTargetPosition(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("rotator/{deviceNumber}/halt")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse halt(@PathParam("deviceNumber") int deviceNumber,
                        @FormParam("ClientID") int clientID,
                        @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("rotator/{deviceNumber}/move")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse move(@PathParam("deviceNumber") int deviceNumber,
                        @FormParam("ClientID") int clientID,
                        @FormParam("ClientTransactionID") long clientTransactionID,
                        @FormParam("Position") double postion);

    @PUT
    @Path("rotator/{deviceNumber}/moveabsolute")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse moveAbsolute(@PathParam("deviceNumber") int deviceNumber,
                                @FormParam("ClientID") int clientID,
                                @FormParam("ClientTransactionID") long clientTransactionID,
                                @FormParam("Position") double postion);

    @PUT
    @Path("rotator/{deviceNumber}/movemechanical")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse moveMechanical(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("Position") double postion);

    @PUT
    @Path("rotator/{deviceNumber}/sync")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse sync(@PathParam("deviceNumber") int deviceNumber,
                        @FormParam("ClientID") int clientID,
                        @FormParam("ClientTransactionID") long clientTransactionID,
                        @FormParam("Position") double postion);
}
