package org.ascom.alpaca.config;

import java.lang.annotation.*;

/**
 * Annotation used to specify a default value for configuration properties or methods.
 * If there is no property defined in the base properties file, this default value will be used
 * when updating the configuration object.
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultValue {
    String value();
}
