package Que1_Message_Logger;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Date;

public class Logger {
    public enum Level { INFO, WARN, ERROR }

    private final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
    private final Thread flusherThread;
    private volatile boolean running = true;

    public Logger() {
        flusherThread = new Thread(() -> {
            try {
                while (running || !logQueue.isEmpty()) {
                    Thread.sleep(5000);
                    flushLogs();
                }
            } catch (InterruptedException e) {
                // Allow graceful shutdown
            }
            flushLogs(); // Final flush on exit
        });

        flusherThread.setDaemon(true); // Background thread
        flusherThread.start();
    }

    public void log(String message, Level level) {
        String timestamp = new Date().toString();
        String formatted = "[" + timestamp + "] [" + level + "] " + message;
        logQueue.offer(formatted);
    }

    public void shutdown() {
        running = false;
//        flusherThread.interrupt(); // Wake up if sleeping
        try {
            flusherThread.join(); // Wait for it to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void flushLogs() {
        String log;
        while ((log = logQueue.poll()) != null) {
            System.out.println(log);
        }
    }
}
