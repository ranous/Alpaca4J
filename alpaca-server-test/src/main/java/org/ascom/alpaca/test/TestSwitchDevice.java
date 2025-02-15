package org.ascom.alpaca.test;

import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.SwitchDevice;
import org.ascom.alpaca.model.DeviceType;

import jakarta.inject.Singleton;
import org.ascom.alpaca.response.InvalidValueException;

import java.util.List;

@Singleton
public class TestSwitchDevice extends BaseDevice implements SwitchDevice {
    private final List<Switch> switches = List.of(new Switch("Switch1", "Test Switch 1", false, 0, 0, 1, true, true),
            new Switch("Switch2", "Test Switch 2", true, 2, 0, 5, true, true),
            new Switch("Switch3", "Test Switch 3", true, 1, 0, 1, false, true),
            new Switch("Switch4", "Test Switch 4", true, 3, 0, 4, false, false));

    @SuppressWarnings("CanBeFinal")
    private static class Switch {
        public String name;
        public String description;
        public boolean state;
        public double value;
        public double minSwitchValue;
        public double maxSwitchValue;
        public boolean canAsync;
        public boolean canWrite;
        public Switch(String name, String description, boolean state, double value, double minSwitchValue, double maxSwitchValue, boolean canAsync, boolean canWrite) {
            this.name = name;
            this.description = description;
            this.state = state;
            this.value = value;
            this.minSwitchValue = minSwitchValue;
            this.maxSwitchValue = maxSwitchValue;
            this.canAsync = canAsync;
            this.canWrite = canWrite;
        }
    }

    public TestSwitchDevice() {
        super(DeviceType.Switch, "Test Switch Driver", 3);
        setDescription("Test Switch Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    private void checkSwitch(int switchID) {
        if (switchID < 0 || switchID > switches.size() -1) {
            throw new InvalidValueException("Invalid switch ID: " + switchID);
        }
    }

    @Override
    public int getMaxSwitch(int clientID) {
        return switches.size();
    }

    @Override
    public boolean canAsync(int switchID,
                            int clientID) {
        checkSwitch(switchID);
        return switches.get(switchID).canAsync;
    }

    @Override
    public boolean canWrite(int switchID,
                            int clientID) {
        checkSwitch(switchID);
        return switches.get(switchID).canWrite;
    }

    @Override
    public boolean getSwitchState(int switchID,int clientID) {
        checkSwitch(switchID);
        return switches.get(switchID).state;
    }

    @Override
    public String getSwitchDescription(int switchID, int clientID) {
        checkSwitch(switchID);
        return switches.get(switchID).description;
    }

    @Override
    public String getSwitchName(int switchID, int clientID) {
        checkSwitch(switchID);
        return switches.get(switchID).name;
    }

    @Override
    public double getSwitchValue(int switchID, int clientID) {
        checkSwitch(switchID);
        return switches.get(switchID).value;
    }

    @Override
    public double getMinSwitchValue(int switchID, int clientID) {
        checkSwitch(switchID);
        return switches.get(switchID).minSwitchValue;
    }

    @Override
    public double getMaxSwitchValue(int switchID, int clientID) {
        checkSwitch(switchID);
        return switches.get(switchID).maxSwitchValue;
    }

    @Override
    public boolean isStateChangeComplete(int switchID, int clientID) {
        checkSwitch(switchID);
        return true;
    }

    @Override
    public void cancelAsync(int switchID, int clientID) {
        checkSwitch(switchID);
    }

    @Override
    public void setAsync(int switchID, boolean state, int clientID) {
        checkSwitch(switchID);
        switches.get(switchID).state = state;
        switches.get(switchID).value = state ? switches.get(switchID).maxSwitchValue: switches.get(switchID).minSwitchValue;
    }

    @Override
    public void setAsyncValue(int switchID, int value, int clientID) {
        checkSwitch(switchID);
        Switch s = switches.get(switchID);
        if (value < s.minSwitchValue || value > s.maxSwitchValue) {
            throw new InvalidValueException("Invalid switch value: " + value);
        }
        s.value = value;
        s.state = value == s.maxSwitchValue || s.state;
    }

    @Override
    public void setSwitchState(int switchID, boolean state, int clientID) {
        checkSwitch(switchID);
        switches.get(switchID).state = state;
        switches.get(switchID).value = state ? switches.get(switchID).maxSwitchValue: switches.get(switchID).minSwitchValue;
    }

    @Override
    public void setSwitchName(int switchID, String switchName, int clientID) {
        checkSwitch(switchID);
        switches.get(switchID).name = switchName;
    }

    @Override
    public void setSwitchValue(int switchID, double value, int clientID) {
        checkSwitch(switchID);
        Switch s = switches.get(switchID);
        if (value < s.minSwitchValue) {
            throw new InvalidValueException("Cannot set switch value to " + value + " which is less than " + s.minSwitchValue);
        } else if (value > s.maxSwitchValue) {
            throw new InvalidValueException("Cannot set switch value to " + value + " which is more than " + s.maxSwitchValue);
        }

        s.value = value;
        s.state = value == s.maxSwitchValue || s.state;
    }

    @Override
    public double getSwitchStep(int switchID, int clientID) {
        checkSwitch(switchID);
        return 1;
    }
}
