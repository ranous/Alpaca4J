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
    public int getBrightness() {
        return brightness;
    }

    @Override
    public boolean isCalibratorChanging() {
        return false;
    }

    @Override
    public CalibratorState getCalibratorState() {
        return calibratorState;
    }

    @Override
    public boolean isCoverMoving() {
        return false;
    }

    @Override
    public CoverState getCoverState() {
        return coverState;
    }

    @Override
    public int getMaxBrightness() {
        return 255;
    }

    @Override
    public void turnCalibratorOff() {
        calibratorState = CalibratorState.Off;
        brightness = 0;
    }

    @Override
    public void turnCalibratorOn(int brightness) {
        if (brightness > 255) {
            throw new InvalidValueException("Cannot exceed the max brightness of 255");
        }
        this.brightness = brightness;
        calibratorState = CalibratorState.Ready;
    }

    @Override
    public void closeCover() {
        coverState = CoverState.Closed;
    }

    @Override
    public void haltCover() {
        throw new PropertyNotImplementedException("The test cover can't be halted");
    }

    @Override
    public void openCover() {
        coverState = CoverState.Open;
    }
}
