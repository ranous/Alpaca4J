package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Alpaca response to a request for a byte array.
 */
@SuppressWarnings("unused")
public class ByteArrayResponse extends AlpacaResponse {
    private byte[] value;

    public ByteArrayResponse() {
        super();
    }

    public ByteArrayResponse(byte[] value) {
        super();
        this.value = value;
    }

    public ByteArrayResponse(long clientTransactionID, byte[] value) {
        super(clientTransactionID);
        this.value = value;
    }

    @JsonProperty("Value")
    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }
}
