package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The base class for all Alpaca responses.  It includes the attributes common to all Alpaca responses.
 */
@SuppressWarnings("unused")
public class AlpacaResponse {
    private long clientTransactionID = 0;
    private int serverTransactionID = 0;
    private int errorNumber = 0;
    private String errorMessage = "";

    public AlpacaResponse() {

    }

    public AlpacaResponse(long clientTransactionID) {
        this.clientTransactionID = clientTransactionID;
    }

    public AlpacaResponse(long clientTransactionID, int errorNumber, String errorMessage) {
        this.clientTransactionID = clientTransactionID;
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    public AlpacaResponse(long clientTransactionID, int serverTransactionID, int errorNumber, String errorMessage) {
        this.clientTransactionID = clientTransactionID;
        this.serverTransactionID = serverTransactionID;
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    /**
     * The client transaction ID of the request that caused this exception.
     * @return the clientTransactionID
     */
    @JsonProperty("ClientTransactionID")
    public long getClientTransactionID() {
        return clientTransactionID;
    }

    public void setClientTransactionID(long clientTransactionID) {
        this.clientTransactionID = clientTransactionID;
    }

    /**
     * The server transaction ID of the request that caused this exception. This id can be used
     * to correlate the response with the original request in order to troubleshoot issues.
     * @return the serverTransactionID
     */
    @JsonProperty("ServerTransactionID")
    public int getServerTransactionID() {
        return serverTransactionID;
    }

    public void setServerTransactionID(int serverTransactionID) {
        this.serverTransactionID = serverTransactionID;
    }

    /**
     * The Alpaca error number. The Alpaca error numbers are defined in the Alpaca specification.
     * The ErrorCode enum also provides a human-readable description of the error.
     * @return the errorNumber
     * @see org.ascom.alpaca.response.ErrorCode
     */
    @JsonProperty("ErrorNumber")
    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    /**
     * A human-readable description of the error.
     * @return
     */
    @JsonProperty("ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
