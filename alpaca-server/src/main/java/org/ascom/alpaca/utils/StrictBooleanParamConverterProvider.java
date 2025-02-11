package org.ascom.alpaca.utils;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * This ParamConverterProvider strictly parses the Boolean parameters.  The standard converter
 * for boolean will always return false when the boolean string value is not a valid true or false.
 */
@Provider
public class StrictBooleanParamConverterProvider implements ParamConverterProvider {
    private final StrictBooleanParamConverter converter = new StrictBooleanParamConverter();

    static class StrictBooleanParamConverter implements ParamConverter<Boolean> {
        @Override
        public Boolean fromString(String value) {
            if ("true".equalsIgnoreCase(value)) {
                return true;
            } else if ("false".equalsIgnoreCase(value)) {
                return false;
            }
            throw new IllegalArgumentException("Invalid boolean value: " + value);
        }

        @Override
        public String toString(Boolean value) {
            return value.toString();
        }
    }

    @Override
    @SuppressWarnings("unchecked") // Suppress warning since we ensure type safety
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType == Boolean.class) {
            return (ParamConverter<T>) converter;
        }
        return null;
    }
}
