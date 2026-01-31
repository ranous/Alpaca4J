package org.ascom.alpaca.test;

import jakarta.inject.Singleton;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.RotatorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.InvalidValueException;

@SuppressWarnings("unused")
@Singleton
public class TestRotatorDevice extends BaseDevice implements RotatorDevice {
    private double position = 0;
    private double mechanicalPosition = 0;
    private double targetPosition = 0;
    private double offset = 0;
    private boolean isMoving = false;
    private boolean isReversed = false;


    public TestRotatorDevice() {
        super(DeviceType.Rotator, "Test Rotator", 4);
        setDescription("Test Rotator Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    @Override
    public boolean canReverse() {
        return true;
    }

    @Override
    public boolean isMoving() {
        return isMoving;
    }

    @Override
    public double getMechanicalPosition() {
        return mechanicalPosition;
    }

    @Override
    public double getPosition() {
        return position;
    }

    @Override
    public boolean isReversed() {
        return isReversed;
    }

    @Override
    public void setReversed(boolean reversed) {
        this.isReversed = reversed;
    }

    @Override
    public double getStepSize() {
        return .1;
    }

    @Override
    public double getTargetPosition() {
        return targetPosition;
    }

    @Override
    public void halt() {
        isMoving = false;
    }

    @Override
    public void move(double position) {
        this.position+=position;
        if (this.position > 360) {
            this.position = this.position % 360;
        }
        if (this.position < 0) {
            this.position = 360 + this.position;
        }
        this.targetPosition+=this.position;
        this.mechanicalPosition+=this.position + offset;
        if (this.mechanicalPosition > 360) {
            this.mechanicalPosition = this.mechanicalPosition % 360;
        }
    }

    @Override
    public void moveAbsolute(double position) {
        if (position < 0 || position > 360) {
            throw new InvalidValueException("Position must be between 0 and 360.");
        }
        this.position = position;
        this.targetPosition = position;
        this.mechanicalPosition = position + offset;
    }

    @Override
    public void moveMechanical(double position) {
        this.mechanicalPosition = position;
        this.position = this.mechanicalPosition + offset;
        this.targetPosition = this.position;
    }

    @Override
    public void sync(double position) {
        this.position = position;
        this.offset = this.position - this.mechanicalPosition;
    }
}
