package org.ascom.alpaca.api;

import org.ascom.alpaca.model.CalibratorState;
import org.ascom.alpaca.model.CoverState;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.IntResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.response.ValueResponse;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface CoverCalibrator {
    @GET
    @Path("covercalibrator/{deviceNumber}/brightness")
    IntResponse getBrightness(@PathParam("deviceNumber") int deviceNumber,
                              @QueryParam("ClientID") int clientID,
                              @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("covercalibrator/{deviceNumber}/calibratorchanging")
    BooleanResponse isCalibratorChanging(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("covercalibrator/{deviceNumber}/calibratorstate")
    ValueResponse<CalibratorState> getCalibratorState(@PathParam("deviceNumber") int deviceNumber,
                                                      @QueryParam("ClientID") int clientID,
                                                      @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("covercalibrator/{deviceNumber}/covermoving")
    BooleanResponse isCoverMoving(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("covercalibrator/{deviceNumber}/coverstate")
    ValueResponse<CoverState> getCoverState(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("covercalibrator/{deviceNumber}/maxbrightness")
    IntResponse getMaxBrightness(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("covercalibrator/{deviceNumber}/calibratoroff")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse turnCalibratorOff(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("covercalibrator/{deviceNumber}/calibratoron")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse turnCalibratorOn(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID,
                                    @FormParam("Brightness") int brightness);

    @PUT
    @Path("covercalibrator/{deviceNumber}/closecover")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse closeCover(@PathParam("deviceNumber") int deviceNumber,
                              @FormParam("ClientID") int clientID,
                              @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("covercalibrator/{deviceNumber}/haltcover")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse haltCover(@PathParam("deviceNumber") int deviceNumber,
                             @FormParam("ClientID") int clientID,
                             @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("covercalibrator/{deviceNumber}/opencover")
    AlpacaResponse openCover(@PathParam("deviceNumber") int deviceNumber,
                             @FormParam("ClientID") int clientID,
                             @FormParam("ClientTransactionID") long clientTransactionID);
}