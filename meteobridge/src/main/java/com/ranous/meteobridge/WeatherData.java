package com.ranous.meteobridge;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings({"SpellCheckingInspection", "unused"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private double dewpoint;
    private double humidity;
    private double pressure;
    private double rainrate;
    private double temperature;
    private double winddirection;
    private double windgust;
    private double windspeed;

    public WeatherData() {

    }

    public WeatherData(double dewpoint, double humidity, double pressure, double rainrate, double temperature, double winddirection, double windgust, double windspeed) {
        this.dewpoint = dewpoint;
        this.humidity = humidity;
        this.pressure = pressure;
        this.rainrate = rainrate;
        this.temperature = temperature;
        this.winddirection = winddirection;
        this.windgust = windgust;
        this.windspeed = windspeed;
    }

    public double getDewpoint() {
        return dewpoint;
    }

    public void setDewpoint(double dewpoint) {
        this.dewpoint = dewpoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getRainrate() {
        return rainrate;
    }

    public void setRainrate(double rainrate) {
        this.rainrate = rainrate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(double winddirection) {
        this.winddirection = winddirection;
    }

    public double getWindgust() {
        return windgust;
    }

    public void setWindgust(double windgust) {
        this.windgust = windgust;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }
}
