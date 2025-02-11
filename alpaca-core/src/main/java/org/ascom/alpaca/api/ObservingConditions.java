package org.ascom.alpaca.api;

import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.StringResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@SuppressWarnings("SpellCheckingInspection")
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public interface ObservingConditions {

    @GET
    @Path("observingconditions/{deviceNumber}/averageperiod")
    DoubleResponse getAveragePeriod(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("observingconditions/{deviceNumber}/averageperiod")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    AlpacaResponse setAveragePeriod(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("AveragePeriod") double averagePeriod,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID);
    
    @GET
    @Path("observingconditions/{deviceNumber}/cloudcover")
    DoubleResponse getCloudCover(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/dewpoint")
    DoubleResponse getDewPoint(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/humidity")
    DoubleResponse getHumidity(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);
    
    @GET
    @Path("observingconditions/{deviceNumber}/pressure")
    DoubleResponse getPressure(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);
    
    @GET
    @Path("observingconditions/{deviceNumber}/rainrate")
    DoubleResponse getRainRate(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);
    
    @GET
    @Path("observingconditions/{deviceNumber}/skybrightness")
    DoubleResponse getSkyBrightness(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);
    
    @GET
    @Path("observingconditions/{deviceNumber}/skyquality")
    DoubleResponse getSkyQuality(@PathParam("deviceNumber") int deviceNumber,
                                 @QueryParam("ClientID") int clientID,
                                 @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/skytemperature")
    DoubleResponse getSkyTemperature(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/starfwhm")
    DoubleResponse getStarFWHM(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/temperature")
    DoubleResponse getTemperature(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/winddirection")
    DoubleResponse getWindDirection(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/windgust")
    DoubleResponse getWindGust(@PathParam("deviceNumber") int deviceNumber,
                               @QueryParam("ClientID") int clientID,
                               @QueryParam("ClientTransactionID") long clientTransactionID);
    
    @GET
    @Path("observingconditions/{deviceNumber}/windspeed")
    DoubleResponse getWindSpeed(@PathParam("deviceNumber") int deviceNumber,
                                @QueryParam("ClientID") int clientID,
                                @QueryParam("ClientTransactionID") long clientTransactionID);

    @PUT
    @Path("observingconditions/{deviceNumber}/refresh")
    AlpacaResponse refresh(@PathParam("deviceNumber") int deviceNumber,
                           @FormParam("ClientID") int clientID,
                           @FormParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/sensordescription")
    StringResponse getSensorDescription(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("SensorName") String sensorName,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID);

    @GET
    @Path("observingconditions/{deviceNumber}/timesincelastupdate")
    DoubleResponse getTimeSinceLastUpdate(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("SensorName") String sensorName,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID);
}
