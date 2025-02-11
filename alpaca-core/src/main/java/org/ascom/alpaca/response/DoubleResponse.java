package org.ascom.alpaca.response;

public class DoubleResponse extends ValueResponse<Double> {
    public DoubleResponse() {
        super();
    }

    public DoubleResponse(double value) {
        super(value);
    }

    public DoubleResponse(long clientTransactionID, double value) {
        super(clientTransactionID, value);
    }
}
