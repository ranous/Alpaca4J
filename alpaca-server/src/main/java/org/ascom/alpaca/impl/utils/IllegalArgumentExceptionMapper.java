package org.ascom.alpaca.impl.utils;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.ascom.alpaca.response.ErrorCode;
import org.ascom.alpaca.response.AlpacaResponse;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException exception) {
        return Response.ok(new AlpacaResponse(0, ErrorCode.InvalidValue.getCode(), exception.getMessage()), MediaType.APPLICATION_JSON_TYPE).build();
    }
}
