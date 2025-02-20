package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.api.FilterWheel;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.device.FilterWheelDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.IntResponse;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.ListResponse;

@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@ApplicationScoped
public class FilterWheelResource implements FilterWheel {
    @Inject
    DeviceManager deviceManager;

    private FilterWheelDevice getDevice(int deviceID, int clientID) {
        FilterWheelDevice device = deviceManager.getDevice(deviceID, DeviceType.FilterWheel);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @GET
    @Path("filterwheel/{deviceNumber}/focusoffsets")
    public ListResponse<Integer> getFocusOffsets(@PathParam("deviceNumber") int deviceNumber,
                                                 @QueryParam("ClientID") int clientID,
                                                 @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getFocusOffsets(clientID));
    }

    @GET
    @Path("filterwheel/{deviceNumber}/names")
    public ListResponse<String> getFilterNames(@PathParam("deviceNumber") int deviceNumber,
                                               @QueryParam("ClientID") int clientID,
                                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getFilterNames(clientID));
    }

    @GET
    @Path("filterwheel/{deviceNumber}/position")
    public IntResponse getPosition(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getPosition(clientID));
    }

    @PUT
    @Path("filterwheel/{deviceNumber}/position")
    public AlpacaResponse setPosition(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID,
                                      @QueryParam("Position") int position) {
        if (position < 0) {
            throw new InvalidValueException("Filter positions cannot be less than zero");
        }
        getDevice(deviceNumber, clientID).setPosition(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }
}
