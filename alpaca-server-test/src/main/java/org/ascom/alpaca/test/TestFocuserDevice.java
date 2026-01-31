package org.ascom.alpaca.test;

import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.FocuserDevice;
import org.ascom.alpaca.model.DeviceType;

import jakarta.inject.Singleton;
import org.ascom.alpaca.model.StateValue;

import java.util.List;

@SuppressWarnings("unused")
@Singleton
public class TestFocuserDevice extends BaseDevice implements FocuserDevice {
    private int position;
    private final int maxPosition = 10000;
    private boolean isTemperatureCompensating = true;
    private boolean isMoving = false;
    private long moveStartTime = 0;

    public TestFocuserDevice() {
        super(DeviceType.Focuser, "Test Focuser Driver", 4);
        setDescription("Test Focuser Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }


    @Override
    public List<StateValue> getDeviceState() {
        List<StateValue> deviceState = super.getDeviceState();
        deviceState.add(new StateValue("IsMoving", isMoving()));
        deviceState.add(new StateValue("Position", getPosition()));
        deviceState.add(new StateValue("Temperature", getTemperature()));
        return deviceState;
    }

    @Override
    public boolean canAbsoluteFocus() {
        return true;
    }

    @Override
    public boolean isMoving() {
        if (isMoving && System.currentTimeMillis() - moveStartTime > 1000) {
            isMoving = false;
        }
        return isMoving;
    }

    @Override
    public int getMaxIncrement() {
        return 100;
    }

    @Override
    public int getMaxStep() {
        return maxPosition;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public double getStepSize() {
        return 100;
    }

    @Override
    public boolean isTemperatureCompensating() {
        return isTemperatureCompensating;
    }

    @Override
    public void setTemperatureCompensation(boolean state) {
        isTemperatureCompensating = state;
    }

    @Override
    public boolean hasTemperatureCompensation() {
        return true;
    }

    @Override
    public double getTemperature() {
        return 19.5;
    }

    @Override
    public void haltFocuser() {
        isMoving = false;
    }

    @Override
    public void moveToPosition(int position) {
        if (position < 0) {
            position = 0;
        } else if (position > maxPosition) {
            position = maxPosition;
        }
        this.position = position;
        isMoving = true;
        moveStartTime = System.currentTimeMillis();
    }
}
