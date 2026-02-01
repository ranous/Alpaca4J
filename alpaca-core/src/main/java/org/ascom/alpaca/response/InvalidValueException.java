package org.ascom.alpaca.response;

/**
 * Exception thrown when an invalid value is provided to a device.
 */
@SuppressWarnings("unused")
public class InvalidValueException extends AlpacaException {
    public static final int ERROR_CODE = ErrorCode.InvalidValue.getCode();

    public InvalidValueException(long clientTransactionID) {
        super(clientTransactionID, ERROR_CODE);
    }

    public InvalidValueException(String message) {
        super(ERROR_CODE, message);
    }

    public InvalidValueException(long clientTransactionID, String message) {
        super(clientTransactionID, ERROR_CODE, message);
    }
}
