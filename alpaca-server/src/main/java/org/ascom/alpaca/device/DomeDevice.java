package org.ascom.alpaca.device;

import org.ascom.alpaca.model.ShutterState;

/**
 * Interface for dome devices. The full documentation of the device interface can be found in the Alpaca documentation.
 * @see <a href="https://ascom-standards.org/api/#/Dome%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public interface DomeDevice extends Device {
    /**
     * The version of the Alpaca Dome interface. Classes implementing this interface
     * should return this from the {@link #getInterfaceVersion}.
     */
    int interfaceVersion = 3;

    double getAltitude();
    boolean atHome();
    boolean atPark();
    double getAzimuth();
    boolean canFindHome();
    boolean canPark();
    boolean canSetAltitude();
    boolean canSetAzimuth();
    boolean canSetPark();
    boolean canSetShutter();
    boolean canSlave();
    boolean canSyncAzimuth();
    ShutterState getShutterStatus();
    boolean isSlaved();
    void setSlaved(boolean slaved);
    boolean isSlewing();
    void abortSlew();
    void closeShutter();
    void findHome();
    void openShutter();
    void park();
    void setPark();
    void slewToAltitude(double altitude);
    void slewToAzimuth(double azimuth);
    void syncToAzimuth(double azimuth);
}
