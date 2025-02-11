package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StateValue(@JsonProperty("Name") String name, @JsonProperty("Value") Object value) {

}
