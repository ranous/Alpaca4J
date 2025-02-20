package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.api.Focuser;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.device.FocuserDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.BooleanResponse;
import org.ascom.alpaca.response.DoubleResponse;
import org.ascom.alpaca.response.IntResponse;

@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@ApplicationScoped
public class FocuserResource implements Focuser {
    @Inject
    DeviceManager deviceManager;

    private FocuserDevice getDevice(int deviceID,
                                    int clientID) {
        FocuserDevice device = deviceManager.getDevice(deviceID, DeviceType.Focuser);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @GET
    @Path("focuser/{deviceNumber}/absolute")
    public BooleanResponse canAbsoluteFocus(@PathParam("deviceNumber") int deviceNumber,
                                            @QueryParam("ClientID") int clientID,
                                            @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canAbsoluteFocus(clientID));
    }

    @GET
    @Path("focuser/{deviceNumber}/ismoving")
    public BooleanResponse isMoving(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isMoving(clientID));
    }

    @GET
    @Path("focuser/{deviceNumber}/maxincrement")
    public IntResponse getMaxIncrement(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxIncrement(clientID));
    }

    @GET
    @Path("focuser/{deviceNumber}/maxstep")
    public IntResponse getMaxStep(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getMaxStep(clientID));
    }

    @GET
    @Path("focuser/{deviceNumber}/position")
    public IntResponse getPosition(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getPosition(clientID));
    }

    @GET
    @Path("focuser/{deviceNumber}/stepsize")
    public DoubleResponse getStepSize(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getStepSize(clientID));
    }

    @GET
    @Path("focuser/{deviceNumber}/tempcomp")
    public BooleanResponse isTemperatureCompensating(@PathParam("deviceNumber") int deviceNumber,
                                                     @QueryParam("ClientID") int clientID,
                                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isTemperatureCompensating(clientID));
    }

    public AlpacaResponse setTemperatureCompensation(int deviceNumber,
                                                     boolean tempCompState,
                                                     int clientID,
                                                     long clientTransactionID) {
        getDevice(deviceNumber, clientID).setTemperatureCompensation(clientID, tempCompState);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("focuser/{deviceNumber}/tempcompavailable")
    public BooleanResponse hasTemperatureCompensation(@PathParam("deviceNumber") int deviceNumber,
                                                      @QueryParam("ClientID") int clientID,
                                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).hasTemperatureCompensation(clientID));
    }

    @GET
    @Path("focuser/{deviceNumber}/temperature")
    public DoubleResponse getTemperature(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getTemperature(clientID));
    }

    @PUT
    @Path("focuser/{deviceNumber}/halt")
    public AlpacaResponse haltFocuser(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).haltFocuser(clientID);
        return new AlpacaResponse(clientTransactionID);

    }

    @PUT
    @Path("focuser/{deviceNumber}/move")
    public AlpacaResponse moveToPosition(@PathParam("deviceNumber") int deviceNumber,
                                         @FormParam("Position") int position,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).moveToPosition(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }
}
