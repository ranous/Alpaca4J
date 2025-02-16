package org.ascom.alpaca;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Shutdown;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

@SuppressWarnings("unused")
@ApplicationScoped
public class DiscoveryService {
    private static final Logger log = LoggerFactory.getLogger(DiscoveryService.class);

    private Thread listenerThread;
    private boolean shouldExit = false;
    @Inject @ConfigProperty(name = "alpaca.discovery.port", defaultValue = "32227")
    int discoveryPort;
    @Inject @ConfigProperty(name = "alpaca.http.port", defaultValue = "11111")
    int alpacaPort;

    // Should be magically called on server startup
    void onStart(@Observes Startup ev) {
        startListener();
    }

    // Should be magically called on server shutdown
    void onStop(@Observes Shutdown ev) {
        stopListener();
    }

    public void startListener() {
        log.info("Starting Alpaca Discovery Protocol Listener");
        if (listenerThread != null) {
            log.warn("Discovery Listener already running");
            return;
        }
        listenerThread = new Thread("Alpaca Discovery Protocol Thread") {
            public synchronized void run() {
                listen();
            }
        };
        listenerThread.start();
    }

    public void stopListener() {
        shouldExit = true;
    }

    public int getDiscoveryPort() {
        return discoveryPort;
    }

    public void setDiscoveryPort(int discoveryPort) {
        this.discoveryPort = discoveryPort;
    }

    public int getAlpacaPort() {
        return alpacaPort;
    }

    public void setAlpacaPort(int alpacaPort) {
        this.alpacaPort = alpacaPort;
    }

    private void listen() {
        try (DatagramSocket socket = new DatagramSocket(discoveryPort, InetAddress.getByName("0.0.0.0"))) {
            byte[] buf = new byte[64];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            log.info("Discovery Listener started on port {}", discoveryPort);
            while (!shouldExit) {
                socket.receive(packet);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                log.info("Received discovery request from {}", address.getHostAddress());
                byte[] receiveBuf = packet.getData();
                //TODO:  verify this
                sendResponse(address, port);
            }
        } catch (Exception e) {
            log.warn("Problem with discovery listener {}", String.valueOf(e));
        }
        log.warn("Discovery Listener stopped");
    }

    private void sendResponse(InetAddress address, int port) {
        try (DatagramSocket socket = new DatagramSocket()) {
            String responseMessage = "{\"AlpacaPort\":" + alpacaPort + "}";
            byte[] responseBuffer = responseMessage.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(responseBuffer, responseBuffer.length, address, port);
            socket.send(packet);
            log.info("Sent discovery response {} to {}", responseMessage, address.getHostAddress());
        } catch (Exception e) {
            log.warn("Problem sending response in discovery listener{}", String.valueOf(e));
        }
    }
}
