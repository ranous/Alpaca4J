package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class ServerInfo {
    private String serverName;
    private String manufacturer;
    private String manufacturerVersion;
    private String location;

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
