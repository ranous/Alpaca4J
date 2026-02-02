package org.ascom.alpaca.device;

/**
 * Interface for rotator devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * @see <a href="https://ascom-standards.org/api/#/Rotator%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public interface RotatorDevice extends Device {
    /**
     * The version of the Alpaca Rotator interface. Classes implementing this interface
     * should return this from the {@link #getInterfaceVersion}.
     */
    int interfaceVersion = 4;

    boolean canReverse();
    boolean isMoving();
    double getMechanicalPosition();
    double getPosition();
    boolean isReversed();
    void setReversed(boolean reversed);
    double getStepSize();
    double getTargetPosition();
    void halt();
    void move(double position);
    void moveAbsolute(double position);
    void moveMechanical(double position);
    void sync(double position);
}
