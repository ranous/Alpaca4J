package org.ascom.alpaca.response;

@SuppressWarnings("unused")
public class InvalidWhileParkedException extends AlpacaException {
    public static final int ERROR_CODE = ErrorCode.InvalidWhileParked.getCode();

    public InvalidWhileParkedException(long clientTransactionID) {
        super(clientTransactionID, ERROR_CODE);
    }

    public InvalidWhileParkedException(String message) {
        super(ERROR_CODE, message);
    }

    public InvalidWhileParkedException(long clientTransactionID, String message) {
        super(clientTransactionID, ERROR_CODE, message);
    }
}
