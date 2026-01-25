package org.ascom.alpaca.device;

/**
 * Interface for devices that can monitor and report safety status.  The full
 * documentation of the SafetyMonitorDevice interface can be found in the
 * Alpaca documentation: <a href="https://ascom-standards.org/api/#/SafetyMonitor%20Specific%20Methods">...</a>
 */
@SuppressWarnings("unused")
public interface SafetyMonitorDevice extends Device {
    boolean isSafe(int clientID);
}
