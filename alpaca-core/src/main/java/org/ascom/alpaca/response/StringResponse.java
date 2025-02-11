package org.ascom.alpaca.response;

public class StringResponse extends ValueResponse<String> {
    public StringResponse() {
        super();
    }

    public StringResponse(String value) {
        super(value);
    }

    public StringResponse(long clientTransactionID, String value) {
        super(clientTransactionID, value);
    }
}
