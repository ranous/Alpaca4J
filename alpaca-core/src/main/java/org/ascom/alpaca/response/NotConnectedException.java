package org.ascom.alpaca.response;

/**
 * The NotConnectedException is thrown when an Alpaca request is made to a device
 * that the client hasn't connected too.
 */
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
