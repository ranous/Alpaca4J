package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * The DeviceDescriptor class represents a device on the Alpaca server.
 */
@SuppressWarnings("unused")
public class DeviceDescriptor {
    private String deviceName;
    private DeviceType deviceType;
    private Integer deviceNumber;
    private UUID uniqueID;

    public DeviceDescriptor() {

    }

    public DeviceDescriptor(String deviceName, DeviceType deviceType, Integer deviceNumber) {
        this(deviceName, deviceType, deviceNumber, UUID.randomUUID());
    }

    public DeviceDescriptor(String deviceName, DeviceType deviceType, Integer deviceNumber, UUID uniqueID) {
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.deviceNumber = deviceNumber;
        this.uniqueID = uniqueID;
    }

    @JsonProperty("DeviceName")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @JsonProperty("DeviceType")
    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = DeviceType.fromType(deviceType);
    }

    @JsonProperty("DeviceNumber")
    public Integer getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(Integer deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    @JsonProperty("UniqueID")
    public UUID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UUID uniqueID) {
        this.uniqueID = uniqueID;
    }
}
