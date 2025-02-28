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
    public List<StateValue> getDeviceState(int clientID) {
        checkConnectionStatus(clientID);
        List<StateValue> deviceState = super.getDeviceState(clientID);
        deviceState.add(new StateValue("IsMoving", isMoving(clientID)));
        deviceState.add(new StateValue("Position", getPosition(clientID)));
        deviceState.add(new StateValue("Temperature", getTemperature(clientID)));
        return deviceState;
    }

    @Override
    public boolean canAbsoluteFocus(int clientID) {
        return true;
    }

    @Override
    public boolean isMoving(int clientID) {
        if (isMoving && System.currentTimeMillis() - moveStartTime > 1000) {
            isMoving = false;
        }
        return isMoving;
    }

    @Override
    public int getMaxIncrement(int clientID) {
        return 100;
    }

    @Override
    public int getMaxStep(int clientID) {
        return maxPosition;
    }

    @Override
    public int getPosition(int clientID) {
        return position;
    }

    @Override
    public double getStepSize(int clientID) {
        return 100;
    }

    @Override
    public boolean isTemperatureCompensating(int clientID) {
        return isTemperatureCompensating;
    }

    @Override
    public void setTemperatureCompensation(int clientID, boolean state) {
        isTemperatureCompensating = state;
    }

    @Override
    public boolean hasTemperatureCompensation(int clientID) {
        return true;
    }

    @Override
    public double getTemperature(int clientID) {
        return 19.5;
    }

    @Override
    public void haltFocuser(int clientID) {
        isMoving = false;
    }

    @Override
    public void moveToPosition(int clientID, int position) {
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
