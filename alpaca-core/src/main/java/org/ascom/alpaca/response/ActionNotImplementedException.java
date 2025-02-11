package org.ascom.alpaca.response;

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
