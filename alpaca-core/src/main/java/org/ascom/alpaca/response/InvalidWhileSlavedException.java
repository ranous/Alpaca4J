package org.ascom.alpaca.response;

@SuppressWarnings("unused")
public class InvalidWhileSlavedException extends AlpacaException {
    public static final int ERROR_CODE = ErrorCode.InvalidWhileSlaved.getCode();

    public InvalidWhileSlavedException(long clientTransactionID) {
        super(clientTransactionID, ERROR_CODE);
    }

    public InvalidWhileSlavedException(String message) {
        super(ERROR_CODE, message);
    }

    public InvalidWhileSlavedException(long clientTransactionID, String message) {
        super(clientTransactionID, ERROR_CODE, message);
    }
}
