import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code LoggerUtility} class provides a simple logging utility to record system events
 * and messages into a log file with timestamps.
 *
 * <p>Each log entry is saved in the file {@code system_log.txt} and includes a timestamp
 * in the format {@code yyyy-MM-dd HH:mm:ss}.</p>
 */
public class LoggerUtility {
    private static final String LOG_FILE = "system_log.txt";

    /** Formatter for the timestamp used in log entries. */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Logs a message to the log file with a timestamp.
     *
     * <p>If the file cannot be written to, an error message is printed to standard error.</p>
     *
     * @param message the message to log
     */
    public static void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(formatter);
            writer.println("[" + timestamp + "] " + message);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}
