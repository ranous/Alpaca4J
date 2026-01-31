package org.ascom.alpaca.device;

/**
 * Interface for switch devices. The full documentation of the device interface can be found in the Alpaca documentation:
 * @see <a href="https://ascom-standards.org/api/#/Switch%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public interface SwitchDevice extends Device {
    int getMaxSwitch();
    boolean canAsync(int switchID);
    boolean canWrite(int switchID);
    boolean getSwitchState(int switchID);
    String getSwitchDescription(int switchID);
    String getSwitchName(int switchID);
    double getSwitchValue(int switchID);
    double getMinSwitchValue(int switchID);
    double getMaxSwitchValue(int switchID);
    boolean isStateChangeComplete(int switchID);
    void cancelAsync(int switchID);
    void setAsync(int switchID, boolean state);
    void setAsyncValue(int switchID, int value);
    void setSwitchState(int switchID, boolean state);
    void setSwitchName(int switchID, String switchName);
    void setSwitchValue(int switchID, double value);
    double getSwitchStep(int switchID);
}
