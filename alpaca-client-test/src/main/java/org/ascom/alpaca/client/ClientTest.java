package org.ascom.alpaca.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientTest {
    private static final Logger log = LoggerFactory.getLogger(ClientTest.class);
    public static void main(String[] args) {
        ClientManager manager = new ClientManager();
        manager.discoverDevices();

        ObservingConditionsClient client = manager.getClient(ObservingConditionsClient.class);
        if (client == null) {
            System.err.println("Cannot find a observing conditions device");
            System.exit(1);
        }
        double temp = client.getTemperature();
        System.out.println("Temperature is " + temp);
    }
}
