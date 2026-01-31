package org.ascom.alpaca.device;

/**
 * Interface for focuser devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * @see <a href="https://ascom-standards.org/api/#/FilterWheel%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public interface FocuserDevice extends Device {
    boolean canAbsoluteFocus();
    boolean isMoving();
    int getMaxIncrement();
    int getMaxStep();
    int getPosition();
    double getStepSize();
    boolean isTemperatureCompensating();
    void setTemperatureCompensation(boolean state);
    boolean hasTemperatureCompensation();
    double getTemperature();
    void haltFocuser();
    void moveToPosition(int position);
}
