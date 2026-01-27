package org.ascom.alpaca.device;

import java.util.List;

/**
 * Interface for filter wheel devices.
 * The full documentation of the device interface can be found in the Alpaca documentation:
 * @see <a href="https://ascom-standards.org/api/#/FilterWheel%20Specific%20Methods">ASCOM Alpaca Specification</a>
 */
@SuppressWarnings("unused")
public interface FilterWheelDevice extends Device {
    List<Integer> getFocusOffsets(int clientID);
    List<String> getFilterNames(int clientID);
    int getPosition(int clientID);
    void setPosition(int clientID, int position);
}
