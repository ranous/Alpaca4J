package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@SuppressWarnings("unused")
@ApplicationScoped
public class ServerInfo {
    @Inject @ConfigProperty(name = "alpaca.server_info.server-name")
    String serverName;
    @Inject @ConfigProperty(name = "alpaca.server_info.manufacturer")
    String manufacturer;
    @Inject @ConfigProperty(name = "alpaca.server_info.manufacturer-version")
    String manufacturerVersion;
    @Inject @ConfigProperty(name = "alpaca.server_info.location")
    String location;

    public ServerInfo() {

    }

    public ServerInfo(String serverName, String manufacturer, String manufacturerVersion) {
        this.serverName = serverName;
        this.manufacturer = manufacturer;
        this.manufacturerVersion = manufacturerVersion;
    }

    public ServerInfo(String serverName, String manufacturer, String manufacturerVersion, String location) {
        this.serverName = serverName;
        this.manufacturer = manufacturer;
        this.manufacturerVersion = manufacturerVersion;
        this.location = location;
    }

    @JsonProperty("ServerName")
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @JsonProperty("Manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @JsonProperty("ManufacturerVersion")
    public String getManufacturerVersion() {
        return manufacturerVersion;
    }

    public void setManufacturerVersion(String manufacturerVersion) {
        this.manufacturerVersion = manufacturerVersion;
    }

    @JsonProperty("Location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
