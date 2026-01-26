package org.ascom.alpaca.config;

import java.lang.annotation.*;

/**
 * Annotation used to specify a default value for configuration properties or methods.
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultValue {
    String value();
}
