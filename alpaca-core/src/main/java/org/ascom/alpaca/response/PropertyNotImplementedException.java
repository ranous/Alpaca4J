package org.ascom.alpaca.response;

@SuppressWarnings("unused")
public class PropertyNotImplementedException extends AlpacaException {
    public PropertyNotImplementedException(long clientTransactionID, String message) {
        super(ErrorCode.PropertyNotImplemented.getCode(), message);
    }

    public PropertyNotImplementedException(String message) {
        super(ErrorCode.PropertyNotImplemented.getCode(), message);
    }
}
