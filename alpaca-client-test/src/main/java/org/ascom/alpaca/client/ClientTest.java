package org.ascom.alpaca.client;

import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.model.ImageArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

// This test program demonstrates the use of the AlpacaClient library to interact with Alpaca devices.
// it uses both the synchronous and asynchronous versions of the API calls.
public class ClientTest {
    private static ClientManager manager;
    public static void main(String[] args) {
        initLogger(ClientTest.class);
        syncTest();
        asyncTest();
        cameraTest();
        System.exit(0);
    }

    // This test calls the ClientManger using the synchronous version of the discoverDevices() method,
    // which will block until all devices are discovered or the timeout expires.  It then
    // gets a reference to the safety monitor device and checks its state.
    static void syncTest() {
        manager = ClientManager.getInstance();

        System.out.println("Sync Test - Discovering devices...");

        // discoverDevices broadcasts on the local network for any Alpaca devices and will
        // wait until all devices are discovered or the timeout expires
        manager.setResponseTimeout(3); // normally defaults to 5 seconds
        manager.discoverDevices();
        System.out.println("Discovered " + manager.getClients().size() + " devices");

        SafetyMonitorClient client = manager.getClient(SafetyMonitorClient.class);
        if (client == null) {
            System.err.println("Cannot find a safety monitor device");
            System.exit(1);
        }

        // Need to connect to the device before we can use it.  We're using the newer
        // Alpca connect() method, so this call can return before the connection is
        // connected on the server.  We'll need to wait for the connection to complete
        // before we can check the safety state.
        client.connect();
        try {
            client.awaitConnectionCompletion(1000);
        } catch (InterruptedException e) {
            System.err.println("Timed out waiting for connection to the safety monitor to complete");
        }

        if (client.isSafe()) {
            System.out.println("Safe to open the roof");
        } else {
            System.out.println("Not safe to open the roof");
        }
    }

    // This test does the same thing as the sync test, instead it uses the asynchronous versions of the Alpaca APIs.
    // These APIs use the AlpacaCallback interface to provide a callback mechanism for the client.
    static void asyncTest() {
        manager = ClientManager.getInstance();
        System.out.println("Discovering devices async...");

        // We're using a latch to let the main thread when all the async calls have completed.
        CountDownLatch done = new CountDownLatch(1);

        manager.discoverDevices(new AlpacaCallback<>() {
            @Override
            public void success(List<CommonClient> result) {
                System.out.println("Discovered " + result.size() + " devices async");
                // So we've discovered the devices, now check the roof state
                checkRoofAsync(manager, done);
            }

            @Override
            public void error(AlpacaClientError error) {
                System.err.println("Error discovering devices: " + error.getErrorMessage());
            }
        });

        try {
            boolean completed = done.await(10, TimeUnit.SECONDS);
            if (!completed) {
                System.err.println("Timed out waiting for async calls to complete");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted waiting for async calls to complete", e);
        }
        System.out.println("Async Test Done");
    }

    // Once we've discovered the devices, check the roof state using the asynchronous APIs.
    private static void checkRoofAsync(ClientManager manager, CountDownLatch done) {
        SafetyMonitorClient client = manager.getClient(SafetyMonitorClient.class);
        if (client == null) {
            System.err.println("Cannot find a safety monitor device");
            System.exit(1);
        }
        // Need to connect to the device before we can use it
        client.connect(new AlpacaCallback<>() {
            @Override
            public void success(Void result) {
                System.out.println("Connected to Safety Monitor asynchronously");

                client.isSafe(new AlpacaCallback<>() {
                    @Override
                    public void success(Boolean result) {
                        if (result) {
                            System.out.println("Async safe to open the roof");
                        } else {
                            System.out.println("Async not safe to open the roof");
                        }
                        // Let the main thread know we're done
                        done.countDown();
                    }

                    @Override
                    public void error(AlpacaClientError error) {
                        System.err.println("Error checking safety: " + error.getErrorMessage());
                        manager.notify();
                        // Let the main thread know we're done
                        done.countDown();
                    }
                });
            }

            @Override
            public void error(AlpacaClientError error) {
                done.countDown();
                System.err.println("Error connecting to device: " + error.getErrorMessage());
                System.exit(1);
            }
        });
    }

    // This demonstrates the use of the camera client to take an image and retreive it
    // using both the ImageArray and ImageBytes methods.
    static void cameraTest() {
        try {
            System.out.println("Camera Test - connectig to camera");
            // manager is initialized in the syncTest() method
            CameraClient client = manager.getClient(CameraClient.class);
            client.connect();
            try {
                client.awaitConnectionCompletion(5000);
            } catch (InterruptedException e) {
                System.err.println("Timed out waiting for connection to the safety monitor to complete");
            }

            System.out.println("Camera Test - setting binning to 1x1 and taking an image");
            client.setBinX(1);
            client.setBinY(1);

            long exposureTime = 5; // seconds
            client.startExposure(exposureTime, true);
            long start = System.currentTimeMillis();
            while (!client.isImageReady() && System.currentTimeMillis() - start < exposureTime * 1000 + 1000) {
                Thread.sleep(100);
            }
            ImageArray array2 = client.getImageBytes();
            ImageArray array = client.getImageArray();
            if (array.equals(array2)) {
                System.out.println("The arrays are equal");
            } else {
                System.out.println("The arrays are not equal");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }



    private static void initLogger(Class<?> classObject) {
        // Attempt to read the logging configuration from the classpath
        try {
            InputStream stream = classObject.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            System.err.println("Error reading logging configuration: " + e.getMessage());
        }
    }
}
