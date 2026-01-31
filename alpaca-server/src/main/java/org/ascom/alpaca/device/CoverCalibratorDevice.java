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
    int getBrightness();
    boolean isCalibratorChanging();
    CalibratorState getCalibratorState();
    boolean isCoverMoving();
    CoverState getCoverState();
    int getMaxBrightness();
    void turnCalibratorOff();
    void turnCalibratorOn(int brightness);
    void closeCover();
    void haltCover();
    void openCover();
}
