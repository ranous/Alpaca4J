package org.ascom.alpaca.test;

import org.ascom.alpaca.device.BaseDevice;
import org.ascom.alpaca.device.DomeDevice;
import org.ascom.alpaca.model.DeviceType;
import org.ascom.alpaca.model.ShutterState;

import jakarta.inject.Singleton;
import org.ascom.alpaca.model.StateValue;
import org.eclipse.microprofile.config.inject.ConfigProperty;
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

    // The version of the driver is injected from the microprofile-config.properties file and can be overridden
    // by the system property test.driver.version
    public TestDomeDevice(@ConfigProperty(name="test.driver.version", defaultValue = "1.0") String deviceVersion) {
        super(DeviceType.Dome, "Test Dome Driver", DomeDevice.interfaceVersion, deviceVersion);
        setDescription("Test Dome Device");
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
    public List<StateValue> getDeviceState() {
        List<StateValue> deviceState = new ArrayList<>();
        deviceState.add(new StateValue("Altitude", getAltitude()));
        deviceState.add(new StateValue("AtHome", atHome()));
        deviceState.add(new StateValue("AtPark", atPark()));
        deviceState.add(new StateValue("Azimuth", getAzimuth()));
        deviceState.add(new StateValue("ShutterStatus", getShutterStatus()));
        deviceState.add(new StateValue("Slewing", isSlewing()));
        return deviceState;
    }

    @Override
    public double getAltitude() {
        return altitude;
    }

    @Override
    public boolean atHome() {
        return atHome;
    }

    @Override
    public boolean atPark() {
        return atPark;
    }

    @Override
    public double getAzimuth() {
        return azimuth;
    }

    @Override
    public boolean canFindHome() {
        return true;
    }

    @Override
    public boolean canPark() {
        return true;
    }

    @Override
    public boolean canSetAltitude() {
        return true;
    }

    @Override
    public boolean canSetAzimuth() {
        return true;
    }

    @Override
    public boolean canSetPark() {
        return true;
    }

    @Override
    public boolean canSetShutter() {
        return true;
    }

    @Override
    public boolean canSlave() {
        return true;
    }

    @Override
    public boolean canSyncAzimuth() {
        return true;
    }

    @Override
    public ShutterState getShutterStatus() {
        return shutterState;
    }

    @Override
    public boolean isSlaved() {
        return isSlaved;
    }

    @Override
    public void setSlaved(boolean slaved) {
        isSlaved = slaved;
    }

    @Override
    public boolean isSlewing() {
        return isSlewing;
    }

    @Override
    public void abortSlew() {
        isSlewing = false;
    }

    @Override
    public void closeShutter() {
        shutterState = ShutterState.Closed;
    }

    @Override
    public void findHome() {
        atHome = true;
    }

    @Override
    public void openShutter() {
        shutterState = ShutterState.Open;
    }

    @Override
    public void park() {
        atPark = true;
    }

    @Override
    public void setPark() {
        atPark = true;
    }

    @Override
    public void slewToAltitude(double altitude) {
        this.altitude = altitude;
        startSlewing(4);
    }

    @Override
    public void slewToAzimuth(double azimuth) {
        this.azimuth = azimuth;
        startSlewing(5);
    }

    @Override
    public void syncToAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }
}
