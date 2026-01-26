package org.ascom.alpaca.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Manages application configuration properties and provides access to them through
 * configuration interfaces.
 */
@ApplicationScoped
public class ConfigManager {
    private static final Logger log = LoggerFactory.getLogger(ConfigManager.class);
    private final Map<Class<?>, Object> configCache = new HashMap<>();

    @Inject
    @ConfigProperty(name="setup.properties_file", defaultValue = "setup_config.properties")
    String configFile;
    @ConfigProperty(name="setup.overrides_file", defaultValue = "setup_override.properties")
    String overrideFile;

    ConfigManager() {
    }

    void onStart(@Observes Startup ev) {
        log.info("Loading config from {}", configFile);
    }

    /**
     * Retrieves a configuration object for the specified interface type.
     * If the configuration is not already present in the cache, it will be loaded
     * and stored in the cache for future access.
     *
     * @param <T>            The type of the configuration interface.
     * @param configInterface The {@code Class} object representing the configuration interface.
     *                        This class must have a default no-argument constructor and may contain
     *                        fields annotated with {@code @Key} to bind configuration properties.
     * @return The configuration object implementing the specified interface {@code T}.
     *         Returns a cached instance if available or a newly instantiated and populated instance otherwise.
     * @throws RuntimeException if there is a failure during configuration instantiation
     *                          or property loading.
     */
    @SuppressWarnings("unchecked")
    public synchronized <T> T getConfig(Class<T> configInterface) {
        return (T) configCache.computeIfAbsent(configInterface, k -> loadConfig(configInterface));
    }

    /**
     * Updates the configuration properties with the specified changes.
     * If a property's value differs from the existing value, it is updated, and the resulting
     * changes are persisted to the configuration file. Additionally, the in-memory configuration
     * cache is refreshed to reflect the updates.
     *
     * @param configChanges A map of configuration key-value pairs representing the changes to
     *                      be applied. Each entry contains a key as the property name and the
     *                      corresponding value as the new property value.
     */
    public synchronized void updateConfig(Map<String, String> configChanges) {
        Properties overrideProps = loadProperties(overrideFile);
        Properties defaultProps = loadProperties(configFile);
        for (String key : configChanges.keySet()) {
            String newValue = configChanges.get(key);
            String oldOverrideValue = overrideProps.getProperty(key);
            String defaultValue = defaultProps.getProperty(key);
            if ((oldOverrideValue == null && !newValue.equals(defaultValue)) || (oldOverrideValue != null && !oldOverrideValue.equals(newValue))) {
                if (newValue.equals(defaultValue)) {
                    log.info("Config key {} has been set from overriden value {} to default value {} - removing from overrides", key, oldOverrideValue, newValue);
                    overrideProps.remove(key);
                } else {
                    log.info("Updating config key {} from {} to value {}", key, oldOverrideValue, newValue);
                    overrideProps.setProperty(key, newValue);
                }
            }
        }
        try {
            overrideProps.store(Files.newOutputStream(Paths.get(overrideFile)), "Updated overidden setup configuration");
        } catch (IOException e) {
            log.warn("Could not write config file {}: {}", configFile, e.getMessage());
        }
        configCache.values().forEach(config -> loadConfig(config, loadProperties(configFile), overrideProps));
    }

    private Properties loadProperties(String filename) {
        Properties props = new Properties();
        try {
            props.load(Files.newInputStream(Paths.get(filename)));
        } catch (IOException e) {
            log.warn("Could not load config file {}: {}. Using defaults.", configFile, e.getMessage());
        }
        return props;
    }

    private Object loadConfig(Class<?> configClass) {
        try {
            Object config = configClass.getDeclaredConstructor().newInstance();
            loadConfig(config, loadProperties(configFile), loadProperties(overrideFile));
            return config;
        } catch (Exception e) {
            log.error("Failed to instantiate config class {}: {}", configClass.getName(), e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Iterates over the member variables in the supplied object and scans for
    // fields annotated with `@Key`. If a matching key is found in the properties,
    // the value is assigned to the field.
    private void loadConfig(Object config, Properties defaultProps, Properties overrideProps) {
        Class<?> clazz = config.getClass();
        // Iterates fields; processes those with `@Key`
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Key.class)) {
                Key keyAnnotation = field.getAnnotation(Key.class);
                String key = keyAnnotation.value();

                String value = overrideProps.getProperty(key);
                // if it's never been overridden, see if it is in the defaults prop
                if (value == null && defaultProps != null) {
                    value = defaultProps.getProperty(key);
                }

                if (value == null && field.isAnnotationPresent(DefaultValue.class)) {
                    value = field.getAnnotation(DefaultValue.class).value();
                }

                if (value != null) {
                    try {
                        field.setAccessible(true);
                        Object convertedValue = convertValue(field.getType(), value);
                        if (convertedValue != null) {
                            field.set(config, convertedValue);
                            log.debug("Assigned {} to field {}", value, field.getName());
                        }
                    } catch (IllegalAccessException e) {
                        log.error("Failed to set field {}: {}", field.getName(), e.getMessage());
                    }
                }
            }
        }
    }

    private Object convertValue(Class<?> type, String value) {
        try {
            if (type == String.class) {
                return value;
            } else if (type == int.class || type == Integer.class) {
                return value.isEmpty() ? 0 : Integer.parseInt(value);
            } else if (type == long.class || type == Long.class) {
                return value.isEmpty() ? 0 : Long.parseLong(value);
            } else if (type == double.class || type == Double.class) {
                return value.isEmpty() ? 0 : Double.parseDouble(value);
            } else if (type == boolean.class || type == Boolean.class) {
                return Boolean.parseBoolean(value);
            }
        } catch (Exception e) {
            log.warn("Cannot convert {} to {}: {}", value, type.getName(), e.getMessage());
            return null;
        }
        log.warn("Unsupported field type: {}", type.getName());
        return null;
    }
}
