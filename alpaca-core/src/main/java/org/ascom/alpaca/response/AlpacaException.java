package org.ascom.alpaca.response;

@SuppressWarnings("unused")
public class AlpacaException extends RuntimeException {
    private final int errorNumber;
    private long clientTransactionID;

    public AlpacaException(long clientTransactionID, int errorNumber) {
        super();
        this.clientTransactionID = clientTransactionID;
        this.errorNumber = errorNumber;
    }

    public AlpacaException(int errorNumber, String message) {
        super(message);
        this.errorNumber = errorNumber;
    }

    public AlpacaException(long clientTransactionID, int errorNumber, String message) {
        super(message);
        this.clientTransactionID = clientTransactionID;
        this.errorNumber = errorNumber;
    }

    public long getClientTransactionID() {
        return clientTransactionID;
    }

    public int getErrorNumber() {
        return errorNumber;
    }
}
