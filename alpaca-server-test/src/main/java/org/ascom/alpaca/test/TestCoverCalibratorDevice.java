package org.ascom.alpaca.test;

import jakarta.inject.Singleton;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.CoverCalibratorDevice;
import org.ascom.alpaca.model.CalibratorState;
import org.ascom.alpaca.model.CoverState;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.InvalidValueException;
import org.ascom.alpaca.response.PropertyNotImplementedException;

@SuppressWarnings("unused")
@Singleton
public class TestCoverCalibratorDevice extends BaseDevice implements CoverCalibratorDevice {
    private int brightness = 0;
    private CoverState coverState = CoverState.Closed;
    private CalibratorState calibratorState = CalibratorState.Ready;

    public TestCoverCalibratorDevice() {
        super(DeviceType.CoverCalibrator, "Test Cover Calibrator Driver", 2);
        setDescription("Test Cover Calibrator Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    @Override
    public int getBrightness(int clientID) {
        return brightness;
    }

    @Override
    public boolean isCalibratorChanging(int clientID) {
        return false;
    }

    @Override
    public CalibratorState getCalibratorState(int clientID) {
        return calibratorState;
    }

    @Override
    public boolean isCoverMoving(int clientID) {
        return false;
    }

    @Override
    public CoverState getCoverState(int clientID) {
        return coverState;
    }

    @Override
    public int getMaxBrightness(int clientID) {
        return 255;
    }

    @Override
    public void turnCalibratorOff(int clientID) {
        calibratorState = CalibratorState.Off;
        brightness = 0;
    }

    @Override
    public void turnCalibratorOn(int clientID, int brightness) {
        if (brightness > 255) {
            throw new InvalidValueException("Cannot exceed the max brightness of 255");
        }
        this.brightness = brightness;
        calibratorState = CalibratorState.Ready;
    }

    @Override
    public void closeCover(int clientID) {
        coverState = CoverState.Closed;
    }

    @Override
    public void haltCover(int clientID) {
        throw new PropertyNotImplementedException("The test cover can't be halted");
    }

    @Override
    public void openCover(int clientID) {
        coverState = CoverState.Open;
    }
}
