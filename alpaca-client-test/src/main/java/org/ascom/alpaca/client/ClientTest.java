package org.ascom.alpaca.client;

public class ClientTest {
    public static void main(String[] args) {
        ClientManager manager = new ClientManager();
        System.out.println("Discovering devices...");
        manager.discoverDevices();
        System.out.println("Discovered " + manager.getClients().size() + " devices");

        SafetyMonitorClient client = manager.getClient(SafetyMonitorClient.class);
        if (client == null) {
            System.err.println("Cannot find a safety monitor device");
            System.exit(1);
        }
        // Need to connect to the device before we can use it
        client.connect();
        if (client.isSafe()) {
            System.out.println("Safe to open the roof");
        } else {
            System.out.println("Not safe to open the roof");
        }

        // Let's call in async mode
        client.isSafe(new AlpacaCallback<>() {
            @Override
            public void success(Boolean result) {
                if (result) {
                    System.out.println("Async safe to open the roof");
                } else {
                    System.out.println("Async not safe to open the roof");
                }
            }

            @Override
            public void error(AlpacaClientError error) {
                System.err.println("Error checking safety: " + error.getErrorMessage());
            }
        });

        ObservingConditionsClient ocClient = manager.getClient(ObservingConditionsClient.class);
        if (ocClient == null) {
            System.err.println("Cannot find an observing conditions device");
            System.exit(1);
        }
        ocClient.connect();
        System.out.println("The temperature is " + ocClient.getTemperature());

        ocClient.getName(new AlpacaCallback<>() {
            @Override
            public void success(String result) {
                System.out.println("The device name is " + result);
            }

            @Override
            public void error(AlpacaClientError error) {
                System.err.println("Error getting device name: " + error.getErrorMessage());
            }
        });

        // Give the async calls a chance to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);
    }
}
