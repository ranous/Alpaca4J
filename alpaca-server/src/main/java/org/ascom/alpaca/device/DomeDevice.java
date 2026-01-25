package org.ascom.alpaca.device;

import org.ascom.alpaca.model.ShutterState;

/**
 * Interface for dome devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * <a href="https://ascom-standards.org/api/#/Dome%20Specific%20Methods"/>.
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public interface DomeDevice extends Device {

    double getAltitude(int clientID);
    boolean atHome(int clientID);
    boolean atPark(int clientID);
    double getAzimuth(int clientID);
    boolean canFindHome(int clientID);
    boolean canPark(int clientID);
    boolean canSetAltitude(int clientID);
    boolean canSetAzimuth(int clientID);
    boolean canSetPark(int clientID);
    boolean canSetShutter(int clientID);
    boolean canSlave(int clientID);
    boolean canSyncAzimuth(int clientID);
    ShutterState getShutterStatus(int clientID);
    boolean isSlaved(int clientID);
    void setSlaved(int clientID, boolean slaved);
    boolean isSlewing(int clientID);
    void abortSlew(int clientID);
    void closeShutter(int clientID);
    void findHome(int clientID);
    void openShutter(int clientID);
    void park(int clientID);
    void setPark(int clientID);
    void slewToAltitude(int clientID, double altitude);
    void slewToAzimuth(int clientID, double azimuth);
    void syncToAzimuth(int clientID, double azimuth);
}
