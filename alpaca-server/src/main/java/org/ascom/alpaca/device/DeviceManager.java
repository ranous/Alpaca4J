package org.ascom.alpaca.device;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Shutdown;
import jakarta.enterprise.event.Startup;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.DeviceType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The device manager is responsible for managing the lifecycle of all Alpaca devices. Implementors of
 * new devices need not interact with the DeviceManager as their device will get registered with
 * the device manager automatically on startup
 */
@SuppressWarnings("unused")
@ApplicationScoped
public class DeviceManager {
    private static final Logger log = LoggerFactory.getLogger(DeviceManager.class);
    private final List<Device> deviceList = new ArrayList<>();
    private final Map<DeviceType, List<Device>> deviceMap = new HashMap<>();
    private Map<String, UUID> knownDevices = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    Instance<Device> devices;

    @Inject @ConfigProperty(name = "alpaca.device-state.file", defaultValue = "./devices.json")
    String persistenceFile;

    public DeviceManager() {

    }

    void onStart(@Observes Startup ev) {
        loadDeviceData();
    }

    void onStop(@Observes Shutdown ev) {
        saveDeviceData();
    }

    private void loadDeviceData() {
        File file = new File(persistenceFile);
        if (file.exists()) {
            try {
                knownDevices = objectMapper.readValue(file, new TypeReference<Map<String, UUID>>() {});
                log.info("Loaded {} known devices from {}", knownDevices.size(), persistenceFile);
            } catch (IOException e) {
                log.error("Failed to load device data from {}", persistenceFile, e);
            }
        }
    }

    private void saveDeviceData() {
        try {
            objectMapper.writeValue(new File(persistenceFile), knownDevices);
            log.info("Saved {} known devices to {}", knownDevices.size(), persistenceFile);
        } catch (IOException e) {
            log.error("Failed to save device data to {}", persistenceFile, e);
        }
    }

    @SuppressWarnings("unchecked") // Suppress warning since we ensure type safety
    public <D extends Device> D getDevice(int deviceID, DeviceType type) {
        if (deviceList.isEmpty()) {
            devices.forEach(this::addDevice);
        }
        List<Device> deviceList = deviceMap.get(type);
        if (deviceList == null) {
            throw new BadRequestException("There are no device of type " + type + " with deviceID " + deviceID);

        }

        if (deviceID < 0 || deviceID > deviceList.size() - 1) {
            throw new BadRequestException("There are no device of type " + type + " with deviceID " + deviceID);
        }
        return (D) deviceList.get(deviceID);
    }

    public synchronized List<Device> getDevices() {
        if (deviceList.isEmpty()) {
            devices.forEach(this::addDevice);
        }
        return deviceMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public synchronized void addDevice(Device device) {
        DeviceDescriptor descriptor = device.getDeviceDescriptor();
        String deviceKey = descriptor.getDeviceType().toString() + ":" + descriptor.getDeviceName();

        if (knownDevices.containsKey(deviceKey)) {
            log.info("Device {} already known with unique ID {}", descriptor.getDeviceName(), descriptor.getUniqueID());
            descriptor.setUniqueID(knownDevices.get(deviceKey));
        } else {
            log.info("Adding new device {} with unique ID {}", descriptor.getDeviceName(), descriptor.getUniqueID());
            knownDevices.put(deviceKey, descriptor.getUniqueID());
            saveDeviceData();
        }

        List<Device> devices = deviceMap.computeIfAbsent(device.getDeviceType(), k -> new ArrayList<>());
        device.setDeviceID(devices.size());
        devices.add(device);

        deviceList.add(device);
    }
}
