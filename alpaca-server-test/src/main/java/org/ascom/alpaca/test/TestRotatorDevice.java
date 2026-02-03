package org.ascom.alpaca.test;

import jakarta.inject.Singleton;
import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.RotatorDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.response.InvalidValueException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@SuppressWarnings("unused")
@Singleton
public class TestRotatorDevice extends BaseDevice implements RotatorDevice {
    private double position = 0;
    private double mechanicalPosition = 0;
    private double targetPosition = 0;
    private double offset = 0;
    private boolean isMoving = false;
    private boolean isReversed = false;

    // The version of the driver is injected from the microprofile-config.properties file and can be overridden
    // by the system property test.driver.version
    public TestRotatorDevice(@ConfigProperty(name="test.driver.version", defaultValue = "1.0") String deviceVersion) {
        super(DeviceType.Rotator, "Test Rotator", RotatorDevice.interfaceVersion, deviceVersion);
        setDescription("Test Rotator Device");
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
