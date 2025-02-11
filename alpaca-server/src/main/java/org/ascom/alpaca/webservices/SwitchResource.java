package org.ascom.alpaca.webservices;


import org.ascom.alpaca.device.DeviceManager;
import org.ascom.alpaca.api.Switch;
import org.ascom.alpaca.device.SwitchDevice;
import org.ascom.alpaca.model.*;
import org.ascom.alpaca.response.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SwitchResource implements Switch {
    private static final Logger log = LoggerFactory.getLogger(SwitchResource.class);
    @Inject
    DeviceManager deviceManager;

    private SwitchDevice getDevice(int deviceID, int clientID) {
        SwitchDevice device = deviceManager.getDevice(deviceID, DeviceType.Switch);
        device.checkConnectionStatus(clientID);
        return device;
    }

    private void checkSwitchID(int id, int deviceNumber, int clientID) {
        if (id < 0 || id > getDevice(deviceNumber, clientID).getMaxSwitch(clientID)-1) {
            throw new InvalidValueException("The switch id value of " + id + " is invalid");
        }
    }

    public BooleanResponse canAsync(Integer deviceNumber,
                                    Integer id,
                                    Integer clientID,
                                    Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new BooleanResponse(clientTransactionID, getDevice(deviceNumber, clientID).canAsync(id, clientID));
    }

    public BooleanResponse canWrite(Integer deviceNumber,
                                    Integer id,
                                    Integer clientID,
                                    Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new BooleanResponse(clientTransactionID, getDevice(deviceNumber, clientID).canWrite(id, clientID));
    }

    public StringResponse getSwitchDescription(Integer deviceNumber,
                                               Integer id,
                                               Integer clientID,
                                               Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new StringResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchDescription(id, clientID));
    }

    public BooleanResponse getSwitchState(Integer deviceNumber,
                                                         Integer id,
                                                         Integer clientID,
                                                         Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new BooleanResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchState(id, clientID));
    }

    public StringResponse getSwitchName(Integer deviceNumber,
                                        Integer id,
                                        Integer clientID,
                                        Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new StringResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchName(id, clientID));
    }

    public IntResponse getMaxSwitch(Integer deviceNumber,
                                    Integer clientID,
                                    Integer clientTransactionID) {
        return new IntResponse(clientTransactionID, getDevice(deviceNumber, clientID).getMaxSwitch(clientID));
    }

    public DoubleResponse getSwitchValue(Integer deviceNumber,
                                         Integer id,
                                         Integer clientID,
                                         Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new DoubleResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchValue(id, clientID));
    }

    public DoubleResponse getMinSwitchValue(Integer deviceNumber,
                                            Integer id,
                                            Integer clientID,
                                            Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new DoubleResponse(clientTransactionID, getDevice(deviceNumber, clientID).getMinSwitchValue(id, clientID));
    }

    public DoubleResponse getMaxSwitchValue(Integer deviceNumber,
                                            Integer id,
                                            Integer clientID,
                                            Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new DoubleResponse(clientTransactionID, getDevice(deviceNumber, clientID).getMaxSwitchValue(id, clientID));

    }

    public BooleanResponse isStateChangeComplete(Integer deviceNumber,
                                                 Integer id,
                                                 Integer clientID,
                                                 Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new BooleanResponse(clientTransactionID, getDevice(deviceNumber, clientID).isStateChangeComplete(id, clientID));
    }

    public AlpacaResponse cancelAsync(Integer deviceNumber,
                                      int id,
                                      int clientID,
                                      long clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).cancelAsync(id, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse setAsync(Integer deviceNumber,
                                   int id,
                                   boolean state,
                                   int clientID,
                                   long clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).setAsync(id, state, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse setAsyncValue(Integer deviceNumber,
                                        int id,
                                        int value,
                                        int clientID,
                                        long clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).setAsyncValue(id, value, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse setSwitchName(Integer deviceNumber,
                                        Integer id,
                                        String name,
                                        Integer clientID,
                                        Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).setSwitchName(id, name, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse setSwitchState(Integer deviceNumber,
                                         int id,
                                         boolean state,
                                         int clientID,
                                         long clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        getDevice(deviceNumber, clientID).setSwitchState(id, state, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public AlpacaResponse setSwitchValue(Integer deviceNumber,
                                         int id,
                                         double value,
                                         int clientID,
                                         long clientTransactionID) {
        log.info("Setting switch value for device {}, id {} to {}", deviceNumber, id, value);
        checkSwitchID(id, deviceNumber, clientID);
        if (value < getDevice(deviceNumber, clientID).getMinSwitchValue(id, clientID) || value > getDevice(deviceNumber, clientID).getMaxSwitchValue(id, clientID)) {
            throw new InvalidValueException("The switch value is outside the switches range");
        }
        getDevice(deviceNumber, clientID).setSwitchValue(id, value, clientID);
        return new AlpacaResponse(clientTransactionID);
    }

    public DoubleResponse getSwitchStep(Integer deviceNumber,
                                        Integer id,
                                        Integer clientID,
                                        Integer clientTransactionID) {
        checkSwitchID(id, deviceNumber, clientID);
        return new DoubleResponse(clientTransactionID, getDevice(deviceNumber, clientID).getSwitchStep(id, clientID));
    }
}
