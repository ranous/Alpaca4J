package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.device.Device;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.model.DeviceType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Map;

@Path("setup/")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class SetupResource {
    @Inject
    DeviceManager deviceManager;
    @Inject @ConfigProperty(name = "alpaca.server_info.server-name")
    String serverName;
    @Inject @ConfigProperty(name = "alpaca.server_info.manufacturer")
    String manufacturer;
    @Inject @ConfigProperty(name = "alpaca.server_info.manufacturer-version")
    String manufacturerVersion;
    @Inject @ConfigProperty(name = "alpaca.server_info.location")
    String location;

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

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/")
    public String getServerConfiguration() {
        return "Alpaca Service " + serverName;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("v1/{deviceType}/{deviceNumber}/setup")
    public String getDeviceConfiguration(@PathParam("deviceType") String deviceType, @PathParam("deviceNumber") int deviceNumber) {
        Device device = getDevice(deviceNumber, deviceType);
        return device.setup();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("v1/{deviceType}/{deviceNumber}/setup")
    public void saveConfig(@PathParam("deviceType") String deviceType, @PathParam("deviceNumber") int deviceNumber, Map<String, String> updates) {
        Device device = getDevice(deviceNumber, deviceType);
        device.update(updates);
    }
}
