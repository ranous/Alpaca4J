package org.ascom.alpaca.response;

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
