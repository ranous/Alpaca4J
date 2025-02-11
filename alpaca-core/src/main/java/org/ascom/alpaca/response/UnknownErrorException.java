package org.ascom.alpaca.response;

public class UnknownErrorException extends AlpacaException {

    public UnknownErrorException(long clientTransactionID, int errorCode, String message) {
        super(clientTransactionID, errorCode, "Unknown error type received - " + message);
    }
}
