package org.ascom.alpaca.config;

import java.lang.annotation.*;

/**
 * Annotation used to mark configuration keys for injection or retrieval.
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Key {
    String value();
}
