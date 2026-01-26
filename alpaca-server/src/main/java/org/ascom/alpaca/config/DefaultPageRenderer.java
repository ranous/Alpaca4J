package org.ascom.alpaca.config;

import org.ascom.alpaca.model.DeviceType;

public class DefaultPageRenderer implements PageRenderer {
    @Override
    public String renderSetupPage(String title, DeviceType deviceType, int deviceID, Object config) {
        return "Device " + title  + " doesn't have any setup parameters";
    }
}
