
# Alpaca4J Example Client

This module contains a simple client application example that demonstrates how to interact with 
an Alpaca-based device. It is designed as a practical implementation of the Alpaca Client 
Library (`alpaca4j`), showcasing how to establish communication with Alpaca devices in Java 
applications.

## Overview

The Alpaca client library leverages the **Retrofit2** library to provide a robust and flexible 
REST client runtime. Retrofit2 is widely used in Java and Android development, making the 
Alpaca client library Android-compatible. The library supports both **synchronous** and
**asynchronous** methods for communication with Alpaca-based devices.   

### Features:
- **Synchronous Requests**: Provides blocking API calls for simple and predictable implementations.
- **Asynchronous Requests**: Allows for non-blocking calls using callbacks, making it ideal for 
responsive applications.
- **Retrofit2 Integration**: Provides a familiar interface for developers acquainted with 
Retrofit2.  
- **Cross-Platform Compatibility**: Suitable for both standard Java applications and 
Android-based systems.

Please note that this client's asynchronous methods are independent of any asychronous Alpaca 
operations. This means if you're using the async version of a call to an asynch Alpaca call, 
you'll still need to poll the appropriate Alpaca methods to check on completion.

## Requirements

To run or develop the Alpaca client test module, you need the following:

- **Java 11 or higher** (Java 21 is recommended for optimal performance).
- **Maven** or **Gradle** for dependency management.
- A running instance of an **Alpaca-compatible device** for testing.

## Getting Started

Follow these steps to use the `alpaca-client-test` module:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repository/alpaca4j.git
   cd alpaca4j/alpaca-client-test
   ```

2. **Build the Module**:
   Ensure that your environment has Maven or Gradle installed, and then run:
   ```bash
   mvn clean install
   ```

3. **Run the Client Application**:
   Execute the client test to interact with an Alpaca-based device:
   ```bash
   java -jar target/alpaca-client-test.jar
   ```

4. **Device Configuration**:
   Configure the Alpaca device details such as host, port, and available capabilities in the application.

5. **Testing**:
   The module includes basic test implementations to verify client-to-device communication. 
Extend these tests to match your specific use cases.

## Key Components

- `ClientTest.java`: The main entry point for the client application. It contains examples of 
both synchronous and asynchronous calls to the Alpaca device.

## Example Usage

Here's a basic example of how to interact with an Alpaca device directly constructing a 
client and using its asynchronous interface:

```java
AlpacaDeviceClient deviceClient = new AlpacaDeviceClient("http://localhost", 11111);

// Synchronous call to get a device's status
DeviceStatus status = deviceClient.getDeviceStatus();
System.out.println("Device status: " + status);

// Asynchronous call to initiate a command
deviceClient.startDeviceActionAsync(action -> {
    if (action.isSuccess()) {
        System.out.println("Command executed successfully!");
    } else {
        System.err.println("Failed to execute the command: " + action.getMessage());
    }
});
```


## Discovering Clients on the Network

Instead of directcly constructing the device client, the Alpaca client library provides a way to
discover Alpaca-compatible devices on the network via the Alpaca Discovery Protocol by using 
the `ClientManager`. This allows you to dynamically find devices instead of hardcoding 
their IPs or addresses.

Here is an example of how you can use the `ClientManager` to discover clients on the network and select a specific client instance:

### Example Code

```java
import alpaca4j.ClientManager;
import alpaca4j.AlpacaDeviceClient;

import java.util.List;

public class ClientDiscoveryExample {
    public static void main(String[] args) {
        try {
            // Create an instance of ClientManager
            ClientManager clientManager = new ClientManager();

            // Discover all available clients on the local network
            System.out.println("Discovering Alpaca clients on the network...");
            List<AlpacaDeviceClient> clients = clientManager.discoverClients();

            if (clients.isEmpty()) {
                System.out.println("No clients found on the network.");
                return;
            }

            // List discovered clients
            System.out.println("Discovered Alpaca clients:");
            for (int i = 0; i < clients.size(); i++) {
                AlpacaDeviceClient client = clients.get(i);
                System.out.printf("%d: %s (%s)%n", i + 1, client.getDeviceName(), client.getBaseUrl());
            }

            // Select a client instance (e.g., the first one)
            AlpacaDeviceClient selectedClient = clients.get(0); // Alternatively, use user input to select a client
            System.out.printf("Selected client: %s (%s)%n", selectedClient.getDeviceName(), selectedClient.getBaseUrl());

            // Perform operations on the selected client
            System.out.println("Getting device information...");
            String deviceInformation = selectedClient.getDeviceInformation();
            System.out.println("Device Information: " + deviceInformation);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error during client discovery or interaction: " + e.getMessage());
        }
    }
}
```

### Explanation

1. **Create a Client Manager**: The `ClientManager` is responsible for discovering Alpaca clients on the network.
2. **Discover Clients**: Use the `discoverClients()` method that returns a list of all discovered clients.
3. **Select a Client**: You can either programmatically select one or prompt the user to select from a list of discovered clients.
4. **Interact with the Client**: Once a client is selected, you can use it to query device information or perform operations.

### Notes

- Ensure the Alpaca devices are accessible over the network and follow the Alpaca protocol.
- The discovery functionality relies on network broadcasts and may need sufficient network permissions depending on the environment.
- Consider error handling for network timeouts or device unavailability.

This approach provides a dynamic and flexible way to work with Alpaca devices within a networked environment.




## Contributing

Contributions are welcome! If you find bugs or have suggestions for improvements, feel free to open an issue or a pull request. When contributing, please ensure that your changes include appropriate tests and documentation updates.

## Resources

- [Alpaca ASCOM Initiative](https://ascom-standards.org/)
- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Java Development Kit](https://openjdk.org/)

