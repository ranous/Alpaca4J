package org.ascom.alpaca.response;

/**
 * The Alpaca response to a request for an integer.
 */
public class IntResponse extends ValueResponse<Integer> {
    @SuppressWarnings("unused")
    IntResponse() {
        super();
    }

    public IntResponse(Integer value) {
        super(value);
    }

    public IntResponse(long clientTransactionID, Integer value) {
        super(clientTransactionID, value);
    }
}
