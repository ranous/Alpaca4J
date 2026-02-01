package org.ascom.alpaca.response;

/**
 * The base class for all Alpaca exceptions.
 */
@SuppressWarnings("unused")
public abstract class AlpacaException extends RuntimeException {
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

    /**
     * The client transaction ID of the request that caused this exception.
     * @return the clientTransactionID
     */
    public long getClientTransactionID() {
        return clientTransactionID;
    }

    /**
     * The Alpaca error number. The Alpaca error numbers are defined in the Alpaca specification.
     * The ErrorCode enum also provides a human-readable description of the error.
     * @return the errorNumber
     * @see org.ascom.alpaca.response.ErrorCode
     */
    public int getErrorNumber() {
        return errorNumber;
    }
}
