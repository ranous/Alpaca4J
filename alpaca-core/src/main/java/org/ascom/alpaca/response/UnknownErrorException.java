package org.ascom.alpaca.response;

public class UnknownErrorException extends AlpacaException {

    public UnknownErrorException(long clientTransactionID, int errorCode, String message) {
        super(clientTransactionID, errorCode, "Received a response with an unknown error code - " + message);
    }
}
