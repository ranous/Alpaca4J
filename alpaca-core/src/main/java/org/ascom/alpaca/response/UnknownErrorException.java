package org.ascom.alpaca.response;

/**
 * The UnknownErrorException is thrown when the server returns an error code not defined
 * in the Alpaca specification.
 */
public class UnknownErrorException extends AlpacaException {

    public UnknownErrorException(long clientTransactionID, int errorCode, String message) {
        super(clientTransactionID, errorCode, "Received a response with an unknown error code - " + message);
    }

    public UnknownErrorException(int errorCode, String message) {
        super(errorCode, "Received a response with an unknown error code - " + message);
    }
}
