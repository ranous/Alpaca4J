package org.ascom.alpaca.device;

/**
 * Interface for rotator devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * @see <a href="https://ascom-standards.org/api/#/Rotator%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public interface RotatorDevice extends Device {
    boolean canReverse(int clientID);
    boolean isMoving(int clientID);
    double getMechanicalPosition(int clientID);
    double getPosition(int clientID);
    boolean isReversed(int clientID);
    void setReversed(int clientID, boolean reversed);
    double getStepSize(int clientID);
    double getTargetPosition(int clientID);
    void halt(int clientID);
    void move(int clientID, double position);
    void moveAbsolute(int clientID, double position);
    void moveMechanical(int clientID, double position);
    void sync(int clientID, double position);
}
