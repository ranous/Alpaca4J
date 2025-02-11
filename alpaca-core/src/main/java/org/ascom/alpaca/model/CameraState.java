package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@SuppressWarnings("unused")
public enum CameraState {
    CameraIdle(0),
    CameraWaiting(1),
    CameraExposing(2),
    CameraReading(3),
    CameraDownload(4),
    CameraError(5);

    private final int state;

    CameraState(int state) {
        this.state = state;
    }

    @JsonValue
    public int getCameraState() {
        return state;
    }

    @JsonCreator
    public static CameraState fromState(int state) {
        return switch (state) {
            case 0 -> CameraIdle;
            case 1 -> CameraWaiting;
            case 2 -> CameraExposing;
            case 3 -> CameraReading;
            case 4 -> CameraDownload;
            case 5 -> CameraError;
            default -> throw new IllegalArgumentException("The value " + state + " is not a valid pier side");
        };
    }
}
