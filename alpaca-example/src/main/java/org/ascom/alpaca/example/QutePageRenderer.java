package org.ascom.alpaca.example;

import io.quarkus.qute.Template;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.ascom.alpaca.config.Description;
import org.ascom.alpaca.config.Key;
import org.ascom.alpaca.config.SetupPageRenderer;
import org.ascom.alpaca.model.DeviceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Renders configuration pages using Quarkus Qute templates.  The template
 * itself is located in src/main/resources/templates/config.html
 */
@ApplicationScoped
public class QutePageRenderer implements SetupPageRenderer {
    private static final Logger log = LoggerFactory.getLogger(QutePageRenderer.class);
    @Inject
    Template configTemplate;

    public record ConfigItem(String key, String label, String description, Object value, String type) { }

    @Override
    public String renderSetupPage(String title, DeviceType deviceType, int deviceID, Object config) {
        List<ConfigItem> items = new ArrayList<>();
        String description = "";
        if (config.getClass().isAnnotationPresent(Description.class)) {
            description = config.getClass().getAnnotation(Description.class).value();
        }
        for (Field field : config.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Key.class)) {
                try {
                    field.setAccessible(true);
                    String key = field.getAnnotation(Key.class).value();
                    String desc = field.isAnnotationPresent(Description.class)
                            ? field.getAnnotation(Description.class).value() : "";
                    Object val = field.get(config);
                    String type = field.getType().getSimpleName().toLowerCase();

                    items.add(new ConfigItem(key, field.getName(), desc, val, type));
                } catch (IllegalAccessException e) {
                    log.error("Failed to access field {}", field.getName(), e);
                }
            }
        }

        return configTemplate
                .data("items", items)
                .data("title", title)
                .data("description", description)
                .data("deviceType", deviceType.getTypeName())
                .data("deviceNumber", deviceID)
                .render();
    }

}
