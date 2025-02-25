package org.ascom.alpaca.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.LogManager;

public class ClientTest {
    public static void main(String[] args) {
        initLogger(ClientTest.class);
        syncTest();
        asyncTest();

        System.exit(0);
    }

    static void syncTest() {
        ClientManager manager = ClientManager.getInstance();
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
    }

    static void asyncTest() {
        ClientManager manager = ClientManager.getInstance();
        System.out.println("Discovering devices async...");
        manager.discoverDevices(new AlpacaCallback<>() {
            @Override
            public void success(List<CommonClient> result) {
                System.out.println("Discovered " + result.size() + " devices async");
                checkRoof(manager);
                // All done here, let the main thread know so we can exit
                synchronized (manager) {
                    manager.notify();
                }
            }

            @Override
            public void error(AlpacaClientError error) {
                System.err.println("Error discovering devices: " + error.getErrorMessage());
            }
        });

        // Wait for async calls to complete.
        synchronized (manager) {
            try {
                manager.wait(20 * 1000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted waiting for async calls to complete");
                throw new RuntimeException(e);
            }
        }
        System.out.println("Test Done");
    }

    private static void checkRoof(ClientManager manager) {
        SafetyMonitorClient client = manager.getClient(SafetyMonitorClient.class);
        if (client == null) {
            System.err.println("Cannot find a safety monitor device");
            System.exit(1);
        }
        // Need to connect to the device before we can use it
        client.connect(new AlpacaCallback<>() {
            @Override
            public void success(Void result) {
                System.out.println("Connected to Safety Monitor");

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
            }

            @Override
            public void error(AlpacaClientError error) {
                System.err.println("Error connecting to device: " + error.getErrorMessage());
                System.exit(1);
            }

        });
    }
    private static void initLogger(Class classObject) {
        // Attempt to read the logging configuration from the classpath
        try {
            InputStream stream = classObject.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
