package org.ascom.alpaca.response;

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
