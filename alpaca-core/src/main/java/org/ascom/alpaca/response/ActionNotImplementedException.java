package org.ascom.alpaca.response;

/**
 * Exception thrown when trying to invoke a device custom action that is not implemented by the device.
 */
@SuppressWarnings("unused")
public class ActionNotImplementedException extends AlpacaException {
    public static final int ERROR_CODE = ErrorCode.ActionNotImplemented.getCode();

    public ActionNotImplementedException(long clientTransactionID) {
        super(clientTransactionID, ERROR_CODE);
    }

    public ActionNotImplementedException(String message) {
        super(ERROR_CODE, message);
    }

    public ActionNotImplementedException(long clientTransactionID, String message) {
        super(clientTransactionID, ERROR_CODE, message);
    }
}
