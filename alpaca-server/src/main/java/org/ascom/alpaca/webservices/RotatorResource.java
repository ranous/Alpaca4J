package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.api.Rotator;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.device.RotatorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.InvalidValueException;

@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@ApplicationScoped
public class RotatorResource implements Rotator {
    @Inject
    DeviceManager deviceManager;

    private RotatorDevice getDevice(int deviceID,
                                    int clientID) {
        RotatorDevice device = deviceManager.getDevice(deviceID, DeviceType.Rotator);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @GET
    @Path("rotator/{deviceNumber}/canreverse")
    public BooleanResponse canReverse(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canReverse(clientID));
    }

    @GET
    @Path("rotator/{deviceNumber}/ismoving")
    public BooleanResponse isMoving(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isMoving(clientID));
    }

    @GET
    @Path("rotator/{deviceNumber}/mechanicalposition")
    public DoubleResponse getMechanicalPosition(@PathParam("deviceNumber") int deviceNumber,
                                                @QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getMechanicalPosition(clientID));
    }

    @GET
    @Path("rotator/{deviceNumber}/position")
    public DoubleResponse getPosition(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getPosition(clientID));
    }

    @GET
    @Path("rotator/{deviceNumber}/reverse")
    public BooleanResponse isReversed(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isReversed(clientID));
    }

    @PUT
    @Path("rotator/{deviceNumber}/reverse")
    public AlpacaResponse setReversed(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID,
                                      @FormParam("Slaved") boolean reversed) {
        getDevice(deviceNumber, clientID).setReversed(clientID, reversed);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("rotator/{deviceNumber}/stepsize")
    public DoubleResponse getStepSize(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getStepSize(clientID));
    }

    @GET
    @Path("rotator/{deviceNumber}/targetposition")
    public DoubleResponse getTargetPosition(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTargetPosition(clientID));
    }

    @PUT
    @Path("rotator/{deviceNumber}/halt")
    public AlpacaResponse halt(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).halt(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("rotator/{deviceNumber}/move")
    public AlpacaResponse move(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID,
                               double position) {
        if (position > 360) {
            throw new InvalidValueException("The position cannot be moved more than 360 degrees");
        }
        getDevice(deviceNumber, clientID).move(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("rotator/{deviceNumber}/moveabsolute")
    public AlpacaResponse moveAbsolute(@PathParam("deviceNumber") int deviceNumber,
                                       @FormParam("ClientID") int clientID,
                                       @FormParam("ClientTransactionID") long clientTransactionID,
                                       double position) {
        if (position < 0 || position > 360) {
            throw new InvalidValueException("Invalid position, must be no less than zero or greater than 360");
        }

        getDevice(deviceNumber, clientID).moveAbsolute(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("rotator/{deviceNumber}/movemechanical")
    public AlpacaResponse moveMechanical(@PathParam("deviceNumber") int deviceNumber,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID,
                                         double position) {
        if (position < 0 || position > 360) {
            throw new InvalidValueException("Invalid position, must be no less than zero or greater than 360");
        }
        getDevice(deviceNumber, clientID).moveMechanical(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("rotator/{deviceNumber}/sync")
    public AlpacaResponse sync(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID,
                               double position) {
        if (position < 0 || position > 360) {
            throw new InvalidValueException("Invalid position, must be no less than zero or greater than 360");
        }
        getDevice(deviceNumber, clientID).sync(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }
}
