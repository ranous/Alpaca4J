package org.ascom.alpaca.webservices;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.device.SwitchDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@ApplicationScoped
public class SwitchResource {
    private static final Logger log = LoggerFactory.getLogger(SwitchResource.class);
    @Inject
    DeviceManager deviceManager;

    private SwitchDevice getDevice(int deviceID,
                                   int clientID) {
        SwitchDevice device = deviceManager.getDevice(deviceID, DeviceType.Switch);
        device.checkConnectionStatus(clientID);
        return device;
    }

    private void checkSwitchID(int id,
                               int deviceNumber,
                               int clientID) {
        if (id < 0 || id > getDevice(deviceNumber, clientID).getMaxSwitch(clientID) - 1) {
            throw new InvalidValueException("The switch id value of " + id + " is invalid");
        }
    }

    @GET
    @Path("/switch/{deviceNumber}/canasync")
    public BooleanResponse canAsync(@PathParam("deviceNumber") Integer deviceNumber,
                                    @QueryParam("Id") Integer id,
                                    @QueryParam("ClientID") Integer clientID,
                                    @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new BooleanResponse(clientTransactionID, getDevice(deviceNumber, clientID).canAsync(id, clientID));
    }

    @GET
    @Path("/switch/{deviceNumber}/canwrite")
    public BooleanResponse canWrite(@PathParam("deviceNumber") Integer deviceNumber,
                                    @QueryParam("Id") Integer id,
                                    @QueryParam("ClientID") Integer clientID,
                                    @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new BooleanResponse(clientTransactionID, getDevice(deviceNumber, clientID).canWrite(id, clientID));
    }

    @GET
    @Path("/switch/{deviceNumber}/getswitchdescription")
    public StringResponse getSwitchDescription(@PathParam("deviceNumber") Integer deviceNumber,
                                               @QueryParam("Id") Integer id,
                                               @QueryParam("ClientID") Integer clientID,
                                               @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new StringResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchDescription(id, clientID));
    }

    @GET
    @Path("/switch/{deviceNumber}/getswitch")
    public BooleanResponse getSwitchState(@PathParam("deviceNumber") Integer deviceNumber,
                                          @QueryParam("Id") Integer id,
                                          @QueryParam("ClientID") Integer clientID,
                                          @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new BooleanResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchState(id, clientID));
    }

    @GET
    @Path("/switch/{deviceNumber}/getswitchname")
    public StringResponse getSwitchName(@PathParam("deviceNumber") Integer deviceNumber,
                                        @QueryParam("Id") Integer id,
                                        @QueryParam("ClientID") Integer clientID,
                                        @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new StringResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchName(id, clientID));
    }

    @GET
    @Path("/switch/{deviceNumber}/maxswitch")
    public IntResponse getMaxSwitch(@PathParam("deviceNumber") Integer deviceNumber,
                                    @QueryParam("ClientID") Integer clientID,
                                    @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        return new IntResponse(clientTransactionID, getDevice(deviceNumber, clientID).getMaxSwitch(clientID));
    }

    @GET
    @Path("/switch/{deviceNumber}/getswitchvalue")
    public DoubleResponse getSwitchValue(@PathParam("deviceNumber") Integer deviceNumber,
                                         @QueryParam("Id") Integer id,
                                         @QueryParam("ClientID") Integer clientID,
                                         @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new DoubleResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchValue(id, clientID));
    }

    @GET
    @Path("/switch/{deviceNumber}/minswitchvalue")
    public DoubleResponse getMinSwitchValue(@PathParam("deviceNumber") Integer deviceNumber,
                                            @QueryParam("Id") Integer id,
                                            @QueryParam("ClientID") Integer clientID,
                                            @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new DoubleResponse(clientTransactionID, getDevice(deviceNumber, clientID).getMinSwitchValue(id, clientID));
    }

    @GET
    @Path("/switch/{deviceNumber}/maxswitchvalue")
    public DoubleResponse getMaxSwitchValue(@PathParam("deviceNumber") Integer deviceNumber,
                                            @QueryParam("Id") Integer id,
                                            @QueryParam("ClientID") Integer clientID,
                                            @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new DoubleResponse(clientTransactionID, getDevice(deviceNumber, clientID).getMaxSwitchValue(id, clientID));

    }

    @GET
    @Path("/switch/{deviceNumber}/statechangecomplete")
    public BooleanResponse isStateChangeComplete(@PathParam("deviceNumber") Integer deviceNumber,
                                                 @QueryParam("Id") Integer id,
                                                 @QueryParam("ClientID") Integer clientID,
                                                 @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new BooleanResponse(clientTransactionID, getDevice(deviceNumber, clientID).isStateChangeComplete(id, clientID));
    }

    @PUT
    @Path("/switch/{deviceNumber}/cancelasync")
    public AlpacaResponse cancelAsync(@PathParam("deviceNumber") Integer deviceNumber,
                                      @FormParam("Id") int id,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).cancelAsync(id, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("/switch/{deviceNumber}/setasync")
    public AlpacaResponse setAsync(@PathParam("deviceNumber") Integer deviceNumber,
                                   @FormParam("Id") int id,
                                   @FormParam("State") boolean state,
                                   @FormParam("ClientID") int clientID,
                                   @FormParam("ClientTransactionID") long clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).setAsync(id, state, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("/switch/{deviceNumber}/setasyncvalue")
    public AlpacaResponse setAsyncValue(@PathParam("deviceNumber") Integer deviceNumber,
                                        @FormParam("Id") int id,
                                        @FormParam("Value") int value,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).setAsyncValue(id, value, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("/switch/{deviceNumber}/setswitchname")
    public AlpacaResponse setSwitchName(@PathParam("deviceNumber") Integer deviceNumber,
                                        @FormParam("Id") Integer id,
                                        @FormParam("Name") String name,
                                        @FormParam("ClientID") Integer clientID,
                                        @FormParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).setSwitchName(id, name, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("/switch/{deviceNumber}/setswitch")
    public AlpacaResponse setSwitchState(@PathParam("deviceNumber") Integer deviceNumber,
                                         @FormParam("Id") int id,
                                         @FormParam("State") boolean state,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).setSwitchState(id, state, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("/switch/{deviceNumber}/setswitchvalue")
    public AlpacaResponse setSwitchValue(@PathParam("deviceNumber") Integer deviceNumber,
                                         @FormParam("Id") int id,
                                         @FormParam("Value") double value,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID) {
        log.info("Setting switch value for device {}, id {} to {}", deviceNumber, id, value);
        checkSwitchID(id, deviceNumber, clientID);
        if (value < getDevice(deviceNumber, clientID).getMinSwitchValue(id, clientID) || value > getDevice(deviceNumber, clientID).getMaxSwitchValue(id, clientID)) {
            throw new InvalidValueException("The switch value is outside the switches range");
        }
        getDevice(deviceNumber, clientID).setSwitchValue(id, value, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("/switch/{deviceNumber}/switchstep")
    public DoubleResponse getSwitchStep(@PathParam("deviceNumber") Integer deviceNumber,
                                        @QueryParam("Id") Integer id,
                                        @QueryParam("ClientID") Integer clientID,
                                        @QueryParam("ClientTransactionID") Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new DoubleResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchStep(id, clientID));
    }
}
