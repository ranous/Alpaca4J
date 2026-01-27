package org.ascom.alpaca.device;

/**
 * Interface for devices that can monitor and report safety status.  The full documentation of the
 * SafetyMonitorDevice interface can be found in the Alpaca documentation.
 * @see <a href="https://ascom-standards.org/api/#/SafetyMonitor%20Specific%20Methods">ASCOM Alpaca Secification</a>
 */
@SuppressWarnings("unused")
public interface SafetyMonitorDevice extends Device {
    /**
     * Returns true if the monitored state is safe for use.
     * @param clientID the client ID
     * @return true if the monitored service is safe, false otherwise
     * @throws org.ascom.alpaca.response.NotConnectedException if the client is not connected
     * @throws org.ascom.alpaca.response.ServerException if some other error occurred during the operation
     */
    boolean isSafe(int clientID);
}
