package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCode {
    Success(0),
    PropertyNotImplemented(1024),
    InvalidValue(1025),
    ValueNotSet(1026),
    NotConnected(1031),
    InvalidWhileParked(1032),
    InvalidWhileSlaved(1033),
    InvalidOperation(1035),
    ActionNotImplemented(1036);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    @JsonCreator
    public static ErrorCode fromCode(int code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        throw new IllegalArgumentException("Invalid error code: " + code);
    }
}
