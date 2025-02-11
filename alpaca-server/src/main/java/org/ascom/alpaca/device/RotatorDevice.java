package org.ascom.alpaca.device;

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
