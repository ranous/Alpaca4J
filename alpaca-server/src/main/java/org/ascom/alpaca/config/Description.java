package org.ascom.alpaca.config;

import java.lang.annotation.*;

/**
 * Annotation used to provide a description for configuration properties or methods.
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Description {
    String value();
}
