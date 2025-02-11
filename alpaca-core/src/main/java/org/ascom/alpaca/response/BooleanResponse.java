package org.ascom.alpaca.response;

public class BooleanResponse extends ValueResponse<Boolean> {

    public BooleanResponse() {
        super();
    }
    public BooleanResponse(boolean value) {
        super(value);
    }

    public BooleanResponse(long clientTransactionID, boolean value) {
        super(clientTransactionID, value);
    }
}
