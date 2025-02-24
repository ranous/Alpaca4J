package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.device.SafetyMonitorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.BooleanResponse;

@ApplicationScoped
@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
public class SafetyMonitorResource {
    @Inject
    DeviceManager deviceManager;

    private SafetyMonitorDevice getDevice(int deviceID, int clientID) {
        SafetyMonitorDevice device = deviceManager.getDevice(deviceID, DeviceType.SafetyMonitor);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @GET
    @Path("safetymonitor/{deviceNumber}/issafe")
    public BooleanResponse isSafe(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isSafe(clientID));
    }
}
