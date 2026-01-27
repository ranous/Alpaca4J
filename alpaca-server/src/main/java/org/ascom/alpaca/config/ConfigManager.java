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
 * The ConfigManager is used to create and manage configuration objects that are used
 * to store the configuration properties that are available to client devices to update.
 * The user creates a new class that should contain fields annotated with the {@code @Key}
 * annotation.  When requesting the configuration, the ConfigManager will load the properties
 * from the specified file and apply any overrides from the override file. The user creates
 * a properties file with key/value pairs that will be used to configure the device.
 * The config file is configured in the application.properties file for the service
 * using the following properties:
 *
 * setup.properties_file: The path to the properties file containing the default configuration.
 * setup.overrides_file: The path to the properties file containing override configuration.
 *
 * The properties file should be formatted as key=value pairs, with one pair per line.
 * When the user updates the configuration, the ConfigManager will apply the changes to the
 * configuration object and save the updated configuration to the override file.
 * The override file is not to be modified by the user.  It'll contain only the properties
 * that have been updated by the user that differ from the default configuration. You'd need
 * to check both files to get the full configuration.
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
     * Retrieves a configuration object for the specified class type. The first time this is
     * done for a given class type, ConfigManager will instanciate a new instance of the class,
     * and will inspect the object's fields annotated with {@code @Key} to bind configuration properties.
     * The Key annotation value should be the name of the configuration property to bind to the field.
     *
     * If the configuration is not already present in the cache, it will be loaded
     * and stored in the cache for future access. If any of the configuration properties
     * in the object are not found in the configuration file, they will be set to their default values.
     *
     * If any of the properties in the object have been updated by the client using the setup endpoint,
     * the updated values will be applied to the configuration object, so the updated configuration
     * is immediately available for use.
     *
     * If more than one type of device is running in this service, each device should create their
     * own config object and will have its own configuration object, and the ConfigManager will manage
     * them independently. A given device's configuration object will be used by all the devices of that type.
     * There is only one properties file, so all the properties used by different devices are in
     * a single file, so property names should be unique between devices.
     *
     * @param <T>            The type of the configuration object
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
     * cache is refreshed to reflect the updates, so all the active configuration objects are
     * immediately available for use.
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
