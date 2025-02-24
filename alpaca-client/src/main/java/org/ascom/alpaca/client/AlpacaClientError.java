package org.ascom.alpaca.client;

import org.ascom.alpaca.response.ErrorCode;

public class AlpacaClientError {
    private final ErrorCode errorCode;
    private String errorMessage;
    private long clientTransactionID;
    private long serverTransactionID;

    public AlpacaClientError(String errorMessage) {
        this.errorCode = ErrorCode.ServerError;
        this.errorMessage = errorMessage;
    }

    public AlpacaClientError(long clientTransactionID, long serverTransactionID, ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.clientTransactionID = clientTransactionID;
        this.serverTransactionID = serverTransactionID;
    }

    public AlpacaClientError(long clientTransactionID, long serverTransactionID, ErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.clientTransactionID = clientTransactionID;
        this.serverTransactionID = serverTransactionID;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public long getClientTransactionID() {
        return clientTransactionID;
    }


    public long getServerTransactionID() {
        return serverTransactionID;
    }

    public static class ServerError extends AlpacaClientError {
        private final int serverErrorCode;

        public ServerError(String errorMessage, int serverErrorCode) {
            super(errorMessage);
            this.serverErrorCode = serverErrorCode;
        }

        public int getServerErrorCode() {
            return serverErrorCode;
        }
    }

    public static class CommunicationError extends AlpacaClientError {
        public CommunicationError(String errorMessage) {
            super(errorMessage);
        }
    }

}
