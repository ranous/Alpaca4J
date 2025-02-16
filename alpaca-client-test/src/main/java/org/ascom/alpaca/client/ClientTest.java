package org.ascom.alpaca.client;

public class ClientTest {
    public static void main(String[] args) {
        ClientManager manager = new ClientManager();
        manager.discoverDevices();

        ObservingConditionsClient client = manager.getClient(ObservingConditionsClient.class);
        if (client == null) {
            System.err.println("Cannot find a observing conditions device");
            System.exit(1);
        }
        client.connect();
        double temp = client.getTemperature();
        System.out.println("Temperature is " + temp);
        System.exit(0);
    }
}
