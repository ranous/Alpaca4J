package org.ascom.alpaca.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.*;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface Common {

    @PUT
    @Path("{deviceType}/{deviceNumber}/connect")
    AlpacaResponse connect(@PathParam("deviceType") String deviceType,
                           @PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("{deviceType}/{deviceNumber}/disconnect")
    AlpacaResponse disconnect(@PathParam("deviceType") String deviceType,
                              @PathParam("deviceNumber") int deviceNumber,
                              @FormParam("ClientID") int clientID,
                              @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("{deviceType}/{deviceNumber}/connecting")
    BooleanResponse isConnecting(@PathParam("deviceType") String deviceType,
                                 @PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("{deviceType}/{deviceNumber}/connected")
    BooleanResponse isConnected(@PathParam("deviceType") String deviceType,
                                @PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("{deviceType}/{deviceNumber}/connected")
    AlpacaResponse setConnectedState(@PathParam("deviceType") String deviceType,
                                     @PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID,
                                     @FormParam("Connected") Boolean connectedState);

    @GET
    @Path("{deviceType}/{deviceNumber}/description")
    StringResponse getDescription(@PathParam("deviceType") String deviceType,
                                  @PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("{deviceType}/{deviceNumber}/devicestate")
    ListResponse<StateValue> getDeviceState(@PathParam("deviceType") String deviceType,
                                            @PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("{deviceType}/{deviceNumber}/driverinfo")
    StringResponse getDriverInfo(@PathParam("deviceType") String deviceType,
                                 @PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("{deviceType}/{deviceNumber}/driverversion")
    StringResponse getDriverVersion(@PathParam("deviceType") String deviceType,
                                    @PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("{deviceType}/{deviceNumber}/interfaceversion")
    IntResponse getInterfaceVersion(@PathParam("deviceType") String deviceType,
                                    @PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("{deviceType}/{deviceNumber}/name")
    StringResponse getName(@PathParam("deviceType") String deviceType,
                           @PathParam("deviceNumber") int deviceNumber,
                           @QueryParam("ClientID") int clientID,
                           @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("{deviceType}/{deviceNumber}/supportedactions")
    ListResponse<String> getSupportedActions(@PathParam("deviceType") String deviceType,
                                             @PathParam("deviceNumber") int deviceNumber,
                                             @QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("{deviceType}/{deviceNumber}/action")
    StringResponse executeAction(@PathParam("deviceType") String deviceType,
                                 @PathParam("deviceNumber") int deviceNumber,
                                 @FormParam("ClientID") int clientID,
                                 @FormParam("ClientTransactionID") long clientTransactionID,
                                 @FormParam("Action") String action,
                                 @FormParam("Parameters") String parameters);

}
