package org.ascom.alpaca.response;

/**
 * The PropertyNotImplementedException is thrown when an Alpaca request is made to a device
 * property that is not implemented by this device.  Not all properties are implemented by all devices.
 * The Alpaca spec defines a set of properties that are required by all devices.
 */
@SuppressWarnings("unused")
public class PropertyNotImplementedException extends AlpacaException {
    public PropertyNotImplementedException(long clientTransactionID, String message) {
        super(ErrorCode.PropertyNotImplemented.getCode(), message);
    }

    public PropertyNotImplementedException(String message) {
        super(ErrorCode.PropertyNotImplemented.getCode(), message);
    }
}
