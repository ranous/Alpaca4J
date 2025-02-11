package org.ascom.alpaca.utils;

public class ClientContext {
    private static final ThreadLocal<ClientContext> threadLocal = ThreadLocal.withInitial(ClientContext::new);

    private long clientID;
    private long clientTransactionID;
    private int serverTransactionID;

    public static ClientContext getContext() {
        return threadLocal.get();
    }

    public static void setContext(ClientContext context) {
        threadLocal.set(context);
    }

    public static void clearContext() {
        threadLocal.remove();
    }

    public ClientContext() {

    }

    public ClientContext(int serverTransactionID,
                         long clientTransactionID,
                         long clientID) {
        this.serverTransactionID = serverTransactionID;
        this.clientTransactionID = clientTransactionID;
        this.clientID = clientID;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getClientTransactionID() {
        return clientTransactionID;
    }

    public void setClientTransactionID(long clientTransactionID) {
        this.clientTransactionID = clientTransactionID;
    }

    public int getServerTransactionID() {
        return serverTransactionID;
    }

    public void setServerTransactionID(int serverTransactionID) {
        this.serverTransactionID = serverTransactionID;
    }
}
