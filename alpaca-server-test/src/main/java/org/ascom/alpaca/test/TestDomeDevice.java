package org.ascom.alpaca.test;

import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.DomeDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.ShutterState;

import jakarta.inject.Singleton;
import org.ascom.alpaca.model.StateValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class TestDomeDevice extends BaseDevice implements DomeDevice {
    private static final Logger log = LoggerFactory.getLogger(TestDomeDevice.class);
    private boolean isSlaved = false;
    private double altitude = 0;
    private double azimuth = 0;
    private boolean atHome = true;
    private boolean atPark = true;
    private boolean isSlewing = false;
    private ShutterState shutterState = ShutterState.Closed;

    public TestDomeDevice() {
        super(DeviceType.Dome, "Test Dome Driver", 3);
        setDescription("Test Dome Device");
        // TODO: figure out a version number system
        setDriverInfo(getDescription() + ". Version 1.0");
    }

    private void startSlewing(int seconds) {
        isSlewing = true;
        Thread t = new Thread(() -> {
            log.info("Slewing {} seconds", seconds);
            try {
                Thread.sleep(seconds* 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Done slewing {} seconds", seconds);
            isSlewing = false;
        });
        t.start();
    }

    @Override
    public List<StateValue> getDeviceState(int clientID) {
        checkConnectionStatus(clientID);
        List<StateValue> deviceState = new ArrayList<>();
        deviceState.add(new StateValue("Altitude", getAltitude(clientID)));
        deviceState.add(new StateValue("AtHome", atHome(clientID)));
        deviceState.add(new StateValue("AtPark", atPark(clientID)));
        deviceState.add(new StateValue("Azimuth", getAzimuth(clientID)));
        deviceState.add(new StateValue("ShutterStatus", getShutterStatus(clientID)));
        deviceState.add(new StateValue("Slewing", isSlewing(clientID)));
        return deviceState;
    }

    @Override
    public double getAltitude(int clientID) {
        return altitude;
    }

    @Override
    public boolean atHome(int clientID) {
        return atHome;
    }

    @Override
    public boolean atPark(int clientID) {
        return atPark;
    }

    @Override
    public double getAzimuth(int clientID) {
        return azimuth;
    }

    @Override
    public boolean canFindHome(int clientID) {
        return true;
    }

    @Override
    public boolean canPark(int clientID) {
        return true;
    }

    @Override
    public boolean canSetAltitude(int clientID) {
        return true;
    }

    @Override
    public boolean canSetAzimuth(int clientID) {
        return true;
    }

    @Override
    public boolean canSetPark(int clientID) {
        return true;
    }

    @Override
    public boolean canSetShutter(int clientID) {
        return true;
    }

    @Override
    public boolean canSlave(int clientID) {
        return true;
    }

    @Override
    public boolean canSyncAzimuth(int clientID) {
        return true;
    }

    @Override
    public ShutterState getShutterStatus(int clientID) {
        return shutterState;
    }

    @Override
    public boolean isSlaved(int clientID) {
        return isSlaved;
    }

    @Override
    public void setSlaved(int clientID, boolean slaved) {
        isSlaved = slaved;
    }

    @Override
    public boolean isSlewing(int clientID) {
        return isSlewing;
    }

    @Override
    public void abortSlew(int clientID) {
        isSlewing = false;
    }

    @Override
    public void closeShutter(int clientID) {
        shutterState = ShutterState.Closed;
    }

    @Override
    public void findHome(int clientID) {
        atHome = true;
    }

    @Override
    public void openShutter(int clientID) {
        shutterState = ShutterState.Open;
    }

    @Override
    public void park(int clientID) {
        atPark = true;
    }

    @Override
    public void setPark(int clientID) {
        atPark = true;
    }

    @Override
    public void slewToAltitude(int clientID, double altitude) {
        this.altitude = altitude;
        startSlewing(4);
    }

    @Override
    public void slewToAzimuth(int clientID, double azimuth) {
        this.azimuth = azimuth;
        startSlewing(5);
    }

    @Override
    public void syncToAzimuth(int clientID, double azimuth) {
        this.azimuth = azimuth;
    }
}
