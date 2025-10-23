package io.github.mananshr.crud.util;

import java.time.Instant;
import java.util.Objects;

/**
 * Lightweight colored Logger utility used across the project.
 *
 * Usage:
 * - Static quick calls: Logger.info("message");
 * - Instance logger for class context: Logger.LoggerInstance log = Logger.getLogger(MyClass.class); log.info(...);
 *
 * Control:
 * - Disable colors with env var NO_COLOR=true or system property -Dmcronaut.color=false
 * - Disable all logging with Logger.setEnabled(false)
 * - Enable debug logs with system property -Dmcronaut.debug=true
 *
 * This is intentionally small and synchronous; for production use consider an async logging framework.
 */
public final class Logger {

    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_RED = "\u001B[41m";
    public static final String BG_GREEN = "\u001B[42m";

    // Control flags: NO_COLOR env var or system property "mcronaut.color=false" disables colors
    private static final boolean COLORS_ENABLED = !"true".equalsIgnoreCase(System.getenv("NO_COLOR"))
            && !"false".equalsIgnoreCase(System.getProperty("mcronaut.color", "true"));

    // Master switch to enable/disable logging at runtime. Volatile for safe cross-thread updates.
    private static volatile boolean ENABLED = true;

    /**
     * Enable or disable all logging produced by this utility.
     * Use false to silence logs (useful in tests or CI).
     */
    public static void setEnabled(boolean enabled) {
        ENABLED = enabled;
    }

    /**
     * Returns true when logging is enabled.
     */
    public static boolean isEnabled() {
        return ENABLED;
    }

    private Logger() { /* utility */ }

    public static void info(String msg, Object... args) {
        if (!ENABLED) return;
        log("INFO", BLUE, msg, args);
    }

    public static void success(String msg, Object... args) {
        if (!ENABLED) return;
        log("OK", GREEN, msg, args);
    }

    public static void warn(String msg, Object... args) {
        if (!ENABLED) return;
        log("WARN", YELLOW, msg, args);
    }

    public static void error(String msg, Object... args) {
        if (!ENABLED) return;
        logErr("ERROR", RED, msg, args);
    }

    public static void debug(String msg, Object... args) {
        // enabled when system property mcronaut.debug=true
        if (!ENABLED) return;
        if ("true".equalsIgnoreCase(System.getProperty("mcronaut.debug", "false"))) {
            log("DEBUG", CYAN, msg, args);
        }
    }

    public static void highlight(String msg, Object... args) {
        if (!ENABLED) return;
        log("HIGHLIGHT", MAGENTA + BG_YELLOW, msg, args);
    }

    private static void log(String level, String color, String msg, Object... args) {
        if (!ENABLED) return;
        String formatted = format(msg, args);
        String timestamp = Instant.now().toString();
        if (COLORS_ENABLED) {
            System.out.println(color + "[" + timestamp + "] [" + level + "] " + formatted + RESET);
        } else {
            System.out.println("[" + timestamp + "] [" + level + "] " + formatted);
        }
    }

    private static void logErr(String level, String color, String msg, Object... args) {
        if (!ENABLED) return;
        String formatted = format(msg, args);
        String timestamp = Instant.now().toString();
        if (COLORS_ENABLED) {
            System.err.println(color + "[" + timestamp + "] [" + level + "] " + formatted + RESET);
        } else {
            System.err.println("[" + timestamp + "] [" + level + "] " + formatted);
        }
    }

    private static String format(String msg, Object... args) {
        if (args == null || args.length == 0) {
            return msg;
        }
        try {
            return String.format(msg, args);
        } catch (Exception e) {
            // fallback if format fails
            return msg + " " + java.util.Arrays.toString(args);
        }
    }

    // New: instance-style logger to simplify refactoring from System.out/err
    public static LoggerInstance getLogger(Class<?> cls) {
        return new LoggerInstance(Objects.requireNonNull(cls).getSimpleName());
    }

    public static LoggerInstance getLogger(String name) {
        return new LoggerInstance(Objects.requireNonNull(name));
    }

    public static final class LoggerInstance {
        private final String name;

        private LoggerInstance(String name) {
            this.name = name;
        }

        public void info(String msg, Object... args) {
            if (!ENABLED) return;
            logWithName("INFO", BLUE, msg, args);
        }

        public void success(String msg, Object... args) {
            if (!ENABLED) return;
            logWithName("OK", GREEN, msg, args);
        }

        public void warn(String msg, Object... args) {
            if (!ENABLED) return;
            logWithName("WARN", YELLOW, msg, args);
        }

        public void error(String msg, Object... args) {
            if (!ENABLED) return;
            logErrWithName("ERROR", RED, msg, args);
        }

        public void debug(String msg, Object... args) {
            if (!ENABLED) return;
            if ("true".equalsIgnoreCase(System.getProperty("mcronaut.debug", "false"))) {
                logWithName("DEBUG", CYAN, msg, args);
            }
        }

        private void logWithName(String level, String color, String msg, Object... args) {
            if (!ENABLED) return;
            String formatted = format(msg, args);
            String timestamp = Instant.now().toString();
            String payload = String.format("[%s] [%s] %s", timestamp, name, formatted);
            if (COLORS_ENABLED) {
                System.out.println(color + "[" + level + "] " + payload + RESET);
            } else {
                System.out.println("[" + level + "] " + payload);
            }
        }

        private void logErrWithName(String level, String color, String msg, Object... args) {
            if (!ENABLED) return;
            String formatted = format(msg, args);
            String timestamp = Instant.now().toString();
            String payload = String.format("[%s] [%s] %s", timestamp, name, formatted);
            if (COLORS_ENABLED) {
                System.err.println(color + "[" + level + "] " + payload + RESET);
            } else {
                System.err.println("[" + level + "] " + payload);
            }
        }
    }
}
