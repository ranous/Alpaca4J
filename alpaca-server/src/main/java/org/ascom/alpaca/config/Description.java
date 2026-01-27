package org.ascom.alpaca.config;

import java.lang.annotation.*;

/**
 * Annotation used to provide a description for configuration properties or methods
 * that will be used in the setup page for the device. If the annotation is applied
 * to the class itself, the SetupPageRenderer can use this description as additional
 * information on the device that can be displayed on the setup page.
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Description {
    String value();
}
