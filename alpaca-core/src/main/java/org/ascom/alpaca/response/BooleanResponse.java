package org.ascom.alpaca.response;

/**
 * An Alpaca response that contains a boolean value.
 */
public class BooleanResponse extends ValueResponse<Boolean> {
    @SuppressWarnings("unused")
    BooleanResponse() {
        super();
    }

    public BooleanResponse(boolean value) {
        super(value);
    }

    public BooleanResponse(long clientTransactionID, boolean value) {
        super(clientTransactionID, value);
    }
}
