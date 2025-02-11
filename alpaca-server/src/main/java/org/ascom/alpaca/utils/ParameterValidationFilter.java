package org.ascom.alpaca.utils;

import jakarta.annotation.Priority;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.TransactionIDFactory;
import org.slf4j.MDC;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This filter preprocesses the client request and extracts the ClientID and ClientTransactionID from the request
 * and validates them.
 */
@Provider
@Priority(10) // Ensures this runs before resource methods
public class ParameterValidationFilter implements ContainerRequestFilter, ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        String method = request.getMethod();
        if (method == null) {
            return;
        }

        int serverTransactionID = TransactionIDFactory.getServerTransactionID();
        ClientContext clientContext = new ClientContext();
        clientContext.setServerTransactionID(serverTransactionID);

        // Put the server transaction id for the call into the mapped diagnostic context so it can be added to all log messages
        MDC.put("ServerTransactionID",  "ServerTransactionID=" + serverTransactionID);

        if ("GET".equalsIgnoreCase(method)) {
            processQueryParameters(request, clientContext);
        } else if ("PUT".equalsIgnoreCase(method) && request.getMediaType().isCompatible(MediaType.APPLICATION_FORM_URLENCODED_TYPE)) {
            processFormParameters(request, clientContext);
        }
        ClientContext.setContext(clientContext);
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        if (responseContext.getEntity() instanceof AlpacaResponse response) {
            response.setServerTransactionID(ClientContext.getContext().getServerTransactionID());
            if (response.getClientTransactionID() == 0) {
                response.setClientTransactionID(ClientContext.getContext().getClientTransactionID());
            }
        }
        MDC.clear();
        ClientContext.clearContext();
    }

    private void processQueryParameters(ContainerRequestContext request, ClientContext clientContext) {
        UriInfo uriInfo = request.getUriInfo();
        MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
        if (params != null) {
            Long clientTransactionID = getParameterAsLong("ClientTransactionID", params, null);
            if (clientTransactionID != null) {
                clientContext.setClientTransactionID(clientTransactionID);
            }
            Long clientID = getParameterAsLong("ClientID", params, clientTransactionID);
            if (clientID != null) {
                clientContext.setClientID(clientID);
            }
        }
    }

    private void processFormParameters(ContainerRequestContext request, ClientContext clientContext) throws IOException {
        // Ok, read the body so we can extract the ClientID and ClientTransactionID.
        String body = new String(request.getEntityStream().readAllBytes(), StandardCharsets.UTF_8);
        // Restore the entity stream, since we consumed it and let the standard runtime process it
        request.setEntityStream(new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8)));

        Map<String, String> formParams = parseFormData(body);

        Long clientTransactionID = getLong("ClientTransactionID", formParams.get("ClientTransactionID"), null);
        if (clientTransactionID != null) {
            clientContext.setClientTransactionID(clientTransactionID);
        }
        Long clientID = getLong("ClientID", formParams.get("ClientID"), clientTransactionID);
        if (clientID != null) {
            clientContext.setClientID(clientID);
        }
    }

    private Map<String, String> parseFormData(String body) {
        return Arrays.stream(body.split("&"))
                .map(param -> param.split("=", 2))
                .filter(pair -> pair.length == 2)
                .collect(Collectors.toMap(pair -> URLDecoder.decode(pair[0], StandardCharsets.UTF_8),
                        pair -> URLDecoder.decode(pair[1], StandardCharsets.UTF_8)));
    }

    // Make sure the parameter is actually a number and unsigned
    private Long getParameterAsLong(String paramName, MultivaluedMap<String, String> params, Long clientTransactionID) {
        for (Map.Entry<String, List<String>> entry: params.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(paramName)) {
                for (String value: entry.getValue()) {
                    return getLong(entry.getKey(), value, clientTransactionID);
                }
            }
        }
        return null;
    }

    private Long getLong(String paramName, String value, Long clientTransactionID) {
        if (value != null) {
            try {
                Long lvalue = Long.decode(value);
                if (lvalue < 0) {
                    throw new InvalidValueException(clientTransactionID != null ? clientTransactionID : 0, paramName + " cannot be less than zero");
                }
                MDC.put(paramName, paramName + "=" + lvalue);
                return lvalue;
            } catch (NumberFormatException e) {
                throw new InvalidValueException(clientTransactionID != null ? clientTransactionID : 0, paramName + " is not an integer");
            }
        }
        return null;
    }
}