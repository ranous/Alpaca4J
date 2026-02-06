package org.ascom.alpaca.example;

import org.ascom.alpaca.config.DefaultValue;
import org.ascom.alpaca.config.Description;
import org.ascom.alpaca.config.Key;

/**
 * Configuration for the Boltwood Safety Monitor device that can be changed at runtime via the Alpaca setup mechanism.
 * The BoltwoodSafteyMonitor registers this class with the base BaseDevice.setPageRendererConfig method.  The
 * BaseDevice gets an instance of this class from the ConfigManager and sends the config object to
 * the SetupPageRenderer to render the configuration page for the device.  The SetupPageRenderer inspects this
 * class and uses the @Key annotation to identify which attributes should be displayed on the setup page for
 * the device.  The @Description tag is used to provide a human-readable description of the attribute for display
 * on the setup page.
 */
public class ExampleSafetyMonitorConfig {
    @Key("boltwood.safetymonitor.max_safe_windspeed") @DefaultValue("10")
    @Description("The wind speed in m/s when conditions are unsafe.  If zero, windspeed is not considered in safety")
    public long maxSafeWindSpeed = 0;
}
