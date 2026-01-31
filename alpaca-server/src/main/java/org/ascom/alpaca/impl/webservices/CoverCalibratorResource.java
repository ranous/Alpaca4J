package org.ascom.alpaca.impl.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.device.CoverCalibratorDevice;
import org.ascom.alpaca.impl.DeviceManager;
import org.ascom.alpaca.model.CalibratorState;
import org.ascom.alpaca.model.CoverState;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.*;

@ApplicationScoped
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class CoverCalibratorResource {
    @Inject
    DeviceManager deviceManager;

    private CoverCalibratorDevice getDevice(int deviceID, int clientID) {
        CoverCalibratorDevice device = deviceManager.getDevice(deviceID, DeviceType.CoverCalibrator);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @GET
    @Path("covercalibrator/{deviceNumber}/brightness")
    public IntResponse getBrightness(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getBrightness());
    }

    @GET
    @Path("covercalibrator/{deviceNumber}/calibratorchanging")
    public BooleanResponse isCalibratorChanging(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isCalibratorChanging());
    }

    @GET
    @Path("covercalibrator/{deviceNumber}/calibratorstate")
    public ValueResponse<CalibratorState> getCalibratorState(@PathParam("deviceNumber") int deviceNumber,
                                                             @QueryParam("ClientID") int clientID,
                                                             @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ValueResponse<>(getDevice(deviceNumber, clientID).getCalibratorState());
    }

    @GET
    @Path("covercalibrator/{deviceNumber}/covermoving")
    public BooleanResponse isCoverMoving(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isCoverMoving());
    }

    @GET
    @Path("covercalibrator/{deviceNumber}/coverstate")
    public ValueResponse<CoverState> getCoverState(@PathParam("deviceNumber") int deviceNumber,
                                                   @QueryParam("ClientID") int clientID,
                                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ValueResponse<>(getDevice(deviceNumber, clientID).getCoverState());
    }

    @GET
    @Path("covercalibrator/{deviceNumber}/maxbrightness")
    public IntResponse getMaxBrightness(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxBrightness());
    }

    @PUT
    @Path("covercalibrator/{deviceNumber}/calibratoroff")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public AlpacaResponse turnCalibratorOff(@PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).turnCalibratorOff();
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("covercalibrator/{deviceNumber}/calibratoron")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public AlpacaResponse turnCalibratorOn(@PathParam("deviceNumber") int deviceNumber,
                                           @FormParam("ClientID") int clientID,
                                           @FormParam("ClientTransactionID") long clientTransactionID,
                                           @FormParam("Brightness") int brightness) {
        if (brightness < 0) {
            throw new InvalidValueException("Invalid altitude, must be no less than zero or greater than 90");
        }
        getDevice(deviceNumber, clientID).turnCalibratorOn(brightness);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("covercalibrator/{deviceNumber}/closecover")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public AlpacaResponse closeCover(@PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).closeCover();
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("covercalibrator/{deviceNumber}/haltcover")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public AlpacaResponse haltCover(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).haltCover();
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("covercalibrator/{deviceNumber}/opencover")
    public AlpacaResponse openCover(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).openCover();
        return new AlpacaResponse(clientTransactionID);
    }
}
