package org.ascom.alpaca.response;

/**
 * Exception thrown when an invalid operation is attempted on a device.
 */
@SuppressWarnings("unused")
public class InvalidOperationException extends AlpacaException {
    public static final int ERROR_CODE = ErrorCode.InvalidOperation.getCode();

    public InvalidOperationException(long clientTransactionID) {
        super(clientTransactionID, ERROR_CODE);
    }

    public InvalidOperationException(String message) {
        super(ERROR_CODE, message);
    }

    public InvalidOperationException(long clientTransactionID, String message) {
        super(clientTransactionID, ERROR_CODE, message);
    }
}
