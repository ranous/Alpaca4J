package org.ascom.alpaca.response;

public class ServerException extends AlpacaException {
    public static final int ERROR_CODE = ErrorCode.ServerError.getCode();

    public ServerException(long clientTransactionID) {
        super(clientTransactionID, ERROR_CODE);
    }

    public ServerException(String message) {
        super(ERROR_CODE, message);
    }

    public ServerException(long clientTransactionID, String message) {
        super(clientTransactionID, ERROR_CODE, message);
    }
}
