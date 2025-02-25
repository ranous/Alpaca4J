package com.ranous.meteobridge;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.core.MediaType;
import org.ascom.alpaca.response.InvalidValueException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
@Singleton
public class WeatherDB {
    private static final Logger log = LoggerFactory.getLogger(WeatherDB.class);

    private WeatherData lastWeatherData = new WeatherData();
    private long lastUpdateTime;// = System.currentTimeMillis();
    // TODO:  implement an averaging system.
    private double averageInterval = 0.0;

    @Inject @RestClient
    MeteobridgeService meteobridgeService;
    @Inject
    @ConfigProperty(name="meteobridge-client.template")
    String template;
    @Inject
    @ConfigProperty(name="meteobridge-db.timeout", defaultValue = "300")
    long timeout;


    public synchronized WeatherData getWeatherData() {
        if (System.currentTimeMillis() > lastUpdateTime + timeout*1000) {
            try {
                log.info("Weather data is old, refreshing");
                lastWeatherData = meteobridgeService.getWeatherData(template, MediaType.APPLICATION_JSON);
                lastUpdateTime = System.currentTimeMillis();
            } catch (Exception e) {
                log.warn("Problem getting weather data from meteobridge", e);
                // We'll just drop through and use the last value we have.
            }
        }
        return lastWeatherData;
    }
    public void setWeatherData(WeatherData weatherData) {
        this.lastWeatherData = weatherData;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public double getDewPoint() {
        return getWeatherData().getDewpoint();
    }

    public void setDewPoint(Double dewPoint) {
        if (dewPoint == null) {
            return;
        }
        lastWeatherData.setDewpoint(dewPoint);
        lastUpdateTime = System.currentTimeMillis();
    }
    public double getHumidity() {
        return getWeatherData().getHumidity();
    }

    public void setHumidity(Double humidity) {
        if (humidity == null) {
            return;
        }
        lastWeatherData.setHumidity(humidity);
        lastUpdateTime = System.currentTimeMillis();
    }

    public double getPressure() {
        return getWeatherData().getPressure();
    }

    public void setPressure(Double pressure) {
        if (pressure == null) {
            return;
        }
        lastWeatherData.setPressure(pressure);
        lastUpdateTime = System.currentTimeMillis();
    }

    public double getRainRate() {
        return getWeatherData().getRainrate();
    }

    public void setRainRate(Double rainRate) {
        if (rainRate == null) {
            return;
        }
        lastWeatherData.setRainrate(rainRate);
        lastUpdateTime = System.currentTimeMillis();
    }

    public double getTemperature() {
        return getWeatherData().getTemperature();
    }

    public void setTemperature(double temperature) {
        lastWeatherData.setTemperature(temperature);
        lastUpdateTime = System.currentTimeMillis();
    }

    public double getWindDirection() {
        return getWeatherData().getWinddirection();
    }

    public void setWindDirection(Double windDirection) {
        if (windDirection == null) {
            return;
        }
        lastUpdateTime = System.currentTimeMillis();
        lastWeatherData.setWinddirection(windDirection);
    }

    public double getWindGust() {
        return getWeatherData().getWindgust();
    }

    public void setWindGust(Double windGust) {
        if (windGust == null) {
            return;
        }
        lastUpdateTime = System.currentTimeMillis();
        lastWeatherData.setWindgust(windGust);
    }

    public double getWindSpeed() {
        return getWeatherData().getWindspeed();
    }

    public void setWindSpeed(Double windSpeed) {
        if (windSpeed == null) {
            return;
        }
        lastUpdateTime = System.currentTimeMillis();
        lastWeatherData.setWindspeed(windSpeed);
    }

    /**
     * The time of the last update
     */
    public long getLastUpdated() {
        return lastUpdateTime;
    }

    /**
     * The elapsed time since the last data update in seconds.
     */
    public long getDurationSinceLastUpdated() {
        return (System.currentTimeMillis() - lastUpdateTime) / 1000;
    }

    public void refresh() {
        getWeatherData();
    }

    public double getAverageInterval() {
        return averageInterval;
    }

    public void setAverageInterval(double interval) {
        if (interval != 0.0) {
            throw new InvalidValueException("The interval of " + interval + " is invalid - only instantaneous values are supported");
        }
        averageInterval = interval;
    }

}
