package org.ascom.alpaca.utils;

import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Context;
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

    // Ugh, this is the only way I've been able to figure out how to get the client IP address while using Quarkus is
    // to use the Vert.X HttpServerRequest object.  In the past when using Jersey, which ran in a Servlet environment,
    // I was using HttpServletRequest, which is more common and would be more portable.  Not available with Quarkus,
    // so here we are.  If we port to another microprofile environment, we most likely need to come up with another
    // mechanism.  You'd either need to remove the serverRequest, or compile this with the vertx-core package.  Even
    // if you don't include the vert-core in your runtime, this should still work as serverRequest will be null,
    // so it won't trigger a runtime exception.
    @Context
    HttpServerRequest serverRequest;

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
                    // shouldn't ever happen as there's no reason for the server to return an error code that
                    // isn't already defined in ErrorCode
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

        // If we're running in a Helidon environment, this is the only way I've been able to figure
        // out how to get the client ip
        Object remoteAddress = context.getProperty("io.helidon.jaxrs.remote-host");
        if (remoteAddress != null && remoteAddress instanceof String) {
            return "client_ip=" + remoteAddress.toString();
        }

        // And this is the Quarkus way
        if (serverRequest != null) {
            ipAddress = serverRequest.remoteAddress().host();
            return "client_ip=" + ipAddress;
        }
        return "";
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
