package org.ascom.alpaca.utils;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.ascom.alpaca.response.AlpacaException;
import org.ascom.alpaca.response.AlpacaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class AlpacaExceptionMapper implements ExceptionMapper<AlpacaException> {
    private static final Logger log = LoggerFactory.getLogger(AlpacaExceptionMapper.class);
    @Override
    public Response toResponse(AlpacaException exception) {
        log.warn(exception.getMessage());
        ClientContext context = ClientContext.getContext();
        AlpacaResponse response = new AlpacaResponse(context.getClientTransactionID(), context.getServerTransactionID(), exception.getErrorNumber(), exception.getMessage());
        return Response.ok(response, MediaType.APPLICATION_JSON_TYPE).build();
    }
}
