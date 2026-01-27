package org.ascom.alpaca.device;

import org.ascom.alpaca.model.CalibratorState;
import org.ascom.alpaca.model.CoverState;

/**
 * Interface for cover calibrator devices.  The full documentation of the device interface can be found in the
 * Alpaca documentation.
 * @see <a href="https://ascom-standards.org/api/#/CoverCalibrator%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings({"unused", "SameReturnValue"})
public interface CoverCalibratorDevice extends Device {
    int getBrightness(int clientID);
    boolean isCalibratorChanging(int clientID);
    CalibratorState getCalibratorState(int clientID);
    boolean isCoverMoving(int clientID);
    CoverState getCoverState(int clientID);
    int getMaxBrightness(int clientID);
    void turnCalibratorOff(int clientID);
    void turnCalibratorOn(int clientID, int brightness);
    void closeCover(int clientID);
    void haltCover(int clientID);
    void openCover(int clientID);
}
