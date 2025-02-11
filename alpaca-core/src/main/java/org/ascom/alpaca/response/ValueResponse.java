package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class ValueResponse<V> extends AlpacaResponse {
    private V value;

    public ValueResponse() {

    }

    public ValueResponse(V value) {
        super();
        this.value = value;
    }

    public ValueResponse(long clientTransactionID, V value) {
        super(clientTransactionID);
        this.value = value;
    }

    @JsonProperty("Value")
    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
