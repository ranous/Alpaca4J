package org.ascom.alpaca.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ascom.alpaca.client.model.AlpacaCallback;
import org.ascom.alpaca.client.model.AlpacaClientError;
import org.ascom.alpaca.client.util.Logger;
import org.ascom.alpaca.model.DeviceDescriptor;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.ServerInfo;

import java.net.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * The client manager is used to discover and instantiate clients for Alpaca devices.
 */
@SuppressWarnings({"unused", "FieldCanBeLocal", "SpellCheckingInspection"})
public class ClientManager {
    private static final Logger log = Logger.getLogger(ClientManager.class);
    private static final ClientManager instance = new ClientManager();

    private final String broadcastMessage = "alpacadiscovery1";
    private final int discoveryPort = 32227;
    private final int currentClientID = new Random().nextInt(Integer.MAX_VALUE);
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    private final Map<AlpacaServerInfo, List<CommonClient>> servers = Collections.synchronizedMap(new HashMap<>());
    private int defaultResponseTimeout = 5; // 5 seconds

    record AlpacaDiscoveryResponse(@JsonProperty("AlpacaPort") int alpacaPort) {}

    record AlpacaServerInfo(InetAddress address, int alpacaPort, ServerInfo serverInfo) {}


    ClientManager() {

    }

    /**
     * Returns the singleton instance of the ClientManager.
     * @return
     */
    public static ClientManager getInstance() {
        return instance;
    }

    /**
     * The amount of time in seconds the client will wait for responses to the discovery broadcast for Alpaca devices.
     * @return The amount of time in seconds the ClientManager will wait for responses.
     */
    public int getResponseTimeout() {
        return defaultResponseTimeout;
    }

    /**
     * Set the timeout in seconds the ClientManger should wait for responses when broadcasting to discover Alpaca devices
     * @param responseTimeout the amount of time to wait for responses in seconds
     */
    public void setResponseTimeout(int responseTimeout) {
        defaultResponseTimeout = responseTimeout;
    }

    /**
     * Returns a list of all the devices discovered using the Alpaca discovery protocol
     * @return a list of the devices known via the discovery protocol
     */
    public List<CommonClient> getClients() {
        return servers.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * Returns the first instance of a client of the supplied device type
     * @param type the type of client to return
     * @return first instance of that type of client, or null if no client of that type.
     */
    @SuppressWarnings("unchecked") // Suppress warning since we ensure type safety
    public <T extends CommonClient> T getClient(Class<T> type) {
        return (T) servers.values().stream().flatMap(List::stream).filter(type::isInstance).findFirst().orElse(null);
    }

    /**
     * Returns all the instances of clients of the supplied client type
     * @param type the type of client to return
     * @return list of clients of the requested type
     */
    @SuppressWarnings("unchecked") // Suppress warning since we ensure type safety
    public <T extends CommonClient> List<T> getClients(Class<T> type) {
        return (List<T>) servers.values().stream().flatMap(List::stream).filter(type::isInstance).collect(Collectors.toList());
    }

    /**
     * Returns all the clients associated with a given address
     */
    public List<CommonClient> getClients(InetAddress address) {
        for (AlpacaServerInfo info: servers.keySet()) {
            if (info.address.equals(address)) {
                return servers.get(info);
            }
        }
        return null;
    }

    /**
     * Discovers Alpaca devices on the local network and returns a list of the discovered devices.
     * This method is asynchronous and will return immediately.  The callback will be called when the
     * discovery is complete.
     */
    public void discoverDevices(AlpacaCallback<List<CommonClient>> callback) {
        discoverDevices(defaultResponseTimeout, callback);
    }

    /**
     * Discovers Alpaca devices on the local network and returns a list of the discovered devices.
     * This method is asynchronous and will return immediately.  The callback will be called when the
     * discovery is complete.
     *
     * @param timeout the amount of time to wait for responses
     * @param callback the callback to call after the discovery completes or an error occurs.
     */
    public void discoverDevices(int timeout, AlpacaCallback<List<CommonClient>> callback) {
        executor.submit(() -> {
            try {
                discoverDevices();
                callback.success(getClients());
            } catch (Exception e) {
                callback.error(new AlpacaClientError(e.getMessage()));
            }
        });
    }

    /**
     * Broadcasts on the local network for any Alpaca devices
     */
    public void discoverDevices() {
        discoverDevices(defaultResponseTimeout);
    }

    /**
     * Broadcasts on the local network for any Alpaca devices using the supplied timeout.
     *
     * @param responseTimeout the amount of time to wait for responses
     */
    public void discoverDevices(int responseTimeout) {
        try (DatagramSocket socket= new DatagramSocket()) {
            try {
                socket.setBroadcast(true);
                byte[] buffer = broadcastMessage.getBytes();

                InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");

                DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, broadcastAddress, discoveryPort);
                log.info("Sending broadcast message");
                socket.send(sendPacket);
            } catch (Exception e) {
                log.warn("Problem sending the Alpaca discovery request", e);
                return;
            }

            try {
                byte[] recvBuf = new byte[1024];

                DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
                log.info("Discovery Listener started on port " + discoveryPort);

                long timeout = System.currentTimeMillis() + (long) responseTimeout * 1000;
                while (System.currentTimeMillis() < timeout) {
                    log.info("Discovery listener Waiting");
                    socket.setSoTimeout(responseTimeout * 1000);
                    socket.receive(recvPacket);
                    InetAddress address = recvPacket.getAddress();
                    byte[] receiveBuf = recvPacket.getData();

                    ObjectMapper mapper = new ObjectMapper();
                    AlpacaDiscoveryResponse response = mapper.readValue(receiveBuf, AlpacaDiscoveryResponse.class);

                    log.info("Received a discovery response from " + address.getHostAddress() + ", port " + response.alpacaPort);
                    int port = 11111;
                    // let's interogate the server in a separate thread as to not delay hearing from other servers
                    executor.submit(() -> interrogateAlpacaServer(address, port));
                }

                executor.shutdown();
                // Wait for the interogation of alpaca servers for a while to finish up
                if (!executor.awaitTermination(500000, TimeUnit.SECONDS)) {
                    log.warn("Alpaca discovery thread timed out - killing");
                    // Shoot any straglers in the head
                    executor.shutdownNow();
                }

                log.info("Discovery Listener stopped");
            } catch (SocketTimeoutException e) {
                log.debug("Alpaca discovery request timed out");
            } catch (Exception e) {
                log.warn("Problem receiving Alpaca discovery responses", e);
            }
        } catch (SocketException e) {
            log.warn("Problem with Alpaca discovery socket", e);
        }
    }

