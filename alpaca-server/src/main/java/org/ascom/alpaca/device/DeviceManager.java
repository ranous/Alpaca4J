package org.ascom.alpaca.device;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import org.ascom.alpaca.model.DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@ApplicationScoped
public class DeviceManager {
    private static final Logger log = LoggerFactory.getLogger(DeviceManager.class);
    private final List<Device> deviceList = new ArrayList<>();
    private final Map<DeviceType, List<Device>> deviceMap = new HashMap<>();

    @Inject
    Instance<Device> devices;

    public DeviceManager() {

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
        List<Device> devices = deviceMap.computeIfAbsent(device.getDeviceType(), k -> new ArrayList<>());
        device.setDeviceID(devices.size());
        devices.add(device);

        deviceList.add(device);
    }
}
