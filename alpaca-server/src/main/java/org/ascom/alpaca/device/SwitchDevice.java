package org.ascom.alpaca.device;

@SuppressWarnings({"unused", "SameReturnValue"})
public interface SwitchDevice extends Device {
    int getMaxSwitch(int clientID);
    boolean canAsync(int switchID, int clientID);
    boolean canWrite(int switchID, int clientID);
    boolean getSwitchState(int switchID, int clientID);
    String getSwitchDescription(int switchID, int clientID);
    String getSwitchName(int switchID, int clientID);
    double getSwitchValue(int switchID, int clientID);
    double getMinSwitchValue(int switchID, int clientID);
    double getMaxSwitchValue(int switchID, int clientID);
    boolean isStateChangeComplete(int switchID, int clientID);
    void cancelAsync(int switchID, int clientID);
    void setAsync(int switchID, boolean state, int clientID);
    void setAsyncValue(int switchID, int value, int clientID);
    void setSwitchState(int switchID, boolean state, int clientID);
    void setSwitchName(int switchID, String switchName, int clientID);
    void setSwitchValue(int switchID, double value, int clientID);
    double getSwitchStep(int switchID, int clientID);
}
