package org.ascom.alpaca.utils;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


/**
 * A filter for logging the web services calls.
 */
@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);
    private static final String START_TIME = "LoggingFilter.START_TIME";

    @Inject
    RoutingContext routingContext;

    @Override
    public void filter(ContainerRequestContext request) {
        long start = System.currentTimeMillis();
        request.setProperty(START_TIME, start);

        StringBuilder sb = new StringBuilder();
        String clientIP = getClientIP(request);
        if (!clientIP.isEmpty()) {
            sb.append(clientIP);
        }
        log.info("REQUEST START: {} {}{} {}", request.getMethod(), request.getUriInfo().getPath(), getQueryParameters(request), sb);
    }

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) {
        long end = System.currentTimeMillis();
        long delta = -1;
        Long start = (Long)request.getProperty(START_TIME);
        if (start != null) {
            delta = end - start;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("status=").append(response.getStatus());
        sb.append(", ms=").append(delta);

        Object entity = response.getEntity();
        if (entity instanceof AlpacaResponse alpacaResponse) {
            int errorNum = alpacaResponse.getErrorNumber();
            if (errorNum > 0) {
                try {
                    sb.append(", error=").append(ErrorCode.fromCode(errorNum));
                } catch (IllegalArgumentException e) {
                    sb.append(", error=").append(errorNum);
                }
            }
        }
        if (entity != null && entity.getClass().equals(String.class)) {
            sb.append(", content-length=").append(((String)entity).length());
        }

        sb.append(", ").append(getClientIP(request));

        log.info("REQUEST END: {} {}{} {}", request.getMethod(), request.getUriInfo().getPath(), getQueryParameters(request), sb);
    }

    private String getClientIP(ContainerRequestContext context) {
        String ipAddress = context.getHeaders().getFirst("X-Forwarded-For");
        if (ipAddress != null) {
            return "client_ip=" + ipAddress;
        }

        ipAddress = context.getHeaders().getFirst("NS-Client-IP");
        if (ipAddress != null && !ipAddress.isEmpty()) {
            return "client_ip=" + ipAddress;
        }

        ipAddress = context.getHeaders().getFirst("X-Forwarded-Host");
        if (ipAddress != null && !ipAddress.isEmpty()) {
            return "client_ip=" + ipAddress;
        }

        HttpServerRequest serverRequest = routingContext.request();
        ipAddress = serverRequest.remoteAddress().host();
        return "client_ip=" + ipAddress;
    }

    /**
     * Stringify query parameters (supports multiple params with same key)
     */
    private String getQueryParameters(ContainerRequestContext request) {
        MultivaluedMap<String, String> params = request.getUriInfo().getQueryParameters();
        if (params != null) {
            StringBuilder sb = new StringBuilder();
            String sep = "?";
            for (Map.Entry<String, List<String>> entry: params.entrySet()) {
                String key = entry.getKey();
                for (String value: entry.getValue()) {
                    sb.append(sep).append(key).append("=").append(value);
                    sep = "&";
                }
            }
            return sb.toString();
        }
        return "";
    }
}
