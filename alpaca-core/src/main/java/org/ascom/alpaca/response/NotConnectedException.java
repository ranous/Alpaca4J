package org.ascom.alpaca.response;

@SuppressWarnings("unused")
public class NotConnectedException extends AlpacaException {
    public static final int ERROR_CODE = ErrorCode.NotConnected.getCode();

    public NotConnectedException(long clientTransactionID) {
        super(clientTransactionID, ERROR_CODE);
    }

    public NotConnectedException(String message) {
        super(ERROR_CODE, message);
    }

    public NotConnectedException(long clientTransactionID, String message) {
        super(clientTransactionID, ERROR_CODE, message);
    }
}
