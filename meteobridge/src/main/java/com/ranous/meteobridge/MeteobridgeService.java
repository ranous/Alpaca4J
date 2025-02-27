package com.ranous.meteobridge;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Base64;

/**
 * This is a REST client interface for querying the Meteobridge device for weather data.
 * This is used when configured to poll the device for weather data.
 */
@SuppressWarnings("SpellCheckingInspection")
@Path("cgi-bin")
@RegisterRestClient(configKey="meteobridge-client")
@ClientHeaderParam(name = "Authorization", value = "{lookupAuth}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface MeteobridgeService {
    @GET
    @Path("template.cgi")
    WeatherData getWeatherData(@QueryParam("template") String template,
                               @QueryParam("contenttype") String contentType);

    @SuppressWarnings("unused")
    default String lookupAuth() {
        Config config = ConfigProvider.getConfig();
        String login = config.getValue("meteobridge-client.login", String.class);
        return "Basic " + Base64.getEncoder().encodeToString(login.getBytes());
    }
}
