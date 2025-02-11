package org.ascom.alpaca.device;

@SuppressWarnings("unused")
public interface SafetyMonitorDevice extends Device {
    boolean isSafe(int clientID);
}
