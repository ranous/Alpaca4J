package com.ranous.meteobridge;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Shutdown;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class MeteobridgePoller {
    private static final Logger log = LoggerFactory.getLogger(MeteobridgePoller.class);
    private boolean stopPolling = false;
    private Thread pollingThread;

    @Inject
    WeatherDB weatherDB;
    @Inject @RestClient
    MeteobridgeService client;

    @Inject @ConfigProperty(name="meteobridge.use-polling")
    boolean usePolling;
    @Inject @ConfigProperty(name="meteobridge.polling-interval")
    long pollingInterval;
    @Inject @ConfigProperty(name="meteobridge-client.template")
    String template;


    MeteobridgePoller() {

    }


    void onStart(@Observes Startup ev) {
        log.info("Initializing meteobridge polling");

        if (usePolling) {
            log.info("Starting meteobridge poller");
            startPolling();
        }
        log.info("Finished initializing meteobridge polling");
    }

    void onStop(@Observes Shutdown ev) {
        log.info("Shutting down the polling thread...");
        stopPolling();
    }

    public void startPolling() {
        if (pollingThread != null) {
            log.warn("Meteobridge poller already running");
            return;
        }
        stopPolling = false;
        pollingThread = new Thread() {
            public synchronized void run() {
                poller();
            }
        };
        pollingThread.start();
    }

    public void stopPolling() {
        stopPolling = true;
    }

    void poller() {
        while (!stopPolling) {
            try {
                log.info("Calling meteobridge to get weather data");
                WeatherData data = client.getWeatherData(template, MediaType.APPLICATION_JSON+";"+ StandardCharsets.ISO_8859_1.name());
                weatherDB.setWeatherData(data);
            } catch (Exception e) {
                log.warn("Got an exception polling meteobridge: ", e);
            }
            try {
                Thread.sleep(pollingInterval * 1000);
            } catch (Exception e) {
                // Ignore
            }
        }
    }
}
