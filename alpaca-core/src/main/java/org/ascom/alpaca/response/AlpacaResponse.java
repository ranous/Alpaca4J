package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonProperty("ClientTransactionID")
    public long getClientTransactionID() {
        return clientTransactionID;
    }

    public void setClientTransactionID(long clientTransactionID) {
        this.clientTransactionID = clientTransactionID;
    }

    @JsonProperty("ServerTransactionID")
    public int getServerTransactionID() {
        return serverTransactionID;
    }

    public void setServerTransactionID(int serverTransactionID) {
        this.serverTransactionID = serverTransactionID;
    }

    @JsonProperty("ErrorNumber")
    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    @JsonProperty("ErrorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
