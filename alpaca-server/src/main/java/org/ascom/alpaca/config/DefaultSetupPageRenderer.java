package org.ascom.alpaca.config;

import org.ascom.alpaca.model.DeviceType;

/**
 * Default implementation of the SetupPageRenderer interface which will be used if no other
 * implemenation has been registered.  This implementation provides a default HTML page for
 * devices that don't have any client-updatable configuration.
 */
public class DefaultSetupPageRenderer implements SetupPageRenderer {
    @Override
    public String renderSetupPage(String title, DeviceType deviceType, int deviceID, Object config) {
        return "Device " + title  + " doesn't have any setup parameters";
    }
}
