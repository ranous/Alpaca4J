package org.ascom.alpaca.client.model;

/**
 * Callback interface for asynchronous operations. For the asynchronous operations,
 * the caller supplies an implmentation this interface provides a way to handle the
 * result or error of the operation.
 *
 * @param <T> The type of the result returned by the operation
 */
public interface AlpacaCallback<T> {
    /**
     * This method is called when the operation completes successfully, with the
     * result of the operation as a parameter.
     * @param result The result of the operation
     */
    void success(T result);

    /**
     * This method is called when the operation completes with an error.
     * @param error The error that occurred
     */
    void error(AlpacaClientError error);
}
