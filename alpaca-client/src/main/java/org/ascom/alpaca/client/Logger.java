package org.ascom.alpaca.client;

import okhttp3.internal.platform.Platform;

import java.text.MessageFormat;

/**
 * A simple logger that logs to the Okio platform.  Okio finds the platform's logger.
 * In the case of the JVM, it's java.util.logging.  In the case of Android, it's android.util.Log.
 */
public class Logger {
    private Platform platform = Platform.get();

    Logger() {
    }

    public static Logger getLogger() {
        return new Logger();
    }

    public static Logger getLogger(Class<?> clazz) {
        return new Logger();
    }

    public void debug(String message) {
        // Okio only supports INFO and WARN levels
        platform.log(Platform.INFO, message, null);
    }

    public void debug(String message, Object ... args) {
        // Okio only supports INFO and WARN levels
        platform.log(Platform.INFO, MessageFormat.format(message, args), null);
    }

    public void info(String message) {
        platform.log(Platform.INFO, message, null);
    }

    public void info(String message, Throwable t) {
        platform.log(Platform.INFO, message, t);
    }

    public void warn(String message) {
        platform.log(Platform.WARN, message, null);
    }

    public void warn(String message, Throwable t) {
        platform.log(Platform.WARN, message, t);
    }
}
