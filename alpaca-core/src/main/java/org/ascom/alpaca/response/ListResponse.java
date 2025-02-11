package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ListResponse<V> extends AlpacaResponse {
    private List<V> value;

    public ListResponse() {
        super();
        value = new ArrayList<>();
    }

    public ListResponse(List<V> value) {
        super();
        this.value = value;
    }

    public ListResponse(long clientTransactionID) {
        super(clientTransactionID);
        value = new ArrayList<>();
    }

    public ListResponse(long clientTransactionID, List<V> value) {
        super(clientTransactionID);
        this.value = value;
    }

    @JsonProperty("Value")
    public List<V> getValue() {
        return value;
    }

    public void setValue(List<V> value) {
        this.value = value;
    }

    public void addValue(V value) {
        this.value.add(value);
    }
}
