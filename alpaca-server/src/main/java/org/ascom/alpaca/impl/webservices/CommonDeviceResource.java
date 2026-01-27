package org.ascom.alpaca.impl.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.device.Device;
import org.ascom.alpaca.impl.DeviceManager;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@Path("api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@ApplicationScoped
public class CommonDeviceResource {
    private static final Logger log = LoggerFactory.getLogger(CommonDeviceResource.class);

    @Inject
    DeviceManager deviceManager;

    private Device getDevice(int deviceID, String deviceType) {
        if (deviceID < 0) {
            throw new BadRequestException("Device id cannot be negative: " + deviceID);
        }
        try {
            DeviceType type = DeviceType.fromType(deviceType);
            Device device = deviceManager.getDevice(deviceID, type);
            if (device == null) {
                throw new BadRequestException("Device id " + deviceID + " of type " + type + " not found");
            }
            return device;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Device type not valid: " + deviceType);
        }
    }

    protected void checkConnection(int clientID, long clientTransactionID) {
        throw new NotConnectedException(clientTransactionID, "Client " + clientID + " is not connected");
    }

    @PUT
    @Path("{deviceType}/{deviceNumber}/action")
    public StringResponse executeAction(@PathParam("deviceType") String deviceType,
                                        @PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID,
                                        @FormParam("Action") String action,
                                        @FormParam("Parameters") String parameters) {
        String response = getDevice(deviceNumber, deviceType).executeAction(clientID, action, parameters);
        return new StringResponse(clientTransactionID, response);
    }

    @PUT
    @Path("{deviceType}/{deviceNumber}/connect")
    public AlpacaResponse connect(@PathParam("deviceType") String deviceType,
                                  @PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID) {
        log.info("Connecting device to client {}", clientID);
        getDevice(deviceNumber, deviceType).connect(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("{deviceType}/{deviceNumber}/disconnect")
    public AlpacaResponse disconnect(@PathParam("deviceType") String deviceType,
                                     @PathParam("deviceNumber") int deviceNumber,
                                     @FormParam("ClientID") int clientID,
                                     @FormParam("ClientTransactionID") long clientTransactionID) {
        log.info("Disconnecting device from client {}", clientID);
        getDevice(deviceNumber, deviceType).disconnect(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/connecting")
    public BooleanResponse isConnecting(@PathParam("deviceType") String deviceType,
                                        @PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        Device device = getDevice(deviceNumber, deviceType);
        return new BooleanResponse(clientTransactionID, device.isConnecting(clientID));
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/connected")
    public BooleanResponse isConnected(@PathParam("deviceType") String deviceType,
                                       @PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new BooleanResponse(clientTransactionID, device.isConnected(clientID));
    }

    @PUT
    @Path("{deviceType}/{deviceNumber}/connected")
    public AlpacaResponse setConnectedState(@PathParam("deviceType") String deviceType,
                                            @PathParam("deviceNumber") int deviceNumber,
                                            @FormParam("ClientID") int clientID,
                                            @FormParam("ClientTransactionID") long clientTransactionID,
                                            @FormParam("Connected") Boolean connectedState) {
        if (connectedState == null) {
            throw new BadRequestException("The Connected parameter missing");
        }
        log.info("Setting device state to {}", connectedState);
        Device device = getDevice(deviceNumber, deviceType);
        device.setConnectedState(clientID, connectedState);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/description")
    public StringResponse getDescription(@PathParam("deviceType") String deviceType,
                                         @PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        device.checkConnectionStatus(clientID);
        return new StringResponse(clientTransactionID, device.getDescription(clientID));
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/devicestate")
    public ListResponse<StateValue> getDeviceState(@PathParam("deviceType") String deviceType,
                                                   @PathParam("deviceNumber") int deviceNumber,
                                                   @QueryParam("ClientID") int clientID,
                                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        Device device = getDevice(deviceNumber, deviceType);
        return new ListResponse<>(clientTransactionID, device.getDeviceState(clientID));
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/driverinfo")
    public StringResponse getDriverInfo(@PathParam("deviceType") String deviceType,
                                        @PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new StringResponse(clientTransactionID, device.getDriverInfo(clientID));
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/driverversion")
    public StringResponse getDriverVersion(@PathParam("deviceType") String deviceType,
                                           @PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new StringResponse(clientTransactionID, device.getDriverVersion(clientID));
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/interfaceversion")
    public IntResponse getInterfaceVersion(@PathParam("deviceType") String deviceType,
                                           @PathParam("deviceNumber") int deviceNumber,
                                           @QueryParam("ClientID") int clientID,
                                           @QueryParam("ClientTransactionID") long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new IntResponse(clientTransactionID, device.getInterfaceVersion(clientID));
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/name")
    public StringResponse getName(@PathParam("deviceType") String deviceType,
                                  @PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new StringResponse(clientTransactionID, device.getName(clientID));
    }

    @GET
    @Path("{deviceType}/{deviceNumber}/supportedactions")
    public ListResponse<String> getSupportedActions(@PathParam("deviceType") String deviceType,
                                                    @PathParam("deviceNumber") int deviceNumber,
                                                    @QueryParam("ClientID") int clientID,
                                                    @QueryParam("ClientTransactionID") long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new ListResponse<>(clientTransactionID, device.getSupportedActions(clientID));
    }
}
