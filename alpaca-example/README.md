# Alpaca Observing Conditions and Safety Monitor Drivers 

This project demonstrates how to use the Alpaca4J framework to develop [ASCOM Alpaca](https://ascom-standards.org/AlpacaDeveloper/Index.htm) 
Observing Conditions and Safety Monitor drivers. These drivers read weather data from a data file
in the format used by the Boltwood cloud sensors.  Besides the Boltwood device, a number of
other weather monitoring devices can produce weather data in this format.

This service uses the BoltwoodFilePoller to poll that file for weather data.  It stores the
weather measurements into an ObservingConditionsDB database.  When a client queries the
ObservingConditionsDriver for weather data, it queries the database to obtain the current
weather measurements.  The ObservingConditionsDB can store multiple measurements for a given
measurement type to enable averaging the measurements over a configured time period.

This service also provides a SafetyMonitor device that can use the ObservingConditionsDB to determine
whether the weather is safe for observing.  It uses the rain rate to determine unsafe conditions.  It
can optionally use the wind speed to determine whether the wind is too fast to observe.

The Observing Conditions and Safety Monitor can be used with different weather monitoring devices.
Some sort of polling mechanism is required to get the weather measurements from whatever source, and
as long as the measurements are stored in the ObservingConditionsDB, the drivers can use that as well.

The safety monitor also demonstrates how to use the Alpaca setup mechanism to configure the property
to use for wind speed.  It creates a simple holder config object that defines the property value to 
determine the max wind speed and uses the @Key annotation to specify the property name. The device 
obtains an instance of that configuration object from the ConfigManger and uses the property value from
that instance to determine whether the wind speed is too fast to observe.  If the client changes
the wind speed property value, the configuration object is updated immediately, so later
queries will use the new value.  Beyond specifying the config object and registering the class
with BaseDevice, the Alpaca framework handles the actual handling of the setup mechanism and any
updates the client submits.  The device's config object is automatically updated.

Rendering of the HTML setup page is handled by an implementation of the SetupPageRenderer interface
which uses Quarkus's Qute template engine.  The HTML template is generic and can present any
set of configuration properties for the client to configure.  The SetupPageRenderer uses the 
devices Config object and reflection to determine the properties that can be configured.

A user wishing to implement their own Alpaca device can copy this project as a template to start from.
This particular project uses the [Quarkus](https://quarkus.io/) runtime to run the service. In theory, any 
[MicroProfile](https://microprofile.io/) compliant runtime could be used instead.  
The Alpaca4J framework is not dependent on any particular runtime.


The application can be run in two modes:

### Development Mode

To run in development mode with hot reload:
```bash
./mvnw quarkus:dev
```

Development mode uses the `%dev` configuration profile, which enables debug logging and uses local paths 
for device state files.

### Production Mode

To build and run in production mode:
```bash
./mvnw clean package
java -jar target/alpaca-example-*-runner.jar
```

Production mode uses the `%prod` configuration profile with appropriate settings for deployed systems.

## Configuration Architecture

This example demonstrates the Alpaca4J framework's flexible multi-layered configuration system with three 
distinct levels:

### 1. Base Configuration (`src/main/resources/application.properties`)

This file contains the default configuration values that are built into the application. These are fallback 
values used when no overrides are provided. Key settings include:

- HTTP server port and host binding
- Logging configuration
- Quarkus-specific settings

This file should contain sensible defaults that allow the application to run out of the box.

### 2. Deployment Configuration (`config/application.properties`)

This file **overrides** values from the resource version and contains deployment-specific settings. 
This is the primary configuration file for customizing how the service runs in different environments.
This file is read from the config directory relative to the CWD of the application.

**Key features:**
- Uses `%dev.` and `%prod.` prefixes to define environment-specific properties
- Development profile (`%dev.`) uses local file paths and debug logging
- Production profile (`%prod.`) uses system paths (`/var/lib/ascom/`, `/var/log/`) and production logging
- Controls where device state and setup override files are stored
- Configures application-specific settings like Boltwood file polling

Example properties:
```properties
# Development-specific
%dev.alpaca.device-state.file=devices.json
%dev.quarkus.log.category."com.ranous".level=DEBUG

# Production-specific
%prod.alpaca.device-state.file=/var/lib/ascom/devices.json
%prod.quarkus.log.file.enabled=true
```

The `config/application.properties` file takes precedence over `src/main/resources/application.properties`, allowing you to customize the deployment without modifying the built-in defaults.

### 3. Alpaca Setup Configuration (`config/setup_config.properties`)

This file defines the **user-configurable properties** that are exposed through the Alpaca setup web interface. These are device-specific parameters that end users can modify at runtime through the ASCOM setup mechanism.

**How it works:**
- Properties in this file become editable fields in the HTML setup page
- When a user changes a value via the setup interface, it's immediately applied to the running device
- Changes are persisted to `setup_overrides.properties` (location defined in `application.properties`)
- The original `setup_config.properties` remains unchanged as the default
- To reset to defaults, simply delete the `setup_overrides.properties` file

**Example:** The SafetyMonitor's maximum safe wind speed is defined in `setup_config.properties`:
```properties
boltwood.safetymonitor.max_safe_windspeed=20
```

This value can be changed by users through the Alpaca setup web UI without restarting the device, 
and the new value takes effect immediately.

### Configuration Precedence

The configuration system follows this precedence order (highest to lowest):
1. `setup_overrides.properties` (user changes via Alpaca setup)
2. `setup_config.properties` (Alpaca-exposed defaults)
3. `config/application.properties` (deployment overrides with `%dev`/`%prod` profiles)
4. `src/main/resources/application.properties` (built-in defaults)

This layered approach allows you to:
- Ship sensible defaults in the resources
- Customize deployment without changing source code
- Allow end users to configure device behavior through the standard Alpaca setup mechanism
- Easily reset to defaults by removing override files