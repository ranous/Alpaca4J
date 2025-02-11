package org.ascom.alpaca.response;

public class ServerInfoResponse extends ValueResponse<ServerInfo> {
    public ServerInfoResponse() {
        super();
    }

    public ServerInfoResponse(ServerInfo value) {
        super(value);
    }

    public ServerInfoResponse(long clientTransactionID, ServerInfo value) {
        super(clientTransactionID, value);
    }
}
