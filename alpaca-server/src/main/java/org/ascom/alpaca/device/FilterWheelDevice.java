package org.ascom.alpaca.device;

import java.util.List;

@SuppressWarnings("unused")
public interface FilterWheelDevice extends Device {
    List<Integer> getFocusOffsets(int clientID);
    List<String> getFilterNames(int clientID);
    int getPosition(int clientID);
    void setPosition(int clientID, int position);
}
