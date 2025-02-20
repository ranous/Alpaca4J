package org.ascom.alpaca.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
    AlpacaResponse halt(@PathParam("deviceNumber") int deviceNumber,
                        @FormParam("ClientID") int clientID,
                        @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("rotator/{deviceNumber}/move")
    AlpacaResponse move(@PathParam("deviceNumber") int deviceNumber,
                        @FormParam("ClientID") int clientID,
                        @FormParam("ClientTransactionID") long clientTransactionID,
                        @FormParam("Position") double postion);

    @PUT
    @Path("rotator/{deviceNumber}/moveabsolute")
    AlpacaResponse moveAbsolute(@PathParam("deviceNumber") int deviceNumber,
                                @FormParam("ClientID") int clientID,
                                @FormParam("ClientTransactionID") long clientTransactionID,
                                @FormParam("Position") double postion);

    @PUT
    @Path("rotator/{deviceNumber}/movemechanical")
    AlpacaResponse moveMechanical(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID,
                                  @FormParam("Position") double postion);

    @PUT
    @Path("rotator/{deviceNumber}/sync")
    AlpacaResponse sync(@PathParam("deviceNumber") int deviceNumber,
                        @FormParam("ClientID") int clientID,
                        @FormParam("ClientTransactionID") long clientTransactionID,
                        @FormParam("Position") double postion);
}
