package org.ascom.alpaca.webservices;

import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.FilterWheel;
import org.ascom.alpaca.device.FilterWheelDevice;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class FilterWheelResource implements FilterWheel {
    @Inject
    DeviceManager deviceManager;

    private FilterWheelDevice getDevice(int deviceID, int clientID) {
        FilterWheelDevice device = deviceManager.getDevice(deviceID, DeviceType.FilterWheel);
        device.checkConnectionStatus(clientID);
        return device;
    }

    public ListResponse<Integer> getFocusOffsets(int deviceNumber,
                                                 int clientID,
                                                 long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getFocusOffsets(clientID));
    }

    public ListResponse<String> getFilterNames(int deviceNumber,
                                               int clientID,
                                               long clientTransactionID) {
        return new ListResponse<>(getDevice(deviceNumber, clientID).getFilterNames(clientID));
    }

    public IntResponse getPosition(int deviceNumber,
                                   int clientID,
                                   long clientTransactionID) {
        return new IntResponse(getDevice(deviceNumber, clientID).getPosition(clientID));
    }

    public AlpacaResponse setPosition(int deviceNumber,
                                      int clientID,
                                      long clientTransactionID,
                                      int position) {
        if (position < 0) {
            throw new InvalidValueException("Filter positions cannot be less than zero");
        }
        getDevice(deviceNumber, clientID).setPosition(clientID, position);
        return new AlpacaResponse(clientTransactionID);
    }
}