    private void interrogateAlpacaServer(InetAddress address, int port) {
        try {
            URI uri = new URI("http", null, address.getHostAddress(), port, null, null, null);
            ManagementClient managementClient = new ManagementClient(uri, currentClientID);
            List<Integer> versions = managementClient.getApiVersions();
            ServerInfo serverInfo = managementClient.getDescription();
            AlpacaServerInfo server = new AlpacaServerInfo(address, port, serverInfo);
            List<CommonClient> clients = new ArrayList<>();

            List<DeviceDescriptor> descriptors = managementClient.getConfiguredDevices();
            log.info("Received " + descriptors.size() + " devices from server " + serverInfo.getServerName() +" running on " + address.getHostAddress());

            for (DeviceDescriptor descriptor : descriptors) {
                DeviceType type = descriptor.getDeviceType();
                CommonClient client = null;
                switch (type) {
                    case Camera -> client = new CameraClient(uri, descriptor, currentClientID);
                    case CoverCalibrator -> client = new CoverCalibratorClient(uri, descriptor, currentClientID);
                    case Dome -> client = new DomeClient(uri, descriptor, currentClientID);
                    case FilterWheel -> client = new FilterWheelClient(uri, descriptor, currentClientID);
                    case Focuser -> client = new FocuserClient(uri, descriptor, currentClientID);
                    case ObservingConditions -> client = new ObservingConditionsClient(uri, descriptor, currentClientID);
                    case Rotator -> client = new RotatorClient(uri, descriptor, currentClientID);
                    case SafetyMonitor -> client = new SafetyMonitorClient(uri, descriptor, currentClientID);
                    case Switch -> client = new SwitchClient(uri, descriptor, currentClientID);
                    case Telescope -> client = new TelescopeClient(uri, descriptor, currentClientID);
                    case Unknown -> log.warn("Received a unknown client type: " + type + " during discovery from server" + uri);
                    default -> log.warn("Received a client of type " + type + " during discovery from server " + uri + " which is unimplemented");
                 }
                if (client != null) {
                    clients.add(client);
                }
            }
            servers.put(server, clients);
        } catch (Throwable e) {
            log.warn("Problem interrogating Alpaca server running at " + address, e);
        }
    }
}
