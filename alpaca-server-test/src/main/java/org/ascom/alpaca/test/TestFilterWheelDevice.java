package org.ascom.alpaca.test;

import jakarta.inject.Singleton;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.FilterWheelDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.InvalidValueException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@SuppressWarnings("unused")
@Singleton
public class TestFilterWheelDevice extends BaseDevice implements FilterWheelDevice {
    private final List<String> filterNames = List.of("Lum", "Red", "Green", "Blue");
    private final List<Integer> focusOffsets = List.of(34,64,23,23);
    private int position = 0;

    // The version of the driver is injected from the microprofile-config.properties file and can be overridden
    // by the system property test.driver.version
    public TestFilterWheelDevice(@ConfigProperty(name="test.driver.version", defaultValue = "1.0") String deviceVersion) {
        super(DeviceType.FilterWheel, "Test FilterWheel Driver", FilterWheelDevice.interfaceVersion, deviceVersion);
        setDescription("Test FilterWheel Device");
    }

    @Override
    public List<Integer> getFocusOffsets() {
        return focusOffsets;
    }

    @Override
    public List<String> getFilterNames() {
        return filterNames;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        if (position > filterNames.size()-1) {
            throw new InvalidValueException("The filter position " + position + " exceeds the number of filters");
        }
        this.position = position;
    }
}
