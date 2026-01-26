package org.ascom.alpaca.config;

import org.ascom.alpaca.model.DeviceType;

/**
 * Interface for rendering configuration setup pages for Alpaca devices. The generated
 * HTML page includes configuration fields based on the provided device type and ID.
 * The resulting page should enable the user to update values and submit the updated configuration.
 * The HTML page should call PUT with the changed key/value pairs in a from parameter on
 * the device's update URL to apply the configuration changes.Each device has an
 * HTML endpoint of the form '/setup/v1/{deviceType}/{deviceID}/setup'.
 */
public interface SetupPageRenderer {
    String renderSetupPage(String title, DeviceType deviceType, int deviceID, Object config);
}
