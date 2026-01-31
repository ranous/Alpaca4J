package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ascom.alpaca.model.ImageArray;

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
