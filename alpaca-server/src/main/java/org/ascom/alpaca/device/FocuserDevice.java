package org.ascom.alpaca.device;

@SuppressWarnings({"unused", "SameReturnValue"})
public interface FocuserDevice extends Device {
    boolean canAbsoluteFocus(int clientID);
    boolean isMoving(int clientID);
    int getMaxIncrement(int clientID);
    int getMaxStep(int clientID);
    int getPosition(int clientID);
    double getStepSize(int clientID);
    boolean isTemperatureCompensating(int clientID);
    void setTemperatureCompensation(int clientID, boolean state);
    boolean hasTemperatureCompensation(int clientID);
    double getTemperature(int clientID);
    void haltFocuser(int clientID);
    void moveToPosition(int clientID, int position);
}
