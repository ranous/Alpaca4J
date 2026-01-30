package org.ascom.alpaca.client.model;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class CommunicationsError extends ClientException {
        public CommunicationsError() {
            super("Problem communicating with the Alpaca device");
        }

        public CommunicationsError(String msg) {
            super("Problem communicating with the Alpaca service - " + msg);
        }
    }

}
