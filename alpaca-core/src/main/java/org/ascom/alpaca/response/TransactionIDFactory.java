package org.ascom.alpaca.response;

/**
 * The TransactionIDFactory is used to generate unique transaction IDs for Alpaca requests.
 */
public class TransactionIDFactory {
    private static int clientTransactionID = 0;
    private static int serverTransactionID = 0;

    public static synchronized int getClientTransactionID() {
        return ++clientTransactionID;
    }

    public static synchronized int getServerTransactionID() {
        return ++serverTransactionID;
    }
}
