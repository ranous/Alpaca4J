package org.ascom.alpaca.api;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.response.*;

@SuppressWarnings("SpellCheckingInspection")
@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface Switch {
    @GET
    @Path("/switch/{deviceNumber}/canasync")
    BooleanResponse canAsync(@PathParam("deviceNumber") Integer deviceNumber,
                             @QueryParam("Id") Integer id,
                             @QueryParam("ClientID") Integer clientID,
                             @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/canwrite")
    BooleanResponse canWrite(@PathParam("deviceNumber") Integer deviceNumber,
                             @QueryParam("Id") Integer id,
                             @QueryParam("ClientID") Integer clientID,
                             @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/maxswitch")
    IntResponse getMaxSwitch(@PathParam("deviceNumber") Integer deviceNumber,
                             @QueryParam("ClientID") Integer clientID,
                             @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/getswitchdescription")
    StringResponse getSwitchDescription(@PathParam("deviceNumber") Integer deviceNumber,
                                        @QueryParam("Id") Integer id,
                                        @QueryParam("ClientID") Integer clientID,
                                        @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/getswitchname")
    StringResponse getSwitchName(@PathParam("deviceNumber") Integer deviceNumber,
                                 @QueryParam("Id") Integer id,
                                 @QueryParam("ClientID") Integer clientID,
                                 @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/getswitch")
    BooleanResponse getSwitchState(@PathParam("deviceNumber") Integer deviceNumber,
                                   @QueryParam("Id") Integer id,
                                   @QueryParam("ClientID") Integer clientID,
                                   @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/getswitchvalue")
    DoubleResponse getSwitchValue(@PathParam("deviceNumber") Integer deviceNumber,
                                  @QueryParam("Id") Integer id,
                                  @QueryParam("ClientID") Integer clientID,
                                  @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/minswitchvalue")
    DoubleResponse getMinSwitchValue(@PathParam("deviceNumber") Integer deviceNumber,
                                     @QueryParam("Id") Integer id,
                                     @QueryParam("ClientID") Integer clientID,
                                     @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/maxswitchvalue")
    DoubleResponse getMaxSwitchValue(@PathParam("deviceNumber") Integer deviceNumber,
                                     @QueryParam("Id") Integer id,
                                     @QueryParam("ClientID") Integer clientID,
                                     @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/statechangecomplete")
    BooleanResponse isStateChangeComplete(@PathParam("deviceNumber") Integer deviceNumber,
                                          @QueryParam("Id") Integer id,
                                          @QueryParam("ClientID") Integer clientID,
                                          @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @PUT
    @Path("/switch/{deviceNumber}/cancelasync")
    AlpacaResponse cancelAsync(@PathParam("deviceNumber") Integer deviceNumber,
                               @FormParam("Id") int id,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("/switch/{deviceNumber}/setasync")
    AlpacaResponse setAsync(@PathParam("deviceNumber") Integer deviceNumber,
                            @FormParam("Id") int id,
                            @FormParam("State") boolean state,
                            @FormParam("ClientID") int clientID,
                            @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("/switch/{deviceNumber}/setasyncvalue")
    AlpacaResponse setAsyncValue(@PathParam("deviceNumber") Integer deviceNumber,
                                 @FormParam("Id") int id,
                                 @FormParam("Value") int state,
                                 @FormParam("ClientID") int clientID,
                                 @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("/switch/{deviceNumber}/setswitch")
    AlpacaResponse setSwitchState(@PathParam("deviceNumber") Integer deviceNumber,
                                  @FormParam("Id") int id,
                                  @FormParam("State") boolean state,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("/switch/{deviceNumber}/setswitchname")
    AlpacaResponse setSwitchName(@PathParam("deviceNumber") Integer deviceNumber,
                                 @FormParam("Id") Integer id,
                                 @FormParam("Name") String name,
                                 @FormParam("ClientID") Integer clientID,
                                 @FormParam("ClientTransactionID") Integer clientTransactionID);

    @GET
    @Path("/switch/{deviceNumber}/switchstep")
    DoubleResponse getSwitchStep(@PathParam("deviceNumber") Integer deviceNumber,
                                 @QueryParam("Id") Integer id,
                                 @QueryParam("ClientID") Integer clientID,
                                 @QueryParam("ClientTransactionID") Integer clientTransactionID);

    @PUT
    @Path("/switch/{deviceNumber}/setswitchvalue")
    AlpacaResponse setSwitchValue(@PathParam("deviceNumber") Integer deviceNumber,
                                  @FormParam("Id") int id,
                                  @FormParam("Value") double value,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID);
}
