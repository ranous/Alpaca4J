package org.ascom.alpaca.webservices;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.BadRequestException;
import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.Common;
import org.ascom.alpaca.device.Device;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.StateValue;
import org.ascom.alpaca.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@Singleton
public class CommonDeviceResource implements Common {
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

    @Override
    public StringResponse executeAction(String deviceType,
                                        int deviceNumber,
                                        int clientID,
                                        long clientTransactionID,
                                        String action,
                                        String parameters) {
        String response = getDevice(deviceNumber, deviceType).executeAction(clientID, action, parameters);
        return new StringResponse(clientTransactionID, response);
    }

    @Override
    public AlpacaResponse connect(String deviceType,
                                  int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {
        log.info("Connecting device to client {}", clientID);
        getDevice(deviceNumber, deviceType).connect(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public AlpacaResponse disconnect(String deviceType,
                                     int deviceNumber,
                                     int clientID,
                                     long clientTransactionID) {
        log.info("Disconnecting device from client {}", clientID);
        getDevice(deviceNumber, deviceType).disconnect(clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public BooleanResponse isConnecting(String deviceType,
                                        int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {
        Device device = getDevice(deviceNumber, deviceType);
        return new BooleanResponse(clientTransactionID, device.isConnecting(clientID));
    }

    @Override
    public BooleanResponse isConnected(String deviceType,
                                       int deviceNumber,
                                       int clientID,
                                       long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new BooleanResponse(clientTransactionID, device.isConnected(clientID));
    }

    @Override
    public AlpacaResponse setConnectedState(String deviceType,
                                            int deviceNumber,
                                            int clientID,
                                            long clientTransactionID,
                                            Boolean connectedState) {
        if (connectedState == null) {
            throw new BadRequestException("The Connected parameter missing");
        }
        log.info("Setting device state to {}", connectedState);
        Device device = getDevice(deviceNumber, deviceType);
        device.setConnectedState(clientID, connectedState);
        return new AlpacaResponse(clientTransactionID);
    }

    @Override
    public StringResponse getDescription(String deviceType,
                                         int deviceNumber,
                                         int clientID,
                                         long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        device.checkConnectionStatus(clientID);
        return new StringResponse(clientTransactionID, device.getDescription(clientID));
    }

    @Override
    public ListResponse<StateValue> getDeviceState(String deviceType,
                                                   int deviceNumber,
                                                   int clientID,
                                                   long clientTransactionID) {
        Device device = getDevice(deviceNumber, deviceType);
        return new ListResponse<>(clientTransactionID, device.getDeviceState(clientID));
    }

    @Override
    public StringResponse getDriverInfo(String deviceType,
                                        int deviceNumber,
                                        int clientID,
                                        long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new StringResponse(clientTransactionID, device.getDriverInfo(clientID));
    }

    @Override
    public StringResponse getDriverVersion(String deviceType,
                                           int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new StringResponse(clientTransactionID, device.getDriverVersion(clientID));
    }

    @Override
    public IntResponse getInterfaceVersion(String deviceType,
                                           int deviceNumber,
                                           int clientID,
                                           long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new IntResponse(clientTransactionID, device.getInterfaceVersion(clientID));
    }

    @Override
    public StringResponse getName(String deviceType,
                                  int deviceNumber,
                                  int clientID,
                                  long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new StringResponse(clientTransactionID, device.getName(clientID));
    }

    @Override
    public ListResponse<String> getSupportedActions(String deviceType,
                                                  int deviceNumber,
                                                  int clientID,
                                                  long clientTransactionID) {

        Device device = getDevice(deviceNumber, deviceType);
        return new ListResponse<>(clientTransactionID, device.getSupportedActions(clientID));
    }
}
