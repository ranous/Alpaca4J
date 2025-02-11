package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("SpellCheckingInspection")
public enum DeviceType {
    Camera("camera"),
    CoverCalibrator("covercalibrator"),
    Dome("dome"),
    FilterWheel("filterwheel"),
    Focuser("focuser"),
    ObservingConditions("observingconditions"),
    Rotator("rotator"),
    SafetyMonitor("safetymonitor"),
    Switch("switch"),
    Telescope("telescope"),
    Unknown("unknown");

    private static final Logger log = LoggerFactory.getLogger(DeviceType.class);
    private final String typeName;

    DeviceType(String typeName) {
        this.typeName = typeName;
    }

    @JsonValue
    public String getTypeName() {
        return this.typeName;
    }

    @JsonCreator
    public static DeviceType fromType(String name) {
        return switch (name) {
            case "camera" -> Camera;
            case "covercalibrator" -> CoverCalibrator;
            case "dome" -> Dome;
            case "filterwheel" -> FilterWheel;
            case "focuser" -> Focuser;
            case "observingconditions" -> ObservingConditions;
            case "rotator" -> Rotator;
            case "safetymonitor" -> SafetyMonitor;
            case "switch" -> Switch;
            case "telescope" -> Telescope;
            default -> {
                log.warn("Unrecognized device type: {}", name);
                yield Unknown;
            }
        };
    }
}
