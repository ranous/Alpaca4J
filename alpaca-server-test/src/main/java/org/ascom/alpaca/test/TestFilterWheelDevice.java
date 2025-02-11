package org.ascom.alpaca.test;

import jakarta.inject.Singleton;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.FilterWheelDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.InvalidValueException;

import java.util.List;

@SuppressWarnings("unused")
@Singleton
public class TestFilterWheelDevice extends BaseDevice implements FilterWheelDevice {
    private final List<String> filterNames = List.of("Lum", "Red", "Green", "Blue");
    private final List<Integer> focusOffsets = List.of(34,64,23,23);
    private int position = 0;

    public TestFilterWheelDevice() {
        super(DeviceType.FilterWheel, "Test FilterWheel Driver", 3);
        setDescription("Test FilterWheel Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    @Override
    public List<Integer> getFocusOffsets(int clientID) {
        return focusOffsets;
    }

    @Override
    public List<String> getFilterNames(int clientID) {
        return filterNames;
    }

    @Override
    public int getPosition(int clientID) {
        return position;
    }

    @Override
    public void setPosition(int clientID, int position) {
        if (position > filterNames.size()-1) {
            throw new InvalidValueException("The filter position " + position + " exceeds the number of filters");
        }
        this.position = position;
    }
}
