package org.ascom.alpaca.response;

import org.ascom.alpaca.model.ServerInfo;

@SuppressWarnings("unused")
public class ServerInfoResponse extends ValueResponse<ServerInfo> {
    ServerInfoResponse() {
        super();
    }

    public ServerInfoResponse(ServerInfo value) {
        super(value);
    }

    public ServerInfoResponse(long clientTransactionID, ServerInfo value) {
        super(clientTransactionID, value);
    }
}
