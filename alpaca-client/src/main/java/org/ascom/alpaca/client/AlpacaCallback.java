package org.ascom.alpaca.client;

public interface AlpacaCallback<T> {
    void success(T result);
    void error(AlpacaClientError error);
}
