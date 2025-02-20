package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.api.ObservingConditions;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.device.ObservingConditionsDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.StringResponse;


@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@ApplicationScoped
public class ObservingConditionsResource implements ObservingConditions {
    @Inject
    DeviceManager deviceManager;

    // This version will check if the client has a valid connection before returning the device
    private ObservingConditionsDevice getDevice(int deviceID, int clientID) {
        ObservingConditionsDevice device = deviceManager.getDevice(deviceID, DeviceType.ObservingConditions);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @GET
    @Path("observingconditions/{deviceNumber}/averageperiod")
    public DoubleResponse getAveragePeriod(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAveragePeriod(clientID));
    }

    @PUT
    @Path("observingconditions/{deviceNumber}/averageperiod")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public AlpacaResponse setAveragePeriod(@PathParam("deviceNumber") int deviceNumber,
                                           @FormParam("AveragePeriod") double averagePeriod,
                                           @FormParam("ClientID") int clientID,
                                           @FormParam("ClientTransactionID") long clientTransactionID) {
        if (averagePeriod < 0) {
            throw new InvalidValueException("The average period must be greater than 0");
        }
        getDevice(deviceNumber, clientID).setAveragePeriod(clientID, averagePeriod);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("observingconditions/{deviceNumber}/cloudcover")
    public DoubleResponse getCloudCover(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getCloudCover(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/dewpoint")
    public DoubleResponse getDewPoint(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getDewPoint(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/humidity")
    public DoubleResponse getHumidity(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getHumidity(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/pressure")
    public DoubleResponse getPressure(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPressure(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/rainrate")
    public DoubleResponse getRainRate(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getRainRate(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/skybrightness")
    public DoubleResponse getSkyBrightness(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSkyBrightness(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/skyquality")
    public DoubleResponse getSkyQuality(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSkyQuality(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/skytemperature")
    public DoubleResponse getSkyTemperature(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getSkyTemperature(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/starfwhm")
    public DoubleResponse getStarFWHM(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getStarFWHM(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/temperature")
    public DoubleResponse getTemperature(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTemperature(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/winddirection")
    public DoubleResponse getWindDirection(@PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getWindDirection(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/windgust")
    public DoubleResponse getWindGust(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getWindGust(clientID));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/windspeed")
    public DoubleResponse getWindSpeed(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getWindSpeed(clientID));
    }

    @PUT
    @Path("observingconditions/{deviceNumber}/refresh")
    public AlpacaResponse refresh(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).refresh(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("observingconditions/{deviceNumber}/sensordescription")
    public StringResponse getSensorDescription(@PathParam("deviceNumber") int deviceNumber,
                                               @QueryParam("SensorName") String sensorName,
                                               @QueryParam("ClientID") int clientID,
                                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new StringResponse(getDevice(deviceNumber, clientID).getSensorDescription(clientID, sensorName));
    }

    @GET
    @Path("observingconditions/{deviceNumber}/timesincelastupdate")
    public DoubleResponse getTimeSinceLastUpdate(@PathParam("deviceNumber") int deviceNumber,
                                                 @QueryParam("SensorName") String sensorName,
                                                 @QueryParam("ClientID") int clientID,
                                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTimeSinceLastUpdate(clientID, sensorName));
    }
}
