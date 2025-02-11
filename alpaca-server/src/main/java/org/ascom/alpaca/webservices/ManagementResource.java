package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.Management;
import org.ascom.alpaca.device.Device;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.response.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ManagementResource implements Management {
    @Inject
    DeviceManager deviceManager;
    private ServerInfo serverInfo = new ServerInfo();

    @Inject
    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    @Override
    public ListResponse<Integer> getApiVersions(int clientID,
                                                long clientTransactionID) {
        return new ListResponse<>(clientTransactionID, List.of(1));
    }

    @Override
    public ListResponse<DeviceDescriptor> getConfiguredDevices(int clientID,
                                                               long clientTransactionID) {
        ListResponse<DeviceDescriptor> response = new ListResponse<>(clientTransactionID);
        for (Device device : deviceManager.getDevices()) {
            response.addValue(device.getDeviceDescriptor());
        }
        return response;
    }

    public ServerInfoResponse getDescription(int clientID,
                                             long clientTransactionID) {

        return new ServerInfoResponse(clientTransactionID, serverInfo);
    }
}
