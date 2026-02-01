# **Alpaca Server Test Module**
## Overview
The `alpaca-server-test` module demonstrates how to implement Alpaca-compliant devices in the server 
implementation. It provides tools and frameworks to simulate devices and validate their behavior 
as per the specifications defined by the Alpaca protocol. At its core, this module leverages 
the concept of device abstraction and interfaces to facilitate the creation of new devices for 
testing purposes.

This document provides guidance on how to extend functionality by creating device implementations 
and running the resulting application.

## BaseDevice and Device Interfaces
To create a new device in the `alpaca-server-test` module, you must subclass the `BaseDevice` class
and implement the appropriate device interface from the `org.ascom.alpaca.device` package.

### **Steps to Create a Custom Device**
1. **Extend the BaseDevice Class**
   The `BaseDevice` class provides core functionality and shared behavior for all devices. Any 
custom device you create must inherit from `BaseDevice`. This ensures that the base requirements 
and lifecycle methods of an Alpaca device are fulfilled.
2. **Implement a Specific Device Interface**
   Each device type in the Alpaca protocol has a corresponding interface under 
the `org.ascom.alpaca.device` package. For example:
    - To create a camera device, implement `CameraDevice`.
    - To create a telescope device, implement `TelescopeDevice`.
    - For observing conditions, implement `ObservingConditionsDevice`.

   These interfaces define specific methods that the device must provide to conform to the protocol.
3. **Override Required Methods**
   You need to override the methods defined in the `BaseDevice` class as well as the specific 
interface. This involves handling specific device properties and actions.
   For example, if you are creating an observing conditions device (`ObservingConditionsDevice`), 
you must implement methods like `getTemperature()` and `getHumidity()` to return meaningful test data 
or mock values.
4. **Handle Error Conditions**
   For various error conditions, appropriate exceptions from the `org.ascom.alpaca.response` 
package should be thrown. These exceptions correspond directly to the various Alpaca error codes 
expected by Alpaca clients. Examples include:
    - `ActionNotImplementedException` - when an unsupported action is requested.
    - `PropertyNotImplementedException` - when a property is not supported by the device.
    - `InvalidValueException` - when a parameter or value is invalid.

These exceptions are automatically converted by the Alpaca4J runtime into proper Alpaca error 
responses, ensuring the client receives standardized error codes and messages.

### **Example: Observing Conditions Device**
The `TestObservingConditionsDevice` class provides a concrete example of how to implement a custom 
device for the "observing conditions" use case. This implementation extends the `BaseDevice` class 
and implements the `ObservingConditionsDevice` interface, adhering to the protocol requirements.
#### Highlights of `TestObservingConditionsDevice`:
- Implements all required methods from the `ObservingConditionsDevice` interface.
- Provides mock values for device-specific properties such as temperature, wind speed, and humidity.
- Overrides lifecycle methods (e.g., `refresh()`) to simulate device-specific behavior.
- Uses exceptions from the `org.ascom.alpaca.response` package to handle error scenarios, ensuring accurate client error handling.

## Configuration
Custom device implementations can leverage dependency injection for dynamic configuration of properties. 
For instance, the `TestObservingConditionsDevice` accesses the driver version as a configurable 
property via the `@ConfigProperty` annotation:
``` properties
# Example configuration in microprofile-config.properties
test.driver.version=1.2.0
```
The configuration property `test.driver.version` allows the runtime to determine the device version 
dynamically or override it via a system property.

## Running the Application
Once your custom device implementations are complete, you can run the resulting application to begin 
testing. The module relies on Quarkus as its runtime, so you have two main methods to run the 
application:
### **Using the Quarkus Development Command**
Quarkus provides a convenient development mode that allows for live reload of code changes. You can run 
the application in development mode using the quakus command:

Quarkus provides a convenient development mode that allows for live reload of code changes. You can run 
the application in development mode using:
``` bash
quarkus dev
```
This starts the application and watches for code changes, making it perfect for active development 
and testing.

### **Running the Compiled Application**
After building the project, you can run the application directly using the jar file created in 
the `target` directory. This is useful for running the application in a production-like environment. 
Follow these steps to run the compiled application:
1. **Build the Application**
   Use Maven to package the application:
2. **Run the Application**
   Navigate to the `target` directory and run the generated application jar:
``` bash
   java -jar target/quarkus-app/quarkus-run.jar
```
This will start the Alpaca server that includes your device implementation. The application will be 
available for client testing via the defined endpoints.

## Summary
To effectively create and test devices using the `alpaca-server-test` module:
1. Subclass the `BaseDevice` class for core Alpaca functionality.
2. Implement the appropriate interface for your device type (`ObservingConditionsDevice`, `CameraDevice`, etc.).
3. Override required methods to provide mock or test data.
4. Throw appropriate exceptions from the `org.ascom.alpaca.response` package for error handling.
5. Build and run the application using either:
    - `quarkus dev` for live development, or
    - `java -jar target/quarkus-app/quarkus-run.jar` for running your packaged application.

By following these steps, your devices will conform to the Alpaca protocol, integrate seamlessly with the Alpaca4J runtime, and support robust error handling for client interactions.



