package org.ascom.alpaca.impl.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.impl.DeviceManager;
import org.ascom.alpaca.device.DomeDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.*;

@Path("api/v1/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@ApplicationScoped
public class DomeResource {
    @Inject
    DeviceManager deviceManager;

    private DomeDevice getDevice(int deviceID,
                                 int clientID) {
        DomeDevice device = deviceManager.getDevice(deviceID, DeviceType.Dome);
        device.checkConnectionStatus(clientID);
        return device;
    }

    @GET
    @Path("dome/{deviceNumber}/altitude")
    public DoubleResponse getAltitude(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAltitude(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/athome")
    public BooleanResponse atHome(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).atHome(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/atpark")
    public BooleanResponse atPark(@PathParam("deviceNumber") int deviceNumber,
                                  @QueryParam("ClientID") int clientID,
                                  @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).atPark(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/azimuth")
    public DoubleResponse getAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new DoubleResponse(getDevice(deviceNumber, clientID).getAzimuth(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/canfindhome")
    public BooleanResponse canFindHome(@PathParam("deviceNumber") int deviceNumber,
                                       @QueryParam("ClientID") int clientID,
                                       @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canFindHome(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/canpark")
    public BooleanResponse canPark(@PathParam("deviceNumber") int deviceNumber,
                                   @QueryParam("ClientID") int clientID,
                                   @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canPark(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/cansetaltitude")
    public BooleanResponse canSetAltitude(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetAltitude(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/cansetazimuth")
    public BooleanResponse canSetAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetAzimuth(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/cansetpark")
    public BooleanResponse canSetPark(@PathParam("deviceNumber") int deviceNumber,
                                      @QueryParam("ClientID") int clientID,
                                      @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetPark(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/cansetshutter")
    public BooleanResponse canSetShutter(@PathParam("deviceNumber") int deviceNumber,
                                         @QueryParam("ClientID") int clientID,
                                         @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSetShutter(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/canslave")
    public BooleanResponse canSlave(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSlave(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/cansyncazimuth")
    public BooleanResponse canSyncAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                          @QueryParam("ClientID") int clientID,
                                          @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).canSyncAzimuth(clientID));
    }

    @GET
    @Path("dome/{deviceNumber}/shutterstatus")
    public IntResponse getShutterStatus(@PathParam("deviceNumber") int deviceNumber,
                                        @QueryParam("ClientID") int clientID,
                                        @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getShutterStatus(clientID).ordinal());
    }

    @GET
    @Path("dome/{deviceNumber}/slaved")
    public BooleanResponse isSlaved(@PathParam("deviceNumber") int deviceNumber,
                                    @QueryParam("ClientID") int clientID,
                                    @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isSlaved(clientID));
    }

    @PUT
    @Path("dome/{deviceNumber}/slaved")
    public AlpacaResponse setSlaved(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID,
                                    @FormParam("Slaved") boolean slaved) {
        getDevice(deviceNumber, clientID).setSlaved(clientID, slaved);
        return new AlpacaResponse(clientTransactionID);
    }

    @GET
    @Path("dome/{deviceNumber}/slewing")
    public BooleanResponse isSlewing(@PathParam("deviceNumber") int deviceNumber,
                                     @QueryParam("ClientID") int clientID,
                                     @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new BooleanResponse(getDevice(deviceNumber, clientID).isSlewing(clientID));
    }

    @PUT
    @Path("dome/{deviceNumber}/abortslew")
    public AlpacaResponse abortSlew(@PathParam("deviceNumber") int deviceNumber,
                                    @FormParam("ClientID") int clientID,
                                    @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).abortSlew(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("dome/{deviceNumber}/closeshutter")
    public AlpacaResponse closeShutter(@PathParam("deviceNumber") int deviceNumber,
                                       @FormParam("ClientID") int clientID,
                                       @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).closeShutter(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("dome/{deviceNumber}/findhome")
    public AlpacaResponse findHome(@PathParam("deviceNumber") int deviceNumber,
                                   @FormParam("ClientID") int clientID,
                                   @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).findHome(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("dome/{deviceNumber}/openshutter")
    public AlpacaResponse openShutter(@PathParam("deviceNumber") int deviceNumber,
                                      @FormParam("ClientID") int clientID,
                                      @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).openShutter(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("dome/{deviceNumber}/park")
    public AlpacaResponse park(@PathParam("deviceNumber") int deviceNumber,
                               @FormParam("ClientID") int clientID,
                               @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).park(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("dome/{deviceNumber}/setpark")
    public AlpacaResponse setPark(@PathParam("deviceNumber") int deviceNumber,
                                  @FormParam("ClientID") int clientID,
                                  @FormParam("ClientTransactionID") long clientTransactionID) {
        getDevice(deviceNumber, clientID).setPark(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("dome/{deviceNumber}/slewtoaltitude")
    public AlpacaResponse slewToAltitude(@PathParam("deviceNumber") int deviceNumber,
                                         @FormParam("ClientID") int clientID,
                                         @FormParam("ClientTransactionID") long clientTransactionID,
                                         @FormParam("Altitude") double altitude) {
        if (altitude < 0 || altitude > 90) {
            throw new InvalidValueException("Invalid altitude, must be no less than zero or greater than 90");
        }
        getDevice(deviceNumber, clientID).slewToAltitude(clientID, altitude);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("dome/{deviceNumber}/slewtoazimuth")
    public AlpacaResponse slewToAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID,
                                        @FormParam("Azimuth") double azimuth) {
        if (azimuth < 0 || azimuth > 360) {
            throw new InvalidValueException("Invalid azimuth, must be no less than zero or greater than 360");
        }
        getDevice(deviceNumber, clientID).slewToAzimuth(clientID, azimuth);
        return new AlpacaResponse(clientTransactionID);
    }

    @PUT
    @Path("dome/{deviceNumber}/synctoazimuth")
    public AlpacaResponse syncToAzimuth(@PathParam("deviceNumber") int deviceNumber,
                                        @FormParam("ClientID") int clientID,
                                        @FormParam("ClientTransactionID") long clientTransactionID,
                                        @FormParam("Azimuth") double azimuth) {
        if (azimuth < 0 || azimuth > 360) {
            throw new InvalidValueException("Invalid azimuth, must be no less than zero or greater than 360");
        }
        getDevice(deviceNumber, clientID).syncToAzimuth(clientID, azimuth);
        return new AlpacaResponse(clientTransactionID);
    }
}
