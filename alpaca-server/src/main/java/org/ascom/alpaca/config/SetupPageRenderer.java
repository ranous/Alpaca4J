package org.ascom.alpaca.config;

import org.ascom.alpaca.model.DeviceType;

/**
 * Interface for rendering configuration setup pages for Alpaca devices. The generated
 * HTML page includes configuration fields based on the provided device type and ID.
 * The resulting page should enable the user to update values and submit the updated configuration.
 * The HTML page should call PUT with the changed key/value pairs in a from parameter on
 * the device's update URL to apply the configuration changes.Each device has an
 * HTML endpoint of the form '/setup/v1/{deviceType}/{deviceID}/setup'.
 *
 * A device implementation uses the {@link org.ascom.alpaca.device.BaseDevice#setPageRenderer(SetupPageRenderer) setPageRenderer}
 * method to register a custom renderer for the device's setup page.
 */
public interface SetupPageRenderer {
    /**
     * Renders an HTML page that lets the client edit device configuration attributes.  This is the
     * result returned to the client when they use the ASCOM Alpaca setup endpoint.  Typically,
     * this uses form data to submit the updated configuration to the device's update URL.  In
     * this framework, the endpoint to apply the configuration changes is the same as the setup,
     * but as a PUT request, with the updated configuration key/value pairs in the body of the request in a form.
     *
     * @param title the title of the device should be displayed on the setup page.
     * @param deviceType the type of device being configured.
     * @param deviceID the unique identifier for the device being configured.
     * @param config The current configuration object for the device.
     * @return The HTML string representing the setup page.
     */
    String renderSetupPage(String title, DeviceType deviceType, int deviceID, Object config);
}
