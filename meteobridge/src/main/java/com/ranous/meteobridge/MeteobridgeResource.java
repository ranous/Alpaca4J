package com.ranous.meteobridge;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This web service receives weather update from a meteobridge.  The meteobridge needs to be configured to send an
 * HTTP event.  It should be configured to perform a GET with a URL such as below
 * http://192.168.5.99:11111/weather/updateWeather?dewpoint=[th0dew-act]&humidity=[th0hum-act]&pressure=[thb0press-act]&rainrate=[rain0rate-act]&temperature=[th0temp-act]&winddirection=[wind0dir-act]&windgust=[wind0wind-max2]&windspeed=[wind0wind-act]
 */

@SuppressWarnings({"SpellCheckingInspection", "GrazieInspection", "JavadocLinkAsPlainText"})
@Path("weather")
@ApplicationScoped
public class MeteobridgeResource {
    private static final Logger log = LoggerFactory.getLogger(MeteobridgeResource.class);

    @Inject
    WeatherDB db;

    @GET
    @Path("updateWeather")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateWeather(@QueryParam("dewpoint") Double dewpoint,
                                @QueryParam("humidity") Double humidity,
                                @QueryParam("pressure") Double pressure,
                                @QueryParam("rainrate") Double rainRate,
                                @QueryParam("temperature") Double temperature,
                                @QueryParam("winddirection") Double windDirection,
                                @QueryParam("windgust") Double windGust,
                                @QueryParam("windspeed") Double windSpeed
                                ) {
        log.info("Temperature={}, Humidity={}, Pressure={}, DewPoint={}", temperature, humidity, pressure, dewpoint);

        db.setDewPoint(dewpoint != null ? dewpoint : 0);
        db.setHumidity(humidity != null ? humidity : 0);
        db.setPressure(pressure);
        db.setRainRate(rainRate);
        db.setTemperature(temperature);
        db.setWindDirection(windDirection);
        db.setWindGust(windGust);
        db.setWindSpeed(windSpeed);
        return ("OK");
    }
}
