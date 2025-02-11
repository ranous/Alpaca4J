package org.ascom.alpaca.response;

@SuppressWarnings("unused")
public class ValueNotSetException extends AlpacaException {
    public static final int ERROR_CODE = ErrorCode.ValueNotSet.getCode();

    public ValueNotSetException(long clientTransactionID) {
        super(clientTransactionID, ERROR_CODE);
    }

    public ValueNotSetException(long clientTransactionID, String message) {
        super(clientTransactionID, ERROR_CODE, message);
    }
}
