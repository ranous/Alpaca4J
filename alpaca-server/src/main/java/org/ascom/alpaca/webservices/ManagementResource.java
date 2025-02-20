package org.ascom.alpaca.webservices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.api.Management;
import org.ascom.alpaca.device.Device;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.ServerInfo;
import org.ascom.alpaca.response.ListResponse;
import org.ascom.alpaca.response.ServerInfoResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@Path("/management")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ManagementResource implements Management {
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

    @Override
    @GET
    @Path("apiversions")
    public ListResponse<Integer> getApiVersions(@QueryParam("ClientID") int clientID,
                                                @QueryParam("ClientTransactionID") long clientTransactionID) {
        return new ListResponse<>(clientTransactionID, List.of(1));
    }

    @Override
    @GET
    @Path("v1/configureddevices")
    public ListResponse<DeviceDescriptor> getConfiguredDevices(@QueryParam("ClientID") int clientID,
                                                               @QueryParam("ClientTransactionID") long clientTransactionID) {
        ListResponse<DeviceDescriptor> response = new ListResponse<>(clientTransactionID);
        for (Device device : deviceManager.getDevices()) {
            response.addValue(device.getDeviceDescriptor());
        }
        return response;
    }

    @GET
    @Path("v1/description")
    public ServerInfoResponse getDescription(@QueryParam("ClientID") int clientID,
                                             @QueryParam("ClientTransactionID") long clientTransactionID) {

        return new ServerInfoResponse(clientTransactionID, new ServerInfo(serverName, manufacturer, manufacturerVersion, location));
    }
}
